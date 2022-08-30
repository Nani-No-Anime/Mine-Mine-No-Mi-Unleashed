/*     */ package xyz.pixelatedw.mineminenomi.screens;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.authlib.minecraft.MinecraftProfileTexture;
/*     */ import com.mojang.blaze3d.platform.GlStateManager;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.screen.Screen;
/*     */ import net.minecraft.client.resources.DefaultPlayerSkin;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.NBTUtil;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraftforge.fml.client.gui.GuiUtils;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.JollyRoger;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WantedPosterScreen
/*     */   extends Screen
/*     */ {
/*     */   private CompoundNBT wantedData;
/*     */   private ExtendedWorldData worldData;
/*     */   private JollyRoger jollyRoger;
/*     */   private GameProfile profile;
/*     */   private UUID wantedPlayerUUID;
/*     */   
/*     */   public WantedPosterScreen() {
/*  43 */     super((ITextComponent)new StringTextComponent(""));
/*  44 */     this.wantedData = (Minecraft.getInstance()).player.getHeldItemMainhand().getTag();
/*     */     
/*  46 */     this.minecraft = Minecraft.getInstance();
/*  47 */     this.worldData = ExtendedWorldData.get(this.minecraft.player.world);
/*     */     
/*  49 */     this.wantedPlayerUUID = UUID.fromString(this.wantedData.getString("UUID"));
/*  50 */     this.profile = NBTUtil.readGameProfile((CompoundNBT)this.wantedData.get("Owner"));
/*     */     
/*  52 */     Crew crew = this.worldData.getCrewWithMember(this.wantedPlayerUUID);
/*  53 */     if (crew != null) {
/*  54 */       this.jollyRoger = crew.getJollyRoger();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(int x, int y, float f) {
/*  62 */     renderBackground();
/*  63 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  65 */     int posX = (this.width - 256) / 2;
/*  66 */     int posY = (this.height - 256) / 2;
/*     */ 
/*     */     
/*  69 */     GL11.glTranslated((posX + 60), (posY + 10), 0.0D);
/*  70 */     GL11.glTranslated(128.0D, 128.0D, 512.0D);
/*  71 */     GL11.glScaled(1.0D, 0.9D, 0.0D);
/*  72 */     GL11.glTranslated(-128.0D, -128.0D, -512.0D);
/*     */ 
/*     */     
/*  75 */     this.minecraft.getTextureManager().bindTexture(ModResources.BOUNTY_POSTER_LARGE);
/*  76 */     GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 220, 250, 0.0F);
/*     */ 
/*     */     
/*  79 */     GL11.glTranslated(67.0D, 150.0D, 0.0D);
/*  80 */     GL11.glTranslated(128.0D, 128.0D, 512.0D);
/*  81 */     GL11.glScaled(1.5D, 1.6D, 0.0D);
/*  82 */     GL11.glTranslated(-128.0D, -128.0D, -512.0D);
/*     */     
/*  84 */     String name = this.wantedData.getString("Name");
/*  85 */     String background = this.wantedData.getString("Background");
/*  86 */     String faction = this.wantedData.getString("Faction");
/*  87 */     DecimalFormat decimalFormat = new DecimalFormat("#,##0");
/*  88 */     String bounty = decimalFormat.format(this.wantedData.getLong("Bounty"));
/*     */ 
/*     */     
/*  91 */     GL11.glPushMatrix();
/*     */ 
/*     */ 
/*     */     
/*  95 */     ResourceLocation rs = new ResourceLocation("mineminenomi", "textures/gui/wantedposters/backgrounds/" + background + ".jpg");
/*  96 */     this.minecraft.getTextureManager().bindTexture(rs);
/*     */     
/*  98 */     GL11.glScaled(0.34D, 0.245D, 0.0D);
/*  99 */     GuiUtils.drawTexturedModalRect(23, -57, 0, 0, 256, 256, 2.0F);
/* 100 */     GL11.glDisable(3042);
/*     */     
/* 102 */     if (this.profile != null) {
/*     */       
/* 104 */       Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map = Minecraft.getInstance().getSkinManager().loadSkinFromCache(this.profile);
/* 105 */       if (map.containsKey(MinecraftProfileTexture.Type.SKIN)) {
/* 106 */         rs = Minecraft.getInstance().getSkinManager().loadSkin(map.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN);
/*     */       } else {
/* 108 */         rs = DefaultPlayerSkin.getDefaultSkin(this.wantedPlayerUUID);
/*     */       } 
/*     */     } else {
/* 111 */       rs = DefaultPlayerSkin.getDefaultSkin(this.wantedPlayerUUID);
/*     */     } 
/* 113 */     this.minecraft.getTextureManager().bindTexture(rs);
/*     */     
/* 115 */     GL11.glScaled(4.25D, 5.5D, 0.0D);
/* 116 */     GuiUtils.drawTexturedModalRect(20, -3, 32, 32, 32, 32, 3.0F);
/*     */     
/* 118 */     RenderSystem.pushMatrix();
/*     */     
/* 120 */     RenderSystem.enableBlend();
/* 121 */     RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
/*     */     
/* 123 */     double scale = 0.08D;
/* 124 */     RenderSystem.scaled(scale, scale, scale);
/* 125 */     RenderSystem.translated(550.0D, 190.0D, 0.0D);
/*     */     
/* 127 */     if (faction.equalsIgnoreCase("pirate")) {
/*     */       
/* 129 */       if (this.jollyRoger != null) {
/* 130 */         RendererHelper.drawPlayerJollyRoger(this.jollyRoger);
/*     */       }
/* 132 */     } else if (faction.equalsIgnoreCase("revolutionary")) {
/*     */       
/* 134 */       this.minecraft.getTextureManager().bindTexture(ModResources.REVOLUTIONARY_ARMY_ICON);
/* 135 */       RenderSystem.scaled(2.0D, 2.0D, 2.0D);
/* 136 */       RenderSystem.translated(-55.0D, -40.0D, 0.0D);
/* 137 */       GuiUtils.drawTexturedModalRect(0, 0, 256, 256, 256, 256, 0.0F);
/*     */     } 
/*     */     
/* 140 */     RenderSystem.disableBlend();
/*     */     
/* 142 */     RenderSystem.popMatrix();
/*     */     
/* 144 */     if (this.worldData.getBounty(this.wantedPlayerUUID.toString()) != Long.parseLong(bounty.replaceAll("\\D+", ""))) {
/*     */       
/* 146 */       RenderSystem.pushMatrix();
/*     */       
/* 148 */       this.minecraft.getTextureManager().bindTexture(ModResources.EXPIRED);
/*     */       
/* 150 */       scale = 0.2D;
/* 151 */       RenderSystem.scaled(scale + 0.022D, scale, scale);
/* 152 */       RenderSystem.translated(50.0D, -47.0D, 0.0D);
/* 153 */       GuiUtils.drawTexturedModalRect(0, 0, 16, 16, 256, 256, 0.0F);
/*     */       
/* 155 */       RenderSystem.popMatrix();
/*     */     } 
/*     */     
/* 158 */     GL11.glPopMatrix();
/*     */     
/* 160 */     this.minecraft.getTextureManager().bindTexture(ModResources.CURRENCIES);
/* 161 */     GuiUtils.drawTexturedModalRect(-2, 63, 0, 0, 32, 32, 1.0F);
/*     */     
/* 163 */     if (name.length() > 13)
/* 164 */       name = name.substring(0, 10) + "..."; 
/* 165 */     this.minecraft.fontRenderer.drawString(TextFormatting.BOLD + name, (47 - this.minecraft.fontRenderer.getStringWidth(name) / 2), 62.0F, WyHelper.hexToRGB("513413").getRGB());
/*     */     
/* 167 */     boolean flag = (bounty.length() > 10);
/* 168 */     if (flag) {
/*     */       
/* 170 */       GL11.glPushMatrix();
/* 171 */       GL11.glTranslated(-21.0D, -5.0D, 0.0D);
/* 172 */       GL11.glTranslated(128.0D, 128.0D, 512.0D);
/* 173 */       GL11.glScaled(0.82D, 0.89D, 0.0D);
/* 174 */       GL11.glTranslated(-128.0D, -128.0D, -512.0D);
/*     */     } 
/*     */     
/* 177 */     this.minecraft.fontRenderer.drawString(TextFormatting.BOLD + bounty, 22.0F, 76.0F, WyHelper.hexToRGB("513413").getRGB());
/* 178 */     if (flag) {
/* 179 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 182 */     GL11.glTranslated(-24.0D, -2.0D, 0.0D);
/* 183 */     GL11.glTranslated(128.0D, 128.0D, 512.0D);
/* 184 */     GL11.glScaled(0.78D, 0.92D, 0.0D);
/* 185 */     GL11.glTranslated(-128.0D, -128.0D, -512.0D);
/*     */     
/* 187 */     this.minecraft.fontRenderer.drawString(TextFormatting.BOLD + this.wantedData.getString("Date"), (36 - this.minecraft.fontRenderer.getStringWidth(this.wantedData.getString("Date")) / 2), 90.0F, WyHelper.hexToRGB("513413").getRGB());
/*     */     
/* 189 */     super.render(x, y, f);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPauseScreen() {
/* 195 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void open() {
/* 200 */     Minecraft.getInstance().displayGuiScreen(new WantedPosterScreen());
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\screens\WantedPosterScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */