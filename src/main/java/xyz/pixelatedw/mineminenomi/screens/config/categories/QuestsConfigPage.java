package xyz.pixelatedw.mineminenomi.screens.config.categories;

import net.minecraft.client.settings.AbstractOption;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.screens.config.ConfigCategoryList;
import xyz.pixelatedw.mineminenomi.screens.config.ConfigPage;
import xyz.pixelatedw.mineminenomi.screens.config.ModBooleanOption;

public class QuestsConfigPage
  extends ConfigPage {
  private ModBooleanOption enableQuests = new ModBooleanOption("enable_quests", CommonConfig.INSTANCE.enableQuests);
  private ModBooleanOption questProgression = new ModBooleanOption("quest_progression", CommonConfig.INSTANCE.questProgression);


  
  public void init(ConfigCategoryList list) {
    list.addOption((AbstractOption)this.enableQuests);
    list.addOption((AbstractOption)this.questProgression);
  }


  
  public ITextComponent getTitle() {
    return (ITextComponent)new TranslationTextComponent("gui.mineminenomi.config.category.quests", new Object[0]);
  }
}


