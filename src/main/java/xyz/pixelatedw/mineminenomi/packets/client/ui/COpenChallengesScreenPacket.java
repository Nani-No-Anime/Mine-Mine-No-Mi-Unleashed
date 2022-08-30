/*    */ package xyz.pixelatedw.mineminenomi.packets.client.ui;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenChallengesScreenPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class COpenChallengesScreenPacket
/*    */ {
/*    */   public void encode(PacketBuffer buffer) {}
/*    */   
/*    */   public static COpenChallengesScreenPacket decode(PacketBuffer buffer) {
/* 23 */     COpenChallengesScreenPacket msg = new COpenChallengesScreenPacket();
/* 24 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(COpenChallengesScreenPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 29 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
/*    */     {
/* 31 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             boolean canRetakeChallenges = CommonConfig.INSTANCE.isReChallengesEnabled();
/*    */             
/*    */             WyNetwork.sendTo(new SOpenChallengesScreenPacket(canRetakeChallenges), (PlayerEntity)serverPlayerEntity);
/*    */           });
/*    */     }
/*    */     
/* 40 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\clien\\ui\COpenChallengesScreenPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */