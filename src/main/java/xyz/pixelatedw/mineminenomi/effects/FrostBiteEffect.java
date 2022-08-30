/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.OverlayEffect;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class FrostBiteEffect
/*    */   extends OverlayEffect
/*    */ {
/*    */   public FrostBiteEffect() {
/* 17 */     super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isReady(int duration, int amplifier) {
/* 23 */     return (duration > 200);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void performEffect(LivingEntity entity, int amplifier) {
/* 29 */     entity.addPotionEffect(new EffectInstance(ModEffects.FROZEN, 80, 0, true, true));
/* 30 */     entity.removePotionEffect(ModEffects.FROSTBITE);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Block getBlockOverlay() {
/* 36 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float[] getOverlayColor() {
/* 42 */     return new float[] { 1.0F, 1.0F, 1.0F, 1.0F };
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasBodyOverlayColor() {
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isBlockingRotations() {
/* 54 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getResourceLocation(int value) {
/* 60 */     if (value > 0 && value < 40)
/* 61 */       return ModResources.FROSTBITE_1; 
/* 62 */     if (value > 40 && value < 80)
/* 63 */       return ModResources.FROSTBITE_2; 
/* 64 */     if (value > 80 && value < 120)
/* 65 */       return ModResources.FROSTBITE_3; 
/* 66 */     if (value > 120 && value < 160) {
/* 67 */       return ModResources.FROSTBITE_4;
/*    */     }
/* 69 */     return ModResources.FROSTBITE_5;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\effects\FrostBiteEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */