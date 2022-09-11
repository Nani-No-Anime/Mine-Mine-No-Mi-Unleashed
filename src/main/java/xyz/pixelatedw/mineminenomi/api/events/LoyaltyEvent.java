package xyz.pixelatedw.mineminenomi.api.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;

@Cancelable
public class LoyaltyEvent
  extends PlayerEvent
{
  private long loyalty;
  
  public LoyaltyEvent(PlayerEntity player) {
    this(player, 0L);
  }

  
  public LoyaltyEvent(PlayerEntity player, long bounty) {
    super(player);
    this.loyalty = bounty;
  }

  
  public long getLoyalty() {
    return this.loyalty;
  }
}


