/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class DrunkEffect extends ModEffect {
/* 13 */   private double oldHealth = 0.0D;
/* 14 */   private int storedDamage = 0;
/*    */ 
/*    */   
/*    */   public DrunkEffect() {
/* 18 */     super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isReady(int duration, int amplifier) {
/* 24 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void performEffect(LivingEntity entity, int amplifier) {
/* 30 */     if (this.oldHealth == 0.0D) {
/* 31 */       this.oldHealth = entity.getHealth();
/*    */     }
/* 33 */     if (entity.getHealth() < this.oldHealth) {
/*    */       
/* 35 */       double damage = this.oldHealth - entity.getHealth();
/* 36 */       this.storedDamage = (int)(this.storedDamage + damage);
/* 37 */       entity.heal((float)damage);
/* 38 */       this.oldHealth = entity.getHealth();
/*    */     } 
/*    */     
/* 41 */     if (amplifier >= 2 && amplifier < 4) {
/* 42 */       entity.addPotionEffect(new EffectInstance(Effects.NAUSEA, 100, 0));
/* 43 */     } else if (amplifier >= 4) {
/* 44 */       entity.addPotionEffect(new EffectInstance(Effects.NAUSEA, 100, 2));
/*    */     } 
/* 46 */     EffectInstance effect = entity.getActivePotionEffect((Effect)this);
/*    */     
/* 48 */     if (effect.getDuration() < 2 || this.storedDamage > 100 + Math.min(amplifier, 5) * 10) {
/*    */       
/* 50 */       entity.attackEntityFrom(DamageSource.MAGIC, this.storedDamage);
/* 51 */       this.storedDamage = 0;
/* 52 */       this.oldHealth = 0.0D;
/* 53 */       entity.removePotionEffect((Effect)this);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 59 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\effects\DrunkEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */