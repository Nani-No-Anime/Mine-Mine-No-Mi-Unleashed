package xyz.pixelatedw.mineminenomi.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PaneBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;

public class OriBarsBlock extends PaneBlock {
  public OriBarsBlock() {
    super(Block.Properties.create(Material.IRON).hardnessAndResistance(50.0F));
  }


  
  public VoxelShape getCollisionShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
    Entity entity = context.getEntity();
    if (entity instanceof PlayerEntity) {
      
      PlayerEntity player = (PlayerEntity)entity;
      if (DevilFruitCapability.get((LivingEntity)player).hasDevilFruit(ModAbilities.ORI_ORI_NO_MI))
      {
        return VoxelShapes.empty();
      }
    } 
    
    if (getIndex(state) == 15) {
      return VoxelShapes.fullCube();
    }
    return this.collisionShapes[getIndex(state)];
  }
}


