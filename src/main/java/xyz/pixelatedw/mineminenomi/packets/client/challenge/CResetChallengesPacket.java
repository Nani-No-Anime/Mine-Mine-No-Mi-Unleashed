package xyz.pixelatedw.mineminenomi.packets.client.challenge;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.challenges.Challenge;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncChallengeDataPacket;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

import java.util.List;
import java.util.function.Supplier;


public class CResetChallengesPacket
{
  private String category;
  
  public CResetChallengesPacket() {}
  
  public CResetChallengesPacket(String category) {
    this.category = category;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeInt(this.category.length());
    buffer.writeString(this.category);
  }

  
  public static CResetChallengesPacket decode(PacketBuffer buffer) {
    CResetChallengesPacket msg = new CResetChallengesPacket();
    int len = buffer.readInt();
    msg.category = buffer.readString(len);
    return msg;
  }

  
  public static void handle(CResetChallengesPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
            if (!CommonConfig.INSTANCE.isReChallengesEnabled()) {
              return;
            }
            
            ServerPlayerEntity player = ((NetworkEvent.Context)ctx.get()).getSender();
            
            IChallengesData props = ChallengesDataCapability.get((PlayerEntity)player);
            
            List<Challenge> challenges = (List<Challenge>)props.getGroupedChallenges().get(message.category);
            for (Challenge ch : challenges) {
              ch.setComplete(false);
            }
            WyNetwork.sendTo(new SSyncChallengeDataPacket(player.getEntityId(), props), (PlayerEntity)player);
          });
    }
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }
}


