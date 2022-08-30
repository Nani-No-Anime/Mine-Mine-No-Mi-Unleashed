/*     */ package xyz.pixelatedw.mineminenomi.abilities.ryupteranodon;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.Optional;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IExtraUpdateData;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.entities.zoan.PteranodonFlyZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.animations.pteranodon.OpenMouthAnimation;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ public class TankyudonAbility extends ContinuousAbility implements IFormRequiredAbility, IAnimatedAbility, IExtraUpdateData {
/*  31 */   public static final TankyudonAbility INSTANCE = new TankyudonAbility();
/*     */   
/*  33 */   private LivingEntity grabbedEntity = null;
/*  34 */   private int grabbedEntityId = -1;
/*     */ 
/*     */   
/*     */   public TankyudonAbility() {
/*  38 */     super("Tankyudon", AbilityHelper.getDevilFruitCategory());
/*  39 */     setMaxCooldown(10.0D);
/*  40 */     setThreshold(10.0D);
/*  41 */     setDescription("The user dashes forward and grabs the enemy, dealing damage while doing so");
/*     */     
/*  43 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/*  44 */     this.duringContinuityEvent = this::duringContinuityEvent;
/*  45 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onStartContinuityEvent(PlayerEntity player) {
/*  50 */     if (!AbilityHelper.canUseMomentumAbility(player)) {
/*  51 */       return false;
/*     */     }
/*  53 */     this.grabbedEntity = null;
/*  54 */     player.world.playSound(null, player.getPosition(), ModSounds.DASH_ABILITY_SWOOSH_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*     */     
/*  56 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringContinuityEvent(PlayerEntity player, int time) {
/*  61 */     if (this.grabbedEntity == null) {
/*     */       
/*  63 */       if (canGrab()) {
/*     */         
/*  65 */         Optional<LivingEntity> target = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.2D, new Class[] { LivingEntity.class }).stream().filter(e -> (e != player)).findFirst();
/*  66 */         if (target.isPresent())
/*     */         {
/*  68 */           LivingEntity e = target.get();
/*  69 */           if (!e.isAlive() || (
/*  70 */             DevilFruitCapability.get(e).isLogia() && !HakiHelper.hasHardeningActive((LivingEntity)player)) || 
/*  71 */             AbilityHelper.isTargetBlockingAbility((LivingEntity)player, e)) {
/*     */             
/*  73 */             endContinuity(player);
/*     */             return;
/*     */           } 
/*  76 */           this.grabbedEntity = e;
/*  77 */           this.grabbedEntityId = e.getEntityId();
/*  78 */           WyNetwork.sendTo(new SUpdateEquippedAbilityPacket(player, (Ability)this), player);
/*  79 */           this.grabbedEntity.attackEntityFrom((DamageSource)ModDamageSource.causeAbilityDamage((LivingEntity)player, (Ability)this, "player"), 8.0F);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/*  84 */         endContinuity(player);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/*  89 */       if (!this.grabbedEntity.isAlive() || this.grabbedEntity
/*  90 */         .getDistanceSq((Entity)player) > 32.0D || (
/*  91 */         DevilFruitCapability.get(this.grabbedEntity).isLogia() && !HakiHelper.hasHardeningActive((LivingEntity)player)) || 
/*  92 */         AbilityHelper.isTargetBlockingAbility((LivingEntity)player, this.grabbedEntity)) {
/*     */         
/*  94 */         endContinuity(player);
/*     */         
/*     */         return;
/*     */       } 
/*  98 */       this.grabbedEntity.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1));
/*  99 */       this.grabbedEntity.setMotion(0.0D, 0.0D, 0.0D);
/*     */       
/* 101 */       float distance = 2.0F;
/* 102 */       Vec3d lookVec = player.getLookVec();
/* 103 */       Vec3d pos = new Vec3d(player.getPosX() + lookVec.x * distance, player.getPosY() + player.getEyeHeight() - 2.0D + lookVec.y * distance, player.getPosZ() + lookVec.z * distance);
/* 104 */       this.grabbedEntity.setPositionAndUpdate(pos.x, pos.y, pos.z);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 110 */     this.grabbedEntity = null;
/* 111 */     this.grabbedEntityId = -1;
/* 112 */     WyNetwork.sendTo(new SUpdateEquippedAbilityPacket(player, (Ability)this), player);
/* 113 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canGrab() {
/* 118 */     return (isContinuous() && this.continueTime > 0 && this.grabbedEntityId <= 0 && this.continueTime < getThreshold() * 0.5D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundNBT getExtraData() {
/* 124 */     CompoundNBT nbt = new CompoundNBT();
/* 125 */     nbt.putInt("grabbedEntity", this.grabbedEntityId);
/* 126 */     return nbt;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExtraData(CompoundNBT nbt) {
/* 132 */     this.grabbedEntityId = nbt.getInt("grabbedEntity");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ZoanInfo[] getRequiredForms(PlayerEntity player) {
/* 138 */     return new ZoanInfo[] { (ZoanInfo)PteranodonFlyZoanInfo.INSTANCE };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IAnimation getAnimation() {
/* 144 */     return (IAnimation)OpenMouthAnimation.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimationActive() {
/* 150 */     return canGrab();
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\ryupteranodon\TankyudonAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */