package xyz.pixelatedw.mineminenomi.entities.projectiles.sniper;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class TokuyoAburaBoshiProjectile
  extends AbilityProjectileEntity {
  public TokuyoAburaBoshiProjectile(World world) {
    super(SniperProjectiles.TOKUYO_ABURA_BOSHI, world);
  }

  
  public TokuyoAburaBoshiProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public TokuyoAburaBoshiProjectile(World world, double x, double y, double z) {
    super(SniperProjectiles.TOKUYO_ABURA_BOSHI, world, x, y, z);
  }

  
  public TokuyoAburaBoshiProjectile(World world, LivingEntity player) {
    super(SniperProjectiles.TOKUYO_ABURA_BOSHI, world, player);
    
    setDamage(4.0F);
    setPhysical(false);
    setAffectedByImbuing();
    this.onBlockImpactEvent = this::onBlockImpactEvent;
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    for (int heightSpread = -3; heightSpread < 3; heightSpread++) {
      
      for (int i = 0; i < 20; i++) {
        
        double offsetX = WyHelper.randomWithRange(-3, 3);
        double offsetZ = WyHelper.randomWithRange(-3, 3);
        
        BlockPos location = new BlockPos(getPosX() + offsetX, getPosY() + heightSpread, getPosZ() + offsetZ);
        
        if (this.world.getBlockState(location.down()).isSolid() && this.world.getBlockState(location.down()).getBlock() != ModBlocks.OIL_SPILL)
          AbilityHelper.placeBlockIfAllowed(this.world, location.getX(), location.getY(), location.getZ(), ModBlocks.OIL_SPILL, DefaultProtectionRules.AIR_FOLIAGE); 
      } 
    } 
  }
}


