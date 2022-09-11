package xyz.pixelatedw.mineminenomi.api.abilities;

import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public interface IOutOfBodyAbility {
  public static final Predicate<Ability> IS_ACTIVE= (ability -> !(ability instanceof IOutOfBodyAbility) ? false : ability.isContinuous());
  












  
  default double getDistanceFromPivot(Entity entity) {
    if (getPivotPoint() == null)
      return -1.0D; 
    return Math.sqrt(entity.getDistanceSq(getPivotPoint().getX(), getPivotPoint().getY(), getPivotPoint().getZ()));
  }

  
  default void startOutOfBody(PlayerEntity player) {
    player.onGround = false;
    player.abilities.isFlying = true;
    if (player instanceof ServerPlayerEntity) {
      ((ServerPlayerEntity)player).sendPlayerAbilities();
    }
  }
  
  default void stopOutOfBody(PlayerEntity player) {
    player.abilities.isFlying = false;
    if (player instanceof ServerPlayerEntity)
      ((ServerPlayerEntity)player).sendPlayerAbilities(); 
  }
  
  float getMaxRange();
  
  @Nullable
  BlockPos getPivotPoint();
  
  boolean isPhysical();
}


