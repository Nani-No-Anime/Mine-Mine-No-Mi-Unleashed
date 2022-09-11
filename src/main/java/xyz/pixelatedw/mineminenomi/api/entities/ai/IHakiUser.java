package xyz.pixelatedw.mineminenomi.api.entities.ai;

import net.minecraft.entity.ai.goal.Goal;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuFullBodyHakiGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.HaoshokuHakiGoal;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public interface IHakiUser
{
  default void addBusoshokuHaki(OPEntity entity, int chance) {
    if (entity.world.isRemote) {
      return;
    }
    if (chance > WyHelper.randomWithRange(0, 100))
    {
      if ((chance / 3) > WyHelper.randomWithRange(0, 100)) {
        entity.goalSelector.addGoal(1, (Goal)new BusoshokuFullBodyHakiGoal(entity));
      } else {
        entity.goalSelector.addGoal(1, (Goal)new BusoshokuHakiGoal(entity));
      } 
    }
  }
  
  default void addHaoshokuHaki(OPEntity entity, int chance) {
    if (entity.world.isRemote) {
      return;
    }
    if (chance > WyHelper.randomWithRange(0, 100))
    {
      entity.goalSelector.addGoal(1, (Goal)new HaoshokuHakiGoal(entity, (float)WyHelper.randomWithRange(0, 100)));
    }
  }
}


