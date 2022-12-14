package xyz.pixelatedw.mineminenomi.entities.projectiles.swordsman;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.damagesource.ModIndirectEntityDamageSource;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class YakkodoriProjectile extends AbilityProjectileEntity {
  public YakkodoriProjectile(World world) {
    super(SwordsmanProjectiles.YAKKODORI, world);
  }

  
  public YakkodoriProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public YakkodoriProjectile(World world, double x, double y, double z) {
    super(SwordsmanProjectiles.YAKKODORI, world, x, y, z);
  }

  
  public YakkodoriProjectile(World world, LivingEntity player) {
    super(SwordsmanProjectiles.YAKKODORI, world, player);
    
    setDamage(15.0F);
    setMaxLife(10);
    setCanGetStuckInGround();
    setPhysical(false);
    setAffectedByImbuing();
    
    this.source = (DamageSource)(new ModIndirectEntityDamageSource("ability_projectile", (Entity)this, (Entity)getThrower())).setProjectile().markDamageAsSlash();
    this.onBlockImpactEvent = this::onBlockImpactEvent;
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 1.0F);
    explosion.setStaticDamage(5.0F);
    explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
    explosion.doExplosion();
  }
}


