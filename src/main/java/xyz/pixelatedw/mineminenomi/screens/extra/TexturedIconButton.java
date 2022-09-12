package xyz.pixelatedw.mineminenomi.screens.extra;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import java.util.Arrays;
import java.util.List;



@OnlyIn(Dist.CLIENT)
public class TexturedIconButton
  extends Button
{
  private ResourceLocation texture;
  private int textureWidth;
  private int textureHeight;
  private int texturePosX;
  private int texturePosY;
  private int textPosX;
  private int textPosY;
  private double textScale = 1.0D;
  private ResourceLocation iconTexture;
  private double iconScale = 1.0D;
  
  private int iconPosX;
  private int iconPosY;
  private boolean isPressed;
  
  public TexturedIconButton(ResourceLocation loc, int posX, int posY, int width, int height, String text, Button.IPressable onPress) {
    super(posX, posY, width, height, text, onPress);
    
    this.texture = loc;
    this.texturePosX = posX;
    this.texturePosY = posY;
    this.textureWidth = width;
    this.textureHeight = height;
  }

  
  public TexturedIconButton setTextureInfo(int texturePosX, int texturePosY, int textureWidth, int textureHeight) {
    this.texturePosX = texturePosX;
    this.texturePosY = texturePosY;
    this.textureWidth = textureWidth;
    this.textureHeight = textureHeight;
    return this;
  }

  
  public TexturedIconButton setTextInfo(int textPosX, int textPosY, double scale) {
    this.textPosX = textPosX;
    this.textPosY = textPosY - 7;
    this.textScale = scale;
    return this;
  }

  
  public TexturedIconButton setIconInfo(ResourceLocation loc, int iconPosX, int iconPosY, double scale) {
    this.iconTexture = loc;
    this.iconPosX = iconPosX;
    this.iconPosY = iconPosY;
    this.iconScale = scale;
    return this;
  }


  
  public void render(int mouseX, int mouseY, float partialTicks) {
    if (!this.visible) {
      return;
    }
    this.isHovered = (this.active && mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height);
    
    RenderSystem.pushMatrix();
    if (this.isHovered || this.isPressed) {
      
      RenderSystem.translated(0.0D, 0.5D, 0.0D);
      RenderSystem.color3f(0.7F, 0.7F, 0.7F);
    } 
    
    if (!this.active) {
      RenderSystem.color3f(0.5F, 0.5F, 0.5F);
    }
    WyHelper.drawIcon(this.texture, this.texturePosX, this.texturePosY, this.textureWidth, this.textureHeight);

    
    if (this.iconTexture != null) {
      
      RenderSystem.pushMatrix();
      
      RenderSystem.enableBlend();
      RenderSystem.translated(this.iconPosX, this.iconPosY, 2.0D);
      GL11.glTranslated(16.0D, 16.0D, 0.0D);
      GL11.glScaled(this.iconScale, this.iconScale, this.iconScale);
      GL11.glTranslated(-16.0D, -16.0D, 1.0D);
      
      WyHelper.drawIcon(this.iconTexture, 0, 0, 16, 16);
      
      RenderSystem.popMatrix();
    } 

    
    RenderSystem.pushMatrix();
    
    FontRenderer font = (Minecraft.getInstance()).fontRenderer;
    List<String> strings = Arrays.asList(new String[] { getMessage() });
    
    int splits = (getMessage().split(" ")).length;
    if (splits > 1) {
      strings = WyHelper.splitString(font, getMessage(), this.textPosX - font.getStringWidth(getMessage()) / 2 + 26, this.width / splits + 10);
    }
    RenderSystem.translated(this.textPosX, (this.textPosY - ((strings.size() > 1) ? (strings.size() * 3) : 0)), 2.0D);
    RenderSystem.translated(128.0D, 128.0D, 0.0D);
    RenderSystem.scaled(this.textScale, this.textScale, this.textScale);
    RenderSystem.translated(-128.0D, -128.0D, 1.0D);
    for (int b = 0; b < strings.size(); b++)
    {
      WyHelper.drawStringWithBorder(font, strings.get(b), 0, 7 + b * 9, WyHelper.hexToRGB("#FFFFFF").getRGB());
    }
    
    RenderSystem.popMatrix();
    
    RenderSystem.color3f(1.0F, 1.0F, 1.0F);



    
    RenderSystem.popMatrix();
  }

  
  public void setIsPressed(boolean flag) {
    this.isPressed = flag;
  }
}


