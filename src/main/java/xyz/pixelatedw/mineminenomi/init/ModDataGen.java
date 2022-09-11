package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.IDataProvider;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import org.apache.logging.log4j.Level;
import xyz.pixelatedw.mineminenomi.ModMain;
import xyz.pixelatedw.mineminenomi.datagen.ModBlocksLootTablesDataGen;
import xyz.pixelatedw.mineminenomi.datagen.ModDFBoxesLootTablesDataGen;
import xyz.pixelatedw.mineminenomi.datagen.ModEntitiesLootTablesDataGen;
import xyz.pixelatedw.mineminenomi.datagen.ModTraderLootTablesDataGen;

@EventBusSubscriber(modid = "mineminenomi", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDataGen
{
  @SubscribeEvent
  public static void onGatherData(GatherDataEvent event) {
    ModMain.LOGGER.log(Level.INFO, "Started Generating JSON files");
    DataGenerator generator = event.getGenerator();

    
    generator.addProvider((IDataProvider)new ModBlocksLootTablesDataGen(generator));
    generator.addProvider((IDataProvider)new ModEntitiesLootTablesDataGen(generator));
    generator.addProvider((IDataProvider)new ModTraderLootTablesDataGen(generator));
    generator.addProvider((IDataProvider)new ModDFBoxesLootTablesDataGen(generator));
  }
}


