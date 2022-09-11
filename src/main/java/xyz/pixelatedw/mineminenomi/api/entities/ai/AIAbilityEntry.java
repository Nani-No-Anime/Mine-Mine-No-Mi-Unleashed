package xyz.pixelatedw.mineminenomi.api.entities.ai;

import java.util.function.Function;
import net.minecraft.entity.ai.goal.Goal;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;



public class AIAbilityEntry
{
  private String id;
  private Function<OPEntity, Goal> goal;
  private int chance;
  
  public AIAbilityEntry(String id, int chance, Function<OPEntity, Goal> goal) {
    this.id = id;
    this.chance = chance;
    this.goal = goal;
  }

  
  public String getId() {
    return this.id;
  }

  
  public int getChance() {
    return this.chance;
  }

  
  public Goal getGoal(OPEntity entity) {
    return this.goal.apply(entity);
  }
}


