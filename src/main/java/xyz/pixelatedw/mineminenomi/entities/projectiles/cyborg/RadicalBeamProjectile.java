package xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg;

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

public class RadicalBeamProjectile extends AbilityProjectileEntity {
  public RadicalBeamProjectile(World world) {
    super(CyborgProjectiles.RADICAL_BEAM, world);
  }

  
  public RadicalBeamProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public RadicalBeamProjectile(World world, double x, double y, double z) {
    super(CyborgProjectiles.RADICAL_BEAM, world, x, y, z);
  }

  
  public RadicalBeamProjectile(World world, LivingEntity player) {
    super(CyborgProjectiles.RADICAL_BEAM, world, player);
    
    setDamage(50.0F);
    setMaxLife(15);
    setArmorPiercing();
    setCanGetStuckInGround();
    setPassThroughEntities();
    
    this.onBlockImpactEvent = this::onBlockImpactEvent;
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 4.0F);
    explosion.setStaticDamage(25.0F);
    explosion.setFireAfterExplosion(true);
    explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(3));
    explosion.doExplosion();
  }
}


