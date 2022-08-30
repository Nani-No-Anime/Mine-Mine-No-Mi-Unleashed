/*     */ package xyz.pixelatedw.mineminenomi.screens;
/*     */ 
/*     */ import com.mojang.blaze3d.platform.GlStateManager;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.screen.Screen;
/*     */ import net.minecraft.client.gui.widget.Widget;
/*     */ import net.minecraft.client.gui.widget.button.Button;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.JollyRoger;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.crew.CKickFromCrewPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.crew.CLeaveCrewPacket;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.NoTextureButton;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ 
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class CrewDetailsScreen
/*     */   extends Screen
/*     */ {
/*     */   private PlayerEntity player;
/*     */   private ExtendedWorldData worldProps;
/*     */   private JollyRoger jollyRoger;
/*     */   private Crew crew;
/*     */   
/*     */   public CrewDetailsScreen() {
/*  41 */     super((ITextComponent)new StringTextComponent(""));
/*  42 */     this.minecraft = Minecraft.getInstance();
/*  43 */     this.player = (PlayerEntity)this.minecraft.player;
/*  44 */     this.worldProps = ExtendedWorldData.get(this.player.world);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(int x, int y, float f) {
/*  50 */     renderBackground();
/*  51 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  53 */     int posX = (this.width - 256) / 2;
/*  54 */     int posY = (this.height - 256) / 2;
/*     */     
/*  56 */     String nameString = I18n.format(ModI18n.GUI_NAME, new Object[0]);
/*  57 */     String jollyRogerString = I18n.format(ModI18n.GUI_CREW_JOLLY_ROGER, new Object[0]);
/*  58 */     String membersString = I18n.format(ModI18n.GUI_CREW_MEMBERS, new Object[0]);
/*     */     
/*  60 */     String crewActual = "";
/*  61 */     if (this.crew == null) {
/*     */       return;
/*     */     }
/*  64 */     crewActual = this.crew.getName();
/*     */     
/*  66 */     WyHelper.drawStringWithBorder(this.font, TextFormatting.BOLD + nameString + ": " + TextFormatting.RESET + crewActual, posX - 50, posY + 50, -1);
/*  67 */     WyHelper.drawStringWithBorder(this.font, TextFormatting.BOLD + jollyRogerString + ": ", posX - 50, posY + 70, -1);
/*  68 */     WyHelper.drawStringWithBorder(this.font, TextFormatting.BOLD + membersString + ": ", posX + 150, posY + 50, -1);
/*     */     
/*  70 */     int memPosY = posY + 70;
/*  71 */     for (Crew.Member member : this.crew.getMembers()) {
/*     */       
/*  73 */       String memberName = member.getUsername();
/*  74 */       if (memberName.length() >= 20)
/*  75 */         memberName = memberName.substring(0, 20) + "..."; 
/*  76 */       memberName = memberName + (member.isCaptain() ? (" (" + I18n.format(ModI18n.CREW_CAPTAIN, new Object[0]) + ")") : "");
/*  77 */       WyHelper.drawStringWithBorder(this.font, memberName, posX + 150, memPosY, -1);
/*  78 */       memPosY += 20;
/*     */     } 
/*     */     
/*  81 */     RenderSystem.pushMatrix();
/*     */     
/*  83 */     RenderSystem.enableBlend();
/*  84 */     RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
/*     */     
/*  86 */     double scale = 0.4D;
/*  87 */     RenderSystem.translated((posX - 110), (posY + 15), 1.0D);
/*  88 */     RenderSystem.translated(128.0D, 128.0D, 0.0D);
/*  89 */     RenderSystem.scaled(scale, scale, scale);
/*  90 */     RenderSystem.translated(-128.0D, -128.0D, 0.0D);
/*     */     
/*  92 */     if (this.jollyRoger != null) {
/*  93 */       RendererHelper.drawPlayerJollyRoger(this.jollyRoger);
/*     */     }
/*  95 */     RenderSystem.disableBlend();
/*     */     
/*  97 */     RenderSystem.popMatrix();
/*     */     
/*  99 */     super.render(x, y, f);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() {
/* 105 */     this.children.clear();
/* 106 */     this.buttons.clear();
/*     */     
/* 108 */     this.crew = this.worldProps.getCrewWithMember(this.player.getUniqueID());
/* 109 */     if (this.crew == null)
/*     */       return; 
/* 111 */     UUID captainUUID = this.crew.getCaptain().getUUID();
/* 112 */     PlayerEntity crewCaptain = this.minecraft.world.getPlayerByUuid(captainUUID);
/*     */     
/* 114 */     this.jollyRoger = this.crew.getJollyRoger();
/*     */     
/* 116 */     int posX = (this.width - 256) / 2 - 50;
/* 117 */     int posY = (this.height - 256) / 2;
/*     */     
/* 119 */     if (this.crew.getCaptain().getUUID().equals(this.player.getUniqueID())) {
/*     */       
/* 121 */       int idx = 0;
/* 122 */       for (Crew.Member member : this.crew.getMembers()) {
/*     */         
/* 124 */         if (member.isCaptain()) {
/*     */           
/* 126 */           idx++;
/*     */           
/*     */           continue;
/*     */         } 
/* 130 */         NoTextureButton kickButton = new NoTextureButton(posX + 180, posY + 68 + 20 * idx, 10, 10, "X", b -> {
/*     */               WyNetwork.sendToServer(new CKickFromCrewPacket(member.getUUID()));
/*     */               
/*     */               this.crew.removeMember(member.getUUID());
/*     */               init();
/*     */             });
/* 136 */         kickButton.setOnHoverTextColor("#FF0000");
/* 137 */         addButton((Widget)kickButton);
/* 138 */         idx++;
/*     */       } 
/*     */     } 
/*     */     
/* 142 */     posX += 80;
/* 143 */     addButton((Widget)new Button(posX, posY + 210, 70, 20, I18n.format(ModI18n.GUI_LEAVE, new Object[0]), b -> {
/*     */             WyNetwork.sendToServer(new CLeaveCrewPacket());
/*     */             
/*     */             Minecraft.getInstance().displayGuiScreen(null);
/*     */           }));
/*     */     
/* 149 */     if (crewCaptain != null && this.player == crewCaptain) {
/*     */       
/* 151 */       posX += 80;
/* 152 */       addButton((Widget)new Button(posX, posY + 210, 120, 20, I18n.format(ModI18n.GUI_CHANGE_JOLLY_ROGER, new Object[0]), b -> Minecraft.getInstance().displayGuiScreen(new JollyRogerCreatorScreen(false))));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\screens\CrewDetailsScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */