package xyz.pixelatedw.mineminenomi.data.entity.haki;

public interface IHakiData {
  float getTotalHakiExp();
  
  int getHakiOveruse();
  
  void alterHakiOveruse(int paramInt);
  
  void setHakiOveruse(int paramInt);
  
  float getKenbunshokuHakiExp();
  
  void alterKenbunshokuHakiExp(float paramFloat);
  
  void setKenbunshokuHakiExp(float paramFloat);
  
  float getBusoshokuHardeningHakiExp();
  
  void alterBusoshokuHardeningHakiExp(float paramFloat);
  
  void setBusoshokuHardeningHakiExp(float paramFloat);
  
  float getBusoshokuImbuingHakiExp();
  
  void alterBusoshokuImbuingHakiExp(float paramFloat);
  
  void setBusoshokuImbuingHakiExp(float paramFloat);
}


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\data\entity\haki\IHakiData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */