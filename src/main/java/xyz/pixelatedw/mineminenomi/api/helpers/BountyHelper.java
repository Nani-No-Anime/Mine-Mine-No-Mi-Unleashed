package xyz.pixelatedw.mineminenomi.api.helpers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncWorldDataPacket;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;


public class BountyHelper
{
  public static boolean canGainBounty(PlayerEntity player) {
    IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
    
    if (props.isPirate() || props.isRevolutionary() || props.isBandit()) {
      return true;
    }
    return false;
  }

  
  public static boolean issueBountyForPlayer(PlayerEntity player) {
    ExtendedWorldData worldData = ExtendedWorldData.get(player.world);
    IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
    
    if (canGainBounty(player) && props.getBounty() > 1000L) {
      
      worldData.issueBounty(player.getUniqueID().toString(), props.getBounty());
      WyNetwork.sendToAllTrackingAndSelf(new SSyncWorldDataPacket(worldData), (LivingEntity)player);
      return true;
    } 
    
    return false;
  }

  
  public static void issueBountyForAllPlayers(World world) {
    if (!(world instanceof net.minecraft.world.server.ServerWorld)) {
      return;
    }
    for (PlayerEntity player : world.getPlayers())
    {
      issueBountyForPlayer(player);
    }
  }
}


