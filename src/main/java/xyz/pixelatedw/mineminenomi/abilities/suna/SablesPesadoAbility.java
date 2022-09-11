package xyz.pixelatedw.mineminenomi.abilities.suna;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundCategory;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.suna.SablesPesadoProjectile;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.particles.effects.suna.SablesParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

public class SablesPesadoAbility extends ChargeableAbility {
  public static final Ability INSTANCE = (Ability)new SablesPesadoAbility();
  
  private static final SablesParticleEffect PARTICLES = new SablesParticleEffect();

  
  public SablesPesadoAbility() {
    super("Sables: Pesado", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(25.0D);
    setMaxChargeTime(5.0D);
    setDescription("The user compresses a sandstorm to its limits and shoots it at extreme speeds");
    
    this.onStartChargingEvent = this::onStartChargingEvent;
    this.duringChargingEvent = this::duringChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
  }

  
  private boolean onStartChargingEvent(PlayerEntity player) {
    setChargeTime(SunaHelper.isFruitBoosted(player) ? (int)(getMaxChargeTime() * 0.5F) : getMaxChargeTime());
    return !player.isWet();
  }

  
  private void duringChargingEvent(PlayerEntity player, int i) {
    player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 2, 1, false, false));
    player.addPotionEffect(new EffectInstance(ModEffects.REDUCED_FALL, 2, 1, false, false));
    PARTICLES.mult = 1.0F - i / getMaxChargeTime() * 10.0F;
    if (i % 5 == 0) {
      PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), (player.getMotion()).x, (player.getMotion()).y, (player.getMotion()).z);
    }
  }
  
  private boolean onEndChargingEvent(PlayerEntity player) {
    player.world.playMovingSound(null, (Entity)player, ModSounds.SABLES_PESADO_SFX, SoundCategory.PLAYERS, 2.0F, 2.0F);
    SablesPesadoProjectile proj = new SablesPesadoProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.25F, 4.0F);
    
    return true;
  }
}


