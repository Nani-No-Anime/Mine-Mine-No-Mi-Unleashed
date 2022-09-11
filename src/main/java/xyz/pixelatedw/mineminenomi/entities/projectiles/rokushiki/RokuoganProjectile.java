package xyz.pixelatedw.mineminenomi.entities.projectiles.rokushiki;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class RokuoganProjectile extends AbilityProjectileEntity {
  private float damage = 60.0F;

  
  public RokuoganProjectile(World world) {
    super(RokushikiProjectiles.ROKUOGAN, world);
  }

  
  public RokuoganProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public RokuoganProjectile(World world, double x, double y, double z) {
    super(RokushikiProjectiles.ROKUOGAN, world, x, y, z);
  }

  
  public RokuoganProjectile(World world, LivingEntity entity) {
    super(RokushikiProjectiles.ROKUOGAN, world, entity);
    
    setMaxLife(5);
    setDamage(60.0F);
    setCanGetStuckInGround();
    setPassThroughEntities();
    setPhysical(false);
    
    this.onBlockImpactEvent = this::onBlockImpactEvent;
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    ExplosionAbility explosion = AbilityHelper.newExplosion(this, this.world, getPosX(), getPosY(), getPosZ(), this.damage / 7.0F);
    explosion.setExplosionSound(true);
    explosion.setDamageOwner(false);
    explosion.setDestroyBlocks(true);
    explosion.setFireAfterExplosion(false);
    explosion.setDamageEntities(false);
    explosion.doExplosion();
  }

  
  private void onTickEvent() {
    if (!this.world.isRemote)
    {
      for (int i = 0; i < 20; i++) {
        
        double offsetX = WyHelper.randomDouble() / 1.25D;
        double offsetY = WyHelper.randomDouble() / 1.25D;
        double offsetZ = WyHelper.randomDouble() / 1.25D;
        
        ((ServerWorld)this.world).spawnParticle((IParticleData)ParticleTypes.POOF, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, -0.1D);
        ((ServerWorld)this.world).spawnParticle((IParticleData)ParticleTypes.EXPLOSION_EMITTER, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.1D);
      } 
    }
    
    if (this.ticksExisted % 2 == 0) {
      reduceDamage();
    }
  }
  
  private void reduceDamage() {
    this.damage -= 10.0F;
    setDamage(this.damage);
  }
}


