package xyz.pixelatedw.mineminenomi.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import java.io.File;
import net.minecraft.client.GameSettings;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.SettingsScreen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.ForgeI18n;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.screens.config.ConfigCategoryList;
import xyz.pixelatedw.mineminenomi.screens.config.ConfigPage;
import xyz.pixelatedw.mineminenomi.screens.config.categories.BountyConfigPage;
import xyz.pixelatedw.mineminenomi.screens.config.categories.ChallengesConfigPage;
import xyz.pixelatedw.mineminenomi.screens.config.categories.CrewsConfigPage;
import xyz.pixelatedw.mineminenomi.screens.config.categories.DevilFruitsConfigPage;
import xyz.pixelatedw.mineminenomi.screens.config.categories.GeneralConfigPage;
import xyz.pixelatedw.mineminenomi.screens.config.categories.OresConfigPage;
import xyz.pixelatedw.mineminenomi.screens.config.categories.QuestsConfigPage;
import xyz.pixelatedw.mineminenomi.screens.config.categories.StructuresConfigPage;
import xyz.pixelatedw.mineminenomi.screens.config.categories.SystemConfigPage;
import xyz.pixelatedw.mineminenomi.screens.config.categories.WorldEventsConfigPage;

@OnlyIn(Dist.CLIENT)
public class ConfigScreen
  extends SettingsScreen
{
  private ConfigPage generalCategory = (ConfigPage)new GeneralConfigPage();
  private ConfigPage devilFruitsCategory = (ConfigPage)new DevilFruitsConfigPage();
  private ConfigPage structuresCategory = (ConfigPage)new StructuresConfigPage();
  private ConfigPage worldEventsCategory = (ConfigPage)new WorldEventsConfigPage();
  private ConfigPage crewsCategory = (ConfigPage)new CrewsConfigPage();
  private ConfigPage oresCategory = (ConfigPage)new OresConfigPage();
  private ConfigPage bountyCategory = (ConfigPage)new BountyConfigPage();
  private ConfigPage questsCategory = (ConfigPage)new QuestsConfigPage();
  private ConfigPage challengesCategory = (ConfigPage)new ChallengesConfigPage();
  private ConfigPage systemCategory = (ConfigPage)new SystemConfigPage();
  
  private ConfigPage[] categories = new ConfigPage[] { this.generalCategory, this.devilFruitsCategory, this.structuresCategory, this.worldEventsCategory, this.crewsCategory, this.oresCategory, this.bountyCategory, this.questsCategory, this.challengesCategory, this.systemCategory };
  
  private int categoryOffset = 0;
  private ConfigPage selectedCategory = this.generalCategory;
  
  private ConfigCategoryList list;

  
  public ConfigScreen(Screen parent, GameSettings settings) {
    super(parent, settings, (ITextComponent)new TranslationTextComponent("gui.mineminenomi.config.title", new Object[0]));
  }


  
  protected void init() {
    this.list = new ConfigCategoryList(this);
    this.buttons.clear();
    this.children.clear();
    
    int posX = this.width / 2 - 265;
    
    Button prevButton = new Button(posX + 60, 25, 20, 20, "<", x -> {
          if (this.categoryOffset > 0) {
            this.categoryOffset--;

            
            init();
          } 
        });
    
    int j = 0;
    for (int i = this.categoryOffset; i < this.categories.length; i++) {
      
      if (j >= 4) {
        break;
      }
      ConfigPage category = this.categories[i];
      
      if (category != null) {

        
        posX += 90;
        Button categoryButton = new Button(posX, 25, 80, 20, category.getTitle().getFormattedText() + "...", x -> {
              this.selectedCategory = category;
              
              init();
            });
        if (this.selectedCategory == category) {
          categoryButton.active = false;
        }
        
        j++;
      } 
    } 
    Button nextButton = new Button(posX + 90, 25, 20, 20, ">", x -> {
          if (this.categoryOffset < this.categories.length - 4) {
            this.categoryOffset++;

            
            init();
          } 
        });
    
    if (this.categoryOffset <= 0)
      prevButton.active = false; 
    if (this.categoryOffset >= this.categories.length - 4) {
      nextButton.active = false;
    }
    this.selectedCategory.init(this.list);



    
    addButton((Widget)new Button(this.width / 2 - 154, this.height - 27, 150, 20, I18n.format("gui.mineminenomi.config.open_config", new Object[0]), x -> Util.getOSType().openFile(new File(CommonConfig.CONFIG_PATH.toString()))));



    
    addButton((Widget)new Button(this.width / 2 + 4, this.height - 27, 150, 20, ForgeI18n.parseMessage("gui.done", new Object[0]), x -> {
            onClose();
            this.minecraft.displayGuiScreen(this.parentScreen);
          }));
  }


  
  public void render(int mouseX, int mouseY, float partialTicks) {
    renderBackground();


    
    drawCenteredString(this.font, this.title.getFormattedText(), this.width / 2, 8, 16777215);
    
    RenderSystem.pushMatrix();
    RenderSystem.translated((this.width / 2), (this.height / 2 - 20), 0.0D);
    RenderSystem.scaled(2.0D, 2.0D, 2.0D);
    drawCenteredString(this.font, "Under Maintenance", 0, 0, 16777215);
    RenderSystem.popMatrix();
    
    super.render(mouseX, mouseY, partialTicks);
  }


  
  public void onClose() {
    CommonConfig.save();
  }
}


