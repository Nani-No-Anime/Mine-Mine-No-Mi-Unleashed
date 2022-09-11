package xyz.pixelatedw.mineminenomi.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.init.ModEffects;

public class BubblyCoralItem
  extends Item {
  public BubblyCoralItem() {
    super((new Item.Properties()).group(ModCreativeTabs.MISC).defaultMaxDamage(3));
  }


  
  public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
    ItemStack itemStack = player.getHeldItem(hand);
    
    if (player.isPotionActive(ModEffects.BUBBLY_CORAL)) {
      return new ActionResult(ActionResultType.PASS, player.getHeldItem(hand));
    }
    player.addPotionEffect(new EffectInstance(ModEffects.BUBBLY_CORAL, 3600, 0));
    itemStack.damageItem(1, (LivingEntity)player, user -> user.sendBreakAnimation(EquipmentSlotType.MAINHAND));
    
    return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
  }

  
  public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
    return false;
  }
}


