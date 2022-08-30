/*    */ package xyz.pixelatedw.mineminenomi.wypi;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.common.capabilities.ICapabilityProvider;
/*    */ import net.minecraftforge.event.AttachCapabilitiesEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataProvider;
/*    */ import xyz.pixelatedw.mineminenomi.packets.client.quest.CUpdateQuestStatePacket;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncQuestDataPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataProvider;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.packets.client.CSyncAbilityDataPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;
/*    */ 
/*    */ public class APIDefaults
/*    */ {
/* 20 */   public static final String ABILITY_MESSAGE_EMPTY_STACK = WyRegistry.registerName("ability.item.message.empty_stack", "Cannot equip because it's an empty stack!");
/* 21 */   public static final String ABILITY_MESSAGE_ANOTHER_ITEM_IN_HAND = WyRegistry.registerName("ability.item.message.another_item_in_hand", "Cannot equip while holding another item in hand!");
/*    */ 
/*    */ 
/*    */   
/*    */   public static void initPackets() {
/* 26 */     WyNetwork.registerPacket(CSyncAbilityDataPacket.class, CSyncAbilityDataPacket::encode, CSyncAbilityDataPacket::decode, CSyncAbilityDataPacket::handle);
/* 27 */     WyNetwork.registerPacket(CUpdateQuestStatePacket.class, CUpdateQuestStatePacket::encode, CUpdateQuestStatePacket::decode, CUpdateQuestStatePacket::handle);
/*    */ 
/*    */     
/* 30 */     WyNetwork.registerPacket(SSyncAbilityDataPacket.class, SSyncAbilityDataPacket::encode, SSyncAbilityDataPacket::decode, SSyncAbilityDataPacket::handle);
/* 31 */     WyNetwork.registerPacket(SSyncQuestDataPacket.class, SSyncQuestDataPacket::encode, SSyncQuestDataPacket::decode, SSyncQuestDataPacket::handle);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void initCapabilities() {
/* 36 */     AbilityDataCapability.register();
/* 37 */     QuestDataCapability.register();
/*    */   }
/*    */ 
/*    */   
/*    */   public static class Registry
/*    */   {
/*    */     @SubscribeEvent
/*    */     public void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
/* 45 */       if (event.getObject() instanceof net.minecraft.entity.player.PlayerEntity) {
/*    */         
/* 47 */         event.addCapability(new ResourceLocation(APIConfig.projectId, "ability_data"), (ICapabilityProvider)new AbilityDataProvider());
/* 48 */         event.addCapability(new ResourceLocation(APIConfig.projectId, "quest_data"), (ICapabilityProvider)new QuestDataProvider());
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\APIDefaults.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */