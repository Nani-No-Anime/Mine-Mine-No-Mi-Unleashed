package xyz.pixelatedw.mineminenomi.quests.brawler;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.abilities.brawler.HakaiHoAbility;
import xyz.pixelatedw.mineminenomi.abilities.brawler.JishinHoAbility;
import xyz.pixelatedw.mineminenomi.abilities.brawler.KingPunchAbility;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
import xyz.pixelatedw.mineminenomi.init.ModQuests;
import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.ReachDorikiObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
import xyz.pixelatedw.mineminenomi.quests.objectives.TimedKillEntityObjective;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class BrawlerTrial06Quest extends Quest {
  private Objective objective01 = (Objective)new ReachDorikiObjective("Reach %s doriki", 7000);
  private Objective objective02 = (new KillEntityObjective("Kill %s enemies using Hakai Ho", 10, SharedKillChecks.checkAbilitySource((Ability)HakaiHoAbility.INSTANCE))).addRequirement(this.objective01);
  private Objective objective03 = (new TimedKillEntityObjective("Kill %s enemies using Jishin Ho in %s seconds or less", 5, 5, SharedKillChecks.checkAbilitySource((Ability)JishinHoAbility.INSTANCE))).addRequirement(this.objective01);

  
  public BrawlerTrial06Quest() {
    super("brawler_trial_06", "Trial: King Punch");
    addRequirements(new Quest[] { ModQuests.BRAWLER_TRIAL_04, ModQuests.BRAWLER_TRIAL_05 });
    
    addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
    
    this.onCompleteEvent = this::giveReward;
  }

  
  public boolean giveReward(PlayerEntity player) {
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    
    props.addUnlockedAbility((Ability)KingPunchAbility.INSTANCE);
    
    return true;
  }
}


