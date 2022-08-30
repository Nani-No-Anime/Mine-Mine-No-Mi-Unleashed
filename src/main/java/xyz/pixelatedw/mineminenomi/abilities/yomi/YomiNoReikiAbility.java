/*     */ package xyz.pixelatedw.mineminenomi.abilities.yomi;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.play.server.SEntityVelocityPacket;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.DamagedContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IExtraUpdateData;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.PhysicalBodyEntity;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IOutOfBodyAbility;
/*     */ 
/*     */ public class YomiNoReikiAbility extends DamagedContinuousAbility implements IOutOfBodyAbility, IExtraUpdateData {
/*  23 */   public static final YomiNoReikiAbility INSTANCE = new YomiNoReikiAbility();
/*     */   
/*     */   private PhysicalBodyEntity body;
/*     */   
/*     */   private BlockPos pivotPoint;
/*     */   
/*     */   public YomiNoReikiAbility() {
/*  30 */     super("Yomi no Reiki", AbilityHelper.getDevilFruitCategory());
/*  31 */     setDescription("The user's spirit leaves their body, allowing them to freely explore the nearby areas");
/*  32 */     setThreshold(200.0D);
/*     */     
/*  34 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/*  35 */     this.duringContinuityEvent = this::duringContinuityEvent;
/*  36 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*  37 */     this.onDamagedEvent = this::onDamagedEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onStartContinuityEvent(PlayerEntity player) {
/*  42 */     if (player.isCreative() || player.isSpectator()) {
/*     */       
/*  44 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_SUVIVAL_ONLY, new Object[0]));
/*  45 */       return false;
/*     */     } 
/*     */     
/*  48 */     player.setMotion(0.0D, 5.0D, 0.0D);
/*  49 */     ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
/*  50 */     player.velocityChanged = true;
/*     */     
/*  52 */     this.body = new PhysicalBodyEntity(player.world);
/*  53 */     this.body.setPositionAndRotation(player.getPosX(), player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
/*  54 */     this.body.setOwner((LivingEntity)player);
/*  55 */     player.world.addEntity((Entity)this.body);
/*  56 */     this.body.setHealth(player.getHealth());
/*  57 */     this.body.setParentAbility((Ability)this);
/*     */     
/*  59 */     this.pivotPoint = new BlockPos(this.body.getPositionVec().getX(), this.body.getPositionVec().getY(), this.body.getPositionVec().getZ());
/*     */     
/*  61 */     startOutOfBody(player);
/*     */     
/*  63 */     WyNetwork.sendToAllTrackingAndSelf(new SSyncAbilityDataPacket(player.getEntityId(), AbilityDataCapability.get((LivingEntity)player)), (LivingEntity)player);
/*     */     
/*  65 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringContinuityEvent(PlayerEntity player, int continueTime) {
/*  70 */     if (Math.sqrt(player.getDistanceSq(this.pivotPoint.getX(), this.pivotPoint.getY(), this.pivotPoint.getZ())) > getMaxRange()) {
/*     */       
/*  72 */       endContinuity(player);
/*     */       
/*     */       return;
/*     */     } 
/*  76 */     if (this.pivotPoint.getX() != (this.body.getPositionVec()).x || this.pivotPoint.getZ() != (this.body.getPositionVec()).z || this.pivotPoint.getY() != (this.body.getPositionVec()).y) {
/*  77 */       this.pivotPoint = this.body.getPosition();
/*     */     }
/*  79 */     if (this.body == null) {
/*     */       
/*  81 */       endContinuity(player);
/*     */       
/*     */       return;
/*     */     } 
/*  85 */     if (!this.body.isAlive()) {
/*  86 */       player.attackEntityFrom(DamageSource.MAGIC, player.getMaxHealth());
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean onEndContinuityEvent(PlayerEntity player) {
/*  91 */     double cooldown = (this.continueTime / 20.0F);
/*     */     
/*  93 */     setMaxCooldown(cooldown);
/*     */     
/*  95 */     if (this.body != null) {
/*     */       
/*  97 */       this.body.remove();
/*  98 */       this.body = null;
/*  99 */       player.setPositionAndUpdate(this.pivotPoint.getX(), this.pivotPoint.getY(), this.pivotPoint.getZ());
/*     */     } 
/*     */     
/* 102 */     stopOutOfBody(player);
/*     */     
/* 104 */     return true;
/*     */   }
/*     */   
/*     */   private boolean onDamagedEvent(LivingEntity entity, DamageSource damageSource, double v) {
/* 108 */     if (entity instanceof PlayerEntity) {
/* 109 */       if (((PlayerEntity)entity).isCreative() || entity.isSpectator()) {
/* 110 */         return false;
/*     */       }
/* 112 */       return (damageSource != DamageSource.MAGIC && !damageSource.isDamageAbsolute());
/*     */     } 
/* 114 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getMaxRange() {
/* 120 */     return 60.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockPos getPivotPoint() {
/* 126 */     return this.pivotPoint;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundNBT getExtraData() {
/* 132 */     CompoundNBT nbt = new CompoundNBT();
/* 133 */     if (this.pivotPoint != null) {
/*     */       
/* 135 */       nbt.putDouble("x", this.pivotPoint.getX());
/* 136 */       nbt.putDouble("y", this.pivotPoint.getY());
/* 137 */       nbt.putDouble("z", this.pivotPoint.getZ());
/*     */     } 
/* 139 */     return nbt;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExtraData(CompoundNBT nbt) {
/* 145 */     double x = nbt.getDouble("x");
/* 146 */     double y = nbt.getDouble("y");
/* 147 */     double z = nbt.getDouble("z");
/* 148 */     this.pivotPoint = new BlockPos(x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPhysical() {
/* 154 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\yomi\YomiNoReikiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */