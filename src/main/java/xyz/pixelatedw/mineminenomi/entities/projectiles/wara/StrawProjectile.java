package xyz.pixelatedw.mineminenomi.entities.projectiles.wara;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class StrawProjectile
  extends AbilityProjectileEntity
{
  public StrawProjectile(World world) {
    super(WaraProjectiles.STRAW_PROJECTILE, world);
  }

  
  public StrawProjectile(EntityType entity, World world) {
    super(entity, world);
  }

  
  public StrawProjectile(World world, double x, double y, double z) {
    super(WaraProjectiles.STRAW_PROJECTILE, world, x, y, z);
  }

  
  public StrawProjectile(World world, LivingEntity p) {
    super(WaraProjectiles.STRAW_PROJECTILE, world, p);
    setDamage(10.0F);
    setPhysical(false);
    setMaxLife(8);
  }
}


