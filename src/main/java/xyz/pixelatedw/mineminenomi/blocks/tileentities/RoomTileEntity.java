package xyz.pixelatedw.mineminenomi.blocks.tileentities;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.abilities.ope.RoomAbility;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModTileEntities;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public class RoomTileEntity
  extends TileEntity
  implements ITickableTileEntity {
  private Optional<UUID> ownerUUID = Optional.empty();

  
  public RoomTileEntity() {
    super(ModTileEntities.ROOM);
  }


  
  public void tick() {
    if (!this.world.isRemote) {
      
      LivingEntity owner = getOwner();
      
      if (owner != null) {
        
        RoomAbility ability = (RoomAbility)AbilityDataCapability.get(owner).getEquippedAbility((Ability)RoomAbility.INSTANCE);
        
        if (ability != null) {
          
          double distance = getDistanceSq(owner.getPosX(), owner.getPosY(), owner.getPosZ());
          if (MathHelper.sqrt(distance) > (ability.getROOMSize() + 2) || !ability.isContinuous()) {
            ability.endContinuity((PlayerEntity)owner);
          }
        } else {
          clearRoom();
        } 
      } else {
        clearRoom();
      } 
    } 
  }
  
  public void clearRoom() {
    for (int i = -45; i < 45; i++) {
      for (int k = -45; k < 45; k++)
      { for (int j = -45; j < 45; j++)
        { if (this.world.getBlockState(new BlockPos(getPos().getX() + i, getPos().getY() + k, getPos().getZ() + j)).getBlock() == ModBlocks.OPE)
            this.world.setBlockState(new BlockPos(getPos().getX() + i, getPos().getY() + k, getPos().getZ() + j), Blocks.AIR.getDefaultState());  }  } 
    }  this.world.setBlockState(new BlockPos(getPos().getX(), getPos().getY(), getPos().getZ()), Blocks.AIR.getDefaultState());
  }

  
  public void setOwner(LivingEntity owner) {
    this.ownerUUID = Optional.of(owner.getUniqueID());
  }

  
  @Nullable
  public LivingEntity getOwner() {
    if (this.world instanceof ServerWorld && this.ownerUUID.isPresent()) {
      
      Entity owner = ((ServerWorld)this.world).getEntityByUuid(this.ownerUUID.get());
      if (owner instanceof LivingEntity) {
        return (LivingEntity)owner;
      }
      return null;
    } 
    
    return null;
  }


  
  public CompoundNBT write(CompoundNBT nbt) {
    super.write(nbt);
    
    if (this.ownerUUID.isPresent()) {
      nbt.putUniqueId("UUID", this.ownerUUID.get());
    }
    return nbt;
  }


  
  public void read(CompoundNBT nbt) {
    super.read(nbt);
    
    this.ownerUUID = Optional.of(nbt.getUniqueId("UUID"));
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
}


