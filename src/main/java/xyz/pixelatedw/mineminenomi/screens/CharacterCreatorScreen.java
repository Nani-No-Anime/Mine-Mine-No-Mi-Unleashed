package xyz.pixelatedw.mineminenomi.screens;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.gui.GuiUtils;
import org.lwjgl.opengl.GL11;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.packets.client.CFinishCCPacket;
import xyz.pixelatedw.mineminenomi.screens.extra.TexturedIconButton;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

@OnlyIn(Dist.CLIENT)
public class CharacterCreatorScreen extends Screen {
  private int page;
  private int renderTick = 0; private int maxOpt;
  private int minkTextureId = 0;
  
  private int[] options = new int[3];
  private final String[] FACTIONS = new String[] { "Random", "pirate", "marine", "bounty_hunter", "revolutionary" };
  private final String[] RACES = new String[] { "Random", "human", "fishman", "cyborg", "mink" };
  private final String[] STYLES = new String[] { "Random", "swordsman", "sniper", "doctor", "art_of_weather", "brawler", "black_leg" };
  
  private TexturedIconButton factionButton;
  
  private TexturedIconButton raceButton;
  
  private TexturedIconButton styleButton;
  private boolean hasRandomizedRace;
  
  public CharacterCreatorScreen(boolean hasRandomizedRace) {
    super((ITextComponent)new StringTextComponent(""));
    this.hasRandomizedRace = hasRandomizedRace;
  }


  
  public void render(int x, int y, float f) {
    renderBackground();
    
    int posX = this.width / 2;
    int posY = this.height / 2;

    
    Minecraft.getInstance().getTextureManager().bindTexture(ModResources.BLANK);
    GuiUtils.drawTexturedModalRect(posX - 110, posY - 80, 0, 0, 256, 256, 0.0F);
    
    if (this.page == 0)
    {
      if (getSelectedFactionId() == 0) {
        
        Minecraft.getInstance().getTextureManager().bindTexture(ModResources.RANDOM);
        GuiUtils.drawTexturedModalRect(posX - 115, posY - 110, 0, 0, 256, 256, 1.0F);
        drawCategory("Random", posX + 269, posY + 190, 3.0D);
      }
      else if (getSelectedFactionId() == 1) {
        
        Minecraft.getInstance().getTextureManager().bindTexture(ModResources.PIRATE_ICON);
        GuiUtils.drawTexturedModalRect(posX - 115, posY - 110, 0, 0, 256, 256, 1.0F);
        drawCategory("Pirate", posX + 268, posY + 190, 3.0D);
      }
      else if (getSelectedFactionId() == 2) {
        
        Minecraft.getInstance().getTextureManager().bindTexture(ModResources.MARINE_ICON);
        GuiUtils.drawTexturedModalRect(posX - 115, posY - 110, 0, 0, 256, 256, 1.0F);
        drawCategory("Marine", posX + 269, posY + 190, 3.0D);
      }
      else if (getSelectedFactionId() == 3) {
        
        Minecraft.getInstance().getTextureManager().bindTexture(ModResources.BOUNTY_HUNTER_ICON);
        GuiUtils.drawTexturedModalRect(posX - 115, posY - 110, 0, 0, 256, 256, 1.0F);
        drawCategory("Bounty", posX + 215, posY + 150, 2.7D);
        drawCategory("Hunter", posX + 248, posY + 170, 2.7D);
      }
      else if (getSelectedFactionId() == 4) {
        
        Minecraft.getInstance().getTextureManager().bindTexture(ModResources.REVOLUTIONARY_ARMY_ICON);
        GuiUtils.drawTexturedModalRect(posX - 115, posY - 110, 0, 0, 256, 256, 1.0F);
        drawCategory("Revolutionary", posX + 165, posY + 85, 2.2D);
        drawCategory("Army", posX + 190, posY + 100, 2.2D);
      } 
    }
    if (this.page == 1)
    {
      if (getSelectedRaceId() == 0) {
        
        Minecraft.getInstance().getTextureManager().bindTexture(ModResources.RANDOM);
        GuiUtils.drawTexturedModalRect(posX - 115, posY - 110, 0, 0, 256, 256, 1.0F);
        drawCategory("Random", posX + 269, posY + 190, 3.0D);
      }
      else if (getSelectedRaceId() == 1) {
        
        Minecraft.getInstance().getTextureManager().bindTexture(ModResources.HUMAN);
        GuiUtils.drawTexturedModalRect(posX - 118, posY - 110, 0, 0, 256, 256, 1.0F);
        drawCategory("Human", posX + 268, posY + 190, 3.0D);
      }
      else if (getSelectedRaceId() == 2) {
        
        Minecraft.getInstance().getTextureManager().bindTexture(ModResources.FISHMAN);
        GuiUtils.drawTexturedModalRect(posX - 118, posY - 110, 0, 0, 256, 256, 1.0F);
        drawCategory("Fishman", posX + 268, posY + 190, 3.0D);
      }
      else if (getSelectedRaceId() == 3) {
        
        Minecraft.getInstance().getTextureManager().bindTexture(ModResources.CYBORG);
        GuiUtils.drawTexturedModalRect(posX - 118, posY - 110, 0, 0, 256, 256, 1.0F);
        drawCategory("Cyborg", posX + 268, posY + 190, 3.0D);
      }
      else if (getSelectedRaceId() == 4) {
        
        ResourceLocation[] minkTexture = { ModResources.MINK1, ModResources.MINK2, ModResources.MINK3 };
        if (this.renderTick % 150 == 0)
        {
          if (this.minkTextureId == 2) {
            this.minkTextureId = 0;
          } else {
            this.minkTextureId++;
          } 
        }
        Minecraft.getInstance().getTextureManager().bindTexture(minkTexture[this.minkTextureId]);
        GuiUtils.drawTexturedModalRect(posX - 118, posY - 110, 0, 0, 256, 256, 1.0F);
        drawCategory("Mink", posX + 268, posY + 190, 3.0D);
      } 
    }
    if (this.page == 2)
    {
      if (getSelectedStyleId() == 0) {
        
        Minecraft.getInstance().getTextureManager().bindTexture(ModResources.RANDOM);
        GuiUtils.drawTexturedModalRect(posX - 115, posY - 110, 0, 0, 256, 256, 1.0F);
        drawCategory("Random", posX + 269, posY + 190, 3.0D);
      }
      else if (getSelectedStyleId() == 1) {
        
        Minecraft.getInstance().getTextureManager().bindTexture(ModResources.SWORDSMAN);
        GuiUtils.drawTexturedModalRect(posX - 113, posY - 115, 0, 0, 256, 256, 1.0F);
        drawCategory("Swordsman", posX + 268, posY + 190, 3.0D);
      }
      else if (getSelectedStyleId() == 2) {
        
        Minecraft.getInstance().getTextureManager().bindTexture(ModResources.SNIPER);
        GuiUtils.drawTexturedModalRect(posX - 113, posY - 115, 0, 0, 256, 256, 1.0F);
        drawCategory("Sniper", posX + 268, posY + 190, 3.0D);
      }
      else if (getSelectedStyleId() == 3) {
        
        Minecraft.getInstance().getTextureManager().bindTexture(ModResources.DOCTOR);
        GuiUtils.drawTexturedModalRect(posX - 113, posY - 115, 0, 0, 256, 256, 1.0F);
        drawCategory("Doctor", posX + 268, posY + 190, 3.0D);
      }
      else if (getSelectedStyleId() == 4) {
        
        Minecraft.getInstance().getTextureManager().bindTexture(ModResources.ART_OF_WEATHER);
        GuiUtils.drawTexturedModalRect(posX - 113, posY - 115, 0, 0, 256, 256, 1.0F);
        drawCategory("Art of Weather", posX + 180, posY + 100, 2.3D);
      }
      else if (getSelectedStyleId() == 5) {
        
        Minecraft.getInstance().getTextureManager().bindTexture(ModResources.BRAWLER);
        GuiUtils.drawTexturedModalRect(posX - 113, posY - 115, 0, 0, 256, 256, 1.0F);
        drawCategory("Brawler", posX + 268, posY + 190, 3.0D);
      }
      else if (getSelectedStyleId() == 6) {
        
        Minecraft.getInstance().getTextureManager().bindTexture(ModResources.BLACK_LEG);
        GuiUtils.drawTexturedModalRect(posX - 113, posY - 115, 0, 0, 256, 256, 1.0F);
        drawCategory("Black Leg", posX + 268, posY + 190, 3.0D);
      } 
    }
    
    this.renderTick++;
    super.render(x, y, f);
  }


  
  public void init() {
    int posX = (this.width - 256) / 2;
    int posY = (this.height - 256) / 2;

    
    int posY2 = posY + 65;
    String msg = (new TranslationTextComponent(ModI18n.FACTION_NAME, new Object[0])).getFormattedText();
    this.factionButton = new TexturedIconButton(ModResources.BLANK2_SIMPLE, posX - 58, posY2, 90, 36, msg, btn -> {
          this.page = 0;
          
          resetButtonState();
          this.factionButton.setIsPressed(true);
        });
    this.factionButton = this.factionButton.setTextureInfo(posX - 58, posY + 65, 90, 60).setTextInfo(posX + 50, posY + 169, 1.75D);
    addButton((Widget)this.factionButton);
    posY2 += 45;
    
    msg = (new TranslationTextComponent(ModI18n.RACE_NAME, new Object[0])).getFormattedText();
    this.raceButton = new TexturedIconButton(ModResources.BLANK2_SIMPLE, posX - 58, posY2, 90, 36, msg, btn -> {
          this.page = 1;
          
          resetButtonState();
          this.raceButton.setIsPressed(true);
        });
    this.raceButton = this.raceButton.setTextureInfo(posX - 58, posY + 110, 90, 60).setTextInfo(posX + 50, posY + 214, 1.75D);
    if (!this.hasRandomizedRace) {
      
      addButton((Widget)this.raceButton);
      posY2 += 45;
    } 
    
    msg = (new TranslationTextComponent(ModI18n.STYLE_NAME, new Object[0])).getFormattedText();
    this.styleButton = new TexturedIconButton(ModResources.BLANK2_SIMPLE, posX - 58, posY2, 90, 36, msg, btn -> {
          this.page = 2;
          
          resetButtonState();
          this.styleButton.setIsPressed(true);
        });
    this.styleButton = this.styleButton.setTextureInfo(posX - 58, posY2, 90, 60).setTextInfo(posX + 50, posY2 + 102, 1.75D);
    addButton((Widget)this.styleButton);

    
    TexturedIconButton nextButton = new TexturedIconButton(ModResources.BIG_WOOD_BUTTON_RIGHT, posX + 215, posY + 80, 24, 100, "", btn -> increaseSelectedPage());


    
    nextButton = nextButton.setTextureInfo(posX + 211, posY + 66, 32, 128);
    addButton((Widget)nextButton);
    
    TexturedIconButton prevButton = new TexturedIconButton(ModResources.BIG_WOOD_BUTTON_LEFT, posX + 45, posY + 80, 24, 100, "", btn -> decreaseSelectedPage());


    
    prevButton = prevButton.setTextureInfo(posX + 41, posY + 66, 32, 128);
    addButton((Widget)prevButton);

    
    TexturedIconButton finishButton = new TexturedIconButton(ModResources.BLANK2_SIMPLE, posX + 100, posY + 200, 90, 36, (new TranslationTextComponent(ModI18n.GUI_FINISH, new Object[0])).getFormattedText(), btn -> completeCreation());
    finishButton = finishButton.setTextureInfo(posX + 100, posY + 200, 90, 60).setTextInfo(posX + 217, posY + 304, 1.75D);
    addButton((Widget)finishButton);
  }

  
  private void resetButtonState() {
    this.factionButton.setIsPressed(false);
    this.raceButton.setIsPressed(false);
    this.styleButton.setIsPressed(false);
  }

  
  private void completeCreation() {
    Minecraft.getInstance().displayGuiScreen(null);
    WyNetwork.sendToServer(new CFinishCCPacket(getSelectedFactionId(), getSelectedRaceId(), getSelectedStyleId()));
  }


  
  public void tick() {
    if (this.page == 0)
      this.maxOpt = this.FACTIONS.length; 
    if (this.page == 1)
      this.maxOpt = this.RACES.length; 
    if (this.page == 2) {
      this.maxOpt = this.STYLES.length;
    }
  }
  
  public boolean doesGuiPauseGame() {
    return false;
  }

  
  private void drawCategory(String text, int posX, int posY, double scale) {
    GL11.glPushMatrix();
    
    GL11.glTranslated(posX, posY, 2.0D);
    GL11.glTranslated(128.0D, 128.0D, 0.0D);
    GL11.glScaled(scale, scale, scale);
    GL11.glTranslated(-128.0D, -128.0D, 0.0D);
    
    WyHelper.drawStringWithBorder(this.font, text, -this.font.getStringWidth(text) / 2, 0, WyHelper.hexToRGB("#FFFFFF").getRGB());
    
    GL11.glPopMatrix();
  }

  
  public int getSelectedFactionId() {
    return this.options[0];
  }

  
  public int getSelectedRaceId() {
    return this.options[1];
  }

  
  public int getSelectedStyleId() {
    return this.options[2];
  }

  
  public void increaseSelectedPage() {
    if (this.options[this.page] + 1 < this.maxOpt) {
      this.options[this.page] = this.options[this.page] + 1;
    } else {
      this.options[this.page] = 0;
    } 
  }
  
  public void decreaseSelectedPage() {
    if (this.options[this.page] - 1 > -1) {
      this.options[this.page] = this.options[this.page] - 1;
    } else {
      this.options[this.page] = this.maxOpt - 1;
    } 
  }
  
  public static void open(boolean hasRandomizedRace) {
    Minecraft.getInstance().displayGuiScreen(new CharacterCreatorScreen(hasRandomizedRace));
  }
}


