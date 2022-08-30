/*    */ package xyz.pixelatedw.mineminenomi.abilities.mero;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.math.EntityRayTraceResult;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.mero.SlaveArrowProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.mero.SlaveArrowParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*    */ 
/*    */ public class SlaveArrowAbility extends ChargeableAbility {
/* 15 */   public static final SlaveArrowAbility INSTANCE = new SlaveArrowAbility();
/*    */   
/* 17 */   private static final SlaveArrowParticleEffect PARTICLES = new SlaveArrowParticleEffect();
/* 18 */   private int limit = 0;
/*    */ 
/*    */   
/*    */   public SlaveArrowAbility() {
/* 22 */     super("Slave Arrow", AbilityHelper.getDevilFruitCategory());
/* 23 */     setMaxCooldown(13.0D);
/* 24 */     setMaxChargeTime(3.0D);
/* 25 */     setDescription("Creates a big heart from which the user shoots multiple love arrows, petrifying enemies");
/*    */     
/* 27 */     this.duringChargingEvent = this::duringChargingEvent;
/* 28 */     this.onEndChargingEvent = this::onEndChargingEvent;
/* 29 */     this.duringCooldownEvent = this::duringCooldownEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringCooldownEvent(PlayerEntity player, int cooldown) {
/* 34 */     int projectileSpace = 1;
/* 35 */     if (this.limit > 0 && (this.cooldown - 10.0D) % 2.0D == 0.0D) {
/*    */       
/* 37 */       SlaveArrowProjectile proj = new SlaveArrowProjectile(player.world, (LivingEntity)player);
/* 38 */       proj.setLocationAndAngles(player
/* 39 */           .getPosX() + WyHelper.randomWithRange(-projectileSpace, projectileSpace) + WyHelper.randomDouble(), player
/* 40 */           .getPosY() + 0.3D + WyHelper.randomWithRange(0, projectileSpace) + WyHelper.randomDouble(), player
/* 41 */           .getPosZ() + WyHelper.randomWithRange(-projectileSpace, projectileSpace) + WyHelper.randomDouble(), 0.0F, 0.0F);
/*    */       
/* 43 */       player.world.addEntity((Entity)proj);
/* 44 */       proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 4.0F);
/* 45 */       this.limit--;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringChargingEvent(PlayerEntity player, int chargeTime) {
/* 51 */     if (chargeTime > getMaxChargeTime() / 2) {
/* 52 */       PARTICLES.setSize(PARTICLES.getSize() + 0.2F);
/* 53 */     } else if (chargeTime <= getMaxChargeTime() / 2) {
/* 54 */       PARTICLES.setSize(PARTICLES.getSize() - 0.05F);
/*    */     } 
/* 56 */     EntityRayTraceResult trace = WyHelper.rayTraceEntities((Entity)player, 0.8D);
/*    */     
/* 58 */     if (chargeTime % 2 == 0)
/* 59 */       PARTICLES.spawn(player.world, trace.getHitVec().getX(), player.getPosY(), trace.getHitVec().getZ(), 0.0D, 0.0D, 0.0D); 
/* 60 */     player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 10, 2000));
/* 61 */     player.setMotion(0.0D, 0.0D, 0.0D);
/* 62 */     player.velocityChanged = true;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndChargingEvent(PlayerEntity player) {
/* 67 */     this.limit = 20;
/*    */     
/* 69 */     PARTICLES.setSize(5.0F);
/*    */     
/* 71 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\mero\SlaveArrowAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */