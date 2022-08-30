/*    */ package xyz.pixelatedw.mineminenomi.abilities.rokushiki;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.animations.CrossedArmsAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ 
/*    */ public class TekkaiAbility extends ContinuousAbility implements IAnimatedAbility {
/* 16 */   public static final Ability INSTANCE = (Ability)new TekkaiAbility();
/*    */ 
/*    */   
/*    */   public TekkaiAbility() {
/* 20 */     super("Tekkai", AbilityHelper.getRacialCategory());
/* 21 */     setThreshold(10.0D);
/* 22 */     setDescription("Hardens the user's body to protect themselves, but they're unable to move");
/* 23 */     addInPool(new AbilityPool[] { AbilityPool.TEKKAI_LIKE });
/*    */     
/* 25 */     this.duringContinuityEvent = this::duringContinuityEvent;
/* 26 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuityEvent(PlayerEntity player, int passiveTimer) {
/* 31 */     player.addPotionEffect(new EffectInstance(ModEffects.GUARDING, 2, 1, false, false));
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 36 */     int cooldown = (int)Math.round(this.continueTime / 20.0D) + 3;
/* 37 */     setMaxCooldown(cooldown);
/* 38 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IAnimation getAnimation() {
/* 44 */     return (IAnimation)CrossedArmsAnimation.INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAnimationActive() {
/* 50 */     return isContinuous();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\rokushiki\TekkaiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */