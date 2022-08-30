/*     */ package xyz.pixelatedw.mineminenomi.events;
/*     */ 
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.enchantment.Enchantments;
/*     */ import net.minecraftforge.event.AnvilUpdateEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEnchantments;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class CraftingEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void onAnvilUpdate(AnvilUpdateEvent event) {
/*  20 */     if (ItemsHelper.isBow(event.getLeft())) {
/*     */       
/*  22 */       if (event.getRight().getItem() == ModBlocks.FLAME_DIAL.asItem() && event.getRight().getCount() >= 3 && 1 > EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, event.getLeft()))
/*     */       {
/*  24 */         event.setCost(1);
/*  25 */         event.setMaterialCost(3);
/*  26 */         event.setOutput(event.getLeft().copy());
/*  27 */         EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(event.getLeft()), event.getOutput());
/*  28 */         event.getOutput().addEnchantment(Enchantments.FLAME, 1);
/*     */       }
/*  30 */       else if (event.getRight().getItem() == ModBlocks.BREATH_DIAL.asItem() && event.getRight().getCount() >= 3 && 1 > EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, event.getLeft()))
/*     */       {
/*  32 */         event.setCost(1);
/*  33 */         event.setMaterialCost(3);
/*  34 */         event.setOutput(event.getLeft().copy());
/*  35 */         EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(event.getLeft()), event.getOutput());
/*  36 */         event.getOutput().addEnchantment(Enchantments.PUNCH, 1);
/*     */       }
/*  38 */       else if (event.getRight().getItem() == ModBlocks.EISEN_DIAL.asItem() && event.getRight().getCount() >= 3 && 1 > EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, event.getLeft()))
/*     */       {
/*  40 */         int level = event.getRight().getCount() / 3;
/*     */         
/*  42 */         if (level > 3) {
/*  43 */           level = 3;
/*     */         }
/*  45 */         event.setCost(1);
/*  46 */         event.setMaterialCost(3 * level);
/*  47 */         event.setOutput(event.getLeft().copy());
/*  48 */         EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(event.getLeft()), event.getOutput());
/*  49 */         event.getOutput().addEnchantment(Enchantments.POWER, level);
/*     */       }
/*     */     
/*  52 */     } else if (ItemsHelper.isSword(event.getLeft())) {
/*     */       
/*  54 */       if (event.getRight().getItem() == ModItems.KAIROSEKI && event.getRight().getCount() >= 10 && 1 > EnchantmentHelper.getEnchantmentLevel(ModEnchantments.KAIROSEKI, event.getLeft())) {
/*     */         
/*  56 */         event.setCost(1);
/*  57 */         event.setMaterialCost(10);
/*  58 */         event.setOutput(event.getLeft().copy());
/*  59 */         EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(event.getLeft()), event.getOutput());
/*  60 */         event.getOutput().addEnchantment(ModEnchantments.KAIROSEKI, 1);
/*     */       }
/*  62 */       else if (event.getRight().getItem() == ModBlocks.FLAME_DIAL.asItem() && event.getRight().getCount() >= 3 && 1 > EnchantmentHelper.getEnchantmentLevel(Enchantments.FIRE_ASPECT, event.getLeft())) {
/*     */         
/*  64 */         event.setCost(1);
/*  65 */         event.setMaterialCost(3);
/*  66 */         event.setOutput(event.getLeft().copy());
/*  67 */         EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(event.getLeft()), event.getOutput());
/*  68 */         event.getOutput().addEnchantment(Enchantments.FIRE_ASPECT, 1);
/*     */       }
/*  70 */       else if (event.getRight().getItem() == ModBlocks.EISEN_DIAL.asItem() && event.getRight().getCount() >= 3 && 1 > EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, event.getLeft())) {
/*     */         
/*  72 */         int level = event.getRight().getCount() / 3;
/*     */         
/*  74 */         if (level > 3) {
/*  75 */           level = 3;
/*     */         }
/*  77 */         event.setCost(1);
/*  78 */         event.setMaterialCost(3 * level);
/*  79 */         event.setOutput(event.getLeft().copy());
/*  80 */         EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(event.getLeft()), event.getOutput());
/*  81 */         event.getOutput().addEnchantment(Enchantments.SHARPNESS, level);
/*     */       }
/*  83 */       else if (event.getRight().getItem() == ModBlocks.FLASH_DIAL.asItem() && event.getRight().getCount() >= 3 && 1 > EnchantmentHelper.getEnchantmentLevel(ModEnchantments.DIAL_FLASH, event.getLeft())) {
/*     */         
/*  85 */         event.setCost(1);
/*  86 */         event.setMaterialCost(3);
/*  87 */         event.setOutput(event.getLeft().copy());
/*  88 */         EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(event.getLeft()), event.getOutput());
/*  89 */         event.getOutput().addEnchantment(ModEnchantments.DIAL_FLASH, 1);
/*     */       }
/*  91 */       else if (event.getRight().getItem() == ModBlocks.IMPACT_DIAL.asItem() && event.getRight().getCount() >= 5 && 1 > EnchantmentHelper.getEnchantmentLevel(ModEnchantments.DIAL_IMPACT, event.getLeft())) {
/*     */         
/*  93 */         int level = event.getRight().getCount() / 5;
/*     */         
/*  95 */         if (level > 2) {
/*  96 */           level = 2;
/*     */         }
/*  98 */         event.setCost(1);
/*  99 */         event.setMaterialCost(5 * level);
/* 100 */         event.setOutput(event.getLeft().copy());
/* 101 */         EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(event.getLeft()), event.getOutput());
/* 102 */         event.getOutput().addEnchantment(ModEnchantments.DIAL_IMPACT, level);
/*     */       }
/* 104 */       else if (event.getRight().getItem() == ModBlocks.BREATH_DIAL.asItem() && event.getRight().getCount() >= 3 && 1 > EnchantmentHelper.getEnchantmentLevel(Enchantments.KNOCKBACK, event.getLeft())) {
/*     */         
/* 106 */         int level = event.getRight().getCount() / 3;
/*     */         
/* 108 */         if (level > 3) {
/* 109 */           level = 3;
/*     */         }
/* 111 */         event.setCost(1);
/* 112 */         event.setMaterialCost(3 * level);
/* 113 */         event.setOutput(event.getLeft().copy());
/* 114 */         EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(event.getLeft()), event.getOutput());
/* 115 */         event.getOutput().addEnchantment(Enchantments.KNOCKBACK, level);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\CraftingEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */