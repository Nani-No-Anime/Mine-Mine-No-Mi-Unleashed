package xyz.pixelatedw.mineminenomi.entities.projectiles.gomu;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class GomuGomuNoLeoBazookaProjectile
  extends AbilityProjectileEntity {
  public GomuGomuNoLeoBazookaProjectile(World world) {
    super(GomuProjectiles.GOMU_GOMU_NO_LEO_BAZOOKA, world);
  }

  
  public GomuGomuNoLeoBazookaProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public GomuGomuNoLeoBazookaProjectile(World world, double x, double y, double z) {
    super(GomuProjectiles.GOMU_GOMU_NO_LEO_BAZOOKA, world, x, y, z);
  }

  
  public GomuGomuNoLeoBazookaProjectile(World world, LivingEntity player) {
    super(GomuProjectiles.GOMU_GOMU_NO_LEO_BAZOOKA, world, player);
    
    setDamage(70.0F);
    setMaxLife(10);
    setPhysical(true);
    setPassThroughEntities();
    setHurtTime(10);
    
    this.onEntityImpactEvent = this::onEntityImpactEvent;
  }

  
  private void onEntityImpactEvent(LivingEntity hitEntity) {
    Vec3d speed = WyHelper.propulsion(getThrower(), 7.0D, 7.0D);
    hitEntity.setMotion(speed.x, 0.8D, speed.z);
    hitEntity.velocityChanged = true;
  }
}


