package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class BambooPillarEntity
  extends AbilityProjectileEntity
{
  public BambooPillarEntity(World world) {
    super(ExtraProjectiles.BAMBOO_PILLAR, world);
  }

  
  public BambooPillarEntity(EntityType type, World world) {
    super(type, world);
  }

  
  public BambooPillarEntity(World world, double x, double y, double z) {
    super(ExtraProjectiles.BAMBOO_PILLAR, world, x, y, z);
  }

  
  public BambooPillarEntity(World world, LivingEntity player) {
    super(ExtraProjectiles.BAMBOO_PILLAR, world, player);
    
    setPhysical(false);
    setMaxLife(8);
    setPassThroughEntities();
    setPassThroughBlocks();
  }
}


