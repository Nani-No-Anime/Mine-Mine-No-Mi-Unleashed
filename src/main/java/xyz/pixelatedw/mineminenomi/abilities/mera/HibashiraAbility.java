package xyz.pixelatedw.mineminenomi.abilities.mera;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.particles.effects.mera.HibashiraParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;

import java.util.List;
import java.util.Objects;

public class HibashiraAbility extends ContinuousAbility implements IMultiTargetAbility {
  public static final HibashiraAbility INSTANCE = new HibashiraAbility();
  
  private static final double PILLAR_SIZE = 3.5D;
  private static final HibashiraParticleEffect PARTICLES = new HibashiraParticleEffect();

  
  public HibashiraAbility() {
    super("Hibashira", AbilityHelper.getDevilFruitCategory());
    setThreshold(5.0D);
    setDescription("Creates a fire pillar extending both upwards and downwards, burning every enemy within it");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    clearTargets();
    return true;
  }

  
  private void duringContinuityEvent(PlayerEntity player, int continuousTime) {
    PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 3.5D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), LivingEntity.class);
    for (int i = -10; i <= 10; i++) {
      
      Objects.requireNonNull(list); WyHelper.getEntitiesNear(player.getPosition().up(i), player.world, 3.5D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), LivingEntity.class ).stream().filter(entity -> !list.contains(entity)).forEach(list::add);
    }  list.remove(player);
    
    list.forEach(entity -> {
          boolean causedDamage = entity.attackEntityFrom((DamageSource)ModDamageSource.FIRE.causeEntityDamageFromSource((Entity)player), 5.0F);

          
          if (causedDamage && isTarget(entity)) {
            entity.setFire(4);
          }
        });
    
    if (continuousTime % 20 == 0) {
      clearTargets();
    }
    AbilityHelper.slowEntityFall((LivingEntity)player);
    player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1, false, false));
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    setMaxCooldown((Math.round(this.continueTime * 1.2F) / 20.0F));
    clearTargets();
    return true;
  }
}


