package xyz.pixelatedw.mineminenomi.world.features.structures;

import com.mojang.datafixers.Dynamic;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Function;
import net.minecraft.block.Block;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.StructureStart;
import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;


public abstract class OPStructure<C extends IFeatureConfig>
  extends Structure<C>
{
  public OPStructure(Function<Dynamic<?>, ? extends C> configFactoryIn) {
    super(configFactoryIn);
  }


  
  public ChunkPos getStartPositionForPosition(ChunkGenerator<?> chunkGenerator, Random random, int chunkX, int chunkZ, int offsetX, int offsetsZ) {
    int featureDistance = getFeatureDistance(chunkGenerator);
    int featureSeparation = getFeatureSeparation(chunkGenerator);
    int posX = chunkX + featureDistance * offsetX;
    int posZ = chunkZ + featureDistance * offsetsZ;
    int validChunkX = ((posX < 0) ? (posX - featureDistance + 1) : posX) / featureDistance;
    int validChunkZ = ((posZ < 0) ? (posZ - featureDistance + 1) : posZ) / featureDistance;
    ((SharedSeedRandom)random).setLargeFeatureSeedWithSalt(chunkGenerator.getSeed(), validChunkX, validChunkZ, getSeedModifier());
    validChunkX *= featureDistance;
    validChunkZ *= featureDistance;
    validChunkX += random.nextInt(featureDistance - featureSeparation);
    validChunkZ += random.nextInt(featureDistance - featureSeparation);
    return new ChunkPos(validChunkX, validChunkZ);
  }


  
  public boolean canBeGenerated(BiomeManager biomeManager, ChunkGenerator<?> chunkGen, Random rand, int chunkX, int chunkZ, Biome biome) {
    ChunkPos chunkPos = getStartPositionForPosition(chunkGen, rand, chunkX, chunkZ, 0, 0);
    boolean sameChunk = (chunkX == chunkPos.x && chunkZ == chunkPos.z);
    
    if (!sameChunk) {
      return false;
    }
    int i = chunkX * 16;
    int j = chunkZ * 16;
    BlockPos blockpos = new BlockPos(i, 90, j);
    int dist = (biome.getCategory() == Biome.Category.OCEAN) ? 128 : 80;
    Iterator<BlockPos> iter = StructuresHelper.SPAWNED_STRUCTURES.iterator();
    while (iter.hasNext()) {
      
      BlockPos pos = iter.next();
      if (blockpos.withinDistance((Vec3i)pos, dist))
      {
        return false;
      }
    } 
    
    for (Biome biomeCheck : chunkGen.getBiomeProvider().getBiomes(chunkX * 16 + 9, 64, chunkZ * 16 + 9, getSize() * 16)) {
      
      if (!chunkGen.hasStructure(biomeCheck, this))
      {
        return false;
      }
    } 
    
    return true;
  }

  
  public abstract Structure.IStartFactory getStartFactory();

  
  public abstract String getStructureName();

  
  public abstract int getSize();
  
  public abstract int getFeatureDistance(ChunkGenerator<?> paramChunkGenerator);
  
  public abstract int getFeatureSeparation(ChunkGenerator<?> paramChunkGenerator);
  
  public abstract int getSeedModifier();
  
  public static abstract class OPStructureStart
    extends StructureStart
  {
    private Block base;
    
    public OPStructureStart(Structure<?> structure, int chunkX, int chunkZ, MutableBoundingBox bb, int i3, long seed) {
      super(structure, chunkX, chunkZ, bb, i3, seed);
    }

    
    public void setBase(Block block) {
      this.base = block;
    }


    
    public void generateStructure(IWorld world, ChunkGenerator<?> generator, Random rand, MutableBoundingBox bb, ChunkPos chunkPos) {
      super.generateStructure(world, generator, rand, bb, chunkPos);
      
      if (this.base == null) {
        return;
      }
      int i = this.bounds.minY;
      
      for (int j = bb.minX; j <= bb.maxX; j++) {
        
        for (int k = bb.minZ; k <= bb.maxZ; k++) {
          
          BlockPos blockpos = new BlockPos(j, i, k);
          if (!world.isAirBlock(blockpos) && this.bounds.isVecInside((Vec3i)blockpos)) {
            
            boolean flag = false;
            
            for (StructurePiece structurepiece : this.components) {
              
              if (structurepiece.getBoundingBox().isVecInside((Vec3i)blockpos)) {
                
                flag = true;
                
                break;
              } 
            } 
            if (flag)
            {
              for (int l = i - 1; l > 1; l--) {
                
                BlockPos blockpos1 = new BlockPos(j, l, k);
                if (!world.isAirBlock(blockpos1) && !world.getBlockState(blockpos1).getMaterial().isLiquid()) {
                  break;
                }

                
                world.setBlockState(blockpos1, this.base.getDefaultState(), 2);
              } 
            }
          } 
        } 
      } 
    }
  }
}


