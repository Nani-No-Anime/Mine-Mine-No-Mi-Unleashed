package xyz.pixelatedw.mineminenomi.wypi.datagen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.data.LootTableProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableManager;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;



public abstract class LootTablesDataGen
  extends LootTableProvider
{
  private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
  
  private DataGenerator generator;
  
  public LootTablesDataGen(DataGenerator dataGenerator) {
    super(dataGenerator);
    this.generator = dataGenerator;
  }

  
  public void writeTables(DirectoryCache cache, Map<ResourceLocation, LootTable> tables) {
    Path outputFolder = this.generator.getOutputFolder().resolve("data/mineminenomi/loot_tables/");
    tables.forEach((res, lootTable) -> {
          Path path = outputFolder.resolve(res.getPath() + ".json");


          
          try {
            IDataProvider.save(GSON, cache, LootTableManager.toJson(lootTable), path);
          } catch (IOException e) {
            e.printStackTrace();
          } 
        });
  }
  
  public abstract String getName();
}


