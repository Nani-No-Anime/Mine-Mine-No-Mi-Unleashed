package xyz.pixelatedw.mineminenomi.packets.client;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncWorldDataPacket;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

import java.util.function.Supplier;






public class CRequestSyncWorldDataPacket
{
  public void encode(PacketBuffer buffer) {}
  
  public static CRequestSyncWorldDataPacket decode(PacketBuffer buffer) {
    CRequestSyncWorldDataPacket msg = new CRequestSyncWorldDataPacket();
    return msg;
  }

  
  public static void handle(CRequestSyncWorldDataPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
            ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
            
            ExtendedWorldData worldData = ExtendedWorldData.get(((PlayerEntity)serverPlayerEntity).world);
            
            WyNetwork.sendTo(new SSyncWorldDataPacket(worldData), (PlayerEntity)serverPlayerEntity);
          });
    }
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }
}


