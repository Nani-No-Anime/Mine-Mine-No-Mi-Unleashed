package xyz.pixelatedw.mineminenomi.entities.projectiles.fishmankarate;

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

public class MurasameProjectile extends AbilityProjectileEntity {
  public MurasameProjectile(World world) {
    super(FishmanKarateProjectiles.MURASAME, world);
  }

  
  public MurasameProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public MurasameProjectile(World world, double x, double y, double z) {
    super(FishmanKarateProjectiles.MURASAME, world, x, y, z);
  }

  
  public MurasameProjectile(World world, LivingEntity player) {
    super(FishmanKarateProjectiles.MURASAME, world, player);
    
    setDamage(10.0F);
    setMaxLife(32);
    setPassThroughEntities();
    setChangeHurtTime(true);
    setPhysical(false);
    setAffectedByImbuing();
    
    this.onBlockImpactEvent = this::onBlockImpactEvent;
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 2.0F);
    explosion.setStaticDamage(8.0F);
    explosion.setExplosionSound(false);
    explosion.setDamageOwner(false);
    explosion.setDestroyBlocks(true);
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


