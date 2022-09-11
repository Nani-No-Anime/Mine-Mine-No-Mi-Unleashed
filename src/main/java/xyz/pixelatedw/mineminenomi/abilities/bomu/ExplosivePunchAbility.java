package xyz.pixelatedw.mineminenomi.abilities.bomu;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class ExplosivePunchAbility extends PunchAbility {
  public static final Ability INSTANCE = (Ability)new ExplosivePunchAbility();

  
  public ExplosivePunchAbility() {
    super("Explosive Punch", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(10.0D);
    setDescription("The user punches and creates an explosion around their fist");
    this.onHitEntityEvent = this::onHitEntity;
    this.onHitEffectEvent = this::onHitEffectEvent;
  }

  
  private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)player, player.world, target.getPosX(), target.getPosY(), target.getPosZ(), 4.0F);
    explosion.setStaticDamage(35.0F);
    explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(3));
    explosion.doExplosion();
  }

  
  private float onHitEntity(PlayerEntity player, LivingEntity target) {
    return 20.0F;
  }
}


