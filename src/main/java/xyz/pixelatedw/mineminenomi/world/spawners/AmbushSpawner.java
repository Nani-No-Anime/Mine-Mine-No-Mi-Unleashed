package xyz.pixelatedw.mineminenomi.world.spawners;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;

public class AmbushSpawner {
  private Random random = new Random();
  
  private int cooldown;
  
  public void tick(ServerWorld world) {
    world.getProfiler().startSection("ambushSpawnerTick");
    if (--this.cooldown <= 0) {
      
      this.cooldown = CommonConfig.INSTANCE.getTimeBetweenAmbushSpawns();
      if (this.random.nextInt(100) <= CommonConfig.INSTANCE.getChanceForAmbushSpawn())
        spawn(world); 
    } 
    world.getProfiler().endSection();
  }

  
  public void spawn(ServerWorld world) {
    world.getProfiler().startSection("ambushSpawnerSpawn");
    ServerPlayerEntity serverPlayerEntity = world.getRandomPlayer();
    if (serverPlayerEntity == null) {
      return;
    }
    
    IEntityStats props = EntityStatsCapability.get((LivingEntity)serverPlayerEntity);
    
    if (props.isPirate() || props.isBandit() || props.isRevolutionary()) {
      
      BlockPos targetPos = serverPlayerEntity.getPosition();
      long bounty = props.getBounty();
      boolean canSpawnInBiome = (world.getBiome(targetPos).getCategory() != Biome.Category.OCEAN);
      boolean canSeeSky = ((PlayerEntity)serverPlayerEntity).world.canBlockSeeSky(targetPos);
      
      if (!canSpawnInBiome || bounty < 10000L || !canSeeSky) {
        return;
      }
      EntityType captainEntity = ModEntities.MARINE_CAPTAIN;
      EntityType grunt1Entity = ModEntities.MARINE_WITH_SWORD;
      EntityType grunt2Entity = ModEntities.MARINE_WITH_GUN;
      
      int r = this.random.nextInt(2);
      int r2 = this.random.nextInt(2);
      if (r == 1);






      
      List<OPEntity> dangers = WyHelper.getEntitiesNear(targetPos, (World)world, 80.0D, new Class[] { OPEntity.class });
      if (dangers.size() > 50) {
        return;
      }
      int nrCaptains = 1 + (int)Math.ceil((bounty / 200000L));
      int nrGrunts = 3 + (int)Math.ceil((bounty / 100000L));
      
      String name = captainEntity.getName().getFormattedText();
      
      if (nrCaptains > 3) {
        nrCaptains = 3;
      }
      if (nrGrunts > 30) {
        nrGrunts = 30;
      }
      int i;
      for (i = 0; i < nrCaptains; i++) {
        
        BlockPos spawnPos = WyHelper.findOnGroundSpawnLocation((World)world, captainEntity, targetPos, 10);
        if (spawnPos != null) {
          captainEntity.spawn((World)world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, spawnPos, SpawnReason.EVENT, false, false);
        }
      } 
      
      for (i = 0; i < nrGrunts; i++) {
        
        EntityType gruntEntity = (i % 2 == 0) ? grunt1Entity : grunt2Entity;
        
        BlockPos spawnPos = WyHelper.findOnGroundSpawnLocation((World)world, gruntEntity, targetPos, 20);
        if (spawnPos != null) {
          gruntEntity.spawn((World)world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, spawnPos, SpawnReason.EVENT, false, false);
        }
      } 
      
      StringTextComponent stringTextComponent = new StringTextComponent("<" + name + "> We've come to arrest you, surrender now criminal scum!");
      if (r2 == 1) {
        stringTextComponent = new StringTextComponent("<" + name + "> You're surrounded and have no escape, surrender now!");
      }
      serverPlayerEntity.sendMessage((ITextComponent)stringTextComponent);
      WyDebug.debug("Ambush spawned around these coords: " + targetPos);
    } 
    
    world.getProfiler().endSection();
  }
}


