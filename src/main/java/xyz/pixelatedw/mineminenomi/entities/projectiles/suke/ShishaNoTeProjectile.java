package xyz.pixelatedw.mineminenomi.entities.projectiles.suke;

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

public class ShishaNoTeProjectile extends AbilityProjectileEntity {
  public ShishaNoTeProjectile(World world) {
    super(SukeProjectiles.SHISHA_NO_TE, world);
  }

  
  public ShishaNoTeProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public ShishaNoTeProjectile(World world, double x, double y, double z) {
    super(SukeProjectiles.SHISHA_NO_TE, world, x, y, z);
  }

  
  public ShishaNoTeProjectile(World world, LivingEntity player) {
    super(SukeProjectiles.SHISHA_NO_TE, world, player);
    
    setDamage(18.0F);
    setPhysical(false);
    setAffectedByImbuing();
    
    this.onBlockImpactEvent = this::onBlockImpactEvent;
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 3.0F);
    explosion.setStaticDamage(8.0F);
    explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
    explosion.doExplosion();
  }
}


