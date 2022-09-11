package xyz.pixelatedw.mineminenomi.quests.sniper;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.abilities.sniper.TetsuBoshiAbility;
import xyz.pixelatedw.mineminenomi.abilities.sniper.TokuyoAburaBoshiAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class SniperTrial03Quest extends Quest {
  static {
    JUMPSHOT_CHECK = ((player, target, source) -> {
        ItemStack heldItem = player.getHeldItemMainhand();
        
        return (ItemsHelper.isBow(heldItem) && !player.onGround);
      });
  }
  private static final KillEntityObjective.ICheckKill JUMPSHOT_CHECK; private Objective objective01 = (Objective)new ObtainItemObjective("Collect %s ink sacs", 40, Items.INK_SAC);
  private Objective objective02 = (Objective)new ObtainItemObjective("Collect %s dried kelp", 30, Items.DRIED_KELP);
  private Objective objective03 = (Objective)new ObtainItemObjective("Collect %s iron ingots", 30, Items.IRON_INGOT);
  private Objective objective04 = (new KillEntityObjective("Kill %s enemies with jump shots", 20, JUMPSHOT_CHECK)).addRequirements(new Objective[] { this.objective01, this.objective02, this.objective03 });

  
  public SniperTrial03Quest() {
    super("sniper_trial_03", "Trial: Hazards");
    addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03, this.objective04 });
    
    this.onCompleteEvent = this::giveReward;
  }

  
  private boolean giveReward(PlayerEntity player) {
    if (!removeQuestItem(player, Items.INK_SAC, 40)) {
      return false;
    }
    if (!removeQuestItem(player, Items.DRIED_KELP, 30)) {
      return false;
    }
    if (!removeQuestItem(player, Items.IRON_INGOT, 30)) {
      return false;
    }
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    
    props.addUnlockedAbility(TokuyoAburaBoshiAbility.INSTANCE);
    props.addUnlockedAbility(TetsuBoshiAbility.INSTANCE);
    
    return true;
  }
}


