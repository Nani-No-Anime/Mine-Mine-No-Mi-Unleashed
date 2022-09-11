package xyz.pixelatedw.mineminenomi.entities.projectiles.goe;

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

public class TodorokiProjectile extends AbilityProjectileEntity {
  public TodorokiProjectile(World world) {
    super(GoeProjectiles.TODOROKI, world);
  }

  
  public TodorokiProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public TodorokiProjectile(World world, double x, double y, double z) {
    super(GoeProjectiles.TODOROKI, world, x, y, z);
  }

  
  public TodorokiProjectile(World world, LivingEntity player) {
    super(GoeProjectiles.TODOROKI, world, player);
    
    setDamage(15.0F);
    setMaxLife(15);
    setDamageSource(this.bypassingSource);
    setPassThroughEntities();
    setCanGetStuckInGround();
    setCollisionSize(2.5D);
    
    this.onBlockImpactEvent = this::onBlockImpactEvent;
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 3.0F);
    explosion.setStaticDamage(15.0F);
    explosion.setExplosionSound(true);
    explosion.setDamageOwner(false);
    explosion.setDestroyBlocks(true);
    explosion.setFireAfterExplosion(false);
    explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(4));
    explosion.setDamageEntities(true);
    explosion.doExplosion();
  }
}


