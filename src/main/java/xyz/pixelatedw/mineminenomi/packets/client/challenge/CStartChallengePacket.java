package xyz.pixelatedw.mineminenomi.packets.client.challenge;

import java.util.function.Supplier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.challenges.Challenge;
import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;


public class CStartChallengePacket
{
  private String id;
  
  public CStartChallengePacket() {}
  
  public CStartChallengePacket(String id) {
    this.id = id;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeInt(this.id.length());
    buffer.writeString(this.id);
  }

  
  public static CStartChallengePacket decode(PacketBuffer buffer) {
    CStartChallengePacket msg = new CStartChallengePacket();
    int len = buffer.readInt();
    msg.id = buffer.readString(len);
    return msg;
  }

  
  public static void handle(CStartChallengePacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
            ServerPlayerEntity player = ((NetworkEvent.Context)ctx.get()).getSender();
            
            IChallengesData props = ChallengesDataCapability.get((PlayerEntity)player);
            Challenge current = props.getChallenge(message.id);
            if (current != null) {
              current.start((PlayerEntity)player);
            }
          });
    }
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }
}


