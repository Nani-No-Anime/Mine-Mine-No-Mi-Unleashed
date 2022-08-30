/*    */ package xyz.pixelatedw.mineminenomi.wypi.datagen;
/*    */ 
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.GsonBuilder;
/*    */ import java.io.IOException;
/*    */ import java.nio.file.Path;
/*    */ import java.util.Map;
/*    */ import net.minecraft.data.DataGenerator;
/*    */ import net.minecraft.data.DirectoryCache;
/*    */ import net.minecraft.data.IDataProvider;
/*    */ import net.minecraft.data.LootTableProvider;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.storage.loot.LootTable;
/*    */ import net.minecraft.world.storage.loot.LootTableManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class LootTablesDataGen
/*    */   extends LootTableProvider
/*    */ {
/* 21 */   private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
/*    */   
/*    */   private DataGenerator generator;
/*    */   
/*    */   public LootTablesDataGen(DataGenerator dataGenerator) {
/* 26 */     super(dataGenerator);
/* 27 */     this.generator = dataGenerator;
/*    */   }
/*    */ 
/*    */   
/*    */   public void writeTables(DirectoryCache cache, Map<ResourceLocation, LootTable> tables) {
/* 32 */     Path outputFolder = this.generator.getOutputFolder().resolve("data/mineminenomi/loot_tables/");
/* 33 */     tables.forEach((res, lootTable) -> {
/*    */           Path path = outputFolder.resolve(res.getPath() + ".json");
/*    */ 
/*    */ 
/*    */           
/*    */           try {
/*    */             IDataProvider.save(GSON, cache, LootTableManager.toJson(lootTable), path);
/* 40 */           } catch (IOException e) {
/*    */             e.printStackTrace();
/*    */           } 
/*    */         });
/*    */   }
/*    */   
/*    */   public abstract String getName();
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\datagen\LootTablesDataGen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */