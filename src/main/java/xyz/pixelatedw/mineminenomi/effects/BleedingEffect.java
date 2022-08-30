/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class BleedingEffect
/*    */   extends Effect
/*    */ {
/*    */   public BleedingEffect() {
/* 13 */     super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isReady(int duration, int amplifier) {
/* 19 */     return (duration % 20 == 0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void performEffect(LivingEntity entity, int amplifier) {
/* 25 */     float damage = Math.max(2, 2 * amplifier);
/* 26 */     entity.attackEntityFrom(DamageSource.MAGIC, damage);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\effects\BleedingEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */