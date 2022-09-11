package xyz.pixelatedw.mineminenomi.wypi.abilities.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Cancelable;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

@Cancelable
public class AbilityUseEvent
  extends AbilityEvent
{
  public AbilityUseEvent(PlayerEntity player, Ability ability) {
    super(player, ability);
  }
}


