package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.Goal;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;

public class JumpAttackGoal
  extends CooldownGoal {
  private OPEntity entity;
  private int hitCount;
  
  public JumpAttackGoal(OPEntity entity) {
    super(entity, 40, 5);
    this.entity = entity;
    this.maxCount = 2;
    this.prevHealth = this.entity.getHealth();
  }
  private int maxCount;
  private float prevHealth;
  
  public boolean shouldExecute() {
    if (this.entity.getHealth() < this.prevHealth) {
      
      this.hitCount++;
      this.prevHealth = this.entity.getHealth();
    } 
    
    boolean shouldExecute = super.shouldExecute();
    boolean hasTarget = (this.entity.getAttackTarget() != null);
    float distance = hasTarget ? this.entity.getDistance((Entity)this.entity.getAttackTarget()) : 0.0F;
    boolean hasDistance = (distance < 10.0F);
    boolean hasHitCount = (this.hitCount > this.maxCount);
    
    if (shouldExecute && hasTarget && hasDistance && hasHitCount) {
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
    this.entity.setMotion(0.0D, 2.0D, 0.0D);
    this.entity.velocityChanged = true;
    this.hitCount = 0;
    
    this.entity.setCurrentGoal((Goal)this);
    setOnCooldown(true);
  }
}


