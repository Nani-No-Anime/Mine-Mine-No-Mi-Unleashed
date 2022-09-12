package xyz.pixelatedw.mineminenomi.quests.swordsman;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.abilities.swordsman.OTatsumakiAbility;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
import xyz.pixelatedw.mineminenomi.init.ModQuests;
import xyz.pixelatedw.mineminenomi.quests.objectives.*;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class SwordsmanTrial04Quest extends Quest {
  private Objective objective01 = (Objective)new HitEntityObjective("Damage %s enemies with sweeping attacks", 20, SharedHitChecks.SWEEP_ATTACK_CHECK);
  private Objective objective02 = (Objective)new TimedKillEntityObjective("Kill %s enemies in less than %s seconds", 3, 5, SharedKillChecks.HAS_SWORD);
  private Objective objective03 = (Objective)new KillEntityObjective("Kill %s airborne enemies with your sword", 5, SharedKillChecks.AIRBORNE_ENEMY_CHECK);

  
  public SwordsmanTrial04Quest() {
    super("swordsman_trial_04", "Trial: O Tatsumaki");
    addRequirements(new Quest[] { ModQuests.SWORDSMAN_TRIAL_03 });
    addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
    
    this.onCompleteEvent = this::giveReward;
  }

  
  public boolean giveReward(PlayerEntity player) {
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    
    props.addUnlockedAbility(OTatsumakiAbility.INSTANCE);
    
    return true;
  }
}


