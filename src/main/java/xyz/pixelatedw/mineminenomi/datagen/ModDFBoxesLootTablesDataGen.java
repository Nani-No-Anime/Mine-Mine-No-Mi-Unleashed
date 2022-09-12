package xyz.pixelatedw.mineminenomi.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.init.ModValues;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiBoxItem;
import xyz.pixelatedw.mineminenomi.wypi.datagen.LootTablesDataGen;

import java.util.HashMap;
import java.util.Map;

public class ModDFBoxesLootTablesDataGen extends LootTablesDataGen {
  private final Map<AkumaNoMiBoxItem, LootTable.Builder> lootTables = new HashMap<>();

  
  public ModDFBoxesLootTablesDataGen(DataGenerator dataGenerator) {
    super(dataGenerator);
  }


  
  public void act(DirectoryCache cache) {
    addLootTable(ModItems.TIER_1_BOX, new LootPool.Builder[0]);
    addLootTable(ModItems.TIER_2_BOX, new LootPool.Builder[0]);
    addLootTable(ModItems.TIER_3_BOX, new LootPool.Builder[0]);
    
    Map<ResourceLocation, LootTable> tables = new HashMap<>();
    this.lootTables.forEach((item, builder) -> {
          ResourceLocation resourcelocation = ForgeRegistries.ITEMS.getKey(item);
          
          ResourceLocation key = new ResourceLocation(resourcelocation.getNamespace(), "dfboxes/" + resourcelocation.getPath());
          tables.put(key, builder.build());
        });
    writeTables(cache, tables);
  }



  
  protected void addLootTable(AkumaNoMiBoxItem item, LootPool.Builder... pools) {
    LootPool.Builder fruits = LootPool.builder().name("mineminenomi:fruits").rolls(ConstantRange.of(1));
    
    ModValues.devilfruits.stream()
      .filter(df -> (df.getTier() == item.getTier()))
      .forEach(df -> fruits.addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)df).weight(100)));
    
    LootTable.Builder builder = LootTable.builder();
    for (LootPool.Builder pool : pools)
    {
      builder.addLootPool(pool);
    }
    builder.addLootPool(fruits);
    this.lootTables.put(item, builder);
  }


  
  public String getName() {
    return "DF Boxes Loot Pools";
  }
}


