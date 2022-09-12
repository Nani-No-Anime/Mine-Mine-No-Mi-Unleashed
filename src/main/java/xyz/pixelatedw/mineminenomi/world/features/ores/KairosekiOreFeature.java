package xyz.pixelatedw.mineminenomi.world.features.ores;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;

public class KairosekiOreFeature
{
  public static void register(Biome biome) {
    if (biome.getCategory() == Biome.Category.OCEAN || biome.getCategory() == Biome.Category.BEACH)
    {
      biome
        .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE

          
          .withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.KAIROSEKI_ORE


              
              .getDefaultState(), 6))


          
          .withPlacement(Placement.COUNT_RANGE
            .configure(new CountRangeConfig(((Integer)CommonConfig.INSTANCE.kairosekiSpawnCount
                
                .get()).intValue(), 0, 0, ((Integer)CommonConfig.INSTANCE.kairosekiSpawnMaxHeight.get()).intValue()))));
    }
  }
}


