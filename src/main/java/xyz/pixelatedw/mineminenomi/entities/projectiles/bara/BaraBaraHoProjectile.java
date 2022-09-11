package xyz.pixelatedw.mineminenomi.entities.projectiles.bara;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class BaraBaraHoProjectile
  extends AbilityProjectileEntity
{
  public BaraBaraHoProjectile(World world) {
    super(BaraProjectiles.BARA_BARA_HO, world);
  }

  
  public BaraBaraHoProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public BaraBaraHoProjectile(World world, double x, double y, double z) {
    super(BaraProjectiles.BARA_BARA_HO, world, x, y, z);
  }

  
  public BaraBaraHoProjectile(World world, LivingEntity player) {
    super(BaraProjectiles.BARA_BARA_HO, world, player);
    
    setDamage(6.0F);
    setMaxLife(12);
    setPhysical(true);
  }
}


