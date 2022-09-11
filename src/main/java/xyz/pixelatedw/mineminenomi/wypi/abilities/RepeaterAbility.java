package xyz.pixelatedw.mineminenomi.wypi.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public abstract class RepeaterAbility
  extends ContinuousAbility implements IParallelContinuousAbility {
  private int repeaterCount;
  protected int maxRepeaterCount;
  protected int repeaterInterval;
  
  public RepeaterAbility(String name, APIConfig.AbilityCategory category) {
    super(name, category);
  }




  
  private void repeater(PlayerEntity player, int passiveTimer) {
    if (this.repeaterCount > 0 && passiveTimer % this.repeaterInterval == 0)
    {
      if (this.onUseEvent.onUse(player)) {
        this.repeaterCount--;
      }
    }
  }



  
  public void setMaxRepeaterCount(int count, int interval) {
    this.maxRepeaterCount = count;
    this.repeaterCount = this.maxRepeaterCount;
    this.repeaterInterval = interval;
    int threshold = (int)Math.ceil(((this.repeaterCount * this.repeaterInterval) / 20.0F));
    if (this.repeaterInterval == 0)
      threshold = -1; 
    setThreshold(threshold);
  }

  
  public void setRepeaterCount(int count) {
    this.repeaterCount = count;
  }


  
  public int getMaxRepeaterCount() {
    return this.maxRepeaterCount;
  }
  
  public int getRepeaterCount() {
    return this.repeaterCount;
  }

  
  public int getRepeaterInterval() {
    return this.repeaterInterval;
  }




  
  public void use(PlayerEntity player) {
    super.use(player);
  }


  
  public void tick(PlayerEntity player) {
    if (isContinuous()) {
      
      if (ExtendedWorldData.get(player.world).isInsideRestrictedArea((int)player.getPosX(), (int)player.getPosY(), (int)player.getPosZ())) {
        
        endContinuity(player);
        
        return;
      } 
      this.continueTime++;
      if (!player.world.isRemote) {
        this.duringContinuityEvent.duringContinuity(player, this.continueTime);
        repeater(player, this.continueTime);
      } 
      
      if (getThreshold() != 0 && this.continueTime >= getThreshold()) {
        endContinuity(player);
      }
    } 
  }

  
  public void endContinuity(PlayerEntity player) {
    if (player.world.isRemote)
      return; 
    if (this.onEndContinuityEvent.onEndContinuity(player)) {
      
      this.continueTime = 0;
      this.repeaterCount = this.maxRepeaterCount;
      startCooldown(player);
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, this), (LivingEntity)player);
    } 
  }
}


