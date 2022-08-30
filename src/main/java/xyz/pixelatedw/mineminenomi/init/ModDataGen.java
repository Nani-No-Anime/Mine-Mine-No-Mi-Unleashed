/*    */ package xyz.pixelatedw.mineminenomi.init;
/*    */ 
/*    */ import net.minecraft.data.DataGenerator;
/*    */ import net.minecraft.data.IDataProvider;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
/*    */ import org.apache.logging.log4j.Level;
/*    */ import xyz.pixelatedw.mineminenomi.ModMain;
/*    */ import xyz.pixelatedw.mineminenomi.datagen.ModBlocksLootTablesDataGen;
/*    */ import xyz.pixelatedw.mineminenomi.datagen.ModDFBoxesLootTablesDataGen;
/*    */ import xyz.pixelatedw.mineminenomi.datagen.ModEntitiesLootTablesDataGen;
/*    */ import xyz.pixelatedw.mineminenomi.datagen.ModTraderLootTablesDataGen;
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi", bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class ModDataGen
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onGatherData(GatherDataEvent event) {
/* 21 */     ModMain.LOGGER.log(Level.INFO, "Started Generating JSON files");
/* 22 */     DataGenerator generator = event.getGenerator();
/*    */ 
/*    */     
/* 25 */     generator.addProvider((IDataProvider)new ModBlocksLootTablesDataGen(generator));
/* 26 */     generator.addProvider((IDataProvider)new ModEntitiesLootTablesDataGen(generator));
/* 27 */     generator.addProvider((IDataProvider)new ModTraderLootTablesDataGen(generator));
/* 28 */     generator.addProvider((IDataProvider)new ModDFBoxesLootTablesDataGen(generator));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\init\ModDataGen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */