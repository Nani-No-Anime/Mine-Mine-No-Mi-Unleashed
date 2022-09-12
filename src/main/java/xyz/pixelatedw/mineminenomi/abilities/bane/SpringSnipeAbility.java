package xyz.pixelatedw.mineminenomi.abilities.bane;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

import java.util.List;

public class SpringSnipeAbility extends ChargeableAbility implements IMultiTargetAbility {
  public static final Ability INSTANCE = (Ability)new SpringSnipeAbility();
  
  private Vec3d look;

  
  public SpringSnipeAbility() {
    super("Spring Snipe", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(14.5D);
    setMaxChargeTime(0.5D);
    setDescription("Turning the user's forelegs into springs, they can launch themselves directly at the opponent");
    
    this.onStartChargingEvent = this::onStartChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
    this.duringCooldownEvent = this::duringCooldown;
  }

  
  private boolean onStartChargingEvent(PlayerEntity player) {
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    SpringHopperAbility ability = (SpringHopperAbility)props.getEquippedAbility(SpringHopperAbility.INSTANCE);
    
    if (ability == null || ability.isOnCooldown()) {
      return false;
    }
    if (!ability.isContinuous())
    {
      ability.startContinuity(player);
    }


    
    ability.jumpPower = Math.min(ability.jumpPower + 3, 9);
    
    clearTargets();
    return AbilityHelper.canUseMomentumAbility(player);
  }

  
  private boolean onEndChargingEvent(PlayerEntity player) {
    this.look = player.getLook(1.0F);
    return true;
  }

  
  private void duringCooldown(PlayerEntity player, int cooldownTimer) {
    if (canDealDamage()) {
      
      IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
      SpringHopperAbility ability = (SpringHopperAbility)props.getEquippedAbility(SpringHopperAbility.INSTANCE);
      
      Vec3d speed = this.look.mul(3.0D, 3.0D, 3.0D);
      player.setMotion(speed.x, speed.y, speed.z);
      ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
      List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 2.0D);
      list.remove(player);
      list.forEach(entity -> {
            if (isTarget(entity) && player.canEntityBeSeen((Entity)entity)) {
              entity.attackEntityFrom(DamageSource.causePlayerDamage(player), (ability.jumpPower * 6));
            }
          });
    } 
  }

  
  public boolean canDealDamage() {
    return (this.cooldown > getMaxCooldown() * 0.9D);
  }
}


