package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class EntityCloud
  extends Entity
{
  private int life = 100;
  
  private LivingEntity thrower;
  
  public EntityCloud(World world) {
    super(ExtraProjectiles.CLOUD, world);
  }
  
  public EntityCloud(EntityType type, World world) {
    super(type, world);
  }

  
  public void tick() {
    super.tick();
    if (!this.world.isRemote) {
      
      if (this.life <= 0) {
        remove();
      }
      this.life--;
    } 
  }

  
  @Nullable
  public LivingEntity getThrower() {
    return this.thrower;
  }

  
  public void setThrower(LivingEntity player) {
    this.thrower = player;
  }

  
  public int getLife() {
    return this.life;
  }

  
  public void setLife(int life) {
    this.life = life;
  }
  
  protected void registerData() {}
  
  protected void readAdditional(CompoundNBT compound) {}
  
  protected void writeAdditional(CompoundNBT compound) {}
  
  public IPacket<?> createSpawnPacket() {
    return NetworkHooks.getEntitySpawningPacket(this);
  }
}


