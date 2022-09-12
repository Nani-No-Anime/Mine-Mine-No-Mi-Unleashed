package xyz.pixelatedw.mineminenomi.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;


public class SpikeEntity
  extends Entity
{
  protected boolean inGround;
  protected List<LivingEntity> targets = new ArrayList<>();

  
  public SpikeEntity(World world) {
    super(ModEntities.SPIKE, world);
  }


  
  public void tick() {
    super.tick();
    
    if (!this.onGround) {
      move(MoverType.SELF, new Vec3d(0.0D, -0.3D, 0.0D));
    }
    if (this.world.isRemote) {
      return;
    }
    List<LivingEntity> targets = WyHelper.getEntitiesNear(getPosition(), this.world, 0.0D, new Class[] { LivingEntity.class });
    
    for (LivingEntity entity : targets)
    {
      entity.attackEntityFrom(DamageSource.GENERIC, 1.0F);
    }
    
    if (this.ticksExisted > 1200.0D + WyHelper.randomWithRange(0, 100)) {
      remove();
    }
  }
  
  protected float getGravityVelocity() {
    return 0.03F;
  }



  
  protected void registerData() {}


  
  @Nullable
  public AxisAlignedBB getCollisionBox(Entity entity) {
    return entity.getBoundingBox();
  }


  
  public AxisAlignedBB getCollisionBoundingBox() {
    return null;
  }


  
  public boolean canBeCollidedWith() {
    return false;
  }


  
  protected void readAdditional(CompoundNBT compound) {
    compound.putBoolean("inGround", this.inGround);
  }


  
  protected void writeAdditional(CompoundNBT compound) {
    this.inGround = compound.getBoolean("inGround");
  }


  
  public IPacket<?> createSpawnPacket() {
    return NetworkHooks.getEntitySpawningPacket(this);
  }
}


