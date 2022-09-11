package xyz.pixelatedw.mineminenomi.screens.config.categories;

import net.minecraft.client.settings.AbstractOption;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.screens.config.ConfigCategoryList;
import xyz.pixelatedw.mineminenomi.screens.config.ConfigPage;
import xyz.pixelatedw.mineminenomi.screens.config.ModBooleanOption;
import xyz.pixelatedw.mineminenomi.screens.config.ModSliderOption;

public class CrewsConfigPage
  extends ConfigPage {
  private ModSliderOption bountyRequirement = new ModSliderOption("bounty_requirement", 0.0D, 100000.0D, 100.0F, CommonConfig.INSTANCE.bountyRequirement);
  private ModBooleanOption worldMessageOnCrewCreate = new ModBooleanOption("new_crew_world_message", CommonConfig.INSTANCE.worldMessageOnCrewCreate);
  private ModBooleanOption disableFriendlyFire = new ModBooleanOption("disable_friendly_fire", CommonConfig.INSTANCE.disableFriendlyFire);


  
  public void init(ConfigCategoryList list) {
    list.addOption((AbstractOption)this.bountyRequirement);
    list.addOption((AbstractOption)this.worldMessageOnCrewCreate);
    list.addOption((AbstractOption)this.disableFriendlyFire);
  }


  
  public ITextComponent getTitle() {
    return (ITextComponent)new TranslationTextComponent("gui.mineminenomi.config.category.crews", new Object[0]);
  }
}


