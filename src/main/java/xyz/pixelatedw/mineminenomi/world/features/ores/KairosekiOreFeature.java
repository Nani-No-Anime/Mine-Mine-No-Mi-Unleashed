/*    */ package xyz.pixelatedw.mineminenomi.world.features.ores;
/*    */ 
/*    */ import net.minecraft.world.biome.Biome;
/*    */ import net.minecraft.world.gen.GenerationStage;
/*    */ import net.minecraft.world.gen.feature.Feature;
/*    */ import net.minecraft.world.gen.feature.IFeatureConfig;
/*    */ import net.minecraft.world.gen.feature.OreFeatureConfig;
/*    */ import net.minecraft.world.gen.placement.CountRangeConfig;
/*    */ import net.minecraft.world.gen.placement.IPlacementConfig;
/*    */ import net.minecraft.world.gen.placement.Placement;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ 
/*    */ public class KairosekiOreFeature
/*    */ {
/*    */   public static void register(Biome biome) {
/* 17 */     if (biome.getCategory() == Biome.Category.OCEAN || biome.getCategory() == Biome.Category.BEACH)
/*    */     {
/* 19 */       biome
/* 20 */         .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE
/*    */ 
/*    */           
/* 23 */           .withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.KAIROSEKI_ORE
/*    */ 
/*    */ 
/*    */               
/* 27 */               .getDefaultState(), 6))
/*    */ 
/*    */ 
/*    */           
/* 31 */           .withPlacement(Placement.COUNT_RANGE
/* 32 */             .configure(new CountRangeConfig(((Integer)CommonConfig.INSTANCE.kairosekiSpawnCount
/*    */                 
/* 34 */                 .get()).intValue(), 0, 0, ((Integer)CommonConfig.INSTANCE.kairosekiSpawnMaxHeight.get()).intValue()))));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\world\features\ores\KairosekiOreFeature.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */