package xyz.pixelatedw.mineminenomi.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.init.ModFoods;

public class SeaKingMeatItem
  extends Item
{
  public SeaKingMeatItem() {
    super((new Item.Properties()).group(ModCreativeTabs.MISC).food(ModFoods.SEA_KING_MEAT));
  }


  
  public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
    player.setActiveHand(hand);
    return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
  }


  
  public ItemStack onItemUseFinish(ItemStack itemStack, World world, LivingEntity entity) {
    if (!world.isRemote && entity instanceof PlayerEntity) {
      
      PlayerEntity player = (PlayerEntity)entity;
      player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 100, 0));
      
      player.heal(10.0F + player.getMaxHealth() / 10.0F);
      player.getFoodStats().consume(this, itemStack);
    } 
    
    itemStack.shrink(1);
    return itemStack;
  }
}


