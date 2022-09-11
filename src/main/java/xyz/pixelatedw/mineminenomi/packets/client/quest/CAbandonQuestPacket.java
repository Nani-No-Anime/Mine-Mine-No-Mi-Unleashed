package xyz.pixelatedw.mineminenomi.packets.client.quest;

import java.util.function.Supplier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncQuestDataPacket;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;


public class CAbandonQuestPacket
{
  private int questId;
  
  public CAbandonQuestPacket() {}
  
  public CAbandonQuestPacket(int questId) {
    this.questId = questId;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeInt(this.questId);
  }

  
  public static CAbandonQuestPacket decode(PacketBuffer buffer) {
    CAbandonQuestPacket msg = new CAbandonQuestPacket();
    msg.questId = buffer.readInt();
    return msg;
  }

  
  public static void handle(CAbandonQuestPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
            ServerPlayerEntity player = ((NetworkEvent.Context)ctx.get()).getSender();
            
            IQuestData props = QuestDataCapability.get((PlayerEntity)player);
            
            Quest current = props.getInProgressQuest(message.questId);
            props.removeInProgressQuest(current);
            WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), props), (PlayerEntity)player);
          });
    }
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }
}


