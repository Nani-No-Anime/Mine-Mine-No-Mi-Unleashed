package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
import xyz.pixelatedw.mineminenomi.entities.projectiles.sniper.KemuriBoshiProjectile;

public class KemuriBoshiGoal
  extends CooldownGoal {
  public KemuriBoshiGoal(OPEntity entity) {
    super(entity, 90, entity.getRNG().nextInt(10));
    this.entity = entity;
    this.entity.addThreat(3);
  }
  
  private OPEntity entity;
  
  public boolean shouldExecute() {
    boolean shouldExecute = super.shouldExecute();
    boolean hasTarget = (this.entity.getAttackTarget() != null);
    boolean hasDistance = (hasTarget && this.entity.getDistance((Entity)this.entity.getAttackTarget()) > 4.0F);
    boolean hasSniperWeapon = (!this.entity.getHeldItemMainhand().isEmpty() && ItemsHelper.isBow(this.entity.getHeldItemMainhand()));
    boolean hasEnemyInSight = (hasTarget && this.entity.getEntitySenses().canSee((Entity)this.entity.getAttackTarget()));
    
    if (shouldExecute && hasTarget && hasEnemyInSight && hasDistance && hasSniperWeapon) {
      return true;
    }
    return false;
  }


  
  public void endCooldown() {
    super.endCooldown();
    this.entity.setCurrentGoal(null);
    this.entity.setPreviousGoal((Goal)this);
  }


  
  public void startExecuting() {
    double d1 = this.entity.getAttackTarget().getPosX() - this.entity.getPosX();
    double d2 = (this.entity.getAttackTarget().getBoundingBox()).minY + (this.entity.getAttackTarget().getHeight() / 2.0F) - this.entity.getPosY() + (this.entity.getHeight() / 2.0F);
    double d3 = this.entity.getAttackTarget().getPosZ() - this.entity.getPosZ();
    
    KemuriBoshiProjectile projectile = new KemuriBoshiProjectile(this.entity.world, (LivingEntity)this.entity);
    projectile.setPosition(projectile.getPosX(), this.entity.getPosY() + (this.entity.getHeight() / 2.0F) + 0.5D, projectile.getPosZ());
    projectile.shoot(d1 + this.entity.getRNG().nextGaussian(), d2, d3 + this.entity.getRNG().nextGaussian(), 1.5F, 0.0F);
    this.entity.world.addEntity((Entity)projectile);
    
    this.entity.setCurrentGoal((Goal)this);
    setOnCooldown(true);
  }
}


