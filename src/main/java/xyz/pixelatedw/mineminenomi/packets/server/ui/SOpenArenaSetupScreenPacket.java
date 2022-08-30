/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ui;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.screens.ArenaSetupScreen;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SOpenArenaSetupScreenPacket
/*    */ {
/*    */   private String arenaName;
/*    */   
/*    */   public SOpenArenaSetupScreenPacket() {}
/*    */   
/*    */   public SOpenArenaSetupScreenPacket(String arenaName) {
/* 20 */     this.arenaName = arenaName;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 25 */     buffer.writeInt(this.arenaName.length());
/* 26 */     buffer.writeString(this.arenaName, this.arenaName.length());
/*    */   }
/*    */ 
/*    */   
/*    */   public static SOpenArenaSetupScreenPacket decode(PacketBuffer buffer) {
/* 31 */     SOpenArenaSetupScreenPacket msg = new SOpenArenaSetupScreenPacket();
/* 32 */     int len = buffer.readInt();
/* 33 */     msg.arenaName = buffer.readString(len);
/* 34 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SOpenArenaSetupScreenPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 39 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/* 40 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message)); 
/* 41 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public static class ClientHandler
/*    */   {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SOpenArenaSetupScreenPacket message) {
/* 49 */       ArenaSetupScreen.open(message.arenaName);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\serve\\ui\SOpenArenaSetupScreenPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */