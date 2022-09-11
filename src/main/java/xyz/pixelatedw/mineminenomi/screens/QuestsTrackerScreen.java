package xyz.pixelatedw.mineminenomi.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.EnchantmentNameParts;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.gui.GuiUtils;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.packets.client.quest.CAbandonQuestPacket;
import xyz.pixelatedw.mineminenomi.packets.client.quest.CStartObjectiveEventPacket;
import xyz.pixelatedw.mineminenomi.screens.extra.TexturedIconButton;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

@OnlyIn(Dist.CLIENT)
public class QuestsTrackerScreen
  extends Screen {
  private PlayerEntity player;
  private IQuestData qprops;
  private int questIndex = 0;
  private List<String> hiddenTexts = new ArrayList<>();
  private Quest currentQuest = null;
  
  private List<Quest> availableQuests;
  
  public QuestsTrackerScreen(PlayerEntity player) {
    super((ITextComponent)new StringTextComponent(""));
    this.player = player;
    this.qprops = QuestDataCapability.get(player);
    this.availableQuests = (List<Quest>)Arrays.<Quest>asList(this.qprops.getInProgressQuests()).stream().filter(quest -> (quest != null)).collect(Collectors.toList());
  }


  
  public void render(int x, int y, float f) {
    renderBackground();
    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    
    int posX = this.width / 2;
    int posY = this.height / 2;
    
    Minecraft.getInstance().getTextureManager().bindTexture(ModResources.BLANK);
    RenderSystem.pushMatrix();
    
    double scale = 1.1D;
    RenderSystem.translated((posX - 35), (posY + 10), 0.0D);
    RenderSystem.translated(256.0D, 256.0D, 0.0D);
    
    RenderSystem.scaled(scale * 1.5D, scale * 1.4D, 0.0D);
    RenderSystem.translated(-256.0D, -256.0D, 0.0D);


    
    GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, 1.0F);
    
    RenderSystem.translated(-30.0D, 50.0D, 0.0D);
    RenderSystem.translated(256.0D, 256.0D, 0.0D);
    
    RenderSystem.scaled(scale * 0.7D, scale * 0.9D, 0.0D);
    RenderSystem.translated(-256.0D, -256.0D, 0.0D);
    
    RenderSystem.popMatrix();
    
    String currentQuestName = (this.currentQuest != null) ? (new TranslationTextComponent(String.format("quest.mineminenomi.%s", new Object[] { this.currentQuest.getId() }), new Object[0])).getFormattedText() : "None";
    double currentQuestProgress = (this.currentQuest != null) ? (this.currentQuest.getProgress() * 100.0D) : -1.0D;
    
    RenderSystem.translated(0.0D, 10.0D, 0.0D);
    
    if (this.currentQuest != null) {

      
      RenderSystem.pushMatrix();
      
      RenderSystem.translated((posX + 150), (posY - 110), 0.0D);
      
      String pageNumber = (this.questIndex + 1) + "/" + this.availableQuests.size();
      WyHelper.drawStringWithBorder(this.font, pageNumber, 0, 0, WyHelper.hexToRGB("#FFFFFF").getRGB());
      
      RenderSystem.popMatrix();

      
      RenderSystem.pushMatrix();
      
      double d = 1.4D;
      RenderSystem.translated((posX + 100), (posY + 10), 0.0D);
      RenderSystem.translated(256.0D, 256.0D, 0.0D);
      
      RenderSystem.scaled(d, d, 0.0D);
      RenderSystem.translated(-256.0D, -256.0D, 0.0D);
      
      WyHelper.drawStringWithBorder(this.font, currentQuestName, -this.font.getStringWidth(currentQuestName) / 2, 0, WyHelper.hexToRGB("#FFFFFF").getRGB());
      
      RenderSystem.popMatrix();

      
      if (currentQuestProgress != -1.0D) {
        
        String textColor = "#FFFFFF";
        if (this.currentQuest.isComplete())
          textColor = "#00FF55"; 
        String progress = TextFormatting.BOLD + (new TranslationTextComponent(ModI18n.GUI_QUEST_PROGRESS, new Object[0])).getFormattedText() + " : " + String.format("%.1f", new Object[] { Double.valueOf(currentQuestProgress) }) + "%";
        WyHelper.drawStringWithBorder(this.font, progress, posX - 120, posY - 65, WyHelper.hexToRGB(textColor).getRGB());
      } 

      
      RenderSystem.pushMatrix();
      
      List<Objective> avilableObjectives = (List<Objective>)this.currentQuest.getObjectives().stream().limit(5L).collect(Collectors.toList());
      
      int yOffset = -20;
      int i = 0;
      for (Objective obj : avilableObjectives) {
        
        String objectiveName = obj.getLocalizedTitle();
        String progress = "";
        double objectiveProgress = obj.getProgress() / obj.getMaxProgress() * 100.0D;
        List<Objective> hiddenObjs = (List<Objective>)avilableObjectives.stream().filter(o -> o.isHidden()).collect(Collectors.toList());
        yOffset += 20;
        
        String textColor = "#FFFFFF";
        if (obj.isComplete())
          textColor = "#00FF00"; 
        if (obj.isLocked()) {
          textColor = "#505050";
        } else {
          progress = " - " + String.format("%.1f", new Object[] { Double.valueOf(objectiveProgress) }) + "%";
        } 
        if (obj.isHidden()) {
          
          FontRenderer galacticFont = this.minecraft.getFontResourceManager().getFontRenderer(Minecraft.standardGalacticFontRenderer);
          WyHelper.drawStringWithBorder(this.font, '\u2022'+" ", posX - 130, posY - 45 + yOffset, WyHelper.hexToRGB(textColor).getRGB());
          if (hiddenObjs.size() > 0) {
            WyHelper.drawStringWithBorder(galacticFont, this.hiddenTexts.get(hiddenObjs.indexOf(obj)), posX - 123, posY - 45 + yOffset, WyHelper.hexToRGB(textColor).getRGB());
          }
        } else {
          
          String optional = obj.isOptional() ? "(Optional) " : "";
          objectiveName = '\u2022'+" " + optional + "" + objectiveName + " " + progress;
          List<String> splitText = WyHelper.splitString(this.font, objectiveName, posX, 210);
          for (int j = 0; j < splitText.size(); j++)
          {
            WyHelper.drawStringWithBorder(this.font, splitText.get(j), posX - 130, posY - 45 + yOffset + j * 12, WyHelper.hexToRGB(textColor).getRGB());
          }
          yOffset += splitText.size() * 8;
        } 
        i++;
      } 
      
      if (i == 0) {
        WyHelper.drawStringWithBorder(this.font, (new TranslationTextComponent(ModI18n.TRAINER_NO_OBJECTIVES_LEFT, new Object[0])).getFormattedText(), posX - 120, posY - 20 + yOffset, WyHelper.hexToRGB("#FFFFFF").getRGB());
      }
      RenderSystem.popMatrix();
      
      RenderSystem.translated(0.0D, 20.0D, 0.0D);
    } 
    
    super.render(x, y, f);
  }


  
  public void init() {
    this.children.clear();
    this.buttons.clear();
    
    int posX = (this.width - 256) / 2;
    int posY = (this.height - 256) / 2;

    
    try {
      this.currentQuest = this.qprops.getInProgressQuests()[this.questIndex];
    }
    catch (Exception e) {
      
      if ((this.qprops.getInProgressQuests()).length > 0) {
        
        this.currentQuest = this.qprops.getInProgressQuests()[0];
        WyDebug.debug(String.format("\n[ArrayOutOfBounds] \n Max possible index is : %s \n But the index requested is : %s", new Object[] { Integer.valueOf((this.qprops.getInProgressQuests()).length - 1), Integer.valueOf(this.questIndex) }));
      } else {
        
        this.currentQuest = null;
      }  this.questIndex = 0;
      e.printStackTrace();
    } 
    
    if (this.currentQuest == null) {
      return;
    }
    this.hiddenTexts.clear();
    for (Objective obj : this.currentQuest.getObjectives()) {
      
      if (obj.isHidden())
      {
        this.hiddenTexts.add(EnchantmentNameParts.getInstance().generateNewRandomName((Minecraft.getInstance()).fontRenderer, obj.getTitle().length() * 2));
      }
    } 
    
    TexturedIconButton nextButton = new TexturedIconButton(ModResources.BIG_WOOD_BUTTON_RIGHT, posX + 285, posY + 80, 24, 100, "", btn -> {
          if (this.questIndex + 1 < this.availableQuests.size()) {
            this.questIndex++;
          } else {
            this.questIndex = 0;
          } 
          init();
        });
    nextButton = nextButton.setTextureInfo(posX + 280, posY + 35, 32, 128);
    if (this.availableQuests.size() <= 1)
      nextButton.visible = false; 
    addButton((Widget)nextButton);
    
    TexturedIconButton prevButton = new TexturedIconButton(ModResources.BIG_WOOD_BUTTON_LEFT, posX - 55, posY + 80, 24, 100, "", btn -> {
          if (this.questIndex > 0) {
            this.questIndex--;
          } else {
            this.questIndex = this.availableQuests.size() - 1;
          } 
          init();
        });
    prevButton = prevButton.setTextureInfo(posX - 58, posY + 35, 32, 128);
    if (this.availableQuests.size() <= 1)
      prevButton.visible = false; 
    addButton((Widget)prevButton);
    
    TexturedIconButton abandonQuestButton = new TexturedIconButton(ModResources.BLANK2_SIMPLE, posX - 40, posY + 210, 60, 30, "Abandon", btn -> {
          this.player.closeScreen();
          if (this.currentQuest != null) {
            WyNetwork.sendToServer(new CAbandonQuestPacket(this.qprops.getInProgressQuestSlot(this.currentQuest)));
          }
        });
    abandonQuestButton = abandonQuestButton.setTextureInfo(posX - 40, posY + 180, 60, 40).setTextInfo(posX - 31, posY + 189, 1.0D);
    addButton((Widget)abandonQuestButton);
    
    List<Objective> avilableObjectives = (List<Objective>)this.currentQuest.getObjectives().stream().limit(5L).collect(Collectors.toList());
    
    int yOffset = -20;
    int objId = -1;
    for (Objective obj : avilableObjectives) {
      
      yOffset += 30;
      objId++;
      
      if (!obj.hasEvent()) {
        continue;
      }
      String startText = obj.hasStartedEvent() ? "Restart Event" : "Start Event";
      
      int objId2 = objId;
      TexturedIconButton startEventButton = new TexturedIconButton(ModResources.BLANK2_SIMPLE, posX + 220, posY + 75 + yOffset, 60, 30, startText, btn -> {
            this.player.closeScreen();
            if (this.currentQuest != null) {
              WyNetwork.sendToServer(new CStartObjectiveEventPacket(this.qprops.getInProgressQuestSlot(this.currentQuest), objId2));
            }
          });
      int xOffset = obj.hasStartedEvent() ? 230 : 236;
      startEventButton = startEventButton.setTextureInfo(posX + 220, posY + 45 + yOffset, 60, 40).setTextInfo(posX + xOffset, posY + 56 + yOffset, 1.0D);
      if (obj.isLocked())
        startEventButton.visible = false; 
      addButton((Widget)startEventButton);
    } 
  }


  
  public boolean isPauseScreen() {
    return false;
  }
}


