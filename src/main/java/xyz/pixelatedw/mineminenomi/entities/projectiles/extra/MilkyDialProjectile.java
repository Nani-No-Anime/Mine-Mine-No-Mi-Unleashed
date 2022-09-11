package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.state.IProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.blocks.SkyBlockBlock;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class MilkyDialProjectile extends AbilityProjectileEntity {
  public MilkyDialProjectile(World world) {
    super(ExtraProjectiles.MILKY_DIAL_PROJECTILE, world);
  }

  
  public MilkyDialProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public MilkyDialProjectile(World world, double x, double y, double z) {
    super(ExtraProjectiles.MILKY_DIAL_PROJECTILE, world, x, y, z);
  }

  
  public MilkyDialProjectile(World world, LivingEntity player) {
    super(ExtraProjectiles.MILKY_DIAL_PROJECTILE, world, player);
    
    setMaxLife(40);
    setPhysical(false);
    setPassThroughBlocks();
    
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onTickEvent() {
    for (BlockPos blockpos : BlockPos.getAllInBoxMutable(getPosition().add(-1, -1, -1), getPosition().add(1, 0, 1))) {
      
      BlockState state = (BlockState)ModBlocks.SKY_BLOCK.getDefaultState().with((IProperty)SkyBlockBlock.TYPE, Integer.valueOf(this.rand.nextInt(4)));
      AbilityHelper.placeBlockIfAllowed(this.world, blockpos.getX(), blockpos.getY(), blockpos.getZ(), state, 3, (BlockProtectionRule)AirBlockProtectionRule.INSTANCE);
    } 
  }
}


