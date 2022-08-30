/*    */ package xyz.pixelatedw.mineminenomi.packets.client.crew;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncWorldDataPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ public class CKickFromCrewPacket
/*    */ {
/*    */   private UUID uuid;
/*    */   
/*    */   public CKickFromCrewPacket() {}
/*    */   
/*    */   public CKickFromCrewPacket(UUID uuid) {
/* 26 */     this.uuid = uuid;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 31 */     buffer.writeUniqueId(this.uuid);
/*    */   }
/*    */ 
/*    */   
/*    */   public static CKickFromCrewPacket decode(PacketBuffer buffer) {
/* 36 */     CKickFromCrewPacket msg = new CKickFromCrewPacket();
/* 37 */     msg.uuid = buffer.readUniqueId();
/* 38 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(CKickFromCrewPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 43 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
/*    */     {
/* 45 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             UUID uuid = message.uuid;
/*    */             
/*    */             ExtendedWorldData worldData = ExtendedWorldData.get(((PlayerEntity)serverPlayerEntity).world);
/*    */             Crew crew = worldData.getCrewWithCaptain(serverPlayerEntity.getUniqueID());
/*    */             PlayerEntity memberPlayer = ((PlayerEntity)serverPlayerEntity).world.getPlayerByUuid(uuid);
/*    */             if (crew != null && crew.hasMember(uuid)) {
/*    */               FactionHelper.sendMessageToCrew(((PlayerEntity)serverPlayerEntity).world, crew, (ITextComponent)new TranslationTextComponent(ModI18n.CREW_MESSAGE_KICKED, new Object[] { crew.getMember(uuid).getUsername() }));
/*    */               worldData.removeCrewMember(crew, uuid);
/*    */               if (memberPlayer != null) {
/*    */                 WyNetwork.sendTo(new SSyncWorldDataPacket(worldData), memberPlayer);
/*    */               }
/*    */               FactionHelper.sendUpdateMessageToCrew(((PlayerEntity)serverPlayerEntity).world, crew);
/*    */             } 
/*    */           });
/*    */     }
/* 63 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\client\crew\CKickFromCrewPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */