package xyz.pixelatedw.mineminenomi.entities.projectiles.gomu;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.gomu.GearSecondParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class GomuGomuNoJetPistolProjectile extends AbilityProjectileEntity {
  private static final ParticleEffect PARTICLES = (ParticleEffect)new GearSecondParticleEffect();

  
  public GomuGomuNoJetPistolProjectile(World world) {
    super(GomuProjectiles.GOMU_GOMU_NO_JET_PISTOL, world);
  }

  
  public GomuGomuNoJetPistolProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public GomuGomuNoJetPistolProjectile(World world, double x, double y, double z) {
    super(GomuProjectiles.GOMU_GOMU_NO_JET_PISTOL, world, x, y, z);
  }

  
  public GomuGomuNoJetPistolProjectile(World world, LivingEntity player) {
    super(GomuProjectiles.GOMU_GOMU_NO_JET_PISTOL, world, player);
    
    setDamage(10.0F);
    setMaxLife(9);
    setPhysical(true);
    setChangeHurtTime(true);
    
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onTickEvent() {
    if (this.ticksExisted % 2 == 0)
      PARTICLES.spawn(this.world, getPosX(), getPosY(), getPosZ(), 0.0D, 0.0D, 0.0D); 
  }
}


