/*    */ package xyz.pixelatedw.mineminenomi.abilities.ryubrachiosaurus;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.BrachiosaurusHeavyZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.animations.PointBothArmsAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*    */ 
/*    */ public class BrachioGrabAbility extends ChargeableAbility implements IFormRequiredAbility, IAnimatedAbility {
/* 25 */   public static final BrachioGrabAbility INSTANCE = new BrachioGrabAbility();
/* 26 */   private LivingEntity grabbedEntity = null;
/*    */ 
/*    */   
/*    */   public BrachioGrabAbility() {
/* 30 */     super("Brachio Grab", AbilityHelper.getDevilFruitCategory());
/* 31 */     setDescription("Grabs an opponent and squashes them");
/* 32 */     setMaxCooldown(15.0D);
/* 33 */     setMaxChargeTime(3.0D);
/*    */     
/* 35 */     this.onStartChargingEvent = this::onStartChargingEvent;
/* 36 */     this.duringChargingEvent = this::duringChargingEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartChargingEvent(PlayerEntity player) {
/* 41 */     List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 4.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 42 */     list.remove(player);
/* 43 */     list.removeIf(entity -> AbilityHelper.isTargetBlockingAbility((LivingEntity)player, entity));
/* 44 */     if (!HakiHelper.hasHardeningActive((LivingEntity)player)) {
/* 45 */       list.removeIf(entity -> DevilFruitCapability.get(entity).isLogia());
/*    */     }
/* 47 */     this.grabbedEntity = (list.size() > 0) ? list.get(0) : null;
/* 48 */     return (list.size() > 0);
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringChargingEvent(PlayerEntity player, int chargeTimer) {
/* 53 */     if (!this.grabbedEntity.isAlive() || (DevilFruitCapability.get(this.grabbedEntity).isLogia() && !HakiHelper.hasHardeningActive((LivingEntity)player)) || AbilityHelper.isTargetBlockingAbility((LivingEntity)player, this.grabbedEntity)) {
/*    */       
/* 55 */       endCharging(player);
/*    */       
/*    */       return;
/*    */     } 
/* 59 */     this.grabbedEntity.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1));
/* 60 */     player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1));
/*    */     
/* 62 */     this.grabbedEntity.attackEntityFrom((DamageSource)ModDamageSource.causeAbilityDamage((LivingEntity)player, (Ability)this, "player"), 6.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IAnimation getAnimation() {
/* 68 */     return (IAnimation)PointBothArmsAnimation.INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAnimationActive() {
/* 74 */     return isCharging();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo[] getRequiredForms(PlayerEntity player) {
/* 80 */     return new ZoanInfo[] { (ZoanInfo)BrachiosaurusHeavyZoanInfo.INSTANCE };
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\ryubrachiosaurus\BrachioGrabAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */