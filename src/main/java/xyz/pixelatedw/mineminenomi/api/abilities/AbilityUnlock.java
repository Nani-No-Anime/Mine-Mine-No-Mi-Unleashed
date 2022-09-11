package xyz.pixelatedw.mineminenomi.api.abilities;

import net.minecraftforge.common.IExtensibleEnum;

public enum AbilityUnlock
  implements IExtensibleEnum {
  PROGRESSION,
  COMMAND;

  
  public static AbilityUnlock create(String name) {
    throw new IllegalStateException("Enum not extended");
  }
}


