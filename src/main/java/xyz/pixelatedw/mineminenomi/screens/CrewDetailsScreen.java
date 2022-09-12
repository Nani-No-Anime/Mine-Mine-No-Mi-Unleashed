package xyz.pixelatedw.mineminenomi.screens;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;
import xyz.pixelatedw.mineminenomi.api.crew.Crew;
import xyz.pixelatedw.mineminenomi.api.crew.JollyRoger;
import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.packets.client.crew.CKickFromCrewPacket;
import xyz.pixelatedw.mineminenomi.packets.client.crew.CLeaveCrewPacket;
import xyz.pixelatedw.mineminenomi.screens.extra.NoTextureButton;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

import java.util.UUID;



@OnlyIn(Dist.CLIENT)
public class CrewDetailsScreen
  extends Screen
{
  private PlayerEntity player;
  private ExtendedWorldData worldProps;
  private JollyRoger jollyRoger;
  private Crew crew;
  
  public CrewDetailsScreen() {
    super((ITextComponent)new StringTextComponent(""));
    this.minecraft = Minecraft.getInstance();
    this.player = (PlayerEntity)this.minecraft.player;
    this.worldProps = ExtendedWorldData.get(this.player.world);
  }


  
  public void render(int x, int y, float f) {
    renderBackground();
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    
    int posX = (this.width - 256) / 2;
    int posY = (this.height - 256) / 2;
    
    String nameString = I18n.format(ModI18n.GUI_NAME, new Object[0]);
    String jollyRogerString = I18n.format(ModI18n.GUI_CREW_JOLLY_ROGER, new Object[0]);
    String membersString = I18n.format(ModI18n.GUI_CREW_MEMBERS, new Object[0]);
    
    String crewActual = "";
    if (this.crew == null) {
      return;
    }
    crewActual = this.crew.getName();
    
    WyHelper.drawStringWithBorder(this.font, TextFormatting.BOLD + nameString + ": " + TextFormatting.RESET + crewActual, posX - 50, posY + 50, -1);
    WyHelper.drawStringWithBorder(this.font, TextFormatting.BOLD + jollyRogerString + ": ", posX - 50, posY + 70, -1);
    WyHelper.drawStringWithBorder(this.font, TextFormatting.BOLD + membersString + ": ", posX + 150, posY + 50, -1);
    
    int memPosY = posY + 70;
    for (Crew.Member member : this.crew.getMembers()) {
      
      String memberName = member.getUsername();
      if (memberName.length() >= 20)
        memberName = memberName.substring(0, 20) + "..."; 
      memberName = memberName + (member.isCaptain() ? (" (" + I18n.format(ModI18n.CREW_CAPTAIN, new Object[0]) + ")") : "");
      WyHelper.drawStringWithBorder(this.font, memberName, posX + 150, memPosY, -1);
      memPosY += 20;
    } 
    
    RenderSystem.pushMatrix();
    
    RenderSystem.enableBlend();
    RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
    
    double scale = 0.4D;
    RenderSystem.translated((posX - 110), (posY + 15), 1.0D);
    RenderSystem.translated(128.0D, 128.0D, 0.0D);
    RenderSystem.scaled(scale, scale, scale);
    RenderSystem.translated(-128.0D, -128.0D, 0.0D);
    
    if (this.jollyRoger != null) {
      RendererHelper.drawPlayerJollyRoger(this.jollyRoger);
    }
    RenderSystem.disableBlend();
    
    RenderSystem.popMatrix();
    
    super.render(x, y, f);
  }


  
  public void init() {
    this.children.clear();
    this.buttons.clear();
    
    this.crew = this.worldProps.getCrewWithMember(this.player.getUniqueID());
    if (this.crew == null)
      return; 
    UUID captainUUID = this.crew.getCaptain().getUUID();
    PlayerEntity crewCaptain = this.minecraft.world.getPlayerByUuid(captainUUID);
    
    this.jollyRoger = this.crew.getJollyRoger();
    
    int posX = (this.width - 256) / 2 - 50;
    int posY = (this.height - 256) / 2;
    
    if (this.crew.getCaptain().getUUID().equals(this.player.getUniqueID())) {
      
      int idx = 0;
      for (Crew.Member member : this.crew.getMembers()) {
        
        if (member.isCaptain()) {
          
          idx++;
          
          continue;
        } 
        NoTextureButton kickButton = new NoTextureButton(posX + 180, posY + 68 + 20 * idx, 10, 10, "X", b -> {
              WyNetwork.sendToServer(new CKickFromCrewPacket(member.getUUID()));
              
              this.crew.removeMember(member.getUUID());
              init();
            });
        kickButton.setOnHoverTextColor("#FF0000");
        addButton((Widget)kickButton);
        idx++;
      } 
    } 
    
    posX += 80;
    addButton((Widget)new Button(posX, posY + 210, 70, 20, I18n.format(ModI18n.GUI_LEAVE, new Object[0]), b -> {
            WyNetwork.sendToServer(new CLeaveCrewPacket());
            
            Minecraft.getInstance().displayGuiScreen(null);
          }));
    
    if (crewCaptain != null && this.player == crewCaptain) {
      
      posX += 80;
      addButton((Widget)new Button(posX, posY + 210, 120, 20, I18n.format(ModI18n.GUI_CHANGE_JOLLY_ROGER, new Object[0]), b -> Minecraft.getInstance().displayGuiScreen(new JollyRogerCreatorScreen(false))));
    } 
  }
}


