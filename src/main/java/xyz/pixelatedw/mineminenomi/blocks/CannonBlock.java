package xyz.pixelatedw.mineminenomi.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.CannonTileEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.CannonBallProjectile;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import javax.annotation.Nullable;

public class CannonBlock extends Block implements IWaterLoggable {
  public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
  public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

  
  public CannonBlock() {
    super(Block.Properties.create(Material.IRON).hardnessAndResistance(1.5F).notSolid());
    setDefaultState((BlockState)((BlockState)((BlockState)this.stateContainer.getBaseState()).with((IProperty)FACING, (Comparable)Direction.NORTH)).with((IProperty)WATERLOGGED, Boolean.valueOf(false)));
  }


  
  @Nullable
  public BlockState getStateForPlacement(BlockItemUseContext context) {
    BlockState blockstate = getDefaultState();
    BlockPos blockpos = context.getPos();
    Direction[] adirection = context.getNearestLookingDirections();
    IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
    
    for (Direction direction : adirection) {
      
      if (direction.getAxis().isHorizontal()) {
        
        blockstate = (BlockState)blockstate.with((IProperty)FACING, (Comparable)direction);
        blockstate = (BlockState)blockstate.with((IProperty)WATERLOGGED, Boolean.valueOf((ifluidstate.getFluid() == Fluids.WATER)));
        if (blockstate.isValidPosition((IWorldReader)context.getWorld(), blockpos))
        {
          return blockstate;
        }
      } 
    } 
    
    return null;
  }


  
  public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
    CannonTileEntity tileEntity = (CannonTileEntity)world.getTileEntity(pos);
    
    if (hand == Hand.OFF_HAND) {
      return ActionResultType.PASS;
    }
    if (!player.getHeldItemMainhand().isEmpty()) {
      
      if (tileEntity.getGunpowederLoaded() < 5 && player.getHeldItemMainhand().getItem() == Items.GUNPOWDER) {
        
        player.getHeldItemMainhand().shrink(1);
        tileEntity.addGunpoweder();
        return ActionResultType.SUCCESS;
      } 
      
      if (!tileEntity.hasCannonBall() && player.getHeldItemMainhand().getItem() == ModItems.CANNON_BALL) {
        
        player.getHeldItemMainhand().shrink(1);
        tileEntity.setHasCannonBall(true);
        return ActionResultType.SUCCESS;
      } 
    } 
    
    if (!world.isRemote && tileEntity.getGunpowederLoaded() > 0 && tileEntity.hasCannonBall()) {
      
      int damage = 30 + tileEntity.getGunpowederLoaded() * 2;
      int life = 100 + tileEntity.getGunpowederLoaded() * 20;
      float gravMod = life / 10000.0F;
      float speed = gravMod * 100.0F;
      if (speed < 1.5F) {
        speed = 0.0F;
      }
      CannonBallProjectile proj = new CannonBallProjectile(player.world, (LivingEntity)player);
      proj.setPosition(pos.getX(), pos.getY() + 1.25D, pos.getZ());
      proj.setDamage(damage);
      proj.setMaxLife(life);
      proj.setGravity(0.04F - gravMod);
      world.addEntity((Entity)proj);
      proj.shoot((Entity)player, 0.0F, ((Direction)state.get((IProperty)FACING)).getHorizontalAngle(), 0.0F, 3.0F + speed, 0.0F);
      
      for (int i = 0; i < 10; i++) {
        
        double offsetX = WyHelper.randomDouble();
        double offsetY = WyHelper.randomDouble();
        double offsetZ = WyHelper.randomDouble();
        
        ((ServerWorld)world).spawnParticle(ParticleTypes.POOF, pos.getX() + offsetX, (pos.getY() + 1) + offsetY, pos.getZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
      } 
      
      tileEntity.emptyGunpoweder();
      tileEntity.setHasCannonBall(false);
      return ActionResultType.SUCCESS;
    } 
    
    return ActionResultType.PASS;
  }


  
  public boolean removedByPlayer(BlockState state, World world, BlockPos pos, PlayerEntity player, boolean willHarvest, IFluidState fluid) {
    if (!world.isRemote) {
      
      TileEntity tileEntity = world.getTileEntity(pos);
      if (tileEntity != null && tileEntity instanceof CannonTileEntity) {
        
        CannonTileEntity tile = (CannonTileEntity)tileEntity;
        if (tile.hasCannonBall()) {
          
          ItemEntity itementity = new ItemEntity(world, tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ(), new ItemStack((IItemProvider)ModItems.CANNON_BALL));
          itementity.setDefaultPickupDelay();
          world.addEntity((Entity)itementity);
        } 
        
        int gunpowder = tile.getGunpowederLoaded();
        if (gunpowder > 0) {
          
          ItemStack gunpowderStack = new ItemStack((IItemProvider)Items.GUNPOWDER);
          gunpowderStack.setCount(gunpowder);
          ItemEntity itementity = new ItemEntity(world, tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ(), gunpowderStack);
          itementity.setDefaultPickupDelay();
          world.addEntity((Entity)itementity);
        } 
      } 
    } 
    return super.removedByPlayer(state, world, pos, player, willHarvest, fluid);
  }


  
  public IFluidState getFluidState(BlockState state) {
    return ((Boolean)state.get((IProperty)WATERLOGGED)).booleanValue() ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
  }


  
  public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
    if (((Boolean)stateIn.get((IProperty)WATERLOGGED)).booleanValue())
    {
      worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate((IWorldReader)worldIn));
    }
    return (facing.getOpposite() == stateIn.get((IProperty)FACING) && !stateIn.isValidPosition((IWorldReader)worldIn, currentPos)) ? Blocks.AIR.getDefaultState() : stateIn;
  }


  
  public BlockState rotate(BlockState state, Rotation rot) {
    return (BlockState)state.with((IProperty)FACING, (Comparable)rot.rotate((Direction)state.get((IProperty)FACING)));
  }


  
  public BlockState mirror(BlockState state, Mirror mirrorIn) {
    return state.rotate(mirrorIn.toRotation((Direction)state.get((IProperty)FACING)));
  }


  
  protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
    builder.add(new IProperty[] { (IProperty)WATERLOGGED });
    builder.add(new IProperty[] { (IProperty)FACING });
  }


  
  public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
    return true;
  }


  
  @OnlyIn(Dist.CLIENT)
  public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) {
    return true;
  }


  
  public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos) {
    return false;
  }


  
  public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
    return false;
  }


  
  public TileEntity createTileEntity(BlockState state, IBlockReader world) {
    return (TileEntity)new CannonTileEntity();
  }


  
  public boolean hasTileEntity(BlockState state) {
    return true;
  }
}


