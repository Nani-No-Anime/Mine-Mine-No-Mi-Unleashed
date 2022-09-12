package xyz.pixelatedw.mineminenomi.quests.objectives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class SharedKillChecks {
  public static final KillEntityObjective.ICheckKill HAS_SWORD;
  public static final KillEntityObjective.ICheckKill HAS_BOW;
  
  static {
    HAS_SWORD = ((player, target, source) -> {
        ItemStack heldItem = player.getHeldItemMainhand();
        
        return ItemsHelper.isSword(heldItem);
      });
    
    HAS_BOW = ((player, target, source) -> {
        ItemStack heldItem = player.getHeldItemMainhand();
        
        return ItemsHelper.isBow(heldItem);
      });
    
    HAS_EMPTY_HAND = ((player, target, source) -> {
        ItemStack heldItem = player.getHeldItemMainhand();
        
        return heldItem.isEmpty();
      });
    
    IS_KICKING = ((player, target, source) -> {
        ItemStack heldItem = player.getHeldItemMainhand();
        
        return (heldItem.isEmpty() && EntityStatsCapability.get((LivingEntity)player).isBlackLeg());
      });
    
    HAS_CANNON_BALL = ((player, target, source) -> {
        ItemStack heldItem = player.getHeldItemMainhand();
        return (heldItem.getItem() == ModItems.CANNON_BALL);
      });
  }
  public static final KillEntityObjective.ICheckKill HAS_EMPTY_HAND; public static final KillEntityObjective.ICheckKill IS_KICKING; public static final KillEntityObjective.ICheckKill HAS_CANNON_BALL;
  public static final KillEntityObjective.ICheckKill HAS_BRALWER_HAND_CHECK = HAS_EMPTY_HAND.or(HAS_CANNON_BALL); public static final KillEntityObjective.ICheckKill AIRBORNE_ENEMY_CHECK; public static final KillEntityObjective.ICheckKill PLAYER_RUNNING_CHECK; public static final KillEntityObjective.ICheckKill CRITICAL_KILL_CHECK; public static final KillEntityObjective.ICheckKill ON_FIRE_ENEMY_CHECK; public static final KillEntityObjective.ICheckKill ON_FIRE_PLAYER_CHECK;
  static {
    AIRBORNE_ENEMY_CHECK = ((player, target, source) -> 
      
      (!target.onGround && !target.isInWater()));

    
    PLAYER_RUNNING_CHECK = ((player, target, source) -> player.isSprinting());



    
    CRITICAL_KILL_CHECK = ((player, target, source) -> 
      
      (player.fallDistance > 0.0F && !player.onGround && !player.isOnLadder() && !player.isInWater() && !player.isPassenger()));


    
    ON_FIRE_ENEMY_CHECK = ((player, target, source) -> (target.getFireTimer() > 0));



    
    ON_FIRE_PLAYER_CHECK = ((player, target, source) -> (player.getFireTimer() > 0));
  }



  
  public static final KillEntityObjective.ICheckKill checkAbilitySource(Ability ability) {
    return (player, target, source) -> 
      
      (source instanceof AbilityDamageSource && ((AbilityDamageSource)source).getAbilitySource().equals(ability));
  }
}


