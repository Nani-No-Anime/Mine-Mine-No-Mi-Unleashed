/*    */ package xyz.pixelatedw.mineminenomi.quests.objectives;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.ISurviveObjective;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ 
/*    */ public class TimedSurvivalObjective
/*    */   extends Objective
/*    */   implements ISurviveObjective {
/*    */   private int timeToSurvive;
/*    */   private float initialHP;
/*    */   
/*    */   public TimedSurvivalObjective(String title, int seconds) {
/* 14 */     super(title);
/* 15 */     this.timeToSurvive = seconds;
/* 16 */     setMaxProgress(this.timeToSurvive);
/*    */     
/* 18 */     this.onStartEvent = this::onStartEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartEvent(PlayerEntity player) {
/* 23 */     this.initialHP = player.getHealth();
/* 24 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean checkTime(PlayerEntity player) {
/* 30 */     if (player.getHealth() > this.initialHP) {
/* 31 */       this.initialHP = player.getHealth();
/*    */     }
/* 33 */     if (player.getHealth() < this.initialHP) {
/*    */       
/* 35 */       setProgress(0.0D);
/* 36 */       this.initialHP = player.getHealth();
/* 37 */       return false;
/*    */     } 
/*    */     
/* 40 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\quests\objectives\TimedSurvivalObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */