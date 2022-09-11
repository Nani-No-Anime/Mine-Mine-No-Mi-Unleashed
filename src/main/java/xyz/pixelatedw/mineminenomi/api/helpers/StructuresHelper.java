package xyz.pixelatedw.mineminenomi.api.helpers;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.CustomSpawnerTileEntity;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;











public class StructuresHelper
{
  public static final List<BlockPos> SPAWNED_STRUCTURES = new ArrayList<>();
  public static final int[] STRUCTURES_COUNT = new int[15];
  
  private static final HashMap<StructureSpawnType, Map<StructureFaction, List<EntityType>>> SPAWN_TYPES = new HashMap<>();

  
  static {
    SPAWN_TYPES.put(StructureSpawnType.GRUNT, 
        ImmutableMap.of(StructureFaction.MARINE, 
          Lists.newArrayList(new EntityType[] { ModEntities.MARINE_WITH_SWORD, ModEntities.MARINE_WITH_GUN } ), StructureFaction.PIRATE, 
          Lists.newArrayList(new EntityType[] { ModEntities.PIRATE_WITH_SWORD, ModEntities.PIRATE_WITH_GUN } ), StructureFaction.BANDIT, 
          Lists.newArrayList(new EntityType[] { ModEntities.BANDIT_WITH_SWORD } ), StructureFaction.REVOLUTIONARY, 
          Lists.newArrayList()));

    
    SPAWN_TYPES.put(StructureSpawnType.CAPTAIN, 
        ImmutableMap.of(StructureFaction.MARINE, 
          Lists.newArrayList(new EntityType[] { ModEntities.MARINE_CAPTAIN } ), StructureFaction.PIRATE, 
          Lists.newArrayList(new EntityType[] { ModEntities.PIRATE_CAPTAIN } ), StructureFaction.BANDIT, 
          Lists.newArrayList(), StructureFaction.REVOLUTIONARY, 
          Lists.newArrayList()));

    
    SPAWN_TYPES.put(StructureSpawnType.BRUTE, 
        ImmutableMap.of(StructureFaction.MARINE, 
          Lists.newArrayList(), StructureFaction.PIRATE, 
          Lists.newArrayList(new EntityType[] { ModEntities.PIRATE_BRUTE } ), StructureFaction.BANDIT, 
          Lists.newArrayList(new EntityType[] { ModEntities.BANDIT_BRUTE } ), StructureFaction.REVOLUTIONARY, 
          Lists.newArrayList()));

    
    SPAWN_TYPES.put(StructureSpawnType.SNIPER, 
        ImmutableMap.of(StructureFaction.MARINE, 
          Lists.newArrayList(new EntityType[] { ModEntities.MARINE_SNIPER } ), StructureFaction.PIRATE, 
          Lists.newArrayList(), StructureFaction.BANDIT, 
          Lists.newArrayList(new EntityType[] { ModEntities.BANDIT_SNIPER } ), StructureFaction.REVOLUTIONARY, 
          Lists.newArrayList()));

    
    SPAWN_TYPES.put(StructureSpawnType.BOMBER, 
        ImmutableMap.of(StructureFaction.MARINE, 
          Lists.newArrayList(new EntityType[] { ModEntities.MARINE_BOMBER } ), StructureFaction.PIRATE, 
          Lists.newArrayList(), StructureFaction.BANDIT, 
          Lists.newArrayList(new EntityType[] { ModEntities.BANDIT_BOMBER } ), StructureFaction.REVOLUTIONARY, 
          Lists.newArrayList()));

    
    SPAWN_TYPES.put(StructureSpawnType.TRADER, 
        ImmutableMap.of(StructureFaction.MARINE, 
          Lists.newArrayList(new EntityType[] { ModEntities.MARINE_TRADER } ), StructureFaction.PIRATE, 
          Lists.newArrayList(new EntityType[] { ModEntities.PIRATE_TRADER } ), StructureFaction.SKYPIEAN, 
          Lists.newArrayList(new EntityType[] { ModEntities.SKYPIEAN_TRADER })));

    
    SPAWN_TYPES.put(StructureSpawnType.CIVILIAN, 
        ImmutableMap.of(StructureFaction.SKYPIEAN, 
          Lists.newArrayList(new EntityType[] { ModEntities.SKYPIEAN_CIVILIAN })));
  }


  
  @Nullable
  public static void spawnMobs(IWorld world, BlockPos pos, StructureFaction faction, StructureSpawnType type, int min, int max) {
    spawnMobs(world, pos, faction, type, (int)WyHelper.randomWithRange(min, max));
  }

  
  @Nullable
  public static void spawnMobs(IWorld world, BlockPos pos, StructureFaction faction, StructureSpawnType type, int amount) {
    if (world == null) {
      return;
    }
    List<EntityType> factionList = (List<EntityType>)((Map)SPAWN_TYPES.get(type)).get(faction);
    
    if (factionList == null || factionList.size() <= 0) {
      return;
    }
    EntityType spawnType = factionList.get((int)WyHelper.randomWithRange(0, factionList.size() - 1));
    
    if (spawnType == null) {
      return;
    }
    world.setBlockState(pos, ModBlocks.CUSTOM_SPAWNER.getDefaultState(), 3);
    if (world.getTileEntity(pos) instanceof CustomSpawnerTileEntity) {
      
      CustomSpawnerTileEntity spawner = (CustomSpawnerTileEntity)world.getTileEntity(pos);
      spawner.setSpawnerLimit(amount);
      spawner.setSpawnerMob(spawnType);
    } 
  }

  
  @Nullable
  public static void spawnLoot(IWorld world, BlockPos pos, ResourceLocation lootTable) {
    world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
    TileEntity tile = world.getTileEntity(pos.down());
    if (tile instanceof ChestTileEntity)
      ((ChestTileEntity)tile).setLootTable(lootTable, world.getRandom().nextLong()); 
  }
  
  public enum StructureFaction
  {
    MARINE, PIRATE, BANDIT, REVOLUTIONARY, SKYPIEAN;
  }
  
  public enum StructureSpawnType
  {
    GRUNT, CAPTAIN, SNIPER, BRUTE, BOMBER, CIVILIAN, TRADER;
  }
}


