/*    */ package xyz.pixelatedw.mineminenomi.screens.extra;
/*    */ 
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.client.gui.widget.button.Button;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.common.util.TextTable;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class NewButton
/*    */   extends Button {
/*    */   private IEntityStats entityData;
/*    */   private boolean isSelected;
/* 19 */   private TextTable.Alignment textAlignment = TextTable.Alignment.LEFT;
/* 20 */   private int lineThickness = 1;
/*    */   
/*    */   private boolean hasIcons = true;
/*    */   
/*    */   private int blackColor;
/*    */   
/*    */   public NewButton(int posX, int posY, int width, int height, String string, Button.IPressable onPress) {
/* 27 */     super(posX, posY, width, height, string, onPress);
/* 28 */     this.blackColor = WyHelper.hexToRGB("#000000").getRGB();
/* 29 */     this.entityData = EntityStatsCapability.get((LivingEntity)(Minecraft.getInstance()).player);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTextAlignment(TextTable.Alignment alignment) {
/* 34 */     this.textAlignment = alignment;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLineThickness(int thickness) {
/* 39 */     this.lineThickness = thickness;
/*    */   }
/*    */ 
/*    */   
/*    */   public void disableIcons() {
/* 44 */     this.hasIcons = false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(int mouseX, int mouseY, float partialTicks) {
/* 50 */     RenderSystem.pushMatrix();
/* 51 */     if (this.visible) {
/*    */       int textPosX;
/* 53 */       this.isHovered = (mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height);
/* 54 */       int rgb = WyHelper.hexToRGB("#FFFFFF").getRGB();
/* 55 */       int lineColor = WyHelper.hexToRGB("#EAEAEA").getRGB();
/*    */       
/* 57 */       if (this.isHovered) {
/*    */         
/* 59 */         RenderSystem.translated(0.0D, 0.5D, 0.0D);
/* 60 */         int factionColor = FactionHelper.getFactionColor(this.entityData);
/* 61 */         rgb = lineColor = factionColor;
/*    */       } 
/*    */       
/* 64 */       FontRenderer font = (Minecraft.getInstance()).fontRenderer;
/*    */ 
/*    */       
/* 67 */       if (this.textAlignment == TextTable.Alignment.CENTER) {
/* 68 */         textPosX = this.x - font.getStringWidth(getMessage()) / 2 + this.width / 2;
/* 69 */       } else if (this.textAlignment == TextTable.Alignment.RIGHT) {
/* 70 */         textPosX = this.x;
/*    */       } else {
/* 72 */         textPosX = this.x;
/*    */       } 
/* 74 */       fillGradient(this.x - 4, this.y + this.height - this.lineThickness + 2, this.width + this.x + 1, this.y + this.height, this.blackColor, this.blackColor);
/* 75 */       fillGradient(this.x - 5, this.y + this.height - this.lineThickness, this.width + this.x, this.y + this.height, lineColor, lineColor);
/*    */       
/* 77 */       int textOffset = 0;
/*    */       
/* 79 */       if (this.hasIcons) {
/*    */         
/* 81 */         ResourceLocation factionIcon = FactionHelper.getFactionIcon(this.entityData);
/* 82 */         if (factionIcon != null) {
/*    */           
/* 84 */           WyHelper.drawIcon(factionIcon, this.x - 12, this.y - 4, 32, 32, this.blackColor);
/* 85 */           WyHelper.drawIcon(factionIcon, this.x - 13, this.y - 5, 32, 32, lineColor);
/* 86 */           textOffset = 13;
/*    */         } 
/*    */       } 
/*    */       
/* 90 */       WyHelper.drawStringWithBorder(font, getMessage(), textPosX + textOffset, this.y + this.height / 2 - 4, rgb);
/*    */     } 
/* 92 */     RenderSystem.popMatrix();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\screens\extra\NewButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */