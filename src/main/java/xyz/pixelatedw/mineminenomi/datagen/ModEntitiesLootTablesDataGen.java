/*     */ package xyz.pixelatedw.mineminenomi.datagen;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.data.DataGenerator;
/*     */ import net.minecraft.data.DirectoryCache;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.storage.loot.ConstantRange;
/*     */ import net.minecraft.world.storage.loot.IRandomRange;
/*     */ import net.minecraft.world.storage.loot.ItemLootEntry;
/*     */ import net.minecraft.world.storage.loot.LootEntry;
/*     */ import net.minecraft.world.storage.loot.LootPool;
/*     */ import net.minecraft.world.storage.loot.LootTable;
/*     */ import net.minecraft.world.storage.loot.RandomValueRange;
/*     */ import net.minecraft.world.storage.loot.functions.ILootFunction;
/*     */ import net.minecraft.world.storage.loot.functions.SetCount;
/*     */ import xyz.pixelatedw.mineminenomi.data.functions.SetBellyInPouchFunction;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.datagen.LootTablesDataGen;
/*     */ 
/*     */ public class ModEntitiesLootTablesDataGen extends LootTablesDataGen {
/*  24 */   private final Map<EntityType, LootTable.Builder> lootTables = new HashMap<>();
/*     */ 
/*     */   
/*     */   public ModEntitiesLootTablesDataGen(DataGenerator dataGenerator) {
/*  28 */     super(dataGenerator);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void act(DirectoryCache cache) {
/*  35 */     addLootTable(ModEntities.KUNG_FU_DUGONG, new LootPool.Builder[] {
/*  36 */           LootPool.builder().rolls((IRandomRange)ConstantRange.of(1))
/*  37 */           .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)Items.SCUTE)
/*  38 */             .weight(80)
/*  39 */             .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(0.0F, 3.0F))))
/*  40 */           .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)Items.SEAGRASS)
/*  41 */             .weight(100)
/*  42 */             .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 5.0F))))
/*  43 */           .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)Items.KELP)
/*  44 */             .weight(100)
/*  45 */             .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(0.0F, 5.0F))))
/*     */         });
/*  47 */     addLootTable(ModEntities.LAPAHN, new LootPool.Builder[] {
/*  48 */           LootPool.builder().rolls((IRandomRange)ConstantRange.of(1))
/*  49 */           .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)Items.SNOWBALL)
/*  50 */             .weight(100)
/*  51 */             .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(0.0F, 5.0F))))
/*  52 */           .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)Items.RABBIT_HIDE)
/*  53 */             .weight(80)
/*  54 */             .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 2.0F))))
/*  55 */           .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)Items.RABBIT)
/*  56 */             .weight(80)
/*  57 */             .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(0.0F, 3.0F))))
/*  58 */           .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)Items.RABBIT_FOOT)
/*  59 */             .weight(20)
/*  60 */             .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(0.0F, 2.0F))))
/*     */         });
/*  62 */     addLootTable(ModEntities.MARINE_TRADER, new LootPool.Builder[] {
/*  63 */           LootPool.builder().rolls((IRandomRange)RandomValueRange.of(0.0F, 1.0F))
/*  64 */           .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.BELLY_POUCH)
/*  65 */             .weight(100)
/*  66 */             .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)ConstantRange.of(1)))
/*  67 */             .acceptFunction((ILootFunction.IBuilder)SetBellyInPouchFunction.builder((IRandomRange)RandomValueRange.of(5.0F, 50.0F))))
/*     */         });
/*  69 */     addLootTable(ModEntities.PIRATE_TRADER, new LootPool.Builder[] {
/*  70 */           LootPool.builder().rolls((IRandomRange)RandomValueRange.of(0.0F, 1.0F))
/*  71 */           .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.BELLY_POUCH)
/*  72 */             .weight(100)
/*  73 */             .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)ConstantRange.of(1)))
/*  74 */             .acceptFunction((ILootFunction.IBuilder)SetBellyInPouchFunction.builder((IRandomRange)RandomValueRange.of(5.0F, 50.0F))))
/*     */         });
/*  76 */     addLootTable(ModEntities.YAGARA_BULL, new LootPool.Builder[] {
/*  77 */           LootPool.builder().rolls((IRandomRange)ConstantRange.of(1))
/*  78 */           .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)Items.SEAGRASS)
/*  79 */             .weight(100)
/*  80 */             .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 5.0F))))
/*  81 */           .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)Items.KELP)
/*  82 */             .weight(100)
/*  83 */             .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(0.0F, 5.0F))))
/*     */         });
/*  85 */     Map<ResourceLocation, LootTable> tables = new HashMap<>();
/*  86 */     this.lootTables.forEach((entityType, builder) -> tables.put(entityType.getLootTable(), builder.build()));
/*     */ 
/*     */ 
/*     */     
/*  90 */     writeTables(cache, tables);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addLootTable(EntityType entityType, LootPool.Builder... pools) {
/*  95 */     LootTable.Builder builder = LootTable.builder();
/*  96 */     for (LootPool.Builder pool : pools)
/*     */     {
/*  98 */       builder.addLootPool(pool);
/*     */     }
/* 100 */     this.lootTables.put(entityType, builder);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 106 */     return "Entities Loot Pools";
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\datagen\ModEntitiesLootTablesDataGen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */