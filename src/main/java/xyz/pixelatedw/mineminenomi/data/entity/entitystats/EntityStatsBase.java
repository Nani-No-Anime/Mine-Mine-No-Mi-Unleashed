package xyz.pixelatedw.mineminenomi.data.entity.entitystats;

import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import javax.annotation.Nullable;


public class EntityStatsBase
  implements IEntityStats
{
  private int doriki;
  private int cola = 100; private int maxCola = 100; private int ultraCola = 0; private int loyalty = 0;
  private long bounty;
  private String faction = ""; private long belly; private long extol; private String race = ""; private String subRace = ""; private String fightingStyle = ""; private boolean hasShadow = true;
  private boolean hasHeart = true;
  private double damageMultiplier = 1.0D; private boolean inCombatMode = false;
  private boolean hasStrawDoll = true;
  private boolean isRogue = false;
  
  public int getDoriki() {
    return this.doriki;
  }


  
  public void alterDoriki(int value) {
    this.doriki = MathHelper.clamp(this.doriki + value, 0, CommonConfig.INSTANCE.getDorikiLimit());
  }


  
  public void setDoriki(int value) {
    if (value <= CommonConfig.INSTANCE.getDorikiLimit()) {
      this.doriki = value;
    } else {
      this.doriki = CommonConfig.INSTANCE.getDorikiLimit();
    } 
  }

  
  public long getBelly() {
    return this.belly;
  }


  
  public void alterBelly(long value) {
    this.belly = WyHelper.clamp(this.belly + value, 0L, 999999999L);
  }


  
  public void setBelly(long value) {
    this.belly = value;
  }


  
  public long getExtol() {
    return this.extol;
  }


  
  public void alterExtol(long value) {
    this.extol = WyHelper.clamp(this.extol + value, 0L, 999999999L);
  }


  
  public void setExtol(long value) {
    this.extol = value;
  }


  
  public long getBounty() {
    return this.bounty;
  }


  
  public void alterBounty(long value) {
    this.bounty = WyHelper.clamp(this.bounty + value, 0L, 100000000000L);
  }


  
  public void setBounty(long value) {
    this.bounty = value;
  }


  
  public int getCola() {
    return this.cola;
  }


  
  public void alterCola(int value) {
    this.cola = MathHelper.clamp(this.cola + value, 0, getMaxCola());
  }


  
  public void setCola(int value) {
    this.cola = value;
  }


  
  public int getMaxCola() {
    return this.maxCola;
  }


  
  public void alterMaxCola(int value) {
    this.maxCola = MathHelper.clamp(this.maxCola + value, 0, 1000);
    if (value != 0) {
      this.cola = MathHelper.clamp(this.cola, 0, this.maxCola);
    }
  }

  
  public void setMaxCola(int value) {
    this.maxCola = value;
  }


  
  public int getUltraCola() {
    return this.ultraCola;
  }


  
  public void setUltraCola(int value) {
    if (this.ultraCola + value < 0) {
      this.ultraCola = 0;
    } else {
      this.ultraCola += value;
    } 
  }

  
  public void addUltraCola(int value) {
    this.ultraCola += value;
  }


  
  public int getLoyalty() {
    return this.loyalty;
  }


  
  public void alterLoyalty(int value) {
    this.loyalty = MathHelper.clamp(this.loyalty + value, 0, 100);
  }


  
  public void setLoyalty(int value) {
    this.loyalty = value;
  }





  
  @Nullable
  public FactionHelper.MarineRank getMarineRank() {
    if (!isMarine()) {
      return null;
    }
    for (int i = 0; i < (FactionHelper.MarineRank.values()).length; i++) {
      
      FactionHelper.MarineRank rank = FactionHelper.MarineRank.values()[i];
      FactionHelper.MarineRank next = (i + 1 < (FactionHelper.MarineRank.values()).length) ? FactionHelper.MarineRank.values()[i + 1] : null;
      
      if (getLoyalty() >= rank.getRequiredLoyalty() && (next == null || getLoyalty() < next.getRequiredLoyalty()))
      {
        return rank;
      }
    } 
    
    return FactionHelper.MarineRank.CHORE_BOY;
  }


  
  public boolean hasMarineRank(FactionHelper.MarineRank rank) {
    if (!isMarine()) {
      return false;
    }
    if (getMarineRank().ordinal() >= rank.ordinal()) {
      return true;
    }
    return false;
  }


  
  @Nullable
  public FactionHelper.RevolutionaryRank getRevolutionaryRank() {
    if (!isRevolutionary()) {
      return null;
    }
    for (int i = 0; i < (FactionHelper.RevolutionaryRank.values()).length; i++) {
      
      FactionHelper.RevolutionaryRank rank = FactionHelper.RevolutionaryRank.values()[i];
      FactionHelper.RevolutionaryRank next = (i + 1 < (FactionHelper.RevolutionaryRank.values()).length) ? FactionHelper.RevolutionaryRank.values()[i + 1] : null;
      
      if (getLoyalty() >= rank.getRequiredLoyalty() && (next == null || getLoyalty() < next.getRequiredLoyalty()))
      {
        return rank;
      }
    } 
    
    return FactionHelper.RevolutionaryRank.MEMBER;
  }


  
  public boolean hasRevolutionaryRank(FactionHelper.RevolutionaryRank rank) {
    if (!isRevolutionary()) {
      return false;
    }
    if (getRevolutionaryRank().ordinal() >= rank.ordinal()) {
      return true;
    }
    return false;
  }


  
  public boolean isPirate() {
    if (WyHelper.isNullOrEmpty(this.faction)) {
      return false;
    }
    return this.faction.equalsIgnoreCase("pirate");
  }


  
  public boolean isMarine() {
    if (WyHelper.isNullOrEmpty(this.faction)) {
      return false;
    }
    return this.faction.equalsIgnoreCase("marine");
  }


  
  public boolean isBountyHunter() {
    if (WyHelper.isNullOrEmpty(this.faction)) {
      return false;
    }
    return this.faction.equalsIgnoreCase("bounty_hunter");
  }


  
  public boolean isRevolutionary() {
    if (WyHelper.isNullOrEmpty(this.faction)) {
      return false;
    }
    return this.faction.equalsIgnoreCase("revolutionary");
  }


  
  public boolean isBandit() {
    if (WyHelper.isNullOrEmpty(this.faction)) {
      return false;
    }
    return this.faction.equalsIgnoreCase("bandit");
  }


  
  public boolean isCivilian() {
    if (WyHelper.isNullOrEmpty(this.faction)) {
      return false;
    }
    return this.faction.equalsIgnoreCase("civilian");
  }


  
  public boolean hasFaction() {
    if (WyHelper.isNullOrEmpty(this.faction)) {
      return false;
    }
    return true;
  }


  
  public void setFaction(String value) {
    this.faction = WyHelper.getResourceName(value);
  }


  
  public String getFaction() {
    return this.faction;
  }


  
  public boolean isHuman() {
    if (WyHelper.isNullOrEmpty(this.race)) {
      return false;
    }
    return this.race.equalsIgnoreCase("human");
  }


  
  public boolean isFishman() {
    if (WyHelper.isNullOrEmpty(this.race)) {
      return false;
    }
    return this.race.equalsIgnoreCase("fishman");
  }


  
  public boolean isCyborg() {
    if (WyHelper.isNullOrEmpty(this.race)) {
      return false;
    }
    return this.race.equalsIgnoreCase("cyborg");
  }


  
  public boolean isMink() {
    if (WyHelper.isNullOrEmpty(this.race)) {
      return false;
    }
    return this.race.equalsIgnoreCase("mink");
  }


  
  public boolean hasRace() {
    if (WyHelper.isNullOrEmpty(this.race)) {
      return false;
    }
    return true;
  }


  
  public void setRace(String value) {
    this.race = WyHelper.getResourceName(value);
  }


  
  public String getRace() {
    return this.race;
  }


  
  public boolean isSwordsman() {
    if (WyHelper.isNullOrEmpty(this.fightingStyle)) {
      return false;
    }
    return this.fightingStyle.equalsIgnoreCase("swordsman");
  }


  
  public boolean isSniper() {
    if (WyHelper.isNullOrEmpty(this.fightingStyle)) {
      return false;
    }
    return this.fightingStyle.equalsIgnoreCase("sniper");
  }


  
  public boolean isDoctor() {
    if (WyHelper.isNullOrEmpty(this.fightingStyle)) {
      return false;
    }
    return this.fightingStyle.equalsIgnoreCase("doctor");
  }


  
  public boolean isWeatherWizard() {
    if (WyHelper.isNullOrEmpty(this.fightingStyle)) {
      return false;
    }
    return this.fightingStyle.equalsIgnoreCase("art_of_weather");
  }


  
  public boolean isBlackLeg() {
    if (WyHelper.isNullOrEmpty(this.fightingStyle)) {
      return false;
    }
    return this.fightingStyle.equalsIgnoreCase("black_leg");
  }


  
  public boolean isBrawler() {
    if (WyHelper.isNullOrEmpty(this.fightingStyle)) {
      return false;
    }
    return this.fightingStyle.equalsIgnoreCase("brawler");
  }


  
  public boolean hasFightingStyle() {
    if (WyHelper.isNullOrEmpty(this.fightingStyle)) {
      return false;
    }
    return true;
  }


  
  public void setFightingStyle(String value) {
    this.fightingStyle = value;
  }


  
  public String getFightingStyle() {
    return this.fightingStyle;
  }


  
  public boolean hasShadow() {
    return this.hasShadow;
  }


  
  public void setShadow(boolean value) {
    this.hasShadow = value;
  }


  
  public boolean hasHeart() {
    return this.hasHeart;
  }


  
  public void setHeart(boolean value) {
    this.hasHeart = value;
  }


  
  public boolean isInCombatMode() {
    return this.inCombatMode;
  }


  
  public void setCombatMode(boolean value) {
    this.inCombatMode = value;
  }


  
  public boolean hasStrawDoll() {
    return this.hasStrawDoll;
  }


  
  public void setStrawDoll(boolean value) {
    this.hasStrawDoll = value;
  }


  
  public double getDamageMultiplier() {
    return this.damageMultiplier;
  }


  
  public void setDamageMultiplier(double multiplier) {
    this.damageMultiplier = multiplier;
  }


  
  public boolean isBunnyMink() {
    return this.subRace.equalsIgnoreCase("mink_bunny");
  }


  
  public boolean isDogMink() {
    return this.subRace.equalsIgnoreCase("mink_dog");
  }


  
  public boolean isLionMink() {
    return this.subRace.equalsIgnoreCase("mink_lion");
  }


  
  public void setSubRace(String value) {
    this.subRace = value;
  }


  
  public String getSubRace() {
    return this.subRace;
  }


  
  public boolean isRogue() {
    return this.isRogue;
  }


  
  public void setRogue(boolean value) {
    this.isRogue = value;
  }
}


