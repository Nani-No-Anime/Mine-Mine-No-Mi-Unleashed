/*     */ package xyz.pixelatedw.mineminenomi.abilities.horo;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SEntityVelocityPacket;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.DamagedContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IExtraUpdateData;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IOutOfBodyAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.PhysicalBodyEntity;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ 
/*     */ public class YutaiRidatsuAbility extends DamagedContinuousAbility implements IOutOfBodyAbility, IExtraUpdateData {
/*  20 */   public static final YutaiRidatsuAbility INSTANCE = new YutaiRidatsuAbility();
/*     */   
/*     */   private PhysicalBodyEntity body;
/*     */   
/*     */   private BlockPos pivotPoint;
/*     */   
/*     */   public YutaiRidatsuAbility() {
/*  27 */     super("Yutai Ridatsu", AbilityHelper.getDevilFruitCategory());
/*  28 */     setDescription("The user's spirit leaves their body, allowing them to freely explore the nearby areas");
/*  29 */     setThreshold(60.0D);
/*     */     
/*  31 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/*  32 */     this.duringContinuityEvent = this::duringContinuityEvent;
/*  33 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*  34 */     this.onDamagedEvent = this::onDamagedEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onStartContinuityEvent(PlayerEntity player) {
/*  39 */     if (player.isCreative() || player.isSpectator()) {
/*     */       
/*  41 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_SUVIVAL_ONLY, new Object[0]));
/*  42 */       return false;
/*     */     } 
/*     */     
/*  45 */     player.setMotion(0.0D, 5.0D, 0.0D);
/*  46 */     ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
/*  47 */     player.velocityChanged = true;
/*     */     
/*  49 */     this.body = new PhysicalBodyEntity(player.world);
/*  50 */     this.body.setPositionAndRotation(player.getPosX(), player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
/*  51 */     this.body.setOwner((LivingEntity)player);
/*  52 */     player.world.addEntity((Entity)this.body);
/*  53 */     this.body.setHealth(player.getHealth());
/*  54 */     this.body.setParentAbility((Ability)this);
/*     */     
/*  56 */     this.pivotPoint = new BlockPos(this.body.getPositionVec().getX(), this.body.getPositionVec().getY(), this.body.getPositionVec().getZ());
/*     */     
/*  58 */     startOutOfBody(player);
/*     */     
/*  60 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringContinuityEvent(PlayerEntity player, int continueTime) {
/*  65 */     if (Math.sqrt(player.getDistanceSq(this.pivotPoint.getX(), this.pivotPoint.getY(), this.pivotPoint.getZ())) > getMaxRange()) {
/*     */       
/*  67 */       endContinuity(player);
/*     */       
/*     */       return;
/*     */     } 
/*  71 */     if (this.pivotPoint.getX() != (this.body.getPositionVec()).x || this.pivotPoint.getZ() != (this.body.getPositionVec()).z || this.pivotPoint.getY() != (this.body.getPositionVec()).y) {
/*  72 */       this.pivotPoint = this.body.getPosition();
/*     */     }
/*  74 */     if (this.body == null) {
/*     */       
/*  76 */       endContinuity(player);
/*     */       
/*     */       return;
/*     */     } 
/*  80 */     if (!this.body.isAlive()) {
/*  81 */       player.attackEntityFrom(DamageSource.MAGIC, player.getMaxHealth());
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean onEndContinuityEvent(PlayerEntity player) {
/*  86 */     double cooldown = (this.continueTime / 20.0F);
/*  87 */     setMaxCooldown(cooldown);
/*     */     
/*  89 */     if (this.body != null) {
/*     */       
/*  91 */       player.setPositionAndUpdate(this.pivotPoint.getX(), this.pivotPoint.getY(), this.pivotPoint.getZ());
/*  92 */       this.body.remove();
/*  93 */       this.body = null;
/*     */     } 
/*     */     
/*  96 */     stopOutOfBody(player);
/*     */     
/*  98 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onDamagedEvent(LivingEntity entity, DamageSource damageSource, double v) {
/* 103 */     if (entity instanceof PlayerEntity) {
/*     */       
/* 105 */       if (((PlayerEntity)entity).isCreative() || entity.isSpectator()) {
/* 106 */         return false;
/*     */       }
/* 108 */       return (damageSource == DamageSource.MAGIC || damageSource.isDamageAbsolute());
/*     */     } 
/* 110 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getMaxRange() {
/* 116 */     return 40.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockPos getPivotPoint() {
/* 122 */     return this.pivotPoint;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundNBT getExtraData() {
/* 128 */     CompoundNBT nbt = new CompoundNBT();
/* 129 */     if (this.pivotPoint != null) {
/*     */       
/* 131 */       nbt.putDouble("x", this.pivotPoint.getX());
/* 132 */       nbt.putDouble("y", this.pivotPoint.getY());
/* 133 */       nbt.putDouble("z", this.pivotPoint.getZ());
/*     */     } 
/* 135 */     return nbt;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExtraData(CompoundNBT nbt) {
/* 141 */     double x = nbt.getDouble("x");
/* 142 */     double y = nbt.getDouble("y");
/* 143 */     double z = nbt.getDouble("z");
/* 144 */     this.pivotPoint = new BlockPos(x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPhysical() {
/* 150 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\horo\YutaiRidatsuAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */