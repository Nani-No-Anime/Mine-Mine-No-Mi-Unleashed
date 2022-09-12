package xyz.pixelatedw.mineminenomi.wypi.abilities;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import java.io.Serializable;

public class PunchTriggerAbility
  extends ContinuousAbility
{
  protected IOnHit onSwingEvent = player -> true;
  public boolean released = false;
  private boolean shouldStopAfterUsing = false;
  
  public PunchTriggerAbility(String name, APIConfig.AbilityCategory category) {
    super(name, category);
  }

  
  public void startContinuity(PlayerEntity player) {
    this.released = false;
    super.startContinuity(player);
  }


  
  public void tick(PlayerEntity player) {
    if (isOnCooldown() || !isContinuous()) {
      this.released = false;
    }
    if (!canUse(player)) {
      
      endContinuity(player);
      
      return;
    } 
    player.world.getProfiler().startSection(WyHelper.getResourceName(getName()));
    
    if (isContinuous()) {
      
      this.continueTime++;
      if ((isClientSide() || !player.world.isRemote) && !isStateForced()) {
        this.duringContinuityEvent.duringContinuity(player, this.continueTime);
      }
      if (player.isSwingInProgress && !this.released) {
        
        this.released = true;
        this.onSwingEvent.onHitEntity(player);
        if (this.shouldStopAfterUsing) {
          endContinuity(player);
        }
      } 
      if (this.released && !player.isSwingInProgress && !this.shouldStopAfterUsing) {
        this.released = false;
      }
      if (this.threshold > 0 && this.continueTime >= this.threshold) {
        endContinuity(player);
      }
    } 
    player.world.getProfiler().endSection();
  }






  
  public void stopAfterUsage(boolean value) {
    this.shouldStopAfterUsing = value;
  }
  
  public static interface IOnHit extends Serializable {
    boolean onHitEntity(PlayerEntity param1PlayerEntity);
  }
}


