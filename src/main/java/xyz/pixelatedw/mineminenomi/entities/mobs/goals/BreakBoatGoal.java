package xyz.pixelatedw.mineminenomi.entities.mobs.goals;

import java.util.List;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.util.DamageSource;


public class BreakBoatGoal
  extends Goal
{
  private final CreatureEntity entity;
  private Entity boat;
  
  public BreakBoatGoal(CreatureEntity entity) {
    this.entity = entity;
  }


  
  public boolean shouldExecute() {
    List<BoatEntity> list = this.entity.world.getEntitiesWithinAABB(BoatEntity.class, this.entity.getBoundingBox().grow(2.0D));
    
    for (BoatEntity boat : list) {
      
      Entity entity = boat.getControllingPassenger();
      if (entity != null && entity instanceof LivingEntity && boat.isAlive() && this.entity.canEntityBeSeen((Entity)boat)) {
        
        this.boat = (Entity)boat;
        
        break;
      } 
    } 
    return (this.boat != null && this.boat.isAlive());
  }


  
  public boolean shouldContinueExecuting() {
    return (this.boat != null && this.boat.isAlive());
  }


  
  public void startExecuting() {
    IAttributeInstance attr = this.entity.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
    float damage = (float)((attr != null) ? attr.getValue() : 1.0D);
    this.boat.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)this.entity), damage);
    this.boat = null;
  }


  
  public void resetTask() {
    this.boat = null;
  }
}


