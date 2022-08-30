/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ui;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.screens.ChallengesScreen;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SOpenChallengesScreenPacket
/*    */ {
/*    */   private boolean canRetakeChallanges;
/*    */   
/*    */   public SOpenChallengesScreenPacket() {}
/*    */   
/*    */   public SOpenChallengesScreenPacket(boolean canRetakeChallanges) {
/* 20 */     this.canRetakeChallanges = canRetakeChallanges;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 25 */     buffer.writeBoolean(this.canRetakeChallanges);
/*    */   }
/*    */ 
/*    */   
/*    */   public static SOpenChallengesScreenPacket decode(PacketBuffer buffer) {
/* 30 */     SOpenChallengesScreenPacket msg = new SOpenChallengesScreenPacket();
/* 31 */     msg.canRetakeChallanges = buffer.readBoolean();
/* 32 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SOpenChallengesScreenPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 37 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/* 38 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message)); 
/* 39 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public static class ClientHandler
/*    */   {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SOpenChallengesScreenPacket message) {
/* 47 */       ChallengesScreen.open(message.canRetakeChallanges);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\serve\\ui\SOpenChallengesScreenPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */