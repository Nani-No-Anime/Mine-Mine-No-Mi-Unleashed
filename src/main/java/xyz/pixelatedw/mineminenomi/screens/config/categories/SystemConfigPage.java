package xyz.pixelatedw.mineminenomi.screens.config.categories;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.screens.config.ConfigCategoryList;
import xyz.pixelatedw.mineminenomi.screens.config.ConfigPage;
















public class SystemConfigPage
  extends ConfigPage
{
  public void init(ConfigCategoryList list) {}
  
  public ITextComponent getTitle() {
    return (ITextComponent)new TranslationTextComponent("gui.mineminenomi.config.category.system", new Object[0]);
  }
}


