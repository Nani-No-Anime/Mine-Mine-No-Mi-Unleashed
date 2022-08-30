/*    */ package xyz.pixelatedw.mineminenomi.abilities.fishmankarate;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.fishkarate.KarakusagawaraSeikenChargeParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*    */ 
/*    */ public class KarakusagawaraSeikenAbility
/*    */   extends ChargeableAbility {
/* 19 */   public static final Ability INSTANCE = (Ability)new KarakusagawaraSeikenAbility();
/*    */   
/* 21 */   private static final KarakusagawaraSeikenChargeParticleEffect PARTICLES = new KarakusagawaraSeikenChargeParticleEffect();
/*    */ 
/*    */   
/*    */   public KarakusagawaraSeikenAbility() {
/* 25 */     super("Karakusagawara Seiken", AbilityHelper.getRacialCategory());
/* 26 */     setMaxCooldown(25.0D);
/* 27 */     setMaxChargeTime(2.0D);
/* 28 */     setDescription("The user punches the air, which sends a shockwave through water vapor in the air");
/*    */     
/* 30 */     this.duringChargingEvent = this::duringChargingEvent;
/* 31 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringChargingEvent(PlayerEntity player, int chargeTime) {
/* 36 */     PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndChargingEvent(PlayerEntity player) {
/* 41 */     List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 10.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 42 */     targets.remove(player);
/*    */     
/* 44 */     targets.forEach(entity -> {
/*    */           
/* 46 */           entity.attackEntityFrom(ModDamageSource.causeAbilityDamage((LivingEntity)player, (Ability)this).setDamageBypassesArmor(), (entity.isInWater() || player.isInWater()) ? 50.0F : 20.0F);
/*    */           
/*    */           entity.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 200, 1));
/*    */         });
/* 50 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\fishmankarate\KarakusagawaraSeikenAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */