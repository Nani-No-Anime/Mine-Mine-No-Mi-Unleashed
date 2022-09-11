package xyz.pixelatedw.mineminenomi.quests.swordsman;
import java.lang.invoke.SerializedLambda;
import java.util.function.Predicate;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.mineminenomi.abilities.swordsman.YakkodoriAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
import xyz.pixelatedw.mineminenomi.quests.objectives.TimedHitEntityObjective;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class SwordsmanTrial02Quest extends Quest {
  static {
    ITEM_WITH_SHARPNESS_2 = (itemStack -> 
      
      (ItemsHelper.isSword(itemStack) && EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, itemStack) > 1));
  }
  private static final Predicate<ItemStack> ITEM_WITH_SHARPNESS_2;
  private Objective objective01 = (Objective)new TimedHitEntityObjective("Hit %s enemies at the same time", 3, 2);
  private Objective objective02 = (Objective)new KillEntityObjective("Kill %s enemies with critical hits", 25, SharedKillChecks.CRITICAL_KILL_CHECK.and(SharedKillChecks.HAS_SWORD));
  private Objective objective03 = (Objective)new ObtainItemObjective("Obtain a sword with Sharpness II", 1, ITEM_WITH_SHARPNESS_2);

  
  public SwordsmanTrial02Quest() {
    super("swordsman_trial_02", "Trial: Yakkodori");
    addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
    
    this.onCompleteEvent = this::giveReward;
  }

  
  public boolean giveReward(PlayerEntity player) {
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    
    props.addUnlockedAbility(YakkodoriAbility.INSTANCE);
    
    return true;
  }
}


