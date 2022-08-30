/*    */ package xyz.pixelatedw.mineminenomi.api;
/*    */ 
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ 
/*    */ 
/*    */ public interface IFruitColor
/*    */ {
/*    */   default boolean hasBaseColor(ItemStack stack) {
/* 10 */     CompoundNBT compoundnbt = stack.getChildTag("display");
/* 11 */     return (compoundnbt != null && compoundnbt.contains("color", 99));
/*    */   }
/*    */ 
/*    */   
/*    */   default int getBaseColor(ItemStack stack) {
/* 16 */     CompoundNBT compoundnbt = stack.getChildTag("display");
/* 17 */     return (compoundnbt != null && compoundnbt.contains("color", 99)) ? compoundnbt.getInt("color") : -1;
/*    */   }
/*    */ 
/*    */   
/*    */   default void removeBaseColor(ItemStack stack) {
/* 22 */     CompoundNBT compoundnbt = stack.getChildTag("display");
/* 23 */     if (compoundnbt != null && compoundnbt.contains("color"))
/*    */     {
/* 25 */       compoundnbt.remove("color");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   default void setBaseColor(ItemStack stack, int color) {
/* 31 */     stack.getOrCreateChildTag("display").putInt("color", color);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   default boolean hasStemColor(ItemStack stack) {
/* 37 */     CompoundNBT compoundnbt = stack.getChildTag("display");
/* 38 */     return (compoundnbt != null && compoundnbt.contains("stem_color", 99));
/*    */   }
/*    */ 
/*    */   
/*    */   default int getStemColor(ItemStack stack) {
/* 43 */     CompoundNBT compoundnbt = stack.getChildTag("display");
/* 44 */     return (compoundnbt != null && compoundnbt.contains("stem_color", 99)) ? compoundnbt.getInt("stem_color") : 10511680;
/*    */   }
/*    */ 
/*    */   
/*    */   default void removeStemColor(ItemStack stack) {
/* 49 */     CompoundNBT compoundnbt = stack.getChildTag("display");
/* 50 */     if (compoundnbt != null && compoundnbt.contains("stem_color"))
/*    */     {
/* 52 */       compoundnbt.remove("stem_color");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   default void setStemColor(ItemStack stack, int color) {
/* 58 */     stack.getOrCreateChildTag("display").putInt("stem_color", color);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\IFruitColor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */