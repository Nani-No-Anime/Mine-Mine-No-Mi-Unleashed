package xyz.pixelatedw.mineminenomi.screens.config;

import net.minecraft.client.GameSettings;
import net.minecraft.client.settings.BooleanOption;
import net.minecraftforge.common.ForgeConfigSpec;

public class ModBooleanOption
  extends BooleanOption
  implements IExtendedOption {
  private String translationKey;
  
  public ModBooleanOption(String name, ForgeConfigSpec.BooleanValue value) {
    super("gui.mineminenomi.config.option." + name, gameSettings -> ((Boolean)value.get()).booleanValue(), (gameSettings, val) -> value.set(val));






    
    this.translationKey = "gui.mineminenomi.config.option." + name;
  }


  
  public String getTranslateKey() {
    return this.translationKey;
  }
}


