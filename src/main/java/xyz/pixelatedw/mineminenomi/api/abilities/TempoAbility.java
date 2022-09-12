package xyz.pixelatedw.mineminenomi.api.abilities;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

import java.io.Serializable;

public abstract class TempoAbility
  extends Ability
{
  protected ICanUse canUseCheck = (player, check) -> false;
  
  public TempoAbility(String name, APIConfig.AbilityCategory category) {
    super(name, category);
  }

  
  public boolean canUseTempo(PlayerEntity player, ICanUse check) {
    return (check != null) ? check.canUse(player, check) : this.canUseCheck.canUse(player, check);
  }
  
  public static interface ICanUse extends Serializable {
    boolean canUse(PlayerEntity param1PlayerEntity, ICanUse param1ICanUse);
  }
}


