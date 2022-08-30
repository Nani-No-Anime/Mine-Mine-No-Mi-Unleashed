/*     */ package xyz.pixelatedw.mineminenomi.world.features.structures.skyisland.house;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.MutableBoundingBox;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraft.world.biome.BiomeManager;
/*     */ import net.minecraft.world.gen.ChunkGenerator;
/*     */ import net.minecraft.world.gen.GenerationStage;
/*     */ import net.minecraft.world.gen.feature.IFeatureConfig;
/*     */ import net.minecraft.world.gen.feature.NoFeatureConfig;
/*     */ import net.minecraft.world.gen.feature.structure.Structure;
/*     */ import net.minecraft.world.gen.feature.structure.StructureStart;
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
/*     */ public class SkyIslandHouseStructure
/*     */   extends OPStructure<NoFeatureConfig>
/*     */ {
/*     */   public SkyIslandHouseStructure() {
/*  34 */     super(NoFeatureConfig::deserialize);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStructureName() {
/*  40 */     return (new ResourceLocation("mineminenomi", "sky_island_house")).toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSeedModifier() {
/*  46 */     return 59223722;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSize() {
/*  52 */     return 3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFeatureDistance(ChunkGenerator<?> chunkGenerator) {
/*  59 */     return 12;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFeatureSeparation(ChunkGenerator<?> chunkGenerator) {
/*  65 */     return 8;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeGenerated(BiomeManager biomeManager, ChunkGenerator<?> chunkGen, Random rand, int chunkX, int chunkZ, Biome biome) {
/*  71 */     boolean canSpawn = CommonConfig.INSTANCE.canSpawnSkyIslands();
/*  72 */     boolean isChance = (MathHelper.clamp(WyHelper.randomWithRange(0, 200) + WyHelper.randomDouble(), 0.0D, 200.0D) < CommonConfig.INSTANCE.getChanceForSkyIslandSpawn());
/*     */     
/*  74 */     return (canSpawn && isChance && super.canBeGenerated(biomeManager, chunkGen, rand, chunkX, chunkZ, biome));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Structure.IStartFactory getStartFactory() {
/*  80 */     return Start::new;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void register(Biome biome) {
/*  85 */     if (!CommonConfig.INSTANCE.canSpawnSkyIslands()) {
/*     */       return;
/*     */     }
/*  88 */     if (biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND) {
/*     */       
/*  90 */       Structure structure = ModFeatures.SKY_ISLAND_HOUSE;
/*  91 */       biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, structure.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(new NoPlacementConfig())));
/*  92 */       biome.addStructure(structure.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class Start
/*     */     extends StructureStart
/*     */   {
/*     */     private TemplateManager templateManager;
/*     */     
/*     */     public Start(Structure<?> structure, int chunkX, int chunkZ, MutableBoundingBox bb, int i3, long seed) {
/* 102 */       super(structure, chunkX, chunkZ, bb, i3, seed);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void init(ChunkGenerator<?> generator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biome) {
/* 108 */       this.templateManager = templateManager;
/* 109 */       int i = chunkX * 16;
/* 110 */       int j = chunkZ * 16;
/* 111 */       BlockPos blockpos = new BlockPos(i, WyHelper.randomWithRange(180, 220), j);
/* 112 */       SkyIslandHousePieces.addComponents(this.templateManager, blockpos, this.components);
/* 113 */       recalculateStructureSize();
/* 114 */       StructuresHelper.SPAWNED_STRUCTURES.add(blockpos);
/* 115 */       StructuresHelper.STRUCTURES_COUNT[11] = StructuresHelper.STRUCTURES_COUNT[11] + 1;
/*     */       
/* 117 */       WyDebug.debug("Sky Island House spawned at: /tp " + blockpos.getX() + " ~ " + blockpos.getZ());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\skyisland\house\SkyIslandHouseStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */