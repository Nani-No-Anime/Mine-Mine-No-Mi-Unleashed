/*    */ package xyz.pixelatedw.mineminenomi.abilities.doru;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ 
/*    */ public class DoruDoruBallAbility extends ContinuousAbility {
/* 15 */   public static final DoruDoruBallAbility INSTANCE = new DoruDoruBallAbility();
/* 16 */   public double rotateAngleX = 0.0D;
/* 17 */   public double rotateAngleZ = 0.0D;
/*    */ 
/*    */   
/*    */   public DoruDoruBallAbility() {
/* 21 */     super("Doru Doru Ball", AbilityHelper.getDevilFruitCategory());
/* 22 */     setThreshold(8.0D);
/* 23 */     setMaxCooldown(10.0D);
/* 24 */     setDescription("Puts the user into a hardened wax ball for max defense");
/* 25 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 26 */     this.duringContinuityEvent = this::duringContinuity;
/* 27 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */     
/* 29 */     addInPool(new AbilityPool[] { AbilityPool.TEKKAI_LIKE });
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 34 */     this.rotateAngleX = 0.0D;
/* 35 */     this.rotateAngleZ = 0.0D;
/* 36 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuity(PlayerEntity player, int passiveTime) {
/* 41 */     player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 20, 5, false, false));
/* 42 */     player.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 20, 5, false, false));
/*    */     
/* 44 */     int power = 5;
/* 45 */     Ability ability = AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)CandleChampionAbility.INSTANCE);
/* 46 */     if (ability != null && ability.isContinuous()) {
/* 47 */       power = 10;
/*    */     }
/* 49 */     if (!player.isBurning()) {
/* 50 */       player.addPotionEffect(new EffectInstance(ModEffects.GUARDING_WITH_MOVEMENT, 2, power, false, false));
/*    */     }
/*    */   }
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 55 */     this.rotateAngleX = 0.0D;
/* 56 */     this.rotateAngleZ = 0.0D;
/* 57 */     player.removePotionEffect(Effects.SLOWNESS);
/* 58 */     player.removePotionEffect(Effects.MINING_FATIGUE);
/*    */     
/* 60 */     int cooldown = (int)Math.round(this.continueTime / 25.0D) + 3;
/* 61 */     setMaxCooldown(cooldown);
/* 62 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\doru\DoruDoruBallAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */