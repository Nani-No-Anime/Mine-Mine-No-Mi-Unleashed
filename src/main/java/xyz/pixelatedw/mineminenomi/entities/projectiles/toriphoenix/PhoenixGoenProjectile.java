package xyz.pixelatedw.mineminenomi.entities.projectiles.toriphoenix;


import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class PhoenixGoenProjectile extends AbilityProjectileEntity {
  private Vec3d lookVec;
  
  public PhoenixGoenProjectile(World world) {
    super(ToriPhoenixProjectiles.PHOENIX_GOEN, world);
  }

  
  public PhoenixGoenProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public PhoenixGoenProjectile(World world, double x, double y, double z) {
    super(ToriPhoenixProjectiles.PHOENIX_GOEN, world, x, y, z);
  }

  
  public PhoenixGoenProjectile(World world, LivingEntity player, Vec3d lookVec) {
    super(ToriPhoenixProjectiles.PHOENIX_GOEN, world, player);
    
    setDamage(10.0F);
    setCanGetStuckInGround();
    setMaxLife(30);
    setChangeHurtTime(true);
    
    this.lookVec = lookVec;
    this.onEntityImpactEvent = this::onEntityImpactEvent;
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onEntityImpactEvent(LivingEntity target) {
    Vec3d speed = this.lookVec.mul(1.5D, 0.0D, 1.5D);
    target.setMotion(speed.x, 0.15D, speed.z);
    target.velocityChanged = true;
    target.fallDistance = 0.0F;
  }

  
  private void onTickEvent() {
    float mult = getLife() / getMaxLife() * 1.25F;
    for (int i = 0; i < 25.0F * mult; i++) {
      
      double offsetX = WyHelper.randomDouble() * mult;
      double offsetY = WyHelper.randomDouble() * mult;
      double offsetZ = WyHelper.randomDouble() * mult;
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.BLUE_FLAME);
      data.setLife(8);
      data.setSize(3.0F * mult);
      WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
    } 
  }
}


