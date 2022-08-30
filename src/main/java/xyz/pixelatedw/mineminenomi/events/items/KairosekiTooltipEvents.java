/*    */ package xyz.pixelatedw.mineminenomi.events.items;
/*    */ 
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.event.entity.player.ItemTooltipEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class KairosekiTooltipEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public static void onKairosekiCheck(ItemTooltipEvent event) {
/* 22 */     if (event.getItemStack().getItem().isIn(ModTags.Items.KAIROSEKI)) {
/*    */       
/* 24 */       StringTextComponent kairosekiString = new StringTextComponent(TextFormatting.YELLOW + "" + (new TranslationTextComponent(ModI18n.ITEM_KAIROSEKI_ITEM, new Object[0])).getFormattedText());
/* 25 */       if (!event.getToolTip().contains(kairosekiString)) {
/*    */         
/* 27 */         event.getToolTip().add(new StringTextComponent(""));
/* 28 */         event.getToolTip().add(kairosekiString);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\items\KairosekiTooltipEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */