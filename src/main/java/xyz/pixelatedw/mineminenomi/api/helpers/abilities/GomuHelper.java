package xyz.pixelatedw.mineminenomi.api.helpers.abilities;

import xyz.pixelatedw.mineminenomi.abilities.gomu.GearFourthAbility;
import xyz.pixelatedw.mineminenomi.abilities.gomu.GearSecondAbility;
import xyz.pixelatedw.mineminenomi.abilities.gomu.GearThirdAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;


public class GomuHelper
{
  public static boolean canActivateGear(IAbilityData props, Ability gear) {
    if (gear.equals(GearSecondAbility.INSTANCE)) {
      
      if (hasGearThirdActive(props) || hasGearFourthActive(props)) {
        return false;
      }
    } else if (gear.equals(GearThirdAbility.INSTANCE)) {
      
      if (hasGearSecondActive(props) || hasGearFourthActive(props)) {
        return false;
      }
    } else if (gear.equals(GearFourthAbility.INSTANCE)) {
      
      if (hasGearThirdActive(props) || hasGearSecondActive(props)) {
        return false;
      }
    } 
    return true;
  }

  
  public static boolean hasGearSecondActive(IAbilityData props) {
    Ability ability = props.getEquippedAbility((Ability)GearSecondAbility.INSTANCE);
    boolean isActive = (ability != null && ability.isContinuous());
    return isActive;
  }

  
  public static boolean hasGearThirdActive(IAbilityData props) {
    Ability ability = props.getEquippedAbility((Ability)GearThirdAbility.INSTANCE);
    boolean isActive = (ability != null && ability.isContinuous());
    return isActive;
  }

  
  public static boolean hasGearFourthActive(IAbilityData props) {
    Ability ability = props.getEquippedAbility((Ability)GearFourthAbility.INSTANCE);
    boolean isActive = (ability != null && ability.isContinuous());
    return isActive;
  }
}


