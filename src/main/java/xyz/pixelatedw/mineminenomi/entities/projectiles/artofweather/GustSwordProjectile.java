package xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class GustSwordProjectile extends AbilityProjectileEntity {
  public GustSwordProjectile(World world) {
    super(ArtOfWeatherProjectiles.GUST_SWORD, world);
  }

  
  public GustSwordProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public GustSwordProjectile(World world, double x, double y, double z) {
    super(ArtOfWeatherProjectiles.GUST_SWORD, world, x, y, z);
  }

  
  public GustSwordProjectile(World world, LivingEntity player) {
    super(ArtOfWeatherProjectiles.GUST_SWORD, world, player);
    
    setDamage(2.0F);
    setPhysical(false);
    
    this.onTickEvent = this::onTickEvent;
    this.onEntityImpactEvent = this::onEntityImpactEvent;
  }

  
  private void onEntityImpactEvent(LivingEntity hitEntity) {
    Vec3d speed = WyHelper.propulsion(getThrower(), 4.0D, 4.0D);
    
    hitEntity.attackEntityFrom(this.source, 15.0F);
    hitEntity.setMotion(speed.x, 0.2D, speed.z);
    hitEntity.velocityChanged = true;
  }

  
  private void onTickEvent() {
    if (!this.world.isRemote)
    {
      for (int i = 0; i < 2; i++) {
        ParticleType<GenericParticleData> particle;
        double offsetX = WyHelper.randomDouble() / 3.0D;
        double offsetY = WyHelper.randomDouble() / 3.0D;
        double offsetZ = WyHelper.randomDouble() / 3.0D;

        
        if (i % 2 == 0) {
          particle = ModParticleTypes.MOKU;
        } else {
          particle = ModParticleTypes.MOKU2;
        } 
        GenericParticleData data = new GenericParticleData(particle);
        data.setLife(10);
        data.setSize(1.5F);
        WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
      } 
    }
  }
}


