package xyz.pixelatedw.mineminenomi.api.entities.ai;

import xyz.pixelatedw.mineminenomi.abilities.swordsman.OTatsumakiAbility;
import xyz.pixelatedw.mineminenomi.abilities.swordsman.YakkodoriAbility;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman.OTatsumakiGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman.YakkodoriGoal;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import java.util.ArrayList;
import java.util.List;

public interface ISwordsman
{
  public static final List<AIAbilityEntry> ABILITIES_POOL = new ArrayList<>();

  
  default void addSwordsmanAbilities(OPEntity entity, int max) {
    if (entity.world.isRemote) {
      return;
    }
    if (ABILITIES_POOL.isEmpty()) {
      
      ABILITIES_POOL.add(new AIAbilityEntry(YakkodoriAbility.INSTANCE.getName().toLowerCase(), 4, e -> new YakkodoriGoal(e)));
      ABILITIES_POOL.add(new AIAbilityEntry(OTatsumakiAbility.INSTANCE.getName().toLowerCase(), 2, e -> new OTatsumakiGoal(e)));
    } 
    
    int abilitiesCount = 0;
    List<String> goals = new ArrayList<>();
    
    while (abilitiesCount < max) {
      
      for (AIAbilityEntry entry : ABILITIES_POOL) {
        
        if (!goals.contains(entry.getId()) && WyHelper.randomWithRange(1, 10) <= entry.getChance()) {
          
          entity.goalSelector.addGoal(1, entry.getGoal(entity));
          abilitiesCount++;
          goals.add(entry.getId());
          
          break;
        } 
      } 
      abilitiesCount++;
    } 
  }
}


