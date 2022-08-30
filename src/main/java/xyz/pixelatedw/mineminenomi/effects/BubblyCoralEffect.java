/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.OverlayEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class BubblyCoralEffect
/*    */   extends OverlayEffect
/*    */ {
/*    */   public BubblyCoralEffect() {
/* 13 */     super(EffectType.NEUTRAL, WyHelper.hexToRGB("#000000").getRGB());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float[] getOverlayColor() {
/* 19 */     return new float[] { 0.0F, 0.41F, 0.58F, 0.3F };
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasBodyOverlayColor() {
/* 25 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isBlockingRotations() {
/* 31 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getResourceLocation(int duration) {
/* 36 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Block getBlockOverlay() {
/* 42 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 48 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\effects\BubblyCoralEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */