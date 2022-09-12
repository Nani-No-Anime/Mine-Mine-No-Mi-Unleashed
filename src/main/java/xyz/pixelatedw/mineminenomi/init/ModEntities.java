package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.entities.DummyEntity;
import xyz.pixelatedw.mineminenomi.wypi.ModdedSpawnEggItem;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;

import java.util.Map;














































@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities
{
  public static final EntityClassification MARINES = EntityClassification.create("marines", "marines", 2, false, false);
  public static final EntityClassification PIRATES = EntityClassification.create("pirates", "pirates", 2, false, false);
  public static final EntityClassification BANDITS = EntityClassification.create("bandits", "bandits", 2, false, false);


  
  public static final EntityType MARINE_WITH_SWORD = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.marines.MarineWithSwordEntity::new, MARINES).build("marine_with_sword");
  public static final EntityType MARINE_WITH_GUN = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.marines.MarineWithGunEntity::new, MARINES).build("marine_with_gun");
  public static final EntityType MARINE_SNIPER = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.marines.MarineSniperEntity::new, MARINES).build("marine_sniper");
  public static final EntityType MARINE_BOMBER = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.marines.MarineBomberEntity::new, MARINES).size(0.8F, 2.3F).build("marine_bomber");
  public static final EntityType MARINE_CAPTAIN = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.marines.MarineCaptainEntity::new, MARINES).build("marine_captain");
  public static final EntityType MARINE_TRADER = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.marines.MarineTraderEntity::new, MARINES).build("marine_trader");
  public static final EntityType MORGAN = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.marines.MorganEntity::new, MARINES).size(0.8F, 2.1F).build("morgan");

  
  public static final EntityType PIRATE_WITH_SWORD = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.pirates.PirateWithSwordEntity::new, PIRATES).build("pirate_with_sword");
  public static final EntityType PIRATE_WITH_GUN = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.pirates.PirateWithGunEntity::new, PIRATES).build("pirate_with_gun");
  public static final EntityType PIRATE_BRUTE = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.pirates.PirateBruteEntity::new, PIRATES).size(0.8F, 2.3F).build("pirate_brute");
  public static final EntityType PIRATE_CAPTAIN = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.pirates.PirateCaptainEntity::new, PIRATES).build("pirate_captain");
  public static final EntityType PIRATE_TRADER = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.pirates.PirateTraderEntity::new, PIRATES).build("pirate_trader");

  
  public static final EntityType GIN = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates.GinEntity::new).build("gin");
  public static final EntityType DON_KRIEG = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates.DonKriegEntity::new).size(0.8F, 2.1F).build("don_krieg");

  
  public static final EntityType BANDIT_WITH_SWORD = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.bandits.BanditWithSwordEntity::new, BANDITS).build("bandit_with_sword");
  public static final EntityType BANDIT_SNIPER = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.bandits.BanditSniperEntity::new, BANDITS).build("bandit_sniper");
  public static final EntityType BANDIT_BRUTE = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.bandits.BanditBruteEntity::new, BANDITS).size(0.8F, 2.3F).build("bandit_brute");
  public static final EntityType BANDIT_BOMBER = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.bandits.BanditBomberEntity::new, BANDITS).size(0.8F, 2.3F).build("bandit_bomber");

  
  public static final EntityType SWORDSMAN_TRAINER = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.SwordsmanTrainerEntity::new).build("swordsman_trainer");
  public static final EntityType SNIPER_TRAINER = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.SniperTrainerEntity::new).build("sniper_trainer");
  public static final EntityType ART_OF_WEATHER_TRAINER = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.ArtOfWeatherTrainerEntity::new).build("art_of_weather_trainer");
  public static final EntityType DOCTOR_TRAINER = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.DoctorTrainerEntity::new).build("doctor_trainer");
  public static final EntityType BRAWLER_TRAINER = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.BrawlerTrainerEntity::new).build("brawler_trainer");
  public static final EntityType BLACK_LEG_TRAINER = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.BlackLegTrainerEntity::new).build("black_leg_trainer");
  public static final EntityType SKYPIEAN_CIVILIAN = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.SkypieanCivilianEntity::new).build("skypiean_civilian");
  public static final EntityType SKYPIEAN_TRADER = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.SkypieanTraderEntity::new).build("skypiean_trader");

  
  public static final EntityType DEN_DEN_MUSHI = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.animals.DenDenMushiEntity::new).size(0.8F, 0.8F).build("den_den_mushi");
  public static final EntityType LAPAHN = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.animals.LapahnEntity::new, EntityClassification.CREATURE).size(0.8F, 2.5F).build("lapahn");
  public static final EntityType KUNG_FU_DUGONG = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.animals.KungFuDugongEntity::new, EntityClassification.CREATURE).size(0.6F, 1.2F).build("kung_fu_dugong");
  public static final EntityType YAGARA_BULL = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.animals.YagaraBullEntity::new, EntityClassification.WATER_CREATURE).size(1.4F, 1.6F).build("yagara_bull");
  public static final EntityType SEA_KING = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.animals.SeaKingEntity::new, EntityClassification.WATER_CREATURE).size(3.0F, 3.0F).build("sea_king");
  public static final EntityType HUMANDRILL = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.animals.HumandrillEntity::new, EntityClassification.CREATURE).size(1.0F, 2.5F).build("humandrill");
  public static final EntityType FIGHTING_FISH = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.animals.FightingFishEntity::new, EntityClassification.WATER_CREATURE).size(3.5F, 3.5F).build("fighting_fish");

  
  public static final EntityType DOPPELMAN = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.ability.DoppelmanEntity::new).build("doppelman");
  public static final EntityType WAX_CLONE = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.ability.WaxCloneEntity::new).build("wax_clone");
  public static final EntityType BLACK_KNIGHT = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.ability.BlackKnightEntity::new).build("black_knight");
  public static final EntityType WANTED_POSTER_PACKAGE = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.WantedPosterPackageEntity::new).size(1.5F, 1.5F).build("wanted_poster_package");
  public static final EntityType VIVRE_CARD = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.VivreCardEntity::new).size(0.4F, 0.4F).build("vivre_card");
  public static final EntityType PHYSICAL_BODY = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.PhysicalBodyEntity::new).build("physical_body");
  public static final EntityType SNIPER_TARGET = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.quest.objectives.SniperTargetEntity::new).build("sniper_target");
  public static final EntityType MIRAGE_CLONE = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.ability.MirageCloneEntity::new).build("mirage_clone");
  public static final EntityType BOMB = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.BombEntity::new).size(0.8F, 0.8F).build("bomb");
  public static final EntityType DEVIL_FRUIT_ITEM = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.DFItemEntity::new).size(0.25F, 0.25F).build("df_item");
  public static final EntityType SPIKE = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.SpikeEntity::new).size(0.2F, 0.2F).build("spike");
  public static final EntityType BOTTOM_HALF_BODY = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.BottomHalfBodyEntity::new).size(0.6F, 0.9F).build("bottom_half_body");
  
  public static EntityType DUMMY_MAMMOTH_GUARD;
  
  public static EntityType DUMMY_MAMMOTH_HEAVY;
  
  public static EntityType DUMMY_KAME_GUARD;
  
  public static EntityType DUMMY_BRACHIO_GUARD;
  
  public static EntityType DUMMY_BRACHIO_HEAVY;
  
  public static EntityType DUMMY_SAI_HEAVY;
  
  public static EntityType DUMMY_SAI_WALK;
  
  public static EntityType DUMMY_PTERANODON_FLY;
  
  public static EntityType DUMMY_ALLOSAURUS_WALK;
  
  public static EntityType DUMMY_LEOPARD_WALK;
  
  public static EntityType DUMMY_LEOPARD_HEAVY;
  public static EntityType DUMMY_SHINOKUNI;
  public static EntityType DUMMY_CORNA_DIO;
  public static EntityType DUMMY_DAMNED_PUNK;
  private static final Biome.Category[] GENERIC_ONES = new Biome.Category[] { Biome.Category.PLAINS, Biome.Category.FOREST, Biome.Category.BEACH, Biome.Category.EXTREME_HILLS, Biome.Category.TAIGA, Biome.Category.SAVANNA, Biome.Category.SWAMP };
  private static final Biome.Category[] PIRATE_BIOMES = new Biome.Category[] { Biome.Category.BEACH, Biome.Category.SWAMP, Biome.Category.FOREST };
  private static final Biome.Category[] MARINE_BIOMES = new Biome.Category[] { Biome.Category.PLAINS, Biome.Category.TAIGA, Biome.Category.FOREST, Biome.Category.SAVANNA, Biome.Category.BEACH, Biome.Category.SWAMP };
  private static final Biome.Category[] BANDIT_BIOMES = new Biome.Category[] { Biome.Category.EXTREME_HILLS, Biome.Category.FOREST, Biome.Category.MESA };


  
  static {
    registerMarineWithSpawnEgg(MARINE_WITH_SWORD, "Marine with Sword");
    registerEntityWorldSpawn(MARINE_WITH_SWORD, 10, 0, 3, MARINE_BIOMES);
    registerMarineWithSpawnEgg(MARINE_WITH_GUN, "Marine with Gun");
    registerEntityWorldSpawn(MARINE_WITH_GUN, 5, 0, 2, MARINE_BIOMES);
    registerMarineWithSpawnEgg(MARINE_SNIPER, "Marine Sniper");
    registerEntityWorldSpawn(MARINE_SNIPER, 1, 0, 1, MARINE_BIOMES);
    registerMarineWithSpawnEgg(MARINE_BOMBER, "Marine Bomber");
    registerEntityWorldSpawn(MARINE_BOMBER, 1, 0, 2, MARINE_BIOMES);
    registerMarineWithSpawnEgg(MARINE_CAPTAIN, "Marine Captain");
    registerMarineWithSpawnEgg(MARINE_TRADER, "Marine Trader");
    WyRegistry.registerEntityType(MORGAN, "Morgan");

    
    registerPirateWithSpawnEgg(PIRATE_WITH_SWORD, "Pirate with Sword");
    registerEntityWorldSpawn(PIRATE_WITH_SWORD, 10, 0, 3, PIRATE_BIOMES);
    registerPirateWithSpawnEgg(PIRATE_WITH_GUN, "Pirate with Gun");
    registerEntityWorldSpawn(PIRATE_WITH_GUN, 5, 0, 2, PIRATE_BIOMES);
    registerPirateWithSpawnEgg(PIRATE_BRUTE, "Pirate Brute");
    registerEntityWorldSpawn(PIRATE_BRUTE, 2, 0, 2, PIRATE_BIOMES);
    registerPirateWithSpawnEgg(PIRATE_CAPTAIN, "Pirate Captain");
    registerPirateWithSpawnEgg(PIRATE_TRADER, "Pirate Trader");

    
    WyRegistry.registerEntityType(GIN, "Gin");
    WyRegistry.registerEntityType(DON_KRIEG, "Don Krieg");

    
    registerBanditWithSpawnEgg(BANDIT_WITH_SWORD, "Bandit with Sword");
    registerEntityWorldSpawn(BANDIT_WITH_SWORD, 10, 0, 5, BANDIT_BIOMES);
    registerBanditWithSpawnEgg(BANDIT_BRUTE, "Bandit Brute");
    registerEntityWorldSpawn(BANDIT_BRUTE, 2, 0, 2, BANDIT_BIOMES);
    registerBanditWithSpawnEgg(BANDIT_SNIPER, "Bandit Sniper");
    registerEntityWorldSpawn(BANDIT_SNIPER, 1, 0, 1, BANDIT_BIOMES);
    registerBanditWithSpawnEgg(BANDIT_BOMBER, "Bandit Bomber");
    registerEntityWorldSpawn(BANDIT_BOMBER, 1, 0, 2, BANDIT_BIOMES);

    
    registerFactionlessWithSpawnEgg(SWORDSMAN_TRAINER, "Dojo Sensei");
    registerFactionlessWithSpawnEgg(SNIPER_TRAINER, "Bow Master");
    registerFactionlessWithSpawnEgg(ART_OF_WEATHER_TRAINER, "Weather Wizard");
    registerFactionlessWithSpawnEgg(DOCTOR_TRAINER, "Doctor");
    registerFactionlessWithSpawnEgg(BRAWLER_TRAINER, "Brawler");
    registerFactionlessWithSpawnEgg(BLACK_LEG_TRAINER, "Black Leg Trainer");
    registerFactionlessWithSpawnEgg(SKYPIEAN_CIVILIAN, "Skypiean Civilian");
    registerFactionlessWithSpawnEgg(SKYPIEAN_TRADER, "Skypiean Trader");

    
    registerAnimalWithSpawnEgg(DEN_DEN_MUSHI, "Den Den Mushi");
    registerEntityWorldSpawn(DEN_DEN_MUSHI, 12, 2, 5, GENERIC_ONES);
    registerAnimalWithSpawnEgg(LAPAHN, "Lapahn");
    registerEntityWorldSpawn(LAPAHN, 20, 1, 3, new Biome.Category[] { Biome.Category.ICY });
    registerAnimalWithSpawnEgg(KUNG_FU_DUGONG, "Kung Fu Dugong");
    registerEntityWorldSpawn(KUNG_FU_DUGONG, 22, 3, 5, new Biome.Category[] { Biome.Category.BEACH, Biome.Category.MUSHROOM, Biome.Category.SWAMP, Biome.Category.RIVER });
    registerAnimalWithSpawnEgg(YAGARA_BULL, "Yagara Bull");
    registerEntityWorldSpawn(YAGARA_BULL, 5, 1, 3, new Biome.Category[] { Biome.Category.RIVER, Biome.Category.OCEAN });

    
    registerAnimalWithSpawnEgg(HUMANDRILL, "Humandrill");
    registerEntityWorldSpawn(HUMANDRILL, 20, 1, 3, new Biome.Category[] { Biome.Category.FOREST, Biome.Category.SWAMP, Biome.Category.PLAINS, Biome.Category.JUNGLE });
    registerAnimalWithSpawnEgg(FIGHTING_FISH, "Fighting Fish");
    registerEntityWorldSpawn(FIGHTING_FISH, 1, 1, 3, new Biome[] { Biomes.OCEAN, Biomes.DEEP_OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN, Biomes.WARM_OCEAN, Biomes.DEEP_WARM_OCEAN });

    
    WyRegistry.registerEntityType(DOPPELMAN, "Doppelman");
    WyRegistry.registerEntityType(WAX_CLONE, "Wax Clone");
    WyRegistry.registerEntityType(BLACK_KNIGHT, "Black Knight");
    WyRegistry.registerEntityType(WANTED_POSTER_PACKAGE, "Wanted Poster Package");
    WyRegistry.registerEntityType(VIVRE_CARD, "Vivre Card");
    WyRegistry.registerEntityType(PHYSICAL_BODY, "Physical Body");
    WyRegistry.registerEntityType(SNIPER_TARGET, "Sniper Target");
    WyRegistry.registerEntityType(MIRAGE_CLONE, "Mirage Clone");
    WyRegistry.registerEntityType(BOMB, "Bomb");
    WyRegistry.registerEntityType(SPIKE, "Spike");
    WyRegistry.registerEntityType(DEVIL_FRUIT_ITEM, "Devil Fruit Item");
    WyRegistry.registerEntityType(BOTTOM_HALF_BODY, "Bottom Half Body");
    
    DUMMY_MAMMOTH_GUARD = WyRegistry.createEntityType(world -> new DummyEntity(DUMMY_MAMMOTH_GUARD, world)).build("dummy_mammoth_guard");
    WyRegistry.registerEntityType(DUMMY_MAMMOTH_GUARD, "Dummy Mammoth Guard");
    DUMMY_MAMMOTH_HEAVY = WyRegistry.createEntityType(world -> new DummyEntity(DUMMY_MAMMOTH_HEAVY, world)).build("dummy_mammoth_heavy");
    WyRegistry.registerEntityType(DUMMY_MAMMOTH_HEAVY, "Dummy Mammoth Heavy");
    
    DUMMY_KAME_GUARD = WyRegistry.createEntityType(world -> new DummyEntity(DUMMY_KAME_GUARD, world)).build("dummy_kame_guard");
    WyRegistry.registerEntityType(DUMMY_KAME_GUARD, "Dummy Kame Guard");
    
    DUMMY_BRACHIO_GUARD = WyRegistry.createEntityType(world -> new DummyEntity(DUMMY_BRACHIO_GUARD, world)).build("dummy_brachio_guard");
    WyRegistry.registerEntityType(DUMMY_BRACHIO_GUARD, "Dummy Brachio Guard");
    DUMMY_BRACHIO_HEAVY = WyRegistry.createEntityType(world -> new DummyEntity(DUMMY_BRACHIO_HEAVY, world)).build("dummy_brachio_heavy");
    WyRegistry.registerEntityType(DUMMY_BRACHIO_HEAVY, "Dummy Brachio Heavy");
    
    DUMMY_SAI_HEAVY = WyRegistry.createEntityType(world -> new DummyEntity(DUMMY_SAI_HEAVY, world)).build("dummy_sai_heavy");
    WyRegistry.registerEntityType(DUMMY_SAI_HEAVY, "Dummy Sai Heavy");
    DUMMY_SAI_WALK = WyRegistry.createEntityType(world -> new DummyEntity(DUMMY_SAI_WALK, world)).build("dummy_sai_walk");
    WyRegistry.registerEntityType(DUMMY_SAI_WALK, "Dummy Sai Walk");
    
    DUMMY_PTERANODON_FLY = WyRegistry.createEntityType(world -> new DummyEntity(DUMMY_PTERANODON_FLY, world)).build("dummy_ptera_fly");
    WyRegistry.registerEntityType(DUMMY_PTERANODON_FLY, "Dummy Ptera Fly");
    
    DUMMY_ALLOSAURUS_WALK = WyRegistry.createEntityType(world -> new DummyEntity(DUMMY_ALLOSAURUS_WALK, world)).build("dummy_allosaurus_walk");
    WyRegistry.registerEntityType(DUMMY_ALLOSAURUS_WALK, "Dummy Allosaurus Walk");
    
    DUMMY_LEOPARD_WALK = WyRegistry.createEntityType(world -> new DummyEntity(DUMMY_LEOPARD_WALK, world)).build("dummy_leopard_walk");
    WyRegistry.registerEntityType(DUMMY_LEOPARD_WALK, "Dummy Leopard Walk");
    DUMMY_LEOPARD_HEAVY = WyRegistry.createEntityType(world -> new DummyEntity(DUMMY_LEOPARD_HEAVY, world)).build("dummy_leopard_heavy");
    WyRegistry.registerEntityType(DUMMY_LEOPARD_HEAVY, "Dummy Leopard Heavy");
    
    DUMMY_SHINOKUNI = WyRegistry.createEntityType(world -> new DummyEntity(DUMMY_SHINOKUNI, world)).build("dummy_shinokuni");
    WyRegistry.registerEntityType(DUMMY_SHINOKUNI, "Dummy Shinokuni");
    
    DUMMY_CORNA_DIO = WyRegistry.createEntityType(world -> new DummyEntity(DUMMY_CORNA_DIO, world)).build("dummy_corna_dio");
    WyRegistry.registerEntityType(DUMMY_CORNA_DIO, "Dummy Corna Dio");
    
    DUMMY_DAMNED_PUNK = WyRegistry.createEntityType(world -> new DummyEntity(DUMMY_DAMNED_PUNK, world)).build("dummy_damned_punk");
    WyRegistry.registerEntityType(DUMMY_DAMNED_PUNK, "Dummy Damned Punk");
  }

  
  public static void registerEntityWorldSpawn(EntityType<?> type, int weight, int minGroup, int maxGroup, Biome.Category... categories) {
    if (!((Boolean)CommonConfig.INSTANCE.canSpawnWorldNPCs.get()).booleanValue())
      return; 
    for (Map.Entry<ResourceLocation, Biome> entry : (Iterable<Map.Entry<ResourceLocation, Biome>>)ForgeRegistries.BIOMES.getEntries()) {
      
      for (Biome.Category category : categories) {
        
        if (entry != null && ((Biome)entry.getValue()).getCategory() == category)
        {
          ((Biome)entry.getValue()).getSpawns(type.getClassification()).add(new Biome.SpawnListEntry(type, weight, minGroup, maxGroup));
        }
      } 
    } 
  }

  
  public static void registerEntityWorldSpawn(EntityType<?> type, int weight, int minGroup, int maxGroup, Biome... biomes) {
    if (!((Boolean)CommonConfig.INSTANCE.canSpawnWorldNPCs.get()).booleanValue())
      return; 
    for (Biome biome : biomes) {
      
      if (biome != null)
      {
        biome.getSpawns(type.getClassification()).add(new Biome.SpawnListEntry(type, weight, minGroup, maxGroup));
      }
    } 
  }

  
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public static void onPostRegisterEntities(RegistryEvent.Register<EntityType<?>> event) {
    ModdedSpawnEggItem.initUnaddedEggs();
  }

  
  private static Item registerMarineWithSpawnEgg(EntityType type, String name) {
    WyRegistry.registerEntityType(type, name);
    return WyRegistry.registerSpawnEggItem(type, name, WyHelper.hexToRGB("#024a81").getRGB(), WyHelper.hexToRGB("#F7F7F7").getRGB());
  }

  
  private static Item registerPirateWithSpawnEgg(EntityType type, String name) {
    WyRegistry.registerEntityType(type, name);
    return WyRegistry.registerSpawnEggItem(type, name, WyHelper.hexToRGB("#c11c1c").getRGB(), WyHelper.hexToRGB("#F7F7F7").getRGB());
  }

  
  private static Item registerBanditWithSpawnEgg(EntityType type, String name) {
    WyRegistry.registerEntityType(type, name);
    return WyRegistry.registerSpawnEggItem(type, name, WyHelper.hexToRGB("#785355").getRGB(), WyHelper.hexToRGB("#F7F7F7").getRGB());
  }

  
  private static Item registerFactionlessWithSpawnEgg(EntityType type, String name) {
    WyRegistry.registerEntityType(type, name);
    return WyRegistry.registerSpawnEggItem(type, name, WyHelper.hexToRGB("#fbbf4c").getRGB(), WyHelper.hexToRGB("#F7F7F7").getRGB());
  }

  
  private static Item registerAnimalWithSpawnEgg(EntityType type, String name) {
    WyRegistry.registerEntityType(type, name);
    return WyRegistry.registerSpawnEggItem(type, name, WyHelper.hexToRGB("#a7ca34").getRGB(), WyHelper.hexToRGB("#a2f7c8").getRGB());
  }

  
  private static Item registerDummyWithSpawnEgg(EntityType type, String name) {
    WyRegistry.registerEntityType(type, name);
    return WyRegistry.registerSpawnEggItem(type, name, WyHelper.hexToRGB("#FF00DD").getRGB(), WyHelper.hexToRGB("#EEFF00").getRGB());
  }
}


