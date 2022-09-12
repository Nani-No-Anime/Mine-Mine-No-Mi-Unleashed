package xyz.pixelatedw.mineminenomi.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModEffects;

import java.util.Random;

public class MucusBlock
  extends Block
{
  protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
  private int ticks = 1200;

  
  public MucusBlock() {
    super(Block.Properties.create(Material.ORGANIC).hardnessAndResistance(8.0F).tickRandomly().noDrops());
  }


  
  public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
    return SHAPE;
  }


  
  public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
    return SHAPE;
  }

  
  public boolean isFullCube(BlockState state) {
    return false;
  }


  
  public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
    return world.getBlockState(pos.down()).isSolid();
  }


  
  public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
    return !stateIn.isValidPosition((IWorldReader)worldIn, currentPos) ? Blocks.AIR.getDefaultState() : stateIn;
  }


  
  @OnlyIn(Dist.CLIENT)
  public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) {
    return (adjacentBlockState.getBlock() == this);
  }


  
  public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
    if (entity instanceof LivingEntity)
    {
      if (!((LivingEntity)entity).isPotionActive(ModEffects.STICKY))
      {
        ((LivingEntity)entity).addPotionEffect(new EffectInstance(ModEffects.STICKY, 50, 0, false, false, false));
      }
    }
  }


  
  public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving) {
    world.getPendingBlockTicks().scheduleTick(pos, this, tickRate((IWorldReader)world));
  }


  
  public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
    if (world.isRemote) {
      return;
    }
    if (this.ticks > 0) {
      
      this.ticks--;
      
      if (areFlamesClose((World)world, pos))
      {
        world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 4.0F, true, CommonConfig.INSTANCE.isAbilityGriefingEnabled() ? Explosion.Mode.DESTROY : Explosion.Mode.NONE);
      }
    }
    else {
      
      world.setBlockState(pos, Blocks.AIR.getDefaultState());
      this.ticks = 1200 + rand.nextInt(400);
    } 
    
    world.getPendingBlockTicks().scheduleTick(pos, this, 1, TickPriority.EXTREMELY_HIGH);
  }

  
  private boolean areFlamesClose(World world, BlockPos pos) {
    int range = 2;
    
    for (int i = -range; i < range; i++) {
      
      for (int j = -range; j < range; j++) {
        
        for (int k = -range; k < range; k++) {
          
          double posX = (pos.getX() + i);
          double posY = (pos.getY() + j);
          double posZ = (pos.getZ() + k);
          
          Block currentBlock = world.getBlockState(new BlockPos(posX, posY, posZ)).getBlock();
          if (currentBlock == Blocks.FIRE)
            return true; 
        } 
      } 
    } 
    return false;
  }
}


