/*    */ package xyz.pixelatedw.mineminenomi.abilities.buki;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.Arrays;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchTriggerAbility;
/*    */ 
/*    */ 
/*    */ public class HandmorphoseAbility
/*    */   extends PunchTriggerAbility
/*    */ {
/*    */   public HandmorphoseAbility() {
/* 14 */     super("Handmorphose", AbilityHelper.getDevilFruitCategory());
/*    */     
/* 16 */     this.onStartContinuityEvent = this::onStartContinuity;
/* 17 */     this.duringContinuityEvent = this::duringContinuityEvent;
/* 18 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */     
/* 20 */     stopAfterUsage(false);
/* 21 */     this.onSwingEvent = this::onSwingEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onSwingEvent(PlayerEntity player) {
/* 26 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 31 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuityEvent(PlayerEntity playerEntity, int i) {}
/*    */ 
/*    */   
/*    */   private boolean onStartContinuity(PlayerEntity player) {
/* 39 */     return true;
/*    */   }
/*    */   
/*    */   public enum Mode
/*    */   {
/* 44 */     SWORD,
/* 45 */     CANNON,
/* 46 */     REVOLVER;
/*    */ 
/*    */     
/*    */     public Mode getNext() {
/* 50 */       return (ordinal() == Arrays.<Mode>stream(values()).count() - 1L) ? SWORD : values()[ordinal() + 1];
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\buki\HandmorphoseAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */