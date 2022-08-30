/*    */ package xyz.pixelatedw.mineminenomi.api.helpers.abilities;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ 
/*    */ public class KageHelper
/*    */ {
/* 14 */   public static final ITextComponent NOT_ENOUGH_SHADOWS_WARN = (ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_SHADOWS, new Object[0]);
/*    */ 
/*    */   
/*    */   public static void removeShadows(PlayerEntity player, int amount) {
/* 18 */     ArrayList<ItemStack> slots = new ArrayList<>();
/* 19 */     slots.addAll((Collection<? extends ItemStack>)player.inventory.mainInventory);
/* 20 */     slots.addAll((Collection<? extends ItemStack>)player.inventory.offHandInventory);
/* 21 */     for (ItemStack stack : slots) {
/*    */       
/* 23 */       if (amount <= 0)
/*    */         break; 
/* 25 */       if (stack.getItem().equals(ModItems.SHADOW)) {
/*    */         
/* 27 */         if (stack.getCount() >= amount) {
/*    */           
/* 29 */           stack.shrink(amount);
/*    */           
/*    */           break;
/*    */         } 
/*    */         
/* 34 */         amount -= stack.getCount();
/* 35 */         stack.shrink(amount);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static int getAvailableShadows(PlayerEntity player) {
/* 43 */     return player.inventory.count(ModItems.SHADOW);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\helpers\abilities\KageHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */