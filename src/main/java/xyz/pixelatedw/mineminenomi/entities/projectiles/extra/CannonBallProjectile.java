package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;


import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class CannonBallProjectile extends AbilityProjectileEntity {
  public CannonBallProjectile(World world) {
    super(ExtraProjectiles.CANNON_BALL, world);
  }

  
  public CannonBallProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public CannonBallProjectile(World world, double x, double y, double z) {
    super(ExtraProjectiles.CANNON_BALL, world, x, y, z);
  }

  
  public CannonBallProjectile(World world, LivingEntity entity) {
    super(ExtraProjectiles.CANNON_BALL, world, entity);
    setDamage(14.0F);
    setMaxLife(40);
    setGravity(0.01F);
    setPhysical(false);
    setAffectedByImbuing();
    
    this.onBlockImpactEvent = this::onBlockImpactEvent;
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    if (this.ticksExisted < 0) {
      return;
    }
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 2.0F);
    explosion.setStaticDamage(8.0F);
    explosion.setDestroyBlocks(true);
    explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
    explosion.doExplosion();
  }
}


