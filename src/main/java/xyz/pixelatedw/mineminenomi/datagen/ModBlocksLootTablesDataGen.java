package xyz.pixelatedw.mineminenomi.datagen;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.functions.ApplyBonus;
import net.minecraft.world.storage.loot.functions.ILootFunction;
import net.minecraft.world.storage.loot.functions.SetCount;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.wypi.datagen.LootTablesDataGen;

import java.util.HashMap;
import java.util.Map;

public class ModBlocksLootTablesDataGen extends LootTablesDataGen {
  private final Map<Block, LootTable.Builder> lootTables = new HashMap<>();

  
  public ModBlocksLootTablesDataGen(DataGenerator dataGenerator) {
    super(dataGenerator);
  }



  
  public void act(DirectoryCache cache) {
    addLootTable(ModBlocks.KAIROSEKI_ORE, new LootPool.Builder[] { createOreLootPool(ModItems.KAIROSEKI, 1, 1, 3.0F, 6.0F) });

    
    addLootTable(ModBlocks.AXE_DIAL, new LootPool.Builder[] { createSelfDropLootPool(ModBlocks.AXE_DIAL) });
    addLootTable(ModBlocks.BREATH_DIAL, new LootPool.Builder[] { createSelfDropLootPool(ModBlocks.BREATH_DIAL) });
    addLootTable(ModBlocks.FLAME_DIAL, new LootPool.Builder[] { createSelfDropLootPool(ModBlocks.FLAME_DIAL) });
    addLootTable(ModBlocks.REJECT_DIAL, new LootPool.Builder[] { createSelfDropLootPool(ModBlocks.REJECT_DIAL) });
    addLootTable(ModBlocks.IMPACT_DIAL, new LootPool.Builder[] { createSelfDropLootPool(ModBlocks.IMPACT_DIAL) });
    addLootTable(ModBlocks.FLASH_DIAL, new LootPool.Builder[] { createSelfDropLootPool(ModBlocks.FLASH_DIAL) });
    addLootTable(ModBlocks.EISEN_DIAL, new LootPool.Builder[] { createSelfDropLootPool(ModBlocks.EISEN_DIAL) });
    addLootTable(ModBlocks.MILKY_DIAL, new LootPool.Builder[] { createSelfDropLootPool(ModBlocks.MILKY_DIAL) });
    addLootTable(ModBlocks.DEN_DEN_MUSHI, new LootPool.Builder[] { createSelfDropLootPool(ModBlocks.DEN_DEN_MUSHI) });
    addLootTable(ModBlocks.CANNON, new LootPool.Builder[] { createSelfDropLootPool(ModBlocks.CANNON) });
    addLootTable(ModBlocks.KAIROSEKI, new LootPool.Builder[] { createSelfDropLootPool(ModBlocks.KAIROSEKI) });
    addLootTable(ModBlocks.KAIROSEKI_BARS, new LootPool.Builder[] { createSelfDropLootPool(ModBlocks.KAIROSEKI_BARS) });
    
    Map<ResourceLocation, LootTable> tables = new HashMap<>();
    this.lootTables.forEach((block, builder) -> tables.put(block.getLootTable(), builder.build()));


    
    writeTables(cache, tables);
  }

  
  protected LootPool.Builder createBasicLootPool(Item drop, int roll, float count) {
    return createBasicLootPool(drop, roll, roll, count, count);
  }
  
  protected LootPool.Builder createBasicLootPool(Item drop, int rollMin, int rollMax, float countMin, float countMax) {
    IRandomRange randomRange1 = null;
    IRandomRange randomRange2 = null;
    if (rollMin == rollMax) {
      randomRange1 = ConstantRange.of(rollMin);
    } else {
      randomRange1 = RandomValueRange.of(rollMin, rollMax);
    } 
    
    if (countMin == countMax) {
      randomRange2 = ConstantRange.of((int)countMin);
    } else {
      randomRange2 = RandomValueRange.of(countMin, countMax);
    } 
    return LootPool.builder().rolls((IRandomRange)randomRange1)
      .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)drop)
        .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)randomRange2)));
  }

  
  protected LootPool.Builder createSelfDropLootPool(Block block) {
    return LootPool.builder().rolls((IRandomRange)ConstantRange.of(1))
      .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)block.asItem())
        .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)ConstantRange.of(1))));
  }

  
  protected LootPool.Builder createOreLootPool(Item ore, int roll, float count) {
    return createOreLootPool(ore, roll, roll, count, count);
  }
  
  protected LootPool.Builder createOreLootPool(Item ore, int rollMin, int rollMax, float countMin, float countMax) {
    IRandomRange randomRange1 = null;
    IRandomRange randomRange2 = null;
    if (rollMin == rollMax) {
      randomRange1 = ConstantRange.of(rollMin);
    } else {
      randomRange1 = RandomValueRange.of(rollMin, rollMax);
    } 
    
    if (countMin == countMax) {
      randomRange2 = ConstantRange.of((int)countMin);
    } else {
      randomRange2 = RandomValueRange.of(countMin, countMax);
    } 
    return LootPool.builder().rolls((IRandomRange)randomRange1)
      .addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)ore)
        .acceptFunction((ILootFunction.IBuilder)SetCount.builder((IRandomRange)randomRange2))
        .acceptFunction((ILootFunction.IBuilder)ApplyBonus.oreDrops(Enchantments.FORTUNE)));
  }

  
  protected void addLootTable(Block block, LootPool.Builder... pools) {
    LootTable.Builder builder = LootTable.builder();
    for (LootPool.Builder pool : pools)
    {
      builder.addLootPool(pool);
    }
    this.lootTables.put(block, builder);
  }


  
  public String getName() {
    return "Blocks Loot Pools";
  }
}


