/*    */ package xyz.pixelatedw.mineminenomi.abilities.bomu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class KickBombAbility extends Ability {
/* 11 */   public static final Ability INSTANCE = new KickBombAbility();
/*    */ 
/*    */   
/*    */   public KickBombAbility() {
/* 15 */     super("Kick Bomb", AbilityHelper.getDevilFruitCategory());
/* 16 */     setMaxCooldown(20.0D);
/* 17 */     setDescription("The user kicks the ground, detonating their leg on impact");
/*    */     
/* 19 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 24 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)player, player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 7.0F);
/* 25 */     explosion.setStaticDamage(70.0F);
/* 26 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(8));
/* 27 */     explosion.doExplosion();
/*    */     
/* 29 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\bomu\KickBombAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */