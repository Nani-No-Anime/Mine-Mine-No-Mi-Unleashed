package xyz.pixelatedw.mineminenomi.abilities.fishmankarate;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.fishkarate.SamehadaParticleEffect;
import xyz.pixelatedw.mineminenomi.renderers.animations.CrossedArmsAnimation;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;

public class SamehadaShoteiAbility extends ContinuousAbility implements IAnimatedAbility {
  public static final Ability INSTANCE = (Ability)new SamehadaShoteiAbility();
  private static final ParticleEffect PARTICLES = (ParticleEffect)new SamehadaParticleEffect();

  
  public SamehadaShoteiAbility() {
    super("Samehada Shotei", AbilityHelper.getRacialCategory());
    setThreshold(10.0D);
    setDescription("The user concentrates their power to their palms, protecting themselves from attacks");
    
    addInPool(new AbilityPool[] { AbilityPool.TEKKAI_LIKE });
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuity;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }
  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    return true;
  }

  
  private void duringContinuity(PlayerEntity player, int passiveTimer) {
    player.addPotionEffect(new EffectInstance(ModEffects.GUARDING, 2, player.isInWater() ? 2 : 1, false, false));
    PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    int cooldown = (int)Math.round(this.continueTime / 20.0D) + 3;
    setMaxCooldown(cooldown);
    return true;
  }


  
  public IAnimation getAnimation() {
    return (IAnimation)CrossedArmsAnimation.INSTANCE;
  }


  
  public boolean isAnimationActive() {
    return isContinuous();
  }
}


