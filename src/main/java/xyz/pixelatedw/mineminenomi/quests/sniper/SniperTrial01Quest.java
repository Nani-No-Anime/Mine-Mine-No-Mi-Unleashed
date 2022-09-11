package xyz.pixelatedw.mineminenomi.quests.sniper;

import java.util.function.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.abilities.sniper.KaenBoshiAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class SniperTrial01Quest extends Quest {
  private static final Predicate<ItemStack> ITEM_IS_BOW;
  
  static {
    ITEM_IS_BOW = (itemStack -> ItemsHelper.isBow(itemStack));



    
    BOW_DISTANCE_CHECK = ((player, target, source) -> {
        ItemStack heldItem = player.getHeldItemMainhand();
        
        boolean isBow = ItemsHelper.isBow(heldItem);
        boolean isArrow = (source.getImmediateSource() instanceof net.minecraft.entity.projectile.ArrowEntity || source.getImmediateSource() instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.extra.KujaArrowProjectile || source.getImmediateSource() instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.extra.PopGreenProjectile);
        
        boolean distance = (player.getDistance((Entity)target) >= 30.0F);
        return (isBow && isArrow && distance);
      });
  }
  private static final KillEntityObjective.ICheckKill BOW_DISTANCE_CHECK; private Objective objective01 = (Objective)new ObtainItemObjective("Obtain a bow", 1, ITEM_IS_BOW);
  private Objective objective02 = (new ObtainItemObjective("Collect %s strings", 30, Items.STRING)).addRequirement(this.objective01);
  private Objective objective03 = (new KillEntityObjective("Kill %s enemies from at least 30 blocks away using your bow", 5, BOW_DISTANCE_CHECK)).addRequirement(this.objective01);

  
  public SniperTrial01Quest() {
    super("sniper_trial_01", "Trial: Kaen Boshi");
    addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
    
    this.onCompleteEvent = this::giveReward;
  }

  
  public boolean giveReward(PlayerEntity player) {
    if (!removeQuestItem(player, Items.STRING, 30)) {
      return false;
    }
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    
    props.addUnlockedAbility(KaenBoshiAbility.INSTANCE);
    
    return true;
  }
}


