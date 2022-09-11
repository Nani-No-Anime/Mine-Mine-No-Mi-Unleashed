package xyz.pixelatedw.mineminenomi.wypi.abilities;

import java.io.Serializable;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.ForgeRegistryEntry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.commands.FGCommand;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SAnimeScreamPacket;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.WyPatreon;
import xyz.pixelatedw.mineminenomi.wypi.abilities.events.AbilityUseEvent;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public abstract class Ability
  extends ForgeRegistryEntry<Ability> {
  private String name = "";
  private String displayName;
  private String textureName = "";
  private ITextComponent tooltip;
  protected double cooldown;
  protected double maxCooldown;
  protected double disableTicks;
  protected double maxDisableTicks = 200.0D;
  private APIConfig.AbilityCategory category = APIConfig.AbilityCategory.ALL;
  private AbilityUnlock unlock = AbilityUnlock.PROGRESSION;
  private State state = State.STANDBY;
  private State previousState = State.STANDBY;
  private boolean hideInGUI = false;
  private boolean forcedState = false;
  @Deprecated
  private boolean needsClientSide = false;
  private int[] pools = new int[0];
  protected double timeProgression = 1.0D;
  
  protected final Random random = new Random(); protected IOnUse onUseEvent = player -> true;
  protected IDuringCooldown duringCooldownEvent = (player, cooldown) -> {
    
    };
  protected IOnEndCooldown onEndCooldownEvent = player -> {
    
    };
  
  public Ability(String name, APIConfig.AbilityCategory category) {
    this.name = name;
    this.category = category;
  }





  
  public void use(PlayerEntity player) {
    if (player.world.isRemote) {
      return;
    }
    player.world.getProfiler().startSection(WyHelper.getResourceName(getName()));
    
    if (isOnCooldown() && getCooldown() <= 10.0D) {
      stopCooldown(player);
    }
    if (!isOnStandby()) {
      return;
    }
    AbilityUseEvent event = new AbilityUseEvent(player, this);
    if (MinecraftForge.EVENT_BUS.post(event)) {
      return;
    }
    if (!isStateForced() && this.onUseEvent.onUse(player)) {
      
      IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
      checkAbilityPool(player, State.COOLDOWN);
      
      if (((Boolean)CommonConfig.INSTANCE.animeScreaming.get()).booleanValue()) {
        WyNetwork.sendToAllTrackingAndSelf(new SAnimeScreamPacket(player.getEntityId(), getDisplayName()), (LivingEntity)player);
      }
      startCooldown(player);
      props.setPreviouslyUsedAbility(this);
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, this), (LivingEntity)player);
    } 
    player.world.getProfiler().endSection();
  }





  
  public boolean isOnStandby() {
    return (this.state == State.STANDBY);
  }

  
  public boolean isOnCooldown() {
    return (this.state == State.COOLDOWN);
  }

  
  public boolean isPassiveEnabled() {
    return (this.state == State.PASSIVE);
  }

  
  public boolean isContinuous() {
    return (this.state == State.CONTINUOUS && !isStateForced());
  }

  
  public boolean isCharging() {
    return (this.state == State.CHARGING && !isStateForced());
  }

  
  public boolean isDisabled() {
    return (this.state == State.DISABLED);
  }

  
  public void startStandby() {
    this.previousState = this.state;
    this.state = State.STANDBY;
  }

  
  public void startDisable() {
    startDisable(20);
  }

  
  public void startDisable(int ticks) {
    this.previousState = this.state;
    this.state = State.DISABLED;
    this.maxDisableTicks = ticks;
    this.disableTicks = this.maxDisableTicks;
  }

  
  public double getDisableTicks() {
    return this.disableTicks;
  }

  
  public void setDisableTicks(double ticks) {
    this.disableTicks = ticks;
  }

  
  public void startCooldown(PlayerEntity player) {
    this.previousState = this.state;
    this.state = State.COOLDOWN;
  }

  
  public void stopCooldown(PlayerEntity player) {
    if (player.world.isRemote) {
      return;
    }
    checkAbilityPool(player, State.STANDBY);
    
    this.cooldown = this.maxCooldown;
    this.previousState = this.state;
    this.state = State.STANDBY;
    if (!isStateForced()) {
      
      this.onEndCooldownEvent.onEndCooldown(player);
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, this), (LivingEntity)player);
    } 
    setForcedState(false);
  }

  
  public void setState(State state) {
    this.previousState = this.state;
    this.state = state;
  }

  
  public State getState() {
    return this.state;
  }

  
  public State getPreviousState() {
    return this.previousState;
  }

  
  public void setForcedState(boolean flag) {
    this.forcedState = flag;
  }

  
  public boolean isStateForced() {
    return this.forcedState;
  }

  
  public void hideInGUI(boolean flag) {
    this.hideInGUI = flag;
  }

  
  public boolean isHideInGUI() {
    return this.hideInGUI;
  }

  
  public void needsClientSide() {
    this.needsClientSide = true;
  }

  
  public boolean isClientSide() {
    return this.needsClientSide;
  }

  
  public void addInPool(AbilityPool... pools) {
    int[] intPools = Arrays.<AbilityPool>stream(pools).mapToInt(AbilityPool::id).toArray();
    addInPool(intPools);
  }

  
  public void addInPool(int... pools) {
    this.pools = pools;
  }

  
  public int[] getPools() {
    return this.pools;
  }

  
  public boolean isInPool() {
    return ((Boolean)CommonConfig.INSTANCE.sharedCooldowns.get()).booleanValue() ? ((this.pools != null && this.pools.length > 0)) : false;
  }

  
  public boolean isInPool(AbilityPool pool) {
    return isInPool(pool.id());
  }

  
  public boolean isInPool(int poolId) {
    if (!((Boolean)CommonConfig.INSTANCE.sharedCooldowns.get()).booleanValue()) {
      return false;
    }
    return Arrays.stream(this.pools).anyMatch(i -> (poolId == i));
  }

  
  public boolean sharesPoolWith(Ability ability) {
    if (!((Boolean)CommonConfig.INSTANCE.sharedCooldowns.get()).booleanValue()) {
      return false;
    }
    boolean flag = false;
    for (int pool : ability.getPools()) {
      
      if (isInPool(pool)) {
        
        flag = true;
        
        break;
      } 
    } 
    return flag;
  }





  
  public void setMaxCooldown(double cooldown) {
    this.maxCooldown = cooldown * 20.0D;
    this.cooldown = this.maxCooldown;
  }

  
  public double getMaxCooldown() {
    return this.maxCooldown;
  }

  
  public void setCooldown(double cooldown) {
    this.cooldown = cooldown * 20.0D;
  }

  
  public double getCooldown() {
    return this.cooldown;
  }

  
  public void setTimeProgression(double timeScale) {
    this.timeProgression = timeScale;
  }

  
  public double getTimeProgression() {
    return this.timeProgression;
  }

  
  public void setDescription(String desc) {
    setDescription((ITextComponent)new StringTextComponent(desc));
  }

  
  public void setDescription(ITextComponent tooltip) {
    this.tooltip = tooltip;
    
    if (this instanceof IFormRequiredAbility) {
      this.tooltip.appendSibling(((IFormRequiredAbility)this).addFormRequirement());
    }
  }
  
  public ITextComponent getDescription() {
    return this.tooltip;
  }

  
  public String getName() {
    return this.name;
  }

  
  public String getI18nKey() {
    String resourceName = WyHelper.getResourceName(getName());
    return "ability." + APIConfig.projectId + "." + resourceName;
  }

  
  public String getDisplayName() {
    return !WyHelper.isNullOrEmpty(this.displayName) ? this.displayName : getName();
  }

  
  public void setDisplayName(String name) {
    this.displayName = name;
  }

  
  public boolean hasCustomTexture() {
    return !WyHelper.isNullOrEmpty(this.textureName);
  }

  
  public String getCustomTexture() {
    return this.textureName;
  }

  
  public void setCustomTexture(String texture) {
    this.textureName = WyHelper.getResourceName(texture);
  }

  
  public APIConfig.AbilityCategory getCategory() {
    return this.category;
  }

  
  public void setUnlockType(AbilityUnlock unlockType) {
    this.unlock = unlockType;
  }

  
  public AbilityUnlock getUnlockType() {
    return this.unlock;
  }





  
  public void disableTick(PlayerEntity player) {
    if (isDisabled() && this.disableTicks > 0.0D)
    {
      this.disableTicks--;
    }
  }




  
  public void cooldown(PlayerEntity player) {
    if (WyPatreon.isDevBuild() && FGCommand.NO_COOLDOWN)
    {
      stopCooldown(player);
    }
    
    player.world.getProfiler().startSection(WyHelper.getResourceName(getName()));
    
    if (isOnCooldown() && this.cooldown > 0.0D) {
      
      this.cooldown -= 1.0D * getTimeProgression();
      if (!player.world.isRemote && getPreviousState() != State.DISABLED && !isStateForced()) {
        this.duringCooldownEvent.duringCooldown(player, (int)this.cooldown);
      }
    } else if (isOnCooldown() && this.cooldown <= 0.0D) {
      
      stopCooldown(player);
    } 
    
    player.world.getProfiler().endSection();
  }

  
  public void checkAbilityPool(PlayerEntity player, State state) {
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    if (isInPool() && !isStateForced())
    {
      for (Ability abl : props.getEquippedAbilities()) {
        
        if (abl != null && abl != this && abl.isInPool() && abl.sharesPoolWith(this)) {
          
          double[] values = null;
          boolean forced = true;
          
          if (state == State.COOLDOWN) {
            values = new double[] { getCooldown() / 20.0D, getCooldown() / 20.0D };
          } else if (state == State.CHARGING && abl instanceof ChargeableAbility && this instanceof ChargeableAbility) {
            values = new double[] { (((ChargeableAbility)this).getChargeTime() / 20), (((ChargeableAbility)this).getMaxChargeTime() / 20) };
          } else if (state == State.CONTINUOUS && abl instanceof ContinuousAbility && this instanceof ContinuousAbility) {
            values = new double[] { (((ContinuousAbility)this).getContinueTime() / 20), (((ContinuousAbility)this).getThreshold() / 20) };
          } else if (state == State.STANDBY) {
            forced = false;
          } 
          abl.previousState = getState();
          abl.state = state;
          
          abl.forcedState = forced;
          
          if (values != null) {
            WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, abl, state, values), (LivingEntity)player);
          } else {
            WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, abl), (LivingEntity)player);
          } 
        } 
      } 
    }
  }



  
  public double getCooldownPercentage() {
    return this.cooldown / this.maxCooldown * 100.0D;
  }




  
  public double getInvertedCooldownPercentage() {
    return (1.0D - this.cooldown / this.maxCooldown) * 100.0D;
  }


  
  public boolean equals(Object abl) {
    if (!(abl instanceof Ability)) {
      return false;
    }
    return getName().equalsIgnoreCase(((Ability)abl).getName());
  }


  
  @Nullable
  public Ability create() {
    try {
      return getClass().getConstructor(new Class[0]).newInstance(new Object[0]);
    }
    catch (Exception ex) {
      
      System.out.println("Exception raised for " + getDisplayName());
      ex.printStackTrace();
      
      return null;
    } 
  }
  
  public static Ability deepClone(Ability abl) {
    Ability newAbility = abl.create();

    
    newAbility.unlock = abl.getUnlockType();
    
    return newAbility;
  }

  
  @Nullable
  public static Ability get(ResourceLocation res) {
    Ability ability = (Ability)GameRegistry.findRegistry(Ability.class).getValue(res);
    return ability;
  } public static interface IOnEndCooldown extends Serializable {
    void onEndCooldown(PlayerEntity param1PlayerEntity); } public static interface IDuringCooldown extends Serializable {
    void duringCooldown(PlayerEntity param1PlayerEntity, int param1Int); }
  public boolean canUse(PlayerEntity player) {
    ExtendedWorldData worldData = ExtendedWorldData.get(player.world);

    
    if (worldData.isInsideRestrictedArea(player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ())) {
      
      boolean isWhitelsited = CommonConfig.INSTANCE.getProtectionWhitelistedAbilities().contains(this);
      if (!isWhitelsited) {
        
        if (!(this instanceof PassiveAbility))
          player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_CANNOT_USE_HERE, new Object[0])); 
        return false;
      } 
    } 

    
    if (this instanceof IFormRequiredAbility) {
      
      int forms = (((IFormRequiredAbility)this).getRequiredForms(player)).length;
      if (forms > 0) {
        
        boolean flag = false;
        List<String> names = (List<String>)Arrays.<ZoanInfo>stream(((IFormRequiredAbility)this).getRequiredForms(player)).map(zoan -> zoan.getDisplayName()).collect(Collectors.toList());
        for (ZoanInfo info : ((IFormRequiredAbility)this).getRequiredForms(player)) {
          
          if (info.isActive((LivingEntity)player)) {
            
            flag = true;
            
            break;
          } 
        } 
        if (!flag) {
          
          if (!(this instanceof PassiveAbility)) {
            
            if (forms == 1)
              player.sendStatusMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ZOAN_FORM_SINGLE, new Object[] { getName(), names.get(0) }), false); 
            if (forms >= 2)
              player.sendStatusMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ZOAN_FORM_DOUBLE, new Object[] { getName(), names.get(0), names.get(1) }), false); 
          } 
          return false;
        } 
      } 
    } 
    
    return true;
  }
  
  public static interface IOnUse
    extends Serializable {
    boolean onUse(PlayerEntity param1PlayerEntity);
  }
  
  public enum State {
    STANDBY, DISABLED,
    
    COOLDOWN, PASSIVE, CONTINUOUS, CHARGING;
  }
}


