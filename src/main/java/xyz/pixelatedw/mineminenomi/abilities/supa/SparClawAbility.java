package xyz.pixelatedw.mineminenomi.abilities.supa;

import java.awt.Color;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
import xyz.pixelatedw.mineminenomi.api.abilities.IPunchOverlayAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class SparClawAbility
  extends PunchAbility implements IPunchOverlayAbility {
  public static final SparClawAbility INSTANCE = new SparClawAbility();
  
  private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setColor(new Color(113, 121, 126));

  
  public SparClawAbility() {
    super("Spar Claw", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(1.0D);
    setThreshold(50.0D);
    setDescription("Turns the undersides of the user's fingers into blades to slash opponents with");
    
    this.onEndContinuityEvent = this::onEndContinuityEvent;
    this.onHitEntityEvent = this::onHitEntityEvent;
  }

  
  private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
    return 15.0F;
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    setMaxCooldown((2.0F + this.continueTime / 60.0F));
    return true;
  }

  
  public AbilityOverlay getPunchOverlay(LivingEntity player) {
    return OVERLAY;
  }
}


