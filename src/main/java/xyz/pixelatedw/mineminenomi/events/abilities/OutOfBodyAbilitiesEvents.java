package xyz.pixelatedw.mineminenomi.events.abilities;

import java.util.Arrays;
import java.util.Optional;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.api.abilities.IOutOfBodyAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.PhysicalBodyEntity;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.events.AbilityUseEvent;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;













@EventBusSubscriber(modid = "mineminenomi")
public class OutOfBodyAbilitiesEvents
{
  @SubscribeEvent
  public static void onAbilityUse(AbilityUseEvent event) {
    PlayerEntity player = event.getPlayer();
    
    Optional<Ability> ability = getOOBAbility((LivingEntity)player);

    
    if (ability != null && ability.isPresent()) {
      
      if (event.getAbility().equals(ability.get())) {
        return;
      }
      
      if (ability.get() instanceof xyz.pixelatedw.mineminenomi.abilities.horo.YutaiRidatsuAbility)
      {
        if (event.getAbility() instanceof xyz.pixelatedw.mineminenomi.abilities.horo.MiniHollowAbility || event.getAbility() instanceof xyz.pixelatedw.mineminenomi.abilities.horo.NegativeHollowAbility || event.getAbility() instanceof xyz.pixelatedw.mineminenomi.abilities.horo.TokuHollowAbility) {
          return;
        }
      }
    } 
    if (isSpirit((LivingEntity)player)) {
      event.setCanceled(true);
    }
  }


  
  @SubscribeEvent
  public static void onItemUse(PlayerInteractEvent.RightClickItem event) {
    PlayerEntity player = event.getPlayer();
    
    if (!isSpirit((LivingEntity)player)) {
      return;
    }
    event.setCanceled(true);
  }

  
  @SubscribeEvent
  public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
    if (event.getEntityLiving() instanceof PlayerEntity) {
      
      PlayerEntity player = (PlayerEntity)event.getEntityLiving();
      
      if (player.isCreative() || player.isSpectator()) {
        return;
      }
      Optional<Ability> ability = getOOBAbility((LivingEntity)player);
      boolean isActive = (ability.isPresent() && ((Ability)ability.get()).isContinuous());
      boolean isPhysical = (ability.isPresent() && ((IOutOfBodyAbility)ability.get()).isPhysical());
      
      if (!isActive) {
        
        if (!isPhysical) {
          player.noClip = false;
        }
        return;
      } 
      if (!isPhysical)
      {
        player.onGround = false;
        player.noClip = true;
      }
    
    } else if (event.getEntityLiving() instanceof PhysicalBodyEntity) {
      
      PhysicalBodyEntity body = (PhysicalBodyEntity)event.getEntityLiving();
      
      if (AbilityHelper.isAffectedByWater((LivingEntity)body) && body.getOwner() != null) {
        
        Optional<Ability> ability = getOOBAbility((LivingEntity)body.getOwner());
        if (ability.get() instanceof ContinuousAbility) {
          ((ContinuousAbility)ability.get()).endContinuity(body.getOwner());
        }
      } 
    } 
  }
  
  @SubscribeEvent
  public static void onItemPickup(EntityItemPickupEvent event) {
    PlayerEntity player = event.getPlayer();
    
    if (!isSpirit((LivingEntity)player)) {
      return;
    }
    event.setCanceled(true);
  }

  
  @SubscribeEvent
  public static void onItemTossed(ItemTossEvent event) {
    PlayerEntity player = event.getPlayer();
    
    if (!isSpirit((LivingEntity)player)) {
      return;
    }
    event.setCanceled(true);
    player.addItemStackToInventory(event.getEntityItem().getItem());
  }

  
  @SubscribeEvent
  public static void onEntityHits(AttackEntityEvent event) {
    PlayerEntity player = event.getPlayer();
    
    if (!isSpirit((LivingEntity)player)) {
      return;
    }
    event.setCanceled(true);
  }

  
  @SubscribeEvent
  public static void onEntityLeftClickBlocks(PlayerInteractEvent.LeftClickBlock event) {
    PlayerEntity player = event.getPlayer();
    
    if (!isSpirit((LivingEntity)player)) {
      return;
    }
    event.setCanceled(true);
  }

  
  @SubscribeEvent
  public static void onEntityRightClickBlocks(PlayerInteractEvent.RightClickBlock event) {
    PlayerEntity player = event.getPlayer();
    
    if (!isSpirit((LivingEntity)player)) {
      return;
    }
    event.setCanceled(true);
  }

  
  @SubscribeEvent
  public static void onEntityBreaksBlocks(BlockEvent.BreakEvent event) {
    PlayerEntity player = event.getPlayer();
    
    if (!isSpirit((LivingEntity)player)) {
      return;
    }
    event.setCanceled(true);
  }

  
  @SubscribeEvent
  public static void onEntityPlaceBlocks(BlockEvent.EntityPlaceEvent event) {
    if (!(event.getEntity() instanceof PlayerEntity)) {
      return;
    }
    PlayerEntity player = (PlayerEntity)event.getEntity();
    
    if (!isSpirit((LivingEntity)player)) {
      return;
    }
    event.setCanceled(true);
  }

  
  @SubscribeEvent
  public static void onEntityAttackEvent(LivingAttackEvent event) {
    if (!(event.getEntity() instanceof PlayerEntity)) {
      return;
    }
    PlayerEntity player = (PlayerEntity)event.getEntity();
    
    if (isSpirit((LivingEntity)player) && event.getSource() != DamageSource.MAGIC) {
      event.setCanceled(true);
    }
  }


  
  public static Optional<Ability> getOOBAbility(LivingEntity entity) {
    IAbilityData abilityProps = AbilityDataCapability.get(entity);
    Optional<Ability> optional = Arrays.<Ability>stream(abilityProps.getEquippedAbilities(IOutOfBodyAbility.IS_ACTIVE)).findFirst();
    return optional;
  }

  
  public static boolean isSpirit(LivingEntity entity) {
    if (entity instanceof PlayerEntity && (((PlayerEntity)entity).isCreative() || entity.isSpectator())) {
      return false;
    }
    Optional<Ability> ability = getOOBAbility(entity);
    
    if (ability == null || !ability.isPresent() || ((IOutOfBodyAbility)ability.get()).isPhysical()) {
      return false;
    }
    return true;
  }
}


