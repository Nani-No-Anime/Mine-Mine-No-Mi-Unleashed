package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.goal.Goal;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import java.util.UUID;

public class SoruGoal
  extends CooldownGoal {
  private OPEntity entity;
  private UUID soruSpeedUUID = UUID.fromString("4929edc3-45e7-4763-aecc-d478f5eadc3b");
  
  private AttributeModifier speedModifier;
  
  public SoruGoal(OPEntity entity) {
    super(entity, 40, (int)WyHelper.randomWithRange(5, 10));
    this.entity = entity;
    this.entity.addThreat(1);
  }


  
  public boolean shouldExecute() {
    if (!super.shouldExecute()) {
      return false;
    }
    if (isOnCooldown()) {
      
      IAttributeInstance soruSpeed = this.entity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
      if (this.cooldown < this.maxCooldown / 2 && soruSpeed.getModifier(this.soruSpeedUUID) != null) {
        soruSpeed.removeModifier(this.speedModifier);
      }
      cooldownTick();
      return false;
    } 
    
    if (this.entity.getAttackTarget() == null) {
      return false;
    }
    float distance = this.entity.getDistance((Entity)this.entity.getAttackTarget());
    if (distance > 15.0F && distance <= 25.0F) {
      
      execute(0.3D);
      return true;
    } 
    if (distance > 25.0F) {
      
      execute(0.4D);
      return true;
    } 

    
    return false;
  }



  
  public void endCooldown() {
    super.endCooldown();
    this.entity.setCurrentGoal(null);
    this.entity.setPreviousGoal((Goal)this);
  }

  
  public void execute(double level) {
    IAttributeInstance soruSpeed = this.entity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
    if (soruSpeed.getModifier(this.soruSpeedUUID) != null && this.speedModifier != null) {
      soruSpeed.removeModifier(this.speedModifier);
    }
    this.speedModifier = new AttributeModifier(this.soruSpeedUUID, "Soru Speed", level, AttributeModifier.Operation.ADDITION);
    
    this.entity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(this.speedModifier);
    this.entity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier(this.speedModifier);
    
    this.entity.setCurrentGoal((Goal)this);
    setOnCooldown(true);
  }
}


