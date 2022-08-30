/*     */ package xyz.pixelatedw.mineminenomi.screens.extra;
/*     */ 
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraftforge.client.gui.ScrollPanel;
/*     */ import net.minecraftforge.fml.client.gui.GuiUtils;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.Challenge;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.screens.ChallengesScreen;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class AvailableChallengesListPanel
/*     */   extends ScrollPanel {
/*     */   private ChallengesScreen parent;
/*     */   private IChallengesData props;
/*  25 */   private List<Challenge> availableChallenges = new ArrayList<>();
/*     */   
/*     */   private static final int ENTRY_HEIGHT = 20;
/*     */   private FontRenderer font;
/*     */   public Optional<Challenge> currentChallenge;
/*     */   
/*     */   public AvailableChallengesListPanel(ChallengesScreen parent, IChallengesData props, List<Challenge> list) {
/*  32 */     super(parent.getMinecraft(), 200, 180, parent.height / 2 - 120, parent.width / 2 - 270);
/*  33 */     this.parent = parent;
/*  34 */     this.props = props;
/*  35 */     this.font = (parent.getMinecraft()).fontRenderer;
/*     */     
/*  37 */     this.availableChallenges.clear();
/*  38 */     this.availableChallenges = list;
/*     */     
/*  40 */     this.scrollDistance = -10.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mouseReleased(double p_mouseReleased_1_, double p_mouseReleased_3_, int p_mouseReleased_5_) {
/*  46 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getContentHeight() {
/*  52 */     return (int)(this.availableChallenges.size() * 55.0D - 2.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getScrollAmount() {
/*  58 */     return 12;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(int mouseX, int mouseY, float partialTicks) {
/*  64 */     Tessellator tess = Tessellator.getInstance();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  73 */     Objects.requireNonNull(this); int baseY = this.top + 4 - (int)this.scrollDistance;
/*  74 */     drawPanel(this.right, baseY, tess, mouseX, mouseY);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawPanel(int entryRight, int relativeY, Tessellator tess, int mouseX, int mouseY) {
/*  84 */     for (Challenge challenge : this.availableChallenges) {
/*     */       
/*  86 */       if (challenge == null) {
/*     */         continue;
/*     */       }
/*  89 */       float y = relativeY;
/*  90 */       float x = (this.parent.width / 2 - 109 - 40);
/*     */       
/*  92 */       String formattedChallengeName = I18n.format(challenge.getId(), new Object[0]);
/*  93 */       String challengeColor = "#FFFFFF";
/*     */       
/*  95 */       if (challenge.isComplete()) {
/*  96 */         challengeColor = "#FFAA33";
/*     */       }
/*  98 */       if (challenge.isLocked((PlayerEntity)(Minecraft.getInstance()).player)) {
/*     */         
/* 100 */         formattedChallengeName = "???";
/* 101 */         challengeColor = "#616161";
/*     */       } 
/*     */       
/* 104 */       RenderSystem.pushMatrix();
/*     */       
/* 106 */       if (isMouseOverChallenge(mouseX, mouseY, challenge)) {
/*     */         
/* 108 */         RenderSystem.color3f(0.8F, 0.8F, 0.8F);
/* 109 */         RenderSystem.translated(0.0D, -2.0D, 0.0D);
/*     */       } 
/*     */       
/* 112 */       RenderSystem.pushMatrix();
/*     */       
/* 114 */       Minecraft.getInstance().getTextureManager().bindTexture(ModResources.SCROLL);
/* 115 */       double scale = 0.5D;
/* 116 */       RenderSystem.translated((x - 180.0F), (y - 196.0F), 0.0D);
/* 117 */       RenderSystem.translated(256.0D, 256.0D, 0.0D);
/*     */       
/* 119 */       RenderSystem.scaled(scale * 1.1D, scale * 0.6D, 0.0D);
/* 120 */       RenderSystem.translated(-256.0D, -256.0D, 0.0D);
/*     */ 
/*     */       
/* 123 */       if (this.currentChallenge != null && this.currentChallenge.isPresent() && challenge.equals(this.currentChallenge.get())) {
/*     */         
/* 125 */         RenderSystem.pushMatrix();
/*     */         
/* 127 */         RenderSystem.color3f(0.7F, 0.5F, 0.3F);
/* 128 */         RenderSystem.scaled(1.04D, 1.07D, 0.0D);
/* 129 */         RenderSystem.translated(-4.0D, -7.0D, 0.0D);
/* 130 */         GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, 1.0F);
/* 131 */         RenderSystem.color3f(1.0F, 1.0F, 1.0F);
/*     */         
/* 133 */         RenderSystem.popMatrix();
/*     */       } 
/* 135 */       GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, 1.0F);
/*     */       
/* 137 */       RenderSystem.popMatrix();
/*     */       
/* 139 */       if (this.font.getStringWidth(formattedChallengeName) > 140) {
/*     */         
/* 141 */         RenderSystem.pushMatrix();
/*     */         
/* 143 */         List<String> splittedText = WyHelper.splitString(this.font, formattedChallengeName, (int)x - 80, 140);
/* 144 */         RenderSystem.translated(0.0D, -((splittedText.size() - 1) * 5), 0.0D);
/* 145 */         for (String string : splittedText) {
/*     */           
/* 147 */           WyHelper.drawStringWithBorder(this.font, string, (int)x - 35, (int)y + 16, WyHelper.hexToRGB(challengeColor).getRGB());
/* 148 */           y += 10.0F;
/*     */         } 
/*     */         
/* 151 */         RenderSystem.popMatrix();
/*     */       }
/*     */       else {
/*     */         
/* 155 */         WyHelper.drawStringWithBorder(this.font, formattedChallengeName, (int)x - 35, (int)y + 16, WyHelper.hexToRGB(challengeColor).getRGB());
/*     */       } 
/*     */       
/* 158 */       RenderSystem.popMatrix();
/*     */       
/* 160 */       relativeY = (int)(relativeY + 55.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mouseClicked(double mouseX, double mouseY, int button) {
/* 167 */     Challenge challenge = findChallengeEntry((int)mouseX, (int)mouseY);
/*     */     
/* 169 */     if (button != 0 || challenge == null) {
/* 170 */       return false;
/*     */     }
/* 172 */     if (challenge.isLocked((PlayerEntity)(Minecraft.getInstance()).player)) {
/* 173 */       return false;
/*     */     }
/* 175 */     this.parent.selectChallengeEntry(challenge);
/*     */     
/* 177 */     return super.mouseClicked(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */   
/*     */   private Challenge findChallengeEntry(int mouseX, int mouseY) {
/* 182 */     double offset = ((mouseY - this.top) + this.scrollDistance);
/* 183 */     boolean isHovered = (mouseX - 60 >= this.left && mouseY >= this.top && mouseX < this.left + this.width - 5 && mouseY < this.top + this.height);
/*     */     
/* 185 */     if (offset <= 0.0D || !isHovered) {
/* 186 */       return null;
/*     */     }
/* 188 */     int lineIdx = (int)(offset / 55.0D);
/* 189 */     if (lineIdx >= this.availableChallenges.size()) {
/* 190 */       return null;
/*     */     }
/* 192 */     Challenge challenge = this.availableChallenges.get(lineIdx);
/* 193 */     if (challenge != null)
/*     */     {
/* 195 */       return challenge.create();
/*     */     }
/*     */     
/* 198 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMouseOverChallenge(double mouseX, double mouseY, Challenge overChallenge) {
/* 203 */     Challenge challenge = findChallengeEntry((int)mouseX, (int)mouseY);
/*     */     
/* 205 */     if (challenge != null && challenge.equals(overChallenge))
/*     */     {
/* 207 */       return isMouseOver(mouseX, mouseY);
/*     */     }
/*     */     
/* 210 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\screens\extra\AvailableChallengesListPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */