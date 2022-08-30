/*    */ package xyz.pixelatedw.mineminenomi.items;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.ActionResultType;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModFoods;
/*    */ 
/*    */ public class SeaKingMeatItem
/*    */   extends Item
/*    */ {
/*    */   public SeaKingMeatItem() {
/* 20 */     super((new Item.Properties()).group(ModCreativeTabs.MISC).food(ModFoods.SEA_KING_MEAT));
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
/*    */   public ItemStack onItemUseFinish(ItemStack itemStack, World world, LivingEntity entity) {
/* 33 */     if (!world.isRemote && entity instanceof PlayerEntity) {
/*    */       
/* 35 */       PlayerEntity player = (PlayerEntity)entity;
/* 36 */       player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 100, 0));
/*    */       
/* 38 */       player.heal(10.0F + player.getMaxHealth() / 10.0F);
/* 39 */       player.getFoodStats().consume(this, itemStack);
/*    */     } 
/*    */     
/* 42 */     itemStack.shrink(1);
/* 43 */     return itemStack;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\items\SeaKingMeatItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */