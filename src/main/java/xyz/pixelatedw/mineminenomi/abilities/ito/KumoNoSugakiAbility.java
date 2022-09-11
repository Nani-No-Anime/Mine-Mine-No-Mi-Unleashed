package xyz.pixelatedw.mineminenomi.abilities.ito;
import java.lang.invoke.SerializedLambda;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.EntityRayTraceResult;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.particles.effects.ito.KumoNoSugakiParticleEffect;
import xyz.pixelatedw.mineminenomi.renderers.animations.PointBothArmsAnimation;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;

public class KumoNoSugakiAbility extends ContinuousAbility implements IAnimatedAbility {
  public static final KumoNoSugakiAbility INSTANCE = new KumoNoSugakiAbility();
  
  private static final KumoNoSugakiParticleEffect PARTICLES = new KumoNoSugakiParticleEffect();

  
  public KumoNoSugakiAbility() {
    super("Kumo no Sugaki", AbilityHelper.getDevilFruitCategory());
    setThreshold(6.0D);
    setDescription("Creates a huge web that protects the user from any attack");
    addInPool(new AbilityPool[] { AbilityPool.TEKKAI_LIKE });
    
    this.duringContinuityEvent = this::duringContinuity;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }

  
  private void duringContinuity(PlayerEntity player, int passiveTimer) {
    EntityRayTraceResult trace = WyHelper.rayTraceEntities((Entity)player, 0.8D);
    player.addPotionEffect(new EffectInstance(ModEffects.GUARDING, 2, 5, false, false));
    AbilityHelper.slowEntityFall((LivingEntity)player);
    
    if (passiveTimer % 2 == 0) {
      PARTICLES.spawn(player.world, trace.getHitVec().getX(), player.getPosY(), trace.getHitVec().getZ(), 0.0D, 0.0D, 0.0D);
    }
  }
  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    int cooldown = (int)Math.round(this.continueTime / 40.0D) + 2;
    setMaxCooldown(cooldown);
    return true;
  }


  
  public IAnimation getAnimation() {
    return (IAnimation)PointBothArmsAnimation.INSTANCE;
  }


  
  public boolean isAnimationActive() {
    return isContinuous();
  }
}


