package xyz.pixelatedw.mineminenomi.api;

import net.minecraft.nbt.CompoundNBT;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;

import java.awt.*;
import java.util.Optional;



public class DFEncyclopediaEntry
{
  private final Optional<Color> baseColor;
  private final Optional<Color> stemColor;
  private final Optional<Integer> shape;
  private AkumaNoMiItem devilFruit;
  
  public static DFEncyclopediaEntry empty() {
    return new DFEncyclopediaEntry(Optional.empty(), Optional.empty(), Optional.empty());
  }

  
  public static DFEncyclopediaEntry of(Optional<Integer> type, Optional<Color> baseColor, Optional<Color> stemColor) {
    return new DFEncyclopediaEntry(type, baseColor, stemColor);
  }

  
  public static DFEncyclopediaEntry of(CompoundNBT nbt) {
    Optional<Integer> shape = Optional.empty();
    
    int shapeVal = nbt.getInt("shape");
    if (shapeVal > 0) {
      shape = Optional.of(Integer.valueOf(shapeVal));
    }
    Color baseVal = new Color(nbt.getInt("baseColor"));
    Color stemVal = new Color(nbt.getInt("stemColor"));
    
    return new DFEncyclopediaEntry(shape, Optional.of(baseVal), Optional.of(stemVal));
  }

  
  public DFEncyclopediaEntry(Optional<Integer> shape, Optional<Color> baseColor, Optional<Color> stemColor) {
    this.shape = shape;
    this.baseColor = baseColor;
    this.stemColor = stemColor;
  }

  
  public Optional<Color> getBaseColor() {
    return this.baseColor;
  }

  
  public Optional<Color> getStemColor() {
    return this.stemColor;
  }

  
  public Optional<Integer> getShape() {
    return this.shape;
  }

  
  public AkumaNoMiItem getDevilFruit() {
    return this.devilFruit;
  }

  
  public void setDevilFruit(AkumaNoMiItem devilFruit) {
    this.devilFruit = devilFruit;
  }

  
  public int getCompletion() {
    int sum = 0;
    if (getShape().isPresent())
      sum++; 
    if (getBaseColor().isPresent() && ((Color)getBaseColor().get()).getRGB() != Color.BLACK.getRGB())
      sum++; 
    if (getStemColor().isPresent() && ((Color)getStemColor().get()).getRGB() != Color.BLACK.getRGB())
      sum++; 
    return sum;
  }


  
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[ ");
    if (getShape().isPresent())
      sb.append("Shape: " + getShape().get() + " "); 
    if (getBaseColor().isPresent())
      sb.append("Base Color: " + ((Color)getBaseColor().get()).toString() + " "); 
    if (getStemColor().isPresent())
      sb.append("Stem Color: " + ((Color)getStemColor().get()).toString() + " "); 
    sb.append("Completion: " + getCompletion());
    sb.append(" ]");
    return sb.toString();
  }
}


