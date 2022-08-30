/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ability;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SAnimeScreamPacket
/*    */ {
/*    */   private int entityId;
/*    */   private String message;
/*    */   
/*    */   public SAnimeScreamPacket() {}
/*    */   
/*    */   public SAnimeScreamPacket(int entityId, String message) {
/* 24 */     this.entityId = entityId;
/* 25 */     this.message = message;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 30 */     buffer.writeInt(this.entityId);
/* 31 */     buffer.writeInt(this.message.length());
/* 32 */     buffer.writeString(this.message, this.message.length());
/*    */   }
/*    */ 
/*    */   
/*    */   public static SAnimeScreamPacket decode(PacketBuffer buffer) {
/* 37 */     SAnimeScreamPacket msg = new SAnimeScreamPacket();
/* 38 */     msg.entityId = buffer.readInt();
/* 39 */     int len = buffer.readInt();
/* 40 */     msg.message = buffer.readString(len);
/* 41 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SAnimeScreamPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 46 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/*    */     {
/* 48 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 53 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public static class ClientHandler
/*    */   {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SAnimeScreamPacket message) {
/* 61 */       Entity target = (Minecraft.getInstance()).world.getEntityByID(message.entityId);
/* 62 */       if (target == null || !(target instanceof net.minecraft.entity.player.PlayerEntity)) {
/*    */         return;
/*    */       }
/* 65 */       (Minecraft.getInstance()).player.sendMessage((ITextComponent)new StringTextComponent("<" + target.getDisplayName().getFormattedText() + "> " + message.message.toUpperCase()));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\SAnimeScreamPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */