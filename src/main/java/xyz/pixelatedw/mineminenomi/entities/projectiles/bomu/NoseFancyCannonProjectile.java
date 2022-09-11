package xyz.pixelatedw.mineminenomi.entities.projectiles.bomu;
import java.lang.invoke.SerializedLambda;
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

public class NoseFancyCannonProjectile extends AbilityProjectileEntity {
  public NoseFancyCannonProjectile(World world) {
    super(BomuProjectiles.NOSE_FANCY_CANNON, world);
  }

  
  public NoseFancyCannonProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public NoseFancyCannonProjectile(World world, double x, double y, double z) {
    super(BomuProjectiles.NOSE_FANCY_CANNON, world, x, y, z);
  }

  
  public NoseFancyCannonProjectile(World world, LivingEntity player) {
    super(BomuProjectiles.NOSE_FANCY_CANNON, world, player);
    
    setDamage(10.0F);
    setPhysical(false);
    setMaxLife(32);
    setAffectedByImbuing();
    
    this.onBlockImpactEvent = this::onBlockImpactEvent;
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 3.0F);
    explosion.setStaticDamage(15.0F);
    explosion.setExplosionSound(true);
    explosion.setDamageOwner(false);
    explosion.setDestroyBlocks(true);
    explosion.setFireAfterExplosion(false);
    explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(3));
    explosion.setDamageEntities(true);
    explosion.doExplosion();
  }
}


