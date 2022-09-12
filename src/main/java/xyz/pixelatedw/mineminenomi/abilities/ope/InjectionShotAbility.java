package xyz.pixelatedw.mineminenomi.abilities.ope;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
import xyz.pixelatedw.mineminenomi.api.animations.IHeldItemAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.renderers.animations.ope.InjectionShotAnimation;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

import java.util.List;

public class InjectionShotAbility extends ChargeableAbility implements IMultiTargetAbility, IAnimatedAbility {
  public static final Ability INSTANCE = (Ability)new InjectionShotAbility();

  
  public InjectionShotAbility() {
    super("Injection Shot", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(10.0D);
    setMaxChargeTime(1.0D);
    setDescription("While holding a weapon, the user charges at the enemy, leaving them confused");
    
    this.onStartChargingEvent = this::onStartChargingEvent;
    this.duringChargingEvent = this::duringChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
    this.duringCooldownEvent = this::duringCooldown;
  }

  
  private boolean onStartChargingEvent(PlayerEntity player) {
    if (!AbilityHelper.canUseMomentumAbility(player) || !canUseAbility(player)) {
      return false;
    }
    player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, getMaxChargeTime(), 0, false, false));
    return true;
  }

  
  private void duringChargingEvent(PlayerEntity player, int i) {
    if (!canUseAbility(player)) {
      startCooldown(player);
    }
  }
  
  private boolean onEndChargingEvent(PlayerEntity player) {
    if (!canUseAbility(player)) {
      return false;
    }
    clearTargets();
    
    Vec3d dirVec = player.getLookVec().mul(16.0D, 1.0D, 16.0D);
    player.setMotion(dirVec.x * 0.25D, 0.35D, dirVec.z * 0.25D);
    ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
    
    return true;
  }

  
  private void duringCooldown(PlayerEntity player, int cooldownTimer) {
    if (canDealDamage()) {
      
      List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.6D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
      list.remove(player);
      
      list.forEach(entity -> {
            if (isTarget(entity)) {
              entity.attackEntityFrom(ModDamageSource.causePlayerDamage(player), 40.0F);
              entity.addPotionEffect(new EffectInstance(Effects.NAUSEA, 60, 0));
            } 
          });
    } 
  }



  
  private boolean canUseAbility(PlayerEntity player) {
    if (!OpeHelper.hasRoomActive(player, (Ability)this)) {
      return false;
    }
    if (!ItemsHelper.isSword(player.getHeldItemMainhand())) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_SWORD, new Object[0]));
      return false;
    } 
    
    return true;
  }

  
  public boolean canDealDamage() {
    return (this.cooldown > getMaxCooldown() * 0.8D);
  }


  
  public IHeldItemAnimation getAnimation() {
    return (IHeldItemAnimation)InjectionShotAnimation.INSTANCE;
  }


  
  public boolean isAnimationActive() {
    return isCharging();
  }
}


