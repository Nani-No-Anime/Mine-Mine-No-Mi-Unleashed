/*    */ package xyz.pixelatedw.mineminenomi.abilities.gura;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.common.GroundParticlesEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*    */ 
/*    */ public class TenchiMeidoAbility extends ChargeableAbility {
/* 20 */   public static final Ability INSTANCE = (Ability)new TenchiMeidoAbility();
/*    */   
/* 22 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new GroundParticlesEffect(20, 200);
/*    */ 
/*    */   
/*    */   public TenchiMeidoAbility() {
/* 26 */     super("Tenchi Meido", AbilityHelper.getDevilFruitCategory());
/* 27 */     setMaxCooldown(20.0D);
/* 28 */     setMaxChargeTime(1.0D);
/* 29 */     setDescription("The user grabs the air and pulls it downwards, after which all of the opponents are tossed into the air");
/*    */     
/* 31 */     this.duringChargingEvent = this::duringChargingEvent;
/* 32 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringChargingEvent(PlayerEntity player, int chargeTime) {
/* 37 */     if (chargeTime % 2 == 0) {
/* 38 */       PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     }
/* 40 */     player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 10, 0));
/*    */     
/* 42 */     List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 26.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 43 */     targets.remove(player);
/*    */     
/* 45 */     targets.stream()
/* 46 */       .filter(target -> 
/* 47 */         (target != null && target.canEntityBeSeen((Entity)player) && target.isAlive()))
/*    */ 
/*    */       
/* 50 */       .forEach(target -> target.addPotionEffect(new EffectInstance(ModEffects.DIZZY, 10, 0, false, false)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private boolean onEndChargingEvent(PlayerEntity player) {
/* 56 */     List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 26.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 57 */     targets.remove(player);
/*    */     
/* 59 */     targets.stream().filter(target -> (target != null && target.isAlive())).forEach(target -> {
/*    */           Vec3d dirVec = player.getPositionVec().subtract(target.getPositionVec()).normalize().mul(15.0D, 1.0D, 15.0D);
/*    */           
/*    */           target.setMotion(-dirVec.x, 3.0D, -dirVec.z);
/*    */           
/*    */           target.velocityChanged = true;
/*    */         });
/* 66 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\gura\TenchiMeidoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */