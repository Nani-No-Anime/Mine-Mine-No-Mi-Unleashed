package xyz.pixelatedw.mineminenomi.items;

import java.awt.Color;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.DFEncyclopediaEntry;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenEncyclopediaScreenPacket;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;


public class DFEncyclopediaItem
  extends Item
{
  public DFEncyclopediaItem() {
    super((new Item.Properties()).group(ModCreativeTabs.MISC).maxStackSize(1));
  }


  
  public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
    ItemStack itemStack = player.getHeldItem(hand);
    
    if (hand != Hand.MAIN_HAND || world.isRemote) {
      return ActionResult.resultPass(itemStack);
    }
    if (!CommonConfig.INSTANCE.getRandomizedFruits()) {
      
      ITextComponent errorMessage = (new StringTextComponent((new TranslationTextComponent(ModI18n.SYSTEM_MESSAGE_RANDOMIZED_FRUITS, new Object[0])).getFormattedText())).setStyle((new Style()).setColor(TextFormatting.RED));
      player.sendMessage(errorMessage);
      return ActionResult.resultFail(itemStack);
    } 





    
    WyNetwork.sendTo(new SOpenEncyclopediaScreenPacket(itemStack), player);
    
    return ActionResult.resultSuccess(itemStack);
  }


  
  public void addInformation(ItemStack itemStack, @Nullable World world, List<ITextComponent> list, ITooltipFlag par4) {
    int count = itemStack.getOrCreateChildTag("unlocked").size();
    
    if (CommonConfig.INSTANCE.getRandomizedFruits()) {
      list.add(new TranslationTextComponent(ModI18n.ITEM_DF_ENCYCLOPEDIA_ENTRIES, new Object[] { Integer.valueOf(count) }));
    } else {
      
      ITextComponent errorMessage = (new StringTextComponent((new TranslationTextComponent(ModI18n.SYSTEM_MESSAGE_RANDOMIZED_FRUITS, new Object[0])).getFormattedText())).setStyle((new Style()).setColor(TextFormatting.RED));
      list.add(errorMessage);
    } 
  }

  
  public static void addFruitClues(ItemStack bookStack, String fruit, DFEncyclopediaEntry clue) {
    CompoundNBT nbt = new CompoundNBT();
    if (clue.getShape().isPresent())
      nbt.putInt("shape", ((Integer)clue.getShape().get()).intValue()); 
    if (clue.getBaseColor().isPresent())
      nbt.putInt("baseColor", ((Color)clue.getBaseColor().get()).getRGB()); 
    if (clue.getStemColor().isPresent())
      nbt.putInt("stemColor", ((Color)clue.getStemColor().get()).getRGB()); 
    bookStack.getOrCreateChildTag("unlocked").put(fruit, (INBT)nbt);
  }
}


