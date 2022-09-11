package xyz.pixelatedw.mineminenomi.world.spawners;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModQuests;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;

public class TrainerSpawner
{
  private Random random = new Random();
  
  private int cooldown;
  
  public void tick(ServerWorld world) {
    world.getProfiler().startSection("traderSpawnerTick");
    if (--this.cooldown <= 0) {
      
      this.cooldown = CommonConfig.INSTANCE.getTimeBetweenTrainerSpawns();
      if (this.random.nextInt(100) <= CommonConfig.INSTANCE.getChanceForTrainerSpawn())
      {
        spawn(world);
      }
    } 
    world.getProfiler().endSection();
  }

  
  private void spawn(ServerWorld world) {
    world.getProfiler().startSection("trainerSpawnerSpawn");
    ServerPlayerEntity serverPlayerEntity = world.getRandomPlayer();
    if (serverPlayerEntity == null) {
      return;
    }
    
    IEntityStats props = EntityStatsCapability.get((LivingEntity)serverPlayerEntity);
    EntityType entityType = null;
    Quest[] quests = null;
    
    if (props.isSwordsman()) {
      
      entityType = ModEntities.SWORDSMAN_TRAINER;
      quests = ModQuests.SWORDSMAN_TRIALS;
    }
    else if (props.isSniper()) {
      
      entityType = ModEntities.SNIPER_TRAINER;
      quests = ModQuests.SNIPER_TRIALS;
    }
    else if (props.isWeatherWizard()) {
      
      entityType = ModEntities.ART_OF_WEATHER_TRAINER;
      quests = ModQuests.ART_OF_WEATHER_TRIALS;
    }
    else if (props.isDoctor()) {
      
      entityType = ModEntities.DOCTOR_TRAINER;
      quests = ModQuests.DOCTOR_TRIALS;
    }
    else if (props.isBrawler()) {
      
      entityType = ModEntities.BRAWLER_TRAINER;
      quests = ModQuests.BRAWLER_TRIALS;
    }
    else if (props.isBlackLeg()) {
      
      entityType = ModEntities.BLACK_LEG_TRAINER;
      quests = ModQuests.BLACK_LEG_TRIALS;
    } 
    
    if (entityType == null) {
      return;
    }
    BlockPos targetPos = serverPlayerEntity.getPosition();
    BlockPos spawnPos = WyHelper.findOnGroundSpawnLocation(world, entityType, targetPos, 20);
    if (spawnPos == null)
      return; 
    List<Entity> trainers = WyHelper.getEntitiesNear(spawnPos, world, 100.0D).stream().filter(entity -> entity instanceof xyz.pixelatedw.mineminenomi.api.entities.TrainerEntity).collect(Collectors.toList());
    boolean hasAvailableQuests = true;
    
    if (quests != null)
    {
      for (Quest trial : quests) {
        
        if (!QuestDataCapability.get((PlayerEntity)serverPlayerEntity).hasFinishedQuest(trial)) {
          break;
        }
        hasAvailableQuests = false;
      } 
    }
    
    if (spawnPos != null && hasAvailableQuests) {
      
      boolean canSpawnInBiome = (world.getBiome(targetPos).getCategory() != Biome.Category.OCEAN);
      
      if (trainers.size() < 2 && canSpawnInBiome) {
        
        entityType.spawn((World)world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, spawnPos, SpawnReason.EVENT, false, false);
        WyDebug.debug("Trainer spawned at: " + spawnPos);
      } 
    } 
    
    world.getProfiler().endSection();
  }
}


