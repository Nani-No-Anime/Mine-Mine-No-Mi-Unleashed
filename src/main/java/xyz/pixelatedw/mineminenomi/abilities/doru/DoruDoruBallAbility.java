package xyz.pixelatedw.mineminenomi.abilities.doru;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

public class DoruDoruBallAbility extends ContinuousAbility {
  public static final DoruDoruBallAbility INSTANCE = new DoruDoruBallAbility();
  public double rotateAngleX = 0.0D;
  public double rotateAngleZ = 0.0D;

  
  public DoruDoruBallAbility() {
    super("Doru Doru Ball", AbilityHelper.getDevilFruitCategory());
    setThreshold(8.0D);
    setMaxCooldown(10.0D);
    setDescription("Puts the user into a hardened wax ball for max defense");
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuity;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
    
    addInPool(new AbilityPool[] { AbilityPool.TEKKAI_LIKE });
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    this.rotateAngleX = 0.0D;
    this.rotateAngleZ = 0.0D;
    return true;
  }

  
  private void duringContinuity(PlayerEntity player, int passiveTime) {
    player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 20, 5, false, false));
    player.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 20, 5, false, false));
    
    int power = 5;
    Ability ability = AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)CandleChampionAbility.INSTANCE);
    if (ability != null && ability.isContinuous()) {
      power = 10;
    }
    if (!player.isBurning()) {
      player.addPotionEffect(new EffectInstance(ModEffects.GUARDING_WITH_MOVEMENT, 2, power, false, false));
    }
  }
  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    this.rotateAngleX = 0.0D;
    this.rotateAngleZ = 0.0D;
    player.removePotionEffect(Effects.SLOWNESS);
    player.removePotionEffect(Effects.MINING_FATIGUE);
    
    int cooldown = (int)Math.round(this.continueTime / 25.0D) + 3;
    setMaxCooldown(cooldown);
    return true;
  }
}


