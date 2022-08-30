/*     */ package xyz.pixelatedw.mineminenomi.world.features.structures.smallbase.marine;
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
/*     */ public class MarineSmallBaseStructure
/*     */   extends OPStructure<NoFeatureConfig>
/*     */ {
/*     */   public MarineSmallBaseStructure() {
/*  35 */     super(NoFeatureConfig::deserialize);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStructureName() {
/*  41 */     return (new ResourceLocation("mineminenomi", "marine_small_base")).toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSeedModifier() {
/*  47 */     return 19773711;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSize() {
/*  53 */     return 6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFeatureDistance(ChunkGenerator<?> chunkGenerator) {
/*  60 */     return 12;
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
/*  72 */     boolean canSpawn = CommonConfig.INSTANCE.canSpawnSmallBases();
/*  73 */     boolean isChance = (MathHelper.clamp(WyHelper.randomWithRange(0, 100) + WyHelper.randomDouble(), 0.0D, 100.0D) < CommonConfig.INSTANCE.getChanceForSmallBasesSpawn());
/*     */     
/*  75 */     return (canSpawn && isChance && super.canBeGenerated(biomeManager, chunkGen, rand, chunkX, chunkZ, biome));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Structure.IStartFactory getStartFactory() {
/*  81 */     return Start::new;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void register(Biome biome) {
/*  86 */     if (!CommonConfig.INSTANCE.canSpawnSmallBases()) {
/*     */       return;
/*     */     }
/*  89 */     if (biome.getCategory() == Biome.Category.PLAINS || biome.getCategory() == Biome.Category.BEACH || biome.getCategory() == Biome.Category.FOREST || biome.getCategory() == Biome.Category.MESA || biome.getCategory() == Biome.Category.SAVANNA) {
/*     */       
/*  91 */       Structure structure = ModFeatures.MARINE_SMALL_BASE;
/*  92 */       biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, structure.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(new NoPlacementConfig())));
/*  93 */       biome.addStructure(structure.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class Start
/*     */     extends OPStructure.OPStructureStart
/*     */   {
/*     */     private TemplateManager templateManager;
/*     */     
/*     */     public Start(Structure<?> structure, int chunkX, int chunkZ, MutableBoundingBox bb, int i3, long seed) {
/* 103 */       super(structure, chunkX, chunkZ, bb, i3, seed);
/* 104 */       setBase(Blocks.DIRT);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void init(ChunkGenerator<?> generator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biome) {
/* 110 */       this.templateManager = templateManager;
/* 111 */       int i = getStructure().getSize();
/*     */       
/* 113 */       int k = (chunkX << 4) + 7;
/* 114 */       int l = (chunkZ << 4) + 7;
/* 115 */       int i1 = generator.getNoiseHeightMinusOne(k, l, Heightmap.Type.WORLD_SURFACE_WG);
/* 116 */       int j1 = generator.getNoiseHeightMinusOne(k, l + i, Heightmap.Type.WORLD_SURFACE_WG);
/* 117 */       int k1 = generator.getNoiseHeightMinusOne(k + i, l, Heightmap.Type.WORLD_SURFACE_WG);
/* 118 */       int l1 = generator.getNoiseHeightMinusOne(k + i, l + i, Heightmap.Type.WORLD_SURFACE_WG);
/* 119 */       int i2 = Math.min(Math.min(i1, j1), Math.min(k1, l1));
/* 120 */       if (i2 >= 60) {
/*     */         
/* 122 */         BlockPos blockpos = new BlockPos(chunkX * 16 + 8, i2, chunkZ * 16 + 8);
/* 123 */         MarineSmallBasePieces.addComponents(this.templateManager, blockpos, this.components);
/* 124 */         recalculateStructureSize();
/* 125 */         StructuresHelper.SPAWNED_STRUCTURES.add(blockpos);
/* 126 */         StructuresHelper.STRUCTURES_COUNT[5] = StructuresHelper.STRUCTURES_COUNT[5] + 1;
/*     */         
/* 128 */         WyDebug.debug("Marine Small Base spawned at: /tp " + blockpos.getX() + " ~ " + blockpos.getZ());
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\smallbase\marine\MarineSmallBaseStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */