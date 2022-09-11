package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.lapahn;

import java.util.List;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import xyz.pixelatedw.mineminenomi.entities.mobs.animals.LapahnEntity;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class LapahnRageGoal
  extends Goal
{
  private LapahnEntity entity;
  
  public LapahnRageGoal(LapahnEntity entity) {
    this.entity = entity;
  }


  
  public boolean shouldExecute() {
    if (this.entity.getAttackTarget() == null) {
      return false;
    }
    if (this.entity.getHealth() > this.entity.getMaxHealth() / 3.0F) {
      return false;
    }
    execute();
    return true;
  }

  
  public void execute() {
    this.entity.setEnraged(true);
    
    List<LivingEntity> targets = WyHelper.getEntitiesNear(this.entity.getPosition(), this.entity.world, 10.0D);
    targets.remove(this.entity);
    
    for (LivingEntity entity : targets) {
      
      if (entity instanceof LapahnEntity) {
        
        LapahnEntity lapahn = (LapahnEntity)entity;
        
        lapahn.setEnraged(true);
        lapahn.setAttackTarget(this.entity.getAttackTarget());
      } 
    } 
  }
}


