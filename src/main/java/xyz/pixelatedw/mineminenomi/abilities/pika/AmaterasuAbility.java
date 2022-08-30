/*    */ package xyz.pixelatedw.mineminenomi.abilities.pika;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.pika.AmaterasuProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.pika.ChargingPikaParticleEvent;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.animations.PointArmAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*    */ 
/*    */ public class AmaterasuAbility extends ChargeableAbility implements IAnimatedAbility {
/* 18 */   public static final Ability INSTANCE = (Ability)new AmaterasuAbility();
/*    */   
/* 20 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new ChargingPikaParticleEvent();
/*    */ 
/*    */   
/*    */   public AmaterasuAbility() {
/* 24 */     super("Amaterasu", AbilityHelper.getDevilFruitCategory());
/* 25 */     setMaxCooldown(12.0D);
/* 26 */     setMaxChargeTime(4.0D);
/* 27 */     setCancelable();
/* 28 */     setDescription("Charges up a powerful concentrated light beam\n\nthe longer its charged the more powerful it becomes.");
/*    */ 
/*    */     
/* 31 */     this.onStartChargingEvent = this::onStartChargingEvent;
/* 32 */     this.duringChargingEvent = this::duringChargingEvent;
/* 33 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartChargingEvent(PlayerEntity player) {
/* 38 */     player.world.playMovingSound(null, (Entity)player, ModSounds.PIKA_CHARGE_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/* 39 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringChargingEvent(PlayerEntity player, int timer) {
/* 44 */     AbilityHelper.slowEntityFall((LivingEntity)player);
/* 45 */     PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndChargingEvent(PlayerEntity player) {
/* 50 */     if (getChargeTime() > getMaxChargeTime() - 5) {
/* 51 */       return false;
/*    */     }
/* 53 */     float multi = 1.0F - getChargeTime() / getMaxChargeTime();
/*    */     
/* 55 */     AmaterasuProjectile proj = new AmaterasuProjectile(player.world, (LivingEntity)player);
/* 56 */     proj.setDamage(proj.getDamage() * multi);
/* 57 */     player.world.addEntity((Entity)proj);
/* 58 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 5.0F, 1.0F);
/* 59 */     player.world.playSound(null, player.getPosition(), ModSounds.PIKA_SFX, SoundCategory.PLAYERS, 0.6F, 1.0F);
/* 60 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IAnimation getAnimation() {
/* 66 */     return (IAnimation)PointArmAnimation.INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAnimationActive() {
/* 72 */     return isCharging();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\pika\AmaterasuAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */