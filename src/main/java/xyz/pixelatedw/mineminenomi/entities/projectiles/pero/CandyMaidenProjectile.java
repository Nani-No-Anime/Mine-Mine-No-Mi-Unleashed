package xyz.pixelatedw.mineminenomi.entities.projectiles.pero;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class CandyMaidenProjectile
  extends AbilityProjectileEntity {
  public CandyMaidenProjectile(World world) {
    super(PeroProjectiles.CANDY_MAIDEN, world);
  }

  
  public CandyMaidenProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public CandyMaidenProjectile(World world, double x, double y, double z) {
    super(PeroProjectiles.CANDY_MAIDEN, world, x, y, z);
  }

  
  public CandyMaidenProjectile(World world, LivingEntity player) {
    super(PeroProjectiles.CANDY_MAIDEN, world, player);
    
    setPassThroughEntities();
    setPhysical(false);
    setAffectedByImbuing();
    setDamage(35.0F);
    setMaxLife(40);
  }
}


