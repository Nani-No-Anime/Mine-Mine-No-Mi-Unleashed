package xyz.pixelatedw.mineminenomi.screens.extra;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.client.gui.ScrollPanel;
import net.minecraftforge.fml.client.gui.GuiUtils;
import org.lwjgl.opengl.GL11;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.packets.client.quest.CUpdateQuestStatePacket;
import xyz.pixelatedw.mineminenomi.screens.TrainerScreen;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;




public class AvailableQuestsListScreenPanel
  extends ScrollPanel
{
  private TrainerScreen parent;
  private IQuestData props;
  private List<Quest> availableQuests = new ArrayList<>();
  
  private static final int ENTRY_HEIGHT = 20;
  private FontRenderer font;
  
  public AvailableQuestsListScreenPanel(TrainerScreen parent, IQuestData abilityProps, Quest[] quests) {
    super(parent.getMinecraft(), 200, 180, parent.height / 2 - 110, parent.width / 2 - 190);
    this.parent = parent;
    this.props = abilityProps;
    this.font = (parent.getMinecraft()).fontRenderer;
    
    updateAvailableQuests(quests);
    
    this.scrollDistance = -10.0F;
  }

  
  public void updateAvailableQuests(List<Quest> quests) {
    Quest[] arr = new Quest[quests.size()];
    Quest[] questsArray = quests.<Quest>toArray(arr);
    updateAvailableQuests(questsArray);
  }

  
  public void updateAvailableQuests(Quest[] quests) {
    this.availableQuests.clear();
    for (int i = 0; i <= quests.length - 1; i++) {
      
      Quest quest = quests[i];
      boolean exists = (quest != null);
      boolean isNotFinished = (exists && !this.props.hasFinishedQuest(quest));
      boolean isNotInProgress = (exists && (this.props.getInProgressQuest(quest) == null || (this.props.getInProgressQuest(quest) != null && this.props.getInProgressQuest(quest).isComplete())));
      
      if (isNotFinished && isNotInProgress)
      {
        this.availableQuests.add(quests[i]);
      }
    } 
  }


  
  public boolean mouseReleased(double p_mouseReleased_1_, double p_mouseReleased_3_, int p_mouseReleased_5_) {
    return true;
  }


  
  protected int getContentHeight() {
    return (int)(this.availableQuests.size() * 55.0D - 2.0D);
  }


  
  protected int getScrollAmount() {
    return 12;
  }


  
  public void render(int mouseX, int mouseY, float partialTicks) {
    Tessellator tess = Tessellator.getInstance();
    
    double scale = this.parent.getMinecraft().getMainWindow().getGuiScaleFactor();
    GL11.glEnable(3089);
    GL11.glScissor((int)(this.left * scale), (int)(this.parent.getMinecraft().getMainWindow().getFramebufferHeight() - this.bottom * scale), (int)(this.width * scale), (int)(this.height * scale));
    
    int baseY = this.top + 4 - (int)this.scrollDistance;
    drawPanel(this.right, baseY, tess, mouseX, mouseY);
    
    GL11.glDisable(3089);
  }




  
  protected void drawPanel(int entryRight, int relativeY, Tessellator tess, int mouseX, int mouseY) {
    for (Quest quest : this.availableQuests) {
      
      if (quest == null) {
        continue;
      }
      float y = relativeY;
      float x = (this.parent.width / 2 - 109 + 40);
      
      String formattedQuestName = I18n.format("quest.mineminenomi." + WyHelper.getResourceName(quest.getId()), new Object[0]);
      String questColor = "#FFFFFF";
      
      Quest inProgressQuest = this.props.getInProgressQuest(quest);
      if (inProgressQuest != null) {
        
        if (isMouseOverQuest(mouseX, mouseY, inProgressQuest) && !inProgressQuest.isComplete())
        {
          formattedQuestName = (new TranslationTextComponent(ModI18n.TRAINER_NO_QUESTS_AVAILABLE, new Object[0])).getFormattedText();
        }
        
        if (inProgressQuest.isComplete())
        {
          questColor = "#00FF55";
        }
      } 
      
      if (quest.isLocked(this.props))
      {
        questColor = "#505050";
      }
      
      if (this.parent.isAnimationComplete() && isMouseOverQuest(mouseX, mouseY, quest))
      {
        RenderSystem.color3f(0.8F, 0.8F, 0.8F);
      }
      
      RenderSystem.pushMatrix();
      
      Minecraft.getInstance().getTextureManager().bindTexture(ModResources.SCROLL);
      double scale = 0.5D;
      RenderSystem.translated((x - 180.0F), (y - 196.0F), 0.0D);
      RenderSystem.translated(256.0D, 256.0D, 0.0D);
      
      RenderSystem.scaled(scale * 1.5D, scale * 0.6D, 0.0D);
      RenderSystem.translated(-256.0D, -256.0D, 0.0D);

      
      GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, 1.0F);
      
      RenderSystem.popMatrix();
      
      if (this.parent.isAnimationComplete()) {
        RenderSystem.color3f(1.0F, 1.0F, 1.0F);
      }
      if (this.font.getStringWidth(formattedQuestName) > 140) {
        
        RenderSystem.pushMatrix();
        
        List<String> splittedText = WyHelper.splitString(this.font, formattedQuestName, (int)x - 80, 140);
        RenderSystem.translated(0.0D, -((splittedText.size() - 1) * 5), 0.0D);
        for (String string : splittedText) {
          
          WyHelper.drawStringWithBorder(this.font, string, (int)x - 80, (int)y + 16, WyHelper.hexToRGB(questColor).getRGB());
          y += 10.0F;
        } 
        
        RenderSystem.popMatrix();
      }
      else {
        
        WyHelper.drawStringWithBorder(this.font, formattedQuestName, (int)x - 80, (int)y + 16, WyHelper.hexToRGB(questColor).getRGB());
      } 
      
      relativeY = (int)(relativeY + 55.0D);
    } 
  }


  
  public boolean mouseClicked(double mouseX, double mouseY, int button) {
    Quest quest = findQuestEntry((int)mouseX, (int)mouseY);
    Quest inProgressQuest = this.props.getInProgressQuest(quest);
    
    if (button != 0) {
      return false;
    }
    if (inProgressQuest != null && inProgressQuest.isComplete()) {
      
      this.props.addFinishedQuest(inProgressQuest);
      this.props.removeInProgressQuest(inProgressQuest);
      WyNetwork.sendToServer(new CUpdateQuestStatePacket(inProgressQuest));
    }
    else if (quest != null && inProgressQuest == null && !quest.isLocked(this.props)) {
      
      this.props.addInProgressQuest(quest);
      WyNetwork.sendToServer(new CUpdateQuestStatePacket(quest));
    } 
    
    updateAvailableQuests(this.availableQuests);
    
    return super.mouseClicked(mouseX, mouseY, button);
  }

  
  public boolean isMouseOverQuest(double mouseX, double mouseY, Quest overQuest) {
    Quest quest = findQuestEntry((int)mouseX, (int)mouseY);
    
    if (quest != null && quest.equals(overQuest))
    {
      return isMouseOver(mouseX, mouseY);
    }
    
    return false;
  }

  
  private Quest findQuestEntry(int mouseX, int mouseY) {
    double offset = ((mouseY - this.top) + this.scrollDistance);
    boolean isHovered = (mouseX >= this.left && mouseY >= this.top && mouseX < this.left + this.width - 5 && mouseY < this.top + this.height);
    
    if (offset <= 0.0D || !isHovered) {
      return null;
    }
    int lineIdx = (int)(offset / 55.0D);
    if (lineIdx >= this.availableQuests.size()) {
      return null;
    }
    Quest quest = this.availableQuests.get(lineIdx);
    if (quest != null)
    {
      return quest.create();
    }
    
    return null;
  }
}


