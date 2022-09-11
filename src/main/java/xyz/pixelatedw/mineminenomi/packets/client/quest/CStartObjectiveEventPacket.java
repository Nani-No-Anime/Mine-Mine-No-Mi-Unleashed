package xyz.pixelatedw.mineminenomi.packets.client.quest;

import java.util.function.Supplier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncQuestDataPacket;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;


public class CStartObjectiveEventPacket
{
  private int questId;
  private int objId;
  
  public CStartObjectiveEventPacket() {}
  
  public CStartObjectiveEventPacket(int questId, int objId) {
    this.questId = questId;
    this.objId = objId;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeInt(this.questId);
    buffer.writeInt(this.objId);
  }

  
  public static CStartObjectiveEventPacket decode(PacketBuffer buffer) {
    CStartObjectiveEventPacket msg = new CStartObjectiveEventPacket();
    msg.questId = buffer.readInt();
    msg.objId = buffer.readInt();
    return msg;
  }

  
  public static void handle(CStartObjectiveEventPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
            ServerPlayerEntity player = ((NetworkEvent.Context)ctx.get()).getSender();
            
            IQuestData props = QuestDataCapability.get((PlayerEntity)player);
            
            Quest current = props.getInProgressQuest(message.questId);
            
            if (current != null) {
              Objective obj = current.getObjectives().get(message.objId);
              if (obj != null) {
                if (obj.hasStartedEvent()) {
                  obj.triggerRestartEvent((PlayerEntity)player);
                } else {
                  obj.triggerStartEvent((PlayerEntity)player);
                } 
                WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), props), (PlayerEntity)player);
              } 
            } 
          });
    }
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }
}


