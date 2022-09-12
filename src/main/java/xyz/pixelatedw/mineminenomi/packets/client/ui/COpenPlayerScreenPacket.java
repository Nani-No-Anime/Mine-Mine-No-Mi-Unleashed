package xyz.pixelatedw.mineminenomi.packets.client.ui;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenCharacterCreatorScreenPacket;
import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenPlayerScreenPacket;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;

import java.util.function.Supplier;






public class COpenPlayerScreenPacket
{
  public void encode(PacketBuffer buffer) {}
  
  public static COpenPlayerScreenPacket decode(PacketBuffer buffer) {
    COpenPlayerScreenPacket msg = new COpenPlayerScreenPacket();
    
    return msg;
  }

  
  public static void handle(COpenPlayerScreenPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
            ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
            
            IEntityStats entityProps = EntityStatsCapability.get((LivingEntity)serverPlayerEntity);
            
            if (!entityProps.hasRace() || !entityProps.hasFaction() || !entityProps.hasFightingStyle()) {
              boolean hasRandomizedRace = CommonConfig.INSTANCE.getRaceRandomizer();
              
              WyNetwork.sendTo(new SOpenCharacterCreatorScreenPacket(hasRandomizedRace), (PlayerEntity)serverPlayerEntity);
            } else {
              IAbilityData props = AbilityDataCapability.get((LivingEntity)serverPlayerEntity);
              
              AbilityHelper.validateDevilFruitMoves((PlayerEntity)serverPlayerEntity);
              
              AbilityHelper.validateRacialMoves((PlayerEntity)serverPlayerEntity);
              
              AbilityHelper.validateStyleMoves((PlayerEntity)serverPlayerEntity);
              
              AbilityHelper.validateFactionMoves((PlayerEntity)serverPlayerEntity);
              
              WyNetwork.sendToAllTrackingAndSelf(new SSyncAbilityDataPacket(serverPlayerEntity.getEntityId(), props), (LivingEntity)serverPlayerEntity);
              
              boolean hasQuests = CommonConfig.INSTANCE.isQuestsEnabled();
              boolean hasChallenges = CommonConfig.INSTANCE.isChallengesEnabled();
              WyNetwork.sendTo(new SOpenPlayerScreenPacket(hasQuests, hasChallenges), (PlayerEntity)serverPlayerEntity);
            } 
          });
    }
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }
}


