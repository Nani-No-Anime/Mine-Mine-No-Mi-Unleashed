package xyz.pixelatedw.mineminenomi.entities.projectiles.doku;


import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class HydraProjectile
  extends AbilityProjectileEntity {
  private boolean teleport = false;
  
  public HydraProjectile(World world) {
    super(DokuProjectiles.HYDRA, world);
  }

  
  public HydraProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public HydraProjectile(World world, double x, double y, double z) {
    super(DokuProjectiles.HYDRA, world, x, y, z);
  }

  
  public HydraProjectile(World world, LivingEntity player, boolean teleport) {
    super(DokuProjectiles.HYDRA, world, player);
    
    setPhysical(false);
    setAffectedByImbuing();
    setDamage(teleport ? 8.0F : 20.0F);
    setMaxLife(teleport ? 30 : 15);
    setPassThroughEntities();
    this.teleport = teleport;
    
    this.onEntityImpactEvent = this::onEntityImpactEvent;
    this.onBlockImpactEvent = this::onBlockImpactEvent;
  }
  
  private void onBlockImpactEvent(BlockPos pos) {
    if (this.teleport) {
      
      if (getThrower() == null) {
        return;
      }
      BlockState state = (getThrower()).world.getBlockState(pos);
      if (state.getBlock().isAir(state, (IBlockReader)this.world, pos)) {
        return;
      }
      if (getThrower().getRidingEntity() != null) {
        getThrower().stopRiding();
      }
      getThrower().setPositionAndUpdate(pos.getX(), pos.getY() + 1.5D, pos.getZ());
      (getThrower()).fallDistance = 0.0F;
    } 
  }

  
  private void onEntityImpactEvent(LivingEntity hitEntity) {
    hitEntity.addPotionEffect(new EffectInstance(Effects.POISON, 500, 1));
  }
}


