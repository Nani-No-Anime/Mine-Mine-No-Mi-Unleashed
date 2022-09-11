package xyz.pixelatedw.mineminenomi.wypi.abilities;

import java.io.Serializable;

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


public abstract class ChargeableAbility
  extends Ability
{
  private int chargeTime;
  private int maxChargeTime;
  private boolean isCancelable;
  protected IOnStartCharging onStartChargingEvent = player -> true;
  protected IOnEndCharging onEndChargingEvent = player -> true;
  protected IDuringCharging duringChargingEvent = (player, chargeTime) -> {
    
    };
  
  public ChargeableAbility(String name, APIConfig.AbilityCategory category) {
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
    if (isCharging() && this.chargeTime > 0) {
      
      endCharging(player);
    }
    else if (isOnStandby()) {
      
      if (this.onStartChargingEvent.onStartCharging(player)) {
        
        checkAbilityPool(player, Ability.State.CHARGING);
        
        if (((Boolean)CommonConfig.INSTANCE.animeScreaming.get()).booleanValue() && (getChargedShouts()).length > 1) {
          WyNetwork.sendToAllTrackingAndSelf(new SAnimeScreamPacket(player.getEntityId(), getChargedShouts()[0] + "..."), (LivingEntity)player);
        }
        this.chargeTime = this.maxChargeTime;
        startCharging(player);

        
        if (this instanceof xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility || this instanceof xyz.pixelatedw.mineminenomi.api.abilities.IBodyOverlayAbility || this instanceof xyz.pixelatedw.mineminenomi.api.abilities.IPunchOverlayAbility)
          WyNetwork.sendToAllTrackingAndSelf(new SSyncAbilityDataPacket(player.getEntityId(), AbilityDataCapability.get((LivingEntity)player)), (LivingEntity)player); 
        WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, this), (LivingEntity)player);
      } 
    } 
  }




  
  public void setMaxChargeTime(double seconds) {
    this.maxChargeTime = (int)(seconds * 20.0D);
    this.chargeTime = this.maxChargeTime;
  }

  
  public int getMaxChargeTime() {
    return this.maxChargeTime;
  }

  
  public int getChargeTime() {
    return this.chargeTime;
  }

  
  public void setChargeTime(int time) {
    this.chargeTime = time * 20;
  }

  
  public void setCancelable() {
    this.isCancelable = true;
  }

  
  public boolean isCancelable() {
    return this.isCancelable;
  }









  
  public void charging(PlayerEntity player) {
    if (!canUse(player)) {
      
      stopCharging(player);
      
      return;
    } 
    player.world.getProfiler().startSection(WyHelper.getResourceName(getName()));
    
    if (isCharging() && this.chargeTime > 0) {
      
      this.chargeTime = (int)(this.chargeTime - 1.0D * getTimeProgression());
      if (!player.world.isRemote && !isStateForced()) {
        this.duringChargingEvent.duringCharging(player, this.chargeTime);
      }
    } else if (isCharging() && this.chargeTime <= 0) {
      
      endCharging(player);
    } 
    
    player.world.getProfiler().endSection();
  }

  
  public void startCharging(PlayerEntity player) {
    setState(Ability.State.CHARGING);
  }





  
  public void endCharging(PlayerEntity player) {
    if (player.world.isRemote)
      return; 
    if (!isStateForced() && this.onEndChargingEvent.onEndCharging(player)) {
      
      if (((Boolean)CommonConfig.INSTANCE.animeScreaming.get()).booleanValue()) {
        WyNetwork.sendToAllTrackingAndSelf(((getChargedShouts()).length > 1) ? new SAnimeScreamPacket(player.getEntityId(), getChargedShouts()[1]) : new SAnimeScreamPacket(player.getEntityId(), getChargedShouts()[0]), (LivingEntity)player);
      }
      stopCharging(player);
    } 
  }





  
  public void stopCharging(PlayerEntity player) {
    checkAbilityPool(player, Ability.State.COOLDOWN);
    setForcedState(false);
    this.chargeTime = this.maxChargeTime;
    startCooldown(player);
    if (!player.world.isRemote) {
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, this), (LivingEntity)player);
    }
  }
  
  public String[] getChargedShouts() {
    String[] nameSplit = getDisplayName().split(" ");
    int midPoint = (int)Math.ceil(nameSplit.length / 2.0D);
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < midPoint; i++)
    {
      sb.append(nameSplit[i] + " ");
    }
    String firstShout = sb.toString().replaceAll("[:-]", "");
    sb = new StringBuilder();
    for (int j = midPoint; j < nameSplit.length; j++)
    {
      sb.append(nameSplit[j] + " ");
    }
    String secondShout = sb.toString().replaceAll("[:-]", "");
    
    (new String[2])[0] = firstShout; (new String[2])[1] = secondShout; (new String[1])[0] = firstShout; return (secondShout.length() > 0) ? new String[2] : new String[1];
  }
  
  public static interface IOnEndCharging extends Serializable {
    boolean onEndCharging(PlayerEntity param1PlayerEntity);
  }
  
  public static interface IOnStartCharging extends Serializable {
    boolean onStartCharging(PlayerEntity param1PlayerEntity);
  }
  
  public static interface IDuringCharging extends Serializable {
    void duringCharging(PlayerEntity param1PlayerEntity, int param1Int);
  }
}


