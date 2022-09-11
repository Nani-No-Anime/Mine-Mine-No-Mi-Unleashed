package xyz.pixelatedw.mineminenomi.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.challenges.ArenaData;
import xyz.pixelatedw.mineminenomi.challenges.ChallengeFailMessageThread;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModArenas;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.packets.server.ui.SCloseScreenPacket;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;



@EventBusSubscriber(modid = "mineminenomi")
public class ChallengesEvents
{
  @SubscribeEvent
  public static void onEntityJoinsWorld(PlayerEvent.PlayerLoggedInEvent event) {
    PlayerEntity player = event.getPlayer();
    if (!player.world.isRemote && player.world.getDimension().getType() == DimensionType.byName(ModResources.DIMENSION_TYPE_CHALLENGES)) {
      
      ServerWorld overworld = player.getServer().getWorld(DimensionType.OVERWORLD);
      ((ServerPlayerEntity)player).teleport(overworld, overworld.getSpawnPoint().getX(), overworld.getSpawnPoint().getY(), overworld.getSpawnPoint().getZ(), 270.0F, 0.0F);
      (new ChallengeFailMessageThread((ServerPlayerEntity)player)).start();
    } 
  }

  
  @SubscribeEvent
  public static void onWorldLoaded(EntityJoinWorldEvent event) {
    if (!(event.getEntity() instanceof PlayerEntity) || event.getWorld().getDimension().getType() != DimensionType.OVERWORLD) {
      return;
    }
    if (!CommonConfig.INSTANCE.isChallengesEnabled()) {
      return;
    }
    World world = event.getWorld();
    
    if (world.isRemote) {
      return;
    }
    PlayerEntity player = (PlayerEntity)event.getEntity();
    ServerWorld nextWorld = world.getServer().getWorld(DimensionType.byName(ModResources.DIMENSION_TYPE_CHALLENGES));
    
    for (ArenaData arena : ModArenas.ARENAS) {
      arena.buildIfNeeded(player, (World)nextWorld);
    }
    WyNetwork.sendTo(new SCloseScreenPacket(), player);
  }
}


