/*     */ package xyz.pixelatedw.mineminenomi.screens;
/*     */ 
/*     */ import com.mojang.blaze3d.platform.GlStateManager;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.awt.Color;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.screen.Screen;
/*     */ import net.minecraft.client.gui.widget.Widget;
/*     */ import net.minecraft.client.gui.widget.button.Button;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.RegistryObject;
/*     */ import net.minecraftforge.fml.client.gui.GuiUtils;
/*     */ import net.minecraftforge.fml.client.gui.widget.Slider;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.JollyRoger;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.JollyRogerElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModJollyRogers;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.crew.CUpdateJollyRogerPacket;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.NoTextureButton;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.TexturedIconButton;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class JollyRogerCreatorScreen
/*     */   extends Screen
/*     */ {
/*     */   private PlayerEntity player;
/*     */   private Widget selectedButton;
/*  47 */   private JollyRogerElement.LayerType layerType = JollyRogerElement.LayerType.BASE;
/*     */   private ExtendedWorldData worldData;
/*     */   private JollyRoger jollyRoger;
/*  50 */   private float animationTime = 0.0F;
/*  51 */   private int nextElementTry = 0;
/*     */   
/*     */   private boolean isEditing = false;
/*     */   
/*     */   private Crew crew;
/*     */   private int layerIndex;
/*     */   private int trueIndex;
/*     */   private Slider redSlider;
/*     */   private Slider greenSlider;
/*     */   private Slider blueSlider;
/*     */   private Collection<RegistryObject<JollyRogerElement>> allElements;
/*     */   private List<RegistryObject<JollyRogerElement>> allBases;
/*     */   private List<RegistryObject<JollyRogerElement>> allBackgrounds;
/*     */   private List<RegistryObject<JollyRogerElement>> allDetails;
/*     */   
/*     */   public JollyRogerCreatorScreen(boolean isEditing) {
/*  67 */     super((ITextComponent)new StringTextComponent(""));
/*  68 */     this.player = (PlayerEntity)(Minecraft.getInstance()).player;
/*  69 */     this.worldData = ExtendedWorldData.get(this.player.world);
/*  70 */     this.crew = this.worldData.getCrewWithMember(this.player.getUniqueID());
/*  71 */     this.jollyRoger = this.crew.getJollyRoger();
/*  72 */     this.isEditing = isEditing;
/*     */     
/*  74 */     this.allElements = ModJollyRogers.JOLLY_ROGER_ELEMENTS.getEntries();
/*  75 */     this.allBases = getTotalElementsForType(this.player, JollyRogerElement.LayerType.BASE);
/*  76 */     this.allBackgrounds = getTotalElementsForType(this.player, JollyRogerElement.LayerType.BACKGROUND);
/*  77 */     this.allDetails = getTotalElementsForType(this.player, JollyRogerElement.LayerType.DETAIL);
/*     */     
/*  79 */     this.animationTime = 1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(int x, int y, float f) {
/*  85 */     this.player.world.getProfiler().startSection("jollyRogerRendering");
/*     */     
/*  87 */     renderBackground();
/*     */     
/*  89 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  91 */     int posX = this.width / 2;
/*  92 */     int posY = this.height / 2;
/*     */     
/*  94 */     RenderSystem.enableBlend();
/*  95 */     RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
/*     */     
/*  97 */     GL11.glPushMatrix();
/*     */     
/*  99 */     double scale = 0.5D;
/* 100 */     GL11.glTranslated((posX - 115), (posY - 130), 1.0D);
/* 101 */     GL11.glTranslated(128.0D, 128.0D, 0.0D);
/* 102 */     GL11.glScaled(scale, scale, scale);
/* 103 */     GL11.glTranslated(-128.0D, -128.0D, 0.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 109 */     RendererHelper.drawPlayerJollyRoger(this.jollyRoger);
/*     */ 
/*     */ 
/*     */     
/* 113 */     if (this.animationTime < 0.15D) {
/*     */       
/* 115 */       this.animationTime = (float)(this.animationTime + 0.007D);
/* 116 */       scale += 0.45D + this.animationTime;
/*     */       
/* 118 */       GL11.glTranslated(128.0D, 128.0D, 0.0D);
/* 119 */       GL11.glScaled(scale, scale, scale);
/* 120 */       GL11.glTranslated(-128.0D, -128.0D, 0.0D);
/*     */       
/* 122 */       JollyRogerElement jollyRogerElement = getLayerElement();
/* 123 */       if (jollyRogerElement != null) {
/*     */         
/* 125 */         if (jollyRogerElement.canBeColored()) {
/*     */           
/* 127 */           Color color = WyHelper.getComplementaryColor(WyHelper.hexToRGB(jollyRogerElement.getColor()));
/* 128 */           RenderSystem.color4f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, 0.9F - this.animationTime * 5.0F);
/*     */         }
/*     */         else {
/*     */           
/* 132 */           RenderSystem.color4f(1.0F, 0.0F, 0.0F, 0.9F - this.animationTime * 4.0F);
/*     */         } 
/* 134 */         Minecraft.getInstance().getTextureManager().bindTexture(jollyRogerElement.getTexture());
/* 135 */         GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, 10.0F);
/*     */       } 
/*     */     } 
/*     */     
/* 139 */     GL11.glPopMatrix();
/*     */     
/* 141 */     String text = (this.trueIndex >= 0) ? ((this.trueIndex + 1) + " / " + this.allBases.size()) : (new TranslationTextComponent(ModI18n.GUI_EMPTY, new Object[0])).getFormattedText();
/* 142 */     if (this.layerType == JollyRogerElement.LayerType.BACKGROUND) {
/* 143 */       text = (this.trueIndex >= 0) ? ((this.trueIndex + 1) + " / " + this.allBackgrounds.size()) : (new TranslationTextComponent(ModI18n.GUI_EMPTY, new Object[0])).getFormattedText();
/* 144 */     } else if (this.layerType == JollyRogerElement.LayerType.DETAIL) {
/* 145 */       text = (this.trueIndex >= 0) ? ((this.trueIndex + 1) + " / " + this.allDetails.size()) : (new TranslationTextComponent(ModI18n.GUI_EMPTY, new Object[0])).getFormattedText();
/* 146 */     }  WyHelper.drawStringWithBorder(this.font, text, posX - this.font.getStringWidth(text) / 2 + 12, posY + 80, WyHelper.hexToRGB("#FFFFFF").getRGB());
/*     */     
/* 148 */     RenderSystem.disableBlend();
/*     */     
/* 150 */     posX = this.width;
/* 151 */     posY = this.height / 2;
/*     */     
/* 153 */     int outlineSize = 2;
/* 154 */     int pX = posX - 95;
/* 155 */     int pY = posY - 85;
/* 156 */     int sW = posX + 100;
/* 157 */     int sH = posY + 45;
/*     */     
/* 159 */     JollyRogerElement element = getLayerElement();
/*     */     
/* 161 */     if (element != null) {
/*     */       
/* 163 */       if (element.canBeColored()) {
/*     */         
/* 165 */         fillGradient(pX - outlineSize, pY - outlineSize, sW + outlineSize, sH + outlineSize, WyHelper.hexToRGB("#000000").getRGB(), WyHelper.hexToRGB("#000000").getRGB());
/* 166 */         fillGradient(pX, pY, sW, sH, WyHelper.hexToRGB("#B3B3B3").getRGB(), WyHelper.hexToRGB("#505050").getRGB());
/*     */         
/* 168 */         posY -= 75;
/* 169 */         WyHelper.drawStringWithBorder(this.font, (new TranslationTextComponent(ModI18n.GUI_RED, new Object[0])).getFormattedText() + " ", posX - 75, posY, WyHelper.hexToRGB("#FFFFFF").getRGB());
/* 170 */         WyHelper.drawStringWithBorder(this.font, "0", posX - 85, posY + 14, WyHelper.hexToRGB("#FFFFFF").getRGB());
/* 171 */         WyHelper.drawStringWithBorder(this.font, "255", posX - 23, posY + 14, WyHelper.hexToRGB("#FFFFFF").getRGB());
/*     */         
/* 173 */         posY += 40;
/* 174 */         WyHelper.drawStringWithBorder(this.font, (new TranslationTextComponent(ModI18n.GUI_GREEN, new Object[0])).getFormattedText() + " ", posX - 75, posY, WyHelper.hexToRGB("#FFFFFF").getRGB());
/* 175 */         WyHelper.drawStringWithBorder(this.font, "0", posX - 85, posY + 14, WyHelper.hexToRGB("#FFFFFF").getRGB());
/* 176 */         WyHelper.drawStringWithBorder(this.font, "255", posX - 23, posY + 14, WyHelper.hexToRGB("#FFFFFF").getRGB());
/*     */         
/* 178 */         posY += 40;
/* 179 */         WyHelper.drawStringWithBorder(this.font, (new TranslationTextComponent(ModI18n.GUI_BLUE, new Object[0])).getFormattedText() + " ", posX - 75, posY, WyHelper.hexToRGB("#FFFFFF").getRGB());
/* 180 */         WyHelper.drawStringWithBorder(this.font, "0", posX - 85, posY + 14, WyHelper.hexToRGB("#FFFFFF").getRGB());
/* 181 */         WyHelper.drawStringWithBorder(this.font, "255", posX - 23, posY + 14, WyHelper.hexToRGB("#FFFFFF").getRGB());
/*     */       } 
/*     */       
/* 184 */       if (element != this.jollyRoger.getBase()) {
/*     */         
/* 186 */         posY = this.height / 2;
/*     */         
/* 188 */         pX = posX - 95;
/* 189 */         pY = posY + 50;
/* 190 */         sW = posX + 100;
/* 191 */         sH = posY + 85;
/*     */         
/* 193 */         fillGradient(pX - outlineSize, pY - outlineSize, sW + outlineSize, sH + outlineSize, WyHelper.hexToRGB("#000000").getRGB(), WyHelper.hexToRGB("#000000").getRGB());
/* 194 */         fillGradient(pX, pY, sW, sH, WyHelper.hexToRGB("#B3B3B3").getRGB(), WyHelper.hexToRGB("#505050").getRGB());
/*     */         
/* 196 */         WyHelper.drawStringWithBorder(this.font, (this.layerIndex + 1) + "", posX - 55, posY + 64, WyHelper.hexToRGB("#FFFFFF").getRGB());
/*     */       } 
/*     */     } 
/*     */     
/* 200 */     this.player.world.getProfiler().endSection();
/*     */     
/* 202 */     super.render(x, y, f);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void init(Minecraft mc, int width, int height) {
/* 208 */     super.init(mc, width, height);
/*     */     
/* 210 */     int posX = 0;
/* 211 */     int posY = this.height / 2;
/*     */     
/* 213 */     int listPosY = posY - 85;
/*     */     
/* 215 */     NoTextureButton baseButton = new NoTextureButton(posX + 5, listPosY, 115, 16, (new TranslationTextComponent(ModI18n.GUI_BASE, new Object[0])).getFormattedText(), this::selectButton);
/* 216 */     addButton((Widget)baseButton);
/*     */     int i;
/* 218 */     for (i = 0; i < (this.jollyRoger.getBackgrounds()).length; i++) {
/*     */       
/* 220 */       NoTextureButton bgButton = new NoTextureButton(posX + 5, listPosY + 20 + i * 20, 115, 16, (new TranslationTextComponent(ModI18n.GUI_BACKGROUND, new Object[0])).getFormattedText() + " " + (i + 1), this::selectButton);
/* 221 */       addButton((Widget)bgButton);
/*     */     } 
/*     */     
/* 224 */     for (i = 0; i < (this.jollyRoger.getDetails()).length; i++) {
/*     */       
/* 226 */       NoTextureButton detailButton = new NoTextureButton(posX + 5, listPosY + 60 + i * 20, 115, 16, (new TranslationTextComponent(ModI18n.GUI_DETAIL, new Object[0])).getFormattedText() + " " + (i + 1), this::selectButton);
/* 227 */       addButton((Widget)detailButton);
/*     */     } 
/*     */     
/* 230 */     posX = this.width / 2;
/*     */     
/* 232 */     TexturedIconButton nextBaseTexture = new TexturedIconButton(ModResources.BIG_WOOD_BUTTON_RIGHT, posX + 80, posY - 65, 32, 110, "", btn -> moveIndex(btn, true));
/* 233 */     nextBaseTexture = nextBaseTexture.setTextureInfo(posX + 80, posY - 75, 32, 128);
/* 234 */     addButton((Widget)nextBaseTexture);
/*     */     
/* 236 */     TexturedIconButton prevBaseTexture = new TexturedIconButton(ModResources.BIG_WOOD_BUTTON_LEFT, posX - 85, posY - 65, 32, 110, "", btn -> moveIndex(btn, false));
/* 237 */     prevBaseTexture = prevBaseTexture.setTextureInfo(posX - 85, posY - 75, 32, 128);
/* 238 */     addButton((Widget)prevBaseTexture);
/*     */     
/* 240 */     posX = this.width;
/*     */     
/* 242 */     Slider redColorPicker = new Slider(posX - 76, posY - 65, 50, 16, "", "", 0.0D, 255.0D, 255.0D, false, true, btn -> {
/*     */         
/*     */         });
/* 245 */     this.redSlider = redColorPicker;
/* 246 */     addButton((Widget)redColorPicker);
/*     */     
/* 248 */     Slider greenColorPicker = new Slider(posX - 76, posY - 25, 50, 16, "", "", 0.0D, 255.0D, 255.0D, false, true, btn -> {
/*     */         
/*     */         });
/* 251 */     this.greenSlider = greenColorPicker;
/* 252 */     addButton((Widget)greenColorPicker);
/*     */     
/* 254 */     Slider blueColorPicker = new Slider(posX - 76, posY + 15, 50, 16, "", "", 0.0D, 255.0D, 255.0D, false, true, btn -> {
/*     */         
/*     */         });
/* 257 */     this.blueSlider = blueColorPicker;
/* 258 */     addButton((Widget)blueColorPicker);
/*     */     
/* 260 */     NoTextureButton editJollyRogerButton = new NoTextureButton(this.width / 2 - 17, posY + 95, 60, 16, (new TranslationTextComponent(ModI18n.GUI_FINISH, new Object[0])).getFormattedText(), btn -> finishEditing());
/* 261 */     addButton((Widget)editJollyRogerButton);
/*     */     
/* 263 */     TexturedIconButton layerUpBtn = new TexturedIconButton(ModResources.BRIGHT_WOOD_ARROW, posX - 80, posY + 53, 16, 25, "", btn -> changeLayerIndex(true));
/* 264 */     layerUpBtn = layerUpBtn.setTextureInfo(posX - 104, posY + 51, 64, 32);
/* 265 */     addButton((Widget)layerUpBtn);
/*     */     
/* 267 */     TexturedIconButton layerDownBtn = new TexturedIconButton(ModResources.BRIGHT_WOOD_ARROW_DOWN, posX - 40, posY + 53, 16, 25, "", btn -> changeLayerIndex(false));
/* 268 */     layerDownBtn = layerDownBtn.setTextureInfo(posX - 64, posY + 48, 64, 32);
/* 269 */     addButton((Widget)layerDownBtn);
/*     */     
/* 271 */     updateButtons();
/*     */   }
/*     */ 
/*     */   
/*     */   private void finishEditing() {
/* 276 */     if (this.isEditing) {
/* 277 */       NewCrewScreen.open();
/*     */     } else {
/* 279 */       onClose();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void changeLayerIndex(boolean isUp) {
/* 284 */     int layerIndex = this.layerIndex;
/* 285 */     boolean canSwitch = false;
/*     */     
/* 287 */     if (this.layerType == JollyRogerElement.LayerType.BACKGROUND) {
/*     */       
/* 289 */       JollyRogerElement currentElement = this.jollyRoger.getBackgrounds()[layerIndex];
/* 290 */       JollyRogerElement nextElement = null;
/* 291 */       JollyRogerElement prevElement = null;
/*     */       
/* 293 */       if (isUp && this.layerIndex >= 0 && this.layerIndex + 1 < (this.jollyRoger.getBackgrounds()).length) {
/*     */         
/* 295 */         nextElement = this.jollyRoger.getBackgrounds()[++layerIndex];
/* 296 */         canSwitch = true;
/*     */       }
/* 298 */       else if (!isUp && this.layerIndex - 1 >= 0 && this.layerIndex <= (this.jollyRoger.getBackgrounds()).length) {
/*     */         
/* 300 */         prevElement = this.jollyRoger.getBackgrounds()[--layerIndex];
/* 301 */         canSwitch = true;
/*     */       } 
/*     */       
/* 304 */       if (currentElement != null && canSwitch)
/*     */       {
/* 306 */         this.jollyRoger.getBackgrounds()[layerIndex] = currentElement;
/*     */         
/* 308 */         if (isUp) {
/*     */           
/* 310 */           this.jollyRoger.getBackgrounds()[--layerIndex] = nextElement;
/* 311 */           this.layerIndex++;
/*     */         }
/* 313 */         else if (!isUp) {
/*     */           
/* 315 */           this.jollyRoger.getBackgrounds()[++layerIndex] = prevElement;
/* 316 */           this.layerIndex--;
/*     */         } 
/*     */         
/* 319 */         updateButtons();
/* 320 */         this.animationTime = 0.0F;
/* 321 */         ((NoTextureButton)this.selectedButton).select();
/* 322 */         this.selectedButton = this.buttons.get(1 + this.layerIndex);
/* 323 */         ((NoTextureButton)this.selectedButton).select();
/*     */       }
/*     */     
/* 326 */     } else if (this.layerType == JollyRogerElement.LayerType.DETAIL) {
/*     */       
/* 328 */       JollyRogerElement currentElement = this.jollyRoger.getDetails()[layerIndex];
/* 329 */       JollyRogerElement nextElement = null;
/* 330 */       JollyRogerElement prevElement = null;
/*     */       
/* 332 */       if (isUp && this.layerIndex >= 0 && this.layerIndex + 1 < (this.jollyRoger.getDetails()).length) {
/*     */         
/* 334 */         nextElement = this.jollyRoger.getDetails()[++layerIndex];
/* 335 */         canSwitch = true;
/*     */       }
/* 337 */       else if (!isUp && this.layerIndex - 1 >= 0 && this.layerIndex <= (this.jollyRoger.getDetails()).length) {
/*     */         
/* 339 */         prevElement = this.jollyRoger.getDetails()[--layerIndex];
/* 340 */         canSwitch = true;
/*     */       } 
/*     */       
/* 343 */       if (currentElement != null && canSwitch) {
/*     */         
/* 345 */         this.jollyRoger.getDetails()[layerIndex] = currentElement;
/*     */         
/* 347 */         if (isUp) {
/*     */           
/* 349 */           this.jollyRoger.getDetails()[--layerIndex] = nextElement;
/* 350 */           this.layerIndex++;
/*     */         }
/* 352 */         else if (!isUp) {
/*     */           
/* 354 */           this.jollyRoger.getDetails()[++layerIndex] = prevElement;
/* 355 */           this.layerIndex--;
/*     */         } 
/*     */         
/* 358 */         updateButtons();
/* 359 */         this.animationTime = 0.0F;
/* 360 */         ((NoTextureButton)this.selectedButton).select();
/* 361 */         this.selectedButton = this.buttons.get(3 + this.layerIndex);
/* 362 */         ((NoTextureButton)this.selectedButton).select();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeColor(Slider slider, String color) {
/* 369 */     if (!slider.isHovered()) {
/* 370 */       slider.dragging = false;
/*     */     }
/* 372 */     JollyRogerElement element = getLayerElement();
/* 373 */     if (element == null) {
/*     */       return;
/*     */     }
/* 376 */     String currentColor = element.getColor();
/* 377 */     Color rgb = WyHelper.hexToRGB(currentColor);
/*     */     
/* 379 */     String hex = "#FFFFFF";
/*     */     
/* 381 */     if (color.equalsIgnoreCase("red")) {
/* 382 */       hex = WyHelper.rgbToHex(slider.getValueInt(), rgb.getGreen(), rgb.getBlue());
/* 383 */     } else if (color.equalsIgnoreCase("green")) {
/* 384 */       hex = WyHelper.rgbToHex(rgb.getRed(), slider.getValueInt(), rgb.getBlue());
/* 385 */     } else if (color.equalsIgnoreCase("blue")) {
/* 386 */       hex = WyHelper.rgbToHex(rgb.getRed(), rgb.getGreen(), slider.getValueInt());
/*     */     } 
/* 388 */     element.setColor(hex);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void moveIndex(Button btn, boolean toRight) {
/*     */     try {
/* 395 */       this.nextElementTry++;
/*     */       
/* 397 */       if (toRight) {
/* 398 */         this.trueIndex++;
/*     */       } else {
/* 400 */         this.trueIndex--;
/*     */       } 
/* 402 */       if (this.layerType == JollyRogerElement.LayerType.BASE) {
/*     */         
/* 404 */         if (this.trueIndex >= this.allBases.size())
/* 405 */           this.trueIndex = -1; 
/* 406 */         if (this.trueIndex < 0 && this.jollyRoger.getBase() == null) {
/* 407 */           this.trueIndex = this.allBases.size() - 1;
/*     */         }
/* 409 */         if (this.trueIndex >= 0 && this.trueIndex <= this.allBases.size()) {
/* 410 */           this.jollyRoger.setBase((JollyRogerElement)((RegistryObject)this.allBases.get(this.trueIndex)).get());
/* 411 */         } else if (this.trueIndex <= 0 && this.jollyRoger.getBase().getTexture() != null) {
/* 412 */           this.jollyRoger.setBase(null);
/*     */         }  int i;
/* 414 */         for (i = 0; i < (this.jollyRoger.getBackgrounds()).length; i++) {
/*     */           
/* 416 */           JollyRogerElement element = this.jollyRoger.getBackgrounds()[i];
/* 417 */           boolean hasElement = this.allBackgrounds.stream().anyMatch(elem -> (elem != null && elem.get() != null && ((JollyRogerElement)elem.get()).equals(element) && !((JollyRogerElement)elem.get()).canUse(this.player)));
/* 418 */           if (hasElement) {
/* 419 */             this.jollyRoger.getBackgrounds()[i] = null;
/*     */           }
/*     */         } 
/* 422 */         for (i = 0; i < (this.jollyRoger.getDetails()).length; i++) {
/*     */           
/* 424 */           JollyRogerElement element = this.jollyRoger.getDetails()[i];
/* 425 */           boolean hasElement = this.allDetails.stream().anyMatch(elem -> (elem != null && elem.get() != null && ((JollyRogerElement)elem.get()).equals(element) && !((JollyRogerElement)elem.get()).canUse(this.player)));
/* 426 */           if (hasElement) {
/* 427 */             this.jollyRoger.getDetails()[i] = null;
/*     */           }
/*     */         } 
/* 430 */       } else if (this.layerType == JollyRogerElement.LayerType.BACKGROUND) {
/*     */         
/* 432 */         if (this.trueIndex >= this.allBackgrounds.size())
/* 433 */           this.trueIndex = -1; 
/* 434 */         if (this.trueIndex < 0 && this.jollyRoger.getBackgrounds()[this.layerIndex] == null) {
/* 435 */           this.trueIndex = this.allBackgrounds.size() - 1;
/*     */         }
/* 437 */         if (this.nextElementTry > this.allBackgrounds.size()) {
/*     */           
/* 439 */           this.jollyRoger.getBackgrounds()[this.layerIndex] = null;
/* 440 */           this.trueIndex = -1;
/* 441 */           this.nextElementTry = 0;
/* 442 */           updateButtons();
/*     */           
/*     */           return;
/*     */         } 
/* 446 */         if (this.trueIndex >= 0 && this.trueIndex <= this.allBackgrounds.size()) {
/*     */           
/* 448 */           JollyRogerElement ogElem = (JollyRogerElement)((RegistryObject)this.allBackgrounds.get(this.trueIndex)).get();
/* 449 */           for (int i = 0; i < (this.jollyRoger.getBackgrounds()).length; i++) {
/*     */             
/* 451 */             JollyRogerElement element = this.jollyRoger.getBackgrounds()[i];
/* 452 */             if (element != null && ogElem != null && ogElem.equals(element)) {
/*     */               
/* 454 */               moveIndex(btn, toRight);
/*     */               
/*     */               return;
/*     */             } 
/*     */           } 
/*     */         } 
/* 460 */         if (this.trueIndex >= 0 && this.trueIndex <= this.allBackgrounds.size()) {
/* 461 */           this.jollyRoger.getBackgrounds()[this.layerIndex] = (JollyRogerElement)((RegistryObject)this.allBackgrounds.get(this.trueIndex)).get();
/* 462 */         } else if (this.trueIndex <= 0 && this.jollyRoger.getBackgrounds()[this.layerIndex].getTexture() != null) {
/* 463 */           this.jollyRoger.getBackgrounds()[this.layerIndex] = null;
/*     */         } 
/* 465 */         this.nextElementTry = 0;
/*     */       }
/* 467 */       else if (this.layerType == JollyRogerElement.LayerType.DETAIL) {
/*     */         
/* 469 */         if (this.trueIndex >= this.allDetails.size())
/* 470 */           this.trueIndex = -1; 
/* 471 */         if (this.trueIndex < 0 && this.trueIndex <= this.allDetails.size() && this.jollyRoger.getDetails()[this.layerIndex] == null) {
/* 472 */           this.trueIndex = this.allDetails.size() - 1;
/*     */         }
/* 474 */         if (this.nextElementTry >= this.allDetails.size()) {
/*     */           
/* 476 */           this.jollyRoger.getDetails()[this.layerIndex] = null;
/* 477 */           this.trueIndex = -1;
/* 478 */           this.nextElementTry = 0;
/* 479 */           updateButtons();
/*     */           
/*     */           return;
/*     */         } 
/* 483 */         if (this.trueIndex >= 0 && this.trueIndex <= this.allDetails.size()) {
/*     */           
/* 485 */           JollyRogerElement ogElem = (JollyRogerElement)((RegistryObject)this.allDetails.get(this.trueIndex)).get();
/* 486 */           for (int i = 0; i < (this.jollyRoger.getDetails()).length; i++) {
/*     */             
/* 488 */             JollyRogerElement element = this.jollyRoger.getDetails()[i];
/* 489 */             if (element != null && ogElem != null && ogElem.equals(element)) {
/*     */               
/* 491 */               moveIndex(btn, toRight);
/*     */               
/*     */               return;
/*     */             } 
/*     */           } 
/*     */         } 
/* 497 */         if (this.trueIndex >= 0 && this.trueIndex <= this.allDetails.size()) {
/* 498 */           this.jollyRoger.getDetails()[this.layerIndex] = (JollyRogerElement)((RegistryObject)this.allDetails.get(this.trueIndex)).get();
/* 499 */         } else if (this.trueIndex <= 0 && this.jollyRoger.getDetails()[this.layerIndex].getTexture() != null) {
/* 500 */           this.jollyRoger.getDetails()[this.layerIndex] = null;
/*     */         } 
/* 502 */         this.nextElementTry = 0;
/*     */       } 
/*     */       
/* 505 */       updateButtons();
/*     */     }
/* 507 */     catch (Exception e) {
/*     */       
/* 509 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void selectButton(Button btn) {
/* 515 */     if (!(btn instanceof NoTextureButton)) {
/*     */       return;
/*     */     }
/* 518 */     if (this.selectedButton != null)
/* 519 */       ((NoTextureButton)this.selectedButton).select(); 
/* 520 */     this.selectedButton = (Widget)btn;
/* 521 */     ((NoTextureButton)btn).select();
/* 522 */     this.animationTime = 0.0F;
/*     */     
/* 524 */     boolean hasLayerSet = false;
/*     */     
/* 526 */     if (this.buttons.get(0) == btn) {
/*     */       
/* 528 */       this.trueIndex = findIndex(getListFromType(JollyRogerElement.LayerType.BASE), this.jollyRoger.getBase(), this.player);
/* 529 */       this.layerType = JollyRogerElement.LayerType.BASE;
/* 530 */       this.layerIndex = 0;
/*     */       
/* 532 */       hasLayerSet = true;
/*     */     } 
/*     */     
/* 535 */     if (!hasLayerSet) {
/*     */       
/* 537 */       int j = 0;
/* 538 */       for (int i = 1; i < (this.jollyRoger.getBackgrounds()).length + 1; i++) {
/*     */         
/* 540 */         if (this.buttons.get(i) == btn) {
/*     */           
/* 542 */           this.trueIndex = findIndex(getListFromType(JollyRogerElement.LayerType.BACKGROUND), this.jollyRoger.getBackgrounds()[j], this.player);
/* 543 */           this.layerType = JollyRogerElement.LayerType.BACKGROUND;
/* 544 */           this.allBackgrounds = getTotalElementsForType(this.player, JollyRogerElement.LayerType.BACKGROUND);
/* 545 */           this.layerIndex = j;
/*     */           
/* 547 */           hasLayerSet = true;
/*     */         } 
/* 549 */         j++;
/*     */       } 
/*     */     } 
/*     */     
/* 553 */     if (!hasLayerSet) {
/*     */       
/* 555 */       int j = 0;
/* 556 */       for (int i = (this.jollyRoger.getBackgrounds()).length + 1; i < (this.jollyRoger.getDetails()).length + (this.jollyRoger.getBackgrounds()).length + 1; i++) {
/*     */         
/* 558 */         if (this.buttons.get(i) == btn) {
/*     */           
/* 560 */           this.trueIndex = findIndex(getListFromType(JollyRogerElement.LayerType.DETAIL), this.jollyRoger.getDetails()[j], this.player);
/* 561 */           this.layerType = JollyRogerElement.LayerType.DETAIL;
/* 562 */           this.allDetails = getTotalElementsForType(this.player, JollyRogerElement.LayerType.DETAIL);
/* 563 */           this.layerIndex = j;
/*     */           
/* 565 */           hasLayerSet = true;
/*     */         } 
/* 567 */         j++;
/*     */       } 
/*     */     } 
/*     */     
/* 571 */     updateButtons();
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateButtons() {
/* 576 */     JollyRogerElement element = getLayerElement();
/*     */     
/* 578 */     if (element == null) {
/*     */ 
/*     */       
/* 581 */       ((Widget)this.buttons.get(this.buttons.size() - 1)).visible = false;
/* 582 */       ((Widget)this.buttons.get(this.buttons.size() - 2)).visible = false;
/*     */ 
/*     */       
/* 585 */       for (Widget widget : this.buttons)
/*     */       {
/* 587 */         if (widget instanceof Slider)
/*     */         {
/* 589 */           widget.visible = false;
/*     */         }
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 595 */       ((Widget)this.buttons.get(this.buttons.size() - 1)).visible = true;
/* 596 */       ((Widget)this.buttons.get(this.buttons.size() - 2)).visible = true;
/*     */       
/* 598 */       if (element == this.jollyRoger.getBase()) {
/*     */         
/* 600 */         ((Widget)this.buttons.get(this.buttons.size() - 1)).visible = false;
/* 601 */         ((Widget)this.buttons.get(this.buttons.size() - 2)).visible = false;
/*     */       } 
/*     */       
/* 604 */       if (!element.canBeColored()) {
/*     */         
/* 606 */         for (Widget widget : this.buttons)
/*     */         {
/* 608 */           if (widget instanceof Slider)
/*     */           {
/* 610 */             widget.visible = false;
/*     */           }
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 616 */         for (Widget widget : this.buttons) {
/*     */           
/* 618 */           if (widget instanceof Slider)
/*     */           {
/* 620 */             widget.visible = true;
/*     */           }
/*     */         } 
/* 623 */         resetColorSliders(element);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private JollyRogerElement getLayerElement() {
/* 630 */     JollyRogerElement element = this.jollyRoger.getBase();
/*     */     
/* 632 */     if (this.layerType == JollyRogerElement.LayerType.BASE) {
/* 633 */       element = this.jollyRoger.getBase();
/* 634 */     } else if (this.layerType == JollyRogerElement.LayerType.BACKGROUND) {
/* 635 */       element = this.jollyRoger.getBackgrounds()[this.layerIndex];
/* 636 */     } else if (this.layerType == JollyRogerElement.LayerType.DETAIL) {
/* 637 */       element = this.jollyRoger.getDetails()[this.layerIndex];
/*     */     } 
/* 639 */     return element;
/*     */   }
/*     */ 
/*     */   
/*     */   private void resetColorSliders(JollyRogerElement element) {
/* 644 */     if (element != null) {
/*     */       
/* 646 */       Color rgb = WyHelper.hexToRGB(element.getColor());
/* 647 */       this.redSlider.setValue(rgb.getRed());
/* 648 */       this.redSlider.updateSlider();
/* 649 */       this.greenSlider.setValue(rgb.getGreen());
/* 650 */       this.greenSlider.updateSlider();
/* 651 */       this.blueSlider.setValue(rgb.getBlue());
/* 652 */       this.blueSlider.updateSlider();
/*     */     }
/*     */     else {
/*     */       
/* 656 */       this.redSlider.setValue(255.0D);
/* 657 */       this.redSlider.updateSlider();
/* 658 */       this.greenSlider.setValue(255.0D);
/* 659 */       this.greenSlider.updateSlider();
/* 660 */       this.blueSlider.setValue(255.0D);
/* 661 */       this.blueSlider.updateSlider();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onClose() {
/* 668 */     WyNetwork.sendToServer(new CUpdateJollyRogerPacket(this.jollyRoger));
/* 669 */     super.onClose();
/*     */   }
/*     */ 
/*     */   
/*     */   private int findIndex(List<RegistryObject<JollyRogerElement>> elements, JollyRogerElement element, PlayerEntity player) {
/* 674 */     for (int i = 0; i < elements.size(); i++) {
/*     */       
/* 676 */       if (((JollyRogerElement)((RegistryObject)elements.get(i)).get()).equals(element))
/*     */       {
/* 678 */         return i;
/*     */       }
/*     */     } 
/*     */     
/* 682 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<RegistryObject<JollyRogerElement>> getListFromType(JollyRogerElement.LayerType type) {
/* 687 */     if (type == JollyRogerElement.LayerType.BASE)
/* 688 */       return this.allBases; 
/* 689 */     if (type == JollyRogerElement.LayerType.BACKGROUND)
/* 690 */       return this.allBackgrounds; 
/* 691 */     if (type == JollyRogerElement.LayerType.DETAIL) {
/* 692 */       return this.allDetails;
/*     */     }
/* 694 */     return this.allBases;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<RegistryObject<JollyRogerElement>> getTotalElementsForType(PlayerEntity player, JollyRogerElement.LayerType type) {
/* 699 */     return (List<RegistryObject<JollyRogerElement>>)this.allElements.stream().filter(reg -> (((JollyRogerElement)reg.get()).getLayerType() == type && ((JollyRogerElement)reg.get()).canUse(player))).collect(Collectors.toList());
/*     */   }
/*     */ 
/*     */   
/*     */   public static void open(boolean isEditing) {
/* 704 */     Minecraft.getInstance().displayGuiScreen(new JollyRogerCreatorScreen(isEditing));
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\screens\JollyRogerCreatorScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */