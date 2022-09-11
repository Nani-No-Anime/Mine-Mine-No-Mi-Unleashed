package xyz.pixelatedw.mineminenomi.abilities.pika;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.pika.AmaterasuProjectile;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.pika.ChargingPikaParticleEvent;
import xyz.pixelatedw.mineminenomi.renderers.animations.PointArmAnimation;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

public class AmaterasuAbility extends ChargeableAbility implements IAnimatedAbility {
  public static final Ability INSTANCE = (Ability)new AmaterasuAbility();
  
  private static final ParticleEffect PARTICLES = (ParticleEffect)new ChargingPikaParticleEvent();

  
  public AmaterasuAbility() {
    super("Amaterasu", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(12.0D);
    setMaxChargeTime(4.0D);
    setCancelable();
    setDescription("Charges up a powerful concentrated light beam\n\nthe longer its charged the more powerful it becomes.");

    
    this.onStartChargingEvent = this::onStartChargingEvent;
    this.duringChargingEvent = this::duringChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
  }

  
  private boolean onStartChargingEvent(PlayerEntity player) {
    player.world.playMovingSound(null, (Entity)player, ModSounds.PIKA_CHARGE_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
    return true;
  }

  
  private void duringChargingEvent(PlayerEntity player, int timer) {
    AbilityHelper.slowEntityFall((LivingEntity)player);
    PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
  }

  
  private boolean onEndChargingEvent(PlayerEntity player) {
    if (getChargeTime() > getMaxChargeTime() - 5) {
      return false;
    }
    float multi = 1.0F - getChargeTime() / getMaxChargeTime();
    
    AmaterasuProjectile proj = new AmaterasuProjectile(player.world, (LivingEntity)player);
    proj.setDamage(proj.getDamage() * multi);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 5.0F, 1.0F);
    player.world.playSound(null, player.getPosition(), ModSounds.PIKA_SFX, SoundCategory.PLAYERS, 0.6F, 1.0F);
    return true;
  }


  
  public IAnimation getAnimation() {
    return (IAnimation)PointArmAnimation.INSTANCE;
  }


  
  public boolean isAnimationActive() {
    return isCharging();
  }
}


