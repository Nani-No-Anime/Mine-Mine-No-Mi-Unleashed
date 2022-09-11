package xyz.pixelatedw.mineminenomi.screens.config;

import net.minecraft.client.GameSettings;
import net.minecraft.client.settings.IteratableOption;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.ForgeConfigSpec;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;

public class ModIteratableOption
  extends IteratableOption
  implements IExtendedOption {
  private String translationKey;
  
  public ModIteratableOption(String name, ForgeConfigSpec.EnumValue<? extends CommonConfig.IConfigEnum> value) {
    super("gui.mineminenomi.config.option." + name, (gameSettings, i) -> value.set(((CommonConfig.IConfigEnum)value.get()).next()), (gameSettings, option) -> {
          TranslationTextComponent nameComp = new TranslationTextComponent("gui.mineminenomi.config.option." + name, new Object[0]);


          
          return (new TranslationTextComponent("%s: %s", new Object[] { nameComp, ((Enum)value.get()).name() })).getFormattedText();
        });

    
    this.translationKey = "gui.mineminenomi.config.option." + name;
  }


  
  public String getTranslateKey() {
    return this.translationKey;
  }
}


