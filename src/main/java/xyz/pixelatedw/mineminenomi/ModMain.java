package xyz.pixelatedw.mineminenomi;

import java.util.Locale;
import java.util.function.Function;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraft.world.storage.loot.conditions.LootConditionManager;
import net.minecraft.world.storage.loot.functions.ILootFunction;
import net.minecraft.world.storage.loot.functions.LootFunctionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.conditions.RandomizedFruitsCondition;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.functions.EncyclopediaCompletionFunction;
import xyz.pixelatedw.mineminenomi.data.functions.FakeWeaponFunction;
import xyz.pixelatedw.mineminenomi.data.functions.SetBellyInPouchFunction;
import xyz.pixelatedw.mineminenomi.data.functions.SetFruitClueFunction;
import xyz.pixelatedw.mineminenomi.data.functions.SetInfiniteStockFunction;
import xyz.pixelatedw.mineminenomi.data.functions.SetPriceFunction;
import xyz.pixelatedw.mineminenomi.init.ModChallenges;
import xyz.pixelatedw.mineminenomi.init.ModJollyRogers;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.APIDefaults;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.WyPatreon;
import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;

@Mod("mineminenomi")
public class ModMain {
  public static ModMain instance;
  public static final String PROJECT_ID = "mineminenomi";
  public static final String PROJECT_VERSION = "0.8.1";
  public static final String PROJECT_NAME = "Mine Mine no Mi";
  public static final Logger LOGGER = LogManager.getLogger("mineminenomi");
  static {
    GET_DF_ICON = (player -> {
        IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
        
        ResourceLocation icon = null;
        
        if (props.hasDevilFruit()) {
          String iconName;
          
          if (props.hasYamiPower()) {
            iconName = "yami_yami_no_mi";
          } else {
            ItemStack df = DevilFruitHelper.getDevilFruitItem(props.getDevilFruit());
            
            iconName = df.getTranslationKey().replace("item.mineminenomi.", "");
          } 
          
          icon = new ResourceLocation("mineminenomi", "textures/items/" + WyHelper.getResourceName(iconName) + ".png");
        } 
        
        return icon;
      });
    GET_RACE_ICON = (player -> {
        String iconName = null;
        IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
        if (props.isHuman()) {
          iconName = "human-rokushiki";
        } else if (props.isFishman()) {
          iconName = "fishman-karate";
        } else if (props.isCyborg()) {
          iconName = "cyborg-abilities";
        } else if (props.isMink()) {
          iconName = "mink-electro";
        } 
        return (iconName != null) ? new ResourceLocation("mineminenomi", "textures/gui/icons/" + iconName + ".png") : null;
      });
  }

  
  private static final Function<PlayerEntity, ResourceLocation> GET_DF_ICON;
  
  private static final Function<PlayerEntity, ResourceLocation> GET_RACE_ICON;

  
  public ModMain() {
    Locale.setDefault(Locale.ENGLISH);
    
    APIConfig.setup("Mine Mine no Mi", "mineminenomi", "0.8.0");
    APIConfig.setupResourceFolderPath();
    
    MinecraftForge.EVENT_BUS.register(new APIDefaults.Registry());
    MinecraftForge.EVENT_BUS.register(new WyPatreon.PatreonEvents());
    //DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> {});



    
    APIConfig.AbilityCategory.create("DEVIL_FRUIT", GET_DF_ICON);
    APIConfig.AbilityCategory.create("RACIAL", GET_RACE_ICON);
    APIConfig.AbilityCategory.create("STYLE", player -> null);
    APIConfig.AbilityCategory.create("HAKI", player -> null);
    APIConfig.AbilityCategory.create("FACTION", player -> null);
    APIConfig.AbilityCategory.create("EQUIPMENT", player -> null);
    
    instance = this;
    
    LootFunctionManager.registerFunction((ILootFunction.Serializer)new SetPriceFunction.Serializer());
    LootFunctionManager.registerFunction((ILootFunction.Serializer)new SetInfiniteStockFunction.Serializer());
    LootFunctionManager.registerFunction((ILootFunction.Serializer)new SetBellyInPouchFunction.Serializer());
    LootFunctionManager.registerFunction((ILootFunction.Serializer)new SetFruitClueFunction.Serializer());
    LootFunctionManager.registerFunction((ILootFunction.Serializer)new FakeWeaponFunction.Serializer());
    LootFunctionManager.registerFunction((ILootFunction.Serializer)new EncyclopediaCompletionFunction.Serializer());
    
    LootConditionManager.registerCondition((ILootCondition.AbstractSerializer)new RandomizedFruitsCondition.Serializer());
    
    IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    
    WyRegistry.SOUNDS.register(modEventBus);
    WyRegistry.ENTITY_TYPES.register(modEventBus);
    WyRegistry.BLOCKS.register(modEventBus);
    WyRegistry.ITEMS.register(modEventBus);
    WyRegistry.ABILITIES.register(modEventBus);
    WyRegistry.ENCHANTMENTS.register(modEventBus);
    WyRegistry.TILE_ENTITIES.register(modEventBus);
    WyRegistry.EFFECTS.register(modEventBus);
    WyRegistry.PARTICLE_TYPES.register(modEventBus);
    WyRegistry.QUESTS.register(modEventBus);
    WyRegistry.CONTAINER_TYPES.register(modEventBus);
    WyRegistry.FEATURES.register(modEventBus);
    ModJollyRogers.JOLLY_ROGER_ELEMENTS.register(modEventBus);
    ModChallenges.CHALLENGES.register(modEventBus);
    
    ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC, "mineminenomi-common.toml");
    //DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> {});
  }
}


