package xyz.pixelatedw.mineminenomi.world.spawners;

import java.util.List;
import java.util.Random;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.entities.TraderEntity;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;

public class TraderSpawner {
  private Random random = new Random();
  private int cooldown;
  private static final EntityType[] TRADER_TYPES = new EntityType[] { ModEntities.MARINE_TRADER, ModEntities.PIRATE_TRADER };

  
  public void tick(ServerWorld world) {
    world.getProfiler().startSection("traderSpawnerTick");
    if (--this.cooldown <= 0) {
      
      this.cooldown = CommonConfig.INSTANCE.getTimeBetweenTraderSpawns();
      if (this.random.nextInt(100) <= CommonConfig.INSTANCE.getChanceForTraderSpawn())
      {
        spawn(world);
      }
    } 
    world.getProfiler().endSection();
  }

  
  private void spawn(ServerWorld world) {
    world.getProfiler().startSection("traderSpawnerSpawn");
    ServerPlayerEntity serverPlayerEntity = world.getRandomPlayer();
    if (serverPlayerEntity == null) {
      return;
    }
    
    int r = this.random.nextInt(TRADER_TYPES.length);
    EntityType entityType = TRADER_TYPES[r];
    BlockPos targetPos = serverPlayerEntity.getPosition();
    BlockPos spawnPos = WyHelper.findOnGroundSpawnLocation((World)world, entityType, targetPos, 20);
    List<TraderEntity> traders = WyHelper.getEntitiesNear(targetPos, (World)world, 40.0D, new Class[] { TraderEntity.class });
    if (spawnPos == null)
      return; 
    boolean canSpawnInBiome = (world.getBiome(targetPos).getCategory() != Biome.Category.OCEAN);
    
    if (traders.size() < 3 && canSpawnInBiome) {
      
      entityType.spawn((World)world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, spawnPos, SpawnReason.EVENT, false, false);
      WyDebug.debug("Trader spawned at: " + spawnPos);
    } 
    
    world.getProfiler().endSection();
  }
}


