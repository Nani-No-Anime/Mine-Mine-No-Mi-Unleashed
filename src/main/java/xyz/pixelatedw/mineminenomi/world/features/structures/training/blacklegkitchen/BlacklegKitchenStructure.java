package xyz.pixelatedw.mineminenomi.world.features.structures.training.blacklegkitchen;

import java.util.Random;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModFeatures;
import xyz.pixelatedw.mineminenomi.world.features.structures.OPStructure;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;




public class BlacklegKitchenStructure
  extends OPStructure<NoFeatureConfig>
{
  public BlacklegKitchenStructure() {
    super(NoFeatureConfig::deserialize);
  }


  
  public String getStructureName() {
    return (new ResourceLocation("mineminenomi", "blackleg_kitchen")).toString();
  }


  
  public int getSize() {
    return 1;
  }


  
  public boolean canBeGenerated(BiomeManager biomeManager, ChunkGenerator<?> chunkGen, Random rand, int chunkX, int chunkZ, Biome biome) {
    boolean canSpawn = CommonConfig.INSTANCE.canSpawnTrainingStructures();
    boolean isFlat = WyHelper.isSurfaceFlat(chunkGen, chunkX, chunkZ, 2);
    boolean isChance = (MathHelper.clamp(WyHelper.randomWithRange(0, 100) + WyHelper.randomDouble(), 0.0D, 100.0D) < CommonConfig.INSTANCE.getChanceForTrainingStructureSpawn());
    
    return (canSpawn && isFlat && isChance && super.canBeGenerated(biomeManager, chunkGen, rand, chunkX, chunkZ, biome));
  }


  
  public Structure.IStartFactory getStartFactory() {
    return Start::new;
  }



  
  public int getFeatureDistance(ChunkGenerator<?> chunkGenerator) {
    return 16;
  }


  
  public int getFeatureSeparation(ChunkGenerator<?> chunkGenerator) {
    return 8;
  }


  
  public int getSeedModifier() {
    return 65891445;
  }

  
  public static void register(Biome biome) {
    if (!CommonConfig.INSTANCE.canSpawnTrainingStructures()) {
      return;
    }
    if (biome.getCategory() == Biome.Category.PLAINS || biome.getCategory() == Biome.Category.BEACH || biome.getCategory() == Biome.Category.SAVANNA || biome.getCategory() == Biome.Category.FOREST) {
      
      Structure structure = ModFeatures.BLACKLEG_KITCHEN;
      biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, structure.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(new NoPlacementConfig())));
      biome.addStructure(structure.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
    } 
  }
  
  public static class Start
    extends StructureStart
  {
    public Start(Structure<?> structure, int chunkX, int chunkZ, MutableBoundingBox bb, int i3, long seed) {
      super(structure, chunkX, chunkZ, bb, i3, seed);
    }


    
    public void init(ChunkGenerator<?> generator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biome) {
      int i = chunkX * 16;
      int j = chunkZ * 16;
      BlockPos blockpos = new BlockPos(i, 90, j);
      Rotation rotation = Rotation.values()[this.rand.nextInt((Rotation.values()).length)];
      this.components.add(new BlacklegKitchenPiece(templateManager, blockpos.add(0, -2, 0), rotation));
      recalculateStructureSize();
      StructuresHelper.SPAWNED_STRUCTURES.add(blockpos);
      StructuresHelper.STRUCTURES_COUNT[9] = StructuresHelper.STRUCTURES_COUNT[9] + 1;
      
      WyDebug.debug("Blackleg Kitchen spawned at: /tp " + blockpos.getX() + " ~ " + blockpos.getZ());
    }
  }
}


