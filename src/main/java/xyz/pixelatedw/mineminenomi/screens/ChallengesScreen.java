package xyz.pixelatedw.mineminenomi.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.gui.GuiUtils;
import org.lwjgl.opengl.GL11;
import xyz.pixelatedw.mineminenomi.challenges.Challenge;
import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.packets.client.challenge.CStartChallengePacket;
import xyz.pixelatedw.mineminenomi.screens.extra.AvailableChallengesListPanel;
import xyz.pixelatedw.mineminenomi.screens.extra.ChallengeButton;
import xyz.pixelatedw.mineminenomi.screens.extra.TexturedIconButton;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

@OnlyIn(Dist.CLIENT)
public class ChallengesScreen
  extends Screen {
  private PlayerEntity player;
  private IChallengesData challengesProps;
  private boolean isGeneratingArena = false;
  private Map.Entry<String, List<Challenge>> selectedCategory;
  private Challenge selectedChallenge;
  private LivingEntity target;
  private final boolean retakeChallenges;
  private AvailableChallengesListPanel availableChallengesPanel;
  
  public ChallengesScreen(boolean retakeChallenges) {
    super((ITextComponent)new StringTextComponent(""));
    this.player = (PlayerEntity)(Minecraft.getInstance()).player;
    this.challengesProps = ChallengesDataCapability.get(this.player);
    this.isGeneratingArena = false;
    this.retakeChallenges = retakeChallenges;
  }


  
  public void render(int mouseX, int mouseY, float partialTicks) {
    int posX = (this.width - 256) / 2;
    int posY = (this.height - 256) / 2;
    
    if (this.isGeneratingArena) {
      
      renderDirtBackground(0);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      
      WyHelper.drawStringWithBorder(this.font, TextFormatting.BOLD + "Generating Arena...", posX + 70, posY + 120, -1);
    }
    else if (this.selectedCategory != null) {
      
      renderBackground();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      
      if (this.selectedChallenge != null) {

        
        RenderSystem.pushMatrix();
        Minecraft.getInstance().getTextureManager().bindTexture(ModResources.BLANK);
        RenderSystem.scaled(1.2000000476837158D, 1.2999999523162842D, 1.0D);
        GuiUtils.drawTexturedModalRect(posX + 20, posY + 15, 0, 0, 256, 256, 0.0F);
        RenderSystem.popMatrix();

        
        RenderSystem.pushMatrix();
        
        if (this.target != null) {
          InventoryScreen.drawEntityOnScreen(posX + 110, posY + 180, 50, 80.0F, -5.0F, this.target);
        }
        RenderSystem.popMatrix();
        
        WyHelper.drawStringWithBorder(this.font, TextFormatting.BOLD + "Objective: ", posX + 160, posY + 60, -1);
        WyHelper.drawStringWithBorder(this.font, this.selectedChallenge.getObjective(), posX + 170, posY + 75, -1);
        
        WyHelper.drawStringWithBorder(this.font, TextFormatting.BOLD + "Time Limit: ", posX + 160, posY + 100, -1);
        WyHelper.drawStringWithBorder(this.font, this.selectedChallenge.getTimeLimit() + " minutes", posX + 170, posY + 115, -1);
        
        WyHelper.drawStringWithBorder(this.font, TextFormatting.BOLD + "Difficulty: ", posX + 160, posY + 140, -1);
        WyHelper.drawStringWithBorder(this.font, this.selectedChallenge.getDifficulty().getName(), posX + 170, posY + 155, -1);
      } 
      
      RenderSystem.pushMatrix();

      
      this.availableChallengesPanel.render(mouseX, mouseY, partialTicks);
      this.availableChallengesPanel.isMouseOver(mouseX, mouseY);
      
      RenderSystem.popMatrix();
    }
    else {
      
      renderBackground();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    } 
    
    super.render(mouseX, mouseY, partialTicks);
  }


  
  public void init() {
    this.children.clear();
    this.buttons.clear();
    
    if (this.isGeneratingArena) {
      return;
    }
    int posX = this.width / 2;
    int posY = this.height / 2;
    
    TexturedIconButton challengeButton = new TexturedIconButton(ModResources.BLANK2_SIMPLE, posX + 30, posY + 80, 80, 40, "Challenge", b -> {
          this.isGeneratingArena = true;
          
          init();
          WyNetwork.sendToServer(new CStartChallengePacket(this.selectedChallenge.getId()));
        });
    challengeButton = challengeButton.setTextInfo(posX + 73, posY + 120, 1.25D);
    addButton((Widget)challengeButton);
    
    TexturedIconButton backButton = new TexturedIconButton(ModResources.BLANK2_SIMPLE, posX - 180, posY + 80, 80, 40, "Back", b -> {
          this.selectedChallenge = null;
          
          this.selectedCategory = null;
          init();
        });
    backButton = backButton.setTextInfo(posX - 121, posY + 120, 1.25D);
    addButton((Widget)backButton);
    
    if (this.selectedCategory != null) {
      backButton.visible = true;
    } else {
      backButton.visible = false;
    } 
    if (this.selectedCategory != null) {
      
      this.availableChallengesPanel = new AvailableChallengesListPanel(this, this.challengesProps, this.selectedCategory.getValue());
      this.availableChallengesPanel.currentChallenge = Optional.ofNullable(this.selectedChallenge);
      
      if (this.selectedChallenge == null) {
        
        Optional<Challenge> currentChallenge = this.challengesProps.getCurrentChallenge((PlayerEntity)(Minecraft.getInstance()).player, this.selectedCategory.getKey());
        this.availableChallengesPanel.currentChallenge = currentChallenge;
        
        if (currentChallenge.isPresent()) {
          
          this.selectedChallenge = currentChallenge.get();
          this.target = (LivingEntity)this.selectedChallenge.getTarget().create((World)this.minecraft.world);
        } 
      } 
      
      this.children.add(this.availableChallengesPanel);
      setFocused((IGuiEventListener)this.availableChallengesPanel);
    }
    else {
      
      int i = 0;
      for (Map.Entry<String, List<Challenge>> entry : (Iterable<Map.Entry<String, List<Challenge>>>)this.challengesProps.getGroupedChallenges().entrySet()) {
        
        posX += 130;
        if (i % 3 == 0) {
          
          posY += 130;
          posX -= 390;
        } 
        
        int challengesLeft = (int)(entry.getValue()).stream().filter(ch -> !ch.isComplete()).count();
        addButton((Widget)new ChallengeButton(posX + 80, posY - 245, 102, 100, entry.getKey(), challengesLeft, b -> {
                this.selectedCategory = entry;
                
                init();
              }));
        i++;
      } 
    } 
    
    if (this.selectedChallenge != null) {
      
      boolean canRetake = this.retakeChallenges;
      boolean isFinished = this.challengesProps.getChallenge(this.selectedChallenge).isComplete();
      if (isFinished && !canRetake) {
        challengeButton.visible = false;
      } else {
        challengeButton.visible = true;
      } 
    } else {
      challengeButton.visible = false;
    } 
  }

  
  public void selectChallengeEntry(Challenge challenge) {
    this.selectedChallenge = challenge;
    this.target = (LivingEntity)this.selectedChallenge.getTarget().create((World)this.minecraft.world);
    init();
  }

  
  public Map.Entry<String, List<Challenge>> getCategory() {
    return this.selectedCategory;
  }

  
  public static void open(boolean retakeChallenges) {
    Minecraft.getInstance().displayGuiScreen(new ChallengesScreen(retakeChallenges));
  }
}


