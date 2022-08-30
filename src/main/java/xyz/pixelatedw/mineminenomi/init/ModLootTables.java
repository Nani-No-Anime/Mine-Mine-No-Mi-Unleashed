/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ 
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.storage.loot.IRandomRange;
/*     */ import net.minecraft.world.storage.loot.ItemLootEntry;
/*     */ import net.minecraft.world.storage.loot.LootEntry;
/*     */ import net.minecraft.world.storage.loot.LootPool;
/*     */ import net.minecraft.world.storage.loot.LootTables;
/*     */ import net.minecraft.world.storage.loot.RandomValueRange;
/*     */ import net.minecraft.world.storage.loot.StandaloneLootEntry;
/*     */ import net.minecraft.world.storage.loot.TableLootEntry;
/*     */ import net.minecraftforge.event.LootTableLoadEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class ModLootTables {
/*  19 */   public static final ResourceLocation SMALL_PIRATE_SHIP_SUPPLIES_CHEST = new ResourceLocation("mineminenomi", "chests/small_pirate_ship/supplies");
/*     */   
/*  21 */   public static final ResourceLocation MEDIUM_PIRATE_SHIP_SUPPLIES_CHEST = new ResourceLocation("mineminenomi", "chests/medium_pirate_ship/supplies");
/*  22 */   public static final ResourceLocation MEDIUM_PIRATE_SHIP_FOOD_CHEST = new ResourceLocation("mineminenomi", "chests/medium_pirate_ship/food");
/*  23 */   public static final ResourceLocation MEDIUM_PIRATE_SHIP_CAPTAIN_CHEST = new ResourceLocation("mineminenomi", "chests/medium_pirate_ship/captain");
/*     */   
/*  25 */   public static final ResourceLocation LARGE_PIRATE_SHIP_SUPPLIES_CHEST = new ResourceLocation("mineminenomi", "chests/large_pirate_ship/supplies");
/*  26 */   public static final ResourceLocation LARGE_PIRATE_SHIP_WEAPONS_CHEST = new ResourceLocation("mineminenomi", "chests/large_pirate_ship/weapons");
/*  27 */   public static final ResourceLocation LARGE_PIRATE_SHIP_CAPTAIN_CHEST = new ResourceLocation("mineminenomi", "chests/large_pirate_ship/captain");
/*  28 */   public static final ResourceLocation LARGE_PIRATE_SHIP_TREASURE_CHEST = new ResourceLocation("mineminenomi", "chests/large_pirate_ship/treasure");
/*     */   
/*  30 */   public static final ResourceLocation GHOST_SHIP_SUPPLIES_CHEST = new ResourceLocation("mineminenomi", "chests/ghost_ship/supplies");
/*  31 */   public static final ResourceLocation GHOST_SHIP_CAPTAIN_CHEST = new ResourceLocation("mineminenomi", "chests/ghost_ship/captain");
/*     */   
/*  33 */   public static final ResourceLocation CAMP_SMALL_TENT_CHEST = new ResourceLocation("mineminenomi", "chests/camp/small_tent");
/*  34 */   public static final ResourceLocation CAMP_LARGE_TENT_CHEST = new ResourceLocation("mineminenomi", "chests/camp/large_tent");
/*     */   
/*  36 */   public static final ResourceLocation SMALL_BANDIT_BASE_GOLD_CHEST = new ResourceLocation("mineminenomi", "chests/small_bandit_base/gold");
/*  37 */   public static final ResourceLocation SMALL_BANDIT_BASE_MINE_CHEST = new ResourceLocation("mineminenomi", "chests/small_bandit_base/mine");
/*  38 */   public static final ResourceLocation SMALL_BANDIT_BASE_LAB_CHEST = new ResourceLocation("mineminenomi", "chests/small_bandit_base/lab");
/*  39 */   public static final ResourceLocation SMALL_BANDIT_BASE_ORES_CHEST = new ResourceLocation("mineminenomi", "chests/small_bandit_base/ores");
/*     */   
/*  41 */   public static final ResourceLocation SMALL_MARINE_BASE_CAPTAIN_CHEST = new ResourceLocation("mineminenomi", "chests/small_marine_base/captain");
/*  42 */   public static final ResourceLocation SMALL_MARINE_BASE_FOOD_CHEST = new ResourceLocation("mineminenomi", "chests/small_marine_base/food");
/*  43 */   public static final ResourceLocation SMALL_MARINE_BASE_DORM_CHEST = new ResourceLocation("mineminenomi", "chests/small_marine_base/dorm");
/*     */   
/*  45 */   public static final ResourceLocation LARGE_MARINE_BASE_CAPTAIN_CHEST = new ResourceLocation("mineminenomi", "chests/large_marine_base/captain");
/*  46 */   public static final ResourceLocation LARGE_MARINE_BASE_FOOD_CHEST = new ResourceLocation("mineminenomi", "chests/large_marine_base/food");
/*  47 */   public static final ResourceLocation LARGE_MARINE_BASE_GENERIC_CHEST = new ResourceLocation("mineminenomi", "chests/large_marine_base/generic");
/*  48 */   public static final ResourceLocation LARGE_MARINE_BASE_LAB_CHEST = new ResourceLocation("mineminenomi", "chests/large_marine_base/lab");
/*     */   
/*  50 */   public static final ResourceLocation LARGE_MARINE_SHIP_CAPTAIN_CHEST = new ResourceLocation("mineminenomi", "chests/large_marine_ship/captain");
/*  51 */   public static final ResourceLocation LARGE_MARINE_SHIP_SUPPLIES_CHEST = new ResourceLocation("mineminenomi", "chests/large_marine_ship/supplies");
/*     */   
/*  53 */   public static final ResourceLocation LARGE_BANDIT_BASE_TOWER_CHEST = new ResourceLocation("mineminenomi", "chests/large_bandit_base/tower");
/*  54 */   public static final ResourceLocation LARGE_BANDIT_BASE_TENT_CHEST = new ResourceLocation("mineminenomi", "chests/large_bandit_base/tent");
/*  55 */   public static final ResourceLocation LARGE_BANDIT_BASE_FOOD_CHEST = new ResourceLocation("mineminenomi", "chests/large_bandit_base/food");
/*  56 */   public static final ResourceLocation LARGE_BANDIT_BASE_DORM_CHEST = new ResourceLocation("mineminenomi", "chests/large_bandit_base/dorm");
/*  57 */   public static final ResourceLocation LARGE_BANDIT_BASE_SECRET_STASH_CHEST = new ResourceLocation("mineminenomi", "chests/large_bandit_base/secret_stash");
/*     */ 
/*     */   
/*  60 */   public static final ResourceLocation SKYPIEAN_TRADER = new ResourceLocation("mineminenomi", "entities/trader/skypiean_trader");
/*  61 */   public static final ResourceLocation PIRATE_TRADER = new ResourceLocation("mineminenomi", "entities/trader/pirate_trader");
/*  62 */   public static final ResourceLocation MARINE_TRADER = new ResourceLocation("mineminenomi", "entities/trader/marine_trader");
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onVanillaLootLoading(LootTableLoadEvent event) {
/*  67 */     if (event.getName().toString().equals("minecraft:blocks/brain_coral_block")) {
/*     */       
/*  69 */       StandaloneLootEntry.Builder lootEntry = TableLootEntry.builder(new ResourceLocation("mineminenomi", "blocks/inject/brain_coral_block"));
/*  70 */       event.getTable().removePool("main");
/*  71 */       event.getTable().addPool(LootPool.builder().addEntry((LootEntry.Builder)lootEntry).build());
/*     */     } 
/*     */     
/*  74 */     if (event.getName().equals(LootTables.CHESTS_UNDERWATER_RUIN_BIG) || event.getName().equals(LootTables.CHESTS_UNDERWATER_RUIN_SMALL)) {
/*     */       
/*  76 */       LootPool fruit_boxes = constructLootPool("fruit_boxes", -7.0F, 1.0F, (LootEntry.Builder<?>[])new LootEntry.Builder[] {
/*  77 */             (LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.TIER_1_BOX).weight(9), 
/*  78 */             (LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.TIER_2_BOX).weight(1) });
/*  79 */       event.getTable().addPool(fruit_boxes);
/*     */     } 
/*     */     
/*  82 */     if (event.getName().equals(LootTables.CHESTS_BURIED_TREASURE) || event.getName().equals(LootTables.CHESTS_SHIPWRECK_TREASURE)) {
/*     */       
/*  84 */       LootPool fruit_boxes = constructLootPool("fruit_boxes", -3.0F, 1.0F, (LootEntry.Builder<?>[])new LootEntry.Builder[] {
/*  85 */             (LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.TIER_1_BOX).weight(280), 
/*  86 */             (LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.TIER_2_BOX).weight(15), 
/*  87 */             (LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.TIER_3_BOX).weight(5) });
/*  88 */       event.getTable().addPool(fruit_boxes);
/*     */     } 
/*     */     
/*  91 */     if (event.getName().equals(LootTables.CHESTS_SHIPWRECK_SUPPLY) && event.getName().equals(LootTables.CHESTS_SHIPWRECK_MAP)) {
/*     */       
/*  93 */       LootPool fruit_boxes = constructLootPool("fruit_boxes", -7.0F, 1.0F, (LootEntry.Builder<?>[])new LootEntry.Builder[] {
/*  94 */             (LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.TIER_1_BOX).weight(9), 
/*  95 */             (LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.TIER_2_BOX).weight(1) });
/*  96 */       event.getTable().addPool(fruit_boxes);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static LootPool constructLootPool(String name, float minRolls, float maxRolls, LootEntry.Builder<?>... lootEntries) {
/* 102 */     LootPool.Builder poolBuilder = LootPool.builder().name(name).rolls((IRandomRange)RandomValueRange.of(minRolls, maxRolls));
/* 103 */     if (lootEntries != null)
/*     */     {
/* 105 */       for (LootEntry.Builder<?> e : lootEntries) {
/*     */         
/* 107 */         if (e != null)
/* 108 */           poolBuilder.addEntry(e); 
/*     */       } 
/*     */     }
/* 111 */     return poolBuilder.build();
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\init\ModLootTables.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */