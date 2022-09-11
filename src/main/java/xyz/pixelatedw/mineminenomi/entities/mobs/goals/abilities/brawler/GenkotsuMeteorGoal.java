package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.CannonBallProjectile;

public class GenkotsuMeteorGoal extends CooldownGoal {
  private OPEntity entity;
  
  public GenkotsuMeteorGoal(OPEntity entity) {
    super(entity, 70, entity.getRNG().nextInt(10));
    this.entity = entity;
    this.entity.addThreat(2);
  }


  
  public boolean shouldExecute() {
    boolean shouldExecute = super.shouldExecute();
    boolean hasTarget = (this.entity.getAttackTarget() != null);
    boolean hasDistance = (hasTarget && this.entity.getDistance((Entity)this.entity.getAttackTarget()) > 10.0F);
    boolean hasEmptyHand = AbilityHelper.canUseBrawlerAbilities((LivingEntity)this.entity);
    boolean hasEnemyInSight = (hasTarget && this.entity.getEntitySenses().canSee((Entity)this.entity.getAttackTarget()));
    
    if (shouldExecute && hasTarget && hasEnemyInSight && hasDistance && hasEmptyHand) {
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
    
    CannonBallProjectile cannonBallProjectile = new CannonBallProjectile(this.entity.world, (LivingEntity)this.entity);
    cannonBallProjectile.setPosition(cannonBallProjectile.getPosX(), this.entity.getPosY() + (this.entity.getHeight() / 2.0F) + 0.5D, cannonBallProjectile.getPosZ());
    cannonBallProjectile.shoot(d1 + this.entity.getRNG().nextGaussian(), d2, d3 + this.entity.getRNG().nextGaussian(), 1.5F, 0.0F);
    cannonBallProjectile.setThrower((LivingEntity)this.entity);
    this.entity.world.addEntity((Entity)cannonBallProjectile);
    
    this.entity.setCurrentGoal((Goal)this);
    setOnCooldown(true);
  }
}


