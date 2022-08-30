/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
/*    */ 
/*    */ public class JumpAttackGoal
/*    */   extends CooldownGoal {
/*    */   private OPEntity entity;
/*    */   private int hitCount;
/*    */   
/*    */   public JumpAttackGoal(OPEntity entity) {
/* 14 */     super(entity, 40, 5);
/* 15 */     this.entity = entity;
/* 16 */     this.maxCount = 2;
/* 17 */     this.prevHealth = this.entity.getHealth();
/*    */   }
/*    */   private int maxCount;
/*    */   private float prevHealth;
/*    */   
/*    */   public boolean shouldExecute() {
/* 23 */     if (this.entity.getHealth() < this.prevHealth) {
/*    */       
/* 25 */       this.hitCount++;
/* 26 */       this.prevHealth = this.entity.getHealth();
/*    */     } 
/*    */     
/* 29 */     boolean shouldExecute = super.shouldExecute();
/* 30 */     boolean hasTarget = (this.entity.getAttackTarget() != null);
/* 31 */     float distance = hasTarget ? this.entity.getDistance((Entity)this.entity.getAttackTarget()) : 0.0F;
/* 32 */     boolean hasDistance = (distance < 10.0F);
/* 33 */     boolean hasHitCount = (this.hitCount > this.maxCount);
/*    */     
/* 35 */     if (shouldExecute && hasTarget && hasDistance && hasHitCount) {
/* 36 */       return true;
/*    */     }
/* 38 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void endCooldown() {
/* 44 */     super.endCooldown();
/* 45 */     this.entity.setCurrentGoal(null);
/* 46 */     this.entity.setPreviousGoal((Goal)this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 52 */     this.entity.setMotion(0.0D, 2.0D, 0.0D);
/* 53 */     this.entity.velocityChanged = true;
/* 54 */     this.hitCount = 0;
/*    */     
/* 56 */     this.entity.setCurrentGoal((Goal)this);
/* 57 */     setOnCooldown(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\JumpAttackGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */