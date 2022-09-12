package xyz.pixelatedw.mineminenomi.packets.client.ui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenChallengesScreenPacket;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

import java.util.function.Supplier;






public class COpenChallengesScreenPacket
{
  public void encode(PacketBuffer buffer) {}
  
  public static COpenChallengesScreenPacket decode(PacketBuffer buffer) {
    COpenChallengesScreenPacket msg = new COpenChallengesScreenPacket();
    return msg;
  }

  
  public static void handle(COpenChallengesScreenPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
            ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
            
            boolean canRetakeChallenges = CommonConfig.INSTANCE.isReChallengesEnabled();
            
            WyNetwork.sendTo(new SOpenChallengesScreenPacket(canRetakeChallenges), (PlayerEntity)serverPlayerEntity);
          });
    }
    
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }
}


