package xyz.pixelatedw.mineminenomi.entities.mobs.goals;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;


public class AvoidAoEGoal
  extends Goal
{
  private OPEntity entity;
  private double speed;
  private double responseTime;
  
  public AvoidAoEGoal(OPEntity entity, double speed, double responseTime) {
    this.entity = entity;
    this.speed = speed;
    this.responseTime = responseTime;
    setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
  }


  
  public boolean shouldExecute() {
    List<PlayerEntity> targets = WyHelper.getEntitiesNear(this.entity.getPosition(), this.entity.world, 20.0D, new Class[] { PlayerEntity.class });















    
    boolean hasTargetNearby = (((List)targets.stream().filter(player -> { IAbilityData props = AbilityDataCapability.get((LivingEntity)player); boolean flag = false; for (Ability abl : props.getEquippedAbilities()) { if (abl instanceof xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility && abl.isCharging()) { flag = true; break; }  }  return flag; }).collect(Collectors.toList())).size() > 0);
    
    return false;
  }
}


