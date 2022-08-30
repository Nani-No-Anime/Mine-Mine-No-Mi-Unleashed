/*    */ package xyz.pixelatedw.mineminenomi.setup;
/*    */ 
/*    */ import net.minecraft.command.arguments.ArgumentSerializer;
/*    */ import net.minecraft.command.arguments.ArgumentTypes;
/*    */ import net.minecraft.command.arguments.IArgumentSerializer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
/*    */ import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.commands.AbilityArgument;
/*    */ import xyz.pixelatedw.mineminenomi.api.commands.AbilityGroupArgument;
/*    */ import xyz.pixelatedw.mineminenomi.api.commands.HakiTypeArgument;
/*    */ import xyz.pixelatedw.mineminenomi.api.commands.QuestArgument;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCapabilities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModFeatures;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18nConfig;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModKeybindings;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModNetwork;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderers;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi", bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class ModSetup
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void commonSetup(FMLCommonSetupEvent event) {
/* 32 */     ModCapabilities.init();
/*    */     
/* 34 */     ModNetwork.init();
/*    */     
/* 36 */     ModFeatures.init();
/*    */     
/* 38 */     ArgumentTypes.register("ability", AbilityArgument.class, (IArgumentSerializer)new ArgumentSerializer(AbilityArgument::ability));
/* 39 */     ArgumentTypes.register("quest", QuestArgument.class, (IArgumentSerializer)new ArgumentSerializer(QuestArgument::quest));
/* 40 */     ArgumentTypes.register("type", HakiTypeArgument.class, (IArgumentSerializer)new ArgumentSerializer(HakiTypeArgument::hakiType));
/* 41 */     ArgumentTypes.register("group", AbilityGroupArgument.class, (IArgumentSerializer)new ArgumentSerializer(AbilityGroupArgument::abilityGroup));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void clientSetup(FMLClientSetupEvent event) {
/* 49 */     ModKeybindings.init();
/*    */ 
/*    */     
/* 52 */     ModI18n.init();
/* 53 */     ModI18nConfig.init();
/*    */ 
/*    */     
/* 56 */     ModRenderers.registerRenderers();
/*    */     
/* 58 */     if (WyDebug.isDebug())
/* 59 */       WyHelper.generateJSONLangs(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\setup\ModSetup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */