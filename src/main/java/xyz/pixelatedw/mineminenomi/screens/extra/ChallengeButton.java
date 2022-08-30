/*    */ package xyz.pixelatedw.mineminenomi.screens.extra;
/*    */ 
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.widget.button.Button;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ import net.minecraftforge.fml.client.gui.GuiUtils;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChallengeButton
/*    */   extends Button
/*    */ {
/*    */   private Minecraft minecraft;
/*    */   private boolean isSelected;
/*    */   private int unfinished;
/*    */   private static final double HOVER_SCALE = 0.4D;
/*    */   
/*    */   public ChallengeButton(int posX, int posY, int width, int height, String string, int unfinished, Button.IPressable onPress) {
/* 23 */     super(posX, posY, width, height, string, onPress);
/* 24 */     this.minecraft = Minecraft.getInstance();
/* 25 */     this.unfinished = unfinished;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(int mouseX, int mouseY, float partialTicks) {
/* 31 */     RenderSystem.pushMatrix();
/*    */     
/* 33 */     if (this.visible) {
/*    */       
/* 35 */       this.isHovered = (mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height);
/*    */       
/* 37 */       if (this.isHovered) {
/* 38 */         RenderSystem.translated(0.0D, -2.0D, 0.0D);
/*    */       }
/* 40 */       RenderSystem.pushMatrix();
/*    */       
/* 42 */       RenderSystem.translated((this.x - 128), (this.y - 128), 0.0D);
/* 43 */       RenderSystem.translated(128.0D, 128.0D, 0.0D);
/* 44 */       RenderSystem.scaled(0.4D, 0.4D, 0.4D);
/*    */       
/* 46 */       Minecraft.getInstance().getTextureManager().bindTexture(ModResources.CHALLENGE_BLANK);
/* 47 */       if (this.unfinished == 0) {
/*    */         
/* 49 */         RenderSystem.pushMatrix();
/*    */         
/* 51 */         RenderSystem.color4f(1.0F, 1.0F, 0.0F, 1.0F);
/* 52 */         RenderSystem.translated(-12.0D, -12.0D, 0.0D);
/* 53 */         RenderSystem.scaled(1.1D, 1.1D, 1.1D);
/* 54 */         GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, 0.0F);
/* 55 */         RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
/*    */         
/* 57 */         RenderSystem.popMatrix();
/*    */       } 
/* 59 */       GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, 0.0F);
/*    */       
/* 61 */       RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 62 */       Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mineminenomi", "textures/gui/challenges/" + WyHelper.getResourceName(getMessage()) + ".png"));
/* 63 */       GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, 2.0F);
/*    */       
/* 65 */       RenderSystem.translated(-128.0D, -128.0D, 0.0D);
/*    */       
/* 67 */       RenderSystem.popMatrix();
/*    */       
/* 69 */       RenderSystem.pushMatrix();
/*    */       
/* 71 */       int color = -1;
/* 72 */       if (this.unfinished == 0)
/* 73 */         color = WyHelper.hexToRGB("#FFBB11").getRGB(); 
/* 74 */       RenderSystem.translated(0.0D, 0.0D, 1.0D);
/* 75 */       WyHelper.drawStringWithBorder(this.minecraft.fontRenderer, TextFormatting.BOLD + "" + getMessage(), this.x + 46 - this.minecraft.fontRenderer.getStringWidth(getMessage()) / 2, this.y + 83, color);
/* 76 */       RenderSystem.disableBlend();
/*    */       
/* 78 */       RenderSystem.popMatrix();
/*    */     } 
/*    */     
/* 81 */     RenderSystem.popMatrix();
/*    */   }
/*    */ 
/*    */   
/*    */   public void select() {
/* 86 */     this.isSelected = !this.isSelected;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\screens\extra\ChallengeButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */