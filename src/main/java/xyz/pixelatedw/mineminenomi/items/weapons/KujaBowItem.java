/*    */ package xyz.pixelatedw.mineminenomi.items.weapons;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.enchantment.EnchantmentHelper;
/*    */ import net.minecraft.enchantment.Enchantments;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.BowItem;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.stats.Stats;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.SoundEvents;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.event.ForgeEventFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.KujaArrowProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ 
/*    */ public class KujaBowItem extends BowItem {
/*    */   public KujaBowItem(int maxDamage) {
/* 23 */     super((new Item.Properties()).group(ModCreativeTabs.WEAPONS).maxStackSize(1).defaultMaxDamage(maxDamage));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onPlayerStoppedUsing(ItemStack itemStack, World world, LivingEntity entityLiving, int timeLeft) {
/* 29 */     if (entityLiving instanceof PlayerEntity) {
/*    */       
/* 31 */       PlayerEntity player = (PlayerEntity)entityLiving;
/* 32 */       boolean flag = (player.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, itemStack) > 0);
/* 33 */       ItemStack arrowStack = player.findAmmo(itemStack);
/*    */       
/* 35 */       int i = getUseDuration(itemStack) - timeLeft;
/* 36 */       i = ForgeEventFactory.onArrowLoose(itemStack, world, player, i, (!arrowStack.isEmpty() || flag));
/* 37 */       if (i < 0) {
/*    */         return;
/*    */       }
/* 40 */       if (!arrowStack.isEmpty() || flag) {
/*    */         
/* 42 */         if (arrowStack.isEmpty()) {
/* 43 */           arrowStack = new ItemStack((IItemProvider)ModItems.KUJA_ARROW);
/*    */         }
/* 45 */         float f = getArrowVelocity(i);
/* 46 */         if (f > 0.4F) {
/*    */           
/* 48 */           if (!world.isRemote) {
/*    */             
/* 50 */             KujaArrowProjectile proj = new KujaArrowProjectile(world, entityLiving);
/* 51 */             proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, f * 3.0F, 1.0F);
/*    */             
/* 53 */             if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, itemStack) > 0) {
/* 54 */               proj.setFire(3);
/*    */             }
/* 56 */             world.addEntity((Entity)proj);
/* 57 */             arrowStack.shrink(1);
/* 58 */             player.addStat(Stats.ITEM_USED.get(this));
/* 59 */             itemStack.damageItem(1, (LivingEntity)player, user -> user.sendBreakAnimation(player.getActiveHand()));
/*    */           } 
/*    */ 
/*    */ 
/*    */ 
/*    */           
/* 65 */           world.playSound((PlayerEntity)null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Predicate<ItemStack> getInventoryAmmoPredicate() {
/* 74 */     return itemStack -> (itemStack.getItem() == ModItems.KUJA_ARROW);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\items\weapons\KujaBowItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */