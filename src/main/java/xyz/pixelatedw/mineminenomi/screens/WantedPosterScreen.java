package xyz.pixelatedw.mineminenomi.screens;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.gui.GuiUtils;
import org.lwjgl.opengl.GL11;
import xyz.pixelatedw.mineminenomi.api.crew.Crew;
import xyz.pixelatedw.mineminenomi.api.crew.JollyRoger;
import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;






public class WantedPosterScreen
  extends Screen
{
  private CompoundNBT wantedData;
  private ExtendedWorldData worldData;
  private JollyRoger jollyRoger;
  private GameProfile profile;
  private UUID wantedPlayerUUID;
  
  public WantedPosterScreen() {
    super((ITextComponent)new StringTextComponent(""));
    this.wantedData = (Minecraft.getInstance()).player.getHeldItemMainhand().getTag();
    
    this.minecraft = Minecraft.getInstance();
    this.worldData = ExtendedWorldData.get(this.minecraft.player.world);
    
    this.wantedPlayerUUID = UUID.fromString(this.wantedData.getString("UUID"));
    this.profile = NBTUtil.readGameProfile((CompoundNBT)this.wantedData.get("Owner"));
    
    Crew crew = this.worldData.getCrewWithMember(this.wantedPlayerUUID);
    if (crew != null) {
      this.jollyRoger = crew.getJollyRoger();
    }
  }



  
  public void render(int x, int y, float f) {
    renderBackground();
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    
    int posX = (this.width - 256) / 2;
    int posY = (this.height - 256) / 2;

    
    GL11.glTranslated((posX + 60), (posY + 10), 0.0D);
    GL11.glTranslated(128.0D, 128.0D, 512.0D);
    GL11.glScaled(1.0D, 0.9D, 0.0D);
    GL11.glTranslated(-128.0D, -128.0D, -512.0D);

    
    this.minecraft.getTextureManager().bindTexture(ModResources.BOUNTY_POSTER_LARGE);
    GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 220, 250, 0.0F);

    
    GL11.glTranslated(67.0D, 150.0D, 0.0D);
    GL11.glTranslated(128.0D, 128.0D, 512.0D);
    GL11.glScaled(1.5D, 1.6D, 0.0D);
    GL11.glTranslated(-128.0D, -128.0D, -512.0D);
    
    String name = this.wantedData.getString("Name");
    String background = this.wantedData.getString("Background");
    String faction = this.wantedData.getString("Faction");
    DecimalFormat decimalFormat = new DecimalFormat("#,##0");
    String bounty = decimalFormat.format(this.wantedData.getLong("Bounty"));

    
    GL11.glPushMatrix();


    
    ResourceLocation rs = new ResourceLocation("mineminenomi", "textures/gui/wantedposters/backgrounds/" + background + ".jpg");
    this.minecraft.getTextureManager().bindTexture(rs);
    
    GL11.glScaled(0.34D, 0.245D, 0.0D);
    GuiUtils.drawTexturedModalRect(23, -57, 0, 0, 256, 256, 2.0F);
    GL11.glDisable(3042);
    
    if (this.profile != null) {
      
      Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map = Minecraft.getInstance().getSkinManager().loadSkinFromCache(this.profile);
      if (map.containsKey(MinecraftProfileTexture.Type.SKIN)) {
        rs = Minecraft.getInstance().getSkinManager().loadSkin(map.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN);
      } else {
        rs = DefaultPlayerSkin.getDefaultSkin(this.wantedPlayerUUID);
      } 
    } else {
      rs = DefaultPlayerSkin.getDefaultSkin(this.wantedPlayerUUID);
    } 
    this.minecraft.getTextureManager().bindTexture(rs);
    
    GL11.glScaled(4.25D, 5.5D, 0.0D);
    GuiUtils.drawTexturedModalRect(20, -3, 32, 32, 32, 32, 3.0F);
    
    RenderSystem.pushMatrix();
    
    RenderSystem.enableBlend();
    RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
    
    double scale = 0.08D;
    RenderSystem.scaled(scale, scale, scale);
    RenderSystem.translated(550.0D, 190.0D, 0.0D);
    
    if (faction.equalsIgnoreCase("pirate")) {
      
      if (this.jollyRoger != null) {
        RendererHelper.drawPlayerJollyRoger(this.jollyRoger);
      }
    } else if (faction.equalsIgnoreCase("revolutionary")) {
      
      this.minecraft.getTextureManager().bindTexture(ModResources.REVOLUTIONARY_ARMY_ICON);
      RenderSystem.scaled(2.0D, 2.0D, 2.0D);
      RenderSystem.translated(-55.0D, -40.0D, 0.0D);
      GuiUtils.drawTexturedModalRect(0, 0, 256, 256, 256, 256, 0.0F);
    } 
    
    RenderSystem.disableBlend();
    
    RenderSystem.popMatrix();
    
    if (this.worldData.getBounty(this.wantedPlayerUUID.toString()) != Long.parseLong(bounty.replaceAll("\\D+", ""))) {
      
      RenderSystem.pushMatrix();
      
      this.minecraft.getTextureManager().bindTexture(ModResources.EXPIRED);
      
      scale = 0.2D;
      RenderSystem.scaled(scale + 0.022D, scale, scale);
      RenderSystem.translated(50.0D, -47.0D, 0.0D);
      GuiUtils.drawTexturedModalRect(0, 0, 16, 16, 256, 256, 0.0F);
      
      RenderSystem.popMatrix();
    } 
    
    GL11.glPopMatrix();
    
    this.minecraft.getTextureManager().bindTexture(ModResources.CURRENCIES);
    GuiUtils.drawTexturedModalRect(-2, 63, 0, 0, 32, 32, 1.0F);
    
    if (name.length() > 13)
      name = name.substring(0, 10) + "..."; 
    this.minecraft.fontRenderer.drawString(TextFormatting.BOLD + name, (47 - this.minecraft.fontRenderer.getStringWidth(name) / 2), 62.0F, WyHelper.hexToRGB("513413").getRGB());
    
    boolean flag = (bounty.length() > 10);
    if (flag) {
      
      GL11.glPushMatrix();
      GL11.glTranslated(-21.0D, -5.0D, 0.0D);
      GL11.glTranslated(128.0D, 128.0D, 512.0D);
      GL11.glScaled(0.82D, 0.89D, 0.0D);
      GL11.glTranslated(-128.0D, -128.0D, -512.0D);
    } 
    
    this.minecraft.fontRenderer.drawString(TextFormatting.BOLD + bounty, 22.0F, 76.0F, WyHelper.hexToRGB("513413").getRGB());
    if (flag) {
      GL11.glPopMatrix();
    }
    
    GL11.glTranslated(-24.0D, -2.0D, 0.0D);
    GL11.glTranslated(128.0D, 128.0D, 512.0D);
    GL11.glScaled(0.78D, 0.92D, 0.0D);
    GL11.glTranslated(-128.0D, -128.0D, -512.0D);
    
    this.minecraft.fontRenderer.drawString(TextFormatting.BOLD + this.wantedData.getString("Date"), (36 - this.minecraft.fontRenderer.getStringWidth(this.wantedData.getString("Date")) / 2), 90.0F, WyHelper.hexToRGB("513413").getRGB());
    
    super.render(x, y, f);
  }


  
  public boolean isPauseScreen() {
    return false;
  }

  
  public static void open() {
    Minecraft.getInstance().displayGuiScreen(new WantedPosterScreen());
  }
}


