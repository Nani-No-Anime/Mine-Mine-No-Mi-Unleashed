package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;


public class NormalBulletProjectile
  extends AbilityProjectileEntity
{
  public NormalBulletProjectile(World world) {
    super(ExtraProjectiles.NORMAL_BULLET, world);
  }

  
  public NormalBulletProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public NormalBulletProjectile(World world, double x, double y, double z) {
    super(ExtraProjectiles.NORMAL_BULLET, world, x, y, z);
  }

  
  public NormalBulletProjectile(World world, LivingEntity player) {
    super(ExtraProjectiles.NORMAL_BULLET, world, player);
    setPhysical(false);
    setAffectedByImbuing();
    setDamage(8.0F);
  }
}


