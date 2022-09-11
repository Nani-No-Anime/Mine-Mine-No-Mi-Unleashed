package xyz.pixelatedw.mineminenomi.world.features;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModFeatures;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class PoneglyphFeature
  extends Feature<NoFeatureConfig>
{
  public static void register(Biome biome) {
    if (!CommonConfig.INSTANCE.canSpawnPoneglyphs()) {
      return;
    }
    if (biome.getCategory() != Biome.Category.THEEND || biome.getCategory() != Biome.Category.NETHER)
    {
      biome
        .addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, ModFeatures.PONEGLYPH
          
          .withConfiguration(new NoFeatureConfig()).withPlacement(Placement.DUNGEONS.configure(new ChanceConfig(1))));
    }
  }


  
  public PoneglyphFeature() {
    super(NoFeatureConfig::deserialize);
  }


  
  public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
    boolean isChance = (MathHelper.clamp(WyHelper.randomWithRange(0, 100) + WyHelper.randomDouble(), 0.0D, 100.0D) < CommonConfig.INSTANCE.getChanceForPoneglyphSpawn());
    if (!isChance) {
      return false;
    }
    int spawnChecks = 0;
    
    int i;
    for (i = -2; i < 3; i++) {
      
      for (int j = -2; j < 3; j++) {
        
        for (int k = -2; k < 3; k++) {
          
          BlockPos blockpos = pos.add(i, j, k);
          Material material = world.getBlockState(blockpos).getMaterial();
          if (!material.isSolid()) {
            return false;
          }
          spawnChecks++;
        } 
      } 
    } 
    
    if (spawnChecks >= 9)
    {
      for (i = -1; i < 2; i++) {
        
        for (int j = -1; j < 2; j++) {
          
          for (int k = -1; k < 2; k++) {
            
            BlockPos pos2 = pos.add(i, j, k);
            world.setBlockState(pos2, ModBlocks.PONEGLYPH.getDefaultState(), 2);
          } 
        } 
      } 
    }


    
    return true;
  }
}


