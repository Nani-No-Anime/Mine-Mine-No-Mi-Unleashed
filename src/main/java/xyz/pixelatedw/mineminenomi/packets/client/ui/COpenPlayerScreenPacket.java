/*    */ package xyz.pixelatedw.mineminenomi.packets.client.ui;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenCharacterCreatorScreenPacket;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenPlayerScreenPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class COpenPlayerScreenPacket
/*    */ {
/*    */   public void encode(PacketBuffer buffer) {}
/*    */   
/*    */   public static COpenPlayerScreenPacket decode(PacketBuffer buffer) {
/* 31 */     COpenPlayerScreenPacket msg = new COpenPlayerScreenPacket();
/*    */     
/* 33 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(COpenPlayerScreenPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 38 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
/*    */     {
/* 40 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             IEntityStats entityProps = EntityStatsCapability.get((LivingEntity)serverPlayerEntity);
/*    */             
/*    */             if (!entityProps.hasRace() || !entityProps.hasFaction() || !entityProps.hasFightingStyle()) {
/*    */               boolean hasRandomizedRace = CommonConfig.INSTANCE.getRaceRandomizer();
/*    */               
/*    */               WyNetwork.sendTo(new SOpenCharacterCreatorScreenPacket(hasRandomizedRace), (PlayerEntity)serverPlayerEntity);
/*    */             } else {
/*    */               IAbilityData props = AbilityDataCapability.get((LivingEntity)serverPlayerEntity);
/*    */               
/*    */               AbilityHelper.validateDevilFruitMoves((PlayerEntity)serverPlayerEntity);
/*    */               
/*    */               AbilityHelper.validateRacialMoves((PlayerEntity)serverPlayerEntity);
/*    */               
/*    */               AbilityHelper.validateStyleMoves((PlayerEntity)serverPlayerEntity);
/*    */               
/*    */               AbilityHelper.validateFactionMoves((PlayerEntity)serverPlayerEntity);
/*    */               
/*    */               WyNetwork.sendToAllTrackingAndSelf(new SSyncAbilityDataPacket(serverPlayerEntity.getEntityId(), props), (LivingEntity)serverPlayerEntity);
/*    */               
/*    */               boolean hasQuests = CommonConfig.INSTANCE.isQuestsEnabled();
/*    */               boolean hasChallenges = CommonConfig.INSTANCE.isChallengesEnabled();
/*    */               WyNetwork.sendTo(new SOpenPlayerScreenPacket(hasQuests, hasChallenges), (PlayerEntity)serverPlayerEntity);
/*    */             } 
/*    */           });
/*    */     }
/* 68 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\clien\\ui\COpenPlayerScreenPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */