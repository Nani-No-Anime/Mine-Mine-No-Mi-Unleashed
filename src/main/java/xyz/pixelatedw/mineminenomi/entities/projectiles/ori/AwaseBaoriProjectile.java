package xyz.pixelatedw.mineminenomi.entities.projectiles.ori;


import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

import javax.annotation.Nullable;
import java.util.List;


public class AwaseBaoriProjectile
  extends AbilityProjectileEntity
{
  private List<BlockPos> blocks;
  
  public AwaseBaoriProjectile(World world) {
    super(OriProjectiles.AWASE_BAORI, world);
  }

  
  public AwaseBaoriProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public AwaseBaoriProjectile(World world, double x, double y, double z) {
    super(OriProjectiles.AWASE_BAORI, world, x, y, z);
  }

  
  public AwaseBaoriProjectile(World world, LivingEntity player) {
    super(OriProjectiles.AWASE_BAORI, world, player);
    
    setDamage(6.0F);
    setPhysical(true);
    setAffectedByImbuing();
    setMaxLife(4);
    
    this.onEntityImpactEvent = this::onEntityImpactEvent;
  }

  
  private void onEntityImpactEvent(LivingEntity target) {
    if (this.blocks == null) {
      this.blocks = AbilityHelper.createEmptyCube(target.world, target.getPosX(), target.getPosY(), target.getPosZ(), 2, 2, 2, ModBlocks.ORI_BARS, DefaultProtectionRules.AIR_FOLIAGE);
    }
  }
  
  @Nullable
  public List<BlockPos> getBlocks() {
    return this.blocks;
  }
}


