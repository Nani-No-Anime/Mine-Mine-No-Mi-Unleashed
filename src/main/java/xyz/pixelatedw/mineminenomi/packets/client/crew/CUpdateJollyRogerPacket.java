/*    */ package xyz.pixelatedw.mineminenomi.packets.client.crew;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*    */ import xyz.pixelatedw.mineminenomi.api.crew.JollyRoger;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncWorldDataPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ public class CUpdateJollyRogerPacket
/*    */ {
/* 18 */   private JollyRoger jollyRoger = new JollyRoger();
/*    */ 
/*    */ 
/*    */   
/*    */   public CUpdateJollyRogerPacket() {}
/*    */ 
/*    */   
/*    */   public CUpdateJollyRogerPacket(JollyRoger jollyRoger) {
/* 26 */     this.jollyRoger = jollyRoger;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 31 */     buffer.writeCompoundTag(this.jollyRoger.write());
/*    */   }
/*    */ 
/*    */   
/*    */   public static CUpdateJollyRogerPacket decode(PacketBuffer buffer) {
/* 36 */     CUpdateJollyRogerPacket msg = new CUpdateJollyRogerPacket();
/* 37 */     msg.jollyRoger.read(buffer.readCompoundTag());
/* 38 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(CUpdateJollyRogerPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 43 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
/*    */     {
/* 45 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             UUID uuid = serverPlayerEntity.getUniqueID();
/*    */             
/*    */             ExtendedWorldData worldData = ExtendedWorldData.get(((PlayerEntity)serverPlayerEntity).world);
/*    */             Crew crew = worldData.getCrewWithCaptain(uuid);
/*    */             if (crew != null) {
/*    */               worldData.updateCrewJollyRoger(crew, message.jollyRoger);
/*    */             }
/*    */             WyNetwork.sendToAll(new SSyncWorldDataPacket(worldData));
/*    */           });
/*    */     }
/* 58 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\client\crew\CUpdateJollyRogerPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */