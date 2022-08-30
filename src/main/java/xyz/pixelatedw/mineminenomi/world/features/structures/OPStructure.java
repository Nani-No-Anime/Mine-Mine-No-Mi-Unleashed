/*     */ package xyz.pixelatedw.mineminenomi.world.features.structures;
/*     */ 
/*     */ import com.mojang.datafixers.Dynamic;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import java.util.function.Function;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.util.SharedSeedRandom;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.ChunkPos;
/*     */ import net.minecraft.util.math.MutableBoundingBox;
/*     */ import net.minecraft.util.math.Vec3i;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraft.world.biome.BiomeManager;
/*     */ import net.minecraft.world.gen.ChunkGenerator;
/*     */ import net.minecraft.world.gen.feature.IFeatureConfig;
/*     */ import net.minecraft.world.gen.feature.structure.Structure;
/*     */ import net.minecraft.world.gen.feature.structure.StructurePiece;
/*     */ import net.minecraft.world.gen.feature.structure.StructureStart;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
/*     */ 
/*     */ 
/*     */ public abstract class OPStructure<C extends IFeatureConfig>
/*     */   extends Structure<C>
/*     */ {
/*     */   public OPStructure(Function<Dynamic<?>, ? extends C> configFactoryIn) {
/*  28 */     super(configFactoryIn);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ChunkPos getStartPositionForPosition(ChunkGenerator<?> chunkGenerator, Random random, int chunkX, int chunkZ, int offsetX, int offsetsZ) {
/*  34 */     int featureDistance = getFeatureDistance(chunkGenerator);
/*  35 */     int featureSeparation = getFeatureSeparation(chunkGenerator);
/*  36 */     int posX = chunkX + featureDistance * offsetX;
/*  37 */     int posZ = chunkZ + featureDistance * offsetsZ;
/*  38 */     int validChunkX = ((posX < 0) ? (posX - featureDistance + 1) : posX) / featureDistance;
/*  39 */     int validChunkZ = ((posZ < 0) ? (posZ - featureDistance + 1) : posZ) / featureDistance;
/*  40 */     ((SharedSeedRandom)random).setLargeFeatureSeedWithSalt(chunkGenerator.getSeed(), validChunkX, validChunkZ, getSeedModifier());
/*  41 */     validChunkX *= featureDistance;
/*  42 */     validChunkZ *= featureDistance;
/*  43 */     validChunkX += random.nextInt(featureDistance - featureSeparation);
/*  44 */     validChunkZ += random.nextInt(featureDistance - featureSeparation);
/*  45 */     return new ChunkPos(validChunkX, validChunkZ);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeGenerated(BiomeManager biomeManager, ChunkGenerator<?> chunkGen, Random rand, int chunkX, int chunkZ, Biome biome) {
/*  51 */     ChunkPos chunkPos = getStartPositionForPosition(chunkGen, rand, chunkX, chunkZ, 0, 0);
/*  52 */     boolean sameChunk = (chunkX == chunkPos.x && chunkZ == chunkPos.z);
/*     */     
/*  54 */     if (!sameChunk) {
/*  55 */       return false;
/*     */     }
/*  57 */     int i = chunkX * 16;
/*  58 */     int j = chunkZ * 16;
/*  59 */     BlockPos blockpos = new BlockPos(i, 90, j);
/*  60 */     int dist = (biome.getCategory() == Biome.Category.OCEAN) ? 128 : 80;
/*  61 */     Iterator<BlockPos> iter = StructuresHelper.SPAWNED_STRUCTURES.iterator();
/*  62 */     while (iter.hasNext()) {
/*     */       
/*  64 */       BlockPos pos = iter.next();
/*  65 */       if (blockpos.withinDistance((Vec3i)pos, dist))
/*     */       {
/*  67 */         return false;
/*     */       }
/*     */     } 
/*     */     
/*  71 */     for (Biome biomeCheck : chunkGen.getBiomeProvider().getBiomes(chunkX * 16 + 9, 64, chunkZ * 16 + 9, getSize() * 16)) {
/*     */       
/*  73 */       if (!chunkGen.hasStructure(biomeCheck, this))
/*     */       {
/*  75 */         return false;
/*     */       }
/*     */     } 
/*     */     
/*  79 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract Structure.IStartFactory getStartFactory();
/*     */ 
/*     */   
/*     */   public abstract String getStructureName();
/*     */ 
/*     */   
/*     */   public abstract int getSize();
/*     */   
/*     */   public abstract int getFeatureDistance(ChunkGenerator<?> paramChunkGenerator);
/*     */   
/*     */   public abstract int getFeatureSeparation(ChunkGenerator<?> paramChunkGenerator);
/*     */   
/*     */   public abstract int getSeedModifier();
/*     */   
/*     */   public static abstract class OPStructureStart
/*     */     extends StructureStart
/*     */   {
/*     */     private Block base;
/*     */     
/*     */     public OPStructureStart(Structure<?> structure, int chunkX, int chunkZ, MutableBoundingBox bb, int i3, long seed) {
/* 103 */       super(structure, chunkX, chunkZ, bb, i3, seed);
/*     */     }
/*     */ 
/*     */     
/*     */     public void setBase(Block block) {
/* 108 */       this.base = block;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void generateStructure(IWorld world, ChunkGenerator<?> generator, Random rand, MutableBoundingBox bb, ChunkPos chunkPos) {
/* 114 */       super.generateStructure(world, generator, rand, bb, chunkPos);
/*     */       
/* 116 */       if (this.base == null) {
/*     */         return;
/*     */       }
/* 119 */       int i = this.bounds.minY;
/*     */       
/* 121 */       for (int j = bb.minX; j <= bb.maxX; j++) {
/*     */         
/* 123 */         for (int k = bb.minZ; k <= bb.maxZ; k++) {
/*     */           
/* 125 */           BlockPos blockpos = new BlockPos(j, i, k);
/* 126 */           if (!world.isAirBlock(blockpos) && this.bounds.isVecInside((Vec3i)blockpos)) {
/*     */             
/* 128 */             boolean flag = false;
/*     */             
/* 130 */             for (StructurePiece structurepiece : this.components) {
/*     */               
/* 132 */               if (structurepiece.getBoundingBox().isVecInside((Vec3i)blockpos)) {
/*     */                 
/* 134 */                 flag = true;
/*     */                 
/*     */                 break;
/*     */               } 
/*     */             } 
/* 139 */             if (flag)
/*     */             {
/* 141 */               for (int l = i - 1; l > 1; l--) {
/*     */                 
/* 143 */                 BlockPos blockpos1 = new BlockPos(j, l, k);
/* 144 */                 if (!world.isAirBlock(blockpos1) && !world.getBlockState(blockpos1).getMaterial().isLiquid()) {
/*     */                   break;
/*     */                 }
/*     */ 
/*     */                 
/* 149 */                 world.setBlockState(blockpos1, this.base.getDefaultState(), 2);
/*     */               } 
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\OPStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */