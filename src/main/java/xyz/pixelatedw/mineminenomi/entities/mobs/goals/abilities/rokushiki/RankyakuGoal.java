/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.rokushiki.RankyakuProjectile;
/*    */ 
/*    */ public class RankyakuGoal
/*    */   extends CooldownGoal {
/*    */   public RankyakuGoal(OPEntity entity) {
/* 13 */     super(entity, 120, entity.getRNG().nextInt(10));
/* 14 */     this.entity = entity;
/* 15 */     this.entity.addThreat(10);
/*    */   }
/*    */   
/*    */   private OPEntity entity;
/*    */   
/*    */   public boolean shouldExecute() {
/* 21 */     if (!super.shouldExecute()) {
/* 22 */       return false;
/*    */     }
/* 24 */     if (this.entity.getAttackTarget() == null) {
/* 25 */       return false;
/*    */     }
/* 27 */     if (!this.entity.getEntitySenses().canSee((Entity)this.entity.getAttackTarget())) {
/* 28 */       return false;
/*    */     }
/* 30 */     if (this.entity.getDistance((Entity)this.entity.getAttackTarget()) < 5.0F) {
/* 31 */       return false;
/*    */     }
/* 33 */     execute();
/* 34 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void endCooldown() {
/* 40 */     super.endCooldown();
/* 41 */     this.entity.setCurrentGoal(null);
/* 42 */     this.entity.setPreviousGoal((Goal)this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute() {
/* 47 */     double d1 = this.entity.getAttackTarget().getPosX() - this.entity.getPosX();
/* 48 */     double d2 = (this.entity.getAttackTarget().getBoundingBox()).minY + (this.entity.getAttackTarget().getHeight() / 2.0F) - this.entity.getPosY() + (this.entity.getHeight() / 2.0F);
/* 49 */     double d3 = this.entity.getAttackTarget().getPosZ() - this.entity.getPosZ();
/*    */     
/* 51 */     RankyakuProjectile projectile = new RankyakuProjectile(this.entity.world, (LivingEntity)this.entity);
/* 52 */     projectile.setPosition(projectile.getPosX(), this.entity.getPosY() + (this.entity.getHeight() / 2.0F) + 0.5D, projectile.getPosZ());
/* 53 */     projectile.shoot(d1 + this.entity.getRNG().nextGaussian(), d2, d3 + this.entity.getRNG().nextGaussian(), 1.5F, 0.0F);
/* 54 */     this.entity.world.addEntity((Entity)projectile);
/*    */     
/* 56 */     this.entity.setCurrentGoal((Goal)this);
/* 57 */     setOnCooldown(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\rokushiki\RankyakuGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */