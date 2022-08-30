/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.lapahn;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.LapahnEntity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ public class LapahnRageGoal
/*    */   extends Goal
/*    */ {
/*    */   private LapahnEntity entity;
/*    */   
/*    */   public LapahnRageGoal(LapahnEntity entity) {
/* 16 */     this.entity = entity;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 22 */     if (this.entity.getAttackTarget() == null) {
/* 23 */       return false;
/*    */     }
/* 25 */     if (this.entity.getHealth() > this.entity.getMaxHealth() / 3.0F) {
/* 26 */       return false;
/*    */     }
/* 28 */     execute();
/* 29 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute() {
/* 34 */     this.entity.setEnraged(true);
/*    */     
/* 36 */     List<LivingEntity> targets = WyHelper.getEntitiesNear(this.entity.getPosition(), this.entity.world, 10.0D);
/* 37 */     targets.remove(this.entity);
/*    */     
/* 39 */     for (LivingEntity entity : targets) {
/*    */       
/* 41 */       if (entity instanceof LapahnEntity) {
/*    */         
/* 43 */         LapahnEntity lapahn = (LapahnEntity)entity;
/*    */         
/* 45 */         lapahn.setEnraged(true);
/* 46 */         lapahn.setAttackTarget(this.entity.getAttackTarget());
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\lapahn\LapahnRageGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */