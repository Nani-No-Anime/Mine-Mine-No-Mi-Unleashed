package xyz.pixelatedw.mineminenomi.datagen;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.ConstantRange;
import net.minecraft.world.storage.loot.IRandomRange;
import net.minecraft.world.storage.loot.ItemLootEntry;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.functions.ILootFunction;
import net.minecraft.world.storage.loot.functions.SetCount;
import xyz.pixelatedw.mineminenomi.data.functions.SetBellyInPouchFunction;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.wypi.datagen.LootTablesDataGen;

public class ModEntitiesLootTablesDataGen extends LootTablesDataGen {
  private final Map<EntityType, LootTable.Builder> lootTables = new HashMap<>();

  
  public ModEntitiesLootTablesDataGen(DataGenerator dataGenerator) {
    super(dataGenerator);
  }



  
  public void act(DirectoryCache cache) {
    addLootTable(ModEntities.KUNG_FU_DUGONG, new LootPool.Builder[] {
          LootPool.builder().rolls((IRandomRange)ConstantRange.of(1))
          .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)Items.SCUTE)
            .weight(80)
            .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(0.0F, 3.0F))))
          .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)Items.SEAGRASS)
            .weight(100)
            .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 5.0F))))
          .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)Items.KELP)
            .weight(100)
            .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(0.0F, 5.0F))))
        });
    addLootTable(ModEntities.LAPAHN, new LootPool.Builder[] {
          LootPool.builder().rolls((IRandomRange)ConstantRange.of(1))
          .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)Items.SNOWBALL)
            .weight(100)
            .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(0.0F, 5.0F))))
          .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)Items.RABBIT_HIDE)
            .weight(80)
            .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 2.0F))))
          .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)Items.RABBIT)
            .weight(80)
            .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(0.0F, 3.0F))))
          .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)Items.RABBIT_FOOT)
            .weight(20)
            .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(0.0F, 2.0F))))
        });
    addLootTable(ModEntities.MARINE_TRADER, new LootPool.Builder[] {
          LootPool.builder().rolls((IRandomRange)RandomValueRange.of(0.0F, 1.0F))
          .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.BELLY_POUCH)
            .weight(100)
            .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)ConstantRange.of(1)))
            .acceptFunction((ILootFunction.IBuilder)SetBellyInPouchFunction.builder((IRandomRange)RandomValueRange.of(5.0F, 50.0F))))
        });
    addLootTable(ModEntities.PIRATE_TRADER, new LootPool.Builder[] {
          LootPool.builder().rolls((IRandomRange)RandomValueRange.of(0.0F, 1.0F))
          .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ModItems.BELLY_POUCH)
            .weight(100)
            .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)ConstantRange.of(1)))
            .acceptFunction((ILootFunction.IBuilder)SetBellyInPouchFunction.builder((IRandomRange)RandomValueRange.of(5.0F, 50.0F))))
        });
    addLootTable(ModEntities.YAGARA_BULL, new LootPool.Builder[] {
          LootPool.builder().rolls((IRandomRange)ConstantRange.of(1))
          .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)Items.SEAGRASS)
            .weight(100)
            .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(1.0F, 5.0F))))
          .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)Items.KELP)
            .weight(100)
            .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)RandomValueRange.of(0.0F, 5.0F))))
        });
    Map<ResourceLocation, LootTable> tables = new HashMap<>();
    this.lootTables.forEach((entityType, builder) -> tables.put(entityType.getLootTable(), builder.build()));


    
    writeTables(cache, tables);
  }

  
  protected void addLootTable(EntityType entityType, LootPool.Builder... pools) {
    LootTable.Builder builder = LootTable.builder();
    for (LootPool.Builder pool : pools)
    {
      builder.addLootPool(pool);
    }
    this.lootTables.put(entityType, builder);
  }


  
  public String getName() {
    return "Entities Loot Pools";
  }
}


