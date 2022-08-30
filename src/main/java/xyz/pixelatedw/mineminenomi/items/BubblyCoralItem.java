/*    */ package xyz.pixelatedw.mineminenomi.items;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.ActionResultType;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class BubblyCoralItem
/*    */   extends Item {
/*    */   public BubblyCoralItem() {
/* 19 */     super((new Item.Properties()).group(ModCreativeTabs.MISC).defaultMaxDamage(3));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
/* 25 */     ItemStack itemStack = player.getHeldItem(hand);
/*    */     
/* 27 */     if (player.isPotionActive(ModEffects.BUBBLY_CORAL)) {
/* 28 */       return new ActionResult(ActionResultType.PASS, player.getHeldItem(hand));
/*    */     }
/* 30 */     player.addPotionEffect(new EffectInstance(ModEffects.BUBBLY_CORAL, 3600, 0));
/* 31 */     itemStack.damageItem(1, (LivingEntity)player, user -> user.sendBreakAnimation(EquipmentSlotType.MAINHAND));
/*    */     
/* 33 */     return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
/* 38 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\items\BubblyCoralItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */