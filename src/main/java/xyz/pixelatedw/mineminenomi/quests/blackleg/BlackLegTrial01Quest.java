package xyz.pixelatedw.mineminenomi.quests.blackleg;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.abilities.blackleg.ConcasseAbility;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class BlackLegTrial01Quest extends Quest {
  private Objective objective01 = (Objective)new KillEntityObjective("Kill %s enemies using your kicks", 30, SharedKillChecks.IS_KICKING);
  private Objective objective02 = (Objective)new KillEntityObjective("Kill %s enemies using critical hits", 15, SharedKillChecks.IS_KICKING.and(SharedKillChecks.CRITICAL_KILL_CHECK));

  
  public BlackLegTrial01Quest() {
    super("black_leg_trial_01", "Trial: Concasse");
    
    addObjectives(new Objective[] { this.objective01, this.objective02 });
    
    this.onCompleteEvent = this::giveReward;
  }

  
  public boolean giveReward(PlayerEntity player) {
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    
    props.addUnlockedAbility((Ability)ConcasseAbility.INSTANCE);
    
    return true;
  }
}


