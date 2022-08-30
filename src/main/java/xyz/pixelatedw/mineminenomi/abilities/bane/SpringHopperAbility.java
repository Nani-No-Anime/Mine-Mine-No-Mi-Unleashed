/*    */ package xyz.pixelatedw.mineminenomi.abilities.bane;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.SpringLegsZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class SpringHopperAbility extends ZoanAbility {
/* 13 */   public static final Ability INSTANCE = (Ability)new SpringHopperAbility();
/* 14 */   public int jumpPower = 0;
/*    */   
/*    */   public boolean canIncreaseJumpPower = false;
/*    */   
/*    */   public SpringHopperAbility() {
/* 19 */     super("Spring Hopper", AbilityHelper.getDevilFruitCategory());
/* 20 */     setMaxCooldown(10.0D);
/* 21 */     setThreshold(50.0D);
/* 22 */     setDescription("By turning the user's legs into springs, they can jump around with great ease bouncing around surfaces");
/*    */     
/* 24 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 25 */     this.duringContinuityEvent = this::duringContiunityEvent;
/* 26 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/* 27 */     this.onStopContinuityEvent = this::onStopContinuityEvent;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean onStartContinuityEvent(PlayerEntity player) {
/* 33 */     return (super.onStartContinuityEvent(player) && AbilityHelper.canUseMomentumAbility(player));
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContiunityEvent(PlayerEntity player, int chargeTime) {
/* 38 */     if (!AbilityHelper.canUseMomentumAbility(player) || player.hurtResistantTime > 0) {
/* 39 */       endContinuity(player);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean onEndContinuityEvent(PlayerEntity player) {
/* 45 */     return super.onEndContinuityEvent(player);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void onStopContinuityEvent(PlayerEntity player) {
/* 51 */     player.getAttribute(ModAttributes.JUMP_HEIGHT).removeModifier(SpringMovementAbility.SPRING_POWER_UUID);
/* 52 */     super.onStopContinuityEvent(player);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo getTransformation() {
/* 58 */     return (ZoanInfo)SpringLegsZoanInfo.INSTANCE;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\bane\SpringHopperAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */