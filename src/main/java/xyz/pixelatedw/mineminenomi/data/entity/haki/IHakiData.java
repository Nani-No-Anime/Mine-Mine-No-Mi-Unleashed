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


