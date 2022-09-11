package xyz.pixelatedw.mineminenomi.abilities.yomi;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.yomi.SoulParadeParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;

public class SoulParadeAbility extends ContinuousAbility {
  public static final SoulParadeAbility INSTANCE = new SoulParadeAbility();
  public static final ParticleEffect PARTICLES = (ParticleEffect)new SoulParadeParticleEffect();

  
  public SoulParadeAbility() {
    super("Soul Parade", AbilityHelper.getDevilFruitCategory());
    setDescription("The user blocks incoming attacks, an enemy hitting them gets frozen immediately");
    setThreshold(15.0D);
    
    addInPool(new AbilityPool[] { AbilityPool.TEKKAI_LIKE });
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuity;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    if (!ItemsHelper.isSword(player.getHeldItemMainhand())) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_SWORD, new Object[0]));
      return false;
    } 
    return true;
  }


  
  private void duringContinuity(PlayerEntity player, int passiveTimer) {
    player.addPotionEffect(new EffectInstance(ModEffects.GUARDING, 2, 3, false, false));
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    int cooldown = (int)Math.round(this.continueTime / 20.0D) + 3;
    setMaxCooldown(cooldown);
    return true;
  }
}


