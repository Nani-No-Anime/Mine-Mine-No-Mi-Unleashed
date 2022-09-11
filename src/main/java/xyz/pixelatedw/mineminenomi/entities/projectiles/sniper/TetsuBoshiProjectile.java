package xyz.pixelatedw.mineminenomi.entities.projectiles.sniper;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.entities.SpikeEntity;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class TetsuBoshiProjectile extends AbilityProjectileEntity {
  public TetsuBoshiProjectile(World world) {
    super(SniperProjectiles.TETSU_BOSHI, world);
  }

  
  public TetsuBoshiProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public TetsuBoshiProjectile(World world, double x, double y, double z) {
    super(SniperProjectiles.TETSU_BOSHI, world, x, y, z);
  }

  
  public TetsuBoshiProjectile(World world, LivingEntity player) {
    super(SniperProjectiles.TETSU_BOSHI, world, player);
    
    setDamage(4.0F);
    setPhysical(false);
    setAffectedByImbuing();
    this.onBlockImpactEvent = this::onBlockImpactEvent;
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    for (int i = 0; i < 10; i++) {
      
      double offsetX = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
      double offsetZ = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
      
      SpikeEntity spike = new SpikeEntity(this.world);
      spike.setPositionAndRotation(hit.getX() + offsetX, (hit.getY() + 1), hit.getZ() + offsetZ, 180.0F, 0.0F);
      this.world.addEntity((Entity)spike);
    } 
  }
}


