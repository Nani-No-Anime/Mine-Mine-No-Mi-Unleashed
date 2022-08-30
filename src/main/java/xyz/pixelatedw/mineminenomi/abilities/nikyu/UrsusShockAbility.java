/*    */ package xyz.pixelatedw.mineminenomi.abilities.nikyu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu.ChargingUrsusShockEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu.UrsusShockProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.renderers.animations.RaiseBothArmAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*    */ 
/*    */ public class UrsusShockAbility extends ChargeableAbility implements IAnimatedAbility {
/* 17 */   public static final Ability INSTANCE = (Ability)new UrsusShockAbility();
/*    */   
/* 19 */   private int power = 0;
/*    */   
/*    */   private ChargingUrsusShockEntity ursusShockEntity;
/*    */   
/*    */   public UrsusShockAbility() {
/* 24 */     super("Ursus Shock", AbilityHelper.getDevilFruitCategory());
/* 25 */     setDescription("The user compresses air and sends it towards the opponent to create a huge shockwave");
/* 26 */     setMaxCooldown(20.0D);
/* 27 */     setMaxChargeTime(16.0D);
/*    */ 
/*    */     
/* 30 */     this.duringChargingEvent = this::duringChargingEvent;
/* 31 */     this.onStartChargingEvent = this::onStartChargingEvent;
/* 32 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringChargingEvent(PlayerEntity player, int chargeTimer) {
/* 37 */     if (this.ursusShockEntity == null) {
/*    */       
/* 39 */       endCharging(player);
/*    */       
/*    */       return;
/*    */     } 
/* 43 */     this.power = chargeTimer;
/* 44 */     double truePower = Math.abs(this.power - getMaxChargeTime());
/*    */     
/* 46 */     float currentCharge = this.ursusShockEntity.getCharge();
/* 47 */     if (-5.0F > currentCharge) {
/* 48 */       this.ursusShockEntity.remove();
/*    */     } else {
/* 50 */       currentCharge = (float)(currentCharge + ((truePower < 190.0D) ? 0.025D : -0.045D));
/* 51 */       this.ursusShockEntity.setCharge(currentCharge);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartChargingEvent(PlayerEntity player) {
/* 57 */     player.world.playMovingSound(null, (Entity)player, ModSounds.URSUS_SHOCK_SFX, SoundCategory.PLAYERS, 2.0F, 2.0F);
/* 58 */     ChargingUrsusShockEntity entity = new ChargingUrsusShockEntity(player.world);
/* 59 */     entity.setOwner((LivingEntity)player);
/* 60 */     entity.setPositionAndRotation(player.getPosX(), player.getPosY() + 2.0D, player.getPosZ(), 0.0F, 0.0F);
/* 61 */     player.world.addEntity((Entity)entity);
/* 62 */     this.ursusShockEntity = entity;
/* 63 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndChargingEvent(PlayerEntity player) {
/* 68 */     if (getChargeTime() > getMaxChargeTime() - 190) {
/* 69 */       return false;
/*    */     }
/* 71 */     float multiplier = 1.0F - getChargeTime() / getMaxChargeTime();
/*    */     
/* 73 */     UrsusShockProjectile projectile = new UrsusShockProjectile(player.world, (LivingEntity)player);
/* 74 */     projectile.multiplier = multiplier;
/* 75 */     projectile.setSize((multiplier > 0.75D) ? 0.6F : (5.0F * (1.0F - multiplier)));
/* 76 */     setMaxCooldown((20.0F * multiplier));
/*    */     
/* 78 */     player.world.addEntity((Entity)projectile);
/* 79 */     projectile.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 0.5F, 0.0F);
/*    */     
/* 81 */     if (this.ursusShockEntity != null) {
/* 82 */       this.ursusShockEntity.remove();
/*    */     }
/* 84 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IAnimation getAnimation() {
/* 90 */     return (IAnimation)RaiseBothArmAnimation.INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAnimationActive() {
/* 96 */     return isCharging();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\nikyu\UrsusShockAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */