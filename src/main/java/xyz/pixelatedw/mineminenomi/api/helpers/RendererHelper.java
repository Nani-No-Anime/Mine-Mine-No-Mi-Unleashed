package xyz.pixelatedw.mineminenomi.api.helpers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.gui.GuiUtils;
import xyz.pixelatedw.mineminenomi.api.crew.JollyRoger;
import xyz.pixelatedw.mineminenomi.api.crew.JollyRogerElement;
import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import java.awt.*;




public class RendererHelper
{
  public static final ResourceLocation VANILLA_VIGNETTE_TEX_PATH = new ResourceLocation("textures/misc/vignette.png");

  
  public static void drawAbilityIcon(String iconName, int x, int y, int u, int v) {
    drawAbilityIcon(iconName, x, y, 1, u, v);
  }

  
  public static void drawAbilityIcon(String iconName, int x, int y, int z, int u, int v) {
    drawAbilityIcon(iconName, x, y, z, u, v, 1.0F, 1.0F, 1.0F);
  }

  
  public static void drawAbilityIcon(String iconName, int x, int y, int z, int u, int v, float red, float green, float blue) {
    RenderSystem.enableAlphaTest();
    RenderSystem.enableBlend();
    Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mineminenomi", "textures/abilities/" + WyHelper.getResourceName(iconName) + ".png"));
    BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR_TEX);
    bufferbuilder.pos(x, (y + v), z).color(red, green, blue, 1.0F).tex(0.0F, 1.0F).endVertex();
    bufferbuilder.pos((x + u), (y + v), z).color(red, green, blue, 1.0F).tex(1.0F, 1.0F).endVertex();
    bufferbuilder.pos((x + u), y, z).color(red, green, blue, 1.0F).tex(1.0F, 0.0F).endVertex();
    bufferbuilder.pos(x, y, z).color(red, green, blue, 1.0F).tex(0.0F, 0.0F).endVertex();
    Tessellator.getInstance().draw();
  }

  
  public static void drawDevilFruitIcon(String iconName, int x, int y, int u, int v) {
    RenderSystem.enableAlphaTest();
    RenderSystem.enableBlend();
    Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mineminenomi", "textures/items/" + WyHelper.getResourceName(iconName) + ".png"));
    BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
    bufferbuilder.pos(x, (y + v), 1.0D).tex(0.0F, 1.0F).endVertex();
    bufferbuilder.pos((x + u), (y + v), 1.0D).tex(1.0F, 1.0F).endVertex();
    bufferbuilder.pos((x + u), y, 1.0D).tex(1.0F, 0.0F).endVertex();
    bufferbuilder.pos(x, y, 1.0D).tex(0.0F, 0.0F).endVertex();
    Tessellator.getInstance().draw();
  }

  
  public static void drawQuad(MatrixStack matrixStack, IVertexBuilder buffer, float red, float green, float blue, float alpha, float x, float y, float u, float v) {
    MatrixStack.Entry entry = matrixStack.getLast();
    Matrix4f matrix4f = entry.getMatrix();
    Matrix3f matrix3f = entry.getNormal();
    
    drawVertex(matrix4f, matrix3f, buffer, red, green, blue, alpha, x, y + u, 0.0F, 1.0F);
    drawVertex(matrix4f, matrix3f, buffer, red, green, blue, alpha, x + u, y + v, 1.0F, 1.0F);
    drawVertex(matrix4f, matrix3f, buffer, red, green, blue, alpha, x + u, y, 1.0F, 0.0F);
    drawVertex(matrix4f, matrix3f, buffer, red, green, blue, alpha, x, y, 0.0F, 0.0F);
  }

  
  public static void drawVertex(Matrix4f matrixPos, Matrix3f matrixNormal, IVertexBuilder bufferIn, float red, float green, float blue, float alpha, float x, float y, float texU, float texV) {
    bufferIn.pos(matrixPos, x, y, 0.0F).color(red, green, blue, alpha).tex(texU, texV).overlay(OverlayTexture.NO_OVERLAY).lightmap(15728880).normal(matrixNormal, 0.0F, 1.0F, 0.0F).endVertex();
  }



  
  public static void drawPlayerJollyRoger(JollyRoger jollyRoger, MatrixStack matrixStack, IRenderTypeBuffer buffer) {
    for (JollyRogerElement element : jollyRoger.getBackgrounds()) {
      
      if (element != null && element.getTexture() != null) {
        
        float red = 1.0F, green = 1.0F, blue = 1.0F, alpha = 1.0F;
        if (element.canBeColored()) {
          
          Color color = WyHelper.hexToRGB(element.getColor());
          red = color.getRed() / 255.0F;
          green = color.getGreen() / 255.0F;
          blue = color.getBlue() / 255.0F;
        } 
        drawQuad(matrixStack, buffer.getBuffer(ModRenderTypes.getWantedPoster(element.getTexture())), red, green, blue, alpha, 0.0F, 0.0F, 1.0F, 1.0F);
      } 
    } 

    
    if (jollyRoger.getBase() != null && jollyRoger.getBase().getTexture() != null) {
      
      float red = 1.0F, green = 1.0F, blue = 1.0F, alpha = 1.0F;
      if (jollyRoger.getBase().canBeColored()) {
        
        Color color = WyHelper.hexToRGB(jollyRoger.getBase().getColor());
        red = color.getRed() / 255.0F;
        green = color.getGreen() / 255.0F;
        blue = color.getBlue() / 255.0F;
      } 
      drawQuad(matrixStack, buffer.getBuffer(ModRenderTypes.getWantedPoster(jollyRoger.getBase().getTexture())), red, green, blue, alpha, 0.0F, 0.0F, 1.0F, 1.0F);
    } 

    
    for (JollyRogerElement element : jollyRoger.getDetails()) {
      
      if (element != null && element.getTexture() != null) {
        
        float red = 1.0F, green = 1.0F, blue = 1.0F, alpha = 1.0F;
        if (element.canBeColored()) {
          
          Color color = WyHelper.hexToRGB(element.getColor());
          red = color.getRed() / 255.0F;
          green = color.getGreen() / 255.0F;
          blue = color.getBlue() / 255.0F;
        } 
        drawQuad(matrixStack, buffer.getBuffer(ModRenderTypes.getWantedPoster(element.getTexture())), red, green, blue, alpha, 0.0F, 0.0F, 1.0F, 1.0F);
      } 
    } 
  }



  
  public static void drawPlayerJollyRoger(JollyRoger jollyRoger) {
    for (JollyRogerElement element : jollyRoger.getBackgrounds()) {
      
      int i = 0;
      if (element != null && element.getTexture() != null) {
        
        if (element.canBeColored()) {
          
          Color color = WyHelper.hexToRGB(element.getColor());
          RenderSystem.color3f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F);
        }
        else {
          
          RenderSystem.color3f(1.0F, 1.0F, 1.0F);
        } 
        Minecraft.getInstance().getTextureManager().bindTexture(element.getTexture());
        GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, i);
      } 
      i--;
    } 

    
    if (jollyRoger.getBase() != null && jollyRoger.getBase().getTexture() != null) {
      
      if (jollyRoger.getBase().canBeColored()) {
        
        Color color = WyHelper.hexToRGB(jollyRoger.getBase().getColor());
        RenderSystem.color3f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F);
      }
      else {
        
        RenderSystem.color3f(1.0F, 1.0F, 1.0F);
      } 
      Minecraft.getInstance().getTextureManager().bindTexture(jollyRoger.getBase().getTexture());
      GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, 0.0F);
    } 

    
    for (JollyRogerElement element : jollyRoger.getDetails()) {
      
      int i = 0;
      if (element != null && element.getTexture() != null) {
        
        if (element.canBeColored()) {
          
          Color color = WyHelper.hexToRGB(element.getColor());
          RenderSystem.color3f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F);
        }
        else {
          
          RenderSystem.color3f(1.0F, 1.0F, 1.0F);
        } 
        Minecraft.getInstance().getTextureManager().bindTexture(element.getTexture());
        GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, i);
      } 
      i++;
    } 
    
    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
  }
  
  private static final float distance = (float)(Math.sqrt(3.0D) / 2.0D);
  
  public static void drawA(IVertexBuilder vertexBuilder, Matrix4f matrix4f, Color color) {
    vertexBuilder.pos(matrix4f, 0.0F, 0.0F, 0.0F).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
    vertexBuilder.pos(matrix4f, 0.0F, 0.0F, 0.0F).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
  }

  
  public static void drawB(IVertexBuilder vertexBuilder, Matrix4f matrix4f, float y, float xz, Color color) {
    vertexBuilder.pos(matrix4f, -distance * xz, y, -0.5F * xz).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
  }

  
  public static void drawC(IVertexBuilder vertexBuilder, Matrix4f matrix4f, float y, float xz, Color color) {
    vertexBuilder.pos(matrix4f, distance * xz, y, -0.5F * xz).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
  }

  
  public static void drawD(IVertexBuilder vertexBuilder, Matrix4f matrix4f, float y, float z, Color color) {
    vertexBuilder.pos(matrix4f, 0.0F, y, z).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
  }

  
  public static void renderVignette(Entity entity, float intensity, double width, double height) {
    RenderSystem.disableDepthTest();
    RenderSystem.depthMask(false);
    RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
    
    float red = intensity;
    float green = intensity;
    float blue = intensity;
    if (intensity > 0.8F) {
      
      red = 0.0F;
      green = 1.0F;
      blue = 1.0F;
    } 
    
    RenderSystem.color4f(red, green, blue, 1.0F);
    
    Minecraft.getInstance().getTextureManager().bindTexture(VANILLA_VIGNETTE_TEX_PATH);
    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder bufferbuilder = tessellator.getBuffer();
    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
    bufferbuilder.pos(0.0D, height, -90.0D).tex(0.0F, 1.0F).endVertex();
    bufferbuilder.pos(width, height, -90.0D).tex(1.0F, 1.0F).endVertex();
    bufferbuilder.pos(width, 0.0D, -90.0D).tex(1.0F, 0.0F).endVertex();
    bufferbuilder.pos(0.0D, 0.0D, -90.0D).tex(0.0F, 0.0F).endVertex();
    tessellator.draw();
    RenderSystem.depthMask(true);
    RenderSystem.enableDepthTest();
    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    RenderSystem.defaultBlendFunc();
  }
}


