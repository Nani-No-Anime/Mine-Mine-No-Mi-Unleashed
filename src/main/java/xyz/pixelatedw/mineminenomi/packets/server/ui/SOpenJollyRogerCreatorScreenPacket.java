/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ui;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.screens.JollyRogerCreatorScreen;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SOpenJollyRogerCreatorScreenPacket
/*    */ {
/*    */   private boolean isEditing;
/*    */   
/*    */   public SOpenJollyRogerCreatorScreenPacket() {}
/*    */   
/*    */   public SOpenJollyRogerCreatorScreenPacket(boolean isEditing) {
/* 22 */     this.isEditing = isEditing;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 27 */     buffer.writeBoolean(this.isEditing);
/*    */   }
/*    */ 
/*    */   
/*    */   public static SOpenJollyRogerCreatorScreenPacket decode(PacketBuffer buffer) {
/* 32 */     SOpenJollyRogerCreatorScreenPacket msg = new SOpenJollyRogerCreatorScreenPacket();
/* 33 */     msg.isEditing = buffer.readBoolean();
/* 34 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SOpenJollyRogerCreatorScreenPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 39 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/* 40 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message)); 
/* 41 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public static class ClientHandler
/*    */   {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SOpenJollyRogerCreatorScreenPacket message) {
/* 49 */       JollyRogerCreatorScreen.open(message.isEditing);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\serve\\ui\SOpenJollyRogerCreatorScreenPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */