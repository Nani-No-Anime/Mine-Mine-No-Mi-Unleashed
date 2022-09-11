package xyz.pixelatedw.mineminenomi.screens.extra;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.button.Button;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class NoTextureButton
  extends Button
{
  private boolean isSelected;
  private boolean isFake;
  private String onHoverTextColor = "#00FFBB";

  
  public NoTextureButton(int posX, int posY, int width, int height, String string, Button.IPressable onPress) {
    super(posX, posY, width, height, string, onPress);
  }

  
  public void setFake() {
    this.isFake = true;
  }

  
  public void setOnHoverTextColor(String color) {
    this.onHoverTextColor = color;
  }


  
  public void render(int mouseX, int mouseY, float partialTicks) {
    RenderSystem.pushMatrix();
    if (this.visible) {
      
      this.isHovered = (mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height);
      int rgb = WyHelper.hexToRGB("#FFFFFF").getRGB();
      int topGrad = WyHelper.hexToRGB("#B3B3B3").getRGB();
      int bottomGrad = WyHelper.hexToRGB("#939393").getRGB();
      
      if (this.isHovered) {
        
        RenderSystem.translated(0.0D, 0.5D, 0.0D);
        rgb = WyHelper.hexToRGB(this.onHoverTextColor).getRGB();
        topGrad = WyHelper.hexToRGB("#B3B3B3").getRGB();
        bottomGrad = WyHelper.hexToRGB("#505050").getRGB();
        
        if (this.isFake) {
          
          topGrad = WyHelper.hexToRGB("#3333BB00").getRGB();
          bottomGrad = WyHelper.hexToRGB("#000055FF").getRGB();
          fillGradient(this.x, this.y, this.width + this.x, this.height + this.y, topGrad, bottomGrad);
        } 
      } 
      
      if (this.isSelected) {
        
        topGrad = WyHelper.hexToRGB("#00CC00").getRGB();
        bottomGrad = WyHelper.hexToRGB("#005500").getRGB();
      } 
      
      int outlineSize = 1;
      
      if (!this.isFake) {
        
        fillGradient(this.x - outlineSize, this.y - outlineSize, this.width + this.x + outlineSize, this.height + this.y + outlineSize, WyHelper.hexToRGB("#000000").getRGB(), WyHelper.hexToRGB("#000000").getRGB());
        fillGradient(this.x, this.y, this.width + this.x, this.height + this.y, topGrad, bottomGrad);
        
        FontRenderer font = (Minecraft.getInstance()).fontRenderer;
        WyHelper.drawStringWithBorder(font, getMessage(), this.x - font.getStringWidth(getMessage()) / 2 + this.width / 2, this.y + this.height / 2 - 4, rgb);
      } 
      
      RenderSystem.color3f(1.0F, 1.0F, 1.0F);
    } 
    RenderSystem.popMatrix();
  }

  
  public void select() {
    this.isSelected = !this.isSelected;
  }
}


