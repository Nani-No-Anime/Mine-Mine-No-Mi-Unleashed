package xyz.pixelatedw.mineminenomi.data.entity.haki;

import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;

public class HakiDataBase
  implements IHakiData {
  private float kenbunshokuExp;
  private float busoshokuHardeningExp;
  private float busoshokuImbuingExp;
  private int hakiOveruse;
  
  public float getTotalHakiExp() {
    return this.kenbunshokuExp + this.busoshokuHardeningExp + this.busoshokuImbuingExp;
  }

  
  public int getHakiOveruse() {
    return this.hakiOveruse;
  }

  
  public void alterHakiOveruse(int value) {
    this.hakiOveruse = MathHelper.clamp(this.hakiOveruse + value, 0, 2147483647);
  }

  
  public void setHakiOveruse(int value) {
    this.hakiOveruse = value;
  }


  
  public float getKenbunshokuHakiExp() {
    return this.kenbunshokuExp;
  }


  
  public void alterKenbunshokuHakiExp(float value) {
    this.kenbunshokuExp = MathHelper.clamp(this.kenbunshokuExp + value, 0.0F, CommonConfig.INSTANCE.getHakiExpLimit());
  }


  
  public void setKenbunshokuHakiExp(float value) {
    this.kenbunshokuExp = value;
  }


  
  public float getBusoshokuHardeningHakiExp() {
    return this.busoshokuHardeningExp;
  }


  
  public void alterBusoshokuHardeningHakiExp(float value) {
    this.busoshokuHardeningExp = MathHelper.clamp(this.busoshokuHardeningExp + value, 0.0F, CommonConfig.INSTANCE.getHakiExpLimit());
  }


  
  public void setBusoshokuHardeningHakiExp(float value) {
    this.busoshokuHardeningExp = value;
  }


  
  public float getBusoshokuImbuingHakiExp() {
    return this.busoshokuImbuingExp;
  }


  
  public void alterBusoshokuImbuingHakiExp(float value) {
    this.busoshokuImbuingExp = MathHelper.clamp(this.busoshokuImbuingExp + value, 0.0F, CommonConfig.INSTANCE.getHakiExpLimit());
  }


  
  public void setBusoshokuImbuingHakiExp(float value) {
    this.busoshokuImbuingExp = value;
  }
}


