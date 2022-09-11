package xyz.pixelatedw.mineminenomi.api.enums;

import net.minecraft.util.text.TextFormatting;

public enum FruitType
{
  PARAMECIA(TextFormatting.RED, "Paramecia"),
  LOGIA(TextFormatting.YELLOW, "Logia"),
  ZOAN(TextFormatting.GREEN, "Zoan"),
  MYTHICAL_ZOAN(TextFormatting.AQUA, "Mythical Zoan"),
  ANCIENT_ZOAN(TextFormatting.DARK_GREEN, "Ancient Zoan"),
  ARTIFICIAL_LOGIA(TextFormatting.GOLD, "Artificial Logia"),
  ARTIFICIAL_PARAMECIA(TextFormatting.GOLD, "Artificial Paramecia"),
  ARTIFICIAL_ZOAN(TextFormatting.GOLD, "Artificial Zoan");
  
  private TextFormatting color;
  
  private String name;
  
  FruitType(TextFormatting color, String name) {
    this.color = color;
    this.name = name;
  }

  
  public TextFormatting getColor() {
    return this.color;
  }

  
  public String getName() {
    return this.name;
  }
}


