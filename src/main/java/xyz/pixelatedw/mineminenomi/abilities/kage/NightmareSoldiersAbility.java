package xyz.pixelatedw.mineminenomi.abilities.kage;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.KageHelper;
import xyz.pixelatedw.mineminenomi.entities.mobs.ability.NightmareSoldierEntity;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.kage.ChargeableKageParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

public class NightmareSoldiersAbility extends ChargeableAbility {
  public static final NightmareSoldiersAbility INSTANCE = new NightmareSoldiersAbility();
  
  private static final ParticleEffect CHARGE_PARTICLE = (ParticleEffect)new ChargeableKageParticleEffect();

  
  public NightmareSoldiersAbility() {
    super("Nightmare Soldiers", AbilityHelper.getDevilFruitCategory());
    setDescription("Creates Nightmare Soldiers using Shadows from the user's inventory, the longer the ability charges the more soldiers it'll create");
    setMaxCooldown(1.0D);
    setMaxChargeTime(10.0D);
    setCancelable();
    
    this.onStartChargingEvent = this::onStartChargingEvent;
    this.duringChargingEvent = this::duringChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
  }

  
  private boolean onStartChargingEvent(PlayerEntity player) {
    if (KageHelper.getAvailableShadows(player) <= 0) {
      
      player.sendMessage(KageHelper.NOT_ENOUGH_SHADOWS_WARN);
      return false;
    } 
    return true;
  }

  
  private void duringChargingEvent(PlayerEntity player, int chargeTime) {
    CHARGE_PARTICLE.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 20, 2, false, false));
    int chargeup = (getMaxChargeTime() - getChargeTime()) / 20;
    if (chargeTime % 20 == 0)
    {
      if (KageHelper.getAvailableShadows(player) < chargeup) {
        
        player.sendMessage(KageHelper.NOT_ENOUGH_SHADOWS_WARN);
        endCharging(player);
      } 
    }
  }

  
  private boolean onEndChargingEvent(PlayerEntity player) {
    int chargeup = (getMaxChargeTime() - getChargeTime()) / 20;
    int cooldown = 2 + chargeup * 2;
    setMaxCooldown(cooldown);
    
    KageHelper.removeShadows(player, chargeup);
    
    for (int i = 0; i < chargeup; i++) {
      
      NightmareSoldierEntity soldier = new NightmareSoldierEntity(player.world);
      soldier.setPositionAndRotation(player.getPosX(), player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
      soldier.setOwner((LivingEntity)player);
      player.world.addEntity((Entity)soldier);
    } 
    
    return true;
  }
}


