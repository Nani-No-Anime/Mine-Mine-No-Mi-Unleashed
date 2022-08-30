/*    */ package xyz.pixelatedw.mineminenomi.quests.objectives;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IObtainItemObjective;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ 
/*    */ public class ObtainItemObjective
/*    */   extends Objective
/*    */   implements IObtainItemObjective
/*    */ {
/*    */   private Predicate<ItemStack> check = itemStack -> false;
/*    */   
/*    */   public ObtainItemObjective(String title, int count, Item itemTarget) {
/* 17 */     super(title);
/* 18 */     setMaxProgress(count);
/* 19 */     this.check = (itemStack -> (itemStack.getItem() == itemTarget));
/*    */   }
/*    */ 
/*    */   
/*    */   public ObtainItemObjective(String title, int count, Predicate<ItemStack> check) {
/* 24 */     super(title);
/* 25 */     setMaxProgress(count);
/* 26 */     this.check = check;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean checkItem(ItemStack stack) {
/* 32 */     return this.check.test(stack);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int checkItems(ArrayList<ItemStack> stacks) {
/* 38 */     return stacks.stream().filter(this::checkItem).mapToInt(stack -> stack.getCount()).sum();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\quests\objectives\ObtainItemObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */