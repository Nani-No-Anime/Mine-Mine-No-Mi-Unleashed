package xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;

public class ThunderboltTempo extends TempoAbility {
  public static final ThunderboltTempo INSTANCE = new ThunderboltTempo();

  
  public ThunderboltTempo() {
    super("Thunderbolt Tempo", AbilityHelper.getStyleCategory());
    setDescription("Weather Cloud + Thunder Ball\nHits an enemy below the cloud with lightning bolt");
    setCustomTexture("tempo");
    setMaxCooldown(10.0D);
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    return true;
  }
}


