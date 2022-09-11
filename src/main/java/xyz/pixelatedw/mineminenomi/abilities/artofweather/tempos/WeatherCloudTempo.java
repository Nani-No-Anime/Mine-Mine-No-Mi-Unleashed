package xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;

public class WeatherCloudTempo extends TempoAbility {
  public static final WeatherCloudTempo INSTANCE = new WeatherCloudTempo();

  
  public WeatherCloudTempo() {
    super("Weather Cloud Tempo", AbilityHelper.getStyleCategory());
    setDescription("Cool Ball + Heat Ball\nCreates a cloud above you");
    setCustomTexture("tempo");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    return true;
  }
}


