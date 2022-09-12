package xyz.pixelatedw.mineminenomi.wypi.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import java.io.Serializable;

public abstract class PassiveAbility
  extends Ability {
  protected IDuringPassive duringPassiveEvent = player -> {
    
    };
  private boolean isPaused = false;
  
  public PassiveAbility(String name, APIConfig.AbilityCategory category) {
    super(name, category);
    hideInGUI(true);
  }

  
  public void setPause(boolean flag) {
    this.isPaused = flag;
  }

  
  public boolean isPaused() {
    return this.isPaused;
  }

  
  public void use(PlayerEntity player) {}

  
  public void tick(PlayerEntity player) {
    if (!canUse(player)) {
      return;
    }
    if (this.isPaused) {
      return;
    }
    player.world.getProfiler().startSection(WyHelper.getResourceName(getName()));
    this.duringPassiveEvent.duringPassive(player);
    
    if (isOnCooldown())
      cooldown(player); 
    player.world.getProfiler().endSection();
  }


  
  public boolean canUse(PlayerEntity player) {
    if (getCategory() == AbilityHelper.getDevilFruitCategory() && AbilityHelper.isAffectedByWater((LivingEntity)player)) {
      return false;
    }
    return super.canUse(player);
  }
  
  public static interface IDuringPassive extends Serializable {
    void duringPassive(PlayerEntity param1PlayerEntity);
  }
}


