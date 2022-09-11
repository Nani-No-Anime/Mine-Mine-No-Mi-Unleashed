package xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.storage.WorldInfo;
import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;

public class RainTempo extends TempoAbility {
  public static final RainTempo INSTANCE = new RainTempo();

  
  public RainTempo() {
    super("Rain Tempo", AbilityHelper.getStyleCategory());
    setDescription("Weather Cloud + 3 Cool Balls\nCreates rain");
    setCustomTexture("tempo");
    setMaxCooldown(10.0D);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    WorldInfo worldinfo = player.world.getWorldInfo();
    worldinfo.setRaining(true);
    return true;
  }
}


