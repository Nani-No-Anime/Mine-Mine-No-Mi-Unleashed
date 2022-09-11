package xyz.pixelatedw.mineminenomi.entities.projectiles.kage;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class TsunoTokagePillarEntity
  extends AbilityProjectileEntity
{
  public TsunoTokagePillarEntity(World world) {
    super(KageProjectiles.TSUNO_TOKAGE, world);
  }

  
  public TsunoTokagePillarEntity(EntityType type, World world) {
    super(type, world);
  }

  
  public TsunoTokagePillarEntity(World world, double x, double y, double z) {
    super(KageProjectiles.TSUNO_TOKAGE, world, x, y, z);
  }

  
  public TsunoTokagePillarEntity(World world, LivingEntity player) {
    super(KageProjectiles.TSUNO_TOKAGE, world, player);
    
    setDamage(30.0F);
    setMaxLife(10);
    setPassThroughEntities();
    setPassThroughBlocks();
    setPhysical(false);
    setAffectedByImbuing();
  }
}


