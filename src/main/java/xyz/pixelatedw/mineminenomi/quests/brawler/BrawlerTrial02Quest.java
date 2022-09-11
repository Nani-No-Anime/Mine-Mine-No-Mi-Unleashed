package xyz.pixelatedw.mineminenomi.quests.brawler;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.abilities.brawler.SpinningBrawlAbility;
import xyz.pixelatedw.mineminenomi.abilities.brawler.SuplexAbility;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
import xyz.pixelatedw.mineminenomi.init.ModQuests;
import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class BrawlerTrial02Quest extends Quest {
  private Objective objective01 = (Objective)new KillEntityObjective("Kill %s enemies using Suplex", 20, SharedKillChecks.HAS_EMPTY_HAND.and(SharedKillChecks.checkAbilitySource((Ability)SuplexAbility.INSTANCE)));

  
  public BrawlerTrial02Quest() {
    super("brawler_trial_02", "Trial: Spinning Brawl");
    addRequirement(ModQuests.BRAWLER_TRIAL_01);



    
    addObjectives(new Objective[] { this.objective01 });
    
    this.onCompleteEvent = this::giveReward;
  }

  
  public boolean startArena(PlayerEntity player) {
    return true;
  }

  
  public boolean giveReward(PlayerEntity player) {
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    
    props.addUnlockedAbility((Ability)SpinningBrawlAbility.INSTANCE);
    
    return true;
  }
}


