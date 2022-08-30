/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ParalysisEffect
/*    */   extends ModEffect
/*    */ {
/*    */   public ParalysisEffect() {
/* 13 */     super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldRender(EffectInstance effect) {
/* 19 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isReady(int duration, int amplifier) {
/* 25 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void performEffect(LivingEntity entity, int amplifier) {
/* 31 */     if (amplifier > 0) {
/* 32 */       entity.setMotion(0.0D, 0.0D, 0.0D);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldRenderHUD(EffectInstance effect) {
/* 38 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 44 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isBlockingSwings() {
/* 50 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\effects\ParalysisEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */