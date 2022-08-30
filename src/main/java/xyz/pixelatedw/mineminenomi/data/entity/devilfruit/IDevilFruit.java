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


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\data\entity\devilfruit\IDevilFruit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */