/*    */ package xyz.pixelatedw.mineminenomi.abilities.ope;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ope.CounterShockParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class CounterShockAbility extends PunchAbility {
/* 17 */   public static final Ability INSTANCE = (Ability)new CounterShockAbility();
/*    */   
/* 19 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new CounterShockParticleEffect();
/*    */ 
/*    */   
/*    */   public CounterShockAbility() {
/* 23 */     super("Counter Shock", AbilityHelper.getDevilFruitCategory());
/* 24 */     setMaxCooldown(20.0D);
/* 25 */     setDescription("Releases an electrical surge like a defibrillator from the users fist which shocks the opponent");
/*    */     
/* 27 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 28 */     this.onHitEntityEvent = this::onHitEntity;
/* 29 */     this.onHitEffectEvent = this::onHitEffectEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 34 */     if (!OpeHelper.hasRoomActive(player, (Ability)this)) {
/* 35 */       return false;
/*    */     }
/* 37 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
/* 42 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)player, player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 2.0F);
/* 43 */     explosion.setStaticDamage(3.0F);
/* 44 */     explosion.setExplosionSound(true);
/* 45 */     explosion.setDamageOwner(false);
/* 46 */     explosion.setDestroyBlocks(false);
/* 47 */     explosion.setFireAfterExplosion(false);
/* 48 */     explosion.setDamageSource((DamageSource)ModDamageSource.LIGHTNING_BOLT.causeEntityDamageFromSource((Entity)player));
/* 49 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(1));
/* 50 */     explosion.setDamageEntities(false);
/* 51 */     explosion.doExplosion();
/*    */     
/* 53 */     Vec3d dirVec = player.getPositionVec().subtract(target.getPositionVec()).normalize().mul(5.0D, 1.0D, 5.0D);
/* 54 */     target.setMotion(-dirVec.x, 0.25D, -dirVec.z);
/* 55 */     target.velocityChanged = true;
/*    */     
/* 57 */     PARTICLES.spawn(target.world, target.getPosX(), target.getPosY(), target.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntity(PlayerEntity player, LivingEntity target) {
/* 62 */     return 40.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\ope\CounterShockAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */