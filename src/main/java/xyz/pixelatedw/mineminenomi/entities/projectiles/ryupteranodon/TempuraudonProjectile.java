package xyz.pixelatedw.mineminenomi.entities.projectiles.ryupteranodon;

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

public class TempuraudonProjectile extends AbilityProjectileEntity {
  public TempuraudonProjectile(World world) {
    super(RyuPteranodonProjectiles.TEMPURAUDON, world);
  }

  
  public TempuraudonProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public TempuraudonProjectile(World world, double x, double y, double z) {
    super(RyuPteranodonProjectiles.TEMPURAUDON, world, x, y, z);
  }

  
  public TempuraudonProjectile(World world, LivingEntity player) {
    super(RyuPteranodonProjectiles.TEMPURAUDON, world, player);
    
    setDamage(35.0F);
    setPassThroughEntities();
    setMaxLife(32);
    setArmorPiercing();
    
    this.onBlockImpactEvent = this::onBlockImpactEvent;
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 4.0F);
    explosion.setStaticDamage(15.0F);
    explosion.disableExplosionKnockback();
    explosion.setDestroyBlocks(true);
    explosion.setFireAfterExplosion(false);
    explosion.setExplosionSound(false);
    explosion.setDamageEntities(false);
    explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(3));
    explosion.doExplosion();
  }
}


