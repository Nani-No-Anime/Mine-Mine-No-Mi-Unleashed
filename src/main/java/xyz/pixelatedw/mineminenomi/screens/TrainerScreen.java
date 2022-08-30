/*     */ package xyz.pixelatedw.mineminenomi.screens;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.IGuiEventListener;
/*     */ import net.minecraft.client.gui.screen.Screen;
/*     */ import net.minecraft.client.gui.screen.inventory.InventoryScreen;
/*     */ import net.minecraft.client.gui.widget.Widget;
/*     */ import net.minecraft.client.gui.widget.button.Button;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.KenbunshokuHakiAuraAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.TrainerEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.IHakiTrainer;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.AvailableQuestsListScreenPanel;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.NewButton;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.SequencedString;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class TrainerScreen extends Screen {
/*     */   private PlayerEntity player;
/*     */   private IQuestData questData;
/*  40 */   private Quest[] availableQuests = new Quest[0]; private IHakiData hakiData; private IAbilityData abilityData;
/*     */   private TrainerEntity trainer;
/*  42 */   private float animationTime = 0.0F;
/*  43 */   private float animationTranslation = 100.0F;
/*  44 */   private int guiState = 0;
/*  45 */   private SequencedString startMessage = new SequencedString("", 0, 0);
/*     */   
/*     */   private AvailableQuestsListScreenPanel availableQuestsPanel;
/*     */ 
/*     */   
/*     */   public TrainerScreen(PlayerEntity player, TrainerEntity trainer, Quest[] availableQuests) {
/*  51 */     super((ITextComponent)new StringTextComponent(""));
/*  52 */     this.player = player;
/*  53 */     this.questData = QuestDataCapability.get(player);
/*  54 */     this.hakiData = HakiDataCapability.get((LivingEntity)player);
/*  55 */     this.abilityData = AbilityDataCapability.get((LivingEntity)player);
/*  56 */     this.availableQuests = availableQuests;
/*  57 */     this.trainer = trainer;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(int mouseX, int mouseY, float partialTicks) {
/*  63 */     renderBackground();
/*  64 */     RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  66 */     if (this.animationTime < 10.0F)
/*     */     {
/*  68 */       this.animationTime = (float)(this.animationTime + 0.2D);
/*     */     }
/*  70 */     if (this.animationTranslation > 0.0F)
/*     */     {
/*  72 */       this.animationTranslation = 100.0F - this.animationTime * 40.0F;
/*     */     }
/*     */     
/*  75 */     int posX = this.width / 2;
/*  76 */     int posY = this.height / 2;
/*     */     
/*  78 */     switch (this.guiState) {
/*     */       
/*     */       case 0:
/*  81 */         renderMenu(mouseX, mouseY, partialTicks);
/*     */         break;
/*     */       case 1:
/*  84 */         renderQuestList(mouseX, mouseY, partialTicks);
/*     */         break;
/*     */       case 2:
/*  87 */         renderMenu(mouseX, mouseY, partialTicks);
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/*  92 */     RenderSystem.pushMatrix();
/*     */     
/*  94 */     RenderSystem.translatef(this.animationTranslation, 0.0F, 0.0F);
/*  95 */     RenderSystem.color4f(1.0F, 1.0F, 1.0F, 0.1F + this.animationTime / 4.0F);
/*  96 */     RenderSystem.enableBlend();
/*  97 */     if (this.trainer instanceof LivingEntity) {
/*  98 */       InventoryScreen.drawEntityOnScreen(posX + 150, posY + 150, 100, 40.0F, 5.0F, (LivingEntity)this.trainer);
/*     */     }
/* 100 */     RenderSystem.popMatrix();
/*     */     
/* 102 */     super.render(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderMenu(int mouseX, int mouseY, float partialTicks) {
/* 107 */     int posX = this.width / 2;
/* 108 */     int posY = this.height / 2;
/*     */     
/* 110 */     this.startMessage.render(posX - 150, posY - 105);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderQuestList(int mouseX, int mouseY, float partialTicks) {
/* 116 */     RenderSystem.pushMatrix();
/*     */     
/* 118 */     RenderSystem.translatef(-this.animationTranslation, 0.0F, 0.0F);
/* 119 */     RenderSystem.color4f(1.0F, 1.0F, 1.0F, 0.1F + this.animationTime / 5.0F);
/* 120 */     RenderSystem.enableBlend();
/* 121 */     this.availableQuestsPanel.render(mouseX, mouseY, partialTicks);
/* 122 */     this.availableQuestsPanel.isMouseOver(mouseX, mouseY);
/*     */     
/* 124 */     RenderSystem.popMatrix();
/*     */     
/* 126 */     super.render(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void init(Minecraft mc, int width, int height) {
/* 132 */     super.init(mc, width, height);
/*     */     
/* 134 */     int posX = this.width / 2;
/* 135 */     int posY = this.height / 2;
/*     */     
/* 137 */     if (this.guiState == 0) {
/*     */       
/* 139 */       NewButton trialsListButton = new NewButton(posX - 180, posY - 50, 100, 20, "Trials", btn -> {
/*     */             boolean hasQuests = false;
/*     */ 
/*     */             
/*     */             for (int i = 0; i <= (this.trainer.getAvailableQuests(this.player)).length - 1; i++) {
/*     */               Quest quest = this.trainer.getAvailableQuests(this.player)[i];
/*     */               
/*     */               if (!this.questData.hasFinishedQuest(quest)) {
/*     */                 hasQuests = true;
/*     */                 
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */             
/*     */             if (hasQuests) {
/*     */               this.guiState = 1;
/*     */               
/*     */               init(getMinecraft(), this.width, this.height);
/*     */             } else {
/*     */               String message = (new TranslationTextComponent(ModI18n.TRAINER_NO_TRIALS_AVAILABLE, new Object[0])).getFormattedText();
/*     */               
/*     */               this.startMessage = new SequencedString(message, 250, this.font.getStringWidth(message) / 2);
/*     */             } 
/*     */           });
/*     */       
/* 164 */       addButton((Widget)trialsListButton);
/*     */       
/* 166 */       if (this.trainer instanceof IHakiTrainer)
/*     */       {
/* 168 */         NewButton hakiTrainingButton = new NewButton(posX - 180, posY - 20, 100, 20, "Haki Training", btn -> {
/*     */               boolean canLearnHaki = (EntityStatsCapability.get((LivingEntity)this.player).getDoriki() > 2000);
/*     */ 
/*     */               
/*     */               if (canLearnHaki) {
/*     */                 this.guiState = 2;
/*     */                 
/*     */                 init(getMinecraft(), this.width, this.height);
/*     */               } else {
/*     */                 String message = (new TranslationTextComponent(ModI18n.TRAINER_CANT_LEARN_HAKI, new Object[0])).getFormattedText();
/*     */                 
/*     */                 this.startMessage = new SequencedString(message, 250, this.font.getStringWidth(message) / 2);
/*     */               } 
/*     */             });
/*     */         
/* 183 */         addButton((Widget)hakiTrainingButton);
/*     */       }
/*     */     
/* 186 */     } else if (this.guiState == 1) {
/*     */       
/* 188 */       this.availableQuestsPanel = new AvailableQuestsListScreenPanel(this, this.questData, this.availableQuests);
/* 189 */       this.children.add(this.availableQuestsPanel);
/* 190 */       setFocused((IGuiEventListener)this.availableQuestsPanel);
/*     */       
/* 192 */       NewButton backButton = new NewButton(posX - 180, posY + 80, 200, 20, I18n.format("gui.cancel", new Object[0]), btn -> {
/*     */             this.guiState = 0;
/*     */             
/*     */             init(getMinecraft(), this.width, this.height);
/*     */           });
/* 197 */       addButton((Widget)backButton);
/*     */     }
/* 199 */     else if (this.guiState == 2) {
/*     */       
/* 201 */       if (this.trainer instanceof IHakiTrainer) {
/*     */         
/* 203 */         String info = "";
/* 204 */         String tempMessage = "";
/*     */         
/* 206 */         HakiType type = ((IHakiTrainer)this.trainer).getTrainingHaki();
/* 207 */         if (type == HakiType.HARDENING) {
/*     */           
/* 209 */           info = ModI18n.TRAINER_HOW_TO_HARDENING;
/* 210 */           tempMessage = ModI18n.TRAINER_HOW_TO_HARDENING_MESSAGE;
/*     */         }
/* 212 */         else if (type == HakiType.IMBUING) {
/*     */           
/* 214 */           info = ModI18n.TRAINER_HOW_TO_IMBUING;
/* 215 */           tempMessage = ModI18n.TRAINER_HOW_TO_IMBUING_MESSAGE;
/*     */         }
/*     */         else {
/*     */           
/* 219 */           info = ModI18n.TRAINER_HOW_TO_OBSERVATION;
/* 220 */           tempMessage = ModI18n.TRAINER_HOW_TO_OBSERVATION_MESSAGE;
/* 221 */           if (this.abilityData.hasUnlockedAbility((Ability)KenbunshokuHakiAuraAbility.INSTANCE))
/*     */           {
/* 223 */             tempMessage = ModI18n.TRAINER_HOW_TO_OBSERVATION_MESSAGE_2;
/*     */           }
/*     */         } 
/* 226 */         String hakiTrainingMessage = tempMessage;
/*     */         
/* 228 */         NewButton hakiInfoButton = new NewButton(posX - 180, posY - 50, 200, 20, (new TranslationTextComponent(info, new Object[0])).getFormattedText(), btn -> {
/*     */               String formattedMessage = (new TranslationTextComponent(hakiTrainingMessage, new Object[0])).getFormattedText();
/*     */               
/*     */               this.startMessage = new SequencedString(formattedMessage, 250, this.font.getStringWidth(formattedMessage) / 3, 650);
/*     */             });
/* 233 */         addButton((Widget)hakiInfoButton);
/*     */         
/* 235 */         info = ModI18n.TRAINER_MY_HAKI;
/* 236 */         NewButton checkHakiButton = new NewButton(posX - 180, posY - 20, 200, 20, (new TranslationTextComponent(info, new Object[0])).getFormattedText(), btn -> {
/*     */               String formattedMessage = HakiHelper.getHakiRank(type, this.player);
/*     */               
/*     */               this.startMessage = new SequencedString(formattedMessage, 250, this.font.getStringWidth(formattedMessage) / 3, 650);
/*     */             });
/* 241 */         addButton((Widget)checkHakiButton);
/*     */       } 
/*     */       
/* 244 */       NewButton backButton = new NewButton(posX - 180, posY + 80, 200, 20, I18n.format("gui.cancel", new Object[0]), btn -> {
/*     */             this.guiState = 0;
/*     */             
/*     */             init(getMinecraft(), this.width, this.height);
/*     */           });
/* 249 */       addButton((Widget)backButton);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAnimationComplete() {
/* 255 */     if (this.animationTime >= 5.0F)
/* 256 */       return true; 
/* 257 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\screens\TrainerScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */