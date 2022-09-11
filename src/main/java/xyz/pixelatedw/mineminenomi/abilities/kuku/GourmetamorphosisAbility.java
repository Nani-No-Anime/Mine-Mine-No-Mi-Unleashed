package xyz.pixelatedw.mineminenomi.abilities.kuku;


import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;

public class GourmetamorphosisAbility extends ContinuousAbility {
  public static final GourmetamorphosisAbility INSTANCE = new GourmetamorphosisAbility();

  
  public GourmetamorphosisAbility() {
    super("Gourmetamorphosis", AbilityHelper.getDevilFruitCategory());
    setDescription("Makes all items in the user's inventory edible.");
    setThreshold(60.0D);
    
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    int cooldown = (int)Math.round(this.continueTime / 40.0D);
    setMaxCooldown(cooldown);
    return true;
  }
}


