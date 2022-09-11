package xyz.pixelatedw.mineminenomi.entities.projectiles.sniper;
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

public class RenpatsuNamariBoshiProjectile extends AbilityProjectileEntity {
  public RenpatsuNamariBoshiProjectile(World world) {
    super(SniperProjectiles.RENPATSU_NAMARI_BOSHI, world);
  }

  
  public RenpatsuNamariBoshiProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public RenpatsuNamariBoshiProjectile(World world, double x, double y, double z) {
    super(SniperProjectiles.RENPATSU_NAMARI_BOSHI, world, x, y, z);
  }

  
  public RenpatsuNamariBoshiProjectile(World world, LivingEntity player) {
    super(SniperProjectiles.RENPATSU_NAMARI_BOSHI, world, player);
    
    setDamage(9.0F);
    setPhysical(false);
    setAffectedByImbuing();
    
    this.onBlockImpactEvent = this::onBlockImpactEvent;
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 3.0F);
    explosion.setStaticDamage(12.0F);
    explosion.setExplosionSound(true);
    explosion.setDamageOwner(false);
    explosion.setDestroyBlocks(true);
    explosion.setFireAfterExplosion(false);
    explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(4));
    explosion.setDamageEntities(true);
    explosion.doExplosion();
  }
}


