package xyz.pixelatedw.mineminenomi.entities.projectiles.bara;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;


public class BaraFestivalEntity
  extends Entity
{
  private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.createKey(BaraFestivalEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
  
  private LivingEntity target;

  
  public BaraFestivalEntity(World world) {
    super(BaraProjectiles.BARA_FESTIVAL, world);
    setNoGravity(true);
  }


  
  protected void registerData() {
    this.dataManager.register(OWNER, Optional.empty());
  }


  
  public void tick() {
    if (!this.world.isRemote) {
      
      if (getOwner() == null || !getOwner().isAlive()) {
        
        remove();
        
        return;
      } 
      if (this.target == null || !this.target.isAlive()) {
        
        remove();
        
        return;
      } 
      setPositionAndUpdate(this.target.getPosX(), this.target.getPosY(), this.target.getPosZ());
    } 
    super.tick();
  }


  
  public void writeAdditional(CompoundNBT compound) {
    if (((Optional)this.dataManager.get(OWNER)).isPresent()) {
      compound.putString("OwnerUUID", ((UUID)((Optional<UUID>)this.dataManager.get(OWNER)).get()).toString());
    }
  }

  
  public void readAdditional(CompoundNBT compound) {
    this.dataManager.set(OWNER, Optional.of(UUID.fromString(compound.getString("OwnerUUID"))));
  }

  
  public void setTarget(LivingEntity target) {
    this.target = target;
  }

  
  public void setOwner(UUID uuid) {
    this.dataManager.set(OWNER, Optional.of(uuid));
  }

  
  @Nullable
  public UUID getOwnerUUID() {
    return ((Optional<UUID>)this.dataManager.get(OWNER)).orElseGet(() -> null);
  }

  
  public PlayerEntity getOwner() {
    return ((Optional)getDataManager().get(OWNER)).isPresent() ? this.world.getPlayerByUuid(((Optional<UUID>)getDataManager().get(OWNER)).get()) : null;
  }


  
  public IPacket<?> createSpawnPacket() {
    return NetworkHooks.getEntitySpawningPacket(this);
  }
}


