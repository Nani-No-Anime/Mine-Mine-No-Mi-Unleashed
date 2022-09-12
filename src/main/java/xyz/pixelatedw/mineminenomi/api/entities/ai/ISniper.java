package xyz.pixelatedw.mineminenomi.api.entities.ai;

import xyz.pixelatedw.mineminenomi.abilities.sniper.KaenBoshiAbility;
import xyz.pixelatedw.mineminenomi.abilities.sniper.KemuriBoshiAbility;
import xyz.pixelatedw.mineminenomi.abilities.sniper.NemuriBoshiAbility;
import xyz.pixelatedw.mineminenomi.abilities.sniper.TetsuBoshiAbility;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper.KaenBoshiGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper.KemuriBoshiGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper.NemuriBoshiGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper.TetsuBoshiGoal;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import java.util.ArrayList;
import java.util.List;

public interface ISniper
{
  public static final List<AIAbilityEntry> ABILITIES_POOL = new ArrayList<>();

  
  default void addSniperAbilities(OPEntity opEntity, int max) {
    if (opEntity.world.isRemote) {
      return;
    }
    if (ABILITIES_POOL.isEmpty()) {
      
      ABILITIES_POOL.add(new AIAbilityEntry(KaenBoshiAbility.INSTANCE.getName().toLowerCase(), 5, e -> new KaenBoshiGoal(e)));
      ABILITIES_POOL.add(new AIAbilityEntry(KemuriBoshiAbility.INSTANCE.getName().toLowerCase(), 3, e -> new KemuriBoshiGoal(e)));
      ABILITIES_POOL.add(new AIAbilityEntry(TetsuBoshiAbility.INSTANCE.getName().toLowerCase(), 3, e -> new TetsuBoshiGoal(e)));
      ABILITIES_POOL.add(new AIAbilityEntry(NemuriBoshiAbility.INSTANCE.getName().toLowerCase(), 1, e -> new NemuriBoshiGoal(e)));
    } 
    
    int abilitiesCount = 0;
    List<String> goals = new ArrayList<>();
    
    while (abilitiesCount < max) {
      
      for (AIAbilityEntry entry : ABILITIES_POOL) {
        
        if (!goals.contains(entry.getId()) && WyHelper.randomWithRange(1, 10) <= entry.getChance()) {
          
          opEntity.goalSelector.addGoal(1, entry.getGoal(opEntity));
          abilitiesCount++;
          goals.add(entry.getId());
          
          break;
        } 
      } 
      abilitiesCount++;
    } 
  }
}


