package xyz.pixelatedw.mineminenomi.entities;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class BombEntity
  extends Entity
{
  public BombEntity(World world) {
    super(ModEntities.BOMB, world);
  }


  
  public void tick() {
    this.prevPosX = getPosX();
    this.prevPosY = getPosY();
    this.prevPosZ = getPosZ();
    
    if (this.ticksExisted % 3 == 0) {
      this.world.addParticle(ParticleTypes.FLAME, getPosX(), getPosY() + 1.0D, getPosZ(), 0.0D, 0.0D, 0.0D);
    }
    if (!this.world.isRemote) {
      
      if (this.ticksExisted % 10 == 0) {
        
        List<PlayerEntity> targets = WyHelper.getEntitiesNear(getPosition(), this.world, 2.0D, new Class[] { PlayerEntity.class });
        if (targets.size() > 0)
        {
          remove();
        }
      } 
      
      if (this.ticksExisted >= 300) {
        remove();
      }
    } 
  }

  
  public void remove() {
    if (!this.world.isRemote) {
      
      ExplosionAbility explosion = AbilityHelper.newExplosion(this, this.world, getPosX(), getPosY(), getPosZ(), 4.0F);
      explosion.setExplosionSound(true);
      explosion.setDamageOwner(false);
      explosion.setDestroyBlocks(false);
      explosion.setFireAfterExplosion(false);
      explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
      explosion.setDamageEntities(true);
      explosion.doExplosion();
    } 
    super.remove();
  }



  
  protected void registerData() {}


  
  @Nullable
  public AxisAlignedBB getCollisionBox(Entity entity) {
    return entity.getBoundingBox();
  }


  
  public AxisAlignedBB getCollisionBoundingBox() {
    return getBoundingBox();
  }


  
  protected boolean canTriggerWalking() {
    return false;
  }


  
  public boolean canBeCollidedWith() {
    return true;
  }



  
  protected void readAdditional(CompoundNBT compound) {}



  
  protected void writeAdditional(CompoundNBT compound) {}


  
  public IPacket<?> createSpawnPacket() {
    return NetworkHooks.getEntitySpawningPacket(this);
  }
}


