package xyz.pixelatedw.mineminenomi.quests.blackleg;
import java.lang.invoke.SerializedLambda;
import java.util.function.Predicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import xyz.pixelatedw.mineminenomi.abilities.blackleg.SkywalkAbility;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModQuests;
import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.TimedSurvivalObjective;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class BlackLegTrial04Quest extends Quest {
  static {
    BREATH_DIAL_CHECK = (itemStack -> (itemStack.getItem() == ModBlocks.BREATH_DIAL.asItem()));
  }

  
  private static final Predicate<ItemStack> BREATH_DIAL_CHECK;
  private Objective objective01 = (Objective)new ObtainItemObjective("Collect %s breath dials", 5, BREATH_DIAL_CHECK);
  private Objective objective02 = (Objective)new ObtainItemObjective("Collect %s rabbit feet", 5, Items.RABBIT_FOOT);
  private Objective objective03 = (Objective)new TimedSurvivalObjective("Survive for %s seconds without getting hit", 1800);

  
  public BlackLegTrial04Quest() {
    super("black_leg_trial_04", "Trial: Skywalk");
    addRequirement(ModQuests.BLACK_LEG_TRIAL_03);
    
    addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
    
    this.onCompleteEvent = this::giveReward;
  }

  
  public boolean giveReward(PlayerEntity player) {
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    
    props.addUnlockedAbility((Ability)SkywalkAbility.INSTANCE);
    
    return true;
  }
}


