package xyz.pixelatedw.mineminenomi.entities.projectiles.ushigiraffe;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

@Deprecated
public class BiganProjectile
  extends AbilityProjectileEntity
{
  public BiganProjectile(World world) {
    super(UshiGiraffeProjectiles.BIGAN, world);
  }

  
  public BiganProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public BiganProjectile(World world, double x, double y, double z) {
    super(UshiGiraffeProjectiles.BIGAN, world, x, y, z);
  }

  
  public BiganProjectile(World world, LivingEntity player) {
    super(UshiGiraffeProjectiles.BIGAN, world, player);
    setPhysical(true);
    setMaxLife(16);
    setDamage(22.0F);
    setCanGetStuckInGround();
  }
}


