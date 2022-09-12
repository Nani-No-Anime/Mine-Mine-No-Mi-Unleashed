package xyz.pixelatedw.mineminenomi.screens;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;
import xyz.pixelatedw.mineminenomi.packets.client.crew.CCreateCrewPacket;
import xyz.pixelatedw.mineminenomi.screens.extra.NoTextureButton;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

@OnlyIn(Dist.CLIENT)
public class NewCrewScreen extends Screen {
  private PlayerEntity player;
  private TextFieldWidget nameEdit;
  
  public NewCrewScreen() {
    super((ITextComponent)new StringTextComponent(""));
    this.player = (PlayerEntity)(Minecraft.getInstance()).player;
  }


  
  public void render(int x, int y, float f) {
    renderBackground();
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    
    this.nameEdit.render(x, y, f);
    
    super.render(x, y, f);
  }


  
  public void init() {
    this.minecraft.keyboardListener.enableRepeatEvents(true);
    int posX = this.width / 2;
    int posY = this.height / 2;
    
    this.nameEdit = new TextFieldWidget(this.font, posX - 152, posY - 10, 300, 20, "");
    this.nameEdit.setMaxStringLength(255);
    this.nameEdit.setText(this.player.getName().getFormattedText() + "'s Crew");
    this.children.add(this.nameEdit);
    setFocusedDefault((IGuiEventListener)this.nameEdit);
    
    String createString = "Create";
    NoTextureButton createCrewButton = new NoTextureButton(posX - 30, posY + 50, 60, 16, createString, btn -> createCrew());
    addButton((Widget)createCrewButton);
  }

  
  private void createCrew() {
    WyNetwork.sendToServer(new CCreateCrewPacket(this.nameEdit.getText()));
    onClose();
  }


  
  public void resize(Minecraft minecraft, int x, int y) {
    String crewName = this.nameEdit.getText();
    init(minecraft, x, y);
    this.nameEdit.setText(crewName);
  }


  
  public void tick() {
    this.nameEdit.tick();
  }

  
  public static void open() {
    Minecraft.getInstance().displayGuiScreen(new NewCrewScreen());
  }
}


