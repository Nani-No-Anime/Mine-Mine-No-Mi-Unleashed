/*    */ package xyz.pixelatedw.mineminenomi.abilities.sui;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ 
/*    */ public class FreeSwimmingAbility extends ContinuousAbility {
/*  9 */   public static final FreeSwimmingAbility INSTANCE = new FreeSwimmingAbility();
/*    */   
/*    */   public boolean isSwimming = false;
/*    */ 
/*    */   
/*    */   public FreeSwimmingAbility() {
/* 15 */     super("Free Swimming", AbilityHelper.getDevilFruitCategory());
/* 16 */     setDescription("Lets the user swim trough blocks like they were in the sea");
/* 17 */     setCooldown(1.0D);
/* 18 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 19 */     this.duringCooldownEvent = this::duringContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuityEvent(PlayerEntity player, int i) {
/* 24 */     if (!AbilityHelper.canUseMomentumAbility(player))
/* 25 */       endContinuity(player); 
/*    */   }
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 29 */     return AbilityHelper.canUseMomentumAbility(player);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\sui\FreeSwimmingAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */