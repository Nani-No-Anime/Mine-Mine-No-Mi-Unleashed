package xyz.pixelatedw.mineminenomi.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModItems;

public class AkumaNoMiBoxItem extends Item {
  private int tier;

  public AkumaNoMiBoxItem(int tier) {
    super((new Item.Properties()).group(ModCreativeTabs.MISC).maxStackSize(1));
    this.tier = tier;
  }

  public int getKeySlot(PlayerEntity player) {
    if (!player.inventory.hasItemStack(new ItemStack((IItemProvider) ModItems.KEY))) {

      player.sendMessage((ITextComponent) new TranslationTextComponent(ModI18n.ITEM_MESSAGE_NEED_KEY, new Object[0]));
      return -1;
    }

    for (int i = 0; i < player.inventory.getSizeInventory(); i++) {

      ItemStack stack = player.inventory.getStackInSlot(i);
      if (stack != null && !stack.isEmpty() && stack.getItem() == ModItems.KEY) {
        return i;
      }
    }
    return -1;
  }
  //[todo : add a config option to use default interaction of removing the box if the randomFruit returns null]
  public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
    if (!world.isRemote) {

      if (hand.equals(Hand.OFF_HAND)) {
        return new ActionResult(ActionResultType.FAIL, player.getHeldItem(hand));
      }
      int keySlot = getKeySlot(player);

      if (keySlot < 0) {
        return new ActionResult(ActionResultType.FAIL, player.getHeldItem(hand));
      }
      ItemStack itemStack = player.getHeldItemMainhand();


      Item randomFruit = DevilFruitHelper.rouletteDevilFruits(world, this.tier);
      randomFruit = DevilFruitHelper.oneFruitPerWorldCheck(world, randomFruit);

      if (randomFruit == null) {
        return new ActionResult(ActionResultType.FAIL, player.getHeldItem(hand));
      } else {
        player.inventory.decrStackSize(keySlot, 1);
        player.inventory.deleteStack(itemStack);  
        if (!(randomFruit instanceof AkumaNoMiItem)) {

          player.inventory.addItemStackToInventory(new ItemStack((IItemProvider) randomFruit));
          return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
        }

        if (DevilFruitHelper.hasDFLimitInInventory(player)) {

          player.dropItem(new ItemStack((IItemProvider) randomFruit), true);
          return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
        }

        player.inventory.addItemStackToInventory(new ItemStack((IItemProvider) randomFruit));
        ExtendedWorldData worldProps = ExtendedWorldData.get(player.world);
        worldProps.addDevilFruitInInventory(player.getUniqueID(), DevilFruitHelper.getDevilFruitKey((AkumaNoMiItem) randomFruit));
        return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
      }
    }

    return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
  }

  public int getTier() { return this.tier; }
}