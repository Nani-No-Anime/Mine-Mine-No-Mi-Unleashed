/*    */ package xyz.pixelatedw.mineminenomi.items;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.UseAction;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.ActionResultType;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModFoods;
/*    */ 
/*    */ public class BottleOfRumItem
/*    */   extends Item
/*    */ {
/*    */   public BottleOfRumItem() {
/* 22 */     super((new Item.Properties()).group(ModCreativeTabs.MISC).defaultMaxDamage(10).food(ModFoods.ALCOHOL));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
/* 28 */     player.setActiveHand(hand);
/* 29 */     return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
/* 34 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack onItemUseFinish(ItemStack itemStack, World world, LivingEntity entity) {
/* 40 */     if (!world.isRemote && entity instanceof PlayerEntity) {
/*    */       
/* 42 */       PlayerEntity player = (PlayerEntity)entity;
/*    */       
/* 44 */       if (entity.isPotionActive(ModEffects.DRUNK)) {
/*    */         
/* 46 */         EffectInstance effect = entity.getActivePotionEffect(ModEffects.DRUNK);
/*    */         
/* 48 */         int amp = effect.getAmplifier();
/* 49 */         int duration = effect.getDuration();
/*    */         
/* 51 */         if (amp < 4) {
/* 52 */           amp++;
/*    */         }
/* 54 */         entity.removePotionEffect(ModEffects.DRUNK);
/* 55 */         entity.addPotionEffect(new EffectInstance(ModEffects.DRUNK, duration + 200, amp));
/*    */       }
/*    */       else {
/*    */         
/* 59 */         entity.addPotionEffect(new EffectInstance(ModEffects.DRUNK, 400, 0));
/*    */       } 
/*    */       
/* 62 */       if (!player.isCreative()) {
/* 63 */         itemStack.damageItem(1, entity, user -> user.sendBreakAnimation(EquipmentSlotType.MAINHAND));
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 68 */     return itemStack;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public UseAction getUseAction(ItemStack stack) {
/* 74 */     return UseAction.DRINK;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\items\BottleOfRumItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */