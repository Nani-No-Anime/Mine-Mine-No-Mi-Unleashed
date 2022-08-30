/*    */ package xyz.pixelatedw.mineminenomi.abilities.goro;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.SoundEvents;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.goro.KariParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*    */ 
/*    */ public class KariAbility
/*    */   extends ChargeableAbility
/*    */ {
/* 24 */   public static final Ability INSTANCE = (Ability)new KariAbility();
/*    */   
/* 26 */   private static final KariParticleEffect PARTICLES = new KariParticleEffect(1);
/*    */ 
/*    */   
/*    */   public KariAbility() {
/* 30 */     super("Kari", AbilityHelper.getDevilFruitCategory());
/* 31 */     setDescription("The user heats the air around them with lightning until it explodes with a thunder clap. \n\nThis can be used to avoid and neutralize projectiles.");
/* 32 */     setMaxCooldown(12.0D);
/* 33 */     setMaxChargeTime(3.0D);
/* 34 */     setCancelable();
/*    */     
/* 36 */     this.duringChargingEvent = this::duringChargingEvent;
/* 37 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringChargingEvent(PlayerEntity player, int chargeTimer) {
/* 42 */     player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1));
/*    */     
/* 44 */     if (chargeTimer % 2 == 0) {
/*    */       
/* 46 */       float percentage = 1.0F - getChargeTime() / getMaxChargeTime();
/* 47 */       PARTICLES.setRange((int)(6.0F + 10.0F * percentage));
/* 48 */       PARTICLES.setSize(2.0F + 3.0F * percentage);
/* 49 */       PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndChargingEvent(PlayerEntity player) {
/* 55 */     float percentage = 1.0F - getChargeTime() / getMaxChargeTime();
/* 56 */     List<Entity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, (6.0F + 10.0F * percentage), FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { Entity.class });
/* 57 */     list.remove(player);
/*    */     
/* 59 */     for (Entity target : list) {
/*    */       
/* 61 */       if (target instanceof net.minecraft.entity.projectile.ThrowableEntity || target instanceof net.minecraft.entity.projectile.AbstractArrowEntity) {
/* 62 */         target.remove();
/*    */       }
/* 64 */       if (target instanceof LivingEntity) {
/* 65 */         target.attackEntityFrom((DamageSource)ModDamageSource.LIGHTNING_BOLT.causeEntityDamageFromSource((Entity)player), 20.0F + 20.0F * percentage);
/*    */       }
/*    */     } 
/* 68 */     player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, SoundCategory.WEATHER, 2.0F, 0.5F + player.getRNG().nextFloat() * 0.2F);
/*    */     
/* 70 */     setMaxCooldown((1.0F + percentage * (getMaxChargeTime() / 20.0F - 1.0F)));
/* 71 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\goro\KariAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */