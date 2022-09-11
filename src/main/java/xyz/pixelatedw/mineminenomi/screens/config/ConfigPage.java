package xyz.pixelatedw.mineminenomi.screens.config;

import net.minecraft.util.text.ITextComponent;

public abstract class ConfigPage {
  public abstract void init(ConfigCategoryList paramConfigCategoryList);
  
  public abstract ITextComponent getTitle();
}


