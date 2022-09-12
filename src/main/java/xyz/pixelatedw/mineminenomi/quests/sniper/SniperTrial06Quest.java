package xyz.pixelatedw.mineminenomi.quests.sniper;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.mineminenomi.abilities.sniper.RenpatsuNamariBoshiAbility;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModQuests;
import xyz.pixelatedw.mineminenomi.quests.objectives.HitEntityObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
import xyz.pixelatedw.mineminenomi.quests.objectives.TimedKillEntityObjective;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

import java.util.function.Predicate;

public class SniperTrial06Quest extends Quest {
  private static final Predicate<ItemStack> IMPACT_DIAL_BOW;
  
  static {
    IMPACT_DIAL_BOW = (itemStack -> (itemStack.getItem() == ModBlocks.IMPACT_DIAL.asItem()));



    
    DETONATE_CREEPER_CHECK = ((player, target, source, amount) -> 
      
      (target.getType() == EntityType.CREEPER && source.getImmediateSource() instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.sniper.KaenBoshiProjectile));
  }
  private static final HitEntityObjective.ICheckHit DETONATE_CREEPER_CHECK;
  private Objective objective01 = (Objective)new ObtainItemObjective("Collect %s impact dials", 3, IMPACT_DIAL_BOW);
  private Objective objective02 = (Objective)new TimedKillEntityObjective("Kill %s enemies in less than %s seconds", 3, 5, SharedKillChecks.HAS_BOW);
  private Objective objective03 = (Objective)new HitEntityObjective("Detonate %s Creepers using Kaen Boshi", 3, DETONATE_CREEPER_CHECK);

  
  public SniperTrial06Quest() {
    super("sniper_trial_06", "Trial: Renpatsu Namari Boshi");
    addRequirements(new Quest[] { ModQuests.SNIPER_TRIAL_05 });
    addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
    
    this.onCompleteEvent = this::giveReward;
  }

  
  private boolean giveReward(PlayerEntity player) {
    if (!removeQuestItem(player, ModBlocks.IMPACT_DIAL.asItem(), 3)) {
      return false;
    }
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    
    props.addUnlockedAbility((Ability)RenpatsuNamariBoshiAbility.INSTANCE);
    
    return true;
  }
}


