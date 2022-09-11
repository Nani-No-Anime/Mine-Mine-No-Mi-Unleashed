package xyz.pixelatedw.mineminenomi.entities.projectiles.ryupteranodon;
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

public class BarizodonProjectile extends AbilityProjectileEntity {
  public BarizodonProjectile(World world) {
    super(RyuPteranodonProjectiles.BARIZODON, world);
  }

  
  public BarizodonProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public BarizodonProjectile(World world, double x, double y, double z) {
    super(RyuPteranodonProjectiles.BARIZODON, world, x, y, z);
  }

  
  public BarizodonProjectile(World world, LivingEntity player) {
    super(RyuPteranodonProjectiles.BARIZODON, world, player);
    
    setDamage(10.0F);
    setPassThroughEntities();
    setMaxLife(32);
    
    this.onBlockImpactEvent = this::onBlockImpactEvent;
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 3.0F);
    explosion.setStaticDamage(5.0F);
    explosion.disableExplosionKnockback();
    explosion.setDestroyBlocks(true);
    explosion.setFireAfterExplosion(false);
    explosion.setExplosionSound(false);
    explosion.setDamageEntities(false);
    explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(3));
    explosion.doExplosion();
  }
}


