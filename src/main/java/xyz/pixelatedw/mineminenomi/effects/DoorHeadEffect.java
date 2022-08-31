/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class DoorHeadEffect
/*    */   extends ModEffect
/*    */ {
/*    */   public DoorHeadEffect() {
/* 12 */     super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldRender(EffectInstance effect) {
/* 18 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldRenderHUD(EffectInstance effect) {
/* 24 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 29 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\effects\DoorHeadEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */