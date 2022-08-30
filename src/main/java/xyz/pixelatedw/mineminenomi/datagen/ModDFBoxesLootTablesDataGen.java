/*    */ package xyz.pixelatedw.mineminenomi.datagen;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import net.minecraft.data.DataGenerator;
/*    */ import net.minecraft.data.DirectoryCache;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.storage.loot.ConstantRange;
/*    */ import net.minecraft.world.storage.loot.ItemLootEntry;
/*    */ import net.minecraft.world.storage.loot.LootEntry;
/*    */ import net.minecraft.world.storage.loot.LootPool;
/*    */ import net.minecraft.world.storage.loot.LootTable;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import net.minecraftforge.registries.IForgeRegistryEntry;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*    */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiBoxItem;
/*    */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.datagen.LootTablesDataGen;
/*    */ 
/*    */ public class ModDFBoxesLootTablesDataGen extends LootTablesDataGen {
/* 22 */   private final Map<AkumaNoMiBoxItem, LootTable.Builder> lootTables = new HashMap<>();
/*    */ 
/*    */   
/*    */   public ModDFBoxesLootTablesDataGen(DataGenerator dataGenerator) {
/* 26 */     super(dataGenerator);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void act(DirectoryCache cache) {
/* 32 */     addLootTable(ModItems.TIER_1_BOX, new LootPool.Builder[0]);
/* 33 */     addLootTable(ModItems.TIER_2_BOX, new LootPool.Builder[0]);
/* 34 */     addLootTable(ModItems.TIER_3_BOX, new LootPool.Builder[0]);
/*    */     
/* 36 */     Map<ResourceLocation, LootTable> tables = new HashMap<>();
/* 37 */     this.lootTables.forEach((item, builder) -> {
/*    */           ResourceLocation resourcelocation = ForgeRegistries.ITEMS.getKey(item);
/*    */           
/*    */           ResourceLocation key = new ResourceLocation(resourcelocation.getNamespace(), "dfboxes/" + resourcelocation.getPath());
/*    */           tables.put(key, builder.build());
/*    */         });
/* 43 */     writeTables(cache, tables);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void addLootTable(AkumaNoMiBoxItem item, LootPool.Builder... pools) {
/* 50 */     LootPool.Builder fruits = LootPool.builder().name("mineminenomi:fruits").rolls(ConstantRange.of(1));
/*    */     
/* 52 */     ModValues.devilfruits.stream()
/* 53 */       .filter(df -> (df.getTier() == item.getTier()))
/* 54 */       .forEach(df -> fruits.addEntry((LootEntry.Builder)ItemLootEntry.builder((IItemProvider)df).weight(100)));
/*    */     
/* 56 */     LootTable.Builder builder = LootTable.builder();
/* 57 */     for (LootPool.Builder pool : pools)
/*    */     {
/* 59 */       builder.addLootPool(pool);
/*    */     }
/* 61 */     builder.addLootPool(fruits);
/* 62 */     this.lootTables.put(item, builder);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 68 */     return "DF Boxes Loot Pools";
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\datagen\ModDFBoxesLootTablesDataGen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */