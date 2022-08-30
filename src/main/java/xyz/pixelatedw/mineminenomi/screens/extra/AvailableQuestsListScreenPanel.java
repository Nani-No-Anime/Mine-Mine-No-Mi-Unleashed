/*     */ package xyz.pixelatedw.mineminenomi.screens.extra;
/*     */ 
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraftforge.client.gui.ScrollPanel;
/*     */ import net.minecraftforge.fml.client.gui.GuiUtils;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.quest.CUpdateQuestStatePacket;
/*     */ import xyz.pixelatedw.mineminenomi.screens.TrainerScreen;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AvailableQuestsListScreenPanel
/*     */   extends ScrollPanel
/*     */ {
/*     */   private TrainerScreen parent;
/*     */   private IQuestData props;
/*  31 */   private List<Quest> availableQuests = new ArrayList<>();
/*     */   
/*     */   private static final int ENTRY_HEIGHT = 20;
/*     */   private FontRenderer font;
/*     */   
/*     */   public AvailableQuestsListScreenPanel(TrainerScreen parent, IQuestData abilityProps, Quest[] quests) {
/*  37 */     super(parent.getMinecraft(), 200, 180, parent.height / 2 - 110, parent.width / 2 - 190);
/*  38 */     this.parent = parent;
/*  39 */     this.props = abilityProps;
/*  40 */     this.font = (parent.getMinecraft()).fontRenderer;
/*     */     
/*  42 */     updateAvailableQuests(quests);
/*     */     
/*  44 */     this.scrollDistance = -10.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateAvailableQuests(List<Quest> quests) {
/*  49 */     Quest[] arr = new Quest[quests.size()];
/*  50 */     Quest[] questsArray = quests.<Quest>toArray(arr);
/*  51 */     updateAvailableQuests(questsArray);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateAvailableQuests(Quest[] quests) {
/*  56 */     this.availableQuests.clear();
/*  57 */     for (int i = 0; i <= quests.length - 1; i++) {
/*     */       
/*  59 */       Quest quest = quests[i];
/*  60 */       boolean exists = (quest != null);
/*  61 */       boolean isNotFinished = (exists && !this.props.hasFinishedQuest(quest));
/*  62 */       boolean isNotInProgress = (exists && (this.props.getInProgressQuest(quest) == null || (this.props.getInProgressQuest(quest) != null && this.props.getInProgressQuest(quest).isComplete())));
/*     */       
/*  64 */       if (isNotFinished && isNotInProgress)
/*     */       {
/*  66 */         this.availableQuests.add(quests[i]);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mouseReleased(double p_mouseReleased_1_, double p_mouseReleased_3_, int p_mouseReleased_5_) {
/*  74 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getContentHeight() {
/*  80 */     return (int)(this.availableQuests.size() * 55.0D - 2.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getScrollAmount() {
/*  86 */     return 12;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(int mouseX, int mouseY, float partialTicks) {
/*  92 */     Tessellator tess = Tessellator.getInstance();
/*     */     
/*  94 */     double scale = this.parent.getMinecraft().getMainWindow().getGuiScaleFactor();
/*  95 */     GL11.glEnable(3089);
/*  96 */     GL11.glScissor((int)(this.left * scale), (int)(this.parent.getMinecraft().getMainWindow().getFramebufferHeight() - this.bottom * scale), (int)(this.width * scale), (int)(this.height * scale));
/*     */     
/*  98 */     int baseY = this.top + 4 - (int)this.scrollDistance;
/*  99 */     drawPanel(this.right, baseY, tess, mouseX, mouseY);
/*     */     
/* 101 */     GL11.glDisable(3089);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawPanel(int entryRight, int relativeY, Tessellator tess, int mouseX, int mouseY) {
/* 109 */     for (Quest quest : this.availableQuests) {
/*     */       
/* 111 */       if (quest == null) {
/*     */         continue;
/*     */       }
/* 114 */       float y = relativeY;
/* 115 */       float x = (this.parent.width / 2 - 109 + 40);
/*     */       
/* 117 */       String formattedQuestName = I18n.format("quest.mineminenomi." + WyHelper.getResourceName(quest.getId()), new Object[0]);
/* 118 */       String questColor = "#FFFFFF";
/*     */       
/* 120 */       Quest inProgressQuest = this.props.getInProgressQuest(quest);
/* 121 */       if (inProgressQuest != null) {
/*     */         
/* 123 */         if (isMouseOverQuest(mouseX, mouseY, inProgressQuest) && !inProgressQuest.isComplete())
/*     */         {
/* 125 */           formattedQuestName = (new TranslationTextComponent(ModI18n.TRAINER_NO_QUESTS_AVAILABLE, new Object[0])).getFormattedText();
/*     */         }
/*     */         
/* 128 */         if (inProgressQuest.isComplete())
/*     */         {
/* 130 */           questColor = "#00FF55";
/*     */         }
/*     */       } 
/*     */       
/* 134 */       if (quest.isLocked(this.props))
/*     */       {
/* 136 */         questColor = "#505050";
/*     */       }
/*     */       
/* 139 */       if (this.parent.isAnimationComplete() && isMouseOverQuest(mouseX, mouseY, quest))
/*     */       {
/* 141 */         RenderSystem.color3f(0.8F, 0.8F, 0.8F);
/*     */       }
/*     */       
/* 144 */       RenderSystem.pushMatrix();
/*     */       
/* 146 */       Minecraft.getInstance().getTextureManager().bindTexture(ModResources.SCROLL);
/* 147 */       double scale = 0.5D;
/* 148 */       RenderSystem.translated((x - 180.0F), (y - 196.0F), 0.0D);
/* 149 */       RenderSystem.translated(256.0D, 256.0D, 0.0D);
/*     */       
/* 151 */       RenderSystem.scaled(scale * 1.5D, scale * 0.6D, 0.0D);
/* 152 */       RenderSystem.translated(-256.0D, -256.0D, 0.0D);
/*     */ 
/*     */       
/* 155 */       GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, 1.0F);
/*     */       
/* 157 */       RenderSystem.popMatrix();
/*     */       
/* 159 */       if (this.parent.isAnimationComplete()) {
/* 160 */         RenderSystem.color3f(1.0F, 1.0F, 1.0F);
/*     */       }
/* 162 */       if (this.font.getStringWidth(formattedQuestName) > 140) {
/*     */         
/* 164 */         RenderSystem.pushMatrix();
/*     */         
/* 166 */         List<String> splittedText = WyHelper.splitString(this.font, formattedQuestName, (int)x - 80, 140);
/* 167 */         RenderSystem.translated(0.0D, -((splittedText.size() - 1) * 5), 0.0D);
/* 168 */         for (String string : splittedText) {
/*     */           
/* 170 */           WyHelper.drawStringWithBorder(this.font, string, (int)x - 80, (int)y + 16, WyHelper.hexToRGB(questColor).getRGB());
/* 171 */           y += 10.0F;
/*     */         } 
/*     */         
/* 174 */         RenderSystem.popMatrix();
/*     */       }
/*     */       else {
/*     */         
/* 178 */         WyHelper.drawStringWithBorder(this.font, formattedQuestName, (int)x - 80, (int)y + 16, WyHelper.hexToRGB(questColor).getRGB());
/*     */       } 
/*     */       
/* 181 */       relativeY = (int)(relativeY + 55.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mouseClicked(double mouseX, double mouseY, int button) {
/* 188 */     Quest quest = findQuestEntry((int)mouseX, (int)mouseY);
/* 189 */     Quest inProgressQuest = this.props.getInProgressQuest(quest);
/*     */     
/* 191 */     if (button != 0) {
/* 192 */       return false;
/*     */     }
/* 194 */     if (inProgressQuest != null && inProgressQuest.isComplete()) {
/*     */       
/* 196 */       this.props.addFinishedQuest(inProgressQuest);
/* 197 */       this.props.removeInProgressQuest(inProgressQuest);
/* 198 */       WyNetwork.sendToServer(new CUpdateQuestStatePacket(inProgressQuest));
/*     */     }
/* 200 */     else if (quest != null && inProgressQuest == null && !quest.isLocked(this.props)) {
/*     */       
/* 202 */       this.props.addInProgressQuest(quest);
/* 203 */       WyNetwork.sendToServer(new CUpdateQuestStatePacket(quest));
/*     */     } 
/*     */     
/* 206 */     updateAvailableQuests(this.availableQuests);
/*     */     
/* 208 */     return super.mouseClicked(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMouseOverQuest(double mouseX, double mouseY, Quest overQuest) {
/* 213 */     Quest quest = findQuestEntry((int)mouseX, (int)mouseY);
/*     */     
/* 215 */     if (quest != null && quest.equals(overQuest))
/*     */     {
/* 217 */       return isMouseOver(mouseX, mouseY);
/*     */     }
/*     */     
/* 220 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private Quest findQuestEntry(int mouseX, int mouseY) {
/* 225 */     double offset = ((mouseY - this.top) + this.scrollDistance);
/* 226 */     boolean isHovered = (mouseX >= this.left && mouseY >= this.top && mouseX < this.left + this.width - 5 && mouseY < this.top + this.height);
/*     */     
/* 228 */     if (offset <= 0.0D || !isHovered) {
/* 229 */       return null;
/*     */     }
/* 231 */     int lineIdx = (int)(offset / 55.0D);
/* 232 */     if (lineIdx >= this.availableQuests.size()) {
/* 233 */       return null;
/*     */     }
/* 235 */     Quest quest = this.availableQuests.get(lineIdx);
/* 236 */     if (quest != null)
/*     */     {
/* 238 */       return quest.create();
/*     */     }
/*     */     
/* 241 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\screens\extra\AvailableQuestsListScreenPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */