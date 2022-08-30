/*     */ package xyz.pixelatedw.mineminenomi.screens;
/*     */ 
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.IGuiEventListener;
/*     */ import net.minecraft.client.gui.screen.Screen;
/*     */ import net.minecraft.client.gui.screen.inventory.InventoryScreen;
/*     */ import net.minecraft.client.gui.widget.Widget;
/*     */ import net.minecraft.client.gui.widget.button.Button;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.client.gui.GuiUtils;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.Challenge;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.challenge.CStartChallengePacket;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.AvailableChallengesListPanel;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.ChallengeButton;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.TexturedIconButton;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class ChallengesScreen
/*     */   extends Screen {
/*     */   private PlayerEntity player;
/*     */   private IChallengesData challengesProps;
/*     */   private boolean isGeneratingArena = false;
/*     */   private Map.Entry<String, List<Challenge>> selectedCategory;
/*     */   private Challenge selectedChallenge;
/*     */   private LivingEntity target;
/*     */   private final boolean retakeChallenges;
/*     */   private AvailableChallengesListPanel availableChallengesPanel;
/*     */   
/*     */   public ChallengesScreen(boolean retakeChallenges) {
/*  47 */     super((ITextComponent)new StringTextComponent(""));
/*  48 */     this.player = (PlayerEntity)(Minecraft.getInstance()).player;
/*  49 */     this.challengesProps = ChallengesDataCapability.get(this.player);
/*  50 */     this.isGeneratingArena = false;
/*  51 */     this.retakeChallenges = retakeChallenges;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(int mouseX, int mouseY, float partialTicks) {
/*  57 */     int posX = (this.width - 256) / 2;
/*  58 */     int posY = (this.height - 256) / 2;
/*     */     
/*  60 */     if (this.isGeneratingArena) {
/*     */       
/*  62 */       renderDirtBackground(0);
/*  63 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */       
/*  65 */       WyHelper.drawStringWithBorder(this.font, TextFormatting.BOLD + "Generating Arena...", posX + 70, posY + 120, -1);
/*     */     }
/*  67 */     else if (this.selectedCategory != null) {
/*     */       
/*  69 */       renderBackground();
/*  70 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */       
/*  72 */       if (this.selectedChallenge != null) {
/*     */ 
/*     */         
/*  75 */         RenderSystem.pushMatrix();
/*  76 */         Minecraft.getInstance().getTextureManager().bindTexture(ModResources.BLANK);
/*  77 */         RenderSystem.scaled(1.2000000476837158D, 1.2999999523162842D, 1.0D);
/*  78 */         GuiUtils.drawTexturedModalRect(posX + 20, posY + 15, 0, 0, 256, 256, 0.0F);
/*  79 */         RenderSystem.popMatrix();
/*     */ 
/*     */         
/*  82 */         RenderSystem.pushMatrix();
/*     */         
/*  84 */         if (this.target != null) {
/*  85 */           InventoryScreen.drawEntityOnScreen(posX + 110, posY + 180, 50, 80.0F, -5.0F, this.target);
/*     */         }
/*  87 */         RenderSystem.popMatrix();
/*     */         
/*  89 */         WyHelper.drawStringWithBorder(this.font, TextFormatting.BOLD + "Objective: ", posX + 160, posY + 60, -1);
/*  90 */         WyHelper.drawStringWithBorder(this.font, this.selectedChallenge.getObjective(), posX + 170, posY + 75, -1);
/*     */         
/*  92 */         WyHelper.drawStringWithBorder(this.font, TextFormatting.BOLD + "Time Limit: ", posX + 160, posY + 100, -1);
/*  93 */         WyHelper.drawStringWithBorder(this.font, this.selectedChallenge.getTimeLimit() + " minutes", posX + 170, posY + 115, -1);
/*     */         
/*  95 */         WyHelper.drawStringWithBorder(this.font, TextFormatting.BOLD + "Difficulty: ", posX + 160, posY + 140, -1);
/*  96 */         WyHelper.drawStringWithBorder(this.font, this.selectedChallenge.getDifficulty().getName(), posX + 170, posY + 155, -1);
/*     */       } 
/*     */       
/*  99 */       RenderSystem.pushMatrix();
/*     */ 
/*     */       
/* 102 */       this.availableChallengesPanel.render(mouseX, mouseY, partialTicks);
/* 103 */       this.availableChallengesPanel.isMouseOver(mouseX, mouseY);
/*     */       
/* 105 */       RenderSystem.popMatrix();
/*     */     }
/*     */     else {
/*     */       
/* 109 */       renderBackground();
/* 110 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     } 
/*     */     
/* 113 */     super.render(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() {
/* 119 */     this.children.clear();
/* 120 */     this.buttons.clear();
/*     */     
/* 122 */     if (this.isGeneratingArena) {
/*     */       return;
/*     */     }
/* 125 */     int posX = this.width / 2;
/* 126 */     int posY = this.height / 2;
/*     */     
/* 128 */     TexturedIconButton challengeButton = new TexturedIconButton(ModResources.BLANK2_SIMPLE, posX + 30, posY + 80, 80, 40, "Challenge", b -> {
/*     */           this.isGeneratingArena = true;
/*     */           
/*     */           init();
/*     */           WyNetwork.sendToServer(new CStartChallengePacket(this.selectedChallenge.getId()));
/*     */         });
/* 134 */     challengeButton = challengeButton.setTextInfo(posX + 73, posY + 120, 1.25D);
/* 135 */     addButton((Widget)challengeButton);
/*     */     
/* 137 */     TexturedIconButton backButton = new TexturedIconButton(ModResources.BLANK2_SIMPLE, posX - 180, posY + 80, 80, 40, "Back", b -> {
/*     */           this.selectedChallenge = null;
/*     */           
/*     */           this.selectedCategory = null;
/*     */           init();
/*     */         });
/* 143 */     backButton = backButton.setTextInfo(posX - 121, posY + 120, 1.25D);
/* 144 */     addButton((Widget)backButton);
/*     */     
/* 146 */     if (this.selectedCategory != null) {
/* 147 */       backButton.visible = true;
/*     */     } else {
/* 149 */       backButton.visible = false;
/*     */     } 
/* 151 */     if (this.selectedCategory != null) {
/*     */       
/* 153 */       this.availableChallengesPanel = new AvailableChallengesListPanel(this, this.challengesProps, this.selectedCategory.getValue());
/* 154 */       this.availableChallengesPanel.currentChallenge = Optional.ofNullable(this.selectedChallenge);
/*     */       
/* 156 */       if (this.selectedChallenge == null) {
/*     */         
/* 158 */         Optional<Challenge> currentChallenge = this.challengesProps.getCurrentChallenge((PlayerEntity)(Minecraft.getInstance()).player, this.selectedCategory.getKey());
/* 159 */         this.availableChallengesPanel.currentChallenge = currentChallenge;
/*     */         
/* 161 */         if (currentChallenge.isPresent()) {
/*     */           
/* 163 */           this.selectedChallenge = currentChallenge.get();
/* 164 */           this.target = (LivingEntity)this.selectedChallenge.getTarget().create((World)this.minecraft.world);
/*     */         } 
/*     */       } 
/*     */       
/* 168 */       this.children.add(this.availableChallengesPanel);
/* 169 */       setFocused((IGuiEventListener)this.availableChallengesPanel);
/*     */     }
/*     */     else {
/*     */       
/* 173 */       int i = 0;
/* 174 */       for (Map.Entry<String, List<Challenge>> entry : (Iterable<Map.Entry<String, List<Challenge>>>)this.challengesProps.getGroupedChallenges().entrySet()) {
/*     */         
/* 176 */         posX += 130;
/* 177 */         if (i % 3 == 0) {
/*     */           
/* 179 */           posY += 130;
/* 180 */           posX -= 390;
/*     */         } 
/*     */         
/* 183 */         int challengesLeft = (int)(entry.getValue()).stream().filter(ch -> !ch.isComplete()).count();
/* 184 */         addButton((Widget)new ChallengeButton(posX + 80, posY - 245, 102, 100, entry.getKey(), challengesLeft, b -> {
/*     */                 this.selectedCategory = entry;
/*     */                 
/*     */                 init();
/*     */               }));
/* 189 */         i++;
/*     */       } 
/*     */     } 
/*     */     
/* 193 */     if (this.selectedChallenge != null) {
/*     */       
/* 195 */       boolean canRetake = this.retakeChallenges;
/* 196 */       boolean isFinished = this.challengesProps.getChallenge(this.selectedChallenge).isComplete();
/* 197 */       if (isFinished && !canRetake) {
/* 198 */         challengeButton.visible = false;
/*     */       } else {
/* 200 */         challengeButton.visible = true;
/*     */       } 
/*     */     } else {
/* 203 */       challengeButton.visible = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void selectChallengeEntry(Challenge challenge) {
/* 209 */     this.selectedChallenge = challenge;
/* 210 */     this.target = (LivingEntity)this.selectedChallenge.getTarget().create((World)this.minecraft.world);
/* 211 */     init();
/*     */   }
/*     */ 
/*     */   
/*     */   public Map.Entry<String, List<Challenge>> getCategory() {
/* 216 */     return this.selectedCategory;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void open(boolean retakeChallenges) {
/* 221 */     Minecraft.getInstance().displayGuiScreen(new ChallengesScreen(retakeChallenges));
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\screens\ChallengesScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */