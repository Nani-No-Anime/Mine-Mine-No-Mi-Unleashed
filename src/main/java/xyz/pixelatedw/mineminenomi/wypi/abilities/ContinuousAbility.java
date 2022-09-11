package xyz.pixelatedw.mineminenomi.wypi.abilities;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SAnimeScreamPacket;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.events.AbilityUseEvent;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;

public abstract class ContinuousAbility
  extends Ability
{
  protected int threshold = 0;
  protected int continueTime = 0;
  protected IOnStartContinuity onStartContinuityEvent = player -> true;
  protected IOnEndContinuity onEndContinuityEvent = player -> true;
  protected IDuringContinuity duringContinuityEvent = (player, continuousTime) -> {
    
    };
  protected IOnStopContinuity onStopContinuityEvent = player -> {
    
    };
  
  public ContinuousAbility(String name, APIConfig.AbilityCategory category) {
    super(name, category);
  }






  
  public void use(PlayerEntity player) {
    if (player.world.isRemote) {
      return;
    }
    if (isOnCooldown() && getCooldown() <= 10.0D) {
      stopCooldown(player);
    }
    AbilityUseEvent event = new AbilityUseEvent(player, this);
    if (MinecraftForge.EVENT_BUS.post(event)) {
      return;
    }
    if (!isContinuous()) {
      
      if (!isOnStandby()) {
        return;
      }
      if (this.onStartContinuityEvent.onStartContinuity(player))
      {
        checkAbilityPool(player, Ability.State.CONTINUOUS);
        
        if (((Boolean)CommonConfig.INSTANCE.animeScreaming.get()).booleanValue() && !(this instanceof PunchAbility)) {
          WyNetwork.sendToAllTrackingAndSelf(new SAnimeScreamPacket(player.getEntityId(), getDisplayName()), (LivingEntity)player);
        }
        startContinuity(player);

        
        if (this instanceof xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility || this instanceof xyz.pixelatedw.mineminenomi.api.abilities.IBodyOverlayAbility || this instanceof xyz.pixelatedw.mineminenomi.api.abilities.IPunchOverlayAbility)
          WyNetwork.sendToAllTrackingAndSelf(new SSyncAbilityDataPacket(player.getEntityId(), AbilityDataCapability.get((LivingEntity)player)), (LivingEntity)player); 
        WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, this), (LivingEntity)player);
      
      }
    
    }
    else if (!isStateForced()) {
      endContinuity(player);
    } 
  }






  
  public void setThreshold(double threshold) {
    this.threshold = (int)(threshold * 20.0D);
  }

  
  public void setThresholdInTicks(int threshold) {
    this.threshold = threshold;
  }

  
  public int getThreshold() {
    return this.threshold;
  }

  
  public void setContinueTime(int time) {
    this.continueTime = time * 20;
  }

  
  public int getContinueTime() {
    return this.continueTime;
  }









  
  public void tick(PlayerEntity player) {
    if (!canUse(player)) {
      
      stopContinuity(player);
      
      return;
    } 
    player.world.getProfiler().startSection(WyHelper.getResourceName(getName()));
    
    if (isContinuous()) {
      
      this.continueTime++;
      
      if ((isClientSide() || !player.world.isRemote) && !isStateForced()) {
        this.duringContinuityEvent.duringContinuity(player, this.continueTime);
      }
      if (this.threshold > 0 && this.continueTime >= this.threshold) {
        endContinuity(player);
      }
    } 
    player.world.getProfiler().endSection();
  }

  
  public void startContinuity(PlayerEntity player) {
    setState(Ability.State.CONTINUOUS);
  }





  
  public void endContinuity(PlayerEntity player) {
    if (player.world.isRemote) {
      return;
    }
    if (this.onEndContinuityEvent.onEndContinuity(player))
    {
      stopContinuity(player);
    }
  }





  
  public void stopContinuity(PlayerEntity player) {
    checkAbilityPool(player, Ability.State.COOLDOWN);
    this.continueTime = 0;
    startCooldown(player);
    if (!player.world.isRemote)
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, this), (LivingEntity)player); 
    this.onStopContinuityEvent.onStopContinuity(player);
  }
  
  public static interface IOnStopContinuity extends Serializable {
    void onStopContinuity(PlayerEntity param1PlayerEntity);
  }
  
  public static interface IOnEndContinuity extends Serializable {
    boolean onEndContinuity(PlayerEntity param1PlayerEntity);
  }
  
  public static interface IOnStartContinuity extends Serializable {
    boolean onStartContinuity(PlayerEntity param1PlayerEntity);
  }
  
  public static interface IDuringContinuity extends Serializable {
    void duringContinuity(PlayerEntity param1PlayerEntity, int param1Int);
  }
}


