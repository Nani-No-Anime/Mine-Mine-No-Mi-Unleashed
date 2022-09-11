package xyz.pixelatedw.mineminenomi.entities.projectiles.kage;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class BrickBatProjectile
  extends AbilityProjectileEntity
{
  public BrickBatProjectile(World world) {
    super(KageProjectiles.BRICK_BAT, world);
  }

  
  public BrickBatProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public BrickBatProjectile(World world, double x, double y, double z) {
    super(KageProjectiles.BRICK_BAT, world, x, y, z);
  }

  
  public BrickBatProjectile(World world, LivingEntity player) {
    super(KageProjectiles.BRICK_BAT, world, player);
    
    setDamage(2.0F);
    setPassThroughEntities();
    setChangeHurtTime(true);
    setPhysical(false);
    setAffectedByImbuing();
  }
}


