package xyz.pixelatedw.mineminenomi.entities.projectiles.jiki;


import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.abilities.jiki.JikiHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

import java.util.List;

public class PunkPistolProjectile
  extends AbilityProjectileEntity
{
  private List<ItemStack> magneticItems;
  
  public PunkPistolProjectile(World world) {
    super(JikiProjectiles.PUNK_PISTOL, world);
  }

  
  public PunkPistolProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public PunkPistolProjectile(World world, double x, double y, double z) {
    super(JikiProjectiles.PUNK_PISTOL, world, x, y, z);
  }

  
  public PunkPistolProjectile(World world, LivingEntity player, List<ItemStack> items) {
    super(JikiProjectiles.PUNK_PISTOL, world, player);
    
    setDamage(10.0F);
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


