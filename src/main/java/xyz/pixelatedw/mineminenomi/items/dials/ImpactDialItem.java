package xyz.pixelatedw.mineminenomi.items.dials;

import javax.annotation.Nullable;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;

public class ImpactDialItem
  extends BlockItem {
  public ImpactDialItem() {
    super(ModBlocks.IMPACT_DIAL, (new Item.Properties()).group(ModCreativeTabs.MISC).maxStackSize(16));
  }


  
  public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
    if (!world.isRemote) {
      
      player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 2, 4));
      world.createExplosion((Entity)player, player.getPosX(), player.getPosY(), player.getPosZ(), 3.0F, false, CommonConfig.INSTANCE.isAbilityGriefingEnabled() ? Explosion.Mode.DESTROY : Explosion.Mode.NONE);
      
      player.getCooldownTracker().setCooldown(getItem(), 10);
      player.getHeldItem(hand).shrink(1);
    } 
    
    return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
  }


  
  protected boolean onBlockPlaced(BlockPos pos, World world, @Nullable PlayerEntity player, ItemStack itemStack, BlockState state) {
    return super.onBlockPlaced(pos, world, player, itemStack, state);
  }
}


