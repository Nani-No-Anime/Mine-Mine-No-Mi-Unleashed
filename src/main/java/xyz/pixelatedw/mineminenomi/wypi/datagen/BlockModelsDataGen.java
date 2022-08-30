/*    */ package xyz.pixelatedw.mineminenomi.wypi.datagen;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.data.DataGenerator;
/*    */ import net.minecraftforge.client.model.generators.BlockModelBuilder;
/*    */ import net.minecraftforge.client.model.generators.BlockModelProvider;
/*    */ import net.minecraftforge.client.model.generators.ExistingFileHelper;
/*    */ import net.minecraftforge.client.model.generators.ModelFile;
/*    */ import net.minecraftforge.fml.RegistryObject;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ public class BlockModelsDataGen
/*    */   extends BlockModelProvider
/*    */ {
/*    */   public BlockModelsDataGen(DataGenerator generator, ExistingFileHelper existingFileHelper) {
/* 17 */     super(generator, APIConfig.projectId, existingFileHelper);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void registerModels() {
/* 23 */     for (RegistryObject<Block> obj : (Iterable<RegistryObject<Block>>)WyRegistry.BLOCKS.getEntries())
/*    */     {
/* 25 */       block(((Block)obj.get()).getRegistryName().getPath());
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 32 */     return "Block Models";
/*    */   }
/*    */ 
/*    */   
/*    */   public void block(String name) {
/* 37 */     ((BlockModelBuilder)((BlockModelBuilder)getBuilder(name)).parent((ModelFile)new ModelFile.UncheckedModelFile("block/cube_all"))).texture("all", "blocks/" + name);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\datagen\BlockModelsDataGen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */