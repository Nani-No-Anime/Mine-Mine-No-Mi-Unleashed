package xyz.pixelatedw.mineminenomi.world;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.DebugGenerationSettings;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.WorldGenRegion;
import xyz.pixelatedw.mineminenomi.challenges.ArenaData;
import xyz.pixelatedw.mineminenomi.init.ModArenas;

public class ChallengesChunkGenerator
  extends ChunkGenerator<DebugGenerationSettings> {
  public ChallengesChunkGenerator(IWorld world, BiomeProvider biomeProvider, DebugGenerationSettings settings) {
    super(world, biomeProvider, settings);
  }


  
  public void decorate(WorldGenRegion region) {
    BlockPos.Mutable blockPos = new BlockPos.Mutable();
    int i = region.getMainChunkX();
    int j = region.getMainChunkZ();
    
    for (ArenaData arena : ModArenas.ARENAS) {
      
      int barrierStartX = arena.startBarrierPos.x;
      int barrierEndX = arena.endBarrierPos.x;
      int barrierStartZ = arena.startBarrierPos.z;
      int barrierEndZ = arena.endBarrierPos.z;
      
      if (i > barrierStartX && i < barrierEndX && j > barrierStartZ && j < barrierEndZ)
      {
        arena.preGen(region);
      }
      
      if (i > barrierStartX && i < barrierEndX && (j == barrierStartZ + 1 || j == barrierEndZ))
      {
        for (int x = 0; x < 16; x++) {
          
          int x1 = (i << 4) + x;
          int z1 = j << 4;
          
          for (int y = 0; y < region.getHeight(); y++) {
            region.setBlockState((BlockPos)blockPos.setPos(x1, y, z1), Blocks.BARRIER.getDefaultState(), 3);
          }
        } 
      }
      if (j > barrierStartZ && j < barrierEndZ && (i == barrierStartX + 1 || i == barrierEndX))
      {
        for (int z = 0; z < 16; z++) {
          
          int x1 = i << 4;
          int z1 = (j << 4) + z;
          
          for (int y = 0; y < region.getHeight(); y++) {
            region.setBlockState((BlockPos)blockPos.setPos(x1, y, z1), Blocks.BARRIER.getDefaultState(), 3);
          }
        } 
      }
    } 
  }

  
  public int getGroundHeight() {
    return 1;
  }



  
  public void makeBase(IWorld worldIn, IChunk chunkIn) {}



  
  public void generateSurface(WorldGenRegion region, IChunk chunk) {}



  
  public int getHeight(int x, int z, Heightmap.Type heightmapType) {
    return 0;
  }
}


