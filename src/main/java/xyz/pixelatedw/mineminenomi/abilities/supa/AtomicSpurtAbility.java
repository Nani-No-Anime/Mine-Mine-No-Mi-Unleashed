package xyz.pixelatedw.mineminenomi.abilities.supa;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;

public class AtomicSpurtAbility extends ContinuousAbility implements IParallelContinuousAbility {
  public static final AtomicSpurtAbility INSTANCE = new AtomicSpurtAbility();

  
  public AtomicSpurtAbility() {
    super("Atomic Spurt", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(5.0D);
    setDescription("Allows the user to skate around using bladed feet");
    needsClientSide();
    
    this.duringContinuityEvent = this::duringContinuityEvent;
  }

  
  private void duringContinuityEvent(PlayerEntity player, int continueTime) {
    if (player.onGround)
    {
      if (Math.abs(player.getMotion().getX()) < 0.2D || Math.abs(player.getMotion().getZ()) < 0.2D)
      {
        player.setMotion(player.getMotion().getX() * 1.6D, player.getMotion().getY(), player.getMotion().getZ() * 1.6D);
      }
    }
  }
}


