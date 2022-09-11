package xyz.pixelatedw.mineminenomi.data.entity.devilfruit;

import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;

public interface IDevilFruit {
  String getDevilFruit();
  
  void setDevilFruit(String paramString);
  
  void setDevilFruit(AkumaNoMiItem paramAkumaNoMiItem);
  
  boolean hasDevilFruit();
  
  boolean hasDevilFruit(AkumaNoMiItem paramAkumaNoMiItem);
  
  boolean isLogia();
  
  void setLogia(boolean paramBoolean);
  
  boolean hasYamiPower();
  
  void setYamiPower(boolean paramBoolean);
  
  String getZoanPoint();
  
  void setZoanPoint(String paramString);
}


