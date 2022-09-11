package xyz.pixelatedw.mineminenomi.api.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Event.HasResult;

@HasResult
public class IssueBountyEvent
  extends PlayerEvent
{
  private PlayerEntity issuer;
  private long bounty;
  
  public IssueBountyEvent(PlayerEntity target, PlayerEntity issuer, long bounty) {
    super(target);
  }

  
  public PlayerEntity getIssuer() {
    return this.issuer;
  }

  
  public long getBounty() {
    return this.bounty;
  }
}


