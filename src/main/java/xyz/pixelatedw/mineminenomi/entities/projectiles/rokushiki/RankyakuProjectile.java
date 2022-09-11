package xyz.pixelatedw.mineminenomi.entities.projectiles.rokushiki;

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

public class RankyakuProjectile extends AbilityProjectileEntity {
  public RankyakuProjectile(World world) {
    super(RokushikiProjectiles.RANKYAKU, world);
  }

  
  public RankyakuProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public RankyakuProjectile(World world, double x, double y, double z) {
    super(RokushikiProjectiles.RANKYAKU, world, x, y, z);
  }

  
  public RankyakuProjectile(World world, LivingEntity entity) {
    super(RokushikiProjectiles.RANKYAKU, world, entity);
    
    setDamage(30.0F);
    setMaxLife(40);
    setCanGetStuckInGround();
    setPassThroughEntities();
    setPhysical(false);
    setAffectedByImbuing();
    
    this.onBlockImpactEvent = this::onBlockImpactEvent;
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, getPosX(), getPosY(), getPosZ(), 3.0F);
    explosion.setStaticDamage(15.0F);
    explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(3));
    explosion.doExplosion();
  }
}


