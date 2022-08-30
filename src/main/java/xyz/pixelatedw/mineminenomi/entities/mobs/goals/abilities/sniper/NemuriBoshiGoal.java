/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.sniper.NemuriBoshiProjectile;
/*    */ 
/*    */ public class NemuriBoshiGoal
/*    */   extends CooldownGoal {
/*    */   public NemuriBoshiGoal(OPEntity entity) {
/* 14 */     super(entity, 170, entity.getRNG().nextInt(30));
/* 15 */     this.entity = entity;
/* 16 */     this.entity.addThreat(5);
/*    */   }
/*    */   
/*    */   private OPEntity entity;
/*    */   
/*    */   public boolean shouldExecute() {
/* 22 */     boolean shouldExecute = super.shouldExecute();
/* 23 */     boolean hasTarget = (this.entity.getAttackTarget() != null);
/* 24 */     boolean hasDistance = (hasTarget && this.entity.getDistance((Entity)this.entity.getAttackTarget()) > 4.0F);
/* 25 */     boolean hasSniperWeapon = (!this.entity.getHeldItemMainhand().isEmpty() && ItemsHelper.isBow(this.entity.getHeldItemMainhand()));
/* 26 */     boolean hasEnemyInSight = (hasTarget && this.entity.getEntitySenses().canSee((Entity)this.entity.getAttackTarget()));
/*    */     
/* 28 */     if (shouldExecute && hasTarget && hasEnemyInSight && hasDistance && hasSniperWeapon) {
/* 29 */       return true;
/*    */     }
/* 31 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void endCooldown() {
/* 37 */     super.endCooldown();
/* 38 */     this.entity.setCurrentGoal(null);
/* 39 */     this.entity.setPreviousGoal((Goal)this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 45 */     double d1 = this.entity.getAttackTarget().getPosX() - this.entity.getPosX();
/* 46 */     double d2 = (this.entity.getAttackTarget().getBoundingBox()).minY + (this.entity.getAttackTarget().getHeight() / 2.0F) - this.entity.getPosY() + (this.entity.getHeight() / 2.0F);
/* 47 */     double d3 = this.entity.getAttackTarget().getPosZ() - this.entity.getPosZ();
/*    */     
/* 49 */     NemuriBoshiProjectile projectile = new NemuriBoshiProjectile(this.entity.world, (LivingEntity)this.entity);
/* 50 */     projectile.setPosition(projectile.getPosX(), this.entity.getPosY() + (this.entity.getHeight() / 2.0F) + 0.5D, projectile.getPosZ());
/* 51 */     projectile.shoot(d1 + this.entity.getRNG().nextGaussian(), d2, d3 + this.entity.getRNG().nextGaussian(), 1.5F, 0.0F);
/* 52 */     this.entity.world.addEntity((Entity)projectile);
/*    */     
/* 54 */     this.entity.setCurrentGoal((Goal)this);
/* 55 */     setOnCooldown(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\sniper\NemuriBoshiGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */