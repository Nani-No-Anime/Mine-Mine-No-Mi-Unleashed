/*    */ package xyz.pixelatedw.mineminenomi.items;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.util.List;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.client.util.ITooltipFlag;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.nbt.INBT;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.util.text.Style;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.DFEncyclopediaEntry;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenEncyclopediaScreenPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ 
/*    */ public class DFEncyclopediaItem
/*    */   extends Item
/*    */ {
/*    */   public DFEncyclopediaItem() {
/* 32 */     super((new Item.Properties()).group(ModCreativeTabs.MISC).maxStackSize(1));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
/* 38 */     ItemStack itemStack = player.getHeldItem(hand);
/*    */     
/* 40 */     if (hand != Hand.MAIN_HAND || world.isRemote) {
/* 41 */       return ActionResult.resultPass(itemStack);
/*    */     }
/* 43 */     if (!CommonConfig.INSTANCE.getRandomizedFruits()) {
/*    */       
/* 45 */       ITextComponent errorMessage = (new StringTextComponent((new TranslationTextComponent(ModI18n.SYSTEM_MESSAGE_RANDOMIZED_FRUITS, new Object[0])).getFormattedText())).setStyle((new Style()).setColor(TextFormatting.RED));
/* 46 */       player.sendMessage(errorMessage);
/* 47 */       return ActionResult.resultFail(itemStack);
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 55 */     WyNetwork.sendTo(new SOpenEncyclopediaScreenPacket(itemStack), player);
/*    */     
/* 57 */     return ActionResult.resultSuccess(itemStack);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void addInformation(ItemStack itemStack, @Nullable World world, List<ITextComponent> list, ITooltipFlag par4) {
/* 63 */     int count = itemStack.getOrCreateChildTag("unlocked").size();
/*    */     
/* 65 */     if (CommonConfig.INSTANCE.getRandomizedFruits()) {
/* 66 */       list.add(new TranslationTextComponent(ModI18n.ITEM_DF_ENCYCLOPEDIA_ENTRIES, new Object[] { Integer.valueOf(count) }));
/*    */     } else {
/*    */       
/* 69 */       ITextComponent errorMessage = (new StringTextComponent((new TranslationTextComponent(ModI18n.SYSTEM_MESSAGE_RANDOMIZED_FRUITS, new Object[0])).getFormattedText())).setStyle((new Style()).setColor(TextFormatting.RED));
/* 70 */       list.add(errorMessage);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void addFruitClues(ItemStack bookStack, String fruit, DFEncyclopediaEntry clue) {
/* 76 */     CompoundNBT nbt = new CompoundNBT();
/* 77 */     if (clue.getShape().isPresent())
/* 78 */       nbt.putInt("shape", ((Integer)clue.getShape().get()).intValue()); 
/* 79 */     if (clue.getBaseColor().isPresent())
/* 80 */       nbt.putInt("baseColor", ((Color)clue.getBaseColor().get()).getRGB()); 
/* 81 */     if (clue.getStemColor().isPresent())
/* 82 */       nbt.putInt("stemColor", ((Color)clue.getStemColor().get()).getRGB()); 
/* 83 */     bookStack.getOrCreateChildTag("unlocked").put(fruit, (INBT)nbt);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\items\DFEncyclopediaItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */