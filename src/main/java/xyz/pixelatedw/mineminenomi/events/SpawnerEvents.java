package xyz.pixelatedw.mineminenomi.events;

import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.world.spawners.AmbushSpawner;
import xyz.pixelatedw.mineminenomi.world.spawners.TraderSpawner;
import xyz.pixelatedw.mineminenomi.world.spawners.TrainerSpawner;



@EventBusSubscriber(modid = "mineminenomi")
public class SpawnerEvents
{
  private static final TraderSpawner TRADER_SPAWNER = new TraderSpawner();
  private static final TrainerSpawner TRAINER_SPAWNER = new TrainerSpawner();
  private static final AmbushSpawner AMBUSH_SPAWNER = new AmbushSpawner();

  
  @SubscribeEvent
  public static void onServerTick(TickEvent.WorldTickEvent event) {
    if (event.phase == TickEvent.Phase.END && event.world.dimension.getType() == DimensionType.OVERWORLD) {
      
      event.world.getProfiler().startSection("worldSpawners");
      
      if (CommonConfig.INSTANCE.canSpawnTraders())
        TRADER_SPAWNER.tick((ServerWorld)event.world); 
      if (CommonConfig.INSTANCE.canSpawnTrainers())
        TRAINER_SPAWNER.tick((ServerWorld)event.world); 
      if (CommonConfig.INSTANCE.canSpawnAmbushes()) {
        AMBUSH_SPAWNER.tick((ServerWorld)event.world);
      }
      event.world.getProfiler().endSection();
    } 
  }
}


