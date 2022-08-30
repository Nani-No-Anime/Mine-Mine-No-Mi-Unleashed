/*    */ package xyz.pixelatedw.mineminenomi.abilities.hana;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.animations.CrossedArmsAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ 
/*    */ public class VeinteFleurCalendulaAbility extends ContinuousAbility implements IAnimatedAbility {
/* 14 */   public static final VeinteFleurCalendulaAbility INSTANCE = new VeinteFleurCalendulaAbility();
/*    */ 
/*    */   
/*    */   public VeinteFleurCalendulaAbility() {
/* 18 */     super("Veinte Fleur: Calendula", AbilityHelper.getDevilFruitCategory());
/* 19 */     setThreshold(10.0D);
/* 20 */     setDescription("Using newly sprouted arms in the form of a shield the user can partially block attacks.");
/*    */     
/* 22 */     this.duringContinuityEvent = this::duringContinuityEvent;
/* 23 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuityEvent(PlayerEntity player, int passiveTimer) {
/* 28 */     player.addPotionEffect(new EffectInstance(ModEffects.PHYSICAL_MOVING_GUARD, 2, 3, false, false));
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 33 */     int cooldown = (int)Math.round(this.continueTime / 20.0D) + 3;
/* 34 */     setMaxCooldown(cooldown);
/* 35 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IAnimation getAnimation() {
/* 41 */     return (IAnimation)CrossedArmsAnimation.INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAnimationActive() {
/* 47 */     return isContinuous();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\hana\VeinteFleurCalendulaAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */