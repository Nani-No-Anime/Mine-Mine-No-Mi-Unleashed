/*    */ package xyz.pixelatedw.mineminenomi.abilities.ito;
/*    */ import java.lang.invoke.SerializedLambda;

import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.math.EntityRayTraceResult;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ito.KumoNoSugakiParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.animations.PointBothArmsAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ 
/*    */ public class KumoNoSugakiAbility extends ContinuousAbility implements IAnimatedAbility {
/* 18 */   public static final KumoNoSugakiAbility INSTANCE = new KumoNoSugakiAbility();
/*    */   
/* 20 */   private static final KumoNoSugakiParticleEffect PARTICLES = new KumoNoSugakiParticleEffect();
/*    */ 
/*    */   
/*    */   public KumoNoSugakiAbility() {
/* 24 */     super("Kumo no Sugaki", AbilityHelper.getDevilFruitCategory());
/* 25 */     setThreshold(6.0D);
/* 26 */     setDescription("Creates a huge web that protects the user from any attack");
/* 27 */     addInPool(new AbilityPool[] { AbilityPool.TEKKAI_LIKE });
/*    */     
/* 29 */     this.duringContinuityEvent = this::duringContinuity;
/* 30 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuity(PlayerEntity player, int passiveTimer) {
/* 35 */     EntityRayTraceResult trace = WyHelper.rayTraceEntities((Entity)player, 0.8D);
/* 36 */     player.addPotionEffect(new EffectInstance(ModEffects.GUARDING, 2, 5, false, false));
/* 37 */     AbilityHelper.slowEntityFall((LivingEntity)player);
/*    */     
/* 39 */     if (passiveTimer % 2 == 0) {
/* 40 */       PARTICLES.spawn(player.world, trace.getHitVec().getX(), player.getPosY(), trace.getHitVec().getZ(), 0.0D, 0.0D, 0.0D);
/*    */     }
/*    */   }
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 45 */     int cooldown = (int)Math.round(this.continueTime / 40.0D) + 2;
/* 46 */     setMaxCooldown(cooldown);
/* 47 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IAnimation getAnimation() {
/* 53 */     return (IAnimation)PointBothArmsAnimation.INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAnimationActive() {
/* 59 */     return isContinuous();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\ito\KumoNoSugakiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */