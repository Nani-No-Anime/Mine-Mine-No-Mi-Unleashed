/*    */ package xyz.pixelatedw.mineminenomi.abilities.hana;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IFallDamageBlockingAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.animations.CrossedArmsAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*    */ 
/*    */ public class CienFleurWingAbility extends ContinuousAbility implements IFallDamageBlockingAbility, IAnimatedAbility, IParallelContinuousAbility {
/* 15 */   public static final CienFleurWingAbility INSTANCE = new CienFleurWingAbility();
/*    */   
/*    */   private boolean hasFallDamage = true;
/*    */ 
/*    */   
/*    */   public CienFleurWingAbility() {
/* 21 */     super("Cien Fleur: Wing", AbilityHelper.getDevilFruitCategory());
/* 22 */     setDescription("While active and the user is in air they will float and not receive any fall damage.");
/* 23 */     setThreshold(10.0D);
/* 24 */     needsClientSide();
/*    */     
/* 26 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 27 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/* 28 */     this.duringContinuityEvent = this::duringContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 33 */     if (player.onGround) {
/* 34 */       return false;
/*    */     }
/* 36 */     MilFleurAbility.spawnBlossomEffect((LivingEntity)player);
/*    */     
/* 38 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuityEvent(PlayerEntity player, int time) {
/* 43 */     if (player.onGround) {
/* 44 */       endContinuity(player);
/*    */     }
/* 46 */     player.addVelocity(0.0D, -((player.getMotion()).y / 6.0D), 0.0D);
/* 47 */     this.hasFallDamage = false;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 52 */     int cooldown = (int)Math.round(this.continueTime / 20.0D) + 5;
/* 53 */     setMaxCooldown(cooldown);
/* 54 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void resetFallDamage(LivingEntity player) {
/* 60 */     this.hasFallDamage = true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasFallDamage() {
/* 66 */     return this.hasFallDamage;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IAnimation getAnimation() {
/* 72 */     return (IAnimation)CrossedArmsAnimation.INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAnimationActive() {
/* 78 */     return isContinuous();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\hana\CienFleurWingAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */