/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.CannonBallProjectile;
/*    */ 
/*    */ public class GenkotsuMeteorGoal extends CooldownGoal {
/*    */   private OPEntity entity;
/*    */   
/*    */   public GenkotsuMeteorGoal(OPEntity entity) {
/* 15 */     super(entity, 70, entity.getRNG().nextInt(10));
/* 16 */     this.entity = entity;
/* 17 */     this.entity.addThreat(2);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 23 */     boolean shouldExecute = super.shouldExecute();
/* 24 */     boolean hasTarget = (this.entity.getAttackTarget() != null);
/* 25 */     boolean hasDistance = (hasTarget && this.entity.getDistance((Entity)this.entity.getAttackTarget()) > 10.0F);
/* 26 */     boolean hasEmptyHand = AbilityHelper.canUseBrawlerAbilities((LivingEntity)this.entity);
/* 27 */     boolean hasEnemyInSight = (hasTarget && this.entity.getEntitySenses().canSee((Entity)this.entity.getAttackTarget()));
/*    */     
/* 29 */     if (shouldExecute && hasTarget && hasEnemyInSight && hasDistance && hasEmptyHand) {
/* 30 */       return true;
/*    */     }
/* 32 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void endCooldown() {
/* 38 */     super.endCooldown();
/* 39 */     this.entity.setCurrentGoal(null);
/* 40 */     this.entity.setPreviousGoal((Goal)this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 46 */     double d1 = this.entity.getAttackTarget().getPosX() - this.entity.getPosX();
/* 47 */     double d2 = (this.entity.getAttackTarget().getBoundingBox()).minY + (this.entity.getAttackTarget().getHeight() / 2.0F) - this.entity.getPosY() + (this.entity.getHeight() / 2.0F);
/* 48 */     double d3 = this.entity.getAttackTarget().getPosZ() - this.entity.getPosZ();
/*    */     
/* 50 */     CannonBallProjectile cannonBallProjectile = new CannonBallProjectile(this.entity.world, (LivingEntity)this.entity);
/* 51 */     cannonBallProjectile.setPosition(cannonBallProjectile.getPosX(), this.entity.getPosY() + (this.entity.getHeight() / 2.0F) + 0.5D, cannonBallProjectile.getPosZ());
/* 52 */     cannonBallProjectile.shoot(d1 + this.entity.getRNG().nextGaussian(), d2, d3 + this.entity.getRNG().nextGaussian(), 1.5F, 0.0F);
/* 53 */     cannonBallProjectile.setThrower((LivingEntity)this.entity);
/* 54 */     this.entity.world.addEntity((Entity)cannonBallProjectile);
/*    */     
/* 56 */     this.entity.setCurrentGoal((Goal)this);
/* 57 */     setOnCooldown(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\brawler\GenkotsuMeteorGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */