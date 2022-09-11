package xyz.pixelatedw.mineminenomi.quests.objectives;

import java.util.Arrays;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.ICureEffectObjective;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;

public class CureEffectObjective
  extends Objective
  implements ICureEffectObjective
{
  private Effect[] effects;
  
  public CureEffectObjective(String title, int count, Effect effect) {
    this(title, count, new Effect[] { effect });
  }

  
  public CureEffectObjective(String title, int count, Effect[] effects) {
    super(title);
    setMaxProgress(count);
    this.effects = effects;
  }


  
  public boolean checkEffect(PlayerEntity player, EffectInstance effectInstance) {
    return Arrays.<Effect>stream(this.effects).anyMatch(effect -> (effectInstance.getPotion() == effect));
  }
}


