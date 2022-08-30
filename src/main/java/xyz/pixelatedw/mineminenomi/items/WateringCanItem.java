/*    */ package xyz.pixelatedw.mineminenomi.items;
/*    */ 
/*    */ import java.util.List;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.client.util.ITooltipFlag;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.UseAction;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.ActionResultType;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WateringCanItem
/*    */   extends Item
/*    */ {
/*    */   public WateringCanItem() {
/* 27 */     super((new Item.Properties()).maxStackSize(1).group(ModCreativeTabs.MISC));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
/* 33 */     ItemStack itemStack = player.getHeldItem(hand);
/* 34 */     if (!itemStack.hasTag()) {
/* 35 */       itemStack.setTag(new CompoundNBT());
/*    */     }
/* 37 */     int currentTears = itemStack.getTag().getInt("tears");
/*    */     
/* 39 */     if (currentTears > 0)
/* 40 */       player.setActiveHand(hand); 
/* 41 */     return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack onItemUseFinish(ItemStack itemStack, World world, LivingEntity livingEntity) {
/* 47 */     if (!(livingEntity instanceof PlayerEntity)) {
/* 48 */       return itemStack;
/*    */     }
/* 50 */     int currentTears = itemStack.getTag().getInt("tears");
/*    */     
/* 52 */     livingEntity.heal(livingEntity.getHealth() / 10.0F);
/* 53 */     itemStack.getTag().putInt("tears", currentTears - 1);
/*    */     
/* 55 */     return itemStack;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public UseAction getUseAction(ItemStack stack) {
/* 61 */     return UseAction.DRINK;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getUseDuration(ItemStack stack) {
/* 67 */     return 32;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void addInformation(ItemStack itemStack, @Nullable World world, List<ITextComponent> list, ITooltipFlag par4) {
/* 73 */     if (!itemStack.hasTag()) {
/* 74 */       itemStack.setTag(new CompoundNBT());
/*    */     }
/* 76 */     int currentTears = itemStack.getTag().getInt("tears");
/* 77 */     list.add(new StringTextComponent("Tears: " + currentTears));
/*    */   }
/*    */ 
/*    */   
/*    */   public void addTears(ItemStack itemStack, LivingEntity entity) {
/* 82 */     if (!itemStack.hasTag()) {
/* 83 */       itemStack.setTag(new CompoundNBT());
/*    */     }
/* 85 */     int currentTears = itemStack.getTag().getInt("tears");
/*    */     
/* 87 */     itemStack.getTag().putInt("tears", currentTears + 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\items\WateringCanItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */