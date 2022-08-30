/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ 
/*     */ import net.minecraftforge.fml.common.Mod;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.artofweather.ArtOfWeatherTrial01Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.artofweather.ArtOfWeatherTrial02Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.artofweather.ArtOfWeatherTrial03Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.artofweather.ArtOfWeatherTrial04Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.blackleg.BlackLegTrial01Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.blackleg.BlackLegTrial02Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.blackleg.BlackLegTrial03Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.blackleg.BlackLegTrial04Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.blackleg.BlackLegTrial05Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.brawler.BrawlerTrial01Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.brawler.BrawlerTrial02Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.brawler.BrawlerTrial03Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.brawler.BrawlerTrial04Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.brawler.BrawlerTrial05Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.brawler.BrawlerTrial06Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.doctor.DoctorTrial01Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.doctor.DoctorTrial02Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.doctor.DoctorTrial03Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.sniper.SniperTrial01Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.sniper.SniperTrial02Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.sniper.SniperTrial03Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.sniper.SniperTrial04Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.sniper.SniperTrial05Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.sniper.SniperTrial06Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.swordsman.SwordsmanTrial01Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.swordsman.SwordsmanTrial02Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.swordsman.SwordsmanTrial03Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.swordsman.SwordsmanTrial04Quest;
/*     */ import xyz.pixelatedw.mineminenomi.quests.swordsman.SwordsmanTrial05Quest;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi", bus = Mod.EventBusSubscriber.Bus.MOD)
/*     */ public class ModQuests
/*     */ {
/*  42 */   public static final Quest SWORDSMAN_TRIAL_01 = (Quest)new SwordsmanTrial01Quest();
/*  43 */   public static final Quest SWORDSMAN_TRIAL_02 = (Quest)new SwordsmanTrial02Quest();
/*  44 */   public static final Quest SWORDSMAN_TRIAL_03 = (Quest)new SwordsmanTrial03Quest();
/*  45 */   public static final Quest SWORDSMAN_TRIAL_04 = (Quest)new SwordsmanTrial04Quest();
/*  46 */   public static final Quest SWORDSMAN_TRIAL_05 = (Quest)new SwordsmanTrial05Quest();
/*  47 */   public static final Quest[] SWORDSMAN_TRIALS = new Quest[] { SWORDSMAN_TRIAL_01, SWORDSMAN_TRIAL_02, SWORDSMAN_TRIAL_03, SWORDSMAN_TRIAL_04, SWORDSMAN_TRIAL_05 };
/*     */ 
/*     */   
/*  50 */   public static final Quest SNIPER_TRIAL_01 = (Quest)new SniperTrial01Quest();
/*  51 */   public static final Quest SNIPER_TRIAL_02 = (Quest)new SniperTrial02Quest();
/*  52 */   public static final Quest SNIPER_TRIAL_03 = (Quest)new SniperTrial03Quest();
/*  53 */   public static final Quest SNIPER_TRIAL_04 = (Quest)new SniperTrial04Quest();
/*  54 */   public static final Quest SNIPER_TRIAL_05 = (Quest)new SniperTrial05Quest();
/*  55 */   public static final Quest SNIPER_TRIAL_06 = (Quest)new SniperTrial06Quest();
/*  56 */   public static final Quest[] SNIPER_TRIALS = new Quest[] { SNIPER_TRIAL_01, SNIPER_TRIAL_02, SNIPER_TRIAL_03, SNIPER_TRIAL_04, SNIPER_TRIAL_05, SNIPER_TRIAL_06 };
/*     */ 
/*     */   
/*  59 */   public static final Quest DOCTOR_TRIAL_01 = (Quest)new DoctorTrial01Quest();
/*  60 */   public static final Quest DOCTOR_TRIAL_02 = (Quest)new DoctorTrial02Quest();
/*  61 */   public static final Quest DOCTOR_TRIAL_03 = (Quest)new DoctorTrial03Quest();
/*  62 */   public static final Quest[] DOCTOR_TRIALS = new Quest[] { DOCTOR_TRIAL_01, DOCTOR_TRIAL_02, DOCTOR_TRIAL_03 };
/*     */ 
/*     */   
/*  65 */   public static final Quest ART_OF_WEATHER_TRIAL_01 = (Quest)new ArtOfWeatherTrial01Quest();
/*  66 */   public static final Quest ART_OF_WEATHER_TRIAL_02 = (Quest)new ArtOfWeatherTrial02Quest();
/*  67 */   public static final Quest ART_OF_WEATHER_TRIAL_03 = (Quest)new ArtOfWeatherTrial03Quest();
/*  68 */   public static final Quest ART_OF_WEATHER_TRIAL_04 = (Quest)new ArtOfWeatherTrial04Quest();
/*  69 */   public static final Quest[] ART_OF_WEATHER_TRIALS = new Quest[] { ART_OF_WEATHER_TRIAL_01, ART_OF_WEATHER_TRIAL_02, ART_OF_WEATHER_TRIAL_03, ART_OF_WEATHER_TRIAL_04 };
/*     */ 
/*     */   
/*  72 */   public static final Quest BRAWLER_TRIAL_01 = (Quest)new BrawlerTrial01Quest();
/*  73 */   public static final Quest BRAWLER_TRIAL_02 = (Quest)new BrawlerTrial02Quest();
/*  74 */   public static final Quest BRAWLER_TRIAL_03 = (Quest)new BrawlerTrial03Quest();
/*  75 */   public static final Quest BRAWLER_TRIAL_04 = (Quest)new BrawlerTrial04Quest();
/*  76 */   public static final Quest BRAWLER_TRIAL_05 = (Quest)new BrawlerTrial05Quest();
/*  77 */   public static final Quest BRAWLER_TRIAL_06 = (Quest)new BrawlerTrial06Quest();
/*  78 */   public static final Quest[] BRAWLER_TRIALS = new Quest[] { BRAWLER_TRIAL_01, BRAWLER_TRIAL_02, BRAWLER_TRIAL_03, BRAWLER_TRIAL_04, BRAWLER_TRIAL_05, BRAWLER_TRIAL_06 };
/*     */ 
/*     */   
/*  81 */   public static final Quest BLACK_LEG_TRIAL_01 = (Quest)new BlackLegTrial01Quest();
/*  82 */   public static final Quest BLACK_LEG_TRIAL_02 = (Quest)new BlackLegTrial02Quest();
/*  83 */   public static final Quest BLACK_LEG_TRIAL_03 = (Quest)new BlackLegTrial03Quest();
/*  84 */   public static final Quest BLACK_LEG_TRIAL_04 = (Quest)new BlackLegTrial04Quest();
/*  85 */   public static final Quest BLACK_LEG_TRIAL_05 = (Quest)new BlackLegTrial05Quest();
/*  86 */   public static final Quest[] BLACK_LEG_TRIALS = new Quest[] { BLACK_LEG_TRIAL_01, BLACK_LEG_TRIAL_02, BLACK_LEG_TRIAL_03, BLACK_LEG_TRIAL_04, BLACK_LEG_TRIAL_05 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  93 */     if (WyDebug.isDebug());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 100 */     for (Quest quest : SWORDSMAN_TRIALS) {
/* 101 */       WyRegistry.registerQuest(quest);
/*     */     }
/*     */     
/* 104 */     for (Quest quest : SNIPER_TRIALS) {
/* 105 */       WyRegistry.registerQuest(quest);
/*     */     }
/*     */     
/* 108 */     for (Quest quest : DOCTOR_TRIALS) {
/* 109 */       WyRegistry.registerQuest(quest);
/*     */     }
/*     */     
/* 112 */     for (Quest quest : ART_OF_WEATHER_TRIALS) {
/* 113 */       WyRegistry.registerQuest(quest);
/*     */     }
/*     */     
/* 116 */     for (Quest quest : BRAWLER_TRIALS) {
/* 117 */       WyRegistry.registerQuest(quest);
/*     */     }
/*     */     
/* 120 */     for (Quest quest : BLACK_LEG_TRIALS)
/* 121 */       WyRegistry.registerQuest(quest); 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\init\ModQuests.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */