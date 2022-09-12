package xyz.pixelatedw.mineminenomi.challenges;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.WorldGenRegion;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.ChallengeArenaTileEntity;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.init.ModArenas;
import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenArenaSetupScreenPacket;
import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

import java.util.UUID;


public abstract class ArenaData
{
  public String arenaName;
  public BlockPos spawnPos = new BlockPos(0, 65, 0);
  public ChunkPos startBarrierPos = new ChunkPos(0, 0);
  public ChunkPos endBarrierPos = new ChunkPos(0, 0);
  
  public boolean isInUse;
  
  public UUID owner;
  protected BlockPos playerSpawn;
  protected BlockPos bossSpawn;
  protected ChallengeArenaTileEntity arenaTileEntity;
  
  public ArenaData(String arenaName) {
    this.arenaName = arenaName;
    ModArenas.ARENAS.add(this);
  }

  
  public void buildIfNeeded(PlayerEntity player, World world) {
    ExtendedWorldData worldData = ExtendedWorldData.get(world);
    
    if (worldData.isArenaGenerated(this.arenaName)) {
      return;
    }
    WyDebug.debug(this.arenaName + " arena is not present so it will be generated!");
    
    WyNetwork.sendTo(new SOpenArenaSetupScreenPacket(this.arenaName), player);
    
    long startTime = System.currentTimeMillis();
    
    build(world);
    
    long stopTime = System.currentTimeMillis();
    
    worldData.addGeneratedArena(this.arenaName);
    WyDebug.debug(this.arenaName + " has been generated at " + this.spawnPos + " in " + ((stopTime - startTime) / 1000L) + " seconds");
  }

  
  public abstract void build(World paramWorld);
  
  public abstract void preGen(WorldGenRegion paramWorldGenRegion);
  
  public BlockPos getPlayerSpawn() {
    return this.playerSpawn;
  }

  
  public BlockPos getBossSpawn() {
    return this.bossSpawn;
  }

  
  public ChallengeArenaTileEntity getArenaTileEntity() {
    return this.arenaTileEntity;
  }
}


