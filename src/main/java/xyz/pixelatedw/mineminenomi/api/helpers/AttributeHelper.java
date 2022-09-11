package xyz.pixelatedw.mineminenomi.api.helpers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;


public class AttributeHelper
{
  public static double getAttackRangeDistance(LivingEntity entity, double baseReachDistance) {
    IAttributeInstance reachDistance = entity.getAttribute(ModAttributes.ATTACK_RANGE);
    return (reachDistance != null) ? (baseReachDistance + reachDistance.getValue()) : baseReachDistance;
  }

  
  public static double getSquaredAttackRangeDistance(LivingEntity entity, double sqBaseReachDistance) {
    double reachDistance = getAttackRangeDistance(entity, Math.sqrt(sqBaseReachDistance));
    return reachDistance * reachDistance;
  }
}


