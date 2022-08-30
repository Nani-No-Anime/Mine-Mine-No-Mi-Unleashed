/*    */ package xyz.pixelatedw.mineminenomi.packets.client.quest;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncQuestDataPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ 
/*    */ public class CAbandonQuestPacket
/*    */ {
/*    */   private int questId;
/*    */   
/*    */   public CAbandonQuestPacket() {}
/*    */   
/*    */   public CAbandonQuestPacket(int questId) {
/* 23 */     this.questId = questId;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 28 */     buffer.writeInt(this.questId);
/*    */   }
/*    */ 
/*    */   
/*    */   public static CAbandonQuestPacket decode(PacketBuffer buffer) {
/* 33 */     CAbandonQuestPacket msg = new CAbandonQuestPacket();
/* 34 */     msg.questId = buffer.readInt();
/* 35 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(CAbandonQuestPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 40 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
/*    */     {
/* 42 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity player = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             IQuestData props = QuestDataCapability.get((PlayerEntity)player);
/*    */             
/*    */             Quest current = props.getInProgressQuest(message.questId);
/*    */             props.removeInProgressQuest(current);
/*    */             WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), props), (PlayerEntity)player);
/*    */           });
/*    */     }
/* 52 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\client\quest\CAbandonQuestPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */