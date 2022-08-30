/*    */ package xyz.pixelatedw.mineminenomi.abilities.yami;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.yami.BlackRoadProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.yami.DarkMatterChargingParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*    */ 
/*    */ public class BlackRoadAbility extends ChargeableAbility {
/* 12 */   public static final BlackRoadAbility INSTANCE = new BlackRoadAbility();
/*    */   
/* 14 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new DarkMatterChargingParticleEffect();
/*    */ 
/*    */   
/*    */   public BlackRoadAbility() {
/* 18 */     super("Black Road", AbilityHelper.getDevilFruitCategory());
/* 19 */     setMaxCooldown(20.0D);
/* 20 */     setMaxChargeTime(6.0D);
/* 21 */     setCancelable();
/* 22 */     setDescription("The user spreads darkness in a forward path");
/*    */     
/* 24 */     this.duringChargingEvent = this::duringChargingEvent;
/* 25 */     this.onEndChargingEvent = this::endChargingEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringChargingEvent(PlayerEntity player, int chargeTime) {
/* 30 */     if (chargeTime % 2 == 0) {
/* 31 */       PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     }
/*    */   }
/*    */   
/*    */   private boolean endChargingEvent(PlayerEntity player) {
/* 36 */     float time = getChargeTime() / getMaxChargeTime();
/* 37 */     float multiplier = 1.0F - time;
/* 38 */     int range = (int)(24.0F * multiplier);
/*    */     
/* 40 */     BlackRoadProjectile proj = new BlackRoadProjectile(player.world, (LivingEntity)player);
/* 41 */     proj.setMaxLife(range);
/* 42 */     player.world.addEntity((Entity)proj);
/* 43 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 1.1F, 5.0F);
/*    */     
/* 45 */     PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     
/* 47 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\yami\BlackRoadAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */