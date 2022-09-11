package xyz.pixelatedw.mineminenomi.abilities.bomu;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class KickBombAbility extends Ability {
  public static final Ability INSTANCE = new KickBombAbility();

  
  public KickBombAbility() {
    super("Kick Bomb", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(20.0D);
    setDescription("The user kicks the ground, detonating their leg on impact");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)player, player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 7.0F);
    explosion.setStaticDamage(70.0F);
    explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(8));
    explosion.doExplosion();
    
    return true;
  }
}


