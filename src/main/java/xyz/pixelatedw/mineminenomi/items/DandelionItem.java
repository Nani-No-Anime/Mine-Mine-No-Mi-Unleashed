/*    */ package xyz.pixelatedw.mineminenomi.items;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Foods;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.UseAction;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.ActionResultType;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ 
/*    */ public class DandelionItem
/*    */   extends Item
/*    */ {
/*    */   public DandelionItem() {
/* 20 */     super((new Item.Properties()).group(ModCreativeTabs.MISC).food(Foods.DRIED_KELP));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
/* 26 */     player.setActiveHand(hand);
/* 27 */     return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack onItemUseFinish(ItemStack itemStack, World world, LivingEntity entity) {
/* 34 */     if (!world.isRemote && entity instanceof PlayerEntity) {
/*    */       
/* 36 */       ((PlayerEntity)entity).getCooldownTracker().setCooldown(getItem(), 600);
/* 37 */       entity.resetActiveHand();
/* 38 */       entity.world.setEntityState((Entity)entity, (byte)30);
/* 39 */       entity.heal(15.0F + entity.getMaxHealth() / 5.0F);
/* 40 */       itemStack.shrink(1);
/*    */     } 
/*    */     
/* 43 */     return itemStack;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public UseAction getUseAction(ItemStack stack) {
/* 50 */     return UseAction.EAT;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\items\DandelionItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */