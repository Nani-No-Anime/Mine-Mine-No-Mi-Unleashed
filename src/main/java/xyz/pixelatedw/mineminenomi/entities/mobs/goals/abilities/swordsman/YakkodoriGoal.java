/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.swordsman.YakkodoriProjectile;
/*    */ 
/*    */ public class YakkodoriGoal
/*    */   extends CooldownGoal {
/*    */   public YakkodoriGoal(OPEntity entity) {
/* 14 */     super(entity, 120, entity.getRNG().nextInt(20));
/* 15 */     this.entity = entity;
/* 16 */     this.entity.addThreat(3);
/*    */   }
/*    */   
/*    */   private OPEntity entity;
/*    */   
/*    */   public boolean shouldExecute() {
/* 22 */     if (!super.shouldExecute()) {
/* 23 */       return false;
/*    */     }
/* 25 */     ItemStack itemStack = this.entity.getHeldItemMainhand();
/*    */     
/* 27 */     if (itemStack == null || itemStack.isEmpty() || this.entity.getAttackTarget() == null) {
/* 28 */       return false;
/*    */     }
/* 30 */     if (!this.entity.getEntitySenses().canSee((Entity)this.entity.getAttackTarget())) {
/* 31 */       return false;
/*    */     }
/* 33 */     if (this.entity.getDistance((Entity)this.entity.getAttackTarget()) < 10.0F) {
/* 34 */       return false;
/*    */     }
/* 36 */     execute();
/* 37 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void endCooldown() {
/* 43 */     super.endCooldown();
/* 44 */     this.entity.setCurrentGoal(null);
/* 45 */     this.entity.setPreviousGoal((Goal)this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute() {
/* 50 */     double d1 = this.entity.getAttackTarget().getPosX() - this.entity.getPosX();
/* 51 */     double d2 = (this.entity.getAttackTarget().getBoundingBox()).minY + (this.entity.getAttackTarget().getHeight() / 2.0F) - this.entity.getPosY() + (this.entity.getHeight() / 2.0F);
/* 52 */     double d3 = this.entity.getAttackTarget().getPosZ() - this.entity.getPosZ();
/*    */     
/* 54 */     YakkodoriProjectile projectile = new YakkodoriProjectile(this.entity.world, (LivingEntity)this.entity);
/* 55 */     projectile.setPosition(projectile.getPosX(), this.entity.getPosY() + (this.entity.getHeight() / 2.0F) + 0.5D, projectile.getPosZ());
/* 56 */     projectile.shoot(d1 + this.entity.getRNG().nextGaussian(), d2, d3 + this.entity.getRNG().nextGaussian(), 1.5F, 0.0F);
/* 57 */     this.entity.world.addEntity((Entity)projectile);
/*    */     
/* 59 */     this.entity.setCurrentGoal((Goal)this);
/* 60 */     setOnCooldown(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\swordsman\YakkodoriGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */