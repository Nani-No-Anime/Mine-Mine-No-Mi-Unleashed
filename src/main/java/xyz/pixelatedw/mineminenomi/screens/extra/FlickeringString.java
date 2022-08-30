/*    */ package xyz.pixelatedw.mineminenomi.screens.extra;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ public class FlickeringString
/*    */ {
/*    */   private String message;
/*    */   private int flicker;
/*    */   private boolean isVisible = true;
/*    */   private Minecraft mc;
/*    */   
/*    */   public FlickeringString(String str, int flicker) {
/* 15 */     this.mc = Minecraft.getInstance();
/* 16 */     this.message = str;
/* 17 */     this.flicker = flicker;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(int posX, int posY) {
/* 22 */     if (this.mc.getFrameTimer().getIndex() % this.flicker == 0) {
/* 23 */       this.isVisible = !this.isVisible;
/*    */     } else {
/*    */       
/* 26 */       String msg = this.isVisible ? this.message : "";
/*    */       
/* 28 */       WyHelper.drawStringWithBorder(this.mc.fontRenderer, msg, posX, posY, WyHelper.hexToRGB("#FFFFFF").getRGB());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\screens\extra\FlickeringString.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */