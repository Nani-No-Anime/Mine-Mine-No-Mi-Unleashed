package xyz.pixelatedw.mineminenomi.entities.projectiles.blackleg;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class ExtraHachisProjectile
  extends AbilityProjectileEntity
{
  public ExtraHachisProjectile(World world) {
    super(BlackLegProjectiles.EXTRA_HACHIS, world);
  }

  
  public ExtraHachisProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public ExtraHachisProjectile(World world, double x, double y, double z) {
    super(BlackLegProjectiles.EXTRA_HACHIS, world, x, y, z);
  }

  
  public ExtraHachisProjectile(World world, LivingEntity player) {
    super(BlackLegProjectiles.EXTRA_HACHIS, world, player);
    
    setDamage(8.0F);
    setMaxLife(4);
    setPhysical(true);
    setChangeHurtTime(true);
    setHurtTime(5);
  }
}


