package xyz.pixelatedw.mineminenomi.screens.config;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.list.AbstractList;
import net.minecraft.client.gui.widget.list.AbstractOptionList;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.AbstractOption;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.config.ClientConfig;
import xyz.pixelatedw.mineminenomi.screens.ConfigScreen;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


@OnlyIn(Dist.CLIENT)
public class ConfigCategoryList
  extends AbstractOptionList<ConfigCategoryList.Entry>
{
  private ConfigScreen parent;
  
  public ConfigCategoryList(ConfigScreen parent) {
    super(parent.getMinecraft(), parent.width + 45, parent.height, 50, parent.height - 40, 35);
    this.parent = parent;
  }


  
  public void render(int mouseX, int mouseY, float partialTicks) {
    super.render(mouseX, mouseY, partialTicks);
    
    for (Entry entry : children()) {
      
      if (!(entry instanceof Row)) {
        continue;
      }
      Row row = (Row)entry;
      
      int i = 0;
      if (ClientConfig.INSTANCE.isTooltipMessageEnabled())
      {
        for (Widget widget : row.widgets) {
          
          boolean isHovered = (mouseY > 50 && mouseY < this.height - 40 && mouseX >= widget.x && mouseY >= widget.y && mouseX < widget.x + widget.getWidth() && mouseY < widget.y + widget.getHeight());
          if (isHovered) {
            
            String tooltip = (new TranslationTextComponent((String)row.getTranslationKeys().get(i) + ".tooltip", new Object[0])).getFormattedText();
            double posX = (this.width - 325);
            double posY = (this.height - 190);
            double width = posX + 280.0D;
            double height = posY + 150.0D;
            int fgColor = 16777215;
            int bgColor1 = WyHelper.hexToRGB("#222222FF").getRGB();
            int bgColor2 = WyHelper.hexToRGB("#686868EE").getRGB();
            fillGradient((int)posX, (int)posY, (int)width, (int)height, bgColor1, bgColor2);
            List<String> strings = WyHelper.splitString(this.minecraft.fontRenderer, tooltip, (int)posX, 270);
            for (int b = 0; b < strings.size(); b++)
            {
              WyHelper.drawStringWithBorder((this.parent.getMinecraft()).fontRenderer, strings.get(b), (int)posX + 5, 5 + (int)posY + 10 * b, fgColor);
            }
          } 
          i++;
        } 
      }
    } 
  }

  
  public int addCategory(String name) {
    return addEntry(new Category(name));
  }

  
  public int addOption(AbstractOption option) {
    return addEntry(new Row(this.minecraft.gameSettings, this.width, option));
  }

  
  public int addOption(AbstractOption option1, @Nullable AbstractOption option2) {
    return addEntry(new Row(this.minecraft.gameSettings, this.width, option1, option2));
  }

  
  public void addOptions(AbstractOption[] options) {
    for (int i = 0; i < options.length; i += 2)
    {
      addOption(options[i], (i < options.length - 1) ? options[i + 1] : null);
    }
  }


  
  protected int getScrollbarPosition() {
    return super.getScrollbarPosition() + 35;
  }


  
  public int getRowWidth() {
    return super.getRowWidth() + 140;
  }
  
  @OnlyIn(Dist.CLIENT)
  public static abstract class Entry
    extends AbstractOptionList.Entry<Entry> {}
  
  @OnlyIn(Dist.CLIENT)
  public class Category
    extends Entry {
    private final String labelText;
    
    public Category(String name) {
      this.labelText = I18n.format(name, new Object[0]);
    }


    
    public void render(int p_render_1_, int p_render_2_, int p_render_3_, int p_render_4_, int p_render_5_, int mouseX, int mouseY, boolean p_render_8_, float partialTicks) {
      RenderSystem.pushMatrix();
      RenderSystem.translated((ConfigCategoryList.this.width / 2 + 30), (p_render_2_ + p_render_5_ + 35), 0.0D);
      RenderSystem.translated(128.0D, 128.0D, 0.0D);
      RenderSystem.scaled(1.4D, 1.4D, 1.4D);
      RenderSystem.translated(-128.0D, -128.0D, -5.0D);
      WyHelper.drawStringWithBorder(ConfigCategoryList.this.minecraft.fontRenderer, this.labelText, -ConfigCategoryList.this.minecraft.fontRenderer.getStringWidth(this.labelText) / 2, 0, 16777215);
      RenderSystem.popMatrix();
    }


    
    public boolean changeFocus(boolean p_changeFocus_1_) {
      return false;
    }


    
    public List<? extends IGuiEventListener> children() {
      return Collections.emptyList();
    }
  }
  
  @OnlyIn(Dist.CLIENT)
  public static class Row
    extends Entry {
    private final List<Widget> widgets;
    private final List<String> translationKeys = new ArrayList<>();

    
    private Row(GameSettings settings, int guiWidth, AbstractOption option) {
      this.widgets = (List<Widget>)ImmutableList.of(option.createWidget(settings, guiWidth / 2 - 175, 0, 310));
      if (option instanceof IExtendedOption) {
        this.translationKeys.add(((IExtendedOption)option).getTranslateKey());
      }
    }
    
    private Row(GameSettings settings, int guiWidth, AbstractOption leftOption, @Nullable AbstractOption rightOption) {
      Widget widget = leftOption.createWidget(settings, guiWidth / 2 - 175, 0, 150);
      if (leftOption instanceof IExtendedOption)
        this.translationKeys.add(((IExtendedOption)leftOption).getTranslateKey()); 
      if (rightOption == null) {
        this.widgets = (List<Widget>)ImmutableList.of(widget);
      } else {
        
        this.widgets = (List<Widget>)ImmutableList.of(widget, rightOption.createWidget(settings, guiWidth / 2 - 20, 0, 150));
        if (rightOption instanceof IExtendedOption) {
          this.translationKeys.add(((IExtendedOption)rightOption).getTranslateKey());
        }
      } 
    }

    
    public void render(int p_render_1_, int p_render_2_, int p_render_3_, int p_render_4_, int p_render_5_, int mouseX, int mouseY, boolean p_render_8_, float partialTicks) {
      this.widgets.forEach(widget -> {
            widget.y = p_render_2_;
            widget.render(mouseX, mouseY, partialTicks);
          });
    }



    
    public List<? extends IGuiEventListener> children() {
      return (List)this.widgets;
    }

    
    public List<String> getTranslationKeys() {
      return this.translationKeys;
    }
  }
}


