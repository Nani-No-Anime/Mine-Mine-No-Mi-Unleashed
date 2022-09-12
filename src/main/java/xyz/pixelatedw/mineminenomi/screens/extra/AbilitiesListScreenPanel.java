package xyz.pixelatedw.mineminenomi.screens.extra;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.gui.ScrollPanel;
import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
import xyz.pixelatedw.mineminenomi.config.ClientConfig;
import xyz.pixelatedw.mineminenomi.packets.client.ability.CEquipAbilityPacket;
import xyz.pixelatedw.mineminenomi.packets.client.ability.CTogglePassiveAbilityPacket;
import xyz.pixelatedw.mineminenomi.screens.SelectHotbarAbilitiesScreen;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;




public class AbilitiesListScreenPanel
  extends ScrollPanel
{
  private SelectHotbarAbilitiesScreen parent;
  private IAbilityData props;
  private List<Entry> allAbilities = new ArrayList<>();
  private List<Entry> activeAbilities = new ArrayList<>();
  private List<Entry> passiveAbilities = new ArrayList<>();
  
  private static final int ENTRY_HEIGHT = 20;
  
  public AbilitiesListScreenPanel(SelectHotbarAbilitiesScreen parent, IAbilityData abilityProps, Ability[] abilities) {
    super(parent.getMinecraft(), 215, 130, parent.height / 2 - 98, parent.width / 2 - 109);
    
    this.parent = parent;
    this.props = abilityProps;
    
    for (int i = 0; i <= abilities.length - 1; i++) {
      
      boolean hideInGUI = abilities[i].isHideInGUI();
      boolean isPassive = abilities[i] instanceof PassiveAbility;
      boolean isTempo = abilities[i] instanceof xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
      
      if (abilities[i] != null)
      {
        if (!isPassive && !isTempo) {
          this.activeAbilities.add(new Entry(abilities[i]));
        } else if ((isPassive || isTempo) && !hideInGUI) {
          this.passiveAbilities.add(new Entry(abilities[i]));
        } 
      }
    } 
    this.allAbilities.addAll(this.activeAbilities);
    
    if (this.passiveAbilities.size() > 0) {
      this.passiveAbilities.add(0, (Entry)null);
    }
    this.allAbilities.addAll(this.passiveAbilities);
  }


  
  public boolean mouseReleased(double p_mouseReleased_1_, double p_mouseReleased_3_, int p_mouseReleased_5_) {
    return true;
  }


  
  protected int getContentHeight() {
    int size = ((List)this.activeAbilities.stream().collect(Collectors.toList())).size();
    if (this.passiveAbilities.size() > 0)
      size += this.passiveAbilities.size(); 
    return (int)(size * 25.0D + 2.0D);
  }


  
  protected int getScrollAmount() {
    return 15;
  }


  
  protected void drawPanel(int entryRight, int relativeY, Tessellator tess, int mouseX, int mouseY) {
    float y = relativeY;
    float x = (this.parent.width / 2 - 109 + 40);

    
    for (Entry entry : this.activeAbilities) {
      
      if (entry == null) {
        continue;
      }
      boolean flag = false;
      
      if (this.props.hasEquippedAbility(entry.ability)) {
        flag = true;
      }
      String abilityName = WyHelper.isNullOrEmpty(entry.ability.getDisplayName()) ? entry.ability.getName() : entry.ability.getDisplayName();
      (Minecraft.getInstance()).fontRenderer.drawStringWithShadow(I18n.format("ability.mineminenomi." + WyHelper.getResourceName(abilityName), new Object[0]), x, y + 4.0F, flag ? 16711680 : 16777215);
      
      String texture = WyHelper.getResourceName(entry.ability.hasCustomTexture() ? entry.ability.getCustomTexture() : entry.ability.getName());
      RendererHelper.drawAbilityIcon(texture, MathHelper.floor(x) - 30, MathHelper.floor(y), 16, 16);
      
      y = (float)(y + 25.0D);
    } 

    
    if (this.passiveAbilities.size() > 0) {
      
      (Minecraft.getInstance()).fontRenderer.drawStringWithShadow("Passives", x - 20.0F, y + 4.0F, 9145227);
      y = (float)(y + 25.0D);
      
      for (Entry entry : this.passiveAbilities) {
        
        if (entry == null) {
          continue;
        }
        Color textColor = WyHelper.hexToRGB("#aaff77");
        Color iconColor = WyHelper.hexToRGB("#FFFFFF");
        
        if (entry.ability instanceof PassiveAbility && ((PassiveAbility)entry.ability).isPaused()) {
          textColor = iconColor = WyHelper.hexToRGB("#FF0000");
        }
        if (entry.ability instanceof xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility) {
          textColor = WyHelper.hexToRGB("#8B8B8B");
        }
        String abilityName = WyHelper.isNullOrEmpty(entry.ability.getDisplayName()) ? entry.ability.getName() : entry.ability.getDisplayName();
        (Minecraft.getInstance()).fontRenderer.drawStringWithShadow(I18n.format("ability.mineminenomi." + WyHelper.getResourceName(abilityName), new Object[0]), x, y + 4.0F, textColor.getRGB());
        
        String texture = WyHelper.getResourceName(entry.ability.hasCustomTexture() ? entry.ability.getCustomTexture() : entry.ability.getName());
        RendererHelper.drawAbilityIcon(texture, MathHelper.floor(x) - 30, MathHelper.floor(y), 1, 16, 16, iconColor.getRed() / 255.0F, iconColor.getGreen() / 255.0F, iconColor.getBlue() / 255.0F);
        
        y = (float)(y + 25.0D);
      } 
    } 
  }

  
  private Entry findAbilityEntry(int mouseX, int mouseY) {
    double offset = ((mouseY - this.top) + this.scrollDistance);
    
    if (offset <= 0.0D) {
      return null;
    }
    int lineIdx = (int)(offset / 25.0D);
    if (lineIdx >= this.allAbilities.size()) {
      return null;
    }
    Entry entry = this.allAbilities.get(lineIdx);
    if (entry != null) {
      return entry;
    }
    return null;
  }


  
  public boolean mouseClicked(double mouseX, double mouseY, int button) {
    Entry entry = findAbilityEntry((int)mouseX, (int)mouseY);
    boolean flag = true;
    
    if (entry != null) {
      
      if (entry.ability instanceof xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility) {
        return false;
      }
      if (entry.ability instanceof PassiveAbility) {
        
        PassiveAbility passive = (PassiveAbility)entry.ability;
        passive.setPause(!passive.isPaused());
        WyNetwork.sendToServer(new CTogglePassiveAbilityPacket(entry.ability, passive.isPaused()));
      }
      else {
        
        Ability ability = this.props.getEquippedAbility(entry.ability);
        if (ability != null && !ability.isOnStandby()) {
          flag = false;
        }
        if (isMouseOver(mouseX, mouseY) && this.parent.slotSelected >= 0 && flag && entry.ability != null) {
          
          this.props.setEquippedAbility(this.parent.slotSelected, entry.ability);
          WyNetwork.sendToServer(new CEquipAbilityPacket(this.parent.slotSelected, entry.ability));
        } 
      } 
    } 

    
    return super.mouseClicked(mouseX, mouseY, button);
  }


  
  public boolean isMouseOver(double mouseX, double mouseY) {
    return (mouseX >= this.left && mouseY >= this.top && mouseX < (this.left + this.width - 5) && mouseY < (this.top + this.height));
  }


  
  public void render(int mouseX, int mouseY, float partialTicks) {
    super.render(mouseX, mouseY, partialTicks);
    
    if (ClientConfig.INSTANCE.isTooltipMessageEnabled() && isMouseOver(mouseX, mouseY)) {
      
      Entry hoveredEntity = findAbilityEntry(mouseX, mouseY);
      
      if (hoveredEntity == null || hoveredEntity.ability.getDescription() == null) {
        return;
      }
      StringBuilder sb = new StringBuilder();
      sb.append(hoveredEntity.ability.getDisplayName() + "\n\n");
      sb.append(hoveredEntity.ability.getDescription().getFormattedText());
      
      String tooltip = sb.toString();
      double posX = (this.parent.width - 220);
      double posY = (this.parent.height - 120);
      double width = posX + 220.0D;
      double height = posY + 120.0D;
      int fgColor = 16777215;
      int bgColor1 = WyHelper.hexToRGB("#222222FF").getRGB();
      int bgColor2 = WyHelper.hexToRGB("#686868EE").getRGB();
      fillGradient((int)posX, (int)posY, (int)width, (int)height, bgColor1, bgColor2);
      List<String> strings = WyHelper.splitString((this.parent.getMinecraft()).fontRenderer, tooltip, (int)posX, 210);
      for (int b = 0; b < strings.size(); b++)
      {
        WyHelper.drawStringWithBorder((this.parent.getMinecraft()).fontRenderer, strings.get(b), (int)posX + 5, 5 + (int)posY + 10 * b, fgColor);
      }
    } 
  }

  
  class Entry
  {
    private Ability ability;
    
    public Entry(Ability ability) {
      this.ability = ability;
    }
  }
}


