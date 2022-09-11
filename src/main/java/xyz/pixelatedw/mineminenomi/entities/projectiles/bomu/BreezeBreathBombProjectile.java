package xyz.pixelatedw.mineminenomi.entities.projectiles.bomu;

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

public class BreezeBreathBombProjectile extends AbilityProjectileEntity {
  private int tick = 0;

  
  public BreezeBreathBombProjectile(World world) {
    super(BomuProjectiles.BREEZE_BREATH_BOMB, world);
  }

  
  public BreezeBreathBombProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public BreezeBreathBombProjectile(World world, double x, double y, double z) {
    super(BomuProjectiles.BREEZE_BREATH_BOMB, world, x, y, z);
  }

  
  public BreezeBreathBombProjectile(World world, LivingEntity player) {
    super(BomuProjectiles.BREEZE_BREATH_BOMB, world, player);
    
    setPhysical(false);
    setDamage(15.0F);
    setMaxLife(26);
    setPassThroughEntities();
    
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onTickEvent() {
    if (this.tick > 0) {
      
      BlockPos pos = getPosition();
      ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, pos.getX(), pos.getY(), pos.getZ(), 2.0F);
      explosion.setHeightDifference(30);
      explosion.setStaticDamage(12.0F);
      explosion.setExplosionSound(true);
      explosion.setDamageOwner(false);
      explosion.setDestroyBlocks(true);
      explosion.setFireAfterExplosion(false);
      explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(3));
      explosion.setDamageEntities(true);
      explosion.doExplosion();
    } 
    this.tick++;
  }
}


