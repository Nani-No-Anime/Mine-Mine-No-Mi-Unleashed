package xyz.pixelatedw.mineminenomi.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.quests.artofweather.ArtOfWeatherTrial01Quest;
import xyz.pixelatedw.mineminenomi.quests.artofweather.ArtOfWeatherTrial02Quest;
import xyz.pixelatedw.mineminenomi.quests.artofweather.ArtOfWeatherTrial03Quest;
import xyz.pixelatedw.mineminenomi.quests.artofweather.ArtOfWeatherTrial04Quest;
import xyz.pixelatedw.mineminenomi.quests.blackleg.*;
import xyz.pixelatedw.mineminenomi.quests.brawler.*;
import xyz.pixelatedw.mineminenomi.quests.doctor.DoctorTrial01Quest;
import xyz.pixelatedw.mineminenomi.quests.doctor.DoctorTrial02Quest;
import xyz.pixelatedw.mineminenomi.quests.doctor.DoctorTrial03Quest;
import xyz.pixelatedw.mineminenomi.quests.sniper.*;
import xyz.pixelatedw.mineminenomi.quests.swordsman.*;
import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;


@EventBusSubscriber(modid = "mineminenomi", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModQuests
{
  public static final Quest SWORDSMAN_TRIAL_01 = (Quest)new SwordsmanTrial01Quest();
  public static final Quest SWORDSMAN_TRIAL_02 = (Quest)new SwordsmanTrial02Quest();
  public static final Quest SWORDSMAN_TRIAL_03 = (Quest)new SwordsmanTrial03Quest();
  public static final Quest SWORDSMAN_TRIAL_04 = (Quest)new SwordsmanTrial04Quest();
  public static final Quest SWORDSMAN_TRIAL_05 = (Quest)new SwordsmanTrial05Quest();
  public static final Quest[] SWORDSMAN_TRIALS = new Quest[] { SWORDSMAN_TRIAL_01, SWORDSMAN_TRIAL_02, SWORDSMAN_TRIAL_03, SWORDSMAN_TRIAL_04, SWORDSMAN_TRIAL_05 };

  
  public static final Quest SNIPER_TRIAL_01 = (Quest)new SniperTrial01Quest();
  public static final Quest SNIPER_TRIAL_02 = (Quest)new SniperTrial02Quest();
  public static final Quest SNIPER_TRIAL_03 = (Quest)new SniperTrial03Quest();
  public static final Quest SNIPER_TRIAL_04 = (Quest)new SniperTrial04Quest();
  public static final Quest SNIPER_TRIAL_05 = (Quest)new SniperTrial05Quest();
  public static final Quest SNIPER_TRIAL_06 = (Quest)new SniperTrial06Quest();
  public static final Quest[] SNIPER_TRIALS = new Quest[] { SNIPER_TRIAL_01, SNIPER_TRIAL_02, SNIPER_TRIAL_03, SNIPER_TRIAL_04, SNIPER_TRIAL_05, SNIPER_TRIAL_06 };

  
  public static final Quest DOCTOR_TRIAL_01 = (Quest)new DoctorTrial01Quest();
  public static final Quest DOCTOR_TRIAL_02 = (Quest)new DoctorTrial02Quest();
  public static final Quest DOCTOR_TRIAL_03 = (Quest)new DoctorTrial03Quest();
  public static final Quest[] DOCTOR_TRIALS = new Quest[] { DOCTOR_TRIAL_01, DOCTOR_TRIAL_02, DOCTOR_TRIAL_03 };

  
  public static final Quest ART_OF_WEATHER_TRIAL_01 = (Quest)new ArtOfWeatherTrial01Quest();
  public static final Quest ART_OF_WEATHER_TRIAL_02 = (Quest)new ArtOfWeatherTrial02Quest();
  public static final Quest ART_OF_WEATHER_TRIAL_03 = (Quest)new ArtOfWeatherTrial03Quest();
  public static final Quest ART_OF_WEATHER_TRIAL_04 = (Quest)new ArtOfWeatherTrial04Quest();
  public static final Quest[] ART_OF_WEATHER_TRIALS = new Quest[] { ART_OF_WEATHER_TRIAL_01, ART_OF_WEATHER_TRIAL_02, ART_OF_WEATHER_TRIAL_03, ART_OF_WEATHER_TRIAL_04 };

  
  public static final Quest BRAWLER_TRIAL_01 = (Quest)new BrawlerTrial01Quest();
  public static final Quest BRAWLER_TRIAL_02 = (Quest)new BrawlerTrial02Quest();
  public static final Quest BRAWLER_TRIAL_03 = (Quest)new BrawlerTrial03Quest();
  public static final Quest BRAWLER_TRIAL_04 = (Quest)new BrawlerTrial04Quest();
  public static final Quest BRAWLER_TRIAL_05 = (Quest)new BrawlerTrial05Quest();
  public static final Quest BRAWLER_TRIAL_06 = (Quest)new BrawlerTrial06Quest();
  public static final Quest[] BRAWLER_TRIALS = new Quest[] { BRAWLER_TRIAL_01, BRAWLER_TRIAL_02, BRAWLER_TRIAL_03, BRAWLER_TRIAL_04, BRAWLER_TRIAL_05, BRAWLER_TRIAL_06 };

  
  public static final Quest BLACK_LEG_TRIAL_01 = (Quest)new BlackLegTrial01Quest();
  public static final Quest BLACK_LEG_TRIAL_02 = (Quest)new BlackLegTrial02Quest();
  public static final Quest BLACK_LEG_TRIAL_03 = (Quest)new BlackLegTrial03Quest();
  public static final Quest BLACK_LEG_TRIAL_04 = (Quest)new BlackLegTrial04Quest();
  public static final Quest BLACK_LEG_TRIAL_05 = (Quest)new BlackLegTrial05Quest();
  public static final Quest[] BLACK_LEG_TRIALS = new Quest[] { BLACK_LEG_TRIAL_01, BLACK_LEG_TRIAL_02, BLACK_LEG_TRIAL_03, BLACK_LEG_TRIAL_04, BLACK_LEG_TRIAL_05 };




  
  static {
    if (WyDebug.isDebug());





    
    for (Quest quest : SWORDSMAN_TRIALS) {
      WyRegistry.registerQuest(quest);
    }
    
    for (Quest quest : SNIPER_TRIALS) {
      WyRegistry.registerQuest(quest);
    }
    
    for (Quest quest : DOCTOR_TRIALS) {
      WyRegistry.registerQuest(quest);
    }
    
    for (Quest quest : ART_OF_WEATHER_TRIALS) {
      WyRegistry.registerQuest(quest);
    }
    
    for (Quest quest : BRAWLER_TRIALS) {
      WyRegistry.registerQuest(quest);
    }
    
    for (Quest quest : BLACK_LEG_TRIALS)
      WyRegistry.registerQuest(quest); 
  }
}


