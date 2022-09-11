package xyz.pixelatedw.mineminenomi.screens;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

@OnlyIn(Dist.CLIENT)
public class ArenaSetupScreen
  extends Screen {
  private String generatedArena = "";

  
  protected ArenaSetupScreen(String arenaName) {
    super((ITextComponent)new StringTextComponent(""));
    this.generatedArena = arenaName;
  }

  
  public void render(int mouseX, int mouseY, float partialTicks) {
    String s;
    int posX = (this.width - 256) / 2;
    int posY = (this.height - 256) / 2;
    
    renderDirtBackground(0);
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    
    String message = "Generating \"" + this.generatedArena + "\" Arena...";
    WyHelper.drawStringWithBorder(this.font, TextFormatting.BOLD + message, posX - this.font.getStringWidth(message) / 2 + 120, posY + 120, -1);

    
    switch ((int)(Util.milliTime() / 300L % 4L)) {
      
      default:
        s = "O o o";
        break;
      case 1:
      case 3:
        s = "o O o";
        break;
      case 2:
        s = "o o O";
        break;
    } 
    WyHelper.drawStringWithBorder(this.font, TextFormatting.BOLD + s, this.width / 2 - 10, posY + 140, -1);
  }

  
  public static void open(String arenaName) {
    Minecraft.getInstance().displayGuiScreen(new ArenaSetupScreen(arenaName));
  }
}


