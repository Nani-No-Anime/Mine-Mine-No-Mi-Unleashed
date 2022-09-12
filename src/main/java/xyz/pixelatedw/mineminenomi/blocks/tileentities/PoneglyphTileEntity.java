package xyz.pixelatedw.mineminenomi.blocks.tileentities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import xyz.pixelatedw.mineminenomi.init.ModTileEntities;

import javax.annotation.Nullable;

public class PoneglyphTileEntity
  extends TileEntity
{
  private Type entryType = Type.CHALLENGE;
  private String entryCategory = "";

  
  public PoneglyphTileEntity() {
    super(ModTileEntities.PONEGLYPH);
  }

  
  public Type getEntryType() {
    return this.entryType;
  }

  
  public void setEntryType(Type entryType) {
    this.entryType = entryType;
  }

  
  public String getEntryCategory() {
    return this.entryCategory;
  }

  
  public void setEntryCategory(String category) {
    this.entryCategory = category;
  }


  
  public CompoundNBT write(CompoundNBT nbt) {
    super.write(nbt);
    
    nbt.putInt("entryType", this.entryType.ordinal());
    nbt.putString("entryCategory", this.entryCategory);
    
    return nbt;
  }


  
  public void read(CompoundNBT nbt) {
    super.read(nbt);
    
    this.entryType = Type.values()[nbt.getInt("entryType")];
    this.entryCategory = nbt.getString("entryCategory");
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


  
  public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
    read(pkt.getNbtCompound());
  }
  
  public enum Type
  {
    CHALLENGE;
  }
}


