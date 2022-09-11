package xyz.pixelatedw.mineminenomi.abilities.ito;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
import xyz.pixelatedw.mineminenomi.api.abilities.DamagedContinuousAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.IExtraUpdateData;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
import xyz.pixelatedw.mineminenomi.renderers.animations.ito.ShufukuSagyoAnimation;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public class ShufukuSagyoAbility extends DamagedContinuousAbility implements IAnimatedAbility, IExtraUpdateData {
  public static final ShufukuSagyoAbility INSTANCE = new ShufukuSagyoAbility();
  
  int acumulatedDamage = 0;
  int timeSinceLastHit = 0;
  
  boolean dealDamage = true;
  
  public ShufukuSagyoAbility() {
    super("Shufuku Sagyo", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(100.0D);
    setDescription("Uses strings to stitch together internal organs have they been injured, minimizing fatal damage\n\nYou will take your damage back, excluding in the case of holding the ability off without taking damage for a long amount of time §2");
    
    addInPool(new AbilityPool[] { AbilityPool.TEKKAI_LIKE });
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuity;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
    this.onDamagedEvent = this::onDamageEvent;
    this.duringCooldownEvent = this::duringCooldownEvent;
  }


  
  private void useDamage(PlayerEntity player) {
    if (this.acumulatedDamage > 0 && this.dealDamage) {
      
      player.attackEntityFrom(DamageSource.OUT_OF_WORLD, this.acumulatedDamage);
      this.acumulatedDamage = 0;
    } 
  }


  
  private void duringCooldownEvent(PlayerEntity player, int i) {
    useDamage(player);
  }

  
  private boolean onDamageEvent(LivingEntity player, DamageSource damageSource, double v) {
    if (damageSource.getDamageType().equals(DamageSource.OUT_OF_WORLD.getDamageType())) {
      return true;
    }
    this.timeSinceLastHit = player.ticksExisted;
    return true;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    this.timeSinceLastHit = player.ticksExisted;
    this.dealDamage = true;
    this.acumulatedDamage = 0;
    setMaxCooldown(100.0D);
    setThreshold(0.0D);
    WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, (Ability)this), (LivingEntity)player);
    return (player.getMaxHealth() * 0.7D > player.getHealth());
  }

  
  private void duringContinuity(PlayerEntity player, int passiveTimer) {
    if (player.getMaxHealth() * 0.7D > player.getHealth() && player.ticksExisted % 2 == 0) {
      
      player.addPotionEffect(new EffectInstance(ModEffects.NO_HANDS, 2, 0, false, false));
      player.addPotionEffect(new EffectInstance(ModEffects.GUARDING, 2, 4, false, false));
      player.heal(1.0F);
      this.acumulatedDamage++;
    } else {
      
      player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 2, 1, false, false));
      player.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 2, 1, false, false));
      player.addPotionEffect(new EffectInstance(ModEffects.PHYSICAL_MOVING_GUARD, 2, 2, false, false));
    } 


    
    if (this.acumulatedDamage > 0 && player.hurtTime > 0 && player.ticksExisted % 10 == 0 && Math.random() > (1.0F - this.acumulatedDamage / Math.min(player.getMaxHealth(), 100.0F) * 2.0F)) {
      
      useDamage(player);
      endContinuity(player);
    } 

    
    if (player.ticksExisted - this.timeSinceLastHit > 1200 && getThreshold() == 0) {
      
      this.dealDamage = false;
      this.continueTime = 0;
      setThreshold(5.0D);
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, (Ability)this), (LivingEntity)player);
    } 
  }

  
  public void tick(PlayerEntity player) {
    if ((isOnStandby() || isOnCooldown()) && 
      this.acumulatedDamage > 0) {

      
      player.attackEntityFrom(DamageSource.OUT_OF_WORLD, this.acumulatedDamage);
      this.acumulatedDamage = 0;
      endContinuity(player);
    } 
    
    super.tick(player);
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    if (getContinueTime() == getThreshold()) {
      
      setMaxCooldown(10.0D);
      this.acumulatedDamage = 0;
      return true;
    } 
    
    return true;
  }

  
  public IAnimation getAnimation() {
    return (IAnimation)ShufukuSagyoAnimation.INSTANCE;
  }

  
  public boolean isAnimationActive() {
    return isContinuous();
  }

  
  public CompoundNBT getExtraData() {
    CompoundNBT nbt = new CompoundNBT();
    nbt.putDouble("accdamage", this.acumulatedDamage);
    return nbt;
  }

  
  public void setExtraData(CompoundNBT nbt) {
    this.acumulatedDamage = nbt.getInt("accdamage");
  }
}


