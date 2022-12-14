package xyz.pixelatedw.mineminenomi.items.dials;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.AxeDialProjectile;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;

import javax.annotation.Nullable;

public class AxeDialItem
  extends BlockItem {
  public AxeDialItem() {
    super(ModBlocks.AXE_DIAL, (new Item.Properties()).group(ModCreativeTabs.MISC).maxStackSize(16));
  }


  
  public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
    if (!world.isRemote) {
      
      AxeDialProjectile proj = new AxeDialProjectile(player.world, (LivingEntity)player);
      
      world.addEntity((Entity)proj);
      proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
      
      player.getCooldownTracker().setCooldown(getItem(), 10);
      player.getHeldItem(hand).shrink(1);
    } 
    
    return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
  }


  
  protected boolean onBlockPlaced(BlockPos pos, World world, @Nullable PlayerEntity player, ItemStack itemStack, BlockState state) {
    return super.onBlockPlaced(pos, world, player, itemStack, state);
  }
}


