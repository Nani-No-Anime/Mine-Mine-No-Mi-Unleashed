package xyz.pixelatedw.mineminenomi.abilities.pika;


import java.util.List;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.pika.ChargingPikaParticleEvent;
import xyz.pixelatedw.mineminenomi.particles.effects.pika.FlashParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

public class FlashAbility
  extends ChargeableAbility {
  public static final Ability INSTANCE = (Ability)new FlashAbility();
  
  private static final ParticleEffect PARTICLES = (ParticleEffect)new FlashParticleEffect();
  private static final ParticleEffect CHARGE_PARTICLE = (ParticleEffect)new ChargingPikaParticleEvent();

  
  public FlashAbility() {
    super("Flash", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(10.0D);
    setMaxChargeTime(2.0D);
    setCancelable();
    setDescription("The user creates a bright flash of light, blinding their opponents");
    
    this.duringChargingEvent = this::duringChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
  }

  
  private void duringChargingEvent(PlayerEntity player, int timer) {
    CHARGE_PARTICLE.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
  }

  
  private boolean onEndChargingEvent(PlayerEntity player) {
    AbilityHelper.reduceEffect(player.getActivePotionEffect(ModEffects.FROZEN), 10.0D);
    AbilityHelper.reduceEffect(player.getActivePotionEffect(ModEffects.FROSTBITE), 10.0D);
    AbilityHelper.reduceEffect(player.getActivePotionEffect(ModEffects.CANDY_STUCK), 10.0D);
    AbilityHelper.reduceEffect(player.getActivePotionEffect(ModEffects.CANDLE_LOCK), 10.0D);

    
    float time = getChargeTime() / getMaxChargeTime();
    float radius = (1.0F - time) * 16.0F;
    List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, radius, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
    list.remove(player);
    
    list.forEach(entity -> {
          entity.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 140, 3));
          
          entity.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 60, 0));
          
          PARTICLES.spawn(player.world, entity.getPosX(), entity.getPosY() + entity.getEyeHeight(), entity.getPosZ(), 0.0D, 0.0D, 0.0D);
        });
    return true;
  }
}


