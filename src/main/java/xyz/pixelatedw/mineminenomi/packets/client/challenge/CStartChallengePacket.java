/*    */ package xyz.pixelatedw.mineminenomi.packets.client.challenge;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.Challenge;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
/*    */ 
/*    */ 
/*    */ public class CStartChallengePacket
/*    */ {
/*    */   private String id;
/*    */   
/*    */   public CStartChallengePacket() {}
/*    */   
/*    */   public CStartChallengePacket(String id) {
/* 21 */     this.id = id;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 26 */     buffer.writeInt(this.id.length());
/* 27 */     buffer.writeString(this.id);
/*    */   }
/*    */ 
/*    */   
/*    */   public static CStartChallengePacket decode(PacketBuffer buffer) {
/* 32 */     CStartChallengePacket msg = new CStartChallengePacket();
/* 33 */     int len = buffer.readInt();
/* 34 */     msg.id = buffer.readString(len);
/* 35 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(CStartChallengePacket message, Supplier<NetworkEvent.Context> ctx) {
/* 40 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
/*    */     {
/* 42 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity player = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             IChallengesData props = ChallengesDataCapability.get((PlayerEntity)player);
/*    */             Challenge current = props.getChallenge(message.id);
/*    */             if (current != null) {
/*    */               current.start((PlayerEntity)player);
/*    */             }
/*    */           });
/*    */     }
/* 52 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\client\challenge\CStartChallengePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */