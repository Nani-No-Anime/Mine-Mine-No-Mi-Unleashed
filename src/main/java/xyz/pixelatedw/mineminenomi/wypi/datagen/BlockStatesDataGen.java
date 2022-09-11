package xyz.pixelatedw.mineminenomi.wypi.datagen;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;

public class BlockStatesDataGen
  extends BlockStateProvider
{
  public BlockStatesDataGen(DataGenerator gen, ExistingFileHelper exFileHelper) {
    super(gen, APIConfig.projectId, exFileHelper);
  }


  
  protected void registerStatesAndModels() {
    for (RegistryObject<Block> obj : (Iterable<RegistryObject<Block>>)WyRegistry.BLOCKS.getEntries())
    {
      simpleBlock((Block)obj.get());
    }
  }


  
  public ResourceLocation blockTexture(Block block) {
    ResourceLocation name = block.getRegistryName();
    return new ResourceLocation(name.getNamespace(), "block/" + name.getPath());
  }
}


