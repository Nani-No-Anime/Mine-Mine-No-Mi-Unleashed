package xyz.pixelatedw.mineminenomi.world.features.structures.skyisland.house;

import java.util.Random;
import net.minecraft.util.ResourceLocation;
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




public class SkyIslandHouseStructure
  extends OPStructure<NoFeatureConfig>
{
  public SkyIslandHouseStructure() {
    super(NoFeatureConfig::deserialize);
  }


  
  public String getStructureName() {
    return (new ResourceLocation("mineminenomi", "sky_island_house")).toString();
  }


  
  public int getSeedModifier() {
    return 59223722;
  }


  
  public int getSize() {
    return 3;
  }



  
  public int getFeatureDistance(ChunkGenerator<?> chunkGenerator) {
    return 12;
  }


  
  public int getFeatureSeparation(ChunkGenerator<?> chunkGenerator) {
    return 8;
  }


  
  public boolean canBeGenerated(BiomeManager biomeManager, ChunkGenerator<?> chunkGen, Random rand, int chunkX, int chunkZ, Biome biome) {
    boolean canSpawn = CommonConfig.INSTANCE.canSpawnSkyIslands();
    boolean isChance = (MathHelper.clamp(WyHelper.randomWithRange(0, 200) + WyHelper.randomDouble(), 0.0D, 200.0D) < CommonConfig.INSTANCE.getChanceForSkyIslandSpawn());
    
    return (canSpawn && isChance && super.canBeGenerated(biomeManager, chunkGen, rand, chunkX, chunkZ, biome));
  }


  
  public Structure.IStartFactory getStartFactory() {
    return Start::new;
  }

  
  public static void register(Biome biome) {
    if (!CommonConfig.INSTANCE.canSpawnSkyIslands()) {
      return;
    }
    if (biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND) {
      
      Structure structure = ModFeatures.SKY_ISLAND_HOUSE;
      biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, structure.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(new NoPlacementConfig())));
      biome.addStructure(structure.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
    } 
  }
  
  public static class Start
    extends StructureStart
  {
    private TemplateManager templateManager;
    
    public Start(Structure<?> structure, int chunkX, int chunkZ, MutableBoundingBox bb, int i3, long seed) {
      super(structure, chunkX, chunkZ, bb, i3, seed);
    }


    
    public void init(ChunkGenerator<?> generator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biome) {
      this.templateManager = templateManager;
      int i = chunkX * 16;
      int j = chunkZ * 16;
      BlockPos blockpos = new BlockPos(i, WyHelper.randomWithRange(180, 220), j);
      SkyIslandHousePieces.addComponents(this.templateManager, blockpos, this.components);
      recalculateStructureSize();
      StructuresHelper.SPAWNED_STRUCTURES.add(blockpos);
      StructuresHelper.STRUCTURES_COUNT[11] = StructuresHelper.STRUCTURES_COUNT[11] + 1;
      
      WyDebug.debug("Sky Island House spawned at: /tp " + blockpos.getX() + " ~ " + blockpos.getZ());
    }
  }
}


