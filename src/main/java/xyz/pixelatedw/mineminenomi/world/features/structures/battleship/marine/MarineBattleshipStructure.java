package xyz.pixelatedw.mineminenomi.world.features.structures.battleship.marine;

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
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModFeatures;
import xyz.pixelatedw.mineminenomi.world.features.structures.OPStructure;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;

import java.util.Random;




public class MarineBattleshipStructure
  extends OPStructure<NoFeatureConfig>
{
  public MarineBattleshipStructure() {
    super(NoFeatureConfig::deserialize);
  }


  
  public String getStructureName() {
    return (new ResourceLocation("mineminenomi", "marine_battleship")).toString();
  }


  
  public int getSeedModifier() {
    return 54136900;
  }


  
  public int getSize() {
    return 5;
  }



  
  public int getFeatureDistance(ChunkGenerator<?> chunkGenerator) {
    return 11;
  }


  
  public int getFeatureSeparation(ChunkGenerator<?> chunkGenerator) {
    return 8;
  }


  
  public boolean canBeGenerated(BiomeManager biomeManager, ChunkGenerator<?> chunkGen, Random rand, int chunkX, int chunkZ, Biome biome) {
    boolean canSpawn = CommonConfig.INSTANCE.canSpawnLargeShips();
    boolean isFlat = WyHelper.isSurfaceFlat(chunkGen, chunkX, chunkZ, 3);
    boolean isChance = (MathHelper.clamp(WyHelper.randomWithRange(0, 100) + WyHelper.randomDouble(), 0.0D, 100.0D) < CommonConfig.INSTANCE.getChanceForLargeShipsSpawn());
    
    return (canSpawn && isFlat && isChance && super.canBeGenerated(biomeManager, chunkGen, rand, chunkX, chunkZ, biome));
  }


  
  public Structure.IStartFactory getStartFactory() {
    return Start::new;
  }

  
  public static void register(Biome biome) {
    if (!CommonConfig.INSTANCE.canSpawnLargeShips()) {
      return;
    }
    if (biome.getCategory() == Biome.Category.OCEAN) {
      
      Structure structure = ModFeatures.MARINE_BATTLESHIP;
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


    
    public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biome) {
      int i = chunkX * 16;
      int j = chunkZ * 16;
      BlockPos blockpos = new BlockPos(i, generator.getSeaLevel() - 11, j);
      MarineBattleshipPieces.addComponents(templateManagerIn, blockpos, Rotation.randomRotation(new Random()), this.components);
      recalculateStructureSize();
      StructuresHelper.SPAWNED_STRUCTURES.add(blockpos);
      StructuresHelper.STRUCTURES_COUNT[3] = StructuresHelper.STRUCTURES_COUNT[3] + 1;
      
      WyDebug.debug("Marine Battleship spawned at: /tp " + blockpos.getX() + " ~ " + blockpos.getZ());
    }
  }
}


