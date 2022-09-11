package xyz.pixelatedw.mineminenomi.screens.config.categories;

import net.minecraft.client.settings.AbstractOption;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.screens.config.ConfigCategoryList;
import xyz.pixelatedw.mineminenomi.screens.config.ConfigPage;
import xyz.pixelatedw.mineminenomi.screens.config.ModBooleanOption;

public class ChallengesConfigPage
  extends ConfigPage {
  private ModBooleanOption enableChallenges = new ModBooleanOption("enable_challenges", CommonConfig.INSTANCE.enableChallenges);
  private ModBooleanOption enableReChallenges = new ModBooleanOption("enable_re_challenges", CommonConfig.INSTANCE.enableReChallenges);


  
  public void init(ConfigCategoryList list) {
    list.addOption((AbstractOption)this.enableChallenges);
    list.addOption((AbstractOption)this.enableReChallenges);
  }


  
  public ITextComponent getTitle() {
    return (ITextComponent)new TranslationTextComponent("gui.mineminenomi.config.category.challenges", new Object[0]);
  }
}


