package xyz.pixelatedw.mineminenomi.world.features.structures.watchtower.marine;

import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
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




public class MarineWatchTowerStructure
  extends OPStructure<NoFeatureConfig>
{
  public MarineWatchTowerStructure() {
    super(NoFeatureConfig::deserialize);
  }


  
  public String getStructureName() {
    return (new ResourceLocation("mineminenomi", "marine_watch_tower")).toString();
  }


  
  public int getSeedModifier() {
    return 31293151;
  }


  
  public int getSize() {
    return 2;
  }



  
  public int getFeatureDistance(ChunkGenerator<?> chunkGenerator) {
    return 10;
  }


  
  public int getFeatureSeparation(ChunkGenerator<?> chunkGenerator) {
    return 8;
  }


  
  public boolean canBeGenerated(BiomeManager biomeManager, ChunkGenerator<?> chunkGen, Random rand, int chunkX, int chunkZ, Biome biome) {
    boolean canSpawn = CommonConfig.INSTANCE.canSpawnWatchTowers();
    boolean isFlat = WyHelper.isSurfaceFlat(chunkGen, chunkX, chunkZ, 2);
    boolean isChance = (MathHelper.clamp(WyHelper.randomWithRange(0, 100) + WyHelper.randomDouble(), 0.0D, 100.0D) < CommonConfig.INSTANCE.getChanceForWatchTowersSpawn());
    
    return (canSpawn && isFlat && isChance && super.canBeGenerated(biomeManager, chunkGen, rand, chunkX, chunkZ, biome));
  }


  
  public Structure.IStartFactory getStartFactory() {
    return Start::new;
  }

  
  public static void register(Biome biome) {
    if (!CommonConfig.INSTANCE.canSpawnWatchTowers()) {
      return;
    }
    if (biome.getCategory() == Biome.Category.PLAINS || biome.getCategory() == Biome.Category.DESERT || biome.getCategory() == Biome.Category.BEACH || biome.getCategory() == Biome.Category.FOREST || biome.getCategory() == Biome.Category.MESA) {
      
      Structure structure = ModFeatures.MARINE_WATCH_TOWER;
      biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, structure.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(new NoPlacementConfig())));
      biome.addStructure(structure.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
    } 
  }
  
  public static class Start
    extends OPStructure.OPStructureStart
  {
    private TemplateManager templateManager;
    
    public Start(Structure<?> structure, int chunkX, int chunkZ, MutableBoundingBox bb, int i3, long seed) {
      super(structure, chunkX, chunkZ, bb, i3, seed);
      setBase(Blocks.DIRT);
    }


    
    public void init(ChunkGenerator<?> generator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biome) {
      this.templateManager = templateManager;
      int i = getStructure().getSize();
      
      int k = (chunkX << 4) + 7;
      int l = (chunkZ << 4) + 7;
      int i1 = generator.getNoiseHeightMinusOne(k, l, Heightmap.Type.WORLD_SURFACE_WG);
      int j1 = generator.getNoiseHeightMinusOne(k, l + i, Heightmap.Type.WORLD_SURFACE_WG);
      int k1 = generator.getNoiseHeightMinusOne(k + i, l, Heightmap.Type.WORLD_SURFACE_WG);
      int l1 = generator.getNoiseHeightMinusOne(k + i, l + i, Heightmap.Type.WORLD_SURFACE_WG);
      int i2 = Math.min(Math.min(i1, j1), Math.min(k1, l1));
      if (i2 >= 60) {
        
        BlockPos blockpos = new BlockPos(chunkX * 16 + 8, i2, chunkZ * 16 + 8);
        MarineWatchTowerPieces.addComponents(this.templateManager, blockpos.add(0, -1, 0), this.components);
        recalculateStructureSize();
        StructuresHelper.SPAWNED_STRUCTURES.add(blockpos);
        StructuresHelper.STRUCTURES_COUNT[10] = StructuresHelper.STRUCTURES_COUNT[10] + 1;
        
        WyDebug.debug("Marine Watch Tower spawned at: /tp " + blockpos.getX() + " ~ " + blockpos.getZ());
      } 
    }
  }
}


