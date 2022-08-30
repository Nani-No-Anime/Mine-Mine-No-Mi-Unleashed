/*    */ package xyz.pixelatedw.mineminenomi.packets.server;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ 
/*    */ public class SFlightValuePacket
/*    */ {
/*    */   private boolean value;
/*    */   
/*    */   public SFlightValuePacket() {}
/*    */   
/*    */   public SFlightValuePacket(boolean value) {
/* 20 */     this.value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 25 */     buffer.writeBoolean(this.value);
/*    */   }
/*    */ 
/*    */   
/*    */   public static SFlightValuePacket decode(PacketBuffer buffer) {
/* 30 */     SFlightValuePacket msg = new SFlightValuePacket();
/* 31 */     msg.value = buffer.readBoolean();
/* 32 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SFlightValuePacket message, Supplier<NetworkEvent.Context> ctx) {
/* 37 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/*    */     {
/* 39 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 44 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public static class ClientHandler
/*    */   {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SFlightValuePacket message) {
/* 52 */       ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
/*    */       
/* 54 */       ((PlayerEntity)clientPlayerEntity).abilities.allowFlying = message.value;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\server\SFlightValuePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */