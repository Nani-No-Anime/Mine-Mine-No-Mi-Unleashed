/*    */ package xyz.pixelatedw.mineminenomi.api.effects;
/*    */ 
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectType;
/*    */ 
/*    */ public class ModEffect
/*    */   extends Effect
/*    */   implements IIgnoreMilkEffect, IBindHandsEffect {
/*    */   public ModEffect(EffectType typeIn, int liquidColorIn) {
/* 10 */     super(typeIn, liquidColorIn);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isBlockingRotations() {
/* 16 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isBlockingSwings() {
/* 22 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 28 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\effects\ModEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */