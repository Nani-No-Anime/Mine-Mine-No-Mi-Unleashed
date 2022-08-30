/*    */ package xyz.pixelatedw.mineminenomi.wypi.datagen;
/*    */ 
/*    */ import net.minecraft.data.DataGenerator;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraftforge.client.model.generators.ExistingFileHelper;
/*    */ import net.minecraftforge.client.model.generators.ItemModelBuilder;
/*    */ import net.minecraftforge.client.model.generators.ItemModelProvider;
/*    */ import net.minecraftforge.client.model.generators.ModelFile;
/*    */ import net.minecraftforge.fml.RegistryObject;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ 
/*    */ public class ItemModelsDataGen
/*    */   extends ItemModelProvider
/*    */ {
/*    */   public ItemModelsDataGen(DataGenerator generator, ExistingFileHelper existingFileHelper) {
/* 18 */     super(generator, APIConfig.projectId, existingFileHelper);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void registerModels() {
/* 24 */     for (RegistryObject<Item> obj : (Iterable<RegistryObject<Item>>)WyRegistry.ITEMS.getEntries()) {
/*    */       
/* 26 */       if (((Item)obj.get()).asItem() instanceof xyz.pixelatedw.mineminenomi.wypi.ModdedSpawnEggItem) {
/*    */         continue;
/*    */       }
/* 29 */       if (((Item)obj.get()).asItem() instanceof net.minecraft.item.BlockItem) {
/* 30 */         blockItem(((Item)obj.get()).asItem().getRegistryName().getPath()); continue;
/*    */       } 
/* 32 */       item(((Item)obj.get()).asItem().getRegistryName().getPath());
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void item(String name) {
/* 38 */     item(name, "");
/*    */   }
/*    */ 
/*    */   
/*    */   public void item(String name, String path) {
/* 43 */     ((ItemModelBuilder)((ItemModelBuilder)getBuilder(name))
/* 44 */       .parent((ModelFile)new ModelFile.UncheckedModelFile("item/generated")))
/* 45 */       .texture("layer0", "items/" + path + name);
/*    */   }
/*    */ 
/*    */   
/*    */   public void blockItem(String name) {
/* 50 */     ((ItemModelBuilder)getBuilder(name))
/* 51 */       .parent((ModelFile)new ModelFile.UncheckedModelFile(APIConfig.projectId + ":block/" + name));
/*    */   }
/*    */ 
/*    */   
/*    */   public void spawnEggItem(String name) {
/* 56 */     ((ItemModelBuilder)getBuilder(name))
/* 57 */       .parent((ModelFile)new ModelFile.UncheckedModelFile("item/template_spawn_egg"));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 63 */     return "Item Models";
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\datagen\ItemModelsDataGen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */