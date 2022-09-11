package xyz.pixelatedw.mineminenomi.quests.objectives;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class SharedHitChecks {
  public static final HitEntityObjective.ICheckHit HAS_SWORD;
  
  static {
    HAS_SWORD = ((player, target, source, amount) -> {
        ItemStack heldItem = player.getHeldItemMainhand();
        
        return ItemsHelper.isSword(heldItem);
      });
    
    HAS_BOW = ((player, target, source, amount) -> {
        ItemStack heldItem = player.getHeldItemMainhand();
        
        return ItemsHelper.isBow(heldItem);
      });
    
    IS_KICKING = ((player, target, source, amount) -> {
        ItemStack heldItem = player.getHeldItemMainhand();
        
        return (heldItem.isEmpty() && EntityStatsCapability.get((LivingEntity)player).isBlackLeg());
      });
    
    SWEEP_ATTACK_CHECK = ((player, target, source, amount) -> source.getDamageType().equalsIgnoreCase("sweep_damage"));
  }
  public static final HitEntityObjective.ICheckHit HAS_BOW;
  public static final HitEntityObjective.ICheckHit IS_KICKING;
  public static final HitEntityObjective.ICheckHit SWEEP_ATTACK_CHECK;
  
  public static final HitEntityObjective.ICheckHit checkAbilitySource(Ability ability) {
    return (player, target, source, amount) -> 
      
      (source instanceof AbilityDamageSource && ((AbilityDamageSource)source).getAbilitySource().equals(ability));
  }
}


