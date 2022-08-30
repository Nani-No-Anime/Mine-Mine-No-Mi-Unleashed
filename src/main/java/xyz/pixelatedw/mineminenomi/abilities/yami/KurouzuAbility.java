/*    */ package xyz.pixelatedw.mineminenomi.abilities.yami;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.yami.KurouzuChargeParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.yami.KurouzuParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.animations.PointArmAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*    */ 
/*    */ public class KurouzuAbility
/*    */   extends ChargeableAbility implements IAnimatedAbility {
/* 28 */   public static final KurouzuAbility INSTANCE = new KurouzuAbility();
/*    */   
/* 30 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new KurouzuParticleEffect();
/* 31 */   private static final KurouzuChargeParticleEffect PARTICLES_CHARGE = new KurouzuChargeParticleEffect();
/* 32 */   private List<LivingEntity> entities = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public KurouzuAbility() {
/* 36 */     super("Kurouzu", AbilityHelper.getDevilFruitCategory());
/* 37 */     setDescription("Creates a strong gravitational force, that pulls the opponent towards the user");
/* 38 */     setMaxCooldown(12.0D);
/* 39 */     setMaxChargeTime(3.0D);
/* 40 */     setCancelable();
/*    */     
/* 42 */     this.duringChargingEvent = this::duringChargingEvent;
/* 43 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private void duringChargingEvent(PlayerEntity player, int chargeTimer) {
/* 49 */     RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player, 100.0D);
/*    */     
/* 51 */     double i = (mop.getHitVec()).x;
/* 52 */     double j = (mop.getHitVec()).y - ((mop instanceof net.minecraft.util.math.EntityRayTraceResult) ? 1 : 0);
/* 53 */     double k = (mop.getHitVec()).z;
/*    */     
/* 55 */     if (chargeTimer % 5 == 0) {
/* 56 */       PARTICLES.spawn(player.world, i, j, k, 0.0D, 0.0D, 0.0D);
/*    */     }
/* 58 */     if (chargeTimer % 5 == 0) {
/*    */       
/* 60 */       Vec3d lookVec = player.getLookVec();
/* 61 */       Vec3d particlePos = player.getPositionVec().add(lookVec.x, lookVec.y + 1.5D, lookVec.z);
/* 62 */       PARTICLES_CHARGE.spawn(player.world, particlePos.getX(), particlePos.getY(), particlePos.getZ(), 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */     
/* 65 */     this.entities.clear();
/* 66 */     List<LivingEntity> targets = WyHelper.getEntitiesNear(new BlockPos(i, j, k), player.world, 5.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 67 */     targets.remove(player);
/*    */     
/* 69 */     this.entities.addAll(targets);
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndChargingEvent(PlayerEntity player) {
/* 74 */     for (LivingEntity target : this.entities) {
/*    */       
/* 76 */       target.setPositionAndUpdate(player.getPosX(), player.getPosY(), player.getPosZ());
/* 77 */       target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200, 5));
/* 78 */       target.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 200, 5));
/* 79 */       target.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 200, 5));
/* 80 */       target.addPotionEffect(new EffectInstance(ModEffects.ABILITY_OFF, 200, 1, false, false, false));
/*    */     } 
/*    */     
/* 83 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAnimationActive() {
/* 89 */     return isCharging();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IAnimation getAnimation() {
/* 95 */     return (IAnimation)PointArmAnimation.INSTANCE;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\yami\KurouzuAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */