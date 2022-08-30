/*     */ package xyz.pixelatedw.mineminenomi.datagen;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.data.DataGenerator;
/*     */ import net.minecraft.data.DirectoryCache;
/*     */ import net.minecraft.enchantment.Enchantments;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.storage.loot.ConstantRange;
/*     */ import net.minecraft.world.storage.loot.IRandomRange;
/*     */ import net.minecraft.world.storage.loot.ItemLootEntry;
/*     */ import net.minecraft.world.storage.loot.LootEntry;
/*     */ import net.minecraft.world.storage.loot.LootPool;
/*     */ import net.minecraft.world.storage.loot.LootTable;
/*     */ import net.minecraft.world.storage.loot.RandomValueRange;
/*     */ import net.minecraft.world.storage.loot.functions.ApplyBonus;
/*     */ import net.minecraft.world.storage.loot.functions.ILootFunction;
/*     */ import net.minecraft.world.storage.loot.functions.SetCount;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.datagen.LootTablesDataGen;
/*     */ 
/*     */ public class ModBlocksLootTablesDataGen extends LootTablesDataGen {
/*  26 */   private final Map<Block, LootTable.Builder> lootTables = new HashMap<>();
/*     */ 
/*     */   
/*     */   public ModBlocksLootTablesDataGen(DataGenerator dataGenerator) {
/*  30 */     super(dataGenerator);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void act(DirectoryCache cache) {
/*  37 */     addLootTable(ModBlocks.KAIROSEKI_ORE, new LootPool.Builder[] { createOreLootPool(ModItems.KAIROSEKI, 1, 1, 3.0F, 6.0F) });
/*     */ 
/*     */     
/*  40 */     addLootTable(ModBlocks.AXE_DIAL, new LootPool.Builder[] { createSelfDropLootPool(ModBlocks.AXE_DIAL) });
/*  41 */     addLootTable(ModBlocks.BREATH_DIAL, new LootPool.Builder[] { createSelfDropLootPool(ModBlocks.BREATH_DIAL) });
/*  42 */     addLootTable(ModBlocks.FLAME_DIAL, new LootPool.Builder[] { createSelfDropLootPool(ModBlocks.FLAME_DIAL) });
/*  43 */     addLootTable(ModBlocks.REJECT_DIAL, new LootPool.Builder[] { createSelfDropLootPool(ModBlocks.REJECT_DIAL) });
/*  44 */     addLootTable(ModBlocks.IMPACT_DIAL, new LootPool.Builder[] { createSelfDropLootPool(ModBlocks.IMPACT_DIAL) });
/*  45 */     addLootTable(ModBlocks.FLASH_DIAL, new LootPool.Builder[] { createSelfDropLootPool(ModBlocks.FLASH_DIAL) });
/*  46 */     addLootTable(ModBlocks.EISEN_DIAL, new LootPool.Builder[] { createSelfDropLootPool(ModBlocks.EISEN_DIAL) });
/*  47 */     addLootTable(ModBlocks.MILKY_DIAL, new LootPool.Builder[] { createSelfDropLootPool(ModBlocks.MILKY_DIAL) });
/*  48 */     addLootTable(ModBlocks.DEN_DEN_MUSHI, new LootPool.Builder[] { createSelfDropLootPool(ModBlocks.DEN_DEN_MUSHI) });
/*  49 */     addLootTable(ModBlocks.CANNON, new LootPool.Builder[] { createSelfDropLootPool(ModBlocks.CANNON) });
/*  50 */     addLootTable(ModBlocks.KAIROSEKI, new LootPool.Builder[] { createSelfDropLootPool(ModBlocks.KAIROSEKI) });
/*  51 */     addLootTable(ModBlocks.KAIROSEKI_BARS, new LootPool.Builder[] { createSelfDropLootPool(ModBlocks.KAIROSEKI_BARS) });
/*     */     
/*  53 */     Map<ResourceLocation, LootTable> tables = new HashMap<>();
/*  54 */     this.lootTables.forEach((block, builder) -> tables.put(block.getLootTable(), builder.build()));
/*     */ 
/*     */ 
/*     */     
/*  58 */     writeTables(cache, tables);
/*     */   }
/*     */ 
/*     */   
/*     */   protected LootPool.Builder createBasicLootPool(Item drop, int roll, float count) {
/*  63 */     return createBasicLootPool(drop, roll, roll, count, count);
/*     */   }
/*     */   
/*     */   protected LootPool.Builder createBasicLootPool(Item drop, int rollMin, int rollMax, float countMin, float countMax) {
/*     */     IRandomRange randomRange1 = null;
/*     */     IRandomRange randomRange2 = null;
/*  69 */     if (rollMin == rollMax) {
/*  70 */       randomRange1 = ConstantRange.of(rollMin);
/*     */     } else {
/*  72 */       randomRange1 = RandomValueRange.of(rollMin, rollMax);
/*     */     } 
/*     */     
/*  75 */     if (countMin == countMax) {
/*  76 */       randomRange2 = ConstantRange.of((int)countMin);
/*     */     } else {
/*  78 */       randomRange2 = RandomValueRange.of(countMin, countMax);
/*     */     } 
/*  80 */     return LootPool.builder().rolls((IRandomRange)randomRange1)
/*  81 */       .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)drop)
/*  82 */         .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)randomRange2)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected LootPool.Builder createSelfDropLootPool(Block block) {
/*  87 */     return LootPool.builder().rolls((IRandomRange)ConstantRange.of(1))
/*  88 */       .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)block.asItem())
/*  89 */         .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)ConstantRange.of(1))));
/*     */   }
/*     */ 
/*     */   
/*     */   protected LootPool.Builder createOreLootPool(Item ore, int roll, float count) {
/*  94 */     return createOreLootPool(ore, roll, roll, count, count);
/*     */   }
/*     */   
/*     */   protected LootPool.Builder createOreLootPool(Item ore, int rollMin, int rollMax, float countMin, float countMax) {
/*     */     IRandomRange randomRange1 = null;
/*     */     IRandomRange randomRange2 = null;
/* 100 */     if (rollMin == rollMax) {
/* 101 */       randomRange1 = ConstantRange.of(rollMin);
/*     */     } else {
/* 103 */       randomRange1 = RandomValueRange.of(rollMin, rollMax);
/*     */     } 
/*     */     
/* 106 */     if (countMin == countMax) {
/* 107 */       randomRange2 = ConstantRange.of((int)countMin);
/*     */     } else {
/* 109 */       randomRange2 = RandomValueRange.of(countMin, countMax);
/*     */     } 
/* 111 */     return LootPool.builder().rolls((IRandomRange)randomRange1)
/* 112 */       .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ore)
/* 113 */         .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)randomRange2))
/* 114 */         .acceptFunction((ILootFunction.IBuilder)ApplyBonus.oreDrops(Enchantments.FORTUNE)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addLootTable(Block block, LootPool.Builder... pools) {
/* 119 */     LootTable.Builder builder = LootTable.builder();
/* 120 */     for (LootPool.Builder pool : pools)
/*     */     {
/* 122 */       builder.addLootPool(pool);
/*     */     }
/* 124 */     this.lootTables.put(block, builder);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 130 */     return "Blocks Loot Pools";
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\datagen\ModBlocksLootTablesDataGen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */