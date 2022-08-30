/*    */ package xyz.pixelatedw.mineminenomi.packets.client;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncWorldDataPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CRequestSyncWorldDataPacket
/*    */ {
/*    */   public void encode(PacketBuffer buffer) {}
/*    */   
/*    */   public static CRequestSyncWorldDataPacket decode(PacketBuffer buffer) {
/* 23 */     CRequestSyncWorldDataPacket msg = new CRequestSyncWorldDataPacket();
/* 24 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(CRequestSyncWorldDataPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 29 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
/*    */     {
/* 31 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             ExtendedWorldData worldData = ExtendedWorldData.get(((PlayerEntity)serverPlayerEntity).world);
/*    */             
/*    */             WyNetwork.sendTo(new SSyncWorldDataPacket(worldData), (PlayerEntity)serverPlayerEntity);
/*    */           });
/*    */     }
/* 39 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\client\CRequestSyncWorldDataPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */