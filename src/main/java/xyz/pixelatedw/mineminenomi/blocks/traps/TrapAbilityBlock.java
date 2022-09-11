package xyz.pixelatedw.mineminenomi.blocks.traps;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;



public abstract class TrapAbilityBlock
  extends Block
{
  private int damageDealt = 0;
  private double horizontalFallSpeed = 0.05D;
  private double verticalFallSpeed = 0.25D;
  private EffectInstance potionEffect = null;

  
  public TrapAbilityBlock(Block.Properties properties) {
    super(properties);
  }


  
  public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entity) {
    if (entity instanceof LivingEntity) {
      
      IDevilFruit props = DevilFruitCapability.get((LivingEntity)entity);
      if (!props.hasDevilFruit(getDevilFruit())) {
        entity.setMotionMultiplier(state, new Vec3d(getHorizontalFallSpeed(), getVerticalFallSpeed(), getHorizontalFallSpeed()));
      }
    } else if (entity instanceof net.minecraft.entity.item.BoatEntity) {
      entity.remove();
    } else {
      entity.setMotionMultiplier(state, new Vec3d(getVerticalFallSpeed(), getHorizontalFallSpeed(), getVerticalFallSpeed()));
    } 
  }

  
  public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
    Entity entity = context.getEntity();
    if (entity instanceof PlayerEntity) {
      
      PlayerEntity player = (PlayerEntity)entity;
      if (DevilFruitCapability.get((LivingEntity)player).hasDevilFruit(getDevilFruit()))
      {
        return state.getShape(worldIn, pos);
      }
    } 
    
    return super.getCollisionShape(state, worldIn, pos, context);
  }

  
  public int getDamageDealt() {
    return this.damageDealt;
  }

  
  public void setDamageDealt(int damageDealt) {
    this.damageDealt = damageDealt;
  }

  
  public double getVerticalFallSpeed() {
    return this.verticalFallSpeed;
  }

  
  public void setVerticalFallSpeed(double verticalFallSpeed) {
    this.verticalFallSpeed = verticalFallSpeed;
  }

  
  public EffectInstance getPotionEffect() {
    return this.potionEffect;
  }

  
  public void setPotionEffect(EffectInstance potionEffect) {
    this.potionEffect = potionEffect;
  }

  
  public abstract AkumaNoMiItem getDevilFruit();

  
  public boolean canSpawnInBlock() {
    return false;
  }

  
  public double getHorizontalFallSpeed() {
    return this.horizontalFallSpeed;
  }

  
  public void setHorizontalFallSpeed(double horizontalFallSpeed) {
    this.horizontalFallSpeed = horizontalFallSpeed;
  }

  
  public static boolean isEntityInsideOpaqueBlock(LivingEntity e, int offSet) {
    BlockPos.PooledMutable blockpos$pooledmutableblockpos = BlockPos.PooledMutable.retain(); try {
      int i;
      for (i = 0; i < 8; i++)
      
      { int j = MathHelper.floor(e.getPosY() - offSet + ((((i >> 0) % 2) - 0.5F) * 0.1F) + e.getEyeHeight());
        int k = MathHelper.floor(e.getPosX() + ((((i >> 1) % 2) - 0.5F) * e.getHeight() * 0.8F));
        int l = MathHelper.floor(e.getPosZ() + ((((i >> 2) % 2) - 0.5F) * e.getWidth() * 0.8F));
        if (blockpos$pooledmutableblockpos.getX() != k || blockpos$pooledmutableblockpos.getY() != j || blockpos$pooledmutableblockpos.getZ() != l)
        
        { blockpos$pooledmutableblockpos.setPos(k, j, l);
          if (e.world.getBlockState((BlockPos)blockpos$pooledmutableblockpos).getBlock() instanceof TrapAbilityBlock)
          
          { boolean bool = true;



            
            if (blockpos$pooledmutableblockpos != null) blockpos$pooledmutableblockpos.close();  return bool; }  }  }  i = 0; if (blockpos$pooledmutableblockpos != null) blockpos$pooledmutableblockpos.close(); 
      return false;
    } catch (Throwable throwable) {
      if (blockpos$pooledmutableblockpos != null)
        try {
          blockpos$pooledmutableblockpos.close();
        } catch (Throwable throwable1) {
          throwable.addSuppressed(throwable1);
        }  
      throw throwable;
    } 
  }
}


