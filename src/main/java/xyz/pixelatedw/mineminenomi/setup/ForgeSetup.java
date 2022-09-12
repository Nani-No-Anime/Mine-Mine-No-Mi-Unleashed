package xyz.pixelatedw.mineminenomi.setup;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import xyz.pixelatedw.mineminenomi.commands.*;
import xyz.pixelatedw.mineminenomi.init.ModDimensions;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;




@EventBusSubscriber(modid = "mineminenomi", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeSetup
{
  @SubscribeEvent
  public static void serverStarting(FMLServerStartingEvent event) {
    CommandDispatcher dispatcher = event.getServer().getCommandManager().getDispatcher();
    
    AbilityProtectionCommand.register(dispatcher);
    DorikiCommand.register(dispatcher);
    BountyCommand.register(dispatcher);
    BellyCommand.register(dispatcher);
    ExtolCommand.register(dispatcher);
    IssueBountyCommand.register(dispatcher);
    GetWantedPosterCommand.register(dispatcher);
    RemoveDFCommand.register(dispatcher);
    AbilityCommand.register(dispatcher);
    QuestCommand.register(dispatcher);
    HakiExpCommand.register(dispatcher);
    PouchCommand.register(dispatcher);
    CheckFruitsCommand.register(dispatcher);
    CheckPlayerCommand.register(dispatcher);
    DamageMultiplierCommand.register(dispatcher);
    LoyaltyCommand.register(dispatcher);
    FactionRougeCommand.register(dispatcher);
    
    if (APIConfig.BUILD_MODE != APIConfig.BuildMode.RELEASE) {
      FGCommand.register(dispatcher);
    }
  }
  
  @SubscribeEvent
  public static void onRegisterDimensionsEvent(RegisterDimensionsEvent event) {
    if (DimensionType.byName(ModResources.DIMENSION_TYPE_CHALLENGES) == null)
      DimensionManager.registerDimension(ModResources.DIMENSION_TYPE_CHALLENGES, ModDimensions.CHALLENGES, null, true); 
  }
}


