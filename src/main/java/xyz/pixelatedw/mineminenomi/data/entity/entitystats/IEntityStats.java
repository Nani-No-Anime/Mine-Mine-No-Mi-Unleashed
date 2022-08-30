package xyz.pixelatedw.mineminenomi.data.entity.entitystats;

import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;

public interface IEntityStats {
  boolean isInCombatMode();
  
  void setCombatMode(boolean paramBoolean);
  
  int getDoriki();
  
  void alterDoriki(int paramInt);
  
  void setDoriki(int paramInt);
  
  long getBelly();
  
  void alterBelly(long paramLong);
  
  void setBelly(long paramLong);
  
  long getExtol();
  
  void alterExtol(long paramLong);
  
  void setExtol(long paramLong);
  
  long getBounty();
  
  void alterBounty(long paramLong);
  
  void setBounty(long paramLong);
  
  int getCola();
  
  void alterCola(int paramInt);
  
  void setCola(int paramInt);
  
  int getMaxCola();
  
  void alterMaxCola(int paramInt);
  
  void setMaxCola(int paramInt);
  
  int getUltraCola();
  
  void setUltraCola(int paramInt);
  
  void addUltraCola(int paramInt);
  
  int getLoyalty();
  
  void alterLoyalty(int paramInt);
  
  void setLoyalty(int paramInt);
  
  FactionHelper.MarineRank getMarineRank();
  
  boolean hasMarineRank(FactionHelper.MarineRank paramMarineRank);
  
  FactionHelper.RevolutionaryRank getRevolutionaryRank();
  
  boolean hasRevolutionaryRank(FactionHelper.RevolutionaryRank paramRevolutionaryRank);
  
  boolean isPirate();
  
  boolean isMarine();
  
  boolean isBountyHunter();
  
  boolean isRevolutionary();
  
  boolean isBandit();
  
  boolean isCivilian();
  
  boolean hasFaction();
  
  void setFaction(String paramString);
  
  String getFaction();
  
  boolean isHuman();
  
  boolean isFishman();
  
  boolean isCyborg();
  
  boolean isMink();
  
  boolean hasRace();
  
  void setRace(String paramString);
  
  String getRace();
  
  boolean isBunnyMink();
  
  boolean isDogMink();
  
  boolean isLionMink();
  
  void setSubRace(String paramString);
  
  String getSubRace();
  
  boolean isSwordsman();
  
  boolean isSniper();
  
  boolean isDoctor();
  
  boolean isWeatherWizard();
  
  boolean isBlackLeg();
  
  boolean isBrawler();
  
  boolean hasFightingStyle();
  
  void setFightingStyle(String paramString);
  
  String getFightingStyle();
  
  boolean hasShadow();
  
  void setShadow(boolean paramBoolean);
  
  boolean hasHeart();
  
  void setHeart(boolean paramBoolean);
  
  double getDamageMultiplier();
  
  void setDamageMultiplier(double paramDouble);
  
  boolean hasStrawDoll();
  
  void setStrawDoll(boolean paramBoolean);
  
  boolean isRogue();
  
  void setRogue(boolean paramBoolean);
}


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\data\entity\entitystats\IEntityStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */