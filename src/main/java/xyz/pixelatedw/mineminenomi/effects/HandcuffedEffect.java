/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.AttackDamageEffect;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.IBindHandsEffect;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.IIgnoreMilkEffect;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class HandcuffedEffect extends AttackDamageEffect implements IIgnoreMilkEffect, IBindHandsEffect {
/*    */   private boolean isKairosekiCuffs;
/*    */   
/*    */   public HandcuffedEffect(boolean isKairosekiCuffs) {
/* 19 */     super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB(), -20.0D);
/* 20 */     this.isKairosekiCuffs = isKairosekiCuffs;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isReady(int duration, int amplifier) {
/* 26 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void performEffect(LivingEntity entity, int amplifier) {
/* 32 */     EffectInstance instance = entity.getActivePotionEffect((Effect)this);
/*    */     
/* 34 */     if (instance.getDuration() <= 1) {
/*    */       
/* 36 */       entity.removePotionEffect((Effect)this);
/* 37 */       if (entity instanceof PlayerEntity) {
/*    */         
/* 39 */         PlayerEntity player = (PlayerEntity)entity;
/* 40 */         AbilityHelper.enableAbilities(player, ability -> (ability != null && ability.getCategory() == AbilityHelper.getDevilFruitCategory()));
/*    */       } 
/*    */     } 
/*    */     
/* 44 */     if (this.isKairosekiCuffs && entity instanceof PlayerEntity) {
/*    */       
/* 46 */       PlayerEntity player = (PlayerEntity)entity;
/* 47 */       AbilityHelper.disableAbilities(player, instance.getDuration(), ability -> (ability != null && ability.getCategory() == AbilityHelper.getDevilFruitCategory()));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 54 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isBlockingSwings() {
/* 60 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\effects\HandcuffedEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */