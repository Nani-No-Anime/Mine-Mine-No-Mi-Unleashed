package xyz.pixelatedw.mineminenomi.api.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;

@Cancelable
public class DorikiEvent
  extends PlayerEvent
{
  public int doriki;
  
  public DorikiEvent(PlayerEntity player, int doriki) {
    super(player);
    this.doriki = doriki;
  }
}


