package xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class PadHoProjectile extends AbilityProjectileEntity {
  public PadHoProjectile(World world) {
    super(NikyuProjectiles.PAD_HO, world);
  }

  
  public PadHoProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public PadHoProjectile(World world, double x, double y, double z) {
    super(NikyuProjectiles.PAD_HO, world, x, y, z);
  }

  
  public PadHoProjectile(World world, LivingEntity player) {
    super(NikyuProjectiles.PAD_HO, world, player);
    
    setDamage(15.0F);
    setPhysical(false);
    setAffectedByImbuing();
    
    this.onBlockImpactEvent = this::onBlockImpactEvent;
    this.onEntityImpactEvent = this::onEntityImpactEvent;
  }

  
  private void onEntityImpactEvent(LivingEntity target) {
    this.onBlockImpactEvent.onImpact(target.getPosition());
    
    if (getDamage() > 10.0F) {
      
      Vec3d speed = target.getLook(1.0F).mul(-1.0D, -1.0D, -1.0D).mul(WyHelper.randomWithRange(4, 6), WyHelper.randomWithRange(1, 3), WyHelper.randomWithRange(4, 6));
      target.setMotion(speed.x, speed.y, speed.z);
      target.velocityChanged = true;
      target.fallDistance = 0.0F;
    } 
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), getDamage() / 5.0F);
    explosion.setStaticDamage(getDamage() / 3.0F);
    explosion.setExplosionSound(true);
    explosion.setDamageOwner(false);
    explosion.setDestroyBlocks(true);
    explosion.setFireAfterExplosion(false);
    explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
    explosion.setDamageEntities(false);
    explosion.doExplosion();
  }
}


