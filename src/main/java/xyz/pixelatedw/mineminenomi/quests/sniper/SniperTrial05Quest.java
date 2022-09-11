package xyz.pixelatedw.mineminenomi.quests.sniper;

import java.lang.invoke.SerializedLambda;
import java.util.function.Predicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import xyz.pixelatedw.mineminenomi.abilities.sniper.NemuriBoshiAbility;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModQuests;
import xyz.pixelatedw.mineminenomi.quests.objectives.BrewPotionObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class SniperTrial05Quest
  extends Quest
{
  private static final Predicate<ItemStack> BREATH_DIAL_CHECK;
  
  static {
    BREATH_DIAL_CHECK = (itemStack -> (itemStack.getItem() == ModBlocks.BREATH_DIAL.asItem()));
  }


  
  private Objective objective01 = (Objective)new ObtainItemObjective("Collect %s breath dials", 3, BREATH_DIAL_CHECK);
  private Objective objective02 = (Objective)new KillEntityObjective("Kill %s airborne enemies with your bow", 20, SharedKillChecks.AIRBORNE_ENEMY_CHECK);
  private Objective objective03 = (new BrewPotionObjective("Brew %s splash potions", 12, new Item[] { Items.SPLASH_POTION }, null)).addRequirements(new Objective[] { this.objective01 });

  
  public SniperTrial05Quest() {
    super("sniper_trial_05", "Trial: Nemuri Boshi");
    addRequirements(new Quest[] { ModQuests.SNIPER_TRIAL_04 });
    addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
    
    this.onCompleteEvent = this::giveReward;
  }

  
  private boolean giveReward(PlayerEntity player) {
    if (!removeQuestItem(player, ModBlocks.BREATH_DIAL.asItem(), 3)) {
      return false;
    }
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    
    props.addUnlockedAbility(NemuriBoshiAbility.INSTANCE);
    
    return true;
  }
}


