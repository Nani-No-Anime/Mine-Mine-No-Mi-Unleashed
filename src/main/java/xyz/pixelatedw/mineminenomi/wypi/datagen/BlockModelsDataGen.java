package xyz.pixelatedw.mineminenomi.wypi.datagen;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.fml.RegistryObject;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;

public class BlockModelsDataGen
  extends BlockModelProvider
{
  public BlockModelsDataGen(DataGenerator generator, ExistingFileHelper existingFileHelper) {
    super(generator, APIConfig.projectId, existingFileHelper);
  }


  
  protected void registerModels() {
    for (RegistryObject<Block> obj : (Iterable<RegistryObject<Block>>)WyRegistry.BLOCKS.getEntries())
    {
      block(((Block)obj.get()).getRegistryName().getPath());
    }
  }


  
  public String getName() {
    return "Block Models";
  }

  
  public void block(String name) {
    ((BlockModelBuilder)((BlockModelBuilder)getBuilder(name)).parent((ModelFile)new ModelFile.UncheckedModelFile("block/cube_all"))).texture("all", "blocks/" + name);
  }
}


