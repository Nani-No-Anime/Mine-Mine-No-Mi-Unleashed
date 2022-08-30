/*    */ package xyz.pixelatedw.mineminenomi.abilities.bomu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class ExplosivePunchAbility extends PunchAbility {
/* 13 */   public static final Ability INSTANCE = (Ability)new ExplosivePunchAbility();
/*    */ 
/*    */   
/*    */   public ExplosivePunchAbility() {
/* 17 */     super("Explosive Punch", AbilityHelper.getDevilFruitCategory());
/* 18 */     setMaxCooldown(10.0D);
/* 19 */     setDescription("The user punches and creates an explosion around their fist");
/* 20 */     this.onHitEntityEvent = this::onHitEntity;
/* 21 */     this.onHitEffectEvent = this::onHitEffectEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
/* 26 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)player, player.world, target.getPosX(), target.getPosY(), target.getPosZ(), 4.0F);
/* 27 */     explosion.setStaticDamage(35.0F);
/* 28 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(3));
/* 29 */     explosion.doExplosion();
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntity(PlayerEntity player, LivingEntity target) {
/* 34 */     return 20.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\bomu\ExplosivePunchAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */