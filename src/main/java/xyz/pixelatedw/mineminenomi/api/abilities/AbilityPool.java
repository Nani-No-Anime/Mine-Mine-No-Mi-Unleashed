package xyz.pixelatedw.mineminenomi.api.abilities;

public enum AbilityPool
{
  GEPPO_LIKE,
  TEKKAI_LIKE,
  BUSOSHOKU_HAKI,
  MINK_ELECTRO,
  WEATHER_BALLS,
  OTO_ABILITY;

  
  public int id() {
    return ordinal() + 1;
  }
}


