package xyz.pixelatedw.mineminenomi.entities.projectiles.sniper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.EntityCloud;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.sniper.KemuriBoshiParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class KemuriBoshiProjectile extends AbilityProjectileEntity {
  private static final ParticleEffect PARTICLES = (ParticleEffect)new KemuriBoshiParticleEffect();

  
  public KemuriBoshiProjectile(World world) {
    super(SniperProjectiles.KEMURI_BOSHI, world);
  }

  
  public KemuriBoshiProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public KemuriBoshiProjectile(World world, double x, double y, double z) {
    super(SniperProjectiles.KEMURI_BOSHI, world, x, y, z);
  }

  
  public KemuriBoshiProjectile(World world, LivingEntity player) {
    super(SniperProjectiles.KEMURI_BOSHI, world, player);
    
    setDamage(3.0F);
    setPhysical(false);
    setAffectedByImbuing();
    
    this.onBlockImpactEvent = this::onBlockImpactEvent;
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    KemuriBoshiCloudEntity smokeCloud = new KemuriBoshiCloudEntity(this.world);
    smokeCloud.setLife(100);
    smokeCloud.setLocationAndAngles(getPosX(), getPosY() + 1.0D, getPosZ(), 0.0F, 0.0F);
    smokeCloud.setMotion(0.0D, 0.0D, 0.0D);
    smokeCloud.setThrower(getThrower());
    this.world.addEntity((Entity)smokeCloud);
  }
  
  public static class KemuriBoshiCloudEntity
    extends EntityCloud
  {
    public KemuriBoshiCloudEntity(World world) {
      super(world);
    }


    
    public void tick() {
      super.tick();
      if (!this.world.isRemote) {
        
        for (LivingEntity target : WyHelper.<LivingEntity>getEntitiesNear(getPosition(), this.world, 6.0D)) {
          
          if (getThrower() != target && !target.isPotionActive(Effects.POISON)) {
            target.addPotionEffect(new EffectInstance(Effects.POISON, 80, 1));
          }
        } 
        if (this.ticksExisted % 2 == 0)
          KemuriBoshiProjectile.PARTICLES.spawn(this.world, getPosX(), getPosY(), getPosZ(), 0.0D, 0.0D, 0.0D); 
      } 
    }
  }
}


