package xyz.pixelatedw.mineminenomi.wypi.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.fml.RegistryObject;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;


public class ItemModelsDataGen
  extends ItemModelProvider
{
  public ItemModelsDataGen(DataGenerator generator, ExistingFileHelper existingFileHelper) {
    super(generator, APIConfig.projectId, existingFileHelper);
  }


  
  protected void registerModels() {
    for (RegistryObject<Item> obj : (Iterable<RegistryObject<Item>>)WyRegistry.ITEMS.getEntries()) {
      
      if (((Item)obj.get()).asItem() instanceof xyz.pixelatedw.mineminenomi.wypi.ModdedSpawnEggItem) {
        continue;
      }
      if (((Item)obj.get()).asItem() instanceof net.minecraft.item.BlockItem) {
        blockItem(((Item)obj.get()).asItem().getRegistryName().getPath()); continue;
      } 
      item(((Item)obj.get()).asItem().getRegistryName().getPath());
    } 
  }

  
  public void item(String name) {
    item(name, "");
  }

  
  public void item(String name, String path) {
    ((ItemModelBuilder)((ItemModelBuilder)getBuilder(name))
      .parent((ModelFile)new ModelFile.UncheckedModelFile("item/generated")))
      .texture("layer0", "items/" + path + name);
  }

  
  public void blockItem(String name) {
    ((ItemModelBuilder)getBuilder(name))
      .parent((ModelFile)new ModelFile.UncheckedModelFile(APIConfig.projectId + ":block/" + name));
  }

  
  public void spawnEggItem(String name) {
    ((ItemModelBuilder)getBuilder(name))
      .parent((ModelFile)new ModelFile.UncheckedModelFile("item/template_spawn_egg"));
  }


  
  public String getName() {
    return "Item Models";
  }
}


