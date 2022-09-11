package xyz.pixelatedw.mineminenomi.screens.extra;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.gui.GuiUtils;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;



public class ChallengeButton
  extends Button
{
  private Minecraft minecraft;
  private boolean isSelected;
  private int unfinished;
  private static final double HOVER_SCALE = 0.4D;
  
  public ChallengeButton(int posX, int posY, int width, int height, String string, int unfinished, Button.IPressable onPress) {
    super(posX, posY, width, height, string, onPress);
    this.minecraft = Minecraft.getInstance();
    this.unfinished = unfinished;
  }


  
  public void render(int mouseX, int mouseY, float partialTicks) {
    RenderSystem.pushMatrix();
    
    if (this.visible) {
      
      this.isHovered = (mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height);
      
      if (this.isHovered) {
        RenderSystem.translated(0.0D, -2.0D, 0.0D);
      }
      RenderSystem.pushMatrix();
      
      RenderSystem.translated((this.x - 128), (this.y - 128), 0.0D);
      RenderSystem.translated(128.0D, 128.0D, 0.0D);
      RenderSystem.scaled(0.4D, 0.4D, 0.4D);
      
      Minecraft.getInstance().getTextureManager().bindTexture(ModResources.CHALLENGE_BLANK);
      if (this.unfinished == 0) {
        
        RenderSystem.pushMatrix();
        
        RenderSystem.color4f(1.0F, 1.0F, 0.0F, 1.0F);
        RenderSystem.translated(-12.0D, -12.0D, 0.0D);
        RenderSystem.scaled(1.1D, 1.1D, 1.1D);
        GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, 0.0F);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        
        RenderSystem.popMatrix();
      } 
      GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, 0.0F);
      
      RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
      Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mineminenomi", "textures/gui/challenges/" + WyHelper.getResourceName(getMessage()) + ".png"));
      GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, 2.0F);
      
      RenderSystem.translated(-128.0D, -128.0D, 0.0D);
      
      RenderSystem.popMatrix();
      
      RenderSystem.pushMatrix();
      
      int color = -1;
      if (this.unfinished == 0)
        color = WyHelper.hexToRGB("#FFBB11").getRGB(); 
      RenderSystem.translated(0.0D, 0.0D, 1.0D);
      WyHelper.drawStringWithBorder(this.minecraft.fontRenderer, TextFormatting.BOLD + "" + getMessage(), this.x + 46 - this.minecraft.fontRenderer.getStringWidth(getMessage()) / 2, this.y + 83, color);
      RenderSystem.disableBlend();
      
      RenderSystem.popMatrix();
    } 
    
    RenderSystem.popMatrix();
  }

  
  public void select() {
    this.isSelected = !this.isSelected;
  }
}


