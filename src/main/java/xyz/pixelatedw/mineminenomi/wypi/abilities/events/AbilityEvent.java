package xyz.pixelatedw.mineminenomi.wypi.abilities.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class AbilityEvent
  extends PlayerEvent
{
  private Ability ability;
  
  public AbilityEvent(PlayerEntity player, Ability ability) {
    super(player);
    this.ability = ability;
  }

  
  public Ability getAbility() {
    return this.ability;
  }
}


