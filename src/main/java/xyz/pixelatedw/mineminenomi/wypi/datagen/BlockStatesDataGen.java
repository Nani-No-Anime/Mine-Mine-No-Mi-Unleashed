/*    */ package xyz.pixelatedw.mineminenomi.wypi.datagen;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.data.DataGenerator;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.client.model.generators.BlockStateProvider;
/*    */ import net.minecraftforge.client.model.generators.ExistingFileHelper;
/*    */ import net.minecraftforge.fml.RegistryObject;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ public class BlockStatesDataGen
/*    */   extends BlockStateProvider
/*    */ {
/*    */   public BlockStatesDataGen(DataGenerator gen, ExistingFileHelper exFileHelper) {
/* 16 */     super(gen, APIConfig.projectId, exFileHelper);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void registerStatesAndModels() {
/* 22 */     for (RegistryObject<Block> obj : (Iterable<RegistryObject<Block>>)WyRegistry.BLOCKS.getEntries())
/*    */     {
/* 24 */       simpleBlock((Block)obj.get());
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation blockTexture(Block block) {
/* 31 */     ResourceLocation name = block.getRegistryName();
/* 32 */     return new ResourceLocation(name.getNamespace(), "block/" + name.getPath());
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\datagen\BlockStatesDataGen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */