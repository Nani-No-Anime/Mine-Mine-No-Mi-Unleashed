/*     */ package xyz.pixelatedw.mineminenomi.world.features.structures.ghostship;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Rotation;
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
/*     */ public class GhostShipStructure
/*     */   extends OPStructure<NoFeatureConfig>
/*     */ {
/*     */   public GhostShipStructure() {
/*  35 */     super(NoFeatureConfig::deserialize);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStructureName() {
/*  41 */     return (new ResourceLocation("mineminenomi", "ghost_ship")).toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSeedModifier() {
/*  47 */     return 76985491;
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
/*  60 */     return 16;
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
/*  72 */     boolean canSpawn = CommonConfig.INSTANCE.canSpawnGhostShips();
/*  73 */     boolean isFlat = WyHelper.isSurfaceFlat(chunkGen, chunkX, chunkZ, 2);
/*  74 */     boolean isChance = (MathHelper.clamp(WyHelper.randomWithRange(0, 100) + WyHelper.randomDouble(), 0.0D, 100.0D) < CommonConfig.INSTANCE.getChanceForGhostShipSpawn());
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
/*  87 */     if (!CommonConfig.INSTANCE.canSpawnGhostShips()) {
/*     */       return;
/*     */     }
/*  90 */     if (biome.getCategory() == Biome.Category.OCEAN) {
/*     */       
/*  92 */       Structure structure = ModFeatures.GHOST_SHIP;
/*  93 */       biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, structure.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(new NoPlacementConfig())));
/*  94 */       biome.addStructure(structure.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class Start
/*     */     extends StructureStart
/*     */   {
/*     */     public Start(Structure<?> structure, int chunkX, int chunkZ, MutableBoundingBox bb, int i3, long seed) {
/* 102 */       super(structure, chunkX, chunkZ, bb, i3, seed);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biome) {
/* 108 */       int i = chunkX * 16;
/* 109 */       int j = chunkZ * 16;
/* 110 */       BlockPos blockpos = new BlockPos(i, generator.getSeaLevel() - 6, j);
/* 111 */       Rotation rotation = Rotation.values()[this.rand.nextInt((Rotation.values()).length)];
/* 112 */       GhostShipPieces.addComponents(templateManagerIn, blockpos, rotation, this.components);
/* 113 */       recalculateStructureSize();
/* 114 */       StructuresHelper.SPAWNED_STRUCTURES.add(blockpos);
/* 115 */       StructuresHelper.STRUCTURES_COUNT[4] = StructuresHelper.STRUCTURES_COUNT[4] + 1;
/*     */       
/* 117 */       WyDebug.debug("Ghost Ship spawned at: /tp " + blockpos.getX() + " ~ " + blockpos.getZ());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\ghostship\GhostShipStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */