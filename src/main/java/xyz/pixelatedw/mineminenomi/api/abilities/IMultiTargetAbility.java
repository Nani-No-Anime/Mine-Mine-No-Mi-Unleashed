package xyz.pixelatedw.mineminenomi.api.abilities;

import net.minecraft.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;









public interface IMultiTargetAbility
{
  public static final List<Integer> TARGETS = new ArrayList<>();

  
  default void clearTargets() {
    TARGETS.clear();
  }

  
  default boolean isTarget(LivingEntity target) {
    if (!TARGETS.contains(Integer.valueOf(target.getEntityId()))) {
      
      TARGETS.add(Integer.valueOf(target.getEntityId()));
      return true;
    } 
    
    return false;
  }
}


