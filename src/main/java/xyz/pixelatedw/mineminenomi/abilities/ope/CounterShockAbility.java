package xyz.pixelatedw.mineminenomi.abilities.ope;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.ope.CounterShockParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class CounterShockAbility extends PunchAbility {
  public static final Ability INSTANCE = (Ability)new CounterShockAbility();
  
  private static final ParticleEffect PARTICLES = (ParticleEffect)new CounterShockParticleEffect();

  
  public CounterShockAbility() {
    super("Counter Shock", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(20.0D);
    setDescription("Releases an electrical surge like a defibrillator from the users fist which shocks the opponent");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.onHitEntityEvent = this::onHitEntity;
    this.onHitEffectEvent = this::onHitEffectEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    if (!OpeHelper.hasRoomActive(player, (Ability)this)) {
      return false;
    }
    return true;
  }

  
  private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)player, player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 2.0F);
    explosion.setStaticDamage(3.0F);
    explosion.setExplosionSound(true);
    explosion.setDamageOwner(false);
    explosion.setDestroyBlocks(false);
    explosion.setFireAfterExplosion(false);
    explosion.setDamageSource((DamageSource)ModDamageSource.LIGHTNING_BOLT.causeEntityDamageFromSource((Entity)player));
    explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(1));
    explosion.setDamageEntities(false);
    explosion.doExplosion();
    
    Vec3d dirVec = player.getPositionVec().subtract(target.getPositionVec()).normalize().mul(5.0D, 1.0D, 5.0D);
    target.setMotion(-dirVec.x, 0.25D, -dirVec.z);
    target.velocityChanged = true;
    
    PARTICLES.spawn(target.world, target.getPosX(), target.getPosY(), target.getPosZ(), 0.0D, 0.0D, 0.0D);
  }

  
  private float onHitEntity(PlayerEntity player, LivingEntity target) {
    return 40.0F;
  }
}


