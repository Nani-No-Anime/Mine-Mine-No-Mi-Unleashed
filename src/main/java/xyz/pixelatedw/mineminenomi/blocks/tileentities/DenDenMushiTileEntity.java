package xyz.pixelatedw.mineminenomi.blocks.tileentities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import xyz.pixelatedw.mineminenomi.init.ModTileEntities;

import javax.annotation.Nullable;



public class DenDenMushiTileEntity
  extends TileEntity
{
  public DenDenMushiTileEntity() {
    super(ModTileEntities.DEN_DEN_MUSHI);
  }


  
  public CompoundNBT getUpdateTag() {
    return write(new CompoundNBT());
  }


  
  @Nullable
  public SUpdateTileEntityPacket getUpdatePacket() {
    CompoundNBT nbttagcompound = new CompoundNBT();
    write(nbttagcompound);
    return new SUpdateTileEntityPacket(this.pos, 9, getUpdateTag());
  }
}


