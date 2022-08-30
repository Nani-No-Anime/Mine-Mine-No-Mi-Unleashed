/*    */ package xyz.pixelatedw.mineminenomi.world.features;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraft.world.biome.Biome;
/*    */ import net.minecraft.world.gen.ChunkGenerator;
/*    */ import net.minecraft.world.gen.GenerationSettings;
/*    */ import net.minecraft.world.gen.GenerationStage;
/*    */ import net.minecraft.world.gen.feature.Feature;
/*    */ import net.minecraft.world.gen.feature.IFeatureConfig;
/*    */ import net.minecraft.world.gen.feature.NoFeatureConfig;
/*    */ import net.minecraft.world.gen.placement.ChanceConfig;
/*    */ import net.minecraft.world.gen.placement.IPlacementConfig;
/*    */ import net.minecraft.world.gen.placement.Placement;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModFeatures;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class PoneglyphFeature
/*    */   extends Feature<NoFeatureConfig>
/*    */ {
/*    */   public static void register(Biome biome) {
/* 27 */     if (!CommonConfig.INSTANCE.canSpawnPoneglyphs()) {
/*    */       return;
/*    */     }
/* 30 */     if (biome.getCategory() != Biome.Category.THEEND || biome.getCategory() != Biome.Category.NETHER)
/*    */     {
/* 32 */       biome
/* 33 */         .addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, ModFeatures.PONEGLYPH
/*    */           
/* 35 */           .withConfiguration(new NoFeatureConfig()).withPlacement(Placement.DUNGEONS.configure(new ChanceConfig(1))));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public PoneglyphFeature() {
/* 42 */     super(NoFeatureConfig::deserialize);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
/* 48 */     boolean isChance = (MathHelper.clamp(WyHelper.randomWithRange(0, 100) + WyHelper.randomDouble(), 0.0D, 100.0D) < CommonConfig.INSTANCE.getChanceForPoneglyphSpawn());
/* 49 */     if (!isChance) {
/* 50 */       return false;
/*    */     }
/* 52 */     int spawnChecks = 0;
/*    */     
/*    */     int i;
/* 55 */     for (i = -2; i < 3; i++) {
/*    */       
/* 57 */       for (int j = -2; j < 3; j++) {
/*    */         
/* 59 */         for (int k = -2; k < 3; k++) {
/*    */           
/* 61 */           BlockPos blockpos = pos.add(i, j, k);
/* 62 */           Material material = world.getBlockState(blockpos).getMaterial();
/* 63 */           if (!material.isSolid()) {
/* 64 */             return false;
/*    */           }
/* 66 */           spawnChecks++;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 71 */     if (spawnChecks >= 9)
/*    */     {
/* 73 */       for (i = -1; i < 2; i++) {
/*    */         
/* 75 */         for (int j = -1; j < 2; j++) {
/*    */           
/* 77 */           for (int k = -1; k < 2; k++) {
/*    */             
/* 79 */             BlockPos pos2 = pos.add(i, j, k);
/* 80 */             world.setBlockState(pos2, ModBlocks.PONEGLYPH.getDefaultState(), 2);
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 88 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\world\features\PoneglyphFeature.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */