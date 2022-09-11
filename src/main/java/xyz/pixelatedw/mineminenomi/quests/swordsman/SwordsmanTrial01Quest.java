package xyz.pixelatedw.mineminenomi.quests.swordsman;
import java.lang.invoke.SerializedLambda;
import java.util.function.Predicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import xyz.pixelatedw.mineminenomi.abilities.swordsman.ShiShishiSonsonAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class SwordsmanTrial01Quest extends Quest {
  static {
    ITEM_OVER_7_DAMAGE = (itemStack -> 
      
      (ItemsHelper.isSword(itemStack) && ItemsHelper.getItemDamage(itemStack) > 7.0F));
  }
  private static final Predicate<ItemStack> ITEM_OVER_7_DAMAGE;
  private Objective objective01 = (Objective)new ObtainItemObjective("Obtain a sword with over 7 damage", 1, ITEM_OVER_7_DAMAGE);
  private Objective objective02 = (new ObtainItemObjective("Collect %s bones", 30, Items.BONE)).addRequirement(this.objective01);
  private Objective objective03 = (new KillEntityObjective("Kill %s enemies while running towards them", 15, SharedKillChecks.PLAYER_RUNNING_CHECK.and(SharedKillChecks.HAS_SWORD))).addRequirement(this.objective01);

  
  public SwordsmanTrial01Quest() {
    super("swordsman_trial_01", "Trial: Shi Shishi Sonson");
    addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
    
    this.onCompleteEvent = this::giveReward;
  }

  
  public boolean giveReward(PlayerEntity player) {
    if (!removeQuestItem(player, Items.BONE, 30)) {
      return false;
    }
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    
    props.addUnlockedAbility((Ability)ShiShishiSonsonAbility.INSTANCE);
    
    return true;
  }
}


