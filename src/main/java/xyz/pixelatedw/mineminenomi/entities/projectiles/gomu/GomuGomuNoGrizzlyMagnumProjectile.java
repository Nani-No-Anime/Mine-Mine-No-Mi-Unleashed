package xyz.pixelatedw.mineminenomi.entities.projectiles.gomu;


import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class GomuGomuNoGrizzlyMagnumProjectile
  extends AbilityProjectileEntity {
  public GomuGomuNoGrizzlyMagnumProjectile(World world) {
    super(GomuProjectiles.GOMU_GOMU_NO_GRIZZLY_MAGNUM, world);
  }

  
  public GomuGomuNoGrizzlyMagnumProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public GomuGomuNoGrizzlyMagnumProjectile(World world, double x, double y, double z) {
    super(GomuProjectiles.GOMU_GOMU_NO_GRIZZLY_MAGNUM, world, x, y, z);
  }

  
  public GomuGomuNoGrizzlyMagnumProjectile(World world, LivingEntity player) {
    super(GomuProjectiles.GOMU_GOMU_NO_GRIZZLY_MAGNUM, world, player);
    
    setDamage(40.0F);
    setMaxLife(10);
    setPhysical(true);
    setPassThroughEntities();
    setHurtTime(10);
    
    this.onEntityImpactEvent = this::onEntityImpactEvent;
  }

  
  private void onEntityImpactEvent(LivingEntity hitEntity) {
    Vec3d speed = WyHelper.propulsion(getThrower(), 5.0D, 5.0D);
    hitEntity.setMotion(speed.x, 0.5D, speed.z);
    hitEntity.velocityChanged = true;
  }
}


