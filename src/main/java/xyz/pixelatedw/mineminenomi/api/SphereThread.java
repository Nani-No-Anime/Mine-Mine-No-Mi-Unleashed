package xyz.pixelatedw.mineminenomi.api;

import net.minecraft.util.math.BlockPos;

import java.util.HashSet;

public class SphereThread
  extends Thread
{
  public boolean isComplete = false;
  public final BlockPos center;
  public final int radius;
  public HashSet<BlockPos> blockList = new HashSet<>();
  
  public final boolean hollow;
  
  public SphereThread(BlockPos pos, int radius, boolean hollow) {
    this.radius = radius;
    this.center = pos;
    this.hollow = hollow;
  }


  
  public void run() {
    super.run();
    
    int bx = this.center.getX();
    int by = this.center.getY();
    int bz = this.center.getZ();
    
    for (int x = bx - this.radius; x <= bx + this.radius; x++) {
      
      for (int y = by - this.radius; y <= by + this.radius; y++) {
        
        for (int z = bz - this.radius; z <= bz + this.radius; z++) {
          
          double distance = ((bx - x) * (bx - x) + (bz - z) * (bz - z) + (by - y) * (by - y));
          
          if (distance < (this.radius * this.radius) && (!this.hollow || distance >= ((this.radius - 1) * (this.radius - 1)))) {
            this.blockList.add(new BlockPos(x, y, z));
          }
        } 
      } 
    } 
    this.isComplete = true;
  }
}


