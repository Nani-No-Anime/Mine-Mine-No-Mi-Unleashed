package xyz.pixelatedw.mineminenomi.abilities.sui;


import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;

public class FreeSwimmingAbility extends ContinuousAbility {
  public static final FreeSwimmingAbility INSTANCE = new FreeSwimmingAbility();
  
  public boolean isSwimming = false;

  
  public FreeSwimmingAbility() {
    super("Free Swimming", AbilityHelper.getDevilFruitCategory());
    setDescription("Lets the user swim trough blocks like they were in the sea");
    setCooldown(1.0D);
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringCooldownEvent = this::duringContinuityEvent;
  }

  
  private void duringContinuityEvent(PlayerEntity player, int i) {
    if (!AbilityHelper.canUseMomentumAbility(player))
      endContinuity(player); 
  }
  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    return AbilityHelper.canUseMomentumAbility(player);
  }
}


