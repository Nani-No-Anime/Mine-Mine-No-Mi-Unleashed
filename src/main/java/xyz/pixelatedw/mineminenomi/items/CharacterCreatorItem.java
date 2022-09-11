package xyz.pixelatedw.mineminenomi.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenCharacterCreatorScreenPacket;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;



public class CharacterCreatorItem
  extends Item
{
  public CharacterCreatorItem() {
    super((new Item.Properties()).group(ModCreativeTabs.MISC).maxStackSize(1));
  }


  
  public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
    if (!world.isRemote) {
      
      boolean hasRandomizedRace = CommonConfig.INSTANCE.getRaceRandomizer();
      WyNetwork.sendTo(new SOpenCharacterCreatorScreenPacket(hasRandomizedRace), player);
    } 
    return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
  }
}


