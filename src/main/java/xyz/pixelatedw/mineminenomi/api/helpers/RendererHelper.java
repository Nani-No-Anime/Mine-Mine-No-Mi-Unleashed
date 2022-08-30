/*     */ package xyz.pixelatedw.mineminenomi.api.helpers;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.platform.GlStateManager;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import java.awt.Color;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.BufferBuilder;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.Matrix3f;
/*     */ import net.minecraft.client.renderer.Matrix4f;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.client.gui.GuiUtils;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.JollyRoger;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.JollyRogerElement;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RendererHelper
/*     */ {
/*  29 */   public static final ResourceLocation VANILLA_VIGNETTE_TEX_PATH = new ResourceLocation("textures/misc/vignette.png");
/*     */ 
/*     */   
/*     */   public static void drawAbilityIcon(String iconName, int x, int y, int u, int v) {
/*  33 */     drawAbilityIcon(iconName, x, y, 1, u, v);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawAbilityIcon(String iconName, int x, int y, int z, int u, int v) {
/*  38 */     drawAbilityIcon(iconName, x, y, z, u, v, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawAbilityIcon(String iconName, int x, int y, int z, int u, int v, float red, float green, float blue) {
/*  43 */     RenderSystem.enableAlphaTest();
/*  44 */     RenderSystem.enableBlend();
/*  45 */     Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mineminenomi", "textures/abilities/" + WyHelper.getResourceName(iconName) + ".png"));
/*  46 */     BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
/*  47 */     bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR_TEX);
/*  48 */     bufferbuilder.pos(x, (y + v), z).color(red, green, blue, 1.0F).tex(0.0F, 1.0F).endVertex();
/*  49 */     bufferbuilder.pos((x + u), (y + v), z).color(red, green, blue, 1.0F).tex(1.0F, 1.0F).endVertex();
/*  50 */     bufferbuilder.pos((x + u), y, z).color(red, green, blue, 1.0F).tex(1.0F, 0.0F).endVertex();
/*  51 */     bufferbuilder.pos(x, y, z).color(red, green, blue, 1.0F).tex(0.0F, 0.0F).endVertex();
/*  52 */     Tessellator.getInstance().draw();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawDevilFruitIcon(String iconName, int x, int y, int u, int v) {
/*  57 */     RenderSystem.enableAlphaTest();
/*  58 */     RenderSystem.enableBlend();
/*  59 */     Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mineminenomi", "textures/items/" + WyHelper.getResourceName(iconName) + ".png"));
/*  60 */     BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
/*  61 */     bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
/*  62 */     bufferbuilder.pos(x, (y + v), 1.0D).tex(0.0F, 1.0F).endVertex();
/*  63 */     bufferbuilder.pos((x + u), (y + v), 1.0D).tex(1.0F, 1.0F).endVertex();
/*  64 */     bufferbuilder.pos((x + u), y, 1.0D).tex(1.0F, 0.0F).endVertex();
/*  65 */     bufferbuilder.pos(x, y, 1.0D).tex(0.0F, 0.0F).endVertex();
/*  66 */     Tessellator.getInstance().draw();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawQuad(MatrixStack matrixStack, IVertexBuilder buffer, float red, float green, float blue, float alpha, float x, float y, float u, float v) {
/*  71 */     MatrixStack.Entry entry = matrixStack.getLast();
/*  72 */     Matrix4f matrix4f = entry.getMatrix();
/*  73 */     Matrix3f matrix3f = entry.getNormal();
/*     */     
/*  75 */     drawVertex(matrix4f, matrix3f, buffer, red, green, blue, alpha, x, y + u, 0.0F, 1.0F);
/*  76 */     drawVertex(matrix4f, matrix3f, buffer, red, green, blue, alpha, x + u, y + v, 1.0F, 1.0F);
/*  77 */     drawVertex(matrix4f, matrix3f, buffer, red, green, blue, alpha, x + u, y, 1.0F, 0.0F);
/*  78 */     drawVertex(matrix4f, matrix3f, buffer, red, green, blue, alpha, x, y, 0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawVertex(Matrix4f matrixPos, Matrix3f matrixNormal, IVertexBuilder bufferIn, float red, float green, float blue, float alpha, float x, float y, float texU, float texV) {
/*  83 */     bufferIn.pos(matrixPos, x, y, 0.0F).color(red, green, blue, alpha).tex(texU, texV).overlay(OverlayTexture.NO_OVERLAY).lightmap(15728880).normal(matrixNormal, 0.0F, 1.0F, 0.0F).endVertex();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawPlayerJollyRoger(JollyRoger jollyRoger, MatrixStack matrixStack, IRenderTypeBuffer buffer) {
/*  90 */     for (JollyRogerElement element : jollyRoger.getBackgrounds()) {
/*     */       
/*  92 */       if (element != null && element.getTexture() != null) {
/*     */         
/*  94 */         float red = 1.0F, green = 1.0F, blue = 1.0F, alpha = 1.0F;
/*  95 */         if (element.canBeColored()) {
/*     */           
/*  97 */           Color color = WyHelper.hexToRGB(element.getColor());
/*  98 */           red = color.getRed() / 255.0F;
/*  99 */           green = color.getGreen() / 255.0F;
/* 100 */           blue = color.getBlue() / 255.0F;
/*     */         } 
/* 102 */         drawQuad(matrixStack, buffer.getBuffer(ModRenderTypes.getWantedPoster(element.getTexture())), red, green, blue, alpha, 0.0F, 0.0F, 1.0F, 1.0F);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 107 */     if (jollyRoger.getBase() != null && jollyRoger.getBase().getTexture() != null) {
/*     */       
/* 109 */       float red = 1.0F, green = 1.0F, blue = 1.0F, alpha = 1.0F;
/* 110 */       if (jollyRoger.getBase().canBeColored()) {
/*     */         
/* 112 */         Color color = WyHelper.hexToRGB(jollyRoger.getBase().getColor());
/* 113 */         red = color.getRed() / 255.0F;
/* 114 */         green = color.getGreen() / 255.0F;
/* 115 */         blue = color.getBlue() / 255.0F;
/*     */       } 
/* 117 */       drawQuad(matrixStack, buffer.getBuffer(ModRenderTypes.getWantedPoster(jollyRoger.getBase().getTexture())), red, green, blue, alpha, 0.0F, 0.0F, 1.0F, 1.0F);
/*     */     } 
/*     */ 
/*     */     
/* 121 */     for (JollyRogerElement element : jollyRoger.getDetails()) {
/*     */       
/* 123 */       if (element != null && element.getTexture() != null) {
/*     */         
/* 125 */         float red = 1.0F, green = 1.0F, blue = 1.0F, alpha = 1.0F;
/* 126 */         if (element.canBeColored()) {
/*     */           
/* 128 */           Color color = WyHelper.hexToRGB(element.getColor());
/* 129 */           red = color.getRed() / 255.0F;
/* 130 */           green = color.getGreen() / 255.0F;
/* 131 */           blue = color.getBlue() / 255.0F;
/*     */         } 
/* 133 */         drawQuad(matrixStack, buffer.getBuffer(ModRenderTypes.getWantedPoster(element.getTexture())), red, green, blue, alpha, 0.0F, 0.0F, 1.0F, 1.0F);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawPlayerJollyRoger(JollyRoger jollyRoger) {
/* 142 */     for (JollyRogerElement element : jollyRoger.getBackgrounds()) {
/*     */       
/* 144 */       int i = 0;
/* 145 */       if (element != null && element.getTexture() != null) {
/*     */         
/* 147 */         if (element.canBeColored()) {
/*     */           
/* 149 */           Color color = WyHelper.hexToRGB(element.getColor());
/* 150 */           RenderSystem.color3f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F);
/*     */         }
/*     */         else {
/*     */           
/* 154 */           RenderSystem.color3f(1.0F, 1.0F, 1.0F);
/*     */         } 
/* 156 */         Minecraft.getInstance().getTextureManager().bindTexture(element.getTexture());
/* 157 */         GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, i);
/*     */       } 
/* 159 */       i--;
/*     */     } 
/*     */ 
/*     */     
/* 163 */     if (jollyRoger.getBase() != null && jollyRoger.getBase().getTexture() != null) {
/*     */       
/* 165 */       if (jollyRoger.getBase().canBeColored()) {
/*     */         
/* 167 */         Color color = WyHelper.hexToRGB(jollyRoger.getBase().getColor());
/* 168 */         RenderSystem.color3f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F);
/*     */       }
/*     */       else {
/*     */         
/* 172 */         RenderSystem.color3f(1.0F, 1.0F, 1.0F);
/*     */       } 
/* 174 */       Minecraft.getInstance().getTextureManager().bindTexture(jollyRoger.getBase().getTexture());
/* 175 */       GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, 0.0F);
/*     */     } 
/*     */ 
/*     */     
/* 179 */     for (JollyRogerElement element : jollyRoger.getDetails()) {
/*     */       
/* 181 */       int i = 0;
/* 182 */       if (element != null && element.getTexture() != null) {
/*     */         
/* 184 */         if (element.canBeColored()) {
/*     */           
/* 186 */           Color color = WyHelper.hexToRGB(element.getColor());
/* 187 */           RenderSystem.color3f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F);
/*     */         }
/*     */         else {
/*     */           
/* 191 */           RenderSystem.color3f(1.0F, 1.0F, 1.0F);
/*     */         } 
/* 193 */         Minecraft.getInstance().getTextureManager().bindTexture(element.getTexture());
/* 194 */         GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, i);
/*     */       } 
/* 196 */       i++;
/*     */     } 
/*     */     
/* 199 */     RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/* 202 */   private static final float distance = (float)(Math.sqrt(3.0D) / 2.0D);
/*     */   
/*     */   public static void drawA(IVertexBuilder vertexBuilder, Matrix4f matrix4f, Color color) {
/* 205 */     vertexBuilder.pos(matrix4f, 0.0F, 0.0F, 0.0F).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
/* 206 */     vertexBuilder.pos(matrix4f, 0.0F, 0.0F, 0.0F).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawB(IVertexBuilder vertexBuilder, Matrix4f matrix4f, float y, float xz, Color color) {
/* 211 */     vertexBuilder.pos(matrix4f, -distance * xz, y, -0.5F * xz).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawC(IVertexBuilder vertexBuilder, Matrix4f matrix4f, float y, float xz, Color color) {
/* 216 */     vertexBuilder.pos(matrix4f, distance * xz, y, -0.5F * xz).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawD(IVertexBuilder vertexBuilder, Matrix4f matrix4f, float y, float z, Color color) {
/* 221 */     vertexBuilder.pos(matrix4f, 0.0F, y, z).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderVignette(Entity entity, float intensity, double width, double height) {
/* 226 */     RenderSystem.disableDepthTest();
/* 227 */     RenderSystem.depthMask(false);
/* 228 */     RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
/*     */     
/* 230 */     float red = intensity;
/* 231 */     float green = intensity;
/* 232 */     float blue = intensity;
/* 233 */     if (intensity > 0.8F) {
/*     */       
/* 235 */       red = 0.0F;
/* 236 */       green = 1.0F;
/* 237 */       blue = 1.0F;
/*     */     } 
/*     */     
/* 240 */     RenderSystem.color4f(red, green, blue, 1.0F);
/*     */     
/* 242 */     Minecraft.getInstance().getTextureManager().bindTexture(VANILLA_VIGNETTE_TEX_PATH);
/* 243 */     Tessellator tessellator = Tessellator.getInstance();
/* 244 */     BufferBuilder bufferbuilder = tessellator.getBuffer();
/* 245 */     bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
/* 246 */     bufferbuilder.pos(0.0D, height, -90.0D).tex(0.0F, 1.0F).endVertex();
/* 247 */     bufferbuilder.pos(width, height, -90.0D).tex(1.0F, 1.0F).endVertex();
/* 248 */     bufferbuilder.pos(width, 0.0D, -90.0D).tex(1.0F, 0.0F).endVertex();
/* 249 */     bufferbuilder.pos(0.0D, 0.0D, -90.0D).tex(0.0F, 0.0F).endVertex();
/* 250 */     tessellator.draw();
/* 251 */     RenderSystem.depthMask(true);
/* 252 */     RenderSystem.enableDepthTest();
/* 253 */     RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 254 */     RenderSystem.defaultBlendFunc();
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\helpers\RendererHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */