/*    */ package xyz.pixelatedw.mineminenomi.abilities.gura;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class ShingenNoIchigekiAbility extends PunchAbility {
/* 22 */   public static final ShingenNoIchigekiAbility INSTANCE = new ShingenNoIchigekiAbility();
/*    */ 
/*    */   
/*    */   public ShingenNoIchigekiAbility() {
/* 26 */     super("Shingen no Ichigeki", AbilityHelper.getDevilFruitCategory());
/* 27 */     setMaxCooldown(10.0D);
/* 28 */     setDescription("The user focuses vibrations around his fist in an spherical bubble, then releasing them in its enemies\n\n§2SHIFT-USE§r: Slams the fist into the ground pushing back all enemies");
/*    */     
/* 30 */     this.onHitEntityEvent = this::onHitEntityEvent;
/* 31 */     this.duringContinuityEvent = this::duringContinuityEvent;
/* 32 */     this.onHitEffectEvent = this::onHitEffectEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuityEvent(PlayerEntity player, int time) {
/* 37 */     if (player.isCrouching()) {
/*    */       
/* 39 */       ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)player, player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 20.0F);
/* 40 */       explosion.setDestroyBlocks(false);
/* 41 */       explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(20));
/* 42 */       explosion.setDamageEntities(false);
/* 43 */       explosion.doExplosion();
/*    */       
/* 45 */       List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 10.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 46 */       targets.remove(player);
/*    */       
/* 48 */       for (LivingEntity target : targets) {
/*    */         
/* 50 */         target.attackEntityFrom((DamageSource)AbilityDamageSource.causeAbilityDamage((LivingEntity)player, (Ability)this, "player"), 6.0F);
/* 51 */         Vec3d dirVec = player.getPositionVector().subtract(target.getPositionVector()).normalize().mul(3.0D, 3.0D, 3.0D);
/* 52 */         target.setMotion(-dirVec.x, 0.25D + -dirVec.y, -dirVec.z);
/* 53 */         target.velocityChanged = true;
/*    */       } 
/*    */       
/* 56 */       endContinuity(player);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
/* 63 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)target, target.world, target.getPosX(), target.getPosY(), target.getPosZ(), 4.0F);
/* 64 */     explosion.setDestroyBlocks(false);
/* 65 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(5));
/* 66 */     explosion.setDamageEntities(false);
/* 67 */     explosion.doExplosion();
/*    */     
/* 69 */     Vec3d speed = player.getLook(1.0F).normalize().mul(3.0D, 2.0D, 3.0D);
/* 70 */     target.setMotion(speed.x, 0.25D + speed.y, speed.z);
/* 71 */     target.velocityChanged = true;
/* 72 */     target.fallDistance = 0.0F;
/*    */     
/* 74 */     target.addPotionEffect(new EffectInstance(ModEffects.DIZZY, 100, 0, false, false));
/*    */   }
/*    */ 
/*    */   
/*    */   public float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
/* 79 */     return 40.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public DamageSource getPunchDamageSource(PlayerEntity player) {
/* 85 */     return ModDamageSource.causeAbilityDamage((LivingEntity)player, (Ability)this).setDamageBypassesArmor();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\gura\ShingenNoIchigekiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */