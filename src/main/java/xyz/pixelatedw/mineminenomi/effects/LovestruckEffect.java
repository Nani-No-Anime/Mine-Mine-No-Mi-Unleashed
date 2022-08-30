/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.OverlayEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ public class LovestruckEffect
/*    */   extends OverlayEffect
/*    */ {
/*    */   public LovestruckEffect() {
/* 14 */     super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float[] getOverlayColor() {
/* 20 */     return new float[] { 0.9F, 0.8F, 0.9F, 0.8F };
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasBodyOverlayColor() {
/* 26 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isBlockingRotations() {
/* 32 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getResourceLocation(int duration) {
/* 37 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Block getBlockOverlay() {
/* 43 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 49 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isBlockingSwings() {
/* 55 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\effects\LovestruckEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */