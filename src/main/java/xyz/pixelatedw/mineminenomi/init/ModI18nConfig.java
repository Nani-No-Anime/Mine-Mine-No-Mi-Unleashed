/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ 
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
/*     */ public class ModI18nConfig
/*     */ {
/*     */   public static final String DORIKI_REWARD_MULTIPLIER_TOOLTIP = "Multiplies any doriki gained by this amount\nDefault: 1";
/*     */   public static final String HAKI_EXP_REWARD_MULTIPLIER_TOOLTIP = "Multiplies any haki gained by this amount\nDefault: 1";
/*     */   public static final String DORIKI_LIMIT_TOOLTIP = "Sets a new limit for maximum doriki a player may obtain \nDefault: 10000";
/*     */   public static final String HAKI_EXP_LIMIT_TOOLTIP = "Sets a new limit for maximum haki exp a player may obtain \nDefault: 100";
/*     */   public static final String EXTRA_HEARTS_TOOLTIP = "Allows players to receive extra hearts based on their doriki\nDefault: true";
/*     */   public static final String MINIMUM_DORIKI_PER_KILL_TOOLTIP = "Guarantees a minimum of 1 doriki per kill\n If used together with a Haki Exp Multiplier with a multiplier less than <1.0 it will convert it to chances\nDefault: false";
/*     */   public static final String MOB_REWARDS_TOOLTIP = "Allows mobs to reward doriki, bounty or items\nDefault: true";
/*     */   public static final String ANIME_SCREAMING_TOOLTIP = "Will send a chat message to nearby players with the used ability's name\nDefault: false";
/*     */   public static final String DESTROY_SPAWNER_TOOLTIP = "Destroys the spawner after all its spawns are killed\nDefault: true";
/*     */   public static final String DESTROY_WATER_TOOLTIP = "Allows big explosions to destroy water \nDefault: false";
/*     */   public static final String HAOSHOKU_UNLOCK_LOGIC_TOOLTIP = "Responsible for how player unlock Haoshoku Haki; \n NONE - Haoshoku Haki cannot be unlocked naturally \n RANDOM - Only a few chosen ones receive it when they spawn \n EXPERIENCE - Will unlock based on the total amount of Haki experience a player has\n COMBINED - Combiens the logic of RANDOM and EXPERIENCE\n TRUE_RANDOM - Each world / server has its own pool of randomly chosen Haoshoku Haki users\nDefault: EXPERIENCE";
/*     */   public static final String RACE_RANDOMIZER_TOOLTIP = "Randomizes the player's race at spawn (making the player unable to choose a race themselves) \nDefault: false";
/*     */   public static final String DESPAWN_WITH_NAMETAG_TOOLTIP = "Normally despawns traders and trainers even if they're nametagged \nDefault: false";
/*     */   public static final String KEEP_STATS_AFTER_DEATH_TOOLTIP = "Defines which logic to apply after a player's death \n NONE - nothing is kept \n AUTO - only the faction/race/fighting style stats are kept \n FULL - everything is kept \n CUSTOM - will use the 'Stats to Keep' section to determine which stats to keep\nDefault: AUTO";
/*     */   public static final String DORIKI_KEEP_PERCENTAGE_TOOLTIP = "Percentage of doriki to keep after death\nDefault: 33";
/*     */   public static final String BOUNTY_KEEP_PERCENTAGE_TOOLTIP = "Percentage of bounty to keep after death\nDefault: 33";
/*     */   public static final String BELLY_KEEP_PERCENTAGE_TOOLTIP = "Percentage of belly to keep after death\nDefault: 33";
/*     */   public static final String HAKI_EXP_KEEP_PERCENTAGE_TOOLTIP = "Percentage of haki exp to keep after death\nDefault: 33";
/*     */   public static final String LOYALTY_KEEP_PERCENTAGE_TOOLTIP = "Percentage of faction loyalty to keep after death\nDefault: 50";
/*     */   public static final String ABILITY_INVULNERABILITY_TOOLTIP = "Invulnerability to avoid attacks\nDefault: true";
/*     */   public static final String WATER_CHECKS_TOOLTIP = "Makes getting out of water much harder as it's supposed to be\nDefault: false";
/*     */   public static final String LOGIA_PROJECTILE_HIT_TOOLTIP = "How logias get affected by projectiles; \n NONE - No projectile can damage logias \n HAKI - Physical projectiles with buso cause damage \n EXTENDED - HAKI + any attack considered special deals damage to logias \nDefault: EXTENDED";
/*     */   public static final String LOGIA_INVULNERABILITY_TOOLTIP = "Enables logia's invulnerability";
/*     */   public static final String LOGIA_RETURN_EFFECT_TOOLTIP = "Allows logia users to have different effects when punched\nDefault: false";
/*     */   public static final String DEVIL_FRUIT_DROP_FROM_LEAVES_TOOLTIP = "Allows Devil Fruits to drop from leaves if higher than 0\nDefault: 0";
/*     */   public static final String ABILITY_GRIEFING_TOOLTIP = "Allows abilities to break or replace blocks; if turned OFF it will make some abilities completly useless\nDefault: true";
/*     */   public static final String SPECIAL_FLYING_TOOLTIP = "Allows Gasu Gasu no Mi, Moku Moku no Mi and Suna Suna no Mi users to fly, this option does not affect flying Zoans which will be able to fly regardless\nDefault: true";
/*     */   public static final String SPECIAL_SOURCES_TOOLTIP = "Makes the fire and lava damage source to reduce fire resistance; only applies to move attacks from fruits \nDefault: true";
/*     */   public static final String YAMI_POWER_TOOLTIP = "Allows Yami Yami no Mi users to eat an additional fruit\nDefault: true";
/*     */   public static final String ABILITY_FRAUD_CHECKS_TOOLTIP = "Runs a check for all abilities on a player to remove dupes or suspicious abilities when the player joins the world\nDefault: true";
/*     */   public static final String STOP_CONTINUOUS_ABILITIES_TOOLTIP = "Used to determine the logic for when a continuous ability is used while another continuous ability is being used;\n true - Currently used ability is stopped and the newly used ability starts its process\n false - The current ability is NOT stopped and the used ability has no effect\nDefault: true";
/*     */   public static final String ABILITY_BARS_TOOLTIP = "Number of ability bars;\nDefault: 2";
/*     */   public static final String SHARED_COOLDOWNS_TOOLTIP = "Enables the shared cooldown between similar abilities\nDefault: true";
/*     */   public static final String FIRE_VISIBILITY_TOOLTIP = "Visibility when on fire while using a fire resistant fruit \nDefault: 20";
/*     */   public static final String RANDOMIZED_FRUITS_TOOLTIP = "Will randomize all visual aspects of a devil fruit, making them impossible to identify\nDefault: false";
/*     */   public static final String ONE_FRUIT_PER_WORLD_LOGIC_TOOLTIP = "Restricts the Devil Fruit spawns to only 1 of each type per world; \n NONE - No logic is applied, an infinite number of each fruit can exist \n SIMPLE - No more than one fruit type can be acquired via natural means (chests, leaves, fruit reincarnations etc) \n EXTENDED - Extra rules are applied on top of the SIMPLE set that blocks any means (or as many as possible) of storing/hoarding fruits \nDefault: NONE";
/*     */   public static final String UNABLE_TO_PICKUP_FRUIT_TOOLTIP = "If the player already has a devil fruit then they will be unable to pickup any other fruit;\nDefault: false";
/*     */   public static final String FRUITS_LIMIT_IN_INVENTORY_TOOLTIP = "Sets the limit for how many fruits a player can hold in their inventory;\nDefault: 3";
/*     */   public static final String DAYS_FOR_INACTIVITY_TOOLTIP = "Defines how many days a player has to be offline before their Devil Fruits are removed\nA value of 0 means the setting is disabled and fruits will not be removed for inactivity!;\nDefault: 6";
/*     */   public static final String CHANCE_FOR_DROPPED_APPLE_TO_REINCARNATE_TOOLTIP = "Sets the % chance for a Devil Fruit to get reincarnated from a dropped apple\nDefault: 15";
/*     */   public static final String CHANCE_FOR_INVENTORY_APPLE_TO_REINCARNATE_TOOLTIP = "Sets the % chance for a Devil Fruit to get reincarnated from an apple inside an entity's inventory\nDefault: 1";
/*     */   public static final String CHANCE_FOR_CHEST_APPLE_TO_REINCARNATE_TOOLTIP = "Sets the % chance for a Devil Fruit to get reincarnated from an apple inside of a nearby chest\nDefault: 1";
/*     */   public static final String BOUNTY_REQUIREMENT_TOOLTIP = "Bounty Requirement for creating a crew; 0 means no requirement\nDefault: 0";
/*     */   public static final String WORLD_MESSAGE_ON_CREW_CREATION_TOOLTIP = "Sends a message to all players when a new crew gets formed\nDefault: false";
/*     */   public static final String DISABLE_FRIENDLY_FIRE_TOOLTIP = "Disabled the friendly damage between crewmates\nDefault: true";
/*     */   public static final String KAIROSEKI_COUNT_TOOLTIP = "Kairoseki count per chunk\nDefault: 5";
/*     */   public static final String KAIROSEKI_MAXIMUM_HEIGHT_TOOLTIP = "Kairoseki maximum height\nDefault: 128";
/*     */   public static final String QUESTS_TOOLTIP = "Allows quests to be accepted / completed\nDefault: true";
/*     */   public static final String QUEST_PROGRESSION_TOOLTIP = "Allows quests to reward players with abilities, otherwise all abilities will be unlocked from the beginning\nDefault: true";
/*     */   public static final String CHALLENGES_TOOLTIP = "Enabled the challenges menu and activation by players\nDefault: true";
/*     */   public static final String RE_CHALLENGES_TOOLTIP = "Enabled retaking of already completed challenges\nDefault: false";
/*     */   public static final String WANTED_POSTER_PACKAGE_DROP_TOOLTIP = "Allows wanted poster packages to drop from the sky\nDefault: true";
/*     */   public static final String TIME_BETWEEN_PACKAGE_DROPS_TOOLTIP = "Time it takes for another package to drop\nDefault: 18000 (15 minutes)";
/*     */   public static final String UPDATE_MESSAGE_TOOLTIP = "Allows the game to show a text message when the installed mod is outdated\nDefault: true";
/*     */   public static final String COMBAT_ITEM_PICKUP_TOOLTIP = "Allows the players to pickup items while the combat bar is active \nDefault: true";
/*     */   public static final String FOV_REMOVER_TOOLTIP = "Keeps the FOV fixed when the player has speed effects active\nDefault: true";
/*     */   public static final String TOOLTIP_MESSAGE_TOOLTIP = "Displays tooltips when hovering over certain elements like config options, abilities etc\nDefault: true";
/*     */   
/*     */   public static void init() {
/*  93 */     WyRegistry.registerName("gui.mineminenomi.config.title", "Config Menu");
/*  94 */     WyRegistry.registerName("gui.mineminenomi.config.open_config", "Open Config File");
/*     */     
/*  96 */     WyRegistry.registerName("gui.mineminenomi.config.category.general", "General");
/*  97 */     WyRegistry.registerName("gui.mineminenomi.config.category.devil_fruits", "Devil Fruits");
/*  98 */     WyRegistry.registerName("gui.mineminenomi.config.category.structures", "Structures");
/*  99 */     WyRegistry.registerName("gui.mineminenomi.config.category.world_events", "World Events");
/* 100 */     WyRegistry.registerName("gui.mineminenomi.config.category.crews", "Crews");
/* 101 */     WyRegistry.registerName("gui.mineminenomi.config.category.ores", "Ores");
/* 102 */     WyRegistry.registerName("gui.mineminenomi.config.category.bounty", "Bounty");
/* 103 */     WyRegistry.registerName("gui.mineminenomi.config.category.quests", "Quests");
/* 104 */     WyRegistry.registerName("gui.mineminenomi.config.category.challenges", "Challenges");
/* 105 */     WyRegistry.registerName("gui.mineminenomi.config.category.system", "System");
/*     */     
/* 107 */     registerConfigWithTooltip("can_spawn", "Can Spawn", "Can Spawn in the world");
/* 108 */     registerConfigWithTooltip("spawn_chance", "Spawn Chance", "Spawn Chance");
/* 109 */     registerConfigWithTooltip("time_between_spawns", "Time Between Spawns", "Ticks between spawns");
/*     */     
/* 111 */     registerConfigWithTooltip("doriki_reward_multiplier", "Doriki Reward Multiplier", "Multiplies any doriki gained by this amount\nDefault: 1");
/* 112 */     registerConfigWithTooltip("haki_exp_multiplier", "Haki Experience Multiplier", "Multiplies any haki gained by this amount\nDefault: 1");
/* 113 */     registerConfigWithTooltip("doriki_limit", "Doriki Limit", "Sets a new limit for maximum doriki a player may obtain \nDefault: 10000");
/* 114 */     registerConfigWithTooltip("haki_exp_limit", "Haki Exp Limit", "Sets a new limit for maximum haki exp a player may obtain \nDefault: 100");
/* 115 */     registerConfigWithTooltip("minimum_doriki_per_kill", "Minimum Doriki per Kill", "Guarantees a minimum of 1 doriki per kill\n If used together with a Haki Exp Multiplier with a multiplier less than <1.0 it will convert it to chances\nDefault: false");
/* 116 */     registerConfigWithTooltip("mob_rewards", "Mob Rewards", "Allows mobs to reward doriki, bounty or items\nDefault: true");
/* 117 */     registerConfigWithTooltip("extra_hearts", "Extra Hearts", "Allows players to receive extra hearts based on their doriki\nDefault: true");
/* 118 */     registerConfigWithTooltip("anime_screaming", "Anime Screaming", "Will send a chat message to nearby players with the used ability's name\nDefault: false");
/* 119 */     registerConfigWithTooltip("destroy_spawner", "Destroy Spawner", "Destroys the spawner after all its spawns are killed\nDefault: true");
/* 120 */     registerConfigWithTooltip("haoshoku_unlock_logic", "Haoshoku Haki Unlock Logic", "Responsible for how player unlock Haoshoku Haki; \n NONE - Haoshoku Haki cannot be unlocked naturally \n RANDOM - Only a few chosen ones receive it when they spawn \n EXPERIENCE - Will unlock based on the total amount of Haki experience a player has\n COMBINED - Combiens the logic of RANDOM and EXPERIENCE\n TRUE_RANDOM - Each world / server has its own pool of randomly chosen Haoshoku Haki users\nDefault: EXPERIENCE");
/* 121 */     registerConfigWithTooltip("race_randomizer", "Race Randomizer", "Randomizes the player's race at spawn (making the player unable to choose a race themselves) \nDefault: false");
/*     */     
/* 123 */     registerConfigWithTooltip("keep_stats_after_death", "Keep Stats after Death", "Defines which logic to apply after a player's death \n NONE - nothing is kept \n AUTO - only the faction/race/fighting style stats are kept \n FULL - everything is kept \n CUSTOM - will use the 'Stats to Keep' section to determine which stats to keep\nDefault: AUTO");
/* 124 */     registerConfigWithTooltip("doriki_percentage_after_death", "Percentage of Doriki kept after death", "Percentage of doriki to keep after death\nDefault: 33");
/* 125 */     registerConfigWithTooltip("bounty_percentage_after_death", "Percentage of Bounty kept after death", "Percentage of bounty to keep after death\nDefault: 33");
/* 126 */     registerConfigWithTooltip("belly_percentage_after_death", "Percentage of Belly kept after death", "Percentage of belly to keep after death\nDefault: 33");
/* 127 */     registerConfigWithTooltip("haki_exp_percentage_after_death", "Percentage of Haki Exp kept after death", "Percentage of haki exp to keep after death\nDefault: 33");
/*     */     
/* 129 */     registerConfigWithTooltip("devil_fruit_drop_from_leaves_chance", "Devil Fruits drop chance from leaves", "Allows Devil Fruits to drop from leaves if higher than 0\nDefault: 0");
/* 130 */     registerConfigWithTooltip("ability_bars", "Ability Bars", "Number of ability bars;\nDefault: 2");
/* 131 */     registerConfigWithTooltip("ability_griefing", "Ability Griefing", "Allows abilities to break or replace blocks; if turned OFF it will make some abilities completly useless\nDefault: true");
/* 132 */     registerConfigWithTooltip("ability_fraud_checks", "Ability Fraud Checks", "Runs a check for all abilities on a player to remove dupes or suspicious abilities when the player joins the world\nDefault: true");
/* 133 */     registerConfigWithTooltip("stop_continuous_abilities", "Stop Continuous Abilities", "Used to determine the logic for when a continuous ability is used while another continuous ability is being used;\n true - Currently used ability is stopped and the newly used ability starts its process\n false - The current ability is NOT stopped and the used ability has no effect\nDefault: true");
/* 134 */     registerConfigWithTooltip("water_checks", "Extra Water Checks", "Makes getting out of water much harder as it's supposed to be\nDefault: false");
/* 135 */     registerConfigWithTooltip("logia_projectile_hit_invulnerability", "Logia Projectile Hit Logic", "How logias get affected by projectiles; \n NONE - No projectile can damage logias \n HAKI - Physical projectiles with buso cause damage \n EXTENDED - HAKI + any attack considered special deals damage to logias \nDefault: EXTENDED");
/* 136 */     registerConfigWithTooltip("logia_invulnerability", "Logia Invulnerability", "Enables logia's invulnerability");
/* 137 */     registerConfigWithTooltip("logia_return_effect", "Logia Return Effects", "Allows logia users to have different effects when punched\nDefault: false");
/* 138 */     registerConfigWithTooltip("special_flying", "Special Flying", "Allows Gasu Gasu no Mi, Moku Moku no Mi and Suna Suna no Mi users to fly, this option does not affect flying Zoans which will be able to fly regardless\nDefault: true");
/* 139 */     registerConfigWithTooltip("special_sources", "Special Source Events", "Makes the fire and lava damage source to reduce fire resistance; only applies to move attacks from fruits \nDefault: true");
/* 140 */     registerConfigWithTooltip("yami_power", "Yami Yami no Mi Additional Fruit", "Allows Yami Yami no Mi users to eat an additional fruit\nDefault: true");
/* 141 */     registerConfigWithTooltip("shared_cooldowns", "Shared Cooldowns", "Enables the shared cooldown between similar abilities\nDefault: true");
/* 142 */     registerConfigWithTooltip("ability_invulnerability", "Ability Invulnerability", "Invulnerability to avoid attacks\nDefault: true");
/* 143 */     registerConfigWithTooltip("randomized_fruits", "Randomized Fruits", "Will randomize all visual aspects of a devil fruit, making them impossible to identify\nDefault: false");
/*     */     
/* 145 */     registerConfigWithTooltip("one_fruit_per_world_logic", "One Fruit per World Logic", "Restricts the Devil Fruit spawns to only 1 of each type per world; \n NONE - No logic is applied, an infinite number of each fruit can exist \n SIMPLE - No more than one fruit type can be acquired via natural means (chests, leaves, fruit reincarnations etc) \n EXTENDED - Extra rules are applied on top of the SIMPLE set that blocks any means (or as many as possible) of storing/hoarding fruits \nDefault: NONE");
/* 146 */     registerConfigWithTooltip("unable_pickup_df", "Unable to pickup Devil Fruit as a fruit user", "If the player already has a devil fruit then they will be unable to pickup any other fruit;\nDefault: false");
/* 147 */     registerConfigWithTooltip("fruits_limit_in_inventory", "Inventory Fruit Limit", "Sets the limit for how many fruits a player can hold in their inventory;\nDefault: 3");
/* 148 */     registerConfigWithTooltip("days_for_inactivity", "Days for Inactivity", "Defines how many days a player has to be offline before their Devil Fruits are removed\nA value of 0 means the setting is disabled and fruits will not be removed for inactivity!;\nDefault: 6");
/*     */     
/* 150 */     registerConfigWithTooltip("dropped_apple_reincarnation", "Chance for Dropped Apples", "Sets the % chance for a Devil Fruit to get reincarnated from a dropped apple\nDefault: 15");
/* 151 */     registerConfigWithTooltip("inventory_apple_reincarnation", "Chance for Inventory Apples", "Sets the % chance for a Devil Fruit to get reincarnated from an apple inside an entity's inventory\nDefault: 1");
/* 152 */     registerConfigWithTooltip("chest_apple_reincarnation", "Chance for Chest Apples", "Sets the % chance for a Devil Fruit to get reincarnated from an apple inside of a nearby chest\nDefault: 1");
/*     */     
/* 154 */     registerConfigWithTooltip("bounty_requirement", "Bounty Requirement", "Bounty Requirement for creating a crew; 0 means no requirement\nDefault: 0");
/* 155 */     registerConfigWithTooltip("new_crew_world_message", "World Message for new Crews", "Sends a message to all players when a new crew gets formed\nDefault: false");
/* 156 */     registerConfigWithTooltip("disable_friendly_fire", "Disable Friendly Fire", "Disabled the friendly damage between crewmates\nDefault: true");
/*     */     
/* 158 */     registerConfigWithTooltip("spawn_count", "Spawn Count per Chunk", "Kairoseki count per chunk\nDefault: 5");
/* 159 */     registerConfigWithTooltip("spawn_height", "Spawn Height", "Kairoseki maximum height\nDefault: 128");
/*     */     
/* 161 */     registerConfigWithTooltip("enable_quests", "Quests", "Allows quests to be accepted / completed\nDefault: true");
/* 162 */     registerConfigWithTooltip("quest_progression", "Quest Progression", "Allows quests to reward players with abilities, otherwise all abilities will be unlocked from the beginning\nDefault: true");
/*     */     
/* 164 */     registerConfigWithTooltip("enable_challenges", "Challenges", "Enabled the challenges menu and activation by players\nDefault: true");
/* 165 */     registerConfigWithTooltip("enable_re_challenges", "Retake Challenges", "Enabled retaking of already completed challenges\nDefault: false");
/*     */     
/* 167 */     registerConfigWithTooltip("drop_wanted_poster_packages", "Spawn Wanted Poster Packages", "Allows wanted poster packages to drop from the sky\nDefault: true");
/* 168 */     registerConfigWithTooltip("time_package_drops", "Time between package drops", "Time it takes for another package to drop\nDefault: 18000 (15 minutes)");
/*     */     
/* 170 */     registerConfigWithTooltip("update_message", "Ingame Update Notification", "Allows the game to show a text message when the installed mod is outdated\nDefault: true");
/* 171 */     registerConfigWithTooltip("fov_remover", "Remove Extreme FOV Changes", "Keeps the FOV fixed when the player has speed effects active\nDefault: true");
/* 172 */     registerConfigWithTooltip("tooltip_message", "Tooltip Messages", "Displays tooltips when hovering over certain elements like config options, abilities etc\nDefault: true");
/*     */   }
/*     */ 
/*     */   
/*     */   private static void registerConfigWithTooltip(String id, String localized, String tootip) {
/* 177 */     WyRegistry.registerName("gui.mineminenomi.config.option." + id, localized);
/* 178 */     WyRegistry.registerName("gui.mineminenomi.config.option." + id + ".tooltip", tootip);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\init\ModI18nConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */