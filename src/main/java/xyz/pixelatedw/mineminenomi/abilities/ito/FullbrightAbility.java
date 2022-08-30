/*    */ package xyz.pixelatedw.mineminenomi.abilities.ito;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.ito.StringPillarProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.animations.RaiseArmAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*    */ 
/*    */ public class FullbrightAbility extends ChargeableAbility implements IAnimatedAbility {
/* 17 */   public static final FullbrightAbility INSTANCE = new FullbrightAbility();
/*    */ 
/*    */   
/*    */   public FullbrightAbility() {
/* 21 */     super("Fullbright", AbilityHelper.getDevilFruitCategory());
/* 22 */     setDescription("Throws five strings to impale a target from above.");
/* 23 */     setMaxCooldown(12.0D);
/* 24 */     setMaxChargeTime(1.0D);
/* 25 */     this.onStartChargingEvent = this::onStartCharging;
/* 26 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartCharging(PlayerEntity player) {
/* 31 */     player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 20, 1, false, false));
/* 32 */     StringPillarProjectile pillar = new StringPillarProjectile(player.world, (LivingEntity)player);
/* 33 */     pillar.rotationPitch = 90.0F;
/* 34 */     pillar.setMaxLife(20);
/* 35 */     pillar.setPosition(player.getPosX(), player.getPosY(), player.getPosZ());
/* 36 */     pillar.setMotion(0.0D, 0.0D, 0.0D);
/* 37 */     player.world.addEntity((Entity)pillar);
/* 38 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndChargingEvent(PlayerEntity player) {
/* 43 */     for (int a = 0; a < 5; a++) {
/*    */       
/* 45 */       RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player, 32.0D);
/*    */       
/* 47 */       double i = (mop.getHitVec()).x + Math.random() * 1.3D;
/* 48 */       double j = (mop.getHitVec()).y;
/* 49 */       double k = (mop.getHitVec()).z + Math.random() * 1.3D;
/*    */       
/* 51 */       StringPillarProjectile pillar = new StringPillarProjectile(player.world, (LivingEntity)player);
/* 52 */       pillar.rotationPitch = 90.0F;
/* 53 */       pillar.setPosition(i, j + 24.0D, k);
/* 54 */       pillar.setMotion(0.0D, -1.5D, 0.0D);
/* 55 */       player.world.addEntity((Entity)pillar);
/*    */     } 
/*    */     
/* 58 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public IAnimation getAnimation() {
/* 63 */     return (IAnimation)RaiseArmAnimation.INSTANCE;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isAnimationActive() {
/* 68 */     return isCharging();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\ito\FullbrightAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */