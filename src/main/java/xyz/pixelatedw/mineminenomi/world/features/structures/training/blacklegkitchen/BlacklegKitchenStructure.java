/*     */ package xyz.pixelatedw.mineminenomi.world.features.structures.training.blacklegkitchen;
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
/*     */ public class BlacklegKitchenStructure
/*     */   extends OPStructure<NoFeatureConfig>
/*     */ {
/*     */   public BlacklegKitchenStructure() {
/*  35 */     super(NoFeatureConfig::deserialize);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStructureName() {
/*  41 */     return (new ResourceLocation("mineminenomi", "blackleg_kitchen")).toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSize() {
/*  47 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeGenerated(BiomeManager biomeManager, ChunkGenerator<?> chunkGen, Random rand, int chunkX, int chunkZ, Biome biome) {
/*  53 */     boolean canSpawn = CommonConfig.INSTANCE.canSpawnTrainingStructures();
/*  54 */     boolean isFlat = WyHelper.isSurfaceFlat(chunkGen, chunkX, chunkZ, 2);
/*  55 */     boolean isChance = (MathHelper.clamp(WyHelper.randomWithRange(0, 100) + WyHelper.randomDouble(), 0.0D, 100.0D) < CommonConfig.INSTANCE.getChanceForTrainingStructureSpawn());
/*     */     
/*  57 */     return (canSpawn && isFlat && isChance && super.canBeGenerated(biomeManager, chunkGen, rand, chunkX, chunkZ, biome));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Structure.IStartFactory getStartFactory() {
/*  63 */     return Start::new;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFeatureDistance(ChunkGenerator<?> chunkGenerator) {
/*  70 */     return 16;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFeatureSeparation(ChunkGenerator<?> chunkGenerator) {
/*  76 */     return 8;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSeedModifier() {
/*  82 */     return 65891445;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void register(Biome biome) {
/*  87 */     if (!CommonConfig.INSTANCE.canSpawnTrainingStructures()) {
/*     */       return;
/*     */     }
/*  90 */     if (biome.getCategory() == Biome.Category.PLAINS || biome.getCategory() == Biome.Category.BEACH || biome.getCategory() == Biome.Category.SAVANNA || biome.getCategory() == Biome.Category.FOREST) {
/*     */       
/*  92 */       Structure structure = ModFeatures.BLACKLEG_KITCHEN;
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
/*     */     public void init(ChunkGenerator<?> generator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biome) {
/* 108 */       int i = chunkX * 16;
/* 109 */       int j = chunkZ * 16;
/* 110 */       BlockPos blockpos = new BlockPos(i, 90, j);
/* 111 */       Rotation rotation = Rotation.values()[this.rand.nextInt((Rotation.values()).length)];
/* 112 */       this.components.add(new BlacklegKitchenPiece(templateManager, blockpos.add(0, -2, 0), rotation));
/* 113 */       recalculateStructureSize();
/* 114 */       StructuresHelper.SPAWNED_STRUCTURES.add(blockpos);
/* 115 */       StructuresHelper.STRUCTURES_COUNT[9] = StructuresHelper.STRUCTURES_COUNT[9] + 1;
/*     */       
/* 117 */       WyDebug.debug("Blackleg Kitchen spawned at: /tp " + blockpos.getX() + " ~ " + blockpos.getZ());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\training\blacklegkitchen\BlacklegKitchenStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */