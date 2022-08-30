/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ 
/*     */ import java.util.Map;
/*     */ import java.util.function.Function;
/*     */ import net.minecraft.entity.EntityClassification;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraft.world.biome.Biomes;
/*     */ import net.minecraftforge.event.RegistryEvent;
/*     */ import net.minecraftforge.eventbus.api.EventPriority;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.entities.DummyEntity;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.ModdedSpawnEggItem;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*     */ public class ModEntities
/*     */ {
/*  72 */   public static final EntityClassification MARINES = EntityClassification.create("marines", "marines", 2, false, false);
/*  73 */   public static final EntityClassification PIRATES = EntityClassification.create("pirates", "pirates", 2, false, false);
/*  74 */   public static final EntityClassification BANDITS = EntityClassification.create("bandits", "bandits", 2, false, false);
/*     */ 
/*     */ 
/*     */   
/*  78 */   public static final EntityType MARINE_WITH_SWORD = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.marines.MarineWithSwordEntity::new, MARINES).build("marine_with_sword");
/*  79 */   public static final EntityType MARINE_WITH_GUN = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.marines.MarineWithGunEntity::new, MARINES).build("marine_with_gun");
/*  80 */   public static final EntityType MARINE_SNIPER = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.marines.MarineSniperEntity::new, MARINES).build("marine_sniper");
/*  81 */   public static final EntityType MARINE_BOMBER = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.marines.MarineBomberEntity::new, MARINES).size(0.8F, 2.3F).build("marine_bomber");
/*  82 */   public static final EntityType MARINE_CAPTAIN = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.marines.MarineCaptainEntity::new, MARINES).build("marine_captain");
/*  83 */   public static final EntityType MARINE_TRADER = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.marines.MarineTraderEntity::new, MARINES).build("marine_trader");
/*  84 */   public static final EntityType MORGAN = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.marines.MorganEntity::new, MARINES).size(0.8F, 2.1F).build("morgan");
/*     */ 
/*     */   
/*  87 */   public static final EntityType PIRATE_WITH_SWORD = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.pirates.PirateWithSwordEntity::new, PIRATES).build("pirate_with_sword");
/*  88 */   public static final EntityType PIRATE_WITH_GUN = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.pirates.PirateWithGunEntity::new, PIRATES).build("pirate_with_gun");
/*  89 */   public static final EntityType PIRATE_BRUTE = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.pirates.PirateBruteEntity::new, PIRATES).size(0.8F, 2.3F).build("pirate_brute");
/*  90 */   public static final EntityType PIRATE_CAPTAIN = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.pirates.PirateCaptainEntity::new, PIRATES).build("pirate_captain");
/*  91 */   public static final EntityType PIRATE_TRADER = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.pirates.PirateTraderEntity::new, PIRATES).build("pirate_trader");
/*     */ 
/*     */   
/*  94 */   public static final EntityType GIN = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates.GinEntity::new).build("gin");
/*  95 */   public static final EntityType DON_KRIEG = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates.DonKriegEntity::new).size(0.8F, 2.1F).build("don_krieg");
/*     */ 
/*     */   
/*  98 */   public static final EntityType BANDIT_WITH_SWORD = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.bandits.BanditWithSwordEntity::new, BANDITS).build("bandit_with_sword");
/*  99 */   public static final EntityType BANDIT_SNIPER = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.bandits.BanditSniperEntity::new, BANDITS).build("bandit_sniper");
/* 100 */   public static final EntityType BANDIT_BRUTE = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.bandits.BanditBruteEntity::new, BANDITS).size(0.8F, 2.3F).build("bandit_brute");
/* 101 */   public static final EntityType BANDIT_BOMBER = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.bandits.BanditBomberEntity::new, BANDITS).size(0.8F, 2.3F).build("bandit_bomber");
/*     */ 
/*     */   
/* 104 */   public static final EntityType SWORDSMAN_TRAINER = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.SwordsmanTrainerEntity::new).build("swordsman_trainer");
/* 105 */   public static final EntityType SNIPER_TRAINER = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.SniperTrainerEntity::new).build("sniper_trainer");
/* 106 */   public static final EntityType ART_OF_WEATHER_TRAINER = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.ArtOfWeatherTrainerEntity::new).build("art_of_weather_trainer");
/* 107 */   public static final EntityType DOCTOR_TRAINER = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.DoctorTrainerEntity::new).build("doctor_trainer");
/* 108 */   public static final EntityType BRAWLER_TRAINER = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.BrawlerTrainerEntity::new).build("brawler_trainer");
/* 109 */   public static final EntityType BLACK_LEG_TRAINER = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.BlackLegTrainerEntity::new).build("black_leg_trainer");
/* 110 */   public static final EntityType SKYPIEAN_CIVILIAN = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.SkypieanCivilianEntity::new).build("skypiean_civilian");
/* 111 */   public static final EntityType SKYPIEAN_TRADER = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.SkypieanTraderEntity::new).build("skypiean_trader");
/*     */ 
/*     */   
/* 114 */   public static final EntityType DEN_DEN_MUSHI = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.animals.DenDenMushiEntity::new).size(0.8F, 0.8F).build("den_den_mushi");
/* 115 */   public static final EntityType LAPAHN = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.animals.LapahnEntity::new, EntityClassification.CREATURE).size(0.8F, 2.5F).build("lapahn");
/* 116 */   public static final EntityType KUNG_FU_DUGONG = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.animals.KungFuDugongEntity::new, EntityClassification.CREATURE).size(0.6F, 1.2F).build("kung_fu_dugong");
/* 117 */   public static final EntityType YAGARA_BULL = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.animals.YagaraBullEntity::new, EntityClassification.WATER_CREATURE).size(1.4F, 1.6F).build("yagara_bull");
/* 118 */   public static final EntityType SEA_KING = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.animals.SeaKingEntity::new, EntityClassification.WATER_CREATURE).size(3.0F, 3.0F).build("sea_king");
/* 119 */   public static final EntityType HUMANDRILL = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.animals.HumandrillEntity::new, EntityClassification.CREATURE).size(1.0F, 2.5F).build("humandrill");
/* 120 */   public static final EntityType FIGHTING_FISH = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.animals.FightingFishEntity::new, EntityClassification.WATER_CREATURE).size(3.5F, 3.5F).build("fighting_fish");
/*     */ 
/*     */   
/* 123 */   public static final EntityType DOPPELMAN = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.ability.DoppelmanEntity::new).build("doppelman");
/* 124 */   public static final EntityType WAX_CLONE = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.ability.WaxCloneEntity::new).build("wax_clone");
/* 125 */   public static final EntityType BLACK_KNIGHT = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.ability.BlackKnightEntity::new).build("black_knight");
/* 126 */   public static final EntityType WANTED_POSTER_PACKAGE = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.WantedPosterPackageEntity::new).size(1.5F, 1.5F).build("wanted_poster_package");
/* 127 */   public static final EntityType VIVRE_CARD = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.VivreCardEntity::new).size(0.4F, 0.4F).build("vivre_card");
/* 128 */   public static final EntityType PHYSICAL_BODY = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.PhysicalBodyEntity::new).build("physical_body");
/* 129 */   public static final EntityType SNIPER_TARGET = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.quest.objectives.SniperTargetEntity::new).build("sniper_target");
/* 130 */   public static final EntityType MIRAGE_CLONE = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.ability.MirageCloneEntity::new).build("mirage_clone");
/* 131 */   public static final EntityType BOMB = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.BombEntity::new).size(0.8F, 0.8F).build("bomb");
/* 132 */   public static final EntityType DEVIL_FRUIT_ITEM = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.DFItemEntity::new).size(0.25F, 0.25F).build("df_item");
/* 133 */   public static final EntityType SPIKE = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.SpikeEntity::new).size(0.2F, 0.2F).build("spike");
/* 134 */   public static final EntityType BOTTOM_HALF_BODY = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.BottomHalfBodyEntity::new).size(0.6F, 0.9F).build("bottom_half_body");
/*     */   
/*     */   public static EntityType DUMMY_MAMMOTH_GUARD;
/*     */   
/*     */   public static EntityType DUMMY_MAMMOTH_HEAVY;
/*     */   
/*     */   public static EntityType DUMMY_KAME_GUARD;
/*     */   
/*     */   public static EntityType DUMMY_BRACHIO_GUARD;
/*     */   
/*     */   public static EntityType DUMMY_BRACHIO_HEAVY;
/*     */   
/*     */   public static EntityType DUMMY_SAI_HEAVY;
/*     */   
/*     */   public static EntityType DUMMY_SAI_WALK;
/*     */   
/*     */   public static EntityType DUMMY_PTERANODON_FLY;
/*     */   
/*     */   public static EntityType DUMMY_ALLOSAURUS_WALK;
/*     */   
/*     */   public static EntityType DUMMY_LEOPARD_WALK;
/*     */   
/*     */   public static EntityType DUMMY_LEOPARD_HEAVY;
/*     */   public static EntityType DUMMY_SHINOKUNI;
/*     */   public static EntityType DUMMY_CORNA_DIO;
/*     */   public static EntityType DUMMY_DAMNED_PUNK;
/* 160 */   private static final Biome.Category[] GENERIC_ONES = new Biome.Category[] { Biome.Category.PLAINS, Biome.Category.FOREST, Biome.Category.BEACH, Biome.Category.EXTREME_HILLS, Biome.Category.TAIGA, Biome.Category.SAVANNA, Biome.Category.SWAMP };
/* 161 */   private static final Biome.Category[] PIRATE_BIOMES = new Biome.Category[] { Biome.Category.BEACH, Biome.Category.SWAMP, Biome.Category.FOREST };
/* 162 */   private static final Biome.Category[] MARINE_BIOMES = new Biome.Category[] { Biome.Category.PLAINS, Biome.Category.TAIGA, Biome.Category.FOREST, Biome.Category.SAVANNA, Biome.Category.BEACH, Biome.Category.SWAMP };
/* 163 */   private static final Biome.Category[] BANDIT_BIOMES = new Biome.Category[] { Biome.Category.EXTREME_HILLS, Biome.Category.FOREST, Biome.Category.MESA };
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 168 */     registerMarineWithSpawnEgg(MARINE_WITH_SWORD, "Marine with Sword");
/* 169 */     registerEntityWorldSpawn(MARINE_WITH_SWORD, 10, 0, 3, MARINE_BIOMES);
/* 170 */     registerMarineWithSpawnEgg(MARINE_WITH_GUN, "Marine with Gun");
/* 171 */     registerEntityWorldSpawn(MARINE_WITH_GUN, 5, 0, 2, MARINE_BIOMES);
/* 172 */     registerMarineWithSpawnEgg(MARINE_SNIPER, "Marine Sniper");
/* 173 */     registerEntityWorldSpawn(MARINE_SNIPER, 1, 0, 1, MARINE_BIOMES);
/* 174 */     registerMarineWithSpawnEgg(MARINE_BOMBER, "Marine Bomber");
/* 175 */     registerEntityWorldSpawn(MARINE_BOMBER, 1, 0, 2, MARINE_BIOMES);
/* 176 */     registerMarineWithSpawnEgg(MARINE_CAPTAIN, "Marine Captain");
/* 177 */     registerMarineWithSpawnEgg(MARINE_TRADER, "Marine Trader");
/* 178 */     WyRegistry.registerEntityType(MORGAN, "Morgan");
/*     */ 
/*     */     
/* 181 */     registerPirateWithSpawnEgg(PIRATE_WITH_SWORD, "Pirate with Sword");
/* 182 */     registerEntityWorldSpawn(PIRATE_WITH_SWORD, 10, 0, 3, PIRATE_BIOMES);
/* 183 */     registerPirateWithSpawnEgg(PIRATE_WITH_GUN, "Pirate with Gun");
/* 184 */     registerEntityWorldSpawn(PIRATE_WITH_GUN, 5, 0, 2, PIRATE_BIOMES);
/* 185 */     registerPirateWithSpawnEgg(PIRATE_BRUTE, "Pirate Brute");
/* 186 */     registerEntityWorldSpawn(PIRATE_BRUTE, 2, 0, 2, PIRATE_BIOMES);
/* 187 */     registerPirateWithSpawnEgg(PIRATE_CAPTAIN, "Pirate Captain");
/* 188 */     registerPirateWithSpawnEgg(PIRATE_TRADER, "Pirate Trader");
/*     */ 
/*     */     
/* 191 */     WyRegistry.registerEntityType(GIN, "Gin");
/* 192 */     WyRegistry.registerEntityType(DON_KRIEG, "Don Krieg");
/*     */ 
/*     */     
/* 195 */     registerBanditWithSpawnEgg(BANDIT_WITH_SWORD, "Bandit with Sword");
/* 196 */     registerEntityWorldSpawn(BANDIT_WITH_SWORD, 10, 0, 5, BANDIT_BIOMES);
/* 197 */     registerBanditWithSpawnEgg(BANDIT_BRUTE, "Bandit Brute");
/* 198 */     registerEntityWorldSpawn(BANDIT_BRUTE, 2, 0, 2, BANDIT_BIOMES);
/* 199 */     registerBanditWithSpawnEgg(BANDIT_SNIPER, "Bandit Sniper");
/* 200 */     registerEntityWorldSpawn(BANDIT_SNIPER, 1, 0, 1, BANDIT_BIOMES);
/* 201 */     registerBanditWithSpawnEgg(BANDIT_BOMBER, "Bandit Bomber");
/* 202 */     registerEntityWorldSpawn(BANDIT_BOMBER, 1, 0, 2, BANDIT_BIOMES);
/*     */ 
/*     */     
/* 205 */     registerFactionlessWithSpawnEgg(SWORDSMAN_TRAINER, "Dojo Sensei");
/* 206 */     registerFactionlessWithSpawnEgg(SNIPER_TRAINER, "Bow Master");
/* 207 */     registerFactionlessWithSpawnEgg(ART_OF_WEATHER_TRAINER, "Weather Wizard");
/* 208 */     registerFactionlessWithSpawnEgg(DOCTOR_TRAINER, "Doctor");
/* 209 */     registerFactionlessWithSpawnEgg(BRAWLER_TRAINER, "Brawler");
/* 210 */     registerFactionlessWithSpawnEgg(BLACK_LEG_TRAINER, "Black Leg Trainer");
/* 211 */     registerFactionlessWithSpawnEgg(SKYPIEAN_CIVILIAN, "Skypiean Civilian");
/* 212 */     registerFactionlessWithSpawnEgg(SKYPIEAN_TRADER, "Skypiean Trader");
/*     */ 
/*     */     
/* 215 */     registerAnimalWithSpawnEgg(DEN_DEN_MUSHI, "Den Den Mushi");
/* 216 */     registerEntityWorldSpawn(DEN_DEN_MUSHI, 12, 2, 5, GENERIC_ONES);
/* 217 */     registerAnimalWithSpawnEgg(LAPAHN, "Lapahn");
/* 218 */     registerEntityWorldSpawn(LAPAHN, 20, 1, 3, new Biome.Category[] { Biome.Category.ICY });
/* 219 */     registerAnimalWithSpawnEgg(KUNG_FU_DUGONG, "Kung Fu Dugong");
/* 220 */     registerEntityWorldSpawn(KUNG_FU_DUGONG, 22, 3, 5, new Biome.Category[] { Biome.Category.BEACH, Biome.Category.MUSHROOM, Biome.Category.SWAMP, Biome.Category.RIVER });
/* 221 */     registerAnimalWithSpawnEgg(YAGARA_BULL, "Yagara Bull");
/* 222 */     registerEntityWorldSpawn(YAGARA_BULL, 5, 1, 3, new Biome.Category[] { Biome.Category.RIVER, Biome.Category.OCEAN });
/*     */ 
/*     */     
/* 225 */     registerAnimalWithSpawnEgg(HUMANDRILL, "Humandrill");
/* 226 */     registerEntityWorldSpawn(HUMANDRILL, 20, 1, 3, new Biome.Category[] { Biome.Category.FOREST, Biome.Category.SWAMP, Biome.Category.PLAINS, Biome.Category.JUNGLE });
/* 227 */     registerAnimalWithSpawnEgg(FIGHTING_FISH, "Fighting Fish");
/* 228 */     registerEntityWorldSpawn(FIGHTING_FISH, 1, 1, 3, new Biome[] { Biomes.OCEAN, Biomes.DEEP_OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN, Biomes.WARM_OCEAN, Biomes.DEEP_WARM_OCEAN });
/*     */ 
/*     */     
/* 231 */     WyRegistry.registerEntityType(DOPPELMAN, "Doppelman");
/* 232 */     WyRegistry.registerEntityType(WAX_CLONE, "Wax Clone");
/* 233 */     WyRegistry.registerEntityType(BLACK_KNIGHT, "Black Knight");
/* 234 */     WyRegistry.registerEntityType(WANTED_POSTER_PACKAGE, "Wanted Poster Package");
/* 235 */     WyRegistry.registerEntityType(VIVRE_CARD, "Vivre Card");
/* 236 */     WyRegistry.registerEntityType(PHYSICAL_BODY, "Physical Body");
/* 237 */     WyRegistry.registerEntityType(SNIPER_TARGET, "Sniper Target");
/* 238 */     WyRegistry.registerEntityType(MIRAGE_CLONE, "Mirage Clone");
/* 239 */     WyRegistry.registerEntityType(BOMB, "Bomb");
/* 240 */     WyRegistry.registerEntityType(SPIKE, "Spike");
/* 241 */     WyRegistry.registerEntityType(DEVIL_FRUIT_ITEM, "Devil Fruit Item");
/* 242 */     WyRegistry.registerEntityType(BOTTOM_HALF_BODY, "Bottom Half Body");
/*     */     
/* 244 */     DUMMY_MAMMOTH_GUARD = WyRegistry.createEntityType(world -> new DummyEntity(DUMMY_MAMMOTH_GUARD, world)).build("dummy_mammoth_guard");
/* 245 */     WyRegistry.registerEntityType(DUMMY_MAMMOTH_GUARD, "Dummy Mammoth Guard");
/* 246 */     DUMMY_MAMMOTH_HEAVY = WyRegistry.createEntityType(world -> new DummyEntity(DUMMY_MAMMOTH_HEAVY, world)).build("dummy_mammoth_heavy");
/* 247 */     WyRegistry.registerEntityType(DUMMY_MAMMOTH_HEAVY, "Dummy Mammoth Heavy");
/*     */     
/* 249 */     DUMMY_KAME_GUARD = WyRegistry.createEntityType(world -> new DummyEntity(DUMMY_KAME_GUARD, world)).build("dummy_kame_guard");
/* 250 */     WyRegistry.registerEntityType(DUMMY_KAME_GUARD, "Dummy Kame Guard");
/*     */     
/* 252 */     DUMMY_BRACHIO_GUARD = WyRegistry.createEntityType(world -> new DummyEntity(DUMMY_BRACHIO_GUARD, world)).build("dummy_brachio_guard");
/* 253 */     WyRegistry.registerEntityType(DUMMY_BRACHIO_GUARD, "Dummy Brachio Guard");
/* 254 */     DUMMY_BRACHIO_HEAVY = WyRegistry.createEntityType(world -> new DummyEntity(DUMMY_BRACHIO_HEAVY, world)).build("dummy_brachio_heavy");
/* 255 */     WyRegistry.registerEntityType(DUMMY_BRACHIO_HEAVY, "Dummy Brachio Heavy");
/*     */     
/* 257 */     DUMMY_SAI_HEAVY = WyRegistry.createEntityType(world -> new DummyEntity(DUMMY_SAI_HEAVY, world)).build("dummy_sai_heavy");
/* 258 */     WyRegistry.registerEntityType(DUMMY_SAI_HEAVY, "Dummy Sai Heavy");
/* 259 */     DUMMY_SAI_WALK = WyRegistry.createEntityType(world -> new DummyEntity(DUMMY_SAI_WALK, world)).build("dummy_sai_walk");
/* 260 */     WyRegistry.registerEntityType(DUMMY_SAI_WALK, "Dummy Sai Walk");
/*     */     
/* 262 */     DUMMY_PTERANODON_FLY = WyRegistry.createEntityType(world -> new DummyEntity(DUMMY_PTERANODON_FLY, world)).build("dummy_ptera_fly");
/* 263 */     WyRegistry.registerEntityType(DUMMY_PTERANODON_FLY, "Dummy Ptera Fly");
/*     */     
/* 265 */     DUMMY_ALLOSAURUS_WALK = WyRegistry.createEntityType(world -> new DummyEntity(DUMMY_ALLOSAURUS_WALK, world)).build("dummy_allosaurus_walk");
/* 266 */     WyRegistry.registerEntityType(DUMMY_ALLOSAURUS_WALK, "Dummy Allosaurus Walk");
/*     */     
/* 268 */     DUMMY_LEOPARD_WALK = WyRegistry.createEntityType(world -> new DummyEntity(DUMMY_LEOPARD_WALK, world)).build("dummy_leopard_walk");
/* 269 */     WyRegistry.registerEntityType(DUMMY_LEOPARD_WALK, "Dummy Leopard Walk");
/* 270 */     DUMMY_LEOPARD_HEAVY = WyRegistry.createEntityType(world -> new DummyEntity(DUMMY_LEOPARD_HEAVY, world)).build("dummy_leopard_heavy");
/* 271 */     WyRegistry.registerEntityType(DUMMY_LEOPARD_HEAVY, "Dummy Leopard Heavy");
/*     */     
/* 273 */     DUMMY_SHINOKUNI = WyRegistry.createEntityType(world -> new DummyEntity(DUMMY_SHINOKUNI, world)).build("dummy_shinokuni");
/* 274 */     WyRegistry.registerEntityType(DUMMY_SHINOKUNI, "Dummy Shinokuni");
/*     */     
/* 276 */     DUMMY_CORNA_DIO = WyRegistry.createEntityType(world -> new DummyEntity(DUMMY_CORNA_DIO, world)).build("dummy_corna_dio");
/* 277 */     WyRegistry.registerEntityType(DUMMY_CORNA_DIO, "Dummy Corna Dio");
/*     */     
/* 279 */     DUMMY_DAMNED_PUNK = WyRegistry.createEntityType(world -> new DummyEntity(DUMMY_DAMNED_PUNK, world)).build("dummy_damned_punk");
/* 280 */     WyRegistry.registerEntityType(DUMMY_DAMNED_PUNK, "Dummy Damned Punk");
/*     */   }
/*     */ 
/*     */   
/*     */   public static void registerEntityWorldSpawn(EntityType<?> type, int weight, int minGroup, int maxGroup, Biome.Category... categories) {
/* 285 */     if (!((Boolean)CommonConfig.INSTANCE.canSpawnWorldNPCs.get()).booleanValue())
/*     */       return; 
/* 287 */     for (Map.Entry<ResourceLocation, Biome> entry : (Iterable<Map.Entry<ResourceLocation, Biome>>)ForgeRegistries.BIOMES.getEntries()) {
/*     */       
/* 289 */       for (Biome.Category category : categories) {
/*     */         
/* 291 */         if (entry != null && ((Biome)entry.getValue()).getCategory() == category)
/*     */         {
/* 293 */           ((Biome)entry.getValue()).getSpawns(type.getClassification()).add(new Biome.SpawnListEntry(type, weight, minGroup, maxGroup));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void registerEntityWorldSpawn(EntityType<?> type, int weight, int minGroup, int maxGroup, Biome... biomes) {
/* 301 */     if (!((Boolean)CommonConfig.INSTANCE.canSpawnWorldNPCs.get()).booleanValue())
/*     */       return; 
/* 303 */     for (Biome biome : biomes) {
/*     */       
/* 305 */       if (biome != null)
/*     */       {
/* 307 */         biome.getSpawns(type.getClassification()).add(new Biome.SpawnListEntry(type, weight, minGroup, maxGroup));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent(priority = EventPriority.LOWEST)
/*     */   public static void onPostRegisterEntities(RegistryEvent.Register<EntityType<?>> event) {
/* 315 */     ModdedSpawnEggItem.initUnaddedEggs();
/*     */   }
/*     */ 
/*     */   
/*     */   private static Item registerMarineWithSpawnEgg(EntityType type, String name) {
/* 320 */     WyRegistry.registerEntityType(type, name);
/* 321 */     return WyRegistry.registerSpawnEggItem(type, name, WyHelper.hexToRGB("#024a81").getRGB(), WyHelper.hexToRGB("#F7F7F7").getRGB());
/*     */   }
/*     */ 
/*     */   
/*     */   private static Item registerPirateWithSpawnEgg(EntityType type, String name) {
/* 326 */     WyRegistry.registerEntityType(type, name);
/* 327 */     return WyRegistry.registerSpawnEggItem(type, name, WyHelper.hexToRGB("#c11c1c").getRGB(), WyHelper.hexToRGB("#F7F7F7").getRGB());
/*     */   }
/*     */ 
/*     */   
/*     */   private static Item registerBanditWithSpawnEgg(EntityType type, String name) {
/* 332 */     WyRegistry.registerEntityType(type, name);
/* 333 */     return WyRegistry.registerSpawnEggItem(type, name, WyHelper.hexToRGB("#785355").getRGB(), WyHelper.hexToRGB("#F7F7F7").getRGB());
/*     */   }
/*     */ 
/*     */   
/*     */   private static Item registerFactionlessWithSpawnEgg(EntityType type, String name) {
/* 338 */     WyRegistry.registerEntityType(type, name);
/* 339 */     return WyRegistry.registerSpawnEggItem(type, name, WyHelper.hexToRGB("#fbbf4c").getRGB(), WyHelper.hexToRGB("#F7F7F7").getRGB());
/*     */   }
/*     */ 
/*     */   
/*     */   private static Item registerAnimalWithSpawnEgg(EntityType type, String name) {
/* 344 */     WyRegistry.registerEntityType(type, name);
/* 345 */     return WyRegistry.registerSpawnEggItem(type, name, WyHelper.hexToRGB("#a7ca34").getRGB(), WyHelper.hexToRGB("#a2f7c8").getRGB());
/*     */   }
/*     */ 
/*     */   
/*     */   private static Item registerDummyWithSpawnEgg(EntityType type, String name) {
/* 350 */     WyRegistry.registerEntityType(type, name);
/* 351 */     return WyRegistry.registerSpawnEggItem(type, name, WyHelper.hexToRGB("#FF00DD").getRGB(), WyHelper.hexToRGB("#EEFF00").getRGB());
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\init\ModEntities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */