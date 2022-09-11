package xyz.pixelatedw.mineminenomi.screens.config.categories;

import net.minecraft.client.settings.AbstractOption;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.screens.config.ConfigCategoryList;
import xyz.pixelatedw.mineminenomi.screens.config.ConfigPage;
import xyz.pixelatedw.mineminenomi.screens.config.ModSliderOption;

public class OresConfigPage
  extends ConfigPage {
  private ModSliderOption kairosekiSpawnCount = new ModSliderOption("spawn_count", 1.0D, 20.0D, 1.0F, CommonConfig.INSTANCE.kairosekiSpawnCount);
  private ModSliderOption kairosekiSpawnMaxHeight = new ModSliderOption("spawn_height", 1.0D, 128.0D, 1.0F, CommonConfig.INSTANCE.kairosekiSpawnMaxHeight);


  
  public void init(ConfigCategoryList list) {
    list.addCategory("Kairoseki");
    list.addOption((AbstractOption)this.kairosekiSpawnCount);
    list.addOption((AbstractOption)this.kairosekiSpawnMaxHeight);
  }


  
  public ITextComponent getTitle() {
    return (ITextComponent)new TranslationTextComponent("gui.mineminenomi.config.category.ores", new Object[0]);
  }
}


