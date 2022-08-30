/*     */ package xyz.pixelatedw.mineminenomi.config;
/*     */ 
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.Paths;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.common.ForgeConfigSpec;
/*     */ import net.minecraftforge.fml.common.Mod;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.ModMain;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi", bus = Mod.EventBusSubscriber.Bus.MOD)
/*     */ public class CommonConfig
/*     */ {
/*  29 */   public static final Path CONFIG_PATH = Paths.get("config", new String[] { "mineminenomi-common.toml" });
/*     */   
/*     */   public static final CommonConfig INSTANCE;
/*     */   
/*     */   public static final ForgeConfigSpec SPEC;
/*     */   
/*     */   public ForgeConfigSpec.BooleanValue extraHearts;
/*     */   
/*     */   public ForgeConfigSpec.EnumValue<KeepStatsLogic> keepStatsAfterDeath;
/*     */   
/*     */   public Map<String, ForgeConfigSpec.BooleanValue> statsToKeep;
/*     */   
/*     */   public ForgeConfigSpec.EnumValue<HaoshokuUnlockLogic> haoshokuUnlockLogic;
/*     */   
/*     */   public ForgeConfigSpec.EnumValue<LogiaProjectileHitLogic> logiaProjectileHitLogic;
/*     */   
/*     */   public ForgeConfigSpec.BooleanValue logiaInvulnerability;
/*     */   
/*     */   public ForgeConfigSpec.BooleanValue waterChecks;
/*     */   
/*     */   public ForgeConfigSpec.BooleanValue mobRewards;
/*     */   
/*     */   public ForgeConfigSpec.DoubleValue dorikiRewardMultiplier;
/*     */   
/*     */   public ForgeConfigSpec.DoubleValue hakiExpMultiplier;
/*     */   
/*     */   public ForgeConfigSpec.BooleanValue minimumDorikiPerKill;
/*     */   
/*     */   public ForgeConfigSpec.BooleanValue destroySpawner;
/*     */   
/*     */   public ForgeConfigSpec.BooleanValue destroyWater;
/*     */   
/*     */   public ForgeConfigSpec.BooleanValue despawnWithNametags;
/*     */   
/*     */   public ForgeConfigSpec.IntValue dorikiLimit;
/*     */   
/*     */   public ForgeConfigSpec.IntValue hakiExpLimit;
/*     */   
/*     */   public ForgeConfigSpec.IntValue dorikiKeepPercentage;
/*     */   
/*     */   public ForgeConfigSpec.IntValue bountyKeepPercentage;
/*     */   
/*     */   public ForgeConfigSpec.IntValue bellyKeepPercentage;
/*     */   
/*     */   public ForgeConfigSpec.IntValue hakiExpKeepPercentage;
/*     */   
/*     */   public ForgeConfigSpec.IntValue loyaltyKeepPercentage;
/*     */   
/*     */   public ForgeConfigSpec.BooleanValue combatPickup;
/*     */   
/*     */   public ForgeConfigSpec.BooleanValue nativeHaki;
/*     */   
/*     */   public ForgeConfigSpec.ConfigValue<List<? extends String>> bannedAbilities;
/*     */   
/*     */   public ForgeConfigSpec.ConfigValue<List<? extends String>> protectionWhitelist;
/*     */   public ForgeConfigSpec.EnumValue<OneFruitPerWorldLogic> oneFruitPerWorldLogic;
/*     */   public ForgeConfigSpec.BooleanValue abilityInvulnerability;
/*     */   public ForgeConfigSpec.BooleanValue logiaReturnEffect;
/*     */   public ForgeConfigSpec.DoubleValue devilFruitDropsFromLeavesChance;
/*     */   public ForgeConfigSpec.BooleanValue abilityGriefing;
/*     */   public ForgeConfigSpec.BooleanValue specialFlying;
/*     */   public ForgeConfigSpec.BooleanValue specialSourceEvents;
/*     */   public ForgeConfigSpec.BooleanValue yamiPower;
/*     */   public ForgeConfigSpec.BooleanValue abilityFraudChecks;
/*     */   public ForgeConfigSpec.BooleanValue stopContinuousAbilities;
/*     */   public ForgeConfigSpec.DoubleValue chanceForDroppedAppleReincarnation;
/*     */   public ForgeConfigSpec.DoubleValue chanceForInventoryAppleReincarnation;
/*     */   public ForgeConfigSpec.DoubleValue chanceForChestAppleReincarnation;
/*     */   public ForgeConfigSpec.BooleanValue unableToPickupDF;
/*     */   public ForgeConfigSpec.IntValue abilityBars;
/*     */   public ForgeConfigSpec.IntValue fruitsLimitInInventory;
/*     */   public ForgeConfigSpec.IntValue daysForInactivity;
/*     */   public ForgeConfigSpec.BooleanValue sharedCooldowns;
/*     */   public ForgeConfigSpec.BooleanValue animeScreaming;
/*     */   public ForgeConfigSpec.BooleanValue randomizeRace;
/*     */   public ForgeConfigSpec.BooleanValue randomizedFruits;
/*     */   public ForgeConfigSpec.BooleanValue spawnTrainingStructures;
/*     */   public ForgeConfigSpec.DoubleValue spawnChanceTrainingStructure;
/*     */   public ForgeConfigSpec.BooleanValue spawnSmallShips;
/*     */   public ForgeConfigSpec.DoubleValue spawnChanceSmallShip;
/*     */   public ForgeConfigSpec.BooleanValue spawnCamps;
/*     */   public ForgeConfigSpec.DoubleValue spawnChanceCamps;
/*     */   public ForgeConfigSpec.BooleanValue spawnSmallBases;
/*     */   public ForgeConfigSpec.DoubleValue spawnChanceSmallBase;
/*     */   public ForgeConfigSpec.BooleanValue spawnLargeShips;
/*     */   public ForgeConfigSpec.DoubleValue spawnChanceLargeShips;
/*     */   public ForgeConfigSpec.BooleanValue spawnLargeBases;
/*     */   public ForgeConfigSpec.DoubleValue spawnChanceLargeBase;
/*     */   public ForgeConfigSpec.BooleanValue spawnPoneglyphs;
/*     */   public ForgeConfigSpec.DoubleValue spawnChancePoneglyph;
/*     */   public ForgeConfigSpec.BooleanValue spawnMediumShips;
/*     */   public ForgeConfigSpec.DoubleValue spawnChanceMediumShip;
/*     */   public ForgeConfigSpec.BooleanValue spawnGhostShips;
/*     */   public ForgeConfigSpec.DoubleValue spawnChanceGhostShip;
/*     */   public ForgeConfigSpec.BooleanValue spawnSkyIslands;
/*     */   public ForgeConfigSpec.DoubleValue spawnChanceSkyIsland;
/*     */   public ForgeConfigSpec.BooleanValue spawnWatchTowers;
/*     */   public ForgeConfigSpec.DoubleValue spawnChanceWatchTower;
/*     */   public ForgeConfigSpec.BooleanValue enableQuests;
/*     */   public ForgeConfigSpec.BooleanValue questProgression;
/*     */   public ForgeConfigSpec.BooleanValue wantedPosterPackages;
/*     */   public ForgeConfigSpec.IntValue timeBetweenPackageDrops;
/*     */   public ForgeConfigSpec.BooleanValue enableChallenges;
/*     */   public ForgeConfigSpec.BooleanValue enableReChallenges;
/*     */   public ForgeConfigSpec.BooleanValue canSpawnWorldNPCs;
/*     */   public ForgeConfigSpec.BooleanValue canSpawnTraders;
/*     */   public ForgeConfigSpec.IntValue timeBetweenTraderSpawns;
/*     */   public ForgeConfigSpec.IntValue chanceForTraderSpawn;
/*     */   public ForgeConfigSpec.BooleanValue canSpawnTrainers;
/*     */   public ForgeConfigSpec.IntValue timeBetweenTrainerSpawns;
/*     */   public ForgeConfigSpec.IntValue chanceForTrainerSpawn;
/*     */   public ForgeConfigSpec.BooleanValue canSpawnAmbushes;
/*     */   public ForgeConfigSpec.IntValue timeBetweenAmbushSpawns;
/*     */   public ForgeConfigSpec.IntValue chanceForAmbushSpawn;
/*     */   public ForgeConfigSpec.IntValue bountyRequirement;
/*     */   public ForgeConfigSpec.BooleanValue worldMessageOnCrewCreate;
/*     */   public ForgeConfigSpec.BooleanValue disableFriendlyFire;
/*     */   public ForgeConfigSpec.IntValue kairosekiSpawnCount;
/*     */   public ForgeConfigSpec.IntValue kairosekiSpawnMaxHeight;
/*     */   
/*     */   public enum KeepStatsLogic
/*     */     implements IConfigEnum
/*     */   {
/* 152 */     NONE, AUTO, FULL, CUSTOM;
/*     */ 
/*     */ 
/*     */     
/*     */     public <T extends CommonConfig.IConfigEnum> T next() {
/* 157 */       return (T)values()[(ordinal() + 1) % (values()).length];
/*     */     }
/*     */   }
/*     */   
/*     */   public enum HaoshokuUnlockLogic
/*     */     implements IConfigEnum {
/* 163 */     NONE, RANDOM, EXPERIENCE, COMBINED, TRUE_RANDOM;
/*     */ 
/*     */ 
/*     */     
/*     */     public <T extends CommonConfig.IConfigEnum> T next() {
/* 168 */       return (T)values()[(ordinal() + 1) % (values()).length];
/*     */     }
/*     */   }
/*     */   
/*     */   public enum OneFruitPerWorldLogic
/*     */     implements IConfigEnum {
/* 174 */     NONE, SIMPLE, EXTENDED;
/*     */ 
/*     */ 
/*     */     
/*     */     public <T extends CommonConfig.IConfigEnum> T next() {
/* 179 */       return (T)values()[(ordinal() + 1) % (values()).length];
/*     */     }
/*     */   }
/*     */   
/*     */   public enum LogiaProjectileHitLogic
/*     */     implements IConfigEnum {
/* 185 */     NONE, HAKI, EXTENDED;
/*     */ 
/*     */ 
/*     */     
/*     */     public <T extends CommonConfig.IConfigEnum> T next() {
/* 190 */       return (T)values()[(ordinal() + 1) % (values()).length];
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 196 */     Pair<CommonConfig, ForgeConfigSpec> pair = (new ForgeConfigSpec.Builder()).configure(CommonConfig::new);
/* 197 */     SPEC = (ForgeConfigSpec)pair.getRight();
/* 198 */     INSTANCE = (CommonConfig)pair.getLeft();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void save() {
/* 207 */     ModMain.LOGGER.warn("save() method got accessed server side! This is bad!");
/*     */   }
/*     */ 
/*     */   
/*     */   public CommonConfig(ForgeConfigSpec.Builder builder) {
/* 212 */     builder.push("General");
/*     */     
/* 214 */     this.extraHearts = builder.comment("Allows players to receive extra hearts based on their doriki\nDefault: true").define("Extra Hearts", true);
/* 215 */     this.mobRewards = builder.comment("Allows mobs to reward doriki, bounty or items\nDefault: true").define("Mob Rewards", true);
/* 216 */     this.dorikiRewardMultiplier = builder.comment("Multiplies any doriki gained by this amount\nDefault: 1").defineInRange("Doriki Reward Multiplier", 1.0D, 0.0D, 10.0D);
/* 217 */     this.minimumDorikiPerKill = builder.comment("Guarantees a minimum of 1 doriki per kill\n If used together with a Haki Exp Multiplier with a multiplier less than <1.0 it will convert it to chances\nDefault: false").define("Minimum Doriki per Kill", false);
/* 218 */     this.hakiExpMultiplier = builder.comment("Multiplies any haki gained by this amount\nDefault: 1").defineInRange("Haki Exp Multiplier", 1.0D, 0.0D, 10.0D);
/* 219 */     this.haoshokuUnlockLogic = builder.comment("Responsible for how player unlock Haoshoku Haki; \n NONE - Haoshoku Haki cannot be unlocked naturally \n RANDOM - Only a few chosen ones receive it when they spawn \n EXPERIENCE - Will unlock based on the total amount of Haki experience a player has\n COMBINED - Combiens the logic of RANDOM and EXPERIENCE\n TRUE_RANDOM - Each world / server has its own pool of randomly chosen Haoshoku Haki users\nDefault: EXPERIENCE").defineEnum("Haoshoku Haki Unlock Logic", HaoshokuUnlockLogic.EXPERIENCE, HaoshokuUnlockLogic.values());
/* 220 */     this.destroySpawner = builder.comment("Destroys the spawner after all its spawns are killed\nDefault: true").define("Destroy Spawner", true);
/* 221 */     this.destroyWater = builder.comment("Allows big explosions to destroy water \nDefault: false").define("Explosions can destroy water", false);
/* 222 */     this.despawnWithNametags = builder.comment("Normally despawns traders and trainers even if they're nametagged \nDefault: false").define("Despawn NPCs with Nametags", false);
/* 223 */     this.dorikiLimit = builder.comment("Sets a new limit for maximum doriki a player may obtain \nDefault: 10000").defineInRange("Doriki Limit", 10000, 0, 50000);
/* 224 */     this.hakiExpLimit = builder.comment("Sets a new limit for maximum haki exp a player may obtain \nDefault: 100").defineInRange("Haki Exp Limit", 100, 0, 300);
/* 225 */     this.randomizeRace = builder.comment("Randomizes the player's race at spawn (making the player unable to choose a race themselves) \nDefault: false").define("Race Randomizer", false);
/* 226 */     this.combatPickup = builder.comment("Allows the players to pickup items while the combat bar is active \nDefault: true").define("Combat Bar Pickup", true);
/* 227 */     this.nativeHaki = builder.comment("Allows vanilla and other modded NPCs to use Busoshoku Haki, has no visual effect on their model however and its purely mechanical \nDefault: true").define("Native Busoshoku Haki", true);
/*     */     
/* 229 */     Predicate<Object> bannedAbilitiesTest = new Predicate()
/*     */       {
/*     */         
/*     */         public boolean test(Object t)
/*     */         {
/* 234 */           if (!(t instanceof String)) {
/* 235 */             return false;
/*     */           }
/* 237 */           String str = (String)t;
/* 238 */           return !WyHelper.isNullOrEmpty(str);
/*     */         }
/*     */       };
/* 241 */     List<String> defaultBannedAbilities = new ArrayList<>();
/* 242 */     defaultBannedAbilities.add("example1");
/* 243 */     defaultBannedAbilities.add("example2");
/* 244 */     this.bannedAbilities = builder.comment("List with ability names that are banned, the names can be written in any case with or without spaces").defineList("Banned Abilities", defaultBannedAbilities, bannedAbilitiesTest);
/*     */     
/* 246 */     Predicate<Object> protectionWhitelistTest = new Predicate()
/*     */       {
/*     */         
/*     */         public boolean test(Object s)
/*     */         {
/* 251 */           if (!(s instanceof String))
/* 252 */             return false; 
/* 253 */           return !WyHelper.isNullOrEmpty((String)s);
/*     */         }
/*     */       };
/* 256 */     List<String> defaultProtectionWhitelist = new ArrayList<>();
/* 257 */     defaultProtectionWhitelist.add("example1");
/* 258 */     defaultProtectionWhitelist.add("mineminenomi:example2");
/* 259 */     this.protectionWhitelist = builder.comment("List with ability names that are allowed inside protection zones, the names should be written as resource locations similar to the below examples, if no namespace is provided 'mineminenomi' will be applied by default.").defineList("Protection Whitelist", defaultProtectionWhitelist, protectionWhitelistTest);
/*     */     
/* 261 */     this.keepStatsAfterDeath = builder.comment("Defines which logic to apply after a player's death \n NONE - nothing is kept \n AUTO - only the faction/race/fighting style stats are kept \n FULL - everything is kept \n CUSTOM - will use the 'Stats to Keep' section to determine which stats to keep\nDefault: AUTO").defineEnum("Keep Stats after Death", KeepStatsLogic.AUTO, 
/* 262 */         KeepStatsLogic.values());
/*     */     
/* 264 */     builder.comment("Please note that these settings only work if the \"Keep stats after Death\" option is set to CUSTOM!").push("Stats to Keep");
/*     */     
/* 266 */     String[] statsToKeepNames = { "Doriki", "Bounty", "Belly", "Race", "Faction", "Fighting Style", "Devil Fruit", "Haki Exp", "Loyalty" };
/* 267 */     this.statsToKeep = new HashMap<>();
/*     */     
/* 269 */     for (String stat : statsToKeepNames) {
/* 270 */       this.statsToKeep.put(stat, builder.define("Keep " + stat, true));
/*     */     }
/* 272 */     this.dorikiKeepPercentage = builder.comment("Percentage of doriki to keep after death\nDefault: 33").defineInRange("Percentage of Doriki kept after death", 33, 0, 100);
/* 273 */     this.bountyKeepPercentage = builder.comment("Percentage of bounty to keep after death\nDefault: 33").defineInRange("Percentage of Bounty kept after death", 33, 0, 100);
/* 274 */     this.bellyKeepPercentage = builder.comment("Percentage of belly to keep after death\nDefault: 33").defineInRange("Percentage of Belly kept after death", 33, 0, 100);
/* 275 */     this.hakiExpKeepPercentage = builder.comment("Percentage of haki exp to keep after death\nDefault: 33").defineInRange("Percentage of Haki Exp kept after death", 33, 0, 100);
/* 276 */     this.loyaltyKeepPercentage = builder.comment("Percentage of faction loyalty to keep after death\nDefault: 50").defineInRange("Percentage of Loyalty kept after death", 50, 0, 100);
/*     */     
/* 278 */     builder.pop();
/*     */     
/* 280 */     builder.pop();
/*     */     
/* 282 */     builder.push("Devil Fruits / Abilities");
/*     */     
/* 284 */     this.abilityInvulnerability = builder.comment("Invulnerability to avoid attacks\nDefault: true").define("Ability Invulnerability", true);
/* 285 */     this.waterChecks = builder.comment("Makes getting out of water much harder as it's supposed to be\nDefault: false").define("Devil Fruit Extended Weakness Checks", false);
/* 286 */     this.logiaProjectileHitLogic = builder.comment("How logias get affected by projectiles; \n NONE - No projectile can damage logias \n HAKI - Physical projectiles with buso cause damage \n EXTENDED - HAKI + any attack considered special deals damage to logias \nDefault: EXTENDED").defineEnum("Logia Projectiles Invulnerability", LogiaProjectileHitLogic.EXTENDED, LogiaProjectileHitLogic.values());
/* 287 */     this.logiaInvulnerability = builder.comment("Enables logia's invulnerability").define("Logia Invulnerability", true);
/* 288 */     this.logiaReturnEffect = builder.comment("Allows logia users to have different effects when punched\nDefault: false").define("Logia Return Effect", false);
/* 289 */     this.devilFruitDropsFromLeavesChance = builder.comment("Allows Devil Fruits to drop from leaves if higher than 0\nDefault: 0").defineInRange("Chance for Devil Fruits to drop from leaves", 0.0D, 0.0D, 100.0D);
/* 290 */     this.abilityGriefing = builder.comment("Allows abilities to break or replace blocks; if turned OFF it will make some abilities completly useless\nDefault: true").define("Ability Griefing", true);
/* 291 */     this.specialFlying = builder.comment("Allows Gasu Gasu no Mi, Moku Moku no Mi and Suna Suna no Mi users to fly, this option does not affect flying Zoans which will be able to fly regardless\nDefault: true").define("Special Flying", true);
/* 292 */     this.specialSourceEvents = builder.comment("Makes the fire and lava damage source to reduce fire resistance; only applies to move attacks from fruits \nDefault: true").define("Special Source Events", true);
/* 293 */     this.yamiPower = builder.comment("Allows Yami Yami no Mi users to eat an additional fruit\nDefault: true").define("Yami Yami no Mi additional fruit", true);
/* 294 */     this.abilityFraudChecks = builder.comment("Runs a check for all abilities on a player to remove dupes or suspicious abilities when the player joins the world\nDefault: true").define("Ability Fraud Checks", true);
/* 295 */     this.stopContinuousAbilities = builder.comment("Used to determine the logic for when a continuous ability is used while another continuous ability is being used;\n true - Currently used ability is stopped and the newly used ability starts its process\n false - The current ability is NOT stopped and the used ability has no effect\nDefault: true").define("Stop Continuous Abilities", true);
/* 296 */     this.abilityBars = builder.comment("Number of ability bars;\nDefault: 2").defineInRange("Ability Bars", 2, 1, 10);
/* 297 */     this.sharedCooldowns = builder.comment("Enables the shared cooldown between similar abilities\nDefault: true").define("Shared Cooldowns", true);
/* 298 */     this.animeScreaming = builder.comment("Will send a chat message to nearby players with the used ability's name\nDefault: false").define("Anime Scream", false);
/* 299 */     this.randomizedFruits = builder.comment("Will randomize all visual aspects of a devil fruit, making them impossible to identify\nDefault: false").define("Randomized Fruits", false);
/*     */     
/* 301 */     builder.comment("These options only work when \"One Fruit per World\" option is set to EXTENDED!").push("One Fruit Per World");
/*     */     
/* 303 */     this.oneFruitPerWorldLogic = builder.comment("Restricts the Devil Fruit spawns to only 1 of each type per world; \n NONE - No logic is applied, an infinite number of each fruit can exist \n SIMPLE - No more than one fruit type can be acquired via natural means (chests, leaves, fruit reincarnations etc) \n EXTENDED - Extra rules are applied on top of the SIMPLE set that blocks any means (or as many as possible) of storing/hoarding fruits \nDefault: NONE").defineEnum("One Devil Fruit per World Logic", OneFruitPerWorldLogic.NONE, OneFruitPerWorldLogic.values());
/* 304 */     this.unableToPickupDF = builder.comment("If the player already has a devil fruit then they will be unable to pickup any other fruit;\nDefault: false").define("Unable to pickup Devil Fruit as a fruit user", false);
/* 305 */     this.fruitsLimitInInventory = builder.comment("Sets the limit for how many fruits a player can hold in their inventory;\nDefault: 3").defineInRange("Inventory Fruit Limit", 3, 1, 10);
/* 306 */     this.daysForInactivity = builder.comment("Defines how many days a player has to be offline before their Devil Fruits are removed\nA value of 0 means the setting is disabled and fruits will not be removed for inactivity!;\nDefault: 6").defineInRange("Days for Inactivity", 6, 0, 30);
/*     */     
/* 308 */     builder.pop();
/*     */     
/* 310 */     builder.push("Devil Fruits Reincarnation");
/*     */     
/* 312 */     this.chanceForDroppedAppleReincarnation = builder.comment("Sets the % chance for a Devil Fruit to get reincarnated from a dropped apple\nDefault: 15").defineInRange("Dropped Apple Reincarnation Chance", 15.0D, 0.0D, 100.0D);
/* 313 */     this.chanceForInventoryAppleReincarnation = builder.comment("Sets the % chance for a Devil Fruit to get reincarnated from an apple inside an entity's inventory\nDefault: 1").defineInRange("Inventory Apple Reincarnation Chance", 1.0D, 0.0D, 100.0D);
/* 314 */     this.chanceForChestAppleReincarnation = builder.comment("Sets the % chance for a Devil Fruit to get reincarnated from an apple inside of a nearby chest\nDefault: 1").defineInRange("Chest Apple Reincarnation Chance", 1.0D, 0.0D, 100.0D);
/*     */     
/* 316 */     builder.pop();
/*     */     
/* 318 */     builder.pop();
/*     */     
/* 320 */     builder.push("Structures");
/*     */     
/* 322 */     builder.push("Training Structures");
/* 323 */     this.spawnTrainingStructures = builder.comment("Allows training structures to spawn in the world\nDefault: true").define("Spawn Training Structures", true);
/* 324 */     this.spawnChanceTrainingStructure = builder.comment("Sets the % chance for a training structure to spawn\nDefault: 60").defineInRange("Training Structure Spawn Chance", 60.0D, 0.0D, 100.0D);
/* 325 */     builder.pop();
/*     */     
/* 327 */     builder.push("Ghost Ships");
/* 328 */     this.spawnGhostShips = builder.comment("Allows ghost ships to spawn in the world\nDefault: true").define("Spawn Ghost Ships", true);
/* 329 */     this.spawnChanceGhostShip = builder.comment("Sets the % chance for a Ghost Ship to spawn, the % is calculated per chunk (16x16)\nDefault: 30").defineInRange("Ghost Ships Spawn Chance", 30.0D, 0.0D, 100.0D);
/* 330 */     builder.pop();
/*     */     
/* 332 */     builder.push("Sky Islands");
/* 333 */     this.spawnSkyIslands = builder.comment("Allows sky islands to spawn in the world\nDefault: true").define("Spawn Sky Islands", true);
/* 334 */     this.spawnChanceSkyIsland = builder.comment("Sets the % chance for a Sky Island to spawn, the % is calculated per chunk (16x16)\nDefault: 5").defineInRange("Sky Islands Spawn Chance", 5.0D, 0.0D, 100.0D);
/* 335 */     builder.pop();
/*     */     
/* 337 */     builder.push("Small Ships");
/* 338 */     this.spawnSmallShips = builder.comment("Allows small ships to spawn in the world\nDefault: true").define("Spawn Small Ships", true);
/* 339 */     this.spawnChanceSmallShip = builder.comment("Sets the % chance for a Small Ship to spawn, the % is calculated per chunk (16x16)\nDefault: 10").defineInRange("Small Ships Spawn Chance", 10.0D, 0.0D, 100.0D);
/* 340 */     builder.pop();
/*     */     
/* 342 */     builder.push("Medium Ships");
/* 343 */     this.spawnMediumShips = builder.comment("Allows medium ships to spawn in the world\nDefault: true").define("Spawn Medium Ships", true);
/* 344 */     this.spawnChanceMediumShip = builder.comment("Sets the % chance for a Medium Ship to spawn, the % is calculated per chunk (16x16)\nDefault: 70").defineInRange("Medium Ships Spawn Chance", 70.0D, 0.0D, 100.0D);
/* 345 */     builder.pop();
/*     */     
/* 347 */     builder.push("Large Ships");
/* 348 */     this.spawnLargeShips = builder.comment("Allows large ships to spawn in the world\nDefault: true").define("Spawn Large Ships", true);
/* 349 */     this.spawnChanceLargeShips = builder.comment("Sets the % chance for a Large Ships to spawn, the % is calculated per chunk (16x16)\nDefault: 30").defineInRange("Large Ships Spawn Chance", 30.0D, 0.0D, 100.0D);
/* 350 */     builder.pop();
/*     */     
/* 352 */     builder.push("Camps");
/* 353 */     this.spawnCamps = builder.comment("Allows camps to spawn in the world\nDefault: true").define("Spawn Camps", true);
/* 354 */     this.spawnChanceCamps = builder.comment("Sets the % chance for a Camp to spawn, the % is calculated per chunk (16x16)\nDefault: 60").defineInRange("Camps Spawn Chance", 60.0D, 0.0D, 100.0D);
/* 355 */     builder.pop();
/*     */     
/* 357 */     builder.push("Small Bases");
/* 358 */     this.spawnSmallBases = builder.comment("Allows small bases to spawn in the world\nDefault: true").define("Spawn Small Bases", true);
/* 359 */     this.spawnChanceSmallBase = builder.comment("Sets the % chance for a Small Base to spawn, the % is calculated per chunk (16x16)\nDefault: 70").defineInRange("Small Bases Spawn Chance", 70.0D, 0.0D, 100.0D);
/* 360 */     builder.pop();
/*     */     
/* 362 */     builder.push("Large Bases");
/* 363 */     this.spawnLargeBases = builder.comment("Allows large bases to spawn in the world\nDefault: true").define("Spawn Large Bases", true);
/* 364 */     this.spawnChanceLargeBase = builder.comment("Sets the % chance for a Large Base to spawn, the % is calculated per chunk (16x16)\nDefault: 80").defineInRange("Large Bases Spawn Chance", 80.0D, 0.0D, 100.0D);
/* 365 */     builder.pop();
/*     */     
/* 367 */     builder.push("Watch Tower");
/* 368 */     this.spawnWatchTowers = builder.comment("Allows watch towers to spawn in the world\nDefault: true").define("Spawn Watch Towers", true);
/* 369 */     this.spawnChanceWatchTower = builder.comment("Sets the % chance for a Watch Tower to spawn, the % is calculated per chunk (16x16)\nDefault: 5%").defineInRange("Watch Tower Spawn Chance", 5.0D, 0.0D, 100.0D);
/* 370 */     builder.pop();
/*     */     
/* 372 */     builder.push("Poneglyph");
/* 373 */     this.spawnPoneglyphs = builder.comment("Allows poneglyphs to spawn in the world\nDefault: true").define("Spawn Poneglyphs", true);
/* 374 */     this.spawnChancePoneglyph = builder.comment("Sets the % chance for a Poneglyph to spawn, the % is calculated per chunk (16x16)\nDefault: 30").defineInRange("Poneglyph Spawn Chance", 30.0D, 0.0D, 100.0D);
/* 375 */     builder.pop();
/*     */     
/* 377 */     builder.pop();
/*     */     
/* 379 */     builder.push("Quests");
/*     */     
/* 381 */     this.enableQuests = builder.comment("Allows quests to be accepted / completed\nDefault: true").define("Quests", true);
/* 382 */     this.questProgression = builder.comment("Allows quests to reward players with abilities, otherwise all abilities will be unlocked from the beginning\nDefault: true").define("Quest Progression", true);
/*     */     
/* 384 */     builder.pop();
/*     */     
/* 386 */     builder.push("Challenges");
/*     */     
/* 388 */     this.enableChallenges = builder.comment("Enabled the challenges menu and activation by players\nDefault: true").define("Challenges", true);
/* 389 */     this.enableReChallenges = builder.comment("Enabled retaking of already completed challenges\nDefault: false").define("Retake Challenges", false);
/*     */     
/* 391 */     builder.pop();
/*     */     
/* 393 */     builder.push("World Events");
/*     */     
/* 395 */     this.canSpawnWorldNPCs = builder.comment("Allows random aggressive faction NPCs to spawn in the world (Marines/Pirates/Bandits enemies)\nDefault: true").define("World NPCs Spawns", true);
/*     */     
/* 397 */     builder.push("Traders");
/* 398 */     this.canSpawnTraders = builder.comment("Allows Traders to spawn in the world\nDefault: true").define("Trader Spawns", true);
/* 399 */     this.timeBetweenTraderSpawns = builder.comment("Determines the time (in seconds) between two spawn attempts\nDefault: 1800").defineInRange("Time Between Trader Spawns", 1800, 1, 3600);
/* 400 */     this.chanceForTraderSpawn = builder.comment("Determines the % chance for a trader to spawn\nDefault: 1").defineInRange("Chance for Trader Spawns", 1, 1, 100);
/* 401 */     builder.pop();
/*     */     
/* 403 */     builder.push("Trainers");
/* 404 */     this.canSpawnTrainers = builder.comment("Allows Trainers to spawn in the world\nDefault: true").define("Trainer Spawns", true);
/* 405 */     this.timeBetweenTrainerSpawns = builder.comment("Determines the time (in seconds) between two spawn attempts\nDefault: 1800").defineInRange("Time Between Trainer Spawns", 1800, 1, 3600);
/* 406 */     this.chanceForTrainerSpawn = builder.comment("Determines the % chance for a trainer to spawn\nDefault: 15").defineInRange("Chance for Trainer Spawns", 15, 1, 100);
/* 407 */     builder.pop();
/*     */     
/* 409 */     builder.push("Ambushes");
/* 410 */     this.canSpawnAmbushes = builder.comment("Allows Ambushes to spawn in the world\nDefault: true").define("Ambushe Spawns", true);
/* 411 */     this.timeBetweenAmbushSpawns = builder.comment("Determines the time (in seconds) between two spawn attempts\nDefault: 3600").defineInRange("Time Between Ambushes Spawns", 3600, 1, 3600);
/* 412 */     this.chanceForAmbushSpawn = builder.comment("Determines the % chance for a ambush to spawn\nDefault: 15").defineInRange("Chance for Ambush Spawns", 15, 1, 100);
/* 413 */     builder.pop();
/*     */     
/* 415 */     builder.pop();
/*     */     
/* 417 */     builder.push("Ores");
/*     */     
/* 419 */     this.kairosekiSpawnCount = builder.comment("Kairoseki count per chunk\nDefault: 5").defineInRange("Kairoseki Count", 2, 1, 20);
/* 420 */     this.kairosekiSpawnMaxHeight = builder.comment("Kairoseki maximum height\nDefault: 128").defineInRange("Kairoseki Height Spawn", 64, 1, 128);
/*     */     
/* 422 */     builder.pop();
/*     */     
/* 424 */     builder.push("Crews");
/*     */     
/* 426 */     this.bountyRequirement = builder.comment("Bounty Requirement for creating a crew; 0 means no requirement\nDefault: 0").defineInRange("Bounty Requirement", 0, 0, 100000);
/* 427 */     this.worldMessageOnCrewCreate = builder.comment("Sends a message to all players when a new crew gets formed\nDefault: false").define("World Message", false);
/*     */     
/* 429 */     this.disableFriendlyFire = builder.comment("Disabled the friendly damage between crewmates\nDefault: true").define("Disable Friendly Damage", true);
/*     */     
/* 431 */     builder.pop();
/*     */     
/* 433 */     builder.push("Bounty");
/*     */     
/* 435 */     this.wantedPosterPackages = builder.comment("Allows wanted poster packages to drop from the sky\nDefault: true").define("Wanted Poster Package Drops", true);
/* 436 */     this.timeBetweenPackageDrops = builder.comment("Time it takes for another package to drop\nDefault: 18000 (15 minutes)").defineInRange("Time Between Package Drops", 18000, 0, 72000);
/*     */     
/* 438 */     builder.pop();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCombatPickupOn() {
/* 443 */     return ((Boolean)this.combatPickup.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Ability> getProtectionWhitelistedAbilities() {
/* 448 */     List<Ability> abilities = new ArrayList<>();
/* 449 */     List<? extends String> list = (List<? extends String>)this.protectionWhitelist.get();
/* 450 */     for (String o : list) {
/*     */       
/* 452 */       if (!(o instanceof String)) {
/*     */         continue;
/*     */       }
/* 455 */       String s = o;
/* 456 */       String[] arr = s.split(":");
/* 457 */       if (arr.length == 0)
/*     */         continue; 
/* 459 */       if (arr.length == 1)
/* 460 */         arr = new String[] { "mineminenomi", s }; 
/* 461 */       ResourceLocation res = new ResourceLocation(arr[0], arr[1]);
/* 462 */       Ability ability = Ability.get(res);
/* 463 */       if (ability != null)
/* 464 */         abilities.add(ability); 
/*     */     } 
/* 466 */     return abilities;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getDespawnWithNametag() {
/* 471 */     return ((Boolean)this.despawnWithNametags.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getRandomizedFruits() {
/* 476 */     return ((Boolean)this.randomizedFruits.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getRaceRandomizer() {
/* 481 */     return ((Boolean)this.randomizeRace.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public double getChanceForSkyIslandSpawn() {
/* 486 */     return ((Double)this.spawnChanceSkyIsland.get()).doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnSkyIslands() {
/* 491 */     return ((Boolean)this.spawnSkyIslands.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public double getChanceForGhostShipSpawn() {
/* 496 */     return ((Double)this.spawnChanceGhostShip.get()).doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnGhostShips() {
/* 501 */     return ((Boolean)this.spawnGhostShips.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isReChallengesEnabled() {
/* 506 */     return ((Boolean)this.enableReChallenges.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isChallengesEnabled() {
/* 511 */     return ((Boolean)this.enableChallenges.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public double getChanceForPoneglyphSpawn() {
/* 516 */     return ((Double)this.spawnChancePoneglyph.get()).doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnPoneglyphs() {
/* 521 */     return ((Boolean)this.spawnPoneglyphs.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDaysForInactivity() {
/* 526 */     return ((Integer)this.daysForInactivity.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBellyKeepPercentage() {
/* 531 */     return ((Integer)this.bellyKeepPercentage.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHakiExpKeepPercentage() {
/* 536 */     return ((Integer)this.hakiExpKeepPercentage.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBountyKeepPercentage() {
/* 541 */     return ((Integer)this.bountyKeepPercentage.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDorikiKeepPercentage() {
/* 546 */     return ((Integer)this.dorikiKeepPercentage.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getInventoryLimitForFruits() {
/* 551 */     return ((Integer)this.fruitsLimitInInventory.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getUnableToPickDFAsUser() {
/* 556 */     return ((Boolean)this.unableToPickupDF.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAbilityBars() {
/* 561 */     return ((Integer)this.abilityBars.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getStopContinuousAbility() {
/* 566 */     return ((Boolean)this.stopContinuousAbilities.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHakiExpLimit() {
/* 571 */     return ((Integer)this.hakiExpLimit.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDorikiLimit() {
/* 576 */     return ((Integer)this.dorikiLimit.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getDestroySpawner() {
/* 581 */     return ((Boolean)this.destroySpawner.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getDestroyWater() {
/* 586 */     return ((Boolean)this.destroyWater.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public double getChanceForChestAppleReincarnation() {
/* 591 */     return ((Double)this.chanceForChestAppleReincarnation.get()).doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public double getChanceForInventoryAppleReincarnation() {
/* 596 */     return ((Double)this.chanceForInventoryAppleReincarnation.get()).doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public double getChanceForDroppedAppleReincarnation() {
/* 601 */     return ((Double)this.chanceForDroppedAppleReincarnation.get()).doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFriendlyDamageDisabled() {
/* 606 */     return ((Boolean)this.disableFriendlyFire.get()).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasOneFruitPerWorldExtendedLogic() {
/* 617 */     return ((OneFruitPerWorldLogic)this.oneFruitPerWorldLogic.get()).equals(OneFruitPerWorldLogic.EXTENDED);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasOneFruitPerWorldSimpleLogic() {
/* 622 */     return (((OneFruitPerWorldLogic)this.oneFruitPerWorldLogic.get()).equals(OneFruitPerWorldLogic.SIMPLE) || hasOneFruitPerWorldExtendedLogic());
/*     */   }
/*     */ 
/*     */   
/*     */   public double getChanceForLargeBasesSpawn() {
/* 627 */     return ((Double)this.spawnChanceLargeBase.get()).doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnLargeBases() {
/* 632 */     return ((Boolean)this.spawnLargeBases.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public double getChanceForWatchTowersSpawn() {
/* 637 */     return ((Double)this.spawnChanceWatchTower.get()).doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnWatchTowers() {
/* 642 */     return ((Boolean)this.spawnWatchTowers.get()).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getChanceForLargeShipsSpawn() {
/* 648 */     return ((Double)this.spawnChanceLargeShips.get()).doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnLargeShips() {
/* 653 */     return ((Boolean)this.spawnLargeShips.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public double getChanceForSmallBasesSpawn() {
/* 658 */     return ((Double)this.spawnChanceSmallBase.get()).doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnSmallBases() {
/* 663 */     return ((Boolean)this.spawnSmallBases.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public double getChanceForCampsSpawn() {
/* 668 */     return ((Double)this.spawnChanceCamps.get()).doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnCamps() {
/* 673 */     return ((Boolean)this.spawnCamps.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public double getChanceForSmallShipsSpawn() {
/* 678 */     return ((Double)this.spawnChanceSmallShip.get()).doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnSmallShips() {
/* 683 */     return ((Boolean)this.spawnSmallShips.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public double getChanceForMediumShipsSpawn() {
/* 688 */     return ((Double)this.spawnChanceMediumShip.get()).doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnMediumShips() {
/* 693 */     return ((Boolean)this.spawnMediumShips.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public double getChanceForTrainingStructureSpawn() {
/* 698 */     return ((Double)this.spawnChanceTrainingStructure.get()).doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnTrainingStructures() {
/* 703 */     return ((Boolean)this.spawnTrainingStructures.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCrewWorldMessageEnabled() {
/* 708 */     return ((Boolean)this.worldMessageOnCrewCreate.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBountyRequirementForCrews() {
/* 713 */     return ((Integer)this.bountyRequirement.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHaoshokuUnlockLogicExpBased() {
/* 718 */     return (this.haoshokuUnlockLogic.get() == HaoshokuUnlockLogic.EXPERIENCE || this.haoshokuUnlockLogic.get() == HaoshokuUnlockLogic.COMBINED);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHaoshokuUnlockLogicChanceBased() {
/* 723 */     return (this.haoshokuUnlockLogic.get() == HaoshokuUnlockLogic.RANDOM || this.haoshokuUnlockLogic.get() == HaoshokuUnlockLogic.COMBINED || this.haoshokuUnlockLogic.get() == HaoshokuUnlockLogic.TRUE_RANDOM);
/*     */   }
/*     */ 
/*     */   
/*     */   public HaoshokuUnlockLogic getHaoshokuUnlockLogic() {
/* 728 */     return (HaoshokuUnlockLogic)this.haoshokuUnlockLogic.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getChanceForAmbushSpawn() {
/* 733 */     return ((Integer)this.chanceForAmbushSpawn.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTimeBetweenAmbushSpawns() {
/* 738 */     return ((Integer)this.timeBetweenAmbushSpawns.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnAmbushes() {
/* 743 */     return ((Boolean)this.canSpawnAmbushes.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getChanceForTrainerSpawn() {
/* 748 */     return ((Integer)this.chanceForTrainerSpawn.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTimeBetweenTrainerSpawns() {
/* 753 */     return ((Integer)this.timeBetweenTrainerSpawns.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnTrainers() {
/* 758 */     return ((Boolean)this.canSpawnTrainers.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getChanceForTraderSpawn() {
/* 763 */     return ((Integer)this.chanceForTraderSpawn.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTimeBetweenTraderSpawns() {
/* 768 */     return ((Integer)this.timeBetweenTraderSpawns.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnTraders() {
/* 773 */     return ((Boolean)this.canSpawnTraders.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getStatsToKeep() {
/* 778 */     List<String> stats = new ArrayList<>();
/*     */     
/* 780 */     for (Map.Entry<String, ForgeConfigSpec.BooleanValue> entry : this.statsToKeep.entrySet()) {
/*     */       
/* 782 */       if (((Boolean)((ForgeConfigSpec.BooleanValue)entry.getValue()).get()).booleanValue())
/*     */       {
/* 784 */         stats.add(entry.getKey());
/*     */       }
/*     */     } 
/*     */     
/* 788 */     String[] newArray = new String[0];
/* 789 */     return stats.<String>toArray(newArray);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTimeBetweenPackages() {
/* 794 */     return ((Integer)this.timeBetweenPackageDrops.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isWantedPosterPackagesEnabled() {
/* 799 */     return ((Boolean)this.wantedPosterPackages.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDorikiRewardMultiplier() {
/* 804 */     return ((Double)this.dorikiRewardMultiplier.get()).doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public double getHakiExpMultiplier() {
/* 809 */     return ((Double)this.hakiExpMultiplier.get()).doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isQuestProgressionEnabled() {
/* 814 */     return ((Boolean)this.questProgression.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isQuestsEnabled() {
/* 819 */     return ((Boolean)this.enableQuests.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> getBannedAbilities() {
/* 824 */     return (List<String>)this.bannedAbilities.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public KeepStatsLogic getAfterDeathLogic() {
/* 829 */     return (KeepStatsLogic)this.keepStatsAfterDeath.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAbilityInvulnerabilityEnabled() {
/* 834 */     return ((Boolean)this.abilityInvulnerability.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean areExtraWaterChecksEnabled() {
/* 839 */     return ((Boolean)this.waterChecks.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLogiaDamageEffectEnabled() {
/* 844 */     return ((Boolean)this.logiaReturnEffect.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean doLogiasHaveHurtHakiLogic() {
/* 849 */     return (((LogiaProjectileHitLogic)this.logiaProjectileHitLogic.get()).equals(LogiaProjectileHitLogic.HAKI) || ((LogiaProjectileHitLogic)this.logiaProjectileHitLogic.get()).equals(LogiaProjectileHitLogic.EXTENDED));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean doLogiasHaveHurtExtendedLogic() {
/* 854 */     return ((LogiaProjectileHitLogic)this.logiaProjectileHitLogic.get()).equals(LogiaProjectileHitLogic.EXTENDED);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDevilFruitDropsFromLeavesChance() {
/* 859 */     return ((Double)this.devilFruitDropsFromLeavesChance.get()).doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isExtraHeartsEnabled() {
/* 864 */     return ((Boolean)this.extraHearts.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMobRewardsEnabled() {
/* 869 */     return ((Boolean)this.mobRewards.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAbilityGriefingEnabled() {
/* 874 */     return ((Boolean)this.abilityGriefing.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSpecialFlyingEnabled() {
/* 879 */     return ((Boolean)this.specialFlying.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSpecialSourceEventsEnabled() {
/* 884 */     return ((Boolean)this.specialSourceEvents.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isYamiPowerEnabled() {
/* 889 */     return ((Boolean)this.yamiPower.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAbilityFraudChecksEnabled() {
/* 894 */     return ((Boolean)this.abilityFraudChecks.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMinimumDorikiPerKillEnabled() {
/* 899 */     return ((Boolean)this.minimumDorikiPerKill.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getKairosekiSpawnCount() {
/* 904 */     return ((Integer)this.kairosekiSpawnCount.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getKairosekiMaxSpawn() {
/* 909 */     return ((Integer)this.kairosekiSpawnMaxHeight.get()).intValue();
/*     */   }
/*     */   
/*     */   public static interface IConfigEnum {
/*     */     <T extends IConfigEnum> T next();
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\config\CommonConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */