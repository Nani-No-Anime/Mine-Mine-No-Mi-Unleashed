package xyz.pixelatedw.mineminenomi.packets.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.IObtainItemObjective;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncQuestDataPacket;
import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenQuestTrackerScreenPacket;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public class CRequestSyncQuestDataPacket
{
  private boolean openScreen;
  
  public CRequestSyncQuestDataPacket() {}
  
  public CRequestSyncQuestDataPacket(boolean openScreen) {
    this.openScreen = openScreen;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeBoolean(this.openScreen);
  }

  
  public static CRequestSyncQuestDataPacket decode(PacketBuffer buffer) {
    CRequestSyncQuestDataPacket msg = new CRequestSyncQuestDataPacket();
    msg.openScreen = buffer.readBoolean();
    return msg;
  }

  
  public static void handle(CRequestSyncQuestDataPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
            ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
            
            IQuestData props = QuestDataCapability.get((PlayerEntity)serverPlayerEntity);
            
            for (Quest qst : props.getInProgressQuests()) {
              if (qst != null) {
                for (Objective obj : qst.getObjectives()) {
                  if (obj instanceof IObtainItemObjective) {
                    ArrayList<ItemStack> slots = new ArrayList<>();
                    
                    slots.addAll((Collection<? extends ItemStack>)((PlayerEntity)serverPlayerEntity).inventory.mainInventory);
                    
                    slots.addAll((Collection<? extends ItemStack>)((PlayerEntity)serverPlayerEntity).inventory.offHandInventory);
                    
                    int items = ((IObtainItemObjective)obj).checkItems(slots);
                    
                    if (items > 0) {
                      obj.setProgress(items);
                      
                      WyNetwork.sendTo(new SSyncQuestDataPacket(serverPlayerEntity.getEntityId(), props), (PlayerEntity)serverPlayerEntity);
                    } 
                  } 
                } 
              }
            } 
            WyNetwork.sendTo(new SSyncQuestDataPacket(serverPlayerEntity.getEntityId(), props), (PlayerEntity)serverPlayerEntity);
            if (message.openScreen) {
              WyNetwork.sendTo(new SOpenQuestTrackerScreenPacket(), (PlayerEntity)serverPlayerEntity);
            }
          });
    }
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }
}


