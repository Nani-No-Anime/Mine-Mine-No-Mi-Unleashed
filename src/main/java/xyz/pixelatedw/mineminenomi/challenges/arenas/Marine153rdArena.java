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

public class Marine153rdArena
  extends ArenaData
{
  public Marine153rdArena() {
    super("marine153branch");
    this.spawnPos = new BlockPos(1000, 65, 0);
    this.startBarrierPos = new ChunkPos(61, -2);
    this.endBarrierPos = new ChunkPos(67, 5);
  }


  
  public void build(World world) {
    world.getChunk(this.spawnPos);
    
    WyHelper.loadNBTStructure((ServerWorld)world, this.arenaName, this.spawnPos.add(0, -13, 0));
    world.setBlockState(this.spawnPos.add(0, -13, 0), Blocks.AIR.getDefaultState());
    
    this.playerSpawn = this.spawnPos.add(30, 62, 35);
    this.bossSpawn = this.playerSpawn.add(10, 0, 0);
    BlockPos arenaSpawn = this.playerSpawn.add(0, -34, 0);
    
    world.setBlockState(arenaSpawn, ModBlocks.CHALLENGE_ARENA.getDefaultState());
    this.arenaTileEntity = (ChallengeArenaTileEntity)world.getTileEntity(arenaSpawn);
    
    this.arenaTileEntity.setupArena(20000);
  }
  
  public void preGen(WorldGenRegion region) {}
}


