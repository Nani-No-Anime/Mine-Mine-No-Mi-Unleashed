package xyz.pixelatedw.mineminenomi.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Foods;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;

public class DandelionItem
  extends Item
{
  public DandelionItem() {
    super((new Item.Properties()).group(ModCreativeTabs.MISC).food(Foods.DRIED_KELP));
  }


  
  public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
    player.setActiveHand(hand);
    return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
  }



  
  public ItemStack onItemUseFinish(ItemStack itemStack, World world, LivingEntity entity) {
    if (!world.isRemote && entity instanceof PlayerEntity) {
      
      ((PlayerEntity)entity).getCooldownTracker().setCooldown(getItem(), 600);
      entity.resetActiveHand();
      entity.world.setEntityState((Entity)entity, (byte)30);
      entity.heal(15.0F + entity.getMaxHealth() / 5.0F);
      itemStack.shrink(1);
    } 
    
    return itemStack;
  }



  
  public UseAction getUseAction(ItemStack stack) {
    return UseAction.EAT;
  }
}


