/*    */ package xyz.pixelatedw.mineminenomi.packets.client.challenge;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.Challenge;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncChallengeDataPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ 
/*    */ public class CResetChallengesPacket
/*    */ {
/*    */   private String category;
/*    */   
/*    */   public CResetChallengesPacket() {}
/*    */   
/*    */   public CResetChallengesPacket(String category) {
/* 25 */     this.category = category;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 30 */     buffer.writeInt(this.category.length());
/* 31 */     buffer.writeString(this.category);
/*    */   }
/*    */ 
/*    */   
/*    */   public static CResetChallengesPacket decode(PacketBuffer buffer) {
/* 36 */     CResetChallengesPacket msg = new CResetChallengesPacket();
/* 37 */     int len = buffer.readInt();
/* 38 */     msg.category = buffer.readString(len);
/* 39 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(CResetChallengesPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 44 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
/*    */     {
/* 46 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             if (!CommonConfig.INSTANCE.isReChallengesEnabled()) {
/*    */               return;
/*    */             }
/*    */             
/*    */             ServerPlayerEntity player = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             IChallengesData props = ChallengesDataCapability.get((PlayerEntity)player);
/*    */             
/*    */             List<Challenge> challenges = (List<Challenge>)props.getGroupedChallenges().get(message.category);
/*    */             for (Challenge ch : challenges) {
/*    */               ch.setComplete(false);
/*    */             }
/*    */             WyNetwork.sendTo(new SSyncChallengeDataPacket(player.getEntityId(), props), (PlayerEntity)player);
/*    */           });
/*    */     }
/* 62 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\client\challenge\CResetChallengesPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */