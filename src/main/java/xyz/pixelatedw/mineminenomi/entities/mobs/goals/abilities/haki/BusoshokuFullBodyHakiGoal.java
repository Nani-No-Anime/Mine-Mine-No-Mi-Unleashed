package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.Goal;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;

public class BusoshokuFullBodyHakiGoal
  extends Goal {
  private OPEntity entity;
  
  public BusoshokuFullBodyHakiGoal(OPEntity entity) {
    this.entity = entity;
    this.entity.addThreat(12);
  }


  
  public boolean shouldExecute() {
    return true;
  }


  
  public void tick() {
    if (this.entity.getAttackTarget() != null && this.entity.getDistance((Entity)this.entity.getAttackTarget()) < 20.0F) {
      this.entity.setFullbodyHaki(true);
    } else {
      this.entity.setFullbodyHaki(false);
    } 
  }
}


