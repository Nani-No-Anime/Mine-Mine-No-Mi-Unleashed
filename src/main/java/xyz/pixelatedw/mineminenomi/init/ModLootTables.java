package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.IRandomRange;
import net.minecraft.world.storage.loot.ItemLootEntry;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTables;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.StandaloneLootEntry;
import net.minecraft.world.storage.loot.TableLootEntry;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = "mineminenomi")
public class ModLootTables {
  public static final ResourceLocation SMALL_PIRATE_SHIP_SUPPLIES_CHEST = new ResourceLocation("mineminenomi", "chests/small_pirate_ship/supplies");
  
  public static final ResourceLocation MEDIUM_PIRATE_SHIP_SUPPLIES_CHEST = new ResourceLocation("mineminenomi", "chests/medium_pirate_ship/supplies");
  public static final ResourceLocation MEDIUM_PIRATE_SHIP_FOOD_CHEST = new ResourceLocation("mineminenomi", "chests/medium_pirate_ship/food");
  public static final ResourceLocation MEDIUM_PIRATE_SHIP_CAPTAIN_CHEST = new ResourceLocation("mineminenomi", "chests/medium_pirate_ship/captain");
  
  public static final ResourceLocation LARGE_PIRATE_SHIP_SUPPLIES_CHEST = new ResourceLocation("mineminenomi", "chests/large_pirate_ship/supplies");
  public static final ResourceLocation LARGE_PIRATE_SHIP_WEAPONS_CHEST = new ResourceLocation("mineminenomi", "chests/large_pirate_ship/weapons");
  public static final ResourceLocation LARGE_PIRATE_SHIP_CAPTAIN_CHEST = new ResourceLocation("mineminenomi", "chests/large_pirate_ship/captain");
  public static final ResourceLocation LARGE_PIRATE_SHIP_TREASURE_CHEST = new ResourceLocation("mineminenomi", "chests/large_pirate_ship/treasure");
  
  public static final ResourceLocation GHOST_SHIP_SUPPLIES_CHEST = new ResourceLocation("mineminenomi", "chests/ghost_ship/supplies");
  public static final ResourceLocation GHOST_SHIP_CAPTAIN_CHEST = new ResourceLocation("mineminenomi", "chests/ghost_ship/captain");
  
  public static final ResourceLocation CAMP_SMALL_TENT_CHEST = new ResourceLocation("mineminenomi", "chests/camp/small_tent");
  public static final ResourceLocation CAMP_LARGE_TENT_CHEST = new ResourceLocation("mineminenomi", "chests/camp/large_tent");
  
  public static final ResourceLocation SMALL_BANDIT_BASE_GOLD_CHEST = new ResourceLocation("mineminenomi", "chests/small_bandit_base/gold");
  public static final ResourceLocation SMALL_BANDIT_BASE_MINE_CHEST = new ResourceLocation("mineminenomi", "chests/small_bandit_base/mine");
  public static final ResourceLocation SMALL_BANDIT_BASE_LAB_CHEST = new ResourceLocation("mineminenomi", "chests/small_bandit_base/lab");
  public static final ResourceLocation SMALL_BANDIT_BASE_ORES_CHEST = new ResourceLocation("mineminenomi", "chests/small_bandit_base/ores");
  
  public static final ResourceLocation SMALL_MARINE_BASE_CAPTAIN_CHEST = new ResourceLocation("mineminenomi", "chests/small_marine_base/captain");
  public static final ResourceLocation SMALL_MARINE_BASE_FOOD_CHEST = new ResourceLocation("mineminenomi", "chests/small_marine_base/food");
  public static final ResourceLocation SMALL_MARINE_BASE_DORM_CHEST = new ResourceLocation("mineminenomi", "chests/small_marine_base/dorm");
  
  public static final ResourceLocation LARGE_MARINE_BASE_CAPTAIN_CHEST = new ResourceLocation("mineminenomi", "chests/large_marine_base/captain");
  public static final ResourceLocation LARGE_MARINE_BASE_FOOD_CHEST = new ResourceLocation("mineminenomi", "chests/large_marine_base/food");
  public static final ResourceLocation LARGE_MARINE_BASE_GENERIC_CHEST = new ResourceLocation("mineminenomi", "chests/large_marine_base/generic");
  public static final ResourceLocation LARGE_MARINE_BASE_LAB_CHEST = new ResourceLocation("mineminenomi", "chests/large_marine_base/lab");
  
  public static final ResourceLocation LARGE_MARINE_SHIP_CAPTAIN_CHEST = new ResourceLocation("mineminenomi", "chests/large_marine_ship/captain");
  public static final ResourceLocation LARGE_MARINE_SHIP_SUPPLIES_CHEST = new ResourceLocation("mineminenomi", "chests/large_marine_ship/supplies");
  
  public static final ResourceLocation LARGE_BANDIT_BASE_TOWER_CHEST = new ResourceLocation("mineminenomi", "chests/large_bandit_base/tower");
  public static final ResourceLocation LARGE_BANDIT_BASE_TENT_CHEST = new ResourceLocation("mineminenomi", "chests/large_bandit_base/tent");
  public static final ResourceLocation LARGE_BANDIT_BASE_FOOD_CHEST = new ResourceLocation("mineminenomi", "chests/large_bandit_base/food");
  public static final ResourceLocation LARGE_BANDIT_BASE_DORM_CHEST = new ResourceLocation("mineminenomi", "chests/large_bandit_base/dorm");
  public static final ResourceLocation LARGE_BANDIT_BASE_SECRET_STASH_CHEST = new ResourceLocation("mineminenomi", "chests/large_bandit_base/secret_stash");

  
  public static final ResourceLocation SKYPIEAN_TRADER = new ResourceLocation("mineminenomi", "entities/trader/skypiean_trader");
  public static final ResourceLocation PIRATE_TRADER = new ResourceLocation("mineminenomi", "entities/trader/pirate_trader");
  public static final ResourceLocation MARINE_TRADER = new ResourceLocation("mineminenomi", "entities/trader/marine_trader");

  
  @SubscribeEvent
  public static void onVanillaLootLoading(LootTableLoadEvent event) {
    if (event.getName().toString().equals("minecraft:blocks/brain_coral_block")) {
      
      StandaloneLootEntry.Builder lootEntry = TableLootEntry.builder(new ResourceLocation("mineminenomi", "blocks/inject/brain_coral_block"));
      event.getTable().removePool("main");
      event.getTable().addPool(LootPool.builder().addEntry((LootEntry.Builder)lootEntry).build());
    } 
    
    if (event.getName().equals(LootTables.CHESTS_UNDERWATER_RUIN_BIG) || event.getName().equals(LootTables.CHESTS_UNDERWATER_RUIN_SMALL)) {
      
      LootPool fruit_boxes = constructLootPool("fruit_boxes", -7.0F, 1.0F, (LootEntry.Builder<?>[])new LootEntry.Builder[] {
            (LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.TIER_1_BOX).weight(9), 
            (LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.TIER_2_BOX).weight(1) });
      event.getTable().addPool(fruit_boxes);
    } 
    
    if (event.getName().equals(LootTables.CHESTS_BURIED_TREASURE) || event.getName().equals(LootTables.CHESTS_SHIPWRECK_TREASURE)) {
      
      LootPool fruit_boxes = constructLootPool("fruit_boxes", -3.0F, 1.0F, (LootEntry.Builder<?>[])new LootEntry.Builder[] {
            (LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.TIER_1_BOX).weight(280), 
            (LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.TIER_2_BOX).weight(15), 
            (LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.TIER_3_BOX).weight(5) });
      event.getTable().addPool(fruit_boxes);
    } 
    
    if (event.getName().equals(LootTables.CHESTS_SHIPWRECK_SUPPLY) && event.getName().equals(LootTables.CHESTS_SHIPWRECK_MAP)) {
      
      LootPool fruit_boxes = constructLootPool("fruit_boxes", -7.0F, 1.0F, (LootEntry.Builder<?>[])new LootEntry.Builder[] {
            (LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.TIER_1_BOX).weight(9), 
            (LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.TIER_2_BOX).weight(1) });
      event.getTable().addPool(fruit_boxes);
    } 
  }

  
  public static LootPool constructLootPool(String name, float minRolls, float maxRolls, LootEntry.Builder<?>... lootEntries) {
    LootPool.Builder poolBuilder = LootPool.builder().name(name).rolls((IRandomRange)RandomValueRange.of(minRolls, maxRolls));
    if (lootEntries != null)
    {
      for (LootEntry.Builder<?> e : lootEntries) {
        
        if (e != null)
          poolBuilder.addEntry(e); 
      } 
    }
    return poolBuilder.build();
  }
}


