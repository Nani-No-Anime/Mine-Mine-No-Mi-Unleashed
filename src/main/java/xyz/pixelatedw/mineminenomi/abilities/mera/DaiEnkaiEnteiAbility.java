/*    */ package xyz.pixelatedw.mineminenomi.abilities.mera;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.mera.DaiEnkaiEnteiProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.mera.DaiEnkai2ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.mera.DaiEnkaiParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.animations.RaiseArmAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*    */ 
/*    */ public class DaiEnkaiEnteiAbility extends ChargeableAbility implements IAnimatedAbility {
/* 19 */   public static final Ability INSTANCE = (Ability)new DaiEnkaiEnteiAbility();
/*    */   
/* 21 */   private static final ParticleEffect PARTICLES_1 = (ParticleEffect)new DaiEnkaiParticleEffect();
/* 22 */   private static final ParticleEffect PARTICLES_2 = (ParticleEffect)new DaiEnkai2ParticleEffect();
/*    */   
/* 24 */   private DaiEnkaiEnteiProjectile proj = null;
/*    */ 
/*    */   
/*    */   public DaiEnkaiEnteiAbility() {
/* 28 */     super("Dai Enkai: Entei", AbilityHelper.getDevilFruitCategory());
/* 29 */     setDescription("Amasses the user's flames into a gigantic fireball that the user hurls at the opponent");
/* 30 */     setMaxCooldown(25.0D);
/* 31 */     setMaxChargeTime(8.0D);
/*    */     
/* 33 */     this.duringChargingEvent = this::duringChargingEvent;
/* 34 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringChargingEvent(PlayerEntity player, int chargeTime) {
/* 39 */     if (this.proj == null) {
/*    */       
/* 41 */       this.proj = new DaiEnkaiEnteiProjectile(player.world, (LivingEntity)player);
/* 42 */       player.world.addEntity((Entity)this.proj);
/*    */     }
/*    */     else {
/*    */       
/* 46 */       this.proj.setLife(this.proj.getMaxLife());
/* 47 */       this.proj.increaseSize();
/* 48 */       this.proj.setPosition(player.getPosX(), player.getPosYEye() + 3.0D, player.getPosZ());
/*    */     } 
/*    */     
/* 51 */     if (chargeTime % 2 == 0) {
/* 52 */       PARTICLES_2.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     }
/*    */   }
/*    */   
/*    */   private boolean onEndChargingEvent(PlayerEntity player) {
/* 57 */     this.proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 1.0F);
/* 58 */     player.world.playSound(null, player.getPosition(), ModSounds.MERA_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*    */     
/* 60 */     this.proj = null;
/* 61 */     PARTICLES_1.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     
/* 63 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IAnimation getAnimation() {
/* 69 */     return (IAnimation)RaiseArmAnimation.INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAnimationActive() {
/* 75 */     return isCharging();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\mera\DaiEnkaiEnteiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */