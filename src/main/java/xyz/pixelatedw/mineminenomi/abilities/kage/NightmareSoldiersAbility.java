/*    */ package xyz.pixelatedw.mineminenomi.abilities.kage;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.KageHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.ability.NightmareSoldierEntity;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.kage.ChargeableKageParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*    */ 
/*    */ public class NightmareSoldiersAbility extends ChargeableAbility {
/* 15 */   public static final NightmareSoldiersAbility INSTANCE = new NightmareSoldiersAbility();
/*    */   
/* 17 */   private static final ParticleEffect CHARGE_PARTICLE = (ParticleEffect)new ChargeableKageParticleEffect();
/*    */ 
/*    */   
/*    */   public NightmareSoldiersAbility() {
/* 21 */     super("Nightmare Soldiers", AbilityHelper.getDevilFruitCategory());
/* 22 */     setDescription("Creates Nightmare Soldiers using Shadows from the user's inventory, the longer the ability charges the more soldiers it'll create");
/* 23 */     setMaxCooldown(1.0D);
/* 24 */     setMaxChargeTime(10.0D);
/* 25 */     setCancelable();
/*    */     
/* 27 */     this.onStartChargingEvent = this::onStartChargingEvent;
/* 28 */     this.duringChargingEvent = this::duringChargingEvent;
/* 29 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartChargingEvent(PlayerEntity player) {
/* 34 */     if (KageHelper.getAvailableShadows(player) <= 0) {
/*    */       
/* 36 */       player.sendMessage(KageHelper.NOT_ENOUGH_SHADOWS_WARN);
/* 37 */       return false;
/*    */     } 
/* 39 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringChargingEvent(PlayerEntity player, int chargeTime) {
/* 44 */     CHARGE_PARTICLE.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/* 45 */     player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 20, 2, false, false));
/* 46 */     int chargeup = (getMaxChargeTime() - getChargeTime()) / 20;
/* 47 */     if (chargeTime % 20 == 0)
/*    */     {
/* 49 */       if (KageHelper.getAvailableShadows(player) < chargeup) {
/*    */         
/* 51 */         player.sendMessage(KageHelper.NOT_ENOUGH_SHADOWS_WARN);
/* 52 */         endCharging(player);
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndChargingEvent(PlayerEntity player) {
/* 59 */     int chargeup = (getMaxChargeTime() - getChargeTime()) / 20;
/* 60 */     int cooldown = 2 + chargeup * 2;
/* 61 */     setMaxCooldown(cooldown);
/*    */     
/* 63 */     KageHelper.removeShadows(player, chargeup);
/*    */     
/* 65 */     for (int i = 0; i < chargeup; i++) {
/*    */       
/* 67 */       NightmareSoldierEntity soldier = new NightmareSoldierEntity(player.world);
/* 68 */       soldier.setPositionAndRotation(player.getPosX(), player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
/* 69 */       soldier.setOwner((LivingEntity)player);
/* 70 */       player.world.addEntity((Entity)soldier);
/*    */     } 
/*    */     
/* 73 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\kage\NightmareSoldiersAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */