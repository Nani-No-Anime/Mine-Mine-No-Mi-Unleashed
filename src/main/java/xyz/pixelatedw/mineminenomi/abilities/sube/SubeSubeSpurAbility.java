package xyz.pixelatedw.mineminenomi.abilities.sube;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;

public class SubeSubeSpurAbility extends ContinuousAbility implements IParallelContinuousAbility {
  public static final SubeSubeSpurAbility INSTANCE = new SubeSubeSpurAbility();

  
  public SubeSubeSpurAbility() {
    super("Sube Sube Spur", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(5.0D);
    setDescription("Allows the user to skate around using their feet.");
    needsClientSide();
    
    this.duringContinuityEvent = this::duringContinuityEvent;
  }

  
  private void duringContinuityEvent(PlayerEntity player, int continueTime) {
    if (player.onGround && (Math.abs(player.getMotion().getX() * 1.7D) < 0.3D || Math.abs(player.getMotion().getZ() * 1.7D) < 0.3D))
    {
      player.setMotion(player.getMotion().getX() * 1.7D, player.getMotion().getY(), player.getMotion().getZ() * 1.7D);
    }
  }
}


