package xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class ChargingUrsusShockEntity
  extends Entity {
  protected static final DataParameter<Float> CHARGE = EntityDataManager.createKey(ChargingUrsusShockEntity.class, DataSerializers.FLOAT);
  
  private LivingEntity owner;

  
  public ChargingUrsusShockEntity(World worldIn) {
    super(NikyuProjectiles.CHARGING_URSUS_SHOCK, worldIn);
  }

  
  public ChargingUrsusShockEntity(EntityType<?> entityTypeIn, World worldIn) {
    super(entityTypeIn, worldIn);
  }


  
  public void tick() {
    super.tick();
    
    if (!this.world.isRemote) {
      
      if (this.owner == null) {
        
        remove();
        
        return;
      } 
      if (this.ticksExisted > 600) {
        
        remove();
        
        return;
      } 
      setPosition(this.owner.getPosX(), this.owner.getPosY() + this.owner.getEyeHeight() + (0.9F * 
          getCharge()), this.owner.getPosZ());
      setRotation(this.owner.rotationYawHead, 0.0F);
    } 
  }


  
  protected void registerData() {
    this.dataManager.register(CHARGE, Float.valueOf(0.0F));
  }

  
  public void setCharge(float value) {
    this.dataManager.set(CHARGE, Float.valueOf(value));
  }

  
  public float getCharge() {
    return ((Float)this.dataManager.get(CHARGE)).floatValue();
  }

  
  public void setOwner(LivingEntity owner) {
    this.owner = owner;
  }



  
  protected void readAdditional(CompoundNBT compound) {}



  
  protected void writeAdditional(CompoundNBT compound) {}


  
  public IPacket<?> createSpawnPacket() {
    return NetworkHooks.getEntitySpawningPacket(this);
  }
}


