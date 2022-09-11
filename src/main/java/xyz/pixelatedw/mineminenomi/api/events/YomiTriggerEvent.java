package xyz.pixelatedw.mineminenomi.api.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;

public class YomiTriggerEvent
  extends PlayerEvent {
  public IDevilFruit oldPlayerData;
  public IDevilFruit newPlayerData;
  
  public YomiTriggerEvent(PlayerEntity player, IDevilFruit oldPlayerData, IDevilFruit newPlayerData) {
    super(player);
    this.oldPlayerData = oldPlayerData;
    this.newPlayerData = newPlayerData;
  }
}


