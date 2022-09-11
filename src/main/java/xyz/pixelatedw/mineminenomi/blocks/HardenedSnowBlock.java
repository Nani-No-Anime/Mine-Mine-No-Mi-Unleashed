package xyz.pixelatedw.mineminenomi.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.init.ModMaterials;

public class HardenedSnowBlock
  extends Block {
  public HardenedSnowBlock() {
    super(Block.Properties.create(ModMaterials.HARDENED_SNOW_BLOCK).doesNotBlockMovement().hardnessAndResistance(8.0F, 4.0F).sound(SoundType.SNOW).harvestTool(ToolType.PICKAXE));
  }


  
  public VoxelShape getCollisionShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
    Entity entity = context.getEntity();
    if (entity instanceof PlayerEntity) {
      
      PlayerEntity player = (PlayerEntity)entity;
      if (DevilFruitCapability.get((LivingEntity)player).hasDevilFruit(ModAbilities.YUKI_YUKI_NO_MI))
      {
        return VoxelShapes.empty();
      }
    } 
    
    return VoxelShapes.fullCube();
  }
}


