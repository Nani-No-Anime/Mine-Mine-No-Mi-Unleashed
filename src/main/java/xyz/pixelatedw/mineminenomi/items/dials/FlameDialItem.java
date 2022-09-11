package xyz.pixelatedw.mineminenomi.items.dials;

import javax.annotation.Nullable;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;

public class FlameDialItem extends BlockItem {
  public FlameDialItem() {
    super(ModBlocks.FLAME_DIAL, (new Item.Properties()).group(ModCreativeTabs.MISC).maxStackSize(16));
  }


  
  public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
    if (!world.isRemote)
    {
      if (!player.isSneaking()) {
        
        Vec3d look = player.getLookVec();
        SmallFireballEntity fireball = new SmallFireballEntity(world, (LivingEntity)player, 1.0D, 1.0D, 1.0D);
        fireball.setPosition(player
            .getPosX(), player
            .getPosY() + 1.5D, player
            .getPosZ());
        fireball.accelerationX = look.x * 0.2D;
        fireball.accelerationY = look.y * 0.2D;
        fireball.accelerationZ = look.z * 0.2D;
        world.addEntity((Entity)fireball);
        
        player.getCooldownTracker().setCooldown(getItem(), 10);
        player.getHeldItem(hand).shrink(1);
      } 
    }
    
    return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
  }


  
  protected boolean onBlockPlaced(BlockPos pos, World world, @Nullable PlayerEntity player, ItemStack itemStack, BlockState state) {
    return super.onBlockPlaced(pos, world, player, itemStack, state);
  }
}


