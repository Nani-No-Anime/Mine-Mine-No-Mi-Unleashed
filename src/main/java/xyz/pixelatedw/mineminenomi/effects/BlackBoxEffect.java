/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.OverlayEffect;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class BlackBoxEffect
/*    */   extends OverlayEffect
/*    */ {
/*    */   public BlackBoxEffect() {
/* 15 */     super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float[] getOverlayColor() {
/* 21 */     return new float[] { 0.0F, 0.0F, 0.0F, 0.8F };
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasBodyOverlayColor() {
/* 27 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isBlockingRotations() {
/* 33 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getResourceLocation(int duration) {
/* 39 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldRender(EffectInstance effect) {
/* 45 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldRenderHUD(EffectInstance effect) {
/* 51 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Block getBlockOverlay() {
/* 57 */     return ModBlocks.DARKNESS;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 63 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\effects\BlackBoxEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */