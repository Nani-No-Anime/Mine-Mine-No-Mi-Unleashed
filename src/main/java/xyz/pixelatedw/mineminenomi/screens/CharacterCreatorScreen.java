/*     */ package xyz.pixelatedw.mineminenomi.screens;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.screen.Screen;
/*     */ import net.minecraft.client.gui.widget.Widget;
/*     */ import net.minecraft.client.gui.widget.button.Button;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.client.gui.GuiUtils;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.CFinishCCPacket;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.TexturedIconButton;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class CharacterCreatorScreen extends Screen {
/*     */   private int page;
/*  25 */   private int renderTick = 0; private int maxOpt;
/*  26 */   private int minkTextureId = 0;
/*     */   
/*  28 */   private int[] options = new int[3];
/*  29 */   private final String[] FACTIONS = new String[] { "Random", "pirate", "marine", "bounty_hunter", "revolutionary" };
/*  30 */   private final String[] RACES = new String[] { "Random", "human", "fishman", "cyborg", "mink" };
/*  31 */   private final String[] STYLES = new String[] { "Random", "swordsman", "sniper", "doctor", "art_of_weather", "brawler", "black_leg" };
/*     */   
/*     */   private TexturedIconButton factionButton;
/*     */   
/*     */   private TexturedIconButton raceButton;
/*     */   
/*     */   private TexturedIconButton styleButton;
/*     */   private boolean hasRandomizedRace;
/*     */   
/*     */   public CharacterCreatorScreen(boolean hasRandomizedRace) {
/*  41 */     super((ITextComponent)new StringTextComponent(""));
/*  42 */     this.hasRandomizedRace = hasRandomizedRace;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(int x, int y, float f) {
/*  48 */     renderBackground();
/*     */     
/*  50 */     int posX = this.width / 2;
/*  51 */     int posY = this.height / 2;
/*     */ 
/*     */     
/*  54 */     Minecraft.getInstance().getTextureManager().bindTexture(ModResources.BLANK);
/*  55 */     GuiUtils.drawTexturedModalRect(posX - 110, posY - 80, 0, 0, 256, 256, 0.0F);
/*     */     
/*  57 */     if (this.page == 0)
/*     */     {
/*  59 */       if (getSelectedFactionId() == 0) {
/*     */         
/*  61 */         Minecraft.getInstance().getTextureManager().bindTexture(ModResources.RANDOM);
/*  62 */         GuiUtils.drawTexturedModalRect(posX - 115, posY - 110, 0, 0, 256, 256, 1.0F);
/*  63 */         drawCategory("Random", posX + 269, posY + 190, 3.0D);
/*     */       }
/*  65 */       else if (getSelectedFactionId() == 1) {
/*     */         
/*  67 */         Minecraft.getInstance().getTextureManager().bindTexture(ModResources.PIRATE_ICON);
/*  68 */         GuiUtils.drawTexturedModalRect(posX - 115, posY - 110, 0, 0, 256, 256, 1.0F);
/*  69 */         drawCategory("Pirate", posX + 268, posY + 190, 3.0D);
/*     */       }
/*  71 */       else if (getSelectedFactionId() == 2) {
/*     */         
/*  73 */         Minecraft.getInstance().getTextureManager().bindTexture(ModResources.MARINE_ICON);
/*  74 */         GuiUtils.drawTexturedModalRect(posX - 115, posY - 110, 0, 0, 256, 256, 1.0F);
/*  75 */         drawCategory("Marine", posX + 269, posY + 190, 3.0D);
/*     */       }
/*  77 */       else if (getSelectedFactionId() == 3) {
/*     */         
/*  79 */         Minecraft.getInstance().getTextureManager().bindTexture(ModResources.BOUNTY_HUNTER_ICON);
/*  80 */         GuiUtils.drawTexturedModalRect(posX - 115, posY - 110, 0, 0, 256, 256, 1.0F);
/*  81 */         drawCategory("Bounty", posX + 215, posY + 150, 2.7D);
/*  82 */         drawCategory("Hunter", posX + 248, posY + 170, 2.7D);
/*     */       }
/*  84 */       else if (getSelectedFactionId() == 4) {
/*     */         
/*  86 */         Minecraft.getInstance().getTextureManager().bindTexture(ModResources.REVOLUTIONARY_ARMY_ICON);
/*  87 */         GuiUtils.drawTexturedModalRect(posX - 115, posY - 110, 0, 0, 256, 256, 1.0F);
/*  88 */         drawCategory("Revolutionary", posX + 165, posY + 85, 2.2D);
/*  89 */         drawCategory("Army", posX + 190, posY + 100, 2.2D);
/*     */       } 
/*     */     }
/*  92 */     if (this.page == 1)
/*     */     {
/*  94 */       if (getSelectedRaceId() == 0) {
/*     */         
/*  96 */         Minecraft.getInstance().getTextureManager().bindTexture(ModResources.RANDOM);
/*  97 */         GuiUtils.drawTexturedModalRect(posX - 115, posY - 110, 0, 0, 256, 256, 1.0F);
/*  98 */         drawCategory("Random", posX + 269, posY + 190, 3.0D);
/*     */       }
/* 100 */       else if (getSelectedRaceId() == 1) {
/*     */         
/* 102 */         Minecraft.getInstance().getTextureManager().bindTexture(ModResources.HUMAN);
/* 103 */         GuiUtils.drawTexturedModalRect(posX - 118, posY - 110, 0, 0, 256, 256, 1.0F);
/* 104 */         drawCategory("Human", posX + 268, posY + 190, 3.0D);
/*     */       }
/* 106 */       else if (getSelectedRaceId() == 2) {
/*     */         
/* 108 */         Minecraft.getInstance().getTextureManager().bindTexture(ModResources.FISHMAN);
/* 109 */         GuiUtils.drawTexturedModalRect(posX - 118, posY - 110, 0, 0, 256, 256, 1.0F);
/* 110 */         drawCategory("Fishman", posX + 268, posY + 190, 3.0D);
/*     */       }
/* 112 */       else if (getSelectedRaceId() == 3) {
/*     */         
/* 114 */         Minecraft.getInstance().getTextureManager().bindTexture(ModResources.CYBORG);
/* 115 */         GuiUtils.drawTexturedModalRect(posX - 118, posY - 110, 0, 0, 256, 256, 1.0F);
/* 116 */         drawCategory("Cyborg", posX + 268, posY + 190, 3.0D);
/*     */       }
/* 118 */       else if (getSelectedRaceId() == 4) {
/*     */         
/* 120 */         ResourceLocation[] minkTexture = { ModResources.MINK1, ModResources.MINK2, ModResources.MINK3 };
/* 121 */         if (this.renderTick % 150 == 0)
/*     */         {
/* 123 */           if (this.minkTextureId == 2) {
/* 124 */             this.minkTextureId = 0;
/*     */           } else {
/* 126 */             this.minkTextureId++;
/*     */           } 
/*     */         }
/* 129 */         Minecraft.getInstance().getTextureManager().bindTexture(minkTexture[this.minkTextureId]);
/* 130 */         GuiUtils.drawTexturedModalRect(posX - 118, posY - 110, 0, 0, 256, 256, 1.0F);
/* 131 */         drawCategory("Mink", posX + 268, posY + 190, 3.0D);
/*     */       } 
/*     */     }
/* 134 */     if (this.page == 2)
/*     */     {
/* 136 */       if (getSelectedStyleId() == 0) {
/*     */         
/* 138 */         Minecraft.getInstance().getTextureManager().bindTexture(ModResources.RANDOM);
/* 139 */         GuiUtils.drawTexturedModalRect(posX - 115, posY - 110, 0, 0, 256, 256, 1.0F);
/* 140 */         drawCategory("Random", posX + 269, posY + 190, 3.0D);
/*     */       }
/* 142 */       else if (getSelectedStyleId() == 1) {
/*     */         
/* 144 */         Minecraft.getInstance().getTextureManager().bindTexture(ModResources.SWORDSMAN);
/* 145 */         GuiUtils.drawTexturedModalRect(posX - 113, posY - 115, 0, 0, 256, 256, 1.0F);
/* 146 */         drawCategory("Swordsman", posX + 268, posY + 190, 3.0D);
/*     */       }
/* 148 */       else if (getSelectedStyleId() == 2) {
/*     */         
/* 150 */         Minecraft.getInstance().getTextureManager().bindTexture(ModResources.SNIPER);
/* 151 */         GuiUtils.drawTexturedModalRect(posX - 113, posY - 115, 0, 0, 256, 256, 1.0F);
/* 152 */         drawCategory("Sniper", posX + 268, posY + 190, 3.0D);
/*     */       }
/* 154 */       else if (getSelectedStyleId() == 3) {
/*     */         
/* 156 */         Minecraft.getInstance().getTextureManager().bindTexture(ModResources.DOCTOR);
/* 157 */         GuiUtils.drawTexturedModalRect(posX - 113, posY - 115, 0, 0, 256, 256, 1.0F);
/* 158 */         drawCategory("Doctor", posX + 268, posY + 190, 3.0D);
/*     */       }
/* 160 */       else if (getSelectedStyleId() == 4) {
/*     */         
/* 162 */         Minecraft.getInstance().getTextureManager().bindTexture(ModResources.ART_OF_WEATHER);
/* 163 */         GuiUtils.drawTexturedModalRect(posX - 113, posY - 115, 0, 0, 256, 256, 1.0F);
/* 164 */         drawCategory("Art of Weather", posX + 180, posY + 100, 2.3D);
/*     */       }
/* 166 */       else if (getSelectedStyleId() == 5) {
/*     */         
/* 168 */         Minecraft.getInstance().getTextureManager().bindTexture(ModResources.BRAWLER);
/* 169 */         GuiUtils.drawTexturedModalRect(posX - 113, posY - 115, 0, 0, 256, 256, 1.0F);
/* 170 */         drawCategory("Brawler", posX + 268, posY + 190, 3.0D);
/*     */       }
/* 172 */       else if (getSelectedStyleId() == 6) {
/*     */         
/* 174 */         Minecraft.getInstance().getTextureManager().bindTexture(ModResources.BLACK_LEG);
/* 175 */         GuiUtils.drawTexturedModalRect(posX - 113, posY - 115, 0, 0, 256, 256, 1.0F);
/* 176 */         drawCategory("Black Leg", posX + 268, posY + 190, 3.0D);
/*     */       } 
/*     */     }
/*     */     
/* 180 */     this.renderTick++;
/* 181 */     super.render(x, y, f);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() {
/* 187 */     int posX = (this.width - 256) / 2;
/* 188 */     int posY = (this.height - 256) / 2;
/*     */ 
/*     */     
/* 191 */     int posY2 = posY + 65;
/* 192 */     String msg = (new TranslationTextComponent(ModI18n.FACTION_NAME, new Object[0])).getFormattedText();
/* 193 */     this.factionButton = new TexturedIconButton(ModResources.BLANK2_SIMPLE, posX - 58, posY2, 90, 36, msg, btn -> {
/*     */           this.page = 0;
/*     */           
/*     */           resetButtonState();
/*     */           this.factionButton.setIsPressed(true);
/*     */         });
/* 199 */     this.factionButton = this.factionButton.setTextureInfo(posX - 58, posY + 65, 90, 60).setTextInfo(posX + 50, posY + 169, 1.75D);
/* 200 */     addButton((Widget)this.factionButton);
/* 201 */     posY2 += 45;
/*     */     
/* 203 */     msg = (new TranslationTextComponent(ModI18n.RACE_NAME, new Object[0])).getFormattedText();
/* 204 */     this.raceButton = new TexturedIconButton(ModResources.BLANK2_SIMPLE, posX - 58, posY2, 90, 36, msg, btn -> {
/*     */           this.page = 1;
/*     */           
/*     */           resetButtonState();
/*     */           this.raceButton.setIsPressed(true);
/*     */         });
/* 210 */     this.raceButton = this.raceButton.setTextureInfo(posX - 58, posY + 110, 90, 60).setTextInfo(posX + 50, posY + 214, 1.75D);
/* 211 */     if (!this.hasRandomizedRace) {
/*     */       
/* 213 */       addButton((Widget)this.raceButton);
/* 214 */       posY2 += 45;
/*     */     } 
/*     */     
/* 217 */     msg = (new TranslationTextComponent(ModI18n.STYLE_NAME, new Object[0])).getFormattedText();
/* 218 */     this.styleButton = new TexturedIconButton(ModResources.BLANK2_SIMPLE, posX - 58, posY2, 90, 36, msg, btn -> {
/*     */           this.page = 2;
/*     */           
/*     */           resetButtonState();
/*     */           this.styleButton.setIsPressed(true);
/*     */         });
/* 224 */     this.styleButton = this.styleButton.setTextureInfo(posX - 58, posY2, 90, 60).setTextInfo(posX + 50, posY2 + 102, 1.75D);
/* 225 */     addButton((Widget)this.styleButton);
/*     */ 
/*     */     
/* 228 */     TexturedIconButton nextButton = new TexturedIconButton(ModResources.BIG_WOOD_BUTTON_RIGHT, posX + 215, posY + 80, 24, 100, "", btn -> increaseSelectedPage());
/*     */ 
/*     */ 
/*     */     
/* 232 */     nextButton = nextButton.setTextureInfo(posX + 211, posY + 66, 32, 128);
/* 233 */     addButton((Widget)nextButton);
/*     */     
/* 235 */     TexturedIconButton prevButton = new TexturedIconButton(ModResources.BIG_WOOD_BUTTON_LEFT, posX + 45, posY + 80, 24, 100, "", btn -> decreaseSelectedPage());
/*     */ 
/*     */ 
/*     */     
/* 239 */     prevButton = prevButton.setTextureInfo(posX + 41, posY + 66, 32, 128);
/* 240 */     addButton((Widget)prevButton);
/*     */ 
/*     */     
/* 243 */     TexturedIconButton finishButton = new TexturedIconButton(ModResources.BLANK2_SIMPLE, posX + 100, posY + 200, 90, 36, (new TranslationTextComponent(ModI18n.GUI_FINISH, new Object[0])).getFormattedText(), btn -> completeCreation());
/* 244 */     finishButton = finishButton.setTextureInfo(posX + 100, posY + 200, 90, 60).setTextInfo(posX + 217, posY + 304, 1.75D);
/* 245 */     addButton((Widget)finishButton);
/*     */   }
/*     */ 
/*     */   
/*     */   private void resetButtonState() {
/* 250 */     this.factionButton.setIsPressed(false);
/* 251 */     this.raceButton.setIsPressed(false);
/* 252 */     this.styleButton.setIsPressed(false);
/*     */   }
/*     */ 
/*     */   
/*     */   private void completeCreation() {
/* 257 */     Minecraft.getInstance().displayGuiScreen(null);
/* 258 */     WyNetwork.sendToServer(new CFinishCCPacket(getSelectedFactionId(), getSelectedRaceId(), getSelectedStyleId()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/* 264 */     if (this.page == 0)
/* 265 */       this.maxOpt = this.FACTIONS.length; 
/* 266 */     if (this.page == 1)
/* 267 */       this.maxOpt = this.RACES.length; 
/* 268 */     if (this.page == 2) {
/* 269 */       this.maxOpt = this.STYLES.length;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean doesGuiPauseGame() {
/* 274 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawCategory(String text, int posX, int posY, double scale) {
/* 279 */     GL11.glPushMatrix();
/*     */     
/* 281 */     GL11.glTranslated(posX, posY, 2.0D);
/* 282 */     GL11.glTranslated(128.0D, 128.0D, 0.0D);
/* 283 */     GL11.glScaled(scale, scale, scale);
/* 284 */     GL11.glTranslated(-128.0D, -128.0D, 0.0D);
/*     */     
/* 286 */     WyHelper.drawStringWithBorder(this.font, text, -this.font.getStringWidth(text) / 2, 0, WyHelper.hexToRGB("#FFFFFF").getRGB());
/*     */     
/* 288 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSelectedFactionId() {
/* 293 */     return this.options[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSelectedRaceId() {
/* 298 */     return this.options[1];
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSelectedStyleId() {
/* 303 */     return this.options[2];
/*     */   }
/*     */ 
/*     */   
/*     */   public void increaseSelectedPage() {
/* 308 */     if (this.options[this.page] + 1 < this.maxOpt) {
/* 309 */       this.options[this.page] = this.options[this.page] + 1;
/*     */     } else {
/* 311 */       this.options[this.page] = 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void decreaseSelectedPage() {
/* 316 */     if (this.options[this.page] - 1 > -1) {
/* 317 */       this.options[this.page] = this.options[this.page] - 1;
/*     */     } else {
/* 319 */       this.options[this.page] = this.maxOpt - 1;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void open(boolean hasRandomizedRace) {
/* 324 */     Minecraft.getInstance().displayGuiScreen(new CharacterCreatorScreen(hasRandomizedRace));
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\screens\CharacterCreatorScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */