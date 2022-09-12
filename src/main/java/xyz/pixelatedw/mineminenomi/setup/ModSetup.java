package xyz.pixelatedw.mineminenomi.setup;

import net.minecraft.command.arguments.ArgumentSerializer;
import net.minecraft.command.arguments.ArgumentTypes;
import net.minecraft.command.arguments.IArgumentSerializer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import xyz.pixelatedw.mineminenomi.api.commands.AbilityArgument;
import xyz.pixelatedw.mineminenomi.api.commands.AbilityGroupArgument;
import xyz.pixelatedw.mineminenomi.api.commands.HakiTypeArgument;
import xyz.pixelatedw.mineminenomi.api.commands.QuestArgument;
import xyz.pixelatedw.mineminenomi.init.*;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;

@EventBusSubscriber(modid = "mineminenomi", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSetup
{
  @SubscribeEvent
  public static void commonSetup(FMLCommonSetupEvent event) {
    ModCapabilities.init();
    
    ModNetwork.init();
    
    ModFeatures.init();
    
    ArgumentTypes.register("ability", AbilityArgument.class, (IArgumentSerializer)new ArgumentSerializer(AbilityArgument::ability));
    ArgumentTypes.register("quest", QuestArgument.class, (IArgumentSerializer)new ArgumentSerializer(QuestArgument::quest));
    ArgumentTypes.register("type", HakiTypeArgument.class, (IArgumentSerializer)new ArgumentSerializer(HakiTypeArgument::hakiType));
    ArgumentTypes.register("group", AbilityGroupArgument.class, (IArgumentSerializer)new ArgumentSerializer(AbilityGroupArgument::abilityGroup));
  }


  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void clientSetup(FMLClientSetupEvent event) {
    ModKeybindings.init();

    
    ModI18n.init();
    ModI18nConfig.init();

    
    ModRenderers.registerRenderers();
    
    if (WyDebug.isDebug())
      WyHelper.generateJSONLangs(); 
  }
}


