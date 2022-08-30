/*    */ package xyz.pixelatedw.mineminenomi.abilities.fishmankarate;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.fishkarate.SamehadaParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.animations.CrossedArmsAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ 
/*    */ public class SamehadaShoteiAbility extends ContinuousAbility implements IAnimatedAbility {
/* 18 */   public static final Ability INSTANCE = (Ability)new SamehadaShoteiAbility();
/* 19 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new SamehadaParticleEffect();
/*    */ 
/*    */   
/*    */   public SamehadaShoteiAbility() {
/* 23 */     super("Samehada Shotei", AbilityHelper.getRacialCategory());
/* 24 */     setThreshold(10.0D);
/* 25 */     setDescription("The user concentrates their power to their palms, protecting themselves from attacks");
/*    */     
/* 27 */     addInPool(new AbilityPool[] { AbilityPool.TEKKAI_LIKE });
/* 28 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 29 */     this.duringContinuityEvent = this::duringContinuity;
/* 30 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */   }
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 34 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuity(PlayerEntity player, int passiveTimer) {
/* 39 */     player.addPotionEffect(new EffectInstance(ModEffects.GUARDING, 2, player.isInWater() ? 2 : 1, false, false));
/* 40 */     PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 45 */     int cooldown = (int)Math.round(this.continueTime / 20.0D) + 3;
/* 46 */     setMaxCooldown(cooldown);
/* 47 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IAnimation getAnimation() {
/* 53 */     return (IAnimation)CrossedArmsAnimation.INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAnimationActive() {
/* 59 */     return isContinuous();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\fishmankarate\SamehadaShoteiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */