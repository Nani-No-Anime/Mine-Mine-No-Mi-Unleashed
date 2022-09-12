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

public class DamnedPunkProjectile
  extends AbilityProjectileEntity
{
  private List<ItemStack> magneticItems;
  
  public DamnedPunkProjectile(World world) {
    super(JikiProjectiles.DAMNED_PUNK, world);
  }

  
  public DamnedPunkProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public DamnedPunkProjectile(World world, double x, double y, double z) {
    super(JikiProjectiles.DAMNED_PUNK, world, x, y, z);
  }

  
  public DamnedPunkProjectile(World world, LivingEntity player, List<ItemStack> items) {
    super(JikiProjectiles.DAMNED_PUNK, world, player);
    
    setDamage(40.0F);
    setArmorPiercing();
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


