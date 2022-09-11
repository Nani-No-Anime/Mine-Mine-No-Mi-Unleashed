package xyz.pixelatedw.mineminenomi.api.events;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;

public class SetPlayerDetailsEvent
  extends PlayerEvent {
  private IEntityStats props;
  
  public SetPlayerDetailsEvent(PlayerEntity player) {
    super(player);
    this.props = EntityStatsCapability.get((LivingEntity)player);
  }

  
  public IEntityStats getEntityStats() {
    return this.props;
  }
}


