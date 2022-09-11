package xyz.pixelatedw.mineminenomi.screens.extra;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.gui.ScrollPanel;
import net.minecraftforge.fml.client.gui.GuiUtils;
import xyz.pixelatedw.mineminenomi.challenges.Challenge;
import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.screens.ChallengesScreen;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class AvailableChallengesListPanel
  extends ScrollPanel {
  private ChallengesScreen parent;
  private IChallengesData props;
  private List<Challenge> availableChallenges = new ArrayList<>();
  
  private static final int ENTRY_HEIGHT = 20;
  private FontRenderer font;
  public Optional<Challenge> currentChallenge;
  
  public AvailableChallengesListPanel(ChallengesScreen parent, IChallengesData props, List<Challenge> list) {
    super(parent.getMinecraft(), 200, 180, parent.height / 2 - 120, parent.width / 2 - 270);
    this.parent = parent;
    this.props = props;
    this.font = (parent.getMinecraft()).fontRenderer;
    
    this.availableChallenges.clear();
    this.availableChallenges = list;
    
    this.scrollDistance = -10.0F;
  }


  
  public boolean mouseReleased(double p_mouseReleased_1_, double p_mouseReleased_3_, int p_mouseReleased_5_) {
    return true;
  }


  
  protected int getContentHeight() {
    return (int)(this.availableChallenges.size() * 55.0D - 2.0D);
  }


  
  protected int getScrollAmount() {
    return 12;
  }


  
  public void render(int mouseX, int mouseY, float partialTicks) {
    Tessellator tess = Tessellator.getInstance();







    
    Objects.requireNonNull(this); int baseY = this.top + 4 - (int)this.scrollDistance;
    drawPanel(this.right, baseY, tess, mouseX, mouseY);
  }






  
  protected void drawPanel(int entryRight, int relativeY, Tessellator tess, int mouseX, int mouseY) {
    for (Challenge challenge : this.availableChallenges) {
      
      if (challenge == null) {
        continue;
      }
      float y = relativeY;
      float x = (this.parent.width / 2 - 109 - 40);
      
      String formattedChallengeName = I18n.format(challenge.getId(), new Object[0]);
      String challengeColor = "#FFFFFF";
      
      if (challenge.isComplete()) {
        challengeColor = "#FFAA33";
      }
      if (challenge.isLocked((PlayerEntity)(Minecraft.getInstance()).player)) {
        
        formattedChallengeName = "???";
        challengeColor = "#616161";
      } 
      
      RenderSystem.pushMatrix();
      
      if (isMouseOverChallenge(mouseX, mouseY, challenge)) {
        
        RenderSystem.color3f(0.8F, 0.8F, 0.8F);
        RenderSystem.translated(0.0D, -2.0D, 0.0D);
      } 
      
      RenderSystem.pushMatrix();
      
      Minecraft.getInstance().getTextureManager().bindTexture(ModResources.SCROLL);
      double scale = 0.5D;
      RenderSystem.translated((x - 180.0F), (y - 196.0F), 0.0D);
      RenderSystem.translated(256.0D, 256.0D, 0.0D);
      
      RenderSystem.scaled(scale * 1.1D, scale * 0.6D, 0.0D);
      RenderSystem.translated(-256.0D, -256.0D, 0.0D);

      
      if (this.currentChallenge != null && this.currentChallenge.isPresent() && challenge.equals(this.currentChallenge.get())) {
        
        RenderSystem.pushMatrix();
        
        RenderSystem.color3f(0.7F, 0.5F, 0.3F);
        RenderSystem.scaled(1.04D, 1.07D, 0.0D);
        RenderSystem.translated(-4.0D, -7.0D, 0.0D);
        GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, 1.0F);
        RenderSystem.color3f(1.0F, 1.0F, 1.0F);
        
        RenderSystem.popMatrix();
      } 
      GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, 1.0F);
      
      RenderSystem.popMatrix();
      
      if (this.font.getStringWidth(formattedChallengeName) > 140) {
        
        RenderSystem.pushMatrix();
        
        List<String> splittedText = WyHelper.splitString(this.font, formattedChallengeName, (int)x - 80, 140);
        RenderSystem.translated(0.0D, -((splittedText.size() - 1) * 5), 0.0D);
        for (String string : splittedText) {
          
          WyHelper.drawStringWithBorder(this.font, string, (int)x - 35, (int)y + 16, WyHelper.hexToRGB(challengeColor).getRGB());
          y += 10.0F;
        } 
        
        RenderSystem.popMatrix();
      }
      else {
        
        WyHelper.drawStringWithBorder(this.font, formattedChallengeName, (int)x - 35, (int)y + 16, WyHelper.hexToRGB(challengeColor).getRGB());
      } 
      
      RenderSystem.popMatrix();
      
      relativeY = (int)(relativeY + 55.0D);
    } 
  }


  
  public boolean mouseClicked(double mouseX, double mouseY, int button) {
    Challenge challenge = findChallengeEntry((int)mouseX, (int)mouseY);
    
    if (button != 0 || challenge == null) {
      return false;
    }
    if (challenge.isLocked((PlayerEntity)(Minecraft.getInstance()).player)) {
      return false;
    }
    this.parent.selectChallengeEntry(challenge);
    
    return super.mouseClicked(mouseX, mouseY, button);
  }

  
  private Challenge findChallengeEntry(int mouseX, int mouseY) {
    double offset = ((mouseY - this.top) + this.scrollDistance);
    boolean isHovered = (mouseX - 60 >= this.left && mouseY >= this.top && mouseX < this.left + this.width - 5 && mouseY < this.top + this.height);
    
    if (offset <= 0.0D || !isHovered) {
      return null;
    }
    int lineIdx = (int)(offset / 55.0D);
    if (lineIdx >= this.availableChallenges.size()) {
      return null;
    }
    Challenge challenge = this.availableChallenges.get(lineIdx);
    if (challenge != null)
    {
      return challenge.create();
    }
    
    return null;
  }

  
  public boolean isMouseOverChallenge(double mouseX, double mouseY, Challenge overChallenge) {
    Challenge challenge = findChallengeEntry((int)mouseX, (int)mouseY);
    
    if (challenge != null && challenge.equals(overChallenge))
    {
      return isMouseOver(mouseX, mouseY);
    }
    
    return false;
  }
}


