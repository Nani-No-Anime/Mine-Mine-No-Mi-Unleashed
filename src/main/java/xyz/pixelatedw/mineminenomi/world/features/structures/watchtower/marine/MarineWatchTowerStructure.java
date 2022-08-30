/*     */ package xyz.pixelatedw.mineminenomi.world.features.structures.watchtower.marine;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.MutableBoundingBox;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraft.world.biome.BiomeManager;
/*     */ import net.minecraft.world.gen.ChunkGenerator;
/*     */ import net.minecraft.world.gen.GenerationStage;
/*     */ import net.minecraft.world.gen.Heightmap;
/*     */ import net.minecraft.world.gen.feature.IFeatureConfig;
/*     */ import net.minecraft.world.gen.feature.NoFeatureConfig;
/*     */ import net.minecraft.world.gen.feature.structure.Structure;
/*     */ import net.minecraft.world.gen.feature.template.TemplateManager;
/*     */ import net.minecraft.world.gen.placement.IPlacementConfig;
/*     */ import net.minecraft.world.gen.placement.NoPlacementConfig;
/*     */ import net.minecraft.world.gen.placement.Placement;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModFeatures;
/*     */ import xyz.pixelatedw.mineminenomi.world.features.structures.OPStructure;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MarineWatchTowerStructure
/*     */   extends OPStructure<NoFeatureConfig>
/*     */ {
/*     */   public MarineWatchTowerStructure() {
/*  35 */     super(NoFeatureConfig::deserialize);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStructureName() {
/*  41 */     return (new ResourceLocation("mineminenomi", "marine_watch_tower")).toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSeedModifier() {
/*  47 */     return 31293151;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSize() {
/*  53 */     return 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFeatureDistance(ChunkGenerator<?> chunkGenerator) {
/*  60 */     return 10;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFeatureSeparation(ChunkGenerator<?> chunkGenerator) {
/*  66 */     return 8;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeGenerated(BiomeManager biomeManager, ChunkGenerator<?> chunkGen, Random rand, int chunkX, int chunkZ, Biome biome) {
/*  72 */     boolean canSpawn = CommonConfig.INSTANCE.canSpawnWatchTowers();
/*  73 */     boolean isFlat = WyHelper.isSurfaceFlat(chunkGen, chunkX, chunkZ, 2);
/*  74 */     boolean isChance = (MathHelper.clamp(WyHelper.randomWithRange(0, 100) + WyHelper.randomDouble(), 0.0D, 100.0D) < CommonConfig.INSTANCE.getChanceForWatchTowersSpawn());
/*     */     
/*  76 */     return (canSpawn && isFlat && isChance && super.canBeGenerated(biomeManager, chunkGen, rand, chunkX, chunkZ, biome));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Structure.IStartFactory getStartFactory() {
/*  82 */     return Start::new;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void register(Biome biome) {
/*  87 */     if (!CommonConfig.INSTANCE.canSpawnWatchTowers()) {
/*     */       return;
/*     */     }
/*  90 */     if (biome.getCategory() == Biome.Category.PLAINS || biome.getCategory() == Biome.Category.DESERT || biome.getCategory() == Biome.Category.BEACH || biome.getCategory() == Biome.Category.FOREST || biome.getCategory() == Biome.Category.MESA) {
/*     */       
/*  92 */       Structure structure = ModFeatures.MARINE_WATCH_TOWER;
/*  93 */       biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, structure.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(new NoPlacementConfig())));
/*  94 */       biome.addStructure(structure.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class Start
/*     */     extends OPStructure.OPStructureStart
/*     */   {
/*     */     private TemplateManager templateManager;
/*     */     
/*     */     public Start(Structure<?> structure, int chunkX, int chunkZ, MutableBoundingBox bb, int i3, long seed) {
/* 104 */       super(structure, chunkX, chunkZ, bb, i3, seed);
/* 105 */       setBase(Blocks.DIRT);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void init(ChunkGenerator<?> generator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biome) {
/* 111 */       this.templateManager = templateManager;
/* 112 */       int i = getStructure().getSize();
/*     */       
/* 114 */       int k = (chunkX << 4) + 7;
/* 115 */       int l = (chunkZ << 4) + 7;
/* 116 */       int i1 = generator.getNoiseHeightMinusOne(k, l, Heightmap.Type.WORLD_SURFACE_WG);
/* 117 */       int j1 = generator.getNoiseHeightMinusOne(k, l + i, Heightmap.Type.WORLD_SURFACE_WG);
/* 118 */       int k1 = generator.getNoiseHeightMinusOne(k + i, l, Heightmap.Type.WORLD_SURFACE_WG);
/* 119 */       int l1 = generator.getNoiseHeightMinusOne(k + i, l + i, Heightmap.Type.WORLD_SURFACE_WG);
/* 120 */       int i2 = Math.min(Math.min(i1, j1), Math.min(k1, l1));
/* 121 */       if (i2 >= 60) {
/*     */         
/* 123 */         BlockPos blockpos = new BlockPos(chunkX * 16 + 8, i2, chunkZ * 16 + 8);
/* 124 */         MarineWatchTowerPieces.addComponents(this.templateManager, blockpos.add(0, -1, 0), this.components);
/* 125 */         recalculateStructureSize();
/* 126 */         StructuresHelper.SPAWNED_STRUCTURES.add(blockpos);
/* 127 */         StructuresHelper.STRUCTURES_COUNT[10] = StructuresHelper.STRUCTURES_COUNT[10] + 1;
/*     */         
/* 129 */         WyDebug.debug("Marine Watch Tower spawned at: /tp " + blockpos.getX() + " ~ " + blockpos.getZ());
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\watchtower\marine\MarineWatchTowerStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */