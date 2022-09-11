package xyz.pixelatedw.mineminenomi.screens;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.abilities.haki.KenbunshokuHakiAuraAbility;
import xyz.pixelatedw.mineminenomi.api.entities.TrainerEntity;
import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
import xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.IHakiTrainer;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.screens.extra.AvailableQuestsListScreenPanel;
import xyz.pixelatedw.mineminenomi.screens.extra.NewButton;
import xyz.pixelatedw.mineminenomi.screens.extra.SequencedString;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

@OnlyIn(Dist.CLIENT)
public class TrainerScreen extends Screen {
  private PlayerEntity player;
  private IQuestData questData;
  private Quest[] availableQuests = new Quest[0]; private IHakiData hakiData; private IAbilityData abilityData;
  private TrainerEntity trainer;
  private float animationTime = 0.0F;
  private float animationTranslation = 100.0F;
  private int guiState = 0;
  private SequencedString startMessage = new SequencedString("", 0, 0);
  
  private AvailableQuestsListScreenPanel availableQuestsPanel;

  
  public TrainerScreen(PlayerEntity player, TrainerEntity trainer, Quest[] availableQuests) {
    super((ITextComponent)new StringTextComponent(""));
    this.player = player;
    this.questData = QuestDataCapability.get(player);
    this.hakiData = HakiDataCapability.get((LivingEntity)player);
    this.abilityData = AbilityDataCapability.get((LivingEntity)player);
    this.availableQuests = availableQuests;
    this.trainer = trainer;
  }


  
  public void render(int mouseX, int mouseY, float partialTicks) {
    renderBackground();
    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    
    if (this.animationTime < 10.0F)
    {
      this.animationTime = (float)(this.animationTime + 0.2D);
    }
    if (this.animationTranslation > 0.0F)
    {
      this.animationTranslation = 100.0F - this.animationTime * 40.0F;
    }
    
    int posX = this.width / 2;
    int posY = this.height / 2;
    
    switch (this.guiState) {
      
      case 0:
        renderMenu(mouseX, mouseY, partialTicks);
        break;
      case 1:
        renderQuestList(mouseX, mouseY, partialTicks);
        break;
      case 2:
        renderMenu(mouseX, mouseY, partialTicks);
        break;
    } 

    
    RenderSystem.pushMatrix();
    
    RenderSystem.translatef(this.animationTranslation, 0.0F, 0.0F);
    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 0.1F + this.animationTime / 4.0F);
    RenderSystem.enableBlend();
    if (this.trainer instanceof LivingEntity) {
      InventoryScreen.drawEntityOnScreen(posX + 150, posY + 150, 100, 40.0F, 5.0F, (LivingEntity)this.trainer);
    }
    RenderSystem.popMatrix();
    
    super.render(mouseX, mouseY, partialTicks);
  }

  
  public void renderMenu(int mouseX, int mouseY, float partialTicks) {
    int posX = this.width / 2;
    int posY = this.height / 2;
    
    this.startMessage.render(posX - 150, posY - 105);
  }


  
  public void renderQuestList(int mouseX, int mouseY, float partialTicks) {
    RenderSystem.pushMatrix();
    
    RenderSystem.translatef(-this.animationTranslation, 0.0F, 0.0F);
    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 0.1F + this.animationTime / 5.0F);
    RenderSystem.enableBlend();
    this.availableQuestsPanel.render(mouseX, mouseY, partialTicks);
    this.availableQuestsPanel.isMouseOver(mouseX, mouseY);
    
    RenderSystem.popMatrix();
    
    super.render(mouseX, mouseY, partialTicks);
  }


  
  public void init(Minecraft mc, int width, int height) {
    super.init(mc, width, height);
    
    int posX = this.width / 2;
    int posY = this.height / 2;
    
    if (this.guiState == 0) {
      
      NewButton trialsListButton = new NewButton(posX - 180, posY - 50, 100, 20, "Trials", btn -> {
            boolean hasQuests = false;

            
            for (int i = 0; i <= (this.trainer.getAvailableQuests(this.player)).length - 1; i++) {
              Quest quest = this.trainer.getAvailableQuests(this.player)[i];
              
              if (!this.questData.hasFinishedQuest(quest)) {
                hasQuests = true;
                
                break;
              } 
            } 
            
            if (hasQuests) {
              this.guiState = 1;
              
              init(getMinecraft(), this.width, this.height);
            } else {
              String message = (new TranslationTextComponent(ModI18n.TRAINER_NO_TRIALS_AVAILABLE, new Object[0])).getFormattedText();
              
              this.startMessage = new SequencedString(message, 250, this.font.getStringWidth(message) / 2);
            } 
          });
      
      addButton((Widget)trialsListButton);
      
      if (this.trainer instanceof IHakiTrainer)
      {
        NewButton hakiTrainingButton = new NewButton(posX - 180, posY - 20, 100, 20, "Haki Training", btn -> {
              boolean canLearnHaki = (EntityStatsCapability.get((LivingEntity)this.player).getDoriki() > 2000);

              
              if (canLearnHaki) {
                this.guiState = 2;
                
                init(getMinecraft(), this.width, this.height);
              } else {
                String message = (new TranslationTextComponent(ModI18n.TRAINER_CANT_LEARN_HAKI, new Object[0])).getFormattedText();
                
                this.startMessage = new SequencedString(message, 250, this.font.getStringWidth(message) / 2);
              } 
            });
        
        addButton((Widget)hakiTrainingButton);
      }
    
    } else if (this.guiState == 1) {
      
      this.availableQuestsPanel = new AvailableQuestsListScreenPanel(this, this.questData, this.availableQuests);
      this.children.add(this.availableQuestsPanel);
      setFocused((IGuiEventListener)this.availableQuestsPanel);
      
      NewButton backButton = new NewButton(posX - 180, posY + 80, 200, 20, I18n.format("gui.cancel", new Object[0]), btn -> {
            this.guiState = 0;
            
            init(getMinecraft(), this.width, this.height);
          });
      addButton((Widget)backButton);
    }
    else if (this.guiState == 2) {
      
      if (this.trainer instanceof IHakiTrainer) {
        
        String info = "";
        String tempMessage = "";
        
        HakiType type = ((IHakiTrainer)this.trainer).getTrainingHaki();
        if (type == HakiType.HARDENING) {
          
          info = ModI18n.TRAINER_HOW_TO_HARDENING;
          tempMessage = ModI18n.TRAINER_HOW_TO_HARDENING_MESSAGE;
        }
        else if (type == HakiType.IMBUING) {
          
          info = ModI18n.TRAINER_HOW_TO_IMBUING;
          tempMessage = ModI18n.TRAINER_HOW_TO_IMBUING_MESSAGE;
        }
        else {
          
          info = ModI18n.TRAINER_HOW_TO_OBSERVATION;
          tempMessage = ModI18n.TRAINER_HOW_TO_OBSERVATION_MESSAGE;
          if (this.abilityData.hasUnlockedAbility((Ability)KenbunshokuHakiAuraAbility.INSTANCE))
          {
            tempMessage = ModI18n.TRAINER_HOW_TO_OBSERVATION_MESSAGE_2;
          }
        } 
        String hakiTrainingMessage = tempMessage;
        
        NewButton hakiInfoButton = new NewButton(posX - 180, posY - 50, 200, 20, (new TranslationTextComponent(info, new Object[0])).getFormattedText(), btn -> {
              String formattedMessage = (new TranslationTextComponent(hakiTrainingMessage, new Object[0])).getFormattedText();
              
              this.startMessage = new SequencedString(formattedMessage, 250, this.font.getStringWidth(formattedMessage) / 3, 650);
            });
        addButton((Widget)hakiInfoButton);
        
        info = ModI18n.TRAINER_MY_HAKI;
        NewButton checkHakiButton = new NewButton(posX - 180, posY - 20, 200, 20, (new TranslationTextComponent(info, new Object[0])).getFormattedText(), btn -> {
              String formattedMessage = HakiHelper.getHakiRank(type, this.player);
              
              this.startMessage = new SequencedString(formattedMessage, 250, this.font.getStringWidth(formattedMessage) / 3, 650);
            });
        addButton((Widget)checkHakiButton);
      } 
      
      NewButton backButton = new NewButton(posX - 180, posY + 80, 200, 20, I18n.format("gui.cancel", new Object[0]), btn -> {
            this.guiState = 0;
            
            init(getMinecraft(), this.width, this.height);
          });
      addButton((Widget)backButton);
    } 
  }

  
  public boolean isAnimationComplete() {
    if (this.animationTime >= 5.0F)
      return true; 
    return false;
  }
}


