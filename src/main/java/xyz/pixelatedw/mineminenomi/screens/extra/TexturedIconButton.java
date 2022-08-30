/*     */ package xyz.pixelatedw.mineminenomi.screens.extra;
/*     */ 
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.widget.button.Button;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class TexturedIconButton
/*     */   extends Button
/*     */ {
/*     */   private ResourceLocation texture;
/*     */   private int textureWidth;
/*     */   private int textureHeight;
/*     */   private int texturePosX;
/*     */   private int texturePosY;
/*     */   private int textPosX;
/*     */   private int textPosY;
/*  28 */   private double textScale = 1.0D;
/*     */   private ResourceLocation iconTexture;
/*  30 */   private double iconScale = 1.0D;
/*     */   
/*     */   private int iconPosX;
/*     */   private int iconPosY;
/*     */   private boolean isPressed;
/*     */   
/*     */   public TexturedIconButton(ResourceLocation loc, int posX, int posY, int width, int height, String text, Button.IPressable onPress) {
/*  37 */     super(posX, posY, width, height, text, onPress);
/*     */     
/*  39 */     this.texture = loc;
/*  40 */     this.texturePosX = posX;
/*  41 */     this.texturePosY = posY;
/*  42 */     this.textureWidth = width;
/*  43 */     this.textureHeight = height;
/*     */   }
/*     */ 
/*     */   
/*     */   public TexturedIconButton setTextureInfo(int texturePosX, int texturePosY, int textureWidth, int textureHeight) {
/*  48 */     this.texturePosX = texturePosX;
/*  49 */     this.texturePosY = texturePosY;
/*  50 */     this.textureWidth = textureWidth;
/*  51 */     this.textureHeight = textureHeight;
/*  52 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public TexturedIconButton setTextInfo(int textPosX, int textPosY, double scale) {
/*  57 */     this.textPosX = textPosX;
/*  58 */     this.textPosY = textPosY - 7;
/*  59 */     this.textScale = scale;
/*  60 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public TexturedIconButton setIconInfo(ResourceLocation loc, int iconPosX, int iconPosY, double scale) {
/*  65 */     this.iconTexture = loc;
/*  66 */     this.iconPosX = iconPosX;
/*  67 */     this.iconPosY = iconPosY;
/*  68 */     this.iconScale = scale;
/*  69 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(int mouseX, int mouseY, float partialTicks) {
/*  75 */     if (!this.visible) {
/*     */       return;
/*     */     }
/*  78 */     this.isHovered = (this.active && mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height);
/*     */     
/*  80 */     RenderSystem.pushMatrix();
/*  81 */     if (this.isHovered || this.isPressed) {
/*     */       
/*  83 */       RenderSystem.translated(0.0D, 0.5D, 0.0D);
/*  84 */       RenderSystem.color3f(0.7F, 0.7F, 0.7F);
/*     */     } 
/*     */     
/*  87 */     if (!this.active) {
/*  88 */       RenderSystem.color3f(0.5F, 0.5F, 0.5F);
/*     */     }
/*  90 */     WyHelper.drawIcon(this.texture, this.texturePosX, this.texturePosY, this.textureWidth, this.textureHeight);
/*     */ 
/*     */     
/*  93 */     if (this.iconTexture != null) {
/*     */       
/*  95 */       RenderSystem.pushMatrix();
/*     */       
/*  97 */       RenderSystem.enableBlend();
/*  98 */       RenderSystem.translated(this.iconPosX, this.iconPosY, 2.0D);
/*  99 */       GL11.glTranslated(16.0D, 16.0D, 0.0D);
/* 100 */       GL11.glScaled(this.iconScale, this.iconScale, this.iconScale);
/* 101 */       GL11.glTranslated(-16.0D, -16.0D, 1.0D);
/*     */       
/* 103 */       WyHelper.drawIcon(this.iconTexture, 0, 0, 16, 16);
/*     */       
/* 105 */       RenderSystem.popMatrix();
/*     */     } 
/*     */ 
/*     */     
/* 109 */     RenderSystem.pushMatrix();
/*     */     
/* 111 */     FontRenderer font = (Minecraft.getInstance()).fontRenderer;
/* 112 */     List<String> strings = Arrays.asList(new String[] { getMessage() });
/*     */     
/* 114 */     int splits = (getMessage().split(" ")).length;
/* 115 */     if (splits > 1) {
/* 116 */       strings = WyHelper.splitString(font, getMessage(), this.textPosX - font.getStringWidth(getMessage()) / 2 + 26, this.width / splits + 10);
/*     */     }
/* 118 */     RenderSystem.translated(this.textPosX, (this.textPosY - ((strings.size() > 1) ? (strings.size() * 3) : 0)), 2.0D);
/* 119 */     RenderSystem.translated(128.0D, 128.0D, 0.0D);
/* 120 */     RenderSystem.scaled(this.textScale, this.textScale, this.textScale);
/* 121 */     RenderSystem.translated(-128.0D, -128.0D, 1.0D);
/* 122 */     for (int b = 0; b < strings.size(); b++)
/*     */     {
/* 124 */       WyHelper.drawStringWithBorder(font, strings.get(b), 0, 7 + b * 9, WyHelper.hexToRGB("#FFFFFF").getRGB());
/*     */     }
/*     */     
/* 127 */     RenderSystem.popMatrix();
/*     */     
/* 129 */     RenderSystem.color3f(1.0F, 1.0F, 1.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 134 */     RenderSystem.popMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIsPressed(boolean flag) {
/* 139 */     this.isPressed = flag;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\screens\extra\TexturedIconButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */