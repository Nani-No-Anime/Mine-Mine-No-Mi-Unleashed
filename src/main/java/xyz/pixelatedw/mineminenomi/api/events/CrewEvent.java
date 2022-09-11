package xyz.pixelatedw.mineminenomi.api.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;
import xyz.pixelatedw.mineminenomi.api.crew.Crew;

public class CrewEvent
  extends PlayerEvent
{
  private Crew crew;
  
  public CrewEvent(PlayerEntity player, Crew crew) {
    super(player);
    this.crew = crew;
  }

  
  public Crew getCrew() {
    return this.crew;
  }
  
  @Cancelable
  public static class Join
    extends CrewEvent
  {
    public Join(PlayerEntity player, Crew crew) {
      super(player, crew);
    }
  }
  
  @Cancelable
  public static class Leave
    extends CrewEvent
  {
    public Leave(PlayerEntity player, Crew crew) {
      super(player, crew);
    }
  }
}


