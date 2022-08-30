/*    */ package xyz.pixelatedw.mineminenomi.api;
/*    */ 
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ public class TradeEntry
/*    */ {
/*    */   private ItemStack itemStack;
/*    */   
/*    */   public TradeEntry(ItemStack itemStack) {
/* 11 */     this.itemStack = itemStack;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getItemStack() {
/* 16 */     return this.itemStack;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getCount() {
/* 21 */     return this.itemStack.getCount();
/*    */   }
/*    */ 
/*    */   
/*    */   public TradeEntry setCount(int count) {
/* 26 */     this.itemStack.setCount(count);
/* 27 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasInfiniteStock() {
/* 32 */     return this.itemStack.getOrCreateTag().getBoolean("isInfinite");
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPrice() {
/* 37 */     return this.itemStack.getOrCreateTag().getInt("price");
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\TradeEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */