/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ 
/*    */ public class BusoshokuFullBodyHakiGoal
/*    */   extends Goal {
/*    */   private OPEntity entity;
/*    */   
/*    */   public BusoshokuFullBodyHakiGoal(OPEntity entity) {
/* 12 */     this.entity = entity;
/* 13 */     this.entity.addThreat(12);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 19 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick() {
/* 25 */     if (this.entity.getAttackTarget() != null && this.entity.getDistance((Entity)this.entity.getAttackTarget()) < 20.0F) {
/* 26 */       this.entity.setFullbodyHaki(true);
/*    */     } else {
/* 28 */       this.entity.setFullbodyHaki(false);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\haki\BusoshokuFullBodyHakiGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */