package xyz.pixelatedw.mineminenomi.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.AblProtectionTileEntity;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;

public class AblProtectionBlock
  extends Block
{
  public AblProtectionBlock() {
    super(Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.5F).doesNotBlockMovement());
  }


  
  public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
    return VoxelShapes.fullCube();
  }


  
  public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
    return true;
  }


  
  @OnlyIn(Dist.CLIENT)
  public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) {
    return true;
  }


  
  public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
    return 0;
  }


  
  public TileEntity createTileEntity(BlockState state, IBlockReader world) {
    return (TileEntity)new AblProtectionTileEntity();
  }


  
  public boolean hasTileEntity(BlockState state) {
    return true;
  }


  
  public boolean removedByPlayer(BlockState state, World world, BlockPos pos, PlayerEntity player, boolean willHarvest, IFluidState fluid) {
    ExtendedWorldData worldData = ExtendedWorldData.get(world);
    worldData.removeRestrictedArea(pos.getX(), pos.getY(), pos.getZ());
    return super.removedByPlayer(state, world, pos, player, willHarvest, fluid);
  }
}


