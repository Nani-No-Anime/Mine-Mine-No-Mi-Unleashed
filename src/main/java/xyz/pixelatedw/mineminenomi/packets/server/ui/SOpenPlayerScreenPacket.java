/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ui;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.screens.PlayerStatsScreen;
/*    */ 
/*    */ 
/*    */ public class SOpenPlayerScreenPacket
/*    */ {
/*    */   private boolean hasQuests;
/*    */   private boolean hasChallenges;
/*    */   
/*    */   public SOpenPlayerScreenPacket() {}
/*    */   
/*    */   public SOpenPlayerScreenPacket(boolean hasQuests, boolean hasChallenges) {
/* 20 */     this.hasQuests = hasQuests;
/* 21 */     this.hasChallenges = hasChallenges;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 26 */     buffer.writeBoolean(this.hasQuests);
/* 27 */     buffer.writeBoolean(this.hasChallenges);
/*    */   }
/*    */ 
/*    */   
/*    */   public static SOpenPlayerScreenPacket decode(PacketBuffer buffer) {
/* 32 */     SOpenPlayerScreenPacket msg = new SOpenPlayerScreenPacket();
/* 33 */     msg.hasQuests = buffer.readBoolean();
/* 34 */     msg.hasChallenges = buffer.readBoolean();
/* 35 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SOpenPlayerScreenPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 40 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/* 41 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message)); 
/* 42 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public static class ClientHandler
/*    */   {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SOpenPlayerScreenPacket message) {
/* 50 */       PlayerStatsScreen.open(message.hasQuests, message.hasChallenges);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\serve\\ui\SOpenPlayerScreenPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */