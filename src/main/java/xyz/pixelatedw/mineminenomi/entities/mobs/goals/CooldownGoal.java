/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*    */ 
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ 
/*    */ public abstract class CooldownGoal extends Goal {
/*    */   private OPEntity entity;
/*    */   private boolean isOnCooldown = false;
/*    */   protected int maxCooldown;
/* 10 */   protected int cooldown = 80;
/*    */   protected int randomizer;
/*    */   
/*    */   public CooldownGoal(OPEntity entity, int timer, int random) {
/* 14 */     this.entity = entity;
/* 15 */     this.maxCooldown = timer;
/* 16 */     this.cooldown = this.maxCooldown;
/* 17 */     this.randomizer = random + 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public CooldownGoal setCooldown(int cooldown) {
/* 22 */     this.maxCooldown = cooldown;
/* 23 */     return this;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 29 */     if (this.isOnCooldown && this.cooldown <= 0) {
/* 30 */       return false;
/*    */     }
/* 32 */     if (isOnCooldown()) {
/*    */       
/* 34 */       cooldownTick();
/* 35 */       return false;
/*    */     } 
/*    */     
/* 38 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void endCooldown() {
/* 43 */     this.isOnCooldown = false;
/* 44 */     this.cooldown = this.maxCooldown + this.entity.getRNG().nextInt(this.randomizer);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isOnCooldown() {
/* 49 */     return this.isOnCooldown;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setOnCooldown(boolean value) {
/* 54 */     this.isOnCooldown = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean cooldownTick() {
/* 59 */     if (this.isOnCooldown) {
/*    */       
/* 61 */       this.cooldown--;
/* 62 */       if (this.cooldown <= 0) {
/* 63 */         endCooldown();
/*    */       }
/* 65 */       return true;
/*    */     } 
/*    */     
/* 68 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\CooldownGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */