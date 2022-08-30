/*    */ package xyz.pixelatedw.mineminenomi.setup;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import net.minecraft.world.dimension.DimensionType;
/*    */ import net.minecraftforge.common.DimensionManager;
/*    */ import net.minecraftforge.event.world.RegisterDimensionsEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
/*    */ import xyz.pixelatedw.mineminenomi.commands.AbilityCommand;
/*    */ import xyz.pixelatedw.mineminenomi.commands.AbilityProtectionCommand;
/*    */ import xyz.pixelatedw.mineminenomi.commands.BellyCommand;
/*    */ import xyz.pixelatedw.mineminenomi.commands.BountyCommand;
/*    */ import xyz.pixelatedw.mineminenomi.commands.CheckFruitsCommand;
/*    */ import xyz.pixelatedw.mineminenomi.commands.CheckPlayerCommand;
/*    */ import xyz.pixelatedw.mineminenomi.commands.DamageMultiplierCommand;
/*    */ import xyz.pixelatedw.mineminenomi.commands.DorikiCommand;
/*    */ import xyz.pixelatedw.mineminenomi.commands.ExtolCommand;
/*    */ import xyz.pixelatedw.mineminenomi.commands.FGCommand;
/*    */ import xyz.pixelatedw.mineminenomi.commands.FactionRougeCommand;
/*    */ import xyz.pixelatedw.mineminenomi.commands.GetWantedPosterCommand;
/*    */ import xyz.pixelatedw.mineminenomi.commands.HakiExpCommand;
/*    */ import xyz.pixelatedw.mineminenomi.commands.IssueBountyCommand;
/*    */ import xyz.pixelatedw.mineminenomi.commands.LoyaltyCommand;
/*    */ import xyz.pixelatedw.mineminenomi.commands.PouchCommand;
/*    */ import xyz.pixelatedw.mineminenomi.commands.QuestCommand;
/*    */ import xyz.pixelatedw.mineminenomi.commands.RemoveDFCommand;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDimensions;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi", bus = Mod.EventBusSubscriber.Bus.FORGE)
/*    */ public class ForgeSetup
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void serverStarting(FMLServerStartingEvent event) {
/* 41 */     CommandDispatcher dispatcher = event.getServer().getCommandManager().getDispatcher();
/*    */     
/* 43 */     AbilityProtectionCommand.register(dispatcher);
/* 44 */     DorikiCommand.register(dispatcher);
/* 45 */     BountyCommand.register(dispatcher);
/* 46 */     BellyCommand.register(dispatcher);
/* 47 */     ExtolCommand.register(dispatcher);
/* 48 */     IssueBountyCommand.register(dispatcher);
/* 49 */     GetWantedPosterCommand.register(dispatcher);
/* 50 */     RemoveDFCommand.register(dispatcher);
/* 51 */     AbilityCommand.register(dispatcher);
/* 52 */     QuestCommand.register(dispatcher);
/* 53 */     HakiExpCommand.register(dispatcher);
/* 54 */     PouchCommand.register(dispatcher);
/* 55 */     CheckFruitsCommand.register(dispatcher);
/* 56 */     CheckPlayerCommand.register(dispatcher);
/* 57 */     DamageMultiplierCommand.register(dispatcher);
/* 58 */     LoyaltyCommand.register(dispatcher);
/* 59 */     FactionRougeCommand.register(dispatcher);
/*    */     
/* 61 */     if (APIConfig.BUILD_MODE != APIConfig.BuildMode.RELEASE) {
/* 62 */       FGCommand.register(dispatcher);
/*    */     }
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onRegisterDimensionsEvent(RegisterDimensionsEvent event) {
/* 68 */     if (DimensionType.byName(ModResources.DIMENSION_TYPE_CHALLENGES) == null)
/* 69 */       DimensionManager.registerDimension(ModResources.DIMENSION_TYPE_CHALLENGES, ModDimensions.CHALLENGES, null, true); 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\setup\ForgeSetup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */