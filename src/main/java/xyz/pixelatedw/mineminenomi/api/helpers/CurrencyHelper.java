package xyz.pixelatedw.mineminenomi.api.helpers;





public class CurrencyHelper
{
  public static final int BELLY_TO_EXTOL = 10000;
  
  public static long getExtolFromBelly(long belly) {
    return belly * 10000L;
  }

  
  public static long getBellyFromExtol(long extol) {
    if (extol < 10000L)
    {
      return -1L;
    }
    return extol / 10000L;
  }
}


