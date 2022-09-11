package xyz.pixelatedw.mineminenomi.screens.config.categories;

import net.minecraft.client.settings.AbstractOption;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.screens.config.ConfigCategoryList;
import xyz.pixelatedw.mineminenomi.screens.config.ConfigPage;
import xyz.pixelatedw.mineminenomi.screens.config.ModBooleanOption;
import xyz.pixelatedw.mineminenomi.screens.config.ModSliderOption;

public class BountyConfigPage
  extends ConfigPage {
  private ModBooleanOption wantedPosterPackages = new ModBooleanOption("drop_wanted_poster_packages", CommonConfig.INSTANCE.wantedPosterPackages);
  private ModSliderOption timeBetweenPackageDrops = new ModSliderOption("time_package_drops", 0.0D, 72000.0D, 200.0F, CommonConfig.INSTANCE.timeBetweenPackageDrops);


  
  public void init(ConfigCategoryList list) {
    list.addOption((AbstractOption)this.wantedPosterPackages);
    list.addOption((AbstractOption)this.timeBetweenPackageDrops);
  }


  
  public ITextComponent getTitle() {
    return (ITextComponent)new TranslationTextComponent("gui.mineminenomi.config.category.bounty", new Object[0]);
  }
}


