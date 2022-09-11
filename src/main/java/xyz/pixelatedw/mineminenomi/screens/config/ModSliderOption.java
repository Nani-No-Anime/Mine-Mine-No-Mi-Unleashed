package xyz.pixelatedw.mineminenomi.screens.config;

import net.minecraft.client.GameSettings;
import net.minecraft.client.settings.SliderPercentageOption;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.ForgeConfigSpec;

public class ModSliderOption
  extends SliderPercentageOption
  implements IExtendedOption
{
  private String translationKey;
  
  public ModSliderOption(String name, double min, double max, float step, ForgeConfigSpec.DoubleValue value) {
    super("gui.mineminenomi.config.option." + name, min, max, step, gameSettings -> Double.valueOf(((Double)value.get()).doubleValue()), (gameSettings, val) -> value.set(Double.valueOf(MathHelper.clamp(val.doubleValue(), min, max))), (gameSettings, option) -> {
          TranslationTextComponent nameComp = new TranslationTextComponent("gui.mineminenomi.config.option." + name, new Object[0]);


          
          String valStr = String.format("%,.1f", new Object[] { Double.valueOf(option.get(gameSettings)) });


          
          return (new TranslationTextComponent("%s: %s", new Object[] { nameComp, valStr })).getFormattedText();
        });

    
    this.translationKey = "gui.mineminenomi.config.option." + name;
  }

  
  public ModSliderOption(String name, double min, double max, float step, ForgeConfigSpec.IntValue value) {
    super("gui.mineminenomi.config.option." + name, min, max, step, gameSettings -> Double.valueOf(((Integer)value.get()).intValue()), (gameSettings, val) -> value.set(Integer.valueOf(MathHelper.clamp(val.intValue(), (int)min, (int)max))), (gameSettings, option) -> {
          TranslationTextComponent nameComp = new TranslationTextComponent("gui.mineminenomi.config.option." + name, new Object[0]);


          
          String valStr = String.format("%,.0f", new Object[] { Double.valueOf(option.get(gameSettings)) });


          
          return (new TranslationTextComponent("%s: %s", new Object[] { nameComp, valStr })).getFormattedText();
        });

    
    this.translationKey = "gui.mineminenomi.config.option." + name;
  }


  
  public String getTranslateKey() {
    return this.translationKey;
  }
}


