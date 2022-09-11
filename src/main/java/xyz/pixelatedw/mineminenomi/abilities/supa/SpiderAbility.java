package xyz.pixelatedw.mineminenomi.abilities.supa;

import java.awt.Color;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
import xyz.pixelatedw.mineminenomi.api.abilities.IBodyOverlayAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;

public class SpiderAbility
  extends ContinuousAbility implements IBodyOverlayAbility {
  public static final SpiderAbility INSTANCE = new SpiderAbility();
  private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setTexture(ModResources.DOKU_COATING).setColor(new Color(33, 26, 26, 158));

  
  public SpiderAbility() {
    super("Spider", AbilityHelper.getDevilFruitCategory());
    setThreshold(15.0D);
    setDescription("Hardens the user's body to protect themselves, but they're unable to move");
    
    addInPool(new AbilityPool[] { AbilityPool.TEKKAI_LIKE });
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuity;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }
  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    return true;
  }

  
  private void duringContinuity(PlayerEntity player, int passiveTimer) {
    player.addPotionEffect(new EffectInstance(ModEffects.GUARDING, 2, 5, false, false));
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    int cooldown = (int)Math.round(this.continueTime / 15.0D);
    setMaxCooldown(cooldown);
    return true;
  }


  
  public AbilityOverlay getBodyOverlay() {
    return OVERLAY;
  }
}


