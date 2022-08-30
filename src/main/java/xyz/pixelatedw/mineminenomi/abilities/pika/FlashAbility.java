/*    */ package xyz.pixelatedw.mineminenomi.abilities.pika;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.pika.ChargingPikaParticleEvent;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.pika.FlashParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*    */ 
/*    */ public class FlashAbility
/*    */   extends ChargeableAbility {
/* 21 */   public static final Ability INSTANCE = (Ability)new FlashAbility();
/*    */   
/* 23 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new FlashParticleEffect();
/* 24 */   private static final ParticleEffect CHARGE_PARTICLE = (ParticleEffect)new ChargingPikaParticleEvent();
/*    */ 
/*    */   
/*    */   public FlashAbility() {
/* 28 */     super("Flash", AbilityHelper.getDevilFruitCategory());
/* 29 */     setMaxCooldown(10.0D);
/* 30 */     setMaxChargeTime(2.0D);
/* 31 */     setCancelable();
/* 32 */     setDescription("The user creates a bright flash of light, blinding their opponents");
/*    */     
/* 34 */     this.duringChargingEvent = this::duringChargingEvent;
/* 35 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringChargingEvent(PlayerEntity player, int timer) {
/* 40 */     CHARGE_PARTICLE.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndChargingEvent(PlayerEntity player) {
/* 45 */     AbilityHelper.reduceEffect(player.getActivePotionEffect(ModEffects.FROZEN), 10.0D);
/* 46 */     AbilityHelper.reduceEffect(player.getActivePotionEffect(ModEffects.FROSTBITE), 10.0D);
/* 47 */     AbilityHelper.reduceEffect(player.getActivePotionEffect(ModEffects.CANDY_STUCK), 10.0D);
/* 48 */     AbilityHelper.reduceEffect(player.getActivePotionEffect(ModEffects.CANDLE_LOCK), 10.0D);
/*    */ 
/*    */     
/* 51 */     float time = getChargeTime() / getMaxChargeTime();
/* 52 */     float radius = (1.0F - time) * 16.0F;
/* 53 */     List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, radius, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 54 */     list.remove(player);
/*    */     
/* 56 */     list.forEach(entity -> {
/*    */           entity.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 140, 3));
/*    */           
/*    */           entity.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 60, 0));
/*    */           
/*    */           PARTICLES.spawn(player.world, entity.getPosX(), entity.getPosY() + entity.getEyeHeight(), entity.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */         });
/* 63 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\pika\FlashAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */