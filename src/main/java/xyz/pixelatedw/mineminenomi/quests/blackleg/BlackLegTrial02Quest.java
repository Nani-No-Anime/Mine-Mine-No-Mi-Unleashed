package xyz.pixelatedw.mineminenomi.quests.blackleg;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.abilities.blackleg.ExtraHachisAbility;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
import xyz.pixelatedw.mineminenomi.init.ModQuests;
import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
import xyz.pixelatedw.mineminenomi.quests.objectives.TimedKillEntityObjective;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class BlackLegTrial02Quest extends Quest {
  private Objective objective01 = (Objective)new TimedKillEntityObjective("Kill %s enemies in less than %s seconds", 3, 7, SharedKillChecks.IS_KICKING);

  
  public BlackLegTrial02Quest() {
    super("black_leg_trial_02", "Trial: Extra Hachis");
    addRequirement(ModQuests.BLACK_LEG_TRIAL_01);
    
    addObjectives(new Objective[] { this.objective01 });
    
    this.onCompleteEvent = this::giveReward;
  }

  
  public boolean giveReward(PlayerEntity player) {
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    
    props.addUnlockedAbility((Ability)ExtraHachisAbility.INSTANCE);
    
    return true;
  }
}


