package xyz.pixelatedw.mineminenomi.screens.extra;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.TextTable;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class NewButton
  extends Button {
  private IEntityStats entityData;
  private boolean isSelected;
  private TextTable.Alignment textAlignment = TextTable.Alignment.LEFT;
  private int lineThickness = 1;
  
  private boolean hasIcons = true;
  
  private int blackColor;
  
  public NewButton(int posX, int posY, int width, int height, String string, Button.IPressable onPress) {
    super(posX, posY, width, height, string, onPress);
    this.blackColor = WyHelper.hexToRGB("#000000").getRGB();
    this.entityData = EntityStatsCapability.get((LivingEntity)(Minecraft.getInstance()).player);
  }

  
  public void setTextAlignment(TextTable.Alignment alignment) {
    this.textAlignment = alignment;
  }

  
  public void setLineThickness(int thickness) {
    this.lineThickness = thickness;
  }

  
  public void disableIcons() {
    this.hasIcons = false;
  }


  
  public void render(int mouseX, int mouseY, float partialTicks) {
    RenderSystem.pushMatrix();
    if (this.visible) {
      int textPosX;
      this.isHovered = (mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height);
      int rgb = WyHelper.hexToRGB("#FFFFFF").getRGB();
      int lineColor = WyHelper.hexToRGB("#EAEAEA").getRGB();
      
      if (this.isHovered) {
        
        RenderSystem.translated(0.0D, 0.5D, 0.0D);
        int factionColor = FactionHelper.getFactionColor(this.entityData);
        rgb = lineColor = factionColor;
      } 
      
      FontRenderer font = (Minecraft.getInstance()).fontRenderer;

      
      if (this.textAlignment == TextTable.Alignment.CENTER) {
        textPosX = this.x - font.getStringWidth(getMessage()) / 2 + this.width / 2;
      } else if (this.textAlignment == TextTable.Alignment.RIGHT) {
        textPosX = this.x;
      } else {
        textPosX = this.x;
      } 
      fillGradient(this.x - 4, this.y + this.height - this.lineThickness + 2, this.width + this.x + 1, this.y + this.height, this.blackColor, this.blackColor);
      fillGradient(this.x - 5, this.y + this.height - this.lineThickness, this.width + this.x, this.y + this.height, lineColor, lineColor);
      
      int textOffset = 0;
      
      if (this.hasIcons) {
        
        ResourceLocation factionIcon = FactionHelper.getFactionIcon(this.entityData);
        if (factionIcon != null) {
          
          WyHelper.drawIcon(factionIcon, this.x - 12, this.y - 4, 32, 32, this.blackColor);
          WyHelper.drawIcon(factionIcon, this.x - 13, this.y - 5, 32, 32, lineColor);
          textOffset = 13;
        } 
      } 
      
      WyHelper.drawStringWithBorder(font, getMessage(), textPosX + textOffset, this.y + this.height / 2 - 4, rgb);
    } 
    RenderSystem.popMatrix();
  }
}


