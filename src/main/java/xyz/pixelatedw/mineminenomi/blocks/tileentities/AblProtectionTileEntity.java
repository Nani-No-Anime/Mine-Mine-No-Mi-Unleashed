package xyz.pixelatedw.mineminenomi.blocks.tileentities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.init.ModTileEntities;

import javax.annotation.Nullable;
import java.util.UUID;


public class AblProtectionTileEntity
  extends TileEntity
{
  private int protectedSize = 10;
  
  private UUID uuid;
  
  public AblProtectionTileEntity() {
    super(ModTileEntities.ABILITY_PROTECTION);
  }

  
  public void resizeProtection(PlayerEntity player, BlockPos pos, int id, int size) {
    int posX = pos.getX();
    int posY = pos.getY();
    int posZ = pos.getZ();
    
    ExtendedWorldData worldData = ExtendedWorldData.get(this.world);
    this.protectedSize = size;
    
    int minPosX = posX - size;
    int minPosY = posY - size;
    int minPosZ = posZ - size;
    int maxPosX = posX + size;
    int maxPosY = posY + size;
    int maxPosZ = posZ + size;
    
    worldData.resizeRestrictedArea(id, new int[] { minPosX, minPosY, minPosZ }, new int[] { maxPosX, maxPosY, maxPosZ });
    
    this.uuid = player.getUniqueID();
  }

  
  public void setupProtection(PlayerEntity player, BlockPos pos, int size) {
    int posX = pos.getX();
    int posY = pos.getY();
    int posZ = pos.getZ();
    
    ExtendedWorldData worldData = ExtendedWorldData.get(this.world);
    this.protectedSize = size;
    
    int minPosX = posX - size;
    int minPosY = posY - size;
    int minPosZ = posZ - size;
    int maxPosX = posX + size;
    int maxPosY = posY + size;
    int maxPosZ = posZ + size;
    
    worldData.addRestrictedArea(new int[] { minPosX, minPosY, minPosZ }, new int[] { maxPosX, maxPosY, maxPosZ });
    
    this.uuid = player.getUniqueID();
  }

  
  public int getSize() {
    return this.protectedSize;
  }

  
  public UUID getOwnerUUID() {
    return this.uuid;
  }


  
  public void read(CompoundNBT nbtTag) {
    super.read(nbtTag);
    
    this.protectedSize = nbtTag.getInt("Size");
    this.uuid = nbtTag.getUniqueId("UUID");
  }


  
  public CompoundNBT write(CompoundNBT nbtTag) {
    super.write(nbtTag);
    
    nbtTag.putInt("Size", this.protectedSize);
    nbtTag.putUniqueId("UUID", this.uuid);
    
    return nbtTag;
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


