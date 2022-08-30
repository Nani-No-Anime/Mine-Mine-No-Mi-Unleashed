/*    */ package xyz.pixelatedw.mineminenomi.screens;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.screen.Screen;
/*    */ import net.minecraft.util.Util;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class ArenaSetupScreen
/*    */   extends Screen {
/* 17 */   private String generatedArena = "";
/*    */ 
/*    */   
/*    */   protected ArenaSetupScreen(String arenaName) {
/* 21 */     super((ITextComponent)new StringTextComponent(""));
/* 22 */     this.generatedArena = arenaName;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(int mouseX, int mouseY, float partialTicks) {
/*    */     String s;
/* 28 */     int posX = (this.width - 256) / 2;
/* 29 */     int posY = (this.height - 256) / 2;
/*    */     
/* 31 */     renderDirtBackground(0);
/* 32 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 34 */     String message = "Generating \"" + this.generatedArena + "\" Arena...";
/* 35 */     WyHelper.drawStringWithBorder(this.font, TextFormatting.BOLD + message, posX - this.font.getStringWidth(message) / 2 + 120, posY + 120, -1);
/*    */ 
/*    */     
/* 38 */     switch ((int)(Util.milliTime() / 300L % 4L)) {
/*    */       
/*    */       default:
/* 41 */         s = "O o o";
/*    */         break;
/*    */       case 1:
/*    */       case 3:
/* 45 */         s = "o O o";
/*    */         break;
/*    */       case 2:
/* 48 */         s = "o o O";
/*    */         break;
/*    */     } 
/* 51 */     WyHelper.drawStringWithBorder(this.font, TextFormatting.BOLD + s, this.width / 2 - 10, posY + 140, -1);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void open(String arenaName) {
/* 56 */     Minecraft.getInstance().displayGuiScreen(new ArenaSetupScreen(arenaName));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\screens\ArenaSetupScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */