package xyz.pixelatedw.mineminenomi.entities.projectiles.swordsman;
import java.lang.invoke.SerializedLambda;
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

public class SanbyakurokujuPoundHoProjectile extends AbilityProjectileEntity {
  public SanbyakurokujuPoundHoProjectile(World world) {
    super(SwordsmanProjectiles.SANBYAKUROKUJU_POUND_HO, world);
  }

  
  public SanbyakurokujuPoundHoProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public SanbyakurokujuPoundHoProjectile(World world, double x, double y, double z) {
    super(SwordsmanProjectiles.SANBYAKUROKUJU_POUND_HO, world, x, y, z);
  }

  
  public SanbyakurokujuPoundHoProjectile(World world, LivingEntity player) {
    super(SwordsmanProjectiles.SANBYAKUROKUJU_POUND_HO, world, player);
    
    setDamage(25.0F);
    setMaxLife(10);
    setPassThroughEntities();
    setPhysical(false);
    setAffectedByImbuing();
    
    this.source = (DamageSource)(new ModIndirectEntityDamageSource("ability_projectile", (Entity)this, (Entity)getThrower())).setProjectile().markDamageAsSlash();
    this.onBlockImpactEvent = this::onBlockImpactEvent;
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 2.0F);
    explosion.setStaticDamage(10.0F);
    explosion.setExplosionSound(true);
    explosion.setDamageOwner(false);
    explosion.setDestroyBlocks(true);
    explosion.setFireAfterExplosion(false);
    explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
    explosion.setDamageEntities(true);
    explosion.doExplosion();
  }
}


