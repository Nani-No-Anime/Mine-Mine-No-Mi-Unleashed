package xyz.pixelatedw.mineminenomi.entities;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.command.arguments.EntityAnchorArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.items.VivreCardItem;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class VivreCardEntity extends Entity {
  @Nullable
  private LivingEntity owner;
  private final double speedLimit = 0.001D;
  private UUID ownerUUID;
  
  public VivreCardEntity(World worldIn) {
    super(ModEntities.VIVRE_CARD, worldIn);
  }

  
  public void setOwner(UUID uuid) {
    this.ownerUUID = uuid;
    this.owner = (LivingEntity)((ServerWorld)this.world).getEntityByUuid(this.ownerUUID);
  }


  
  public void tick() {
    super.tick();
    
    if (this.world.isRemote) {
      return;
    }
    if (!this.onGround) {
      move(MoverType.SELF, new Vec3d(0.0D, -0.1D, 0.0D));
    }
    if (this.ownerUUID == null || isBurning()) {
      
      remove();
      
      return;
    } 
    if (this.owner == null) {
      return;
    }
    if (this.owner.getHealth() <= 0.0F) {
      
      WyHelper.spawnParticles(ParticleTypes.FLAME, (ServerWorld)this.world, getPosX(), getPosY() + 0.3D, getPosZ());
      remove();
    } 
    
    double posX = getPosX() - this.owner.getPosX();
    double posZ = getPosZ() - this.owner.getPosZ();
    
    Objects.requireNonNull(this); if (posX > 0.0D && posX > 0.001D)
    { Objects.requireNonNull(this); posX = 0.001D; }
    else { Objects.requireNonNull(this); if (posX < 0.0D && posX < -0.001D) {
        Objects.requireNonNull(this); posX = -0.001D;
      }  }
     Objects.requireNonNull(this); if (posZ > 0.0D && posZ > 0.001D)
    { Objects.requireNonNull(this); posZ = 0.001D; }
    else { Objects.requireNonNull(this); if (posZ < 0.0D && posZ < -0.001D) {
        Objects.requireNonNull(this); posZ = -0.001D;
      }  }
     move(MoverType.SELF, new Vec3d(-posX, 0.0D, -posZ));
    
    lookAt(EntityAnchorArgument.Type.EYES, this.owner.getPositionVec());
    
    if (this.ticksExisted > 40) {
      
      List<PlayerEntity> players = WyHelper.getEntitiesNear(getPosition(), this.world, 0.5D, new Class[] { PlayerEntity.class });
      
      Iterator<PlayerEntity> iterator = players.iterator(); if (iterator.hasNext()) { PlayerEntity player = iterator.next();
        
        ItemStack stack = new ItemStack((IItemProvider)ModItems.VIVRE_CARD);
        
        ((VivreCardItem)stack.getItem()).setOwner(stack, this.owner);
        
        player.addItemStackToInventory(stack);
        remove(); }
    
    } 
  }


  
  protected void registerData() {}


  
  protected void writeAdditional(CompoundNBT compound) {
    compound.putString("OwnerUUID", this.ownerUUID.toString());
  }


  
  protected void readAdditional(CompoundNBT compound) {
    if (compound.contains("OwnerUUID", 8)) {
      setOwner(UUID.fromString(compound.getString("OwnerUUID")));
    }
  }

  
  public IPacket<?> createSpawnPacket() {
    return NetworkHooks.getEntitySpawningPacket(this);
  }
}


