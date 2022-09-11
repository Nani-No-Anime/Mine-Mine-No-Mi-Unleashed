package xyz.pixelatedw.mineminenomi.quests.brawler;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.abilities.brawler.JishinHoAbility;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
import xyz.pixelatedw.mineminenomi.init.ModQuests;
import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
import xyz.pixelatedw.mineminenomi.quests.objectives.TimedKillEntityObjective;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class BrawlerTrial05Quest extends Quest {
  private Objective objective01 = (Objective)new TimedKillEntityObjective("Kill %s enemies in less than %s seconds", 3, 7, SharedKillChecks.HAS_BRALWER_HAND_CHECK);

  
  public BrawlerTrial05Quest() {
    super("brawler_trial_05", "Trial: Jishin Ho");
    addRequirement(ModQuests.BRAWLER_TRIAL_03);
    
    addObjectives(new Objective[] { this.objective01 });
    
    this.onCompleteEvent = this::giveReward;
  }

  
  public boolean giveReward(PlayerEntity player) {
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    
    props.addUnlockedAbility((Ability)JishinHoAbility.INSTANCE);
    
    return true;
  }
}


