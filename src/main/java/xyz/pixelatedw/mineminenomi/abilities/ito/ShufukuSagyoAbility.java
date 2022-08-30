/*     */ package xyz.pixelatedw.mineminenomi.abilities.ito;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.DamagedContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IExtraUpdateData;
/*     */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.animations.ito.ShufukuSagyoAnimation;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ public class ShufukuSagyoAbility extends DamagedContinuousAbility implements IAnimatedAbility, IExtraUpdateData {
/*  22 */   public static final ShufukuSagyoAbility INSTANCE = new ShufukuSagyoAbility();
/*     */   
/*  24 */   int acumulatedDamage = 0;
/*  25 */   int timeSinceLastHit = 0;
/*     */   
/*     */   boolean dealDamage = true;
/*     */   
/*     */   public ShufukuSagyoAbility() {
/*  30 */     super("Shufuku Sagyo", AbilityHelper.getDevilFruitCategory());
/*  31 */     setMaxCooldown(100.0D);
/*  32 */     setDescription("Uses strings to stitch together internal organs have they been injured, minimizing fatal damage\n\nYou will take your damage back, excluding in the case of holding the ability off without taking damage for a long amount of time §2");
/*     */     
/*  34 */     addInPool(new AbilityPool[] { AbilityPool.TEKKAI_LIKE });
/*  35 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/*  36 */     this.duringContinuityEvent = this::duringContinuity;
/*  37 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*  38 */     this.onDamagedEvent = this::onDamageEvent;
/*  39 */     this.duringCooldownEvent = this::duringCooldownEvent;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void useDamage(PlayerEntity player) {
/*  45 */     if (this.acumulatedDamage > 0 && this.dealDamage) {
/*     */       
/*  47 */       player.attackEntityFrom(DamageSource.OUT_OF_WORLD, this.acumulatedDamage);
/*  48 */       this.acumulatedDamage = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void duringCooldownEvent(PlayerEntity player, int i) {
/*  55 */     useDamage(player);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onDamageEvent(LivingEntity player, DamageSource damageSource, double v) {
/*  60 */     if (damageSource.getDamageType().equals(DamageSource.OUT_OF_WORLD.getDamageType())) {
/*  61 */       return true;
/*     */     }
/*  63 */     this.timeSinceLastHit = player.ticksExisted;
/*  64 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onStartContinuityEvent(PlayerEntity player) {
/*  69 */     this.timeSinceLastHit = player.ticksExisted;
/*  70 */     this.dealDamage = true;
/*  71 */     this.acumulatedDamage = 0;
/*  72 */     setMaxCooldown(100.0D);
/*  73 */     setThreshold(0.0D);
/*  74 */     WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, (Ability)this), (LivingEntity)player);
/*  75 */     return (player.getMaxHealth() * 0.7D > player.getHealth());
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringContinuity(PlayerEntity player, int passiveTimer) {
/*  80 */     if (player.getMaxHealth() * 0.7D > player.getHealth() && player.ticksExisted % 2 == 0) {
/*     */       
/*  82 */       player.addPotionEffect(new EffectInstance(ModEffects.NO_HANDS, 2, 0, false, false));
/*  83 */       player.addPotionEffect(new EffectInstance(ModEffects.GUARDING, 2, 4, false, false));
/*  84 */       player.heal(1.0F);
/*  85 */       this.acumulatedDamage++;
/*     */     } else {
/*     */       
/*  88 */       player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 2, 1, false, false));
/*  89 */       player.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 2, 1, false, false));
/*  90 */       player.addPotionEffect(new EffectInstance(ModEffects.PHYSICAL_MOVING_GUARD, 2, 2, false, false));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  95 */     if (this.acumulatedDamage > 0 && player.hurtTime > 0 && player.ticksExisted % 10 == 0 && Math.random() > (1.0F - this.acumulatedDamage / Math.min(player.getMaxHealth(), 100.0F) * 2.0F)) {
/*     */       
/*  97 */       useDamage(player);
/*  98 */       endContinuity(player);
/*     */     } 
/*     */ 
/*     */     
/* 102 */     if (player.ticksExisted - this.timeSinceLastHit > 1200 && getThreshold() == 0) {
/*     */       
/* 104 */       this.dealDamage = false;
/* 105 */       this.continueTime = 0;
/* 106 */       setThreshold(5.0D);
/* 107 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, (Ability)this), (LivingEntity)player);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick(PlayerEntity player) {
/* 113 */     if ((isOnStandby() || isOnCooldown()) && 
/* 114 */       this.acumulatedDamage > 0) {
/*     */ 
/*     */       
/* 117 */       player.attackEntityFrom(DamageSource.OUT_OF_WORLD, this.acumulatedDamage);
/* 118 */       this.acumulatedDamage = 0;
/* 119 */       endContinuity(player);
/*     */     } 
/*     */     
/* 122 */     super.tick(player);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 127 */     if (getContinueTime() == getThreshold()) {
/*     */       
/* 129 */       setMaxCooldown(10.0D);
/* 130 */       this.acumulatedDamage = 0;
/* 131 */       return true;
/*     */     } 
/*     */     
/* 134 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public IAnimation getAnimation() {
/* 139 */     return (IAnimation)ShufukuSagyoAnimation.INSTANCE;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAnimationActive() {
/* 144 */     return isContinuous();
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundNBT getExtraData() {
/* 149 */     CompoundNBT nbt = new CompoundNBT();
/* 150 */     nbt.putDouble("accdamage", this.acumulatedDamage);
/* 151 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExtraData(CompoundNBT nbt) {
/* 156 */     this.acumulatedDamage = nbt.getInt("accdamage");
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\ito\ShufukuSagyoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */