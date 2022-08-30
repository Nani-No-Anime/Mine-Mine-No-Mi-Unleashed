/*     */ package xyz.pixelatedw.mineminenomi.abilities.bara;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IExtraUpdateData;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.entities.BottomHalfBodyEntity;
import xyz.pixelatedw.mineminenomi.entities.zoan.BaraSplitZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IOutOfBodyAbility;
/*     */ 
/*     */ public class BaraSplitAbility extends ZoanAbility implements IOutOfBodyAbility, IExtraUpdateData {
/*  21 */   public static final BaraSplitAbility INSTANCE = new BaraSplitAbility();
/*     */   
/*     */   private BottomHalfBodyEntity legs;
/*     */   
/*     */   private BlockPos pivotPoint;
/*     */   
/*     */   public BaraSplitAbility() {
/*  28 */     super("Bara Split", AbilityHelper.getDevilFruitCategory());
/*  29 */     setMaxCooldown(15.0D);
/*  30 */     setThreshold(50.0D);
/*  31 */     setDescription("Allows the user to split its upper part of the body from the lower.");
/*     */     
/*  33 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/*  34 */     this.duringContinuityEvent = this::duringContiunityEvent;
/*  35 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean onStartContinuityEvent(PlayerEntity player) {
/*  41 */     if (!super.onStartContinuityEvent(player) || !AbilityHelper.canUseMomentumAbility(player)) {
/*  42 */       return false;
/*     */     }
/*  44 */     player.setMotion(0.0D, 2.0D, 0.0D);
/*  45 */     ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
/*  46 */     player.velocityChanged = true;
/*     */     
/*  48 */     this.legs = new BottomHalfBodyEntity(player.world);
/*  49 */     this.legs.setPositionAndRotation(player.getPosX(), player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
/*  50 */     this.legs.setOwner((LivingEntity)player);
/*  51 */     player.world.addEntity((Entity)this.legs);
/*  52 */     this.legs.setHealth(player.getHealth());
/*  53 */     this.legs.setParentAbility((Ability)this);
/*     */     
/*  55 */     startOutOfBody(player);
/*     */     
/*  57 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringContiunityEvent(PlayerEntity player, int time) {
/*  62 */     if (this.legs == null) {
/*     */       
/*  64 */       endContinuity(player);
/*     */       
/*     */       return;
/*     */     } 
/*  68 */     if (Math.sqrt(player.getDistanceSq(this.legs.getPosX(), this.legs.getPosY(), this.legs.getPosZ())) > getMaxRange()) {
/*     */       
/*  70 */       endContinuity(player);
/*     */       
/*     */       return;
/*     */     } 
/*  74 */     if (this.legs != null) {
/*     */       
/*  76 */       if (!this.legs.isAlive()) {
/*  77 */         player.attackEntityFrom(DamageSource.MAGIC, player.getMaxHealth());
/*     */       }
/*  79 */       this.pivotPoint = this.legs.getPosition();
/*     */     } 
/*     */     
/*  82 */     if (time > 10 && player.onGround) {
/*     */       
/*  84 */       endContinuity(player);
/*     */       
/*     */       return;
/*     */     } 
/*  88 */     if (time % 20 == 0) {
/*  89 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, (Ability)this), (LivingEntity)player);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean onEndContinuityEvent(PlayerEntity player) {
/*  95 */     if (!super.onEndContinuityEvent(player)) {
/*  96 */       return false;
/*     */     }
/*  98 */     double cooldown = (this.continueTime / 20.0F);
/*     */     
/* 100 */     setMaxCooldown(cooldown);
/* 101 */     player.fallDistance = 0.0F;
/*     */     
/* 103 */     if (this.legs != null) {
/*     */       
/* 105 */       player.setPositionAndUpdate(this.legs.getPosX(), this.legs.getPosY(), this.legs.getPosZ());
/* 106 */       this.legs.remove();
/* 107 */       this.legs = null;
/*     */     } 
/*     */     
/* 110 */     stopOutOfBody(player);
/*     */     
/* 112 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getMaxRange() {
/* 118 */     return 30.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockPos getPivotPoint() {
/* 124 */     return this.pivotPoint;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundNBT getExtraData() {
/* 130 */     CompoundNBT nbt = new CompoundNBT();
/* 131 */     if (this.pivotPoint != null) {
/*     */       
/* 133 */       nbt.putDouble("x", this.pivotPoint.getX());
/* 134 */       nbt.putDouble("y", this.pivotPoint.getY());
/* 135 */       nbt.putDouble("z", this.pivotPoint.getZ());
/*     */     } 
/* 137 */     return nbt;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExtraData(CompoundNBT nbt) {
/* 143 */     double x = nbt.getDouble("x");
/* 144 */     double y = nbt.getDouble("y");
/* 145 */     double z = nbt.getDouble("z");
/* 146 */     this.pivotPoint = new BlockPos(x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ZoanInfo getTransformation() {
/* 152 */     return (ZoanInfo)BaraSplitZoanInfo.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPhysical() {
/* 158 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\bara\BaraSplitAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */