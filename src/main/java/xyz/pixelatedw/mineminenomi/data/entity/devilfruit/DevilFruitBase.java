package xyz.pixelatedw.mineminenomi.data.entity.devilfruit;

import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class DevilFruitBase
  implements IDevilFruit {
  private String devilFruit = ""; private String zoanPoint = "";
  
  private boolean isLogia = false, hasYamiPower = false;

  
  public String getDevilFruit() {
    return this.devilFruit;
  }


  
  public void setDevilFruit(String value) {
    this.devilFruit = value;
  }


  
  public void setDevilFruit(AkumaNoMiItem fruit) {
    this.devilFruit = DevilFruitHelper.getDevilFruitKey(fruit);
  }


  
  public boolean hasDevilFruit() {
    return !WyHelper.isNullOrEmpty(this.devilFruit);
  }


  
  public boolean hasDevilFruit(AkumaNoMiItem fruit) {
    String key = DevilFruitHelper.getDevilFruitKey(fruit);
    boolean check = getDevilFruit().equalsIgnoreCase(key);
    
    if (!check && key.equalsIgnoreCase("yami_yami")) {
      check = hasYamiPower();
    }
    return check;
  }


  
  public boolean isLogia() {
    return this.isLogia;
  }


  
  public void setLogia(boolean value) {
    this.isLogia = value;
  }


  
  public boolean hasYamiPower() {
    return this.hasYamiPower;
  }


  
  public void setYamiPower(boolean value) {
    this.hasYamiPower = value;
  }


  
  public String getZoanPoint() {
    return this.zoanPoint;
  }


  
  public void setZoanPoint(String value) {
    this.zoanPoint = value;
  }
}


