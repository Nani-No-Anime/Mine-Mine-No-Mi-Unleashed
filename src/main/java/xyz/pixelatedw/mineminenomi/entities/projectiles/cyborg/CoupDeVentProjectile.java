package xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class CoupDeVentProjectile extends AbilityProjectileEntity {
  public CoupDeVentProjectile(World world) {
    super(CyborgProjectiles.COUP_DE_VENT, world);
  }

  
  public CoupDeVentProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public CoupDeVentProjectile(World world, double x, double y, double z) {
    super(CyborgProjectiles.COUP_DE_VENT, world, x, y, z);
  }

  
  public CoupDeVentProjectile(World world, LivingEntity player) {
    super(CyborgProjectiles.COUP_DE_VENT, world, player);
    
    setDamage(15.0F);
    setPassThroughEntities();
    setMaxLife(15);
    setPhysical(false);
    
    this.onEntityImpactEvent = this::onEntityImpactEvent;
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onEntityImpactEvent(LivingEntity target) {
    double xPower = WyHelper.randomWithRange(-10, 10);
    if (xPower >= 0.0D) {
      xPower += 10.0D;
    } else {
      xPower -= 10.0D;
    } 
    double zPower = WyHelper.randomWithRange(-10, 10);
    if (zPower >= 0.0D) {
      zPower += 10.0D;
    } else {
      zPower -= 10.0D;
    } 
    target.setPosition(target.getPosX(), target.getPosY() + 10.0D, target.getPosZ());
    target.setMotion(xPower, 2.5D, zPower);
    target.velocityChanged = true;
    target.fallDistance = 0.0F;
    
    target.attackEntityFrom(DamageSource.causeMobDamage(getThrower()), 15.0F);
  }

  
  private void onTickEvent() {
    for (int i = 0; i < 25; i++) {
      
      double offsetX = WyHelper.randomDouble() * 1.2D;
      double offsetY = WyHelper.randomDouble() * 1.2D;
      double offsetZ = WyHelper.randomDouble() * 1.2D;
      
      ((ServerWorld)this.world).spawnParticle((IParticleData)ParticleTypes.END_ROD, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, -0.1D);
      if (i % 5 == 0)
        ((ServerWorld)this.world).spawnParticle((IParticleData)ParticleTypes.FLASH, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, -0.1D); 
    } 
  }
}


