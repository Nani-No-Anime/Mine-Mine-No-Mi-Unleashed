package xyz.pixelatedw.mineminenomi.wypi;

import java.util.HashSet;
import net.minecraft.util.math.BlockPos;


public class BlockPlacingHelper
{
  private HashSet<BlockPos> blockList = new HashSet<>();

  
  public void setBlockList(HashSet<BlockPos> list) {
    this.blockList = list;
  }

  
  public void addBlockPos(BlockPos pos, int hash) {
    this.blockList.add(new DistanceBlockPos(pos.getX(), pos.getY(), pos.getZ(), hash));
  }

  
  public void addBlockPos(double x, double y, double z, int hash) {
    this.blockList.add(new DistanceBlockPos(x, y, z, hash));
  }

  
  public HashSet<BlockPos> getBlockList() {
    return this.blockList;
  }

  
  public void clearList() {
    this.blockList = new HashSet<>();
  }
}


