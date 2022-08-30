/*    */ package xyz.pixelatedw.mineminenomi.packets.client;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IObtainItemObjective;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncQuestDataPacket;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenQuestTrackerScreenPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ public class CRequestSyncQuestDataPacket
/*    */ {
/*    */   private boolean openScreen;
/*    */   
/*    */   public CRequestSyncQuestDataPacket() {}
/*    */   
/*    */   public CRequestSyncQuestDataPacket(boolean openScreen) {
/* 28 */     this.openScreen = openScreen;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 33 */     buffer.writeBoolean(this.openScreen);
/*    */   }
/*    */ 
/*    */   
/*    */   public static CRequestSyncQuestDataPacket decode(PacketBuffer buffer) {
/* 38 */     CRequestSyncQuestDataPacket msg = new CRequestSyncQuestDataPacket();
/* 39 */     msg.openScreen = buffer.readBoolean();
/* 40 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(CRequestSyncQuestDataPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 45 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
/*    */     {
/* 47 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             IQuestData props = QuestDataCapability.get((PlayerEntity)serverPlayerEntity);
/*    */             
/*    */             for (Quest qst : props.getInProgressQuests()) {
/*    */               if (qst != null) {
/*    */                 for (Objective obj : qst.getObjectives()) {
/*    */                   if (obj instanceof IObtainItemObjective) {
/*    */                     ArrayList<ItemStack> slots = new ArrayList<>();
/*    */                     
/*    */                     slots.addAll((Collection<? extends ItemStack>)((PlayerEntity)serverPlayerEntity).inventory.mainInventory);
/*    */                     
/*    */                     slots.addAll((Collection<? extends ItemStack>)((PlayerEntity)serverPlayerEntity).inventory.offHandInventory);
/*    */                     
/*    */                     int items = ((IObtainItemObjective)obj).checkItems(slots);
/*    */                     
/*    */                     if (items > 0) {
/*    */                       obj.setProgress(items);
/*    */                       
/*    */                       WyNetwork.sendTo(new SSyncQuestDataPacket(serverPlayerEntity.getEntityId(), props), (PlayerEntity)serverPlayerEntity);
/*    */                     } 
/*    */                   } 
/*    */                 } 
/*    */               }
/*    */             } 
/*    */             WyNetwork.sendTo(new SSyncQuestDataPacket(serverPlayerEntity.getEntityId(), props), (PlayerEntity)serverPlayerEntity);
/*    */             if (message.openScreen) {
/*    */               WyNetwork.sendTo(new SOpenQuestTrackerScreenPacket(), (PlayerEntity)serverPlayerEntity);
/*    */             }
/*    */           });
/*    */     }
/* 79 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\client\CRequestSyncQuestDataPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */