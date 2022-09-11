package xyz.pixelatedw.mineminenomi.api.entities.ai;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.ai.goal.Goal;
import xyz.pixelatedw.mineminenomi.abilities.brawler.GenkotsuMeteorAbility;
import xyz.pixelatedw.mineminenomi.abilities.brawler.HakaiHoAbility;
import xyz.pixelatedw.mineminenomi.abilities.brawler.JishinHoAbility;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.GenkotsuMeteorGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.HakaiHoGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.JishinHoGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.TatsuMakiGoal;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public interface IBrawler
{
  public static final List<AIAbilityEntry> ABILITIES_POOL = new ArrayList<>();

  
  default void addBrawlerAbilities(OPEntity entity, int max) {
    if (entity.world.isRemote) {
      return;
    }
    if (ABILITIES_POOL.isEmpty()) {
      
      ABILITIES_POOL.add(new AIAbilityEntry(HakaiHoAbility.INSTANCE.getName().toLowerCase(), 3, e -> new HakaiHoGoal(e)));
      ABILITIES_POOL.add(new AIAbilityEntry(GenkotsuMeteorAbility.INSTANCE.getName().toLowerCase(), 5, e -> new GenkotsuMeteorGoal(entity)));
      ABILITIES_POOL.add(new AIAbilityEntry(JishinHoAbility.INSTANCE.getName().toLowerCase(), 1, e -> new JishinHoGoal(entity)));
      
      ABILITIES_POOL.add(new AIAbilityEntry("tatsu maki", 3, e -> new TatsuMakiGoal(entity)));
    } 
    
    int abilitiesCount = 0;
    List<String> goals = new ArrayList<>();
    
    while (abilitiesCount < max) {
      
      for (AIAbilityEntry entry : ABILITIES_POOL) {
        
        if (!goals.contains(entry.getId()) && WyHelper.randomWithRange(1, 10) <= entry.getChance()) {
          
          entity.goalSelector.addGoal(3, entry.getGoal(entity));
          abilitiesCount++;
          goals.add(entry.getId());
          
          break;
        } 
      } 
      abilitiesCount++;
    } 
  }
}


