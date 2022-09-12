package xyz.pixelatedw.mineminenomi.items.dials;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;

import javax.annotation.Nullable;


public class EisenDialItem
  extends BlockItem
{
  public EisenDialItem() {
    super(ModBlocks.EISEN_DIAL, (new Item.Properties()).group(ModCreativeTabs.MISC).maxStackSize(16));
  }


  
  protected boolean onBlockPlaced(BlockPos pos, World world, @Nullable PlayerEntity player, ItemStack itemStack, BlockState state) {
    return super.onBlockPlaced(pos, world, player, itemStack, state);
  }
}


