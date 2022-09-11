package xyz.pixelatedw.mineminenomi.api.entities.ai;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.ai.goal.Goal;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.GeppoAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.RankyakuAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.SoruAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.TekkaiAbility;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.GeppoGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.RankyakuGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.SoruGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.TekkaiGoal;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public interface IRokushikiUser
{
  public static final List<AIAbilityEntry> ABILITIES_POOL = new ArrayList<>();

  
  default void addRokushikiAbilities(OPEntity entity, int max) {
    if (entity.world.isRemote) {
      return;
    }
    if (ABILITIES_POOL.isEmpty()) {
      
      ABILITIES_POOL.add(new AIAbilityEntry(SoruAbility.INSTANCE.getName().toLowerCase(), 5, e -> new SoruGoal(e)));
      ABILITIES_POOL.add(new AIAbilityEntry(TekkaiAbility.INSTANCE.getName().toLowerCase(), 3, e -> new TekkaiGoal(e)));
      ABILITIES_POOL.add(new AIAbilityEntry(RankyakuAbility.INSTANCE.getName().toLowerCase(), 1, e -> new RankyakuGoal(e)));
      ABILITIES_POOL.add(new AIAbilityEntry(GeppoAbility.INSTANCE.getName().toLowerCase(), 2, e -> new GeppoGoal(e)));
    } 
    
    int rokushikiCount = 0;
    List<String> goals = new ArrayList<>();
    
    while (rokushikiCount < max) {
      
      for (AIAbilityEntry entry : ABILITIES_POOL) {
        
        if (!goals.contains(entry.getId()) && WyHelper.randomWithRange(1, 10) <= entry.getChance()) {
          
          entity.goalSelector.addGoal(1, entry.getGoal(entity));
          rokushikiCount++;
          goals.add(entry.getId());
          
          break;
        } 
      } 
      rokushikiCount++;
    } 
  }
}


