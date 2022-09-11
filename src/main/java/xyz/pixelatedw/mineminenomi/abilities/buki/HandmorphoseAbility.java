package xyz.pixelatedw.mineminenomi.abilities.buki;

import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchTriggerAbility;


public class HandmorphoseAbility
  extends PunchTriggerAbility
{
  public HandmorphoseAbility() {
    super("Handmorphose", AbilityHelper.getDevilFruitCategory());
    
    this.onStartContinuityEvent = this::onStartContinuity;
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
    
    stopAfterUsage(false);
    this.onSwingEvent = this::onSwingEvent;
  }

  
  private boolean onSwingEvent(PlayerEntity player) {
    return true;
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    return true;
  }

  
  private void duringContinuityEvent(PlayerEntity playerEntity, int i) {}

  
  private boolean onStartContinuity(PlayerEntity player) {
    return true;
  }
  
  public enum Mode
  {
    SWORD,
    CANNON,
    REVOLVER;

    
    public Mode getNext() {
      return (ordinal() == Arrays.<Mode>stream(values()).count() - 1L) ? SWORD : values()[ordinal() + 1];
    }
  }
}


