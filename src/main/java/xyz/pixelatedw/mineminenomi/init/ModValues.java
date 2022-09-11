package xyz.pixelatedw.mineminenomi.init;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.UUID;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;




public class ModValues
{
  public static ArrayList<AkumaNoMiItem> devilfruits = new ArrayList<>();
  public static ArrayList<AkumaNoMiItem> logias = new ArrayList<>();
  
  public static final String API_URL = "https://pixelatedw.xyz/api/v1";
  
  public static final Gson GSON = (new GsonBuilder())
    .disableHtmlEscaping()
    .setPrettyPrinting()
    .create();


  
  public static final UUID NIL_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");
  public static final int MAX_QUESTS = 4;
  public static final int MAX_ULTRACOLA = 20;
  public static final int MAX_GENERAL = 999999999;
  public static final int MAX_CREW = 50;
  public static final long MAX_CURRENCY = 999999999L;
  public static final int MAX_LOYALTY = 100;
  public static final int CHARACTER_CREATOR = 0;
  public static final int WANTED_POSTER = 1;
  public static final long MAX_BOUNTY = 100000000000L;
  public static final String PIRATE = "pirate";
  public static final String MARINE = "marine";
  public static final String BOUNTY_HUNTER = "bounty_hunter";
  public static final String REVOLUTIONARY = "revolutionary";
  public static final String BANDIT = "bandit";
  public static final String CIVILIAN = "civilian";
  public static final String HUMAN = "human";
  public static final String FISHMAN = "fishman";
  public static final String CYBORG = "cyborg";
  public static final String MINK = "mink";
  public static final String MINK_BUNNY = "mink_bunny";
  public static final String MINK_DOG = "mink_dog";
  public static final String MINK_LION = "mink_lion";
  public static final String SWORDSMAN = "swordsman";
  public static final String SNIPER = "sniper";
  public static final String DOCTOR = "doctor";
  public static final String ART_OF_WEATHER = "art_of_weather";
  public static final String BLACK_LEG = "black_leg";
  public static final String BRAWLER = "brawler";
}


