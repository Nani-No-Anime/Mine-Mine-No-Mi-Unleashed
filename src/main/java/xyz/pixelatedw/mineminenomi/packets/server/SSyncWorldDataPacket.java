/*    */ package xyz.pixelatedw.mineminenomi.packets.server;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.world.ClientWorld;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SSyncWorldDataPacket
/*    */ {
/*    */   private CompoundNBT data;
/*    */   
/*    */   public SSyncWorldDataPacket() {}
/*    */   
/*    */   public SSyncWorldDataPacket(ExtendedWorldData worldData) {
/* 25 */     this.data = new CompoundNBT();
/* 26 */     this.data = worldData.write(this.data);
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 31 */     buffer.writeCompoundTag(this.data);
/*    */   }
/*    */ 
/*    */   
/*    */   public static SSyncWorldDataPacket decode(PacketBuffer buffer) {
/* 36 */     SSyncWorldDataPacket msg = new SSyncWorldDataPacket();
/* 37 */     msg.data = buffer.readCompoundTag();
/* 38 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SSyncWorldDataPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 43 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/*    */     {
/* 45 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 50 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public static class ClientHandler
/*    */   {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SSyncWorldDataPacket message) {
/* 58 */       ClientWorld clientWorld = (Minecraft.getInstance()).world;
/* 59 */       ExtendedWorldData worldData = ExtendedWorldData.get((World)clientWorld);
/* 60 */       if (worldData != null)
/* 61 */         worldData.read(message.data); 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\server\SSyncWorldDataPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */