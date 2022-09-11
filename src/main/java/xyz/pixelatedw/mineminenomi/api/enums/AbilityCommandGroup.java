package xyz.pixelatedw.mineminenomi.api.enums;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import net.minecraftforge.common.IExtensibleEnum;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public enum AbilityCommandGroup
  implements IExtensibleEnum
{
  HUMAN(() -> ModAbilities.HUMAN_ABILITIES),
  FISHMAN(() -> ModAbilities.FISHMAN_ABILITIES),
  CYBORG(() -> ModAbilities.CYBORG_ABILITIES),
  MINK(() -> ModAbilities.MINK_ABILITIES),
  
  SWORDSMAN(() -> ModAbilities.SWORDSMAN_ABILITIES),
  SNIPER(() -> ModAbilities.SNIPER_ABILITIES),
  DOCTOR(() -> ModAbilities.DOCTOR_ABILITIES),
  ART_OF_WEATHER(() -> ModAbilities.ART_OF_WEATHER_ABILITIES),
  BRAWLER(() -> ModAbilities.BRAWLER_ABILITIES),
  BLACK_LEG(() -> ModAbilities.BLACK_LEG_ABILITIES),
  
  HAKI(() -> ModAbilities.HAKI_ABILITIES);
  
  private Supplier<Ability[]> abilities;

  
  AbilityCommandGroup(Supplier<Ability[]> abilities) {
    this.abilities = abilities;
  }

  
  public List<Ability> getAbilities() {
    return Arrays.asList(this.abilities.get());
  }

  
  public static AbilityCommandGroup create(String name, Supplier<Ability[]> abilities) {
    throw new IllegalStateException("Enum not extended");
  }
}


