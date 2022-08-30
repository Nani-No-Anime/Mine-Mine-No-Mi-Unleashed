/*     */ package xyz.pixelatedw.mineminenomi.api.helpers;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.tileentity.ChestTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.IWorld;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.CustomSpawnerTileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StructuresHelper
/*     */ {
/*  34 */   public static final List<BlockPos> SPAWNED_STRUCTURES = new ArrayList<>();
/*  35 */   public static final int[] STRUCTURES_COUNT = new int[15];
/*     */   
/*  37 */   private static final HashMap<StructureSpawnType, Map<StructureFaction, List<EntityType>>> SPAWN_TYPES = new HashMap<>();
/*     */ 
/*     */   
/*     */   static {
/*  41 */     SPAWN_TYPES.put(StructureSpawnType.GRUNT, 
/*  42 */         ImmutableMap.of(StructureFaction.MARINE, 
/*  43 */           Lists.newArrayList(new EntityType[] { ModEntities.MARINE_WITH_SWORD, ModEntities.MARINE_WITH_GUN } ), StructureFaction.PIRATE, 
/*  44 */           Lists.newArrayList(new EntityType[] { ModEntities.PIRATE_WITH_SWORD, ModEntities.PIRATE_WITH_GUN } ), StructureFaction.BANDIT, 
/*  45 */           Lists.newArrayList(new EntityType[] { ModEntities.BANDIT_WITH_SWORD } ), StructureFaction.REVOLUTIONARY, 
/*  46 */           Lists.newArrayList()));
/*     */ 
/*     */     
/*  49 */     SPAWN_TYPES.put(StructureSpawnType.CAPTAIN, 
/*  50 */         ImmutableMap.of(StructureFaction.MARINE, 
/*  51 */           Lists.newArrayList(new EntityType[] { ModEntities.MARINE_CAPTAIN } ), StructureFaction.PIRATE, 
/*  52 */           Lists.newArrayList(new EntityType[] { ModEntities.PIRATE_CAPTAIN } ), StructureFaction.BANDIT, 
/*  53 */           Lists.newArrayList(), StructureFaction.REVOLUTIONARY, 
/*  54 */           Lists.newArrayList()));
/*     */ 
/*     */     
/*  57 */     SPAWN_TYPES.put(StructureSpawnType.BRUTE, 
/*  58 */         ImmutableMap.of(StructureFaction.MARINE, 
/*  59 */           Lists.newArrayList(), StructureFaction.PIRATE, 
/*  60 */           Lists.newArrayList(new EntityType[] { ModEntities.PIRATE_BRUTE } ), StructureFaction.BANDIT, 
/*  61 */           Lists.newArrayList(new EntityType[] { ModEntities.BANDIT_BRUTE } ), StructureFaction.REVOLUTIONARY, 
/*  62 */           Lists.newArrayList()));
/*     */ 
/*     */     
/*  65 */     SPAWN_TYPES.put(StructureSpawnType.SNIPER, 
/*  66 */         ImmutableMap.of(StructureFaction.MARINE, 
/*  67 */           Lists.newArrayList(new EntityType[] { ModEntities.MARINE_SNIPER } ), StructureFaction.PIRATE, 
/*  68 */           Lists.newArrayList(), StructureFaction.BANDIT, 
/*  69 */           Lists.newArrayList(new EntityType[] { ModEntities.BANDIT_SNIPER } ), StructureFaction.REVOLUTIONARY, 
/*  70 */           Lists.newArrayList()));
/*     */ 
/*     */     
/*  73 */     SPAWN_TYPES.put(StructureSpawnType.BOMBER, 
/*  74 */         ImmutableMap.of(StructureFaction.MARINE, 
/*  75 */           Lists.newArrayList(new EntityType[] { ModEntities.MARINE_BOMBER } ), StructureFaction.PIRATE, 
/*  76 */           Lists.newArrayList(), StructureFaction.BANDIT, 
/*  77 */           Lists.newArrayList(new EntityType[] { ModEntities.BANDIT_BOMBER } ), StructureFaction.REVOLUTIONARY, 
/*  78 */           Lists.newArrayList()));
/*     */ 
/*     */     
/*  81 */     SPAWN_TYPES.put(StructureSpawnType.TRADER, 
/*  82 */         ImmutableMap.of(StructureFaction.MARINE, 
/*  83 */           Lists.newArrayList(new EntityType[] { ModEntities.MARINE_TRADER } ), StructureFaction.PIRATE, 
/*  84 */           Lists.newArrayList(new EntityType[] { ModEntities.PIRATE_TRADER } ), StructureFaction.SKYPIEAN, 
/*  85 */           Lists.newArrayList(new EntityType[] { ModEntities.SKYPIEAN_TRADER })));
/*     */ 
/*     */     
/*  88 */     SPAWN_TYPES.put(StructureSpawnType.CIVILIAN, 
/*  89 */         ImmutableMap.of(StructureFaction.SKYPIEAN, 
/*  90 */           Lists.newArrayList(new EntityType[] { ModEntities.SKYPIEAN_CIVILIAN })));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static void spawnMobs(IWorld world, BlockPos pos, StructureFaction faction, StructureSpawnType type, int min, int max) {
/*  97 */     spawnMobs(world, pos, faction, type, (int)WyHelper.randomWithRange(min, max));
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static void spawnMobs(IWorld world, BlockPos pos, StructureFaction faction, StructureSpawnType type, int amount) {
/* 103 */     if (world == null) {
/*     */       return;
/*     */     }
/* 106 */     List<EntityType> factionList = (List<EntityType>)((Map)SPAWN_TYPES.get(type)).get(faction);
/*     */     
/* 108 */     if (factionList == null || factionList.size() <= 0) {
/*     */       return;
/*     */     }
/* 111 */     EntityType spawnType = factionList.get((int)WyHelper.randomWithRange(0, factionList.size() - 1));
/*     */     
/* 113 */     if (spawnType == null) {
/*     */       return;
/*     */     }
/* 116 */     world.setBlockState(pos, ModBlocks.CUSTOM_SPAWNER.getDefaultState(), 3);
/* 117 */     if (world.getTileEntity(pos) instanceof CustomSpawnerTileEntity) {
/*     */       
/* 119 */       CustomSpawnerTileEntity spawner = (CustomSpawnerTileEntity)world.getTileEntity(pos);
/* 120 */       spawner.setSpawnerLimit(amount);
/* 121 */       spawner.setSpawnerMob(spawnType);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static void spawnLoot(IWorld world, BlockPos pos, ResourceLocation lootTable) {
/* 128 */     world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
/* 129 */     TileEntity tile = world.getTileEntity(pos.down());
/* 130 */     if (tile instanceof ChestTileEntity)
/* 131 */       ((ChestTileEntity)tile).setLootTable(lootTable, world.getRandom().nextLong()); 
/*     */   }
/*     */   
/*     */   public enum StructureFaction
/*     */   {
/* 136 */     MARINE, PIRATE, BANDIT, REVOLUTIONARY, SKYPIEAN;
/*     */   }
/*     */   
/*     */   public enum StructureSpawnType
/*     */   {
/* 141 */     GRUNT, CAPTAIN, SNIPER, BRUTE, BOMBER, CIVILIAN, TRADER;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\helpers\StructuresHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */