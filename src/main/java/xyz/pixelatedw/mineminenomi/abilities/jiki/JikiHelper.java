/*     */ package xyz.pixelatedw.mineminenomi.abilities.jiki;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.item.ItemEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class JikiHelper
/*     */ {
/*     */   public static List<ItemStack> getMagneticItems(PlayerEntity player, int maxCount) {
/*  20 */     int count = 0;
/*  21 */     List<ItemStack> inventory = new ArrayList<>();
/*  22 */     List<ItemStack> list = new ArrayList<>();
/*  23 */     inventory.addAll((Collection<? extends ItemStack>)player.inventory.mainInventory);
/*  24 */     inventory.addAll((Collection<? extends ItemStack>)player.inventory.offHandInventory);
/*  25 */     inventory.addAll((Collection<? extends ItemStack>)player.inventory.armorInventory);
/*  26 */     for (ItemStack stack : inventory) {
/*     */       
/*  28 */       if (maxCount > 0 && count >= maxCount) {
/*     */         break;
/*     */       }
/*  31 */       if (stack == null) {
/*     */         continue;
/*     */       }
/*  34 */       if (!stack.isEmpty() && stack.getItem().isIn(ModTags.Items.MAGNETIC)) {
/*     */         
/*  36 */         list.add(stack);
/*  37 */         count += stack.getCount();
/*     */       } 
/*     */     } 
/*     */     
/*  41 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<ItemStack> getMagneticItemsStack(PlayerEntity player, List<ItemStack> items, int countPerStack) {
/*  47 */     List<ItemStack> components = new ArrayList<>();
/*  48 */     int count = 0;
/*  49 */     for (ItemStack stack : items) {
/*     */       
/*  51 */       if (count >= countPerStack) {
/*     */         break;
/*     */       }
/*  54 */       ItemStack clone = ItemStack.read(stack.write(new CompoundNBT()));
/*     */       
/*  56 */       if (stack.isDamageable()) {
/*     */         
/*  58 */         components.add(clone);
/*  59 */         stack.shrink(1);
/*  60 */         count++;
/*     */         
/*     */         continue;
/*     */       } 
/*  64 */       int needed = countPerStack - count;
/*  65 */       if (stack.getCount() < needed) {
/*     */         
/*  67 */         components.add(clone);
/*  68 */         stack.shrink(stack.getCount());
/*  69 */         count += clone.getCount();
/*     */         
/*     */         continue;
/*     */       } 
/*  73 */       ItemStack split = stack.split(needed);
/*  74 */       components.add(split);
/*  75 */       count += split.getCount();
/*     */     } 
/*     */ 
/*     */     
/*  79 */     return components;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int countMagneticItems(List<ItemStack> list) {
/*  85 */     int count = 0;
/*  86 */     for (ItemStack stack : list) {
/*     */       
/*  88 */       if (stack == null) {
/*     */         continue;
/*     */       }
/*  91 */       if (!stack.isEmpty() && stack.getItem().isIn(ModTags.Items.MAGNETIC)) {
/*  92 */         count += stack.getCount();
/*     */       }
/*     */     } 
/*  95 */     return count;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void dropComponentItems(PlayerEntity player, BlockPos pos, List<ItemStack> list) {
/* 100 */     damageMagneticItems(player, list);
/* 101 */     for (ItemStack stack : list) {
/*     */       
/* 103 */       ItemEntity item = new ItemEntity(player.world, pos.getX(), (pos.getY() + 0.4F), pos.getZ(), stack);
/* 104 */       item.setPickupDelay(20);
/* 105 */       player.world.addEntity((Entity)item);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void damageMagneticItems(PlayerEntity player, List<ItemStack> list) {
/* 111 */     for (ItemStack stack : list) {
/*     */       
/* 113 */       if (stack == null || stack.isEmpty()) {
/*     */         continue;
/*     */       }
/* 116 */       boolean chance = (player.getRNG().nextInt(10) < 1);
/* 117 */       if (!chance) {
/*     */         continue;
/*     */       }
/* 120 */       if (stack.isDamageable()) {
/*     */         
/* 122 */         int maxDamage = stack.getMaxDamage();
/* 123 */         int damage = (int)WyHelper.clamp(player.getRNG().nextInt(maxDamage), 50L, maxDamage);
/* 124 */         stack.damageItem(damage, (LivingEntity)player, user -> user.sendBreakAnimation(EquipmentSlotType.MAINHAND));
/*     */         
/*     */         continue;
/*     */       } 
/* 128 */       int maxCount = stack.getCount();
/* 129 */       int destroy = (int)WyHelper.clamp(player.getRNG().nextInt(maxCount), 1L, maxCount);
/* 130 */       stack.shrink(destroy);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\jiki\JikiHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */