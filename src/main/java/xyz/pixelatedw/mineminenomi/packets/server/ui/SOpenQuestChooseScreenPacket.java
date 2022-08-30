/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ui;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.client.gui.screen.Screen;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.TrainerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.screens.TrainerScreen;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SOpenQuestChooseScreenPacket
/*    */ {
/*    */   private int questGiverEntity;
/*    */   
/*    */   public SOpenQuestChooseScreenPacket() {}
/*    */   
/*    */   public SOpenQuestChooseScreenPacket(int questGiverEntity) {
/* 26 */     this.questGiverEntity = questGiverEntity;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 31 */     buffer.writeInt(this.questGiverEntity);
/*    */   }
/*    */ 
/*    */   
/*    */   public static SOpenQuestChooseScreenPacket decode(PacketBuffer buffer) {
/* 36 */     SOpenQuestChooseScreenPacket msg = new SOpenQuestChooseScreenPacket();
/* 37 */     msg.questGiverEntity = buffer.readInt();
/* 38 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SOpenQuestChooseScreenPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 43 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/* 44 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message)); 
/* 45 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public static class ClientHandler
/*    */   {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SOpenQuestChooseScreenPacket message) {
/* 53 */       ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
/* 54 */       Entity questGiver = (Minecraft.getInstance()).world.getEntityByID(message.questGiverEntity);
/*    */       
/* 56 */       if (!(questGiver instanceof TrainerEntity)) {
/*    */         return;
/*    */       }
/* 59 */       Minecraft.getInstance().displayGuiScreen((Screen)new TrainerScreen((PlayerEntity)clientPlayerEntity, (TrainerEntity)questGiver, ((TrainerEntity)questGiver).getAvailableQuests((PlayerEntity)clientPlayerEntity)));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\serve\\ui\SOpenQuestChooseScreenPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */