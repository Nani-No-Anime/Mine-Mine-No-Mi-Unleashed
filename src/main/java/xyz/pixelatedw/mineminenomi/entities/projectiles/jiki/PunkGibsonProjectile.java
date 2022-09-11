package xyz.pixelatedw.mineminenomi.entities.projectiles.jiki;


import java.util.List;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.abilities.jiki.JikiHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class PunkGibsonProjectile
  extends AbilityProjectileEntity
{
  private List<ItemStack> magneticItems;
  
  public PunkGibsonProjectile(World world) {
    super(JikiProjectiles.PUNK_GIBSON, world);
  }

  
  public PunkGibsonProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public PunkGibsonProjectile(World world, double x, double y, double z) {
    super(JikiProjectiles.PUNK_GIBSON, world, x, y, z);
  }

  
  public PunkGibsonProjectile(World world, LivingEntity player, List<ItemStack> items) {
    super(JikiProjectiles.PUNK_GIBSON, world, player);
    
    setDamage(40.0F);
    setPhysical(false);
    setAffectedByImbuing();
    
    this.magneticItems = items;
    
    this.onBlockImpactEvent = this::onBlockImpactEvent;
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    if (getThrower() != null && getThrower() instanceof PlayerEntity)
      JikiHelper.dropComponentItems((PlayerEntity)getThrower(), hit, this.magneticItems); 
  }
}


