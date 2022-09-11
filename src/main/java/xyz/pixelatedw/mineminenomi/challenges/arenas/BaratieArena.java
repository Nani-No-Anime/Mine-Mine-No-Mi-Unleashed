package xyz.pixelatedw.mineminenomi.challenges.arenas;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.ChallengeArenaTileEntity;
import xyz.pixelatedw.mineminenomi.challenges.ArenaData;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class BaratieArena
  extends ArenaData
{
  public BaratieArena() {
    super("baratie");
    this.spawnPos = new BlockPos(0, 65, 0);
    this.startBarrierPos = new ChunkPos(-5, -5);
    this.endBarrierPos = new ChunkPos(15, 15);
  }


  
  public void build(World world) {
    WyHelper.loadNBTStructure((ServerWorld)world, this.arenaName, this.spawnPos.add(0, -13, 0));
    world.setBlockState(this.spawnPos.add(0, -13, 0), Blocks.AIR.getDefaultState());
    
    this.playerSpawn = this.spawnPos.add(50, -1, 53);
    this.bossSpawn = this.playerSpawn.add(10, 0, 0);
    BlockPos arenaSpawn = this.playerSpawn.add(0, 5, 0);
    
    world.setBlockState(arenaSpawn, ModBlocks.CHALLENGE_ARENA.getDefaultState());
    this.arenaTileEntity = (ChallengeArenaTileEntity)world.getTileEntity(arenaSpawn);
    
    this.arenaTileEntity.setupArena(10000);
  }


  
  public void preGen(WorldGenRegion region) {
    BlockPos.Mutable blockPos = new BlockPos.Mutable();
    int i = region.getMainChunkX();
    int j = region.getMainChunkZ();
    
    for (int x = 0; x < 16; x++) {
      
      for (int z = 0; z < 16; z++) {
        
        int x1 = (i << 4) + x;
        int z1 = (j << 4) + z;
        
        for (int y = 0; y < 64; y++)
          region.setBlockState((BlockPos)blockPos.setPos(x1, y, z1), Blocks.WATER.getDefaultState(), 3); 
      } 
    } 
  }
}


