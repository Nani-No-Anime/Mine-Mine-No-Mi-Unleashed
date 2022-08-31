/*     */ package xyz.pixelatedw.mineminenomi.screens;
/*     */ 
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.screen.Screen;
/*     */ import net.minecraft.client.gui.widget.Widget;
/*     */ import net.minecraft.client.gui.widget.button.Button;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.EnchantmentNameParts;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.client.gui.GuiUtils;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.quest.CAbandonQuestPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.quest.CStartObjectiveEventPacket;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.TexturedIconButton;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class QuestsTrackerScreen
/*     */   extends Screen {
/*     */   private PlayerEntity player;
/*     */   private IQuestData qprops;
/*  40 */   private int questIndex = 0;
/*  41 */   private List<String> hiddenTexts = new ArrayList<>();
/*  42 */   private Quest currentQuest = null;
/*     */   
/*     */   private List<Quest> availableQuests;
/*     */   
/*     */   public QuestsTrackerScreen(PlayerEntity player) {
/*  47 */     super((ITextComponent)new StringTextComponent(""));
/*  48 */     this.player = player;
/*  49 */     this.qprops = QuestDataCapability.get(player);
/*  50 */     this.availableQuests = (List<Quest>)Arrays.<Quest>asList(this.qprops.getInProgressQuests()).stream().filter(quest -> (quest != null)).collect(Collectors.toList());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(int x, int y, float f) {
/*  56 */     renderBackground();
/*  57 */     RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  59 */     int posX = this.width / 2;
/*  60 */     int posY = this.height / 2;
/*     */     
/*  62 */     Minecraft.getInstance().getTextureManager().bindTexture(ModResources.BLANK);
/*  63 */     RenderSystem.pushMatrix();
/*     */     
/*  65 */     double scale = 1.1D;
/*  66 */     RenderSystem.translated((posX - 35), (posY + 10), 0.0D);
/*  67 */     RenderSystem.translated(256.0D, 256.0D, 0.0D);
/*     */     
/*  69 */     RenderSystem.scaled(scale * 1.5D, scale * 1.4D, 0.0D);
/*  70 */     RenderSystem.translated(-256.0D, -256.0D, 0.0D);
/*     */ 
/*     */ 
/*     */     
/*  74 */     GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, 1.0F);
/*     */     
/*  76 */     RenderSystem.translated(-30.0D, 50.0D, 0.0D);
/*  77 */     RenderSystem.translated(256.0D, 256.0D, 0.0D);
/*     */     
/*  79 */     RenderSystem.scaled(scale * 0.7D, scale * 0.9D, 0.0D);
/*  80 */     RenderSystem.translated(-256.0D, -256.0D, 0.0D);
/*     */     
/*  82 */     RenderSystem.popMatrix();
/*     */     
/*  84 */     String currentQuestName = (this.currentQuest != null) ? (new TranslationTextComponent(String.format("quest.mineminenomi.%s", new Object[] { this.currentQuest.getId() }), new Object[0])).getFormattedText() : "None";
/*  85 */     double currentQuestProgress = (this.currentQuest != null) ? (this.currentQuest.getProgress() * 100.0D) : -1.0D;
/*     */     
/*  87 */     RenderSystem.translated(0.0D, 10.0D, 0.0D);
/*     */     
/*  89 */     if (this.currentQuest != null) {
/*     */ 
/*     */       
/*  92 */       RenderSystem.pushMatrix();
/*     */       
/*  94 */       RenderSystem.translated((posX + 150), (posY - 110), 0.0D);
/*     */       
/*  96 */       String pageNumber = (this.questIndex + 1) + "/" + this.availableQuests.size();
/*  97 */       WyHelper.drawStringWithBorder(this.font, pageNumber, 0, 0, WyHelper.hexToRGB("#FFFFFF").getRGB());
/*     */       
/*  99 */       RenderSystem.popMatrix();
/*     */ 
/*     */       
/* 102 */       RenderSystem.pushMatrix();
/*     */       
/* 104 */       double d = 1.4D;
/* 105 */       RenderSystem.translated((posX + 100), (posY + 10), 0.0D);
/* 106 */       RenderSystem.translated(256.0D, 256.0D, 0.0D);
/*     */       
/* 108 */       RenderSystem.scaled(d, d, 0.0D);
/* 109 */       RenderSystem.translated(-256.0D, -256.0D, 0.0D);
/*     */       
/* 111 */       WyHelper.drawStringWithBorder(this.font, currentQuestName, -this.font.getStringWidth(currentQuestName) / 2, 0, WyHelper.hexToRGB("#FFFFFF").getRGB());
/*     */       
/* 113 */       RenderSystem.popMatrix();
/*     */ 
/*     */       
/* 116 */       if (currentQuestProgress != -1.0D) {
/*     */         
/* 118 */         String textColor = "#FFFFFF";
/* 119 */         if (this.currentQuest.isComplete())
/* 120 */           textColor = "#00FF55"; 
/* 121 */         String progress = TextFormatting.BOLD + (new TranslationTextComponent(ModI18n.GUI_QUEST_PROGRESS, new Object[0])).getFormattedText() + " : " + String.format("%.1f", new Object[] { Double.valueOf(currentQuestProgress) }) + "%";
/* 122 */         WyHelper.drawStringWithBorder(this.font, progress, posX - 120, posY - 65, WyHelper.hexToRGB(textColor).getRGB());
/*     */       } 
/*     */ 
/*     */       
/* 126 */       RenderSystem.pushMatrix();
/*     */       
/* 128 */       List<Objective> avilableObjectives = (List<Objective>)this.currentQuest.getObjectives().stream().limit(5L).collect(Collectors.toList());
/*     */       
/* 130 */       int yOffset = -20;
/* 131 */       int i = 0;
/* 132 */       for (Objective obj : avilableObjectives) {
/*     */         
/* 134 */         String objectiveName = obj.getLocalizedTitle();
/* 135 */         String progress = "";
/* 136 */         double objectiveProgress = obj.getProgress() / obj.getMaxProgress() * 100.0D;
/* 137 */         List<Objective> hiddenObjs = (List<Objective>)avilableObjectives.stream().filter(o -> o.isHidden()).collect(Collectors.toList());
/* 138 */         yOffset += 20;
/*     */         
/* 140 */         String textColor = "#FFFFFF";
/* 141 */         if (obj.isComplete())
/* 142 */           textColor = "#00FF00"; 
/* 143 */         if (obj.isLocked()) {
/* 144 */           textColor = "#505050";
/*     */         } else {
/* 146 */           progress = " - " + String.format("%.1f", new Object[] { Double.valueOf(objectiveProgress) }) + "%";
/*     */         } 
/* 148 */         if (obj.isHidden()) {
/*     */           
/* 150 */           FontRenderer galacticFont = this.minecraft.getFontResourceManager().getFontRenderer(Minecraft.standardGalacticFontRenderer);
/* 151 */           WyHelper.drawStringWithBorder(this.font, '\u2022'+" ", posX - 130, posY - 45 + yOffset, WyHelper.hexToRGB(textColor).getRGB());
/* 152 */           if (hiddenObjs.size() > 0) {
/* 153 */             WyHelper.drawStringWithBorder(galacticFont, this.hiddenTexts.get(hiddenObjs.indexOf(obj)), posX - 123, posY - 45 + yOffset, WyHelper.hexToRGB(textColor).getRGB());
/*     */           }
/*     */         } else {
/*     */           
/* 157 */           String optional = obj.isOptional() ? "(Optional) " : "";
/* 158 */           objectiveName = '\u2022'+" " + optional + "" + objectiveName + " " + progress;
/* 159 */           List<String> splitText = WyHelper.splitString(this.font, objectiveName, posX, 210);
/* 160 */           for (int j = 0; j < splitText.size(); j++)
/*     */           {
/* 162 */             WyHelper.drawStringWithBorder(this.font, splitText.get(j), posX - 130, posY - 45 + yOffset + j * 12, WyHelper.hexToRGB(textColor).getRGB());
/*     */           }
/* 164 */           yOffset += splitText.size() * 8;
/*     */         } 
/* 166 */         i++;
/*     */       } 
/*     */       
/* 169 */       if (i == 0) {
/* 170 */         WyHelper.drawStringWithBorder(this.font, (new TranslationTextComponent(ModI18n.TRAINER_NO_OBJECTIVES_LEFT, new Object[0])).getFormattedText(), posX - 120, posY - 20 + yOffset, WyHelper.hexToRGB("#FFFFFF").getRGB());
/*     */       }
/* 172 */       RenderSystem.popMatrix();
/*     */       
/* 174 */       RenderSystem.translated(0.0D, 20.0D, 0.0D);
/*     */     } 
/*     */     
/* 177 */     super.render(x, y, f);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() {
/* 183 */     this.children.clear();
/* 184 */     this.buttons.clear();
/*     */     
/* 186 */     int posX = (this.width - 256) / 2;
/* 187 */     int posY = (this.height - 256) / 2;
/*     */ 
/*     */     
/*     */     try {
/* 191 */       this.currentQuest = this.qprops.getInProgressQuests()[this.questIndex];
/*     */     }
/* 193 */     catch (Exception e) {
/*     */       
/* 195 */       if ((this.qprops.getInProgressQuests()).length > 0) {
/*     */         
/* 197 */         this.currentQuest = this.qprops.getInProgressQuests()[0];
/* 198 */         WyDebug.debug(String.format("\n[ArrayOutOfBounds] \n Max possible index is : %s \n But the index requested is : %s", new Object[] { Integer.valueOf((this.qprops.getInProgressQuests()).length - 1), Integer.valueOf(this.questIndex) }));
/*     */       } else {
/*     */         
/* 201 */         this.currentQuest = null;
/* 202 */       }  this.questIndex = 0;
/* 203 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 206 */     if (this.currentQuest == null) {
/*     */       return;
/*     */     }
/* 209 */     this.hiddenTexts.clear();
/* 210 */     for (Objective obj : this.currentQuest.getObjectives()) {
/*     */       
/* 212 */       if (obj.isHidden())
/*     */       {
/* 214 */         this.hiddenTexts.add(EnchantmentNameParts.getInstance().generateNewRandomName((Minecraft.getInstance()).fontRenderer, obj.getTitle().length() * 2));
/*     */       }
/*     */     } 
/*     */     
/* 218 */     TexturedIconButton nextButton = new TexturedIconButton(ModResources.BIG_WOOD_BUTTON_RIGHT, posX + 285, posY + 80, 24, 100, "", btn -> {
/*     */           if (this.questIndex + 1 < this.availableQuests.size()) {
/*     */             this.questIndex++;
/*     */           } else {
/*     */             this.questIndex = 0;
/*     */           } 
/*     */           init();
/*     */         });
/* 226 */     nextButton = nextButton.setTextureInfo(posX + 280, posY + 35, 32, 128);
/* 227 */     if (this.availableQuests.size() <= 1)
/* 228 */       nextButton.visible = false; 
/* 229 */     addButton((Widget)nextButton);
/*     */     
/* 231 */     TexturedIconButton prevButton = new TexturedIconButton(ModResources.BIG_WOOD_BUTTON_LEFT, posX - 55, posY + 80, 24, 100, "", btn -> {
/*     */           if (this.questIndex > 0) {
/*     */             this.questIndex--;
/*     */           } else {
/*     */             this.questIndex = this.availableQuests.size() - 1;
/*     */           } 
/*     */           init();
/*     */         });
/* 239 */     prevButton = prevButton.setTextureInfo(posX - 58, posY + 35, 32, 128);
/* 240 */     if (this.availableQuests.size() <= 1)
/* 241 */       prevButton.visible = false; 
/* 242 */     addButton((Widget)prevButton);
/*     */     
/* 244 */     TexturedIconButton abandonQuestButton = new TexturedIconButton(ModResources.BLANK2_SIMPLE, posX - 40, posY + 210, 60, 30, "Abandon", btn -> {
/*     */           this.player.closeScreen();
/*     */           if (this.currentQuest != null) {
/*     */             WyNetwork.sendToServer(new CAbandonQuestPacket(this.qprops.getInProgressQuestSlot(this.currentQuest)));
/*     */           }
/*     */         });
/* 250 */     abandonQuestButton = abandonQuestButton.setTextureInfo(posX - 40, posY + 180, 60, 40).setTextInfo(posX - 31, posY + 189, 1.0D);
/* 251 */     addButton((Widget)abandonQuestButton);
/*     */     
/* 253 */     List<Objective> avilableObjectives = (List<Objective>)this.currentQuest.getObjectives().stream().limit(5L).collect(Collectors.toList());
/*     */     
/* 255 */     int yOffset = -20;
/* 256 */     int objId = -1;
/* 257 */     for (Objective obj : avilableObjectives) {
/*     */       
/* 259 */       yOffset += 30;
/* 260 */       objId++;
/*     */       
/* 262 */       if (!obj.hasEvent()) {
/*     */         continue;
/*     */       }
/* 265 */       String startText = obj.hasStartedEvent() ? "Restart Event" : "Start Event";
/*     */       
/* 267 */       int objId2 = objId;
/* 268 */       TexturedIconButton startEventButton = new TexturedIconButton(ModResources.BLANK2_SIMPLE, posX + 220, posY + 75 + yOffset, 60, 30, startText, btn -> {
/*     */             this.player.closeScreen();
/*     */             if (this.currentQuest != null) {
/*     */               WyNetwork.sendToServer(new CStartObjectiveEventPacket(this.qprops.getInProgressQuestSlot(this.currentQuest), objId2));
/*     */             }
/*     */           });
/* 274 */       int xOffset = obj.hasStartedEvent() ? 230 : 236;
/* 275 */       startEventButton = startEventButton.setTextureInfo(posX + 220, posY + 45 + yOffset, 60, 40).setTextInfo(posX + xOffset, posY + 56 + yOffset, 1.0D);
/* 276 */       if (obj.isLocked())
/* 277 */         startEventButton.visible = false; 
/* 278 */       addButton((Widget)startEventButton);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPauseScreen() {
/* 285 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\screens\QuestsTrackerScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */