package xyz.pixelatedw.mineminenomi.entities.mobs.goals;

import net.minecraft.entity.ai.goal.Goal;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;

public abstract class CooldownGoal extends Goal {
  private OPEntity entity;
  private boolean isOnCooldown = false;
  protected int maxCooldown;
  protected int cooldown = 80;
  protected int randomizer;
  
  public CooldownGoal(OPEntity entity, int timer, int random) {
    this.entity = entity;
    this.maxCooldown = timer;
    this.cooldown = this.maxCooldown;
    this.randomizer = random + 1;
  }

  
  public CooldownGoal setCooldown(int cooldown) {
    this.maxCooldown = cooldown;
    return this;
  }


  
  public boolean shouldExecute() {
    if (this.isOnCooldown && this.cooldown <= 0) {
      return false;
    }
    if (isOnCooldown()) {
      
      cooldownTick();
      return false;
    } 
    
    return true;
  }

  
  public void endCooldown() {
    this.isOnCooldown = false;
    this.cooldown = this.maxCooldown + this.entity.getRNG().nextInt(this.randomizer);
  }

  
  public boolean isOnCooldown() {
    return this.isOnCooldown;
  }

  
  public void setOnCooldown(boolean value) {
    this.isOnCooldown = value;
  }

  
  public boolean cooldownTick() {
    if (this.isOnCooldown) {
      
      this.cooldown--;
      if (this.cooldown <= 0) {
        endCooldown();
      }
      return true;
    } 
    
    return false;
  }
}


