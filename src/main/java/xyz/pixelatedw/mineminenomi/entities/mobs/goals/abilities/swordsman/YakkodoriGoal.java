package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
import xyz.pixelatedw.mineminenomi.entities.projectiles.swordsman.YakkodoriProjectile;

public class YakkodoriGoal
  extends CooldownGoal {
  public YakkodoriGoal(OPEntity entity) {
    super(entity, 120, entity.getRNG().nextInt(20));
    this.entity = entity;
    this.entity.addThreat(3);
  }
  
  private OPEntity entity;
  
  public boolean shouldExecute() {
    if (!super.shouldExecute()) {
      return false;
    }
    ItemStack itemStack = this.entity.getHeldItemMainhand();
    
    if (itemStack == null || itemStack.isEmpty() || this.entity.getAttackTarget() == null) {
      return false;
    }
    if (!this.entity.getEntitySenses().canSee((Entity)this.entity.getAttackTarget())) {
      return false;
    }
    if (this.entity.getDistance((Entity)this.entity.getAttackTarget()) < 10.0F) {
      return false;
    }
    execute();
    return true;
  }


  
  public void endCooldown() {
    super.endCooldown();
    this.entity.setCurrentGoal(null);
    this.entity.setPreviousGoal((Goal)this);
  }

  
  public void execute() {
    double d1 = this.entity.getAttackTarget().getPosX() - this.entity.getPosX();
    double d2 = (this.entity.getAttackTarget().getBoundingBox()).minY + (this.entity.getAttackTarget().getHeight() / 2.0F) - this.entity.getPosY() + (this.entity.getHeight() / 2.0F);
    double d3 = this.entity.getAttackTarget().getPosZ() - this.entity.getPosZ();
    
    YakkodoriProjectile projectile = new YakkodoriProjectile(this.entity.world, (LivingEntity)this.entity);
    projectile.setPosition(projectile.getPosX(), this.entity.getPosY() + (this.entity.getHeight() / 2.0F) + 0.5D, projectile.getPosZ());
    projectile.shoot(d1 + this.entity.getRNG().nextGaussian(), d2, d3 + this.entity.getRNG().nextGaussian(), 1.5F, 0.0F);
    this.entity.world.addEntity((Entity)projectile);
    
    this.entity.setCurrentGoal((Goal)this);
    setOnCooldown(true);
  }
}


