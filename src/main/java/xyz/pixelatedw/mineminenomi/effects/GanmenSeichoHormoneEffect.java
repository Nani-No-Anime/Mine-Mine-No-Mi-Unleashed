/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.potion.EffectType;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class GanmenSeichoHormoneEffect
/*    */   extends ModEffect
/*    */ {
/*    */   public GanmenSeichoHormoneEffect() {
/* 11 */     super(EffectType.NEUTRAL, WyHelper.hexToRGB("#000000").getRGB());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 17 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\effects\GanmenSeichoHormoneEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */