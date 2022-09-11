package xyz.pixelatedw.mineminenomi.entities.projectiles.fishmankarate;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.WaterExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class UchimizuProjectile extends AbilityProjectileEntity {
  public UchimizuProjectile(World world) {
    super(FishmanKarateProjectiles.UCHIMIZU, world);
  }

  
  public UchimizuProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public UchimizuProjectile(World world, double x, double y, double z) {
    super(FishmanKarateProjectiles.UCHIMIZU, world, x, y, z);
  }

  
  public UchimizuProjectile(World world, LivingEntity player) {
    super(FishmanKarateProjectiles.UCHIMIZU, world, player);
    
    setDamage(2.0F);
    setMaxLife(30);
    setChangeHurtTime(true);
    setHurtTime(10);
    setPhysical(false);
    setAffectedByImbuing();
    
    this.onBlockImpactEvent = this::onBlockImpactEvent;
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 1.0F);
    explosion.setExplosionSound(false);
    explosion.setDamageOwner(false);
    explosion.disableExplosionKnockback();
    explosion.setDestroyBlocks(false);
    explosion.setFireAfterExplosion(false);
    explosion.setSmokeParticles((ParticleEffect)new WaterExplosionParticleEffect());
    explosion.setDamageEntities(false);
    explosion.doExplosion();
  }

  
  private void onTickEvent() {
    if (!this.world.isRemote)
    {
      for (int i = 0; i < 2; i++) {
        
        double offsetX = WyHelper.randomDouble() / 2.0D;
        double offsetY = WyHelper.randomDouble() / 2.0D;
        double offsetZ = WyHelper.randomDouble() / 2.0D;
        
        ((ServerWorld)this.world).spawnParticle((IParticleData)ParticleTypes.FISHING, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, -0.1D);
        ((ServerWorld)this.world).spawnParticle((IParticleData)ParticleTypes.BUBBLE, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.1D);
      } 
    }
  }
}


