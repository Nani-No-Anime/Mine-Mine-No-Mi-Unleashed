/*    */ package xyz.pixelatedw.mineminenomi.packets.client.crew;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*    */ import xyz.pixelatedw.mineminenomi.api.events.CrewEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncWorldDataPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CLeaveCrewPacket
/*    */ {
/*    */   public void encode(PacketBuffer buffer) {}
/*    */   
/*    */   public static CLeaveCrewPacket decode(PacketBuffer buffer) {
/* 30 */     CLeaveCrewPacket msg = new CLeaveCrewPacket();
/* 31 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(CLeaveCrewPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 36 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
/*    */     {
/* 38 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             UUID uuid = serverPlayerEntity.getUniqueID();
/*    */             
/*    */             ExtendedWorldData worldData = ExtendedWorldData.get(((PlayerEntity)serverPlayerEntity).world);
/*    */             Crew crew = worldData.getCrewWithMember(uuid);
/*    */             if (crew != null) {
/*    */               CrewEvent.Leave event = new CrewEvent.Leave((PlayerEntity)serverPlayerEntity, crew);
/*    */               if (!MinecraftForge.EVENT_BUS.post(event)) {
/*    */                 boolean captainChange = false;
/*    */                 FactionHelper.sendMessageToCrew(((PlayerEntity)serverPlayerEntity).world, crew, (ITextComponent)new TranslationTextComponent(ModI18n.CREW_MESSAGE_LEFT, new Object[] { serverPlayerEntity.getName().getFormattedText() }));
/*    */                 if (crew.getCaptain().getUUID().equals(uuid)) {
/*    */                   captainChange = true;
/*    */                 }
/*    */                 worldData.removeCrewMember(crew, uuid);
/*    */                 if (captainChange && crew.getCaptain() != null) {
/*    */                   FactionHelper.sendMessageToCrew(((PlayerEntity)serverPlayerEntity).world, crew, (ITextComponent)new TranslationTextComponent(ModI18n.CREW_MESSAGE_NEW_CAPTAIN, new Object[] { crew.getCaptain().getUsername() }));
/*    */                 }
/*    */                 if (crew.getMembers().size() <= 0) {
/*    */                   worldData.removeCrew(crew);
/*    */                 }
/*    */                 WyNetwork.sendTo(new SSyncWorldDataPacket(worldData), (PlayerEntity)serverPlayerEntity);
/*    */                 FactionHelper.sendUpdateMessageToCrew(((PlayerEntity)serverPlayerEntity).world, crew);
/*    */               } 
/*    */             } 
/*    */           });
/*    */     }
/* 66 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\client\crew\CLeaveCrewPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */