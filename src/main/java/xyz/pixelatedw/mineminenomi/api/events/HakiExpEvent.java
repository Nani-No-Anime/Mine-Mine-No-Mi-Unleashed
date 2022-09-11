package xyz.pixelatedw.mineminenomi.api.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import xyz.pixelatedw.mineminenomi.api.enums.HakiType;

public class HakiExpEvent
  extends PlayerEvent
{
  private float hakiExp;
  private HakiType hakiType;
  
  public HakiExpEvent(PlayerEntity player, float hakiExp, HakiType hakiType) {
    super(player);
    this.hakiExp = hakiExp;
    this.hakiType = hakiType;
  }

  
  public HakiType getHakiType() {
    return this.hakiType;
  }

  
  public float getHakiExp() {
    return this.hakiExp;
  }
}


