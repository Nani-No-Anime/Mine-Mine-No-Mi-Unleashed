/*    */ package xyz.pixelatedw.mineminenomi.abilities.yami;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.yami.DarkMatterProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.yami.DarkMatterChargingParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.animations.RaiseArmAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*    */ 
/*    */ public class DarkMatterAbility extends ChargeableAbility implements IAnimatedAbility {
/* 17 */   public static final DarkMatterAbility INSTANCE = new DarkMatterAbility();
/* 18 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new DarkMatterChargingParticleEffect();
/*    */   
/* 20 */   private DarkMatterProjectile proj = null;
/*    */ 
/*    */   
/*    */   public DarkMatterAbility() {
/* 24 */     super("Dark Matter", AbilityHelper.getDevilFruitCategory());
/* 25 */     setDescription("Launches a ball of darkness that engulfs the opponent");
/* 26 */     setMaxCooldown(14.0D);
/* 27 */     setMaxChargeTime(4.0D);
/*    */     
/* 29 */     this.duringChargingEvent = this::duringChargingEvent;
/* 30 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringChargingEvent(PlayerEntity player, int chargeTime) {
/* 35 */     if (this.proj == null) {
/*    */       
/* 37 */       this.proj = new DarkMatterProjectile(player.world, (LivingEntity)player);
/* 38 */       player.world.addEntity((Entity)this.proj);
/*    */     }
/*    */     else {
/*    */       
/* 42 */       this.proj.setLife(this.proj.getMaxLife());
/* 43 */       this.proj.increaseSize();
/* 44 */       this.proj.setPosition(player.getPosX(), player.getPosYEye() + 3.0D, player.getPosZ());
/*    */     } 
/*    */     
/* 47 */     if (chargeTime % 2 == 0) {
/* 48 */       PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     }
/*    */   }
/*    */   
/*    */   private boolean onEndChargingEvent(PlayerEntity player) {
/* 53 */     this.proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 1.0F);
/* 54 */     player.world.playSound(null, player.getPosition(), ModSounds.MERA_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*    */     
/* 56 */     this.proj = null;
/*    */     
/* 58 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IAnimation getAnimation() {
/* 64 */     return (IAnimation)RaiseArmAnimation.INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAnimationActive() {
/* 70 */     return isCharging();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\yami\DarkMatterAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */