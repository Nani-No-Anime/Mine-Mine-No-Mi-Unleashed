package xyz.pixelatedw.mineminenomi.wypi;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataProvider;
import xyz.pixelatedw.mineminenomi.packets.client.quest.CUpdateQuestStatePacket;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncQuestDataPacket;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataProvider;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
import xyz.pixelatedw.mineminenomi.wypi.network.packets.client.CSyncAbilityDataPacket;
import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;

public class APIDefaults
{
  public static final String ABILITY_MESSAGE_EMPTY_STACK = WyRegistry.registerName("ability.item.message.empty_stack", "Cannot equip because it's an empty stack!");
  public static final String ABILITY_MESSAGE_ANOTHER_ITEM_IN_HAND = WyRegistry.registerName("ability.item.message.another_item_in_hand", "Cannot equip while holding another item in hand!");


  
  public static void initPackets() {
    WyNetwork.registerPacket(CSyncAbilityDataPacket.class, CSyncAbilityDataPacket::encode, CSyncAbilityDataPacket::decode, CSyncAbilityDataPacket::handle);
    WyNetwork.registerPacket(CUpdateQuestStatePacket.class, CUpdateQuestStatePacket::encode, CUpdateQuestStatePacket::decode, CUpdateQuestStatePacket::handle);

    
    WyNetwork.registerPacket(SSyncAbilityDataPacket.class, SSyncAbilityDataPacket::encode, SSyncAbilityDataPacket::decode, SSyncAbilityDataPacket::handle);
    WyNetwork.registerPacket(SSyncQuestDataPacket.class, SSyncQuestDataPacket::encode, SSyncQuestDataPacket::decode, SSyncQuestDataPacket::handle);
  }

  
  public static void initCapabilities() {
    AbilityDataCapability.register();
    QuestDataCapability.register();
  }

  
  public static class Registry
  {
    @SubscribeEvent
    public void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
      if (event.getObject() instanceof net.minecraft.entity.player.PlayerEntity) {
        
        event.addCapability(new ResourceLocation(APIConfig.projectId, "ability_data"), (ICapabilityProvider)new AbilityDataProvider());
        event.addCapability(new ResourceLocation(APIConfig.projectId, "quest_data"), (ICapabilityProvider)new QuestDataProvider());
      } 
    }
  }
}


