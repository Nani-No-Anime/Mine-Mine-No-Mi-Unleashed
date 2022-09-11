package xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;

public class ThunderstormTempo extends TempoAbility {
  public static final ThunderstormTempo INSTANCE = new ThunderstormTempo();

  
  public ThunderstormTempo() {
    super("Thunderstorm Tempo", AbilityHelper.getStyleCategory());
    setDescription("Thunderbolt Tempo + 2 Thunder Balls\nHits multiple enemies below the cloud with multiple lightning bolts");
    setCustomTexture("tempo");
    setMaxCooldown(15.0D);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    return true;
  }
}


