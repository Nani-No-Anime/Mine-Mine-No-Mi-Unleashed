/*    */ package xyz.pixelatedw.mineminenomi.screens.extra;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ public class SequencedString
/*    */ {
/*    */   public String string;
/*    */   public int maxLength;
/* 13 */   public int color = Color.WHITE.getRGB();
/*    */   public char[] chars;
/*    */   public int maxTicks;
/*    */   public int ticksExisted;
/*    */   public Minecraft mc;
/* 18 */   public int delayTicks = this.maxTicks;
/*    */ 
/*    */   
/*    */   public SequencedString(String str, int maxLength, int maxTicks) {
/* 22 */     this(str, maxLength, maxTicks, maxTicks + 100);
/*    */   }
/*    */ 
/*    */   
/*    */   public SequencedString(String str, int maxLength, int maxTicks, int delay) {
/* 27 */     this.mc = Minecraft.getInstance();
/* 28 */     this.string = str;
/* 29 */     this.maxLength = maxLength;
/* 30 */     this.chars = new char[this.string.length()];
/* 31 */     for (int i = 0; i < this.string.length(); i++)
/*    */     {
/* 33 */       this.chars[i] = this.string.charAt(i);
/*    */     }
/* 35 */     this.maxTicks = maxTicks;
/* 36 */     this.ticksExisted = 0;
/* 37 */     this.delayTicks = delay;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(int posX, int posY) {
/* 42 */     String tempStr = "";
/* 43 */     for (int i = 0; i < this.chars.length; i++) {
/*    */       
/* 45 */       if (this.ticksExisted >= calculateTicksNeeded(i) && this.ticksExisted < this.delayTicks)
/*    */       {
/* 47 */         tempStr = tempStr + this.chars[i];
/*    */       }
/*    */     } 
/* 50 */     List<String> strings = WyHelper.splitString(this.mc.fontRenderer, tempStr, posX, this.maxLength);
/* 51 */     for (int b = 0; b < strings.size(); b++)
/*    */     {
/* 53 */       WyHelper.drawStringWithBorder(this.mc.fontRenderer, strings.get(b), posX, posY + 10 * b, this.color);
/*    */     }
/*    */     
/* 56 */     this.ticksExisted++;
/*    */   }
/*    */ 
/*    */   
/*    */   public int calculateTicksNeeded(int index) {
/* 61 */     int oldRange = this.string.length();
/* 62 */     int newRange = this.maxTicks;
/* 63 */     int newValue = index * newRange / oldRange;
/*    */     
/* 65 */     return newValue;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\screens\extra\SequencedString.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */