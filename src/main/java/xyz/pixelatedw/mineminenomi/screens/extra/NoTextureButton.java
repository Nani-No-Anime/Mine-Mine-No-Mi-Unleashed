/*    */ package xyz.pixelatedw.mineminenomi.screens.extra;
/*    */ 
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.client.gui.widget.button.Button;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ public class NoTextureButton
/*    */   extends Button
/*    */ {
/*    */   private boolean isSelected;
/*    */   private boolean isFake;
/* 15 */   private String onHoverTextColor = "#00FFBB";
/*    */ 
/*    */   
/*    */   public NoTextureButton(int posX, int posY, int width, int height, String string, Button.IPressable onPress) {
/* 19 */     super(posX, posY, width, height, string, onPress);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFake() {
/* 24 */     this.isFake = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setOnHoverTextColor(String color) {
/* 29 */     this.onHoverTextColor = color;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(int mouseX, int mouseY, float partialTicks) {
/* 35 */     RenderSystem.pushMatrix();
/* 36 */     if (this.visible) {
/*    */       
/* 38 */       this.isHovered = (mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height);
/* 39 */       int rgb = WyHelper.hexToRGB("#FFFFFF").getRGB();
/* 40 */       int topGrad = WyHelper.hexToRGB("#B3B3B3").getRGB();
/* 41 */       int bottomGrad = WyHelper.hexToRGB("#939393").getRGB();
/*    */       
/* 43 */       if (this.isHovered) {
/*    */         
/* 45 */         RenderSystem.translated(0.0D, 0.5D, 0.0D);
/* 46 */         rgb = WyHelper.hexToRGB(this.onHoverTextColor).getRGB();
/* 47 */         topGrad = WyHelper.hexToRGB("#B3B3B3").getRGB();
/* 48 */         bottomGrad = WyHelper.hexToRGB("#505050").getRGB();
/*    */         
/* 50 */         if (this.isFake) {
/*    */           
/* 52 */           topGrad = WyHelper.hexToRGB("#3333BB00").getRGB();
/* 53 */           bottomGrad = WyHelper.hexToRGB("#000055FF").getRGB();
/* 54 */           fillGradient(this.x, this.y, this.width + this.x, this.height + this.y, topGrad, bottomGrad);
/*    */         } 
/*    */       } 
/*    */       
/* 58 */       if (this.isSelected) {
/*    */         
/* 60 */         topGrad = WyHelper.hexToRGB("#00CC00").getRGB();
/* 61 */         bottomGrad = WyHelper.hexToRGB("#005500").getRGB();
/*    */       } 
/*    */       
/* 64 */       int outlineSize = 1;
/*    */       
/* 66 */       if (!this.isFake) {
/*    */         
/* 68 */         fillGradient(this.x - outlineSize, this.y - outlineSize, this.width + this.x + outlineSize, this.height + this.y + outlineSize, WyHelper.hexToRGB("#000000").getRGB(), WyHelper.hexToRGB("#000000").getRGB());
/* 69 */         fillGradient(this.x, this.y, this.width + this.x, this.height + this.y, topGrad, bottomGrad);
/*    */         
/* 71 */         FontRenderer font = (Minecraft.getInstance()).fontRenderer;
/* 72 */         WyHelper.drawStringWithBorder(font, getMessage(), this.x - font.getStringWidth(getMessage()) / 2 + this.width / 2, this.y + this.height / 2 - 4, rgb);
/*    */       } 
/*    */       
/* 75 */       RenderSystem.color3f(1.0F, 1.0F, 1.0F);
/*    */     } 
/* 77 */     RenderSystem.popMatrix();
/*    */   }
/*    */ 
/*    */   
/*    */   public void select() {
/* 82 */     this.isSelected = !this.isSelected;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\screens\extra\NoTextureButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */