/*    */ package xyz.pixelatedw.mineminenomi.packets.server.randfruit;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.events.devilfruits.RandomFruitEvents;
/*    */ 
/*    */ 
/*    */ public class SSetFruitSeedsPacket
/*    */ {
/* 16 */   private HashMap<Integer, Long> seeds = new HashMap<>();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SSetFruitSeedsPacket(HashMap<Integer, Long> seeds) {
/* 22 */     this.seeds = seeds;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 27 */     buffer.writeInt(this.seeds.entrySet().size());
/* 28 */     for (Map.Entry<Integer, Long> entry : this.seeds.entrySet()) {
/*    */       
/* 30 */       buffer.writeInt(((Integer)entry.getKey()).intValue());
/* 31 */       buffer.writeLong(((Long)entry.getValue()).longValue());
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static SSetFruitSeedsPacket decode(PacketBuffer buffer) {
/* 37 */     SSetFruitSeedsPacket msg = new SSetFruitSeedsPacket();
/* 38 */     HashMap<Integer, Long> seeds = new HashMap<>();
/* 39 */     int size = buffer.readInt();
/* 40 */     for (int i = 0; i < size; i++) {
/*    */       
/* 42 */       int key = buffer.readInt();
/* 43 */       long seed = buffer.readLong();
/* 44 */       seeds.put(Integer.valueOf(key), Long.valueOf(seed));
/*    */     } 
/* 46 */     msg.seeds = seeds;
/* 47 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SSetFruitSeedsPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 52 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/*    */     {
/* 54 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 59 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public SSetFruitSeedsPacket() {}
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SSetFruitSeedsPacket message) {
/* 67 */       RandomFruitEvents.Client.FRUIT_SEEDS = message.seeds;
/* 68 */       RandomFruitEvents.Client.DIRTY = true;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\server\randfruit\SSetFruitSeedsPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */