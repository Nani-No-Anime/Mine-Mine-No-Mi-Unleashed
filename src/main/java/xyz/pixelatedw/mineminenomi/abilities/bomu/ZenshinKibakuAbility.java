package xyz.pixelatedw.mineminenomi.abilities.bomu;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

public class ZenshinKibakuAbility extends ChargeableAbility {
  public static final ZenshinKibakuAbility INSTANCE = new ZenshinKibakuAbility();
  private int power = 0;

  
  public ZenshinKibakuAbility() {
    super("Zenshin Kibaku", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(30.0D);
    setDescription("The user creates a massive explosion from their body");
    setMaxChargeTime(5.0D);
    setCancelable();
    this.duringChargingEvent = this::duringChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
  }

  
  private void duringChargingEvent(PlayerEntity player, int timer) {
    this.power = timer;
  }

  
  private boolean onEndChargingEvent(PlayerEntity player) {
    float power = (getMaxChargeTime() - this.power) / 20.0F * 2.0F;
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)player, player.world, player.getPosX(), player.getPosY(), player.getPosZ(), power);
    explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect((int)power));
    explosion.setStaticDamage(power * 12.0F);
    explosion.doExplosion();
    
    return true;
  }
}


