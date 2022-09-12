package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.projectile.ThrowableEntity;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.GeppoAbility;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import java.util.List;

public class GeppoGoal
  extends CooldownGoal {
  private OPEntity entity;
  
  public GeppoGoal(OPEntity entity) {
    super(entity, 80, (int)WyHelper.randomWithRange(2, 7));
    this.entity = entity;
    this.entity.addThreat(4);
  }


  
  public boolean shouldExecute() {
    if (!super.shouldExecute()) {
      return false;
    }
    this.entity.fallDistance = 0.0F;
    
    List<ThrowableEntity> dangers = WyHelper.getEntitiesNear(this.entity.getPosition(), this.entity.world, 3.0D, new Class[] { ThrowableEntity.class });
    
    if (dangers.size() > 0) {
      
      if (this.cooldown > this.maxCooldown / 2 && this.cooldown < this.maxCooldown) {
        return false;
      }
      execute();
      return true;
    } 
    
    if (this.entity.getAttackTarget() == null) {
      return false;
    }
    float distance = this.entity.getDistance((Entity)this.entity.getAttackTarget());
    if (distance > 5.0F && this.entity.getHealth() > this.entity.getMaxHealth() / 4.0F) {
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
    this.entity.setMotion(0.0D, 1.3D, 0.0D);
    
    GeppoAbility.PARTICLES.spawn(this.entity.world, this.entity.getPosX(), this.entity.getPosY(), this.entity.getPosZ(), 0.0D, 0.0D, 0.0D);
    
    this.entity.setCurrentGoal((Goal)this);
    setOnCooldown(true);
  }
}


