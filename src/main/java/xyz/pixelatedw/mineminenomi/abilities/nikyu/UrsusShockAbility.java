package xyz.pixelatedw.mineminenomi.abilities.nikyu;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu.ChargingUrsusShockEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu.UrsusShockProjectile;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.renderers.animations.RaiseBothArmAnimation;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

public class UrsusShockAbility extends ChargeableAbility implements IAnimatedAbility {
  public static final Ability INSTANCE = (Ability)new UrsusShockAbility();
  
  private int power = 0;
  
  private ChargingUrsusShockEntity ursusShockEntity;
  
  public UrsusShockAbility() {
    super("Ursus Shock", AbilityHelper.getDevilFruitCategory());
    setDescription("The user compresses air and sends it towards the opponent to create a huge shockwave");
    setMaxCooldown(20.0D);
    setMaxChargeTime(16.0D);

    
    this.duringChargingEvent = this::duringChargingEvent;
    this.onStartChargingEvent = this::onStartChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
  }

  
  private void duringChargingEvent(PlayerEntity player, int chargeTimer) {
    if (this.ursusShockEntity == null) {
      
      endCharging(player);
      
      return;
    } 
    this.power = chargeTimer;
    double truePower = Math.abs(this.power - getMaxChargeTime());
    
    float currentCharge = this.ursusShockEntity.getCharge();
    if (-5.0F > currentCharge) {
      this.ursusShockEntity.remove();
    } else {
      currentCharge = (float)(currentCharge + ((truePower < 190.0D) ? 0.025D : -0.045D));
      this.ursusShockEntity.setCharge(currentCharge);
    } 
  }

  
  private boolean onStartChargingEvent(PlayerEntity player) {
    player.world.playMovingSound(null, (Entity)player, ModSounds.URSUS_SHOCK_SFX, SoundCategory.PLAYERS, 2.0F, 2.0F);
    ChargingUrsusShockEntity entity = new ChargingUrsusShockEntity(player.world);
    entity.setOwner((LivingEntity)player);
    entity.setPositionAndRotation(player.getPosX(), player.getPosY() + 2.0D, player.getPosZ(), 0.0F, 0.0F);
    player.world.addEntity((Entity)entity);
    this.ursusShockEntity = entity;
    return true;
  }

  
  private boolean onEndChargingEvent(PlayerEntity player) {
    if (getChargeTime() > getMaxChargeTime() - 190) {
      return false;
    }
    float multiplier = 1.0F - getChargeTime() / getMaxChargeTime();
    
    UrsusShockProjectile projectile = new UrsusShockProjectile(player.world, (LivingEntity)player);
    projectile.multiplier = multiplier;
    projectile.setSize((multiplier > 0.75D) ? 0.6F : (5.0F * (1.0F - multiplier)));
    setMaxCooldown((20.0F * multiplier));
    
    player.world.addEntity((Entity)projectile);
    projectile.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 0.5F, 0.0F);
    
    if (this.ursusShockEntity != null) {
      this.ursusShockEntity.remove();
    }
    return true;
  }


  
  public IAnimation getAnimation() {
    return (IAnimation)RaiseBothArmAnimation.INSTANCE;
  }


  
  public boolean isAnimationActive() {
    return isCharging();
  }
}


