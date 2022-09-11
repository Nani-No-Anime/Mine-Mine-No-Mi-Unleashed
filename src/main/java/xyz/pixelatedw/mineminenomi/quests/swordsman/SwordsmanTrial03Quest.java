package xyz.pixelatedw.mineminenomi.quests.swordsman;
import java.lang.invoke.SerializedLambda;

import java.util.function.Predicate;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.abilities.swordsman.SanbyakurokujuPoundHoAbility;
import xyz.pixelatedw.mineminenomi.abilities.swordsman.ShiShishiSonsonAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
import xyz.pixelatedw.mineminenomi.init.ModQuests;
import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class SwordsmanTrial03Quest extends Quest {
  private static final Predicate<ItemStack> ITEM_WITH_UNBREAKING;
  
  static {
    ITEM_WITH_UNBREAKING = (itemStack -> 
      
      (ItemsHelper.isSword(itemStack) && EnchantmentHelper.getEnchantmentLevel(Enchantments.UNBREAKING, itemStack) > 0));

    
    YAKKODORI_KILL_CHECK = ((player, target, source) -> source.getImmediateSource() instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.swordsman.YakkodoriProjectile);



    
    SHI_SHISHI_SONSON_KILL_CHECK = ((player, target, source) -> {
        IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
        
        ShiShishiSonsonAbility ability = (ShiShishiSonsonAbility)props.getEquippedAbility((Ability)ShiShishiSonsonAbility.INSTANCE);
        boolean hasAbility = (ability != null && ability.isOnCooldown());
        return (hasAbility && ability.canDealDamage());
      });
  }
  private static final KillEntityObjective.ICheckKill YAKKODORI_KILL_CHECK; private static final KillEntityObjective.ICheckKill SHI_SHISHI_SONSON_KILL_CHECK;
  private Objective objective01 = (Objective)new ObtainItemObjective("Obtain a sword with Unbreaking", 1, ITEM_WITH_UNBREAKING);
  private Objective objective02 = (new KillEntityObjective("Kill %s enemies using Yakkodori", 10, YAKKODORI_KILL_CHECK)).addRequirement(this.objective01);
  private Objective objective03 = (new KillEntityObjective("Kill %s enemies using Shi Shishi Sonson", 30, SHI_SHISHI_SONSON_KILL_CHECK)).addRequirement(this.objective01);

  
  public SwordsmanTrial03Quest() {
    super("swordsman_trial_03", "Trial: Sanbyakurokuju Pound Ho");
    addRequirement(ModQuests.SWORDSMAN_TRIAL_01);
    addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
    
    this.onCompleteEvent = this::giveReward;
  }

  
  public boolean giveReward(PlayerEntity player) {
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    
    props.addUnlockedAbility(SanbyakurokujuPoundHoAbility.INSTANCE);
    
    return true;
  }
}


