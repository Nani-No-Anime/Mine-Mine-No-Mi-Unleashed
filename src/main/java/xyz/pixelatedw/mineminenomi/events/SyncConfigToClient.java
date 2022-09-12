package xyz.pixelatedw.mineminenomi.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncConfigDataPacket;

@EventBusSubscriber(modid = "mineminenomi")

public class SyncConfigToClient {
  @SubscribeEvent
  public static void onPlayerLoggedIn(EntityJoinWorldEvent event) {
    if (event.getEntity() instanceof PlayerEntity && !(event.getWorld()).isRemote) {

      PlayerEntity player = (PlayerEntity) event.getEntity();
      WyNetwork.sendTo(new SSyncConfigDataPacket(CommonConfig.INSTANCE.getDorikiLimit()), player);
    }
  }
}
