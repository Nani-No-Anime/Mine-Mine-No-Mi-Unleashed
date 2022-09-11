package xyz.pixelatedw.mineminenomi.api.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;

@Cancelable
public class BountyEvent
  extends PlayerEvent
{
  private long bounty;
  
  public BountyEvent(PlayerEntity player) {
    this(player, 0L);
  }

  
  public BountyEvent(PlayerEntity player, long bounty) {
    super(player);
    this.bounty = bounty;
  }

  
  public long getBounty() {
    return this.bounty;
  }
}


