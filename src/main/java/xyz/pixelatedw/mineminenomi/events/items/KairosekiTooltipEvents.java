package xyz.pixelatedw.mineminenomi.events.items;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModTags;



@EventBusSubscriber(modid = "mineminenomi")
public class KairosekiTooltipEvents
{
  @SubscribeEvent
  @OnlyIn(Dist.CLIENT)
  public static void onKairosekiCheck(ItemTooltipEvent event) {
    if (event.getItemStack().getItem().isIn(ModTags.Items.KAIROSEKI)) {
      
      StringTextComponent kairosekiString = new StringTextComponent(TextFormatting.YELLOW + "" + (new TranslationTextComponent(ModI18n.ITEM_KAIROSEKI_ITEM, new Object[0])).getFormattedText());
      if (!event.getToolTip().contains(kairosekiString)) {
        
        event.getToolTip().add(new StringTextComponent(""));
        event.getToolTip().add(kairosekiString);
      } 
    } 
  }
}


