/*     */ package xyz.pixelatedw.mineminenomi;
/*     */ 
/*     */ import java.util.Locale;
/*     */ import java.util.function.Function;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.storage.loot.conditions.ILootCondition;
/*     */ import net.minecraft.world.storage.loot.conditions.LootConditionManager;
/*     */ import net.minecraft.world.storage.loot.functions.ILootFunction;
/*     */ import net.minecraft.world.storage.loot.functions.LootFunctionManager;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.IEventBus;
/*     */ import net.minecraftforge.fml.DistExecutor;
/*     */ import net.minecraftforge.fml.ModLoadingContext;
/*     */ import net.minecraftforge.fml.common.Mod;
/*     */ import net.minecraftforge.fml.config.ModConfig;
/*     */ import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.conditions.RandomizedFruitsCondition;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.functions.EncyclopediaCompletionFunction;
/*     */ import xyz.pixelatedw.mineminenomi.data.functions.FakeWeaponFunction;
/*     */ import xyz.pixelatedw.mineminenomi.data.functions.SetBellyInPouchFunction;
/*     */ import xyz.pixelatedw.mineminenomi.data.functions.SetFruitClueFunction;
/*     */ import xyz.pixelatedw.mineminenomi.data.functions.SetInfiniteStockFunction;
/*     */ import xyz.pixelatedw.mineminenomi.data.functions.SetPriceFunction;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModChallenges;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModJollyRogers;
/*     */ import xyz.pixelatedw.mineminenomi.setup.ClientProxy;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.APIDefaults;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyPatreon;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyServerProtection;
/*     */ 
/*     */ @Mod("mineminenomi")
/*     */ public class ModMain {
/*     */   public static ModMain instance;
/*     */   public static final String PROJECT_ID = "mineminenomi";
/*     */   public static final String PROJECT_VERSION = "0.8.1";
/*     */   public static final String PROJECT_NAME = "Mine Mine no Mi";
/*  52 */   public static final Logger LOGGER = LogManager.getLogger("mineminenomi");
/*     */   static {
/*  54 */     GET_DF_ICON = (player -> {
/*     */         IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/*     */         
/*     */         ResourceLocation icon = null;
/*     */         
/*     */         if (props.hasDevilFruit()) {
/*     */           String iconName;
/*     */           
/*     */           if (props.hasYamiPower()) {
/*     */             iconName = "yami_yami_no_mi";
/*     */           } else {
/*     */             ItemStack df = DevilFruitHelper.getDevilFruitItem(props.getDevilFruit());
/*     */             
/*     */             iconName = df.getTranslationKey().replace("item.mineminenomi.", "");
/*     */           } 
/*     */           
/*     */           icon = new ResourceLocation("mineminenomi", "textures/items/" + WyHelper.getResourceName(iconName) + ".png");
/*     */         } 
/*     */         
/*     */         return icon;
/*     */       });
/*  75 */     GET_RACE_ICON = (player -> {
/*     */         String iconName = null;
/*     */         IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*     */         if (props.isHuman()) {
/*     */           iconName = "human-rokushiki";
/*     */         } else if (props.isFishman()) {
/*     */           iconName = "fishman-karate";
/*     */         } else if (props.isCyborg()) {
/*     */           iconName = "cyborg-abilities";
/*     */         } else if (props.isMink()) {
/*     */           iconName = "mink-electro";
/*     */         } 
/*     */         return (iconName != null) ? new ResourceLocation("mineminenomi", "textures/gui/icons/" + iconName + ".png") : null;
/*     */       });
/*     */   }
/*     */ 
/*     */   
/*     */   private static final Function<PlayerEntity, ResourceLocation> GET_DF_ICON;
/*     */   
/*     */   private static final Function<PlayerEntity, ResourceLocation> GET_RACE_ICON;
/*     */ 
/*     */   
/*     */   public ModMain() {
/*  98 */     Locale.setDefault(Locale.ENGLISH);
/*     */     
/* 100 */     APIConfig.setup("Mine Mine no Mi", "mineminenomi", "0.8.0");
/* 101 */     APIConfig.setupResourceFolderPath();
/*     */     
/* 103 */     MinecraftForge.EVENT_BUS.register(new APIDefaults.Registry());
/* 104 */     MinecraftForge.EVENT_BUS.register(new WyPatreon.PatreonEvents());
/* 105 */     //DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> {});
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 110 */     APIConfig.AbilityCategory.create("DEVIL_FRUIT", GET_DF_ICON);
/* 111 */     APIConfig.AbilityCategory.create("RACIAL", GET_RACE_ICON);
/* 112 */     APIConfig.AbilityCategory.create("STYLE", player -> null);
/* 113 */     APIConfig.AbilityCategory.create("HAKI", player -> null);
/* 114 */     APIConfig.AbilityCategory.create("FACTION", player -> null);
/* 115 */     APIConfig.AbilityCategory.create("EQUIPMENT", player -> null);
/*     */     
/* 117 */     instance = this;
/*     */     
/* 119 */     LootFunctionManager.registerFunction((ILootFunction.Serializer)new SetPriceFunction.Serializer());
/* 120 */     LootFunctionManager.registerFunction((ILootFunction.Serializer)new SetInfiniteStockFunction.Serializer());
/* 121 */     LootFunctionManager.registerFunction((ILootFunction.Serializer)new SetBellyInPouchFunction.Serializer());
/* 122 */     LootFunctionManager.registerFunction((ILootFunction.Serializer)new SetFruitClueFunction.Serializer());
/* 123 */     LootFunctionManager.registerFunction((ILootFunction.Serializer)new FakeWeaponFunction.Serializer());
/* 124 */     LootFunctionManager.registerFunction((ILootFunction.Serializer)new EncyclopediaCompletionFunction.Serializer());
/*     */     
/* 126 */     LootConditionManager.registerCondition((ILootCondition.AbstractSerializer)new RandomizedFruitsCondition.Serializer());
/*     */     
/* 128 */     IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
/*     */     
/* 130 */     WyRegistry.SOUNDS.register(modEventBus);
/* 131 */     WyRegistry.ENTITY_TYPES.register(modEventBus);
/* 132 */     WyRegistry.BLOCKS.register(modEventBus);
/* 133 */     WyRegistry.ITEMS.register(modEventBus);
/* 134 */     WyRegistry.ABILITIES.register(modEventBus);
/* 135 */     WyRegistry.ENCHANTMENTS.register(modEventBus);
/* 136 */     WyRegistry.TILE_ENTITIES.register(modEventBus);
/* 137 */     WyRegistry.EFFECTS.register(modEventBus);
/* 138 */     WyRegistry.PARTICLE_TYPES.register(modEventBus);
/* 139 */     WyRegistry.QUESTS.register(modEventBus);
/* 140 */     WyRegistry.CONTAINER_TYPES.register(modEventBus);
/* 141 */     WyRegistry.FEATURES.register(modEventBus);
/* 142 */     ModJollyRogers.JOLLY_ROGER_ELEMENTS.register(modEventBus);
/* 143 */     ModChallenges.CHALLENGES.register(modEventBus);
/*     */     
/* 145 */     ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC, "mineminenomi-common.toml");
/* 146 */     //DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> {});
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\ModMain.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */