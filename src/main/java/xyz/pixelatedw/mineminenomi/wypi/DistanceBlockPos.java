package xyz.pixelatedw.mineminenomi.wypi;

import net.minecraft.util.math.BlockPos;

































class DistanceBlockPos
  extends BlockPos
{
  public int hash;
  
  public DistanceBlockPos(double xIn, double yIn, double zIn, int hash) {
    super(xIn, yIn, zIn);
    this.hash = hash;
  }


  
  public int hashCode() {
    return this.hash;
  }
}


