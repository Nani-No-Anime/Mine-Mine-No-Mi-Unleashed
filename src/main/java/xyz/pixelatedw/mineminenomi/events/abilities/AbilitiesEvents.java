package xyz.pixelatedw.mineminenomi.events.abilities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.api.abilities.PotionPassiveAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.*;
import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.abilities.*;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;

import java.util.Arrays;
import java.util.Objects;






@EventBusSubscriber(modid = "mineminenomi")
public class AbilitiesEvents
{
  @SubscribeEvent
  public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
    if (event.getEntityLiving() instanceof PlayerEntity) {
      
      PlayerEntity player = (PlayerEntity)event.getEntityLiving();
      IAbilityData ablProps = AbilityDataCapability.get((LivingEntity)player);




















      
      player.world.getProfiler().startSection("abilityCooldown");
      for (Ability ability : ablProps.getUnlockedAbilities(APIConfig.AbilityCategory.ALL)) {
        
        if (ability == null) {
          continue;
        }
        
        try {
          if (ability instanceof PassiveAbility) {
            ((PassiveAbility)ablProps.getUnlockedAbility(ability)).tick(player);
          }
          if (ability instanceof xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility) {
            ablProps.getUnlockedAbility(ability).cooldown(player);
          }
        } catch (Exception e) {
          
          e.printStackTrace();
          ability.startCooldown(player);
        } 
      } 
      player.world.getProfiler().endSection();
      
      player.world.getProfiler().startSection("abilityTick");
      for (Ability ability : ablProps.getEquippedAbilities(APIConfig.AbilityCategory.ALL)) {
        
        if (ability != null)
          
          try {

            
            if (ability instanceof ChargeableAbility && ability.isCharging()) {
              ((ChargeableAbility)ablProps.getEquippedAbility(ability)).charging(player);
            }
            if (ability instanceof ContinuousAbility && ability.isContinuous()) {
              ((ContinuousAbility)ablProps.getEquippedAbility(ability)).tick(player);
            }
            if (ability.isDisabled()) {
              ablProps.getEquippedAbility(ability).disableTick(player);
            }
            if (ability.isOnCooldown()) {
              ablProps.getEquippedAbility(ability).cooldown(player);
            }
          } catch (Exception e) {
            
            e.printStackTrace();
            ability.startCooldown(player);
          }  
      } 
      player.world.getProfiler().endSection();
    } 
  }

  
  @SubscribeEvent
  public static void onPlayerDies(LivingDeathEvent event) {
    if (event.getEntityLiving() instanceof PlayerEntity) {
      
      PlayerEntity player = (PlayerEntity)event.getEntityLiving();
      IAbilityData ablProps = AbilityDataCapability.get((LivingEntity)player);
      
      for (Ability ability : ablProps.getUnlockedAbilities(APIConfig.AbilityCategory.ALL)) {
        
        if (ability == null) {
          continue;
        }
        if (ability instanceof PassiveAbility && ability instanceof IDeathAbility) {
          
          boolean flag = ((IDeathAbility)ability).onUserDeath((LivingEntity)player, event.getSource());
          event.setCanceled(!flag);
        } 
      } 
      
      for (Ability ability : ablProps.getEquippedAbilities(APIConfig.AbilityCategory.ALL)) {
        
        if (ability != null) {
          
          try {

            
            if (ability.getState() == Ability.State.CONTINUOUS) {
              
              if (ability instanceof ContinuousAbility) {
                
                ((ContinuousAbility)ability).endContinuity(player);
                if (ability instanceof IDeathAbility) {
                  
                  boolean flag = ((IDeathAbility)ability).onUserDeath((LivingEntity)player, event.getSource());
                  event.setCanceled(!flag);
                } 
              } 
              
              if (ability instanceof RepeaterAbility) {
                ((RepeaterAbility)ability).setRepeaterCount(((RepeaterAbility)ability).getMaxRepeaterCount());
              }
            } else if (ability instanceof ChargeableAbility && ability.getState() == Ability.State.CHARGING) {
              
              ((ChargeableAbility)ability).setChargeTime(((ChargeableAbility)ability).getMaxChargeTime() / 20);
              ability.startCooldown(player);
            } else {
              
              ability.startCooldown(player);
            } 
          } catch (Exception e) {
            
            e.printStackTrace();
            ability.startCooldown(player);
          } 
        }
      } 
    } 
  }
  
  @SubscribeEvent
  public static void onEntityAttackEvent(LivingHurtEvent event) {
    if (event.getEntityLiving() != null && !(event.getEntityLiving()).world.isRemote) {
      
      LivingEntity entity = event.getEntityLiving();
      Entity attacker = event.getSource().getImmediateSource();
      IAbilityData ablProps = AbilityDataCapability.get(entity);
      
      for (Ability ability : ablProps.getUnlockedAbilities(APIConfig.AbilityCategory.ALL)) {
        
        if (ability == null) {
          continue;
        }
        
        try {
          if (ability instanceof HurtPassiveAbility)
          {
            HurtPassiveAbility hurtAbility = (HurtPassiveAbility)ablProps.getUnlockedAbility(ability);
            boolean result = hurtAbility.hurt(entity, event.getSource().getTrueSource(), event.getAmount());
            event.setAmount(hurtAbility.getAmount());
            event.setCanceled(!result);
          }
        
        } catch (Exception e) {
          
          e.printStackTrace();
        } 
      } 
      
      if (attacker instanceof PlayerEntity) {
        
        PlayerEntity player = (PlayerEntity)attacker;
        
        for (Ability ability : AbilityDataCapability.get((LivingEntity)player).getEquippedAbilities(APIConfig.AbilityCategory.ALL)) {
          
          if (ability != null)
          {
            
            if (ability.getCategory() != AbilityHelper.getDevilFruitCategory() || !AbilityHelper.isAffectedByWater(entity))
            {
              
              if (ability instanceof PunchAbility && (event.getSource()).damageType.equalsIgnoreCase("ability") && ability.isContinuous()) {
                
                Ability source = ((AbilityDamageSource)event.getSource()).getAbilitySource();
                if (source != null)
                {
                  ((PunchAbility)ability).hitEffect(player, entity); } 
              } 
            }
          }
        } 
      } 
    } 
  }
  
  @SubscribeEvent
  public static void onEntityAttackEvent(LivingAttackEvent event) {
    if (event.getEntityLiving() != null && !(event.getEntityLiving()).world.isRemote) {
      
      LivingEntity entity = event.getEntityLiving();
      IAbilityData ablProps = AbilityDataCapability.get(entity);
      
      if (!CommonConfig.INSTANCE.isAbilityInvulnerabilityEnabled()) {
        return;
      }
      
      if (event.getSource() == ModDamageSource.DEVILS_CURSE) {
        return;
      }
      for (Ability ability : ablProps.getUnlockedAbilities(APIConfig.AbilityCategory.ALL)) {
        
        if (ability == null) {
          continue;
        }
        if (ability.getCategory() == AbilityHelper.getDevilFruitCategory() && AbilityHelper.isAffectedByWater(entity)) {
          continue;
        }
        
        try {
          if (ability instanceof IOnDamageAbility && IOnDamageAbility.IS_ACTIVE.test(ability)) {
            
            boolean result = ((IOnDamageAbility)ability).onDamage(entity, event.getSource(), event.getAmount());
            event.setCanceled(!result);
          } 
          if (ability instanceof DamagedPassiveAbility)
          {
            boolean result = ((DamagedPassiveAbility)ablProps.getUnlockedAbility(ability)).damage(entity, event.getSource());
            event.setCanceled(!result);
          }
        
        } catch (Exception e) {
          
          e.printStackTrace();
        } 
      } 
      
      for (Ability ability : ablProps.getEquippedAbilities()) {
        
        if (ability != null) {
          
          try {

            
            if (ability instanceof IOnDamageAbility && IOnDamageAbility.IS_ACTIVE.test(ability)) {
              
              boolean result = ((IOnDamageAbility)ability).onDamage(entity, event.getSource(), event.getAmount());
              event.setCanceled(!result);
            } 
            
            if (ability instanceof DamagedContinuousAbility && ability.isContinuous())
            {
              if (event.getSource() instanceof ModDamageSource && !((ModDamageSource)event.getSource()).isInternalDamage()) {
                
                boolean result = ((DamagedContinuousAbility)ablProps.getUnlockedAbility(ability)).damage(entity, event.getSource(), event.getAmount());
                event.setCanceled(!result);
              } 
            }
            
            if (ability instanceof IFallDamageBlockingAbility && event.getSource() == DamageSource.FALL) {
              
              boolean blockFallDamage = !((IFallDamageBlockingAbility)ability).hasFallDamage();
              if (blockFallDamage)
              {
                entity.fallDistance = 0.0F;
                ((IFallDamageBlockingAbility)ability).resetFallDamage(entity);
                event.setCanceled(true);
              }
            
            } 
          } catch (Exception e) {
            
            e.printStackTrace();
          } 
        }
      } 
      if (event.getSource().getImmediateSource() instanceof PlayerEntity && (event.getSource().getDamageType().equals("player") || event.getSource().getDamageType().equals("mob")) && event.getAmount() > 0.0F) {
        
        PlayerEntity attacker = (PlayerEntity)event.getSource().getImmediateSource();
        ablProps = AbilityDataCapability.get((LivingEntity)attacker);
        
        Arrays.<Ability>stream(ablProps.getEquippedAbilities(APIConfig.AbilityCategory.ALL)).filter(Objects::nonNull).forEach(ability -> {
              try {
                if (ability instanceof IChangeDamageSourceAbility) {
                  IChangeDamageSourceAbility abl = (IChangeDamageSourceAbility)ability;
                  
                  if (abl.isSourceChangeEnabled()) {
                    boolean sameGroup = FactionHelper.getSameGroupPredicate((LivingEntity)attacker).test(entity);
                    
                    if (sameGroup) {
                      return;
                    }
                    
                    double strength = attacker.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
                    
                    if (strength == 0.0D) {
                      return;
                    }
                    
                    float damage = (float)(abl.damageToEntityWithSource(attacker, entity) * event.getAmount() / strength);
                    
                    DamageSource source = abl.getSourceToUse(attacker);
                    
                    boolean damaged = true;
                    
                    if (entity.hurtResistantTime == 0 || abl.cancelsOriginalDamage()) {
                      damaged = entity.attackEntityFrom(source, damage);
                      entity.hurtTime = entity.hurtResistantTime = 0;
                    } 
                    if (!damaged || abl.cancelsOriginalDamage()) {
                      event.setCanceled(true);
                    }
                  } 
                } 
              } catch (Exception e) {
                e.printStackTrace();
              } 
            });
      } 
    } 
  }



  
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public static void onAttackedByPlayer(AttackEntityEvent event) {
    if (!(event.getPlayer()).world.isRemote && event.getTarget() instanceof LivingEntity) {
      
      PlayerEntity player = event.getPlayer();
      ItemStack heldItem = player.getHeldItemMainhand();
      
      if (!heldItem.isEmpty()) {
        return;
      }
      IEntityStats statProps = EntityStatsCapability.get((LivingEntity)player);
      IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
      LivingEntity target = (LivingEntity)event.getTarget();
      
      boolean isTargetLogia = DevilFruitCapability.get(target).isLogia();
      boolean isHardeningActive = HakiHelper.hasHardeningActive((LivingEntity)player);
      boolean isTargetSpirit = OutOfBodyAbilitiesEvents.isSpirit(target);
      
      if (isTargetSpirit) {
        return;
      }
      if (isTargetLogia && !isHardeningActive) {
        return;
      }
      for (Ability ability : props.getEquippedAbilities(APIConfig.AbilityCategory.ALL)) {
        
        if (ability != null) {
          
          try {

            
            if (ability instanceof PunchAbility && ability.isContinuous())
            {

              
              float damage = ((PunchAbility)ability).hitEntity(player, target);
              
              if (damage <= 0.0F) {
                
                event.setCanceled(true);
                
                return;
              } 
              float strength = (float)player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
              float finalDamage = (damage + strength) * (float)EntityStatsCapability.get((LivingEntity)player).getDamageMultiplier();
              WyDebug.debug("Hardening Haki Punch Damage: " + finalDamage);
              target.attackEntityFrom(((PunchAbility)ability).getPunchDamageSource(player), finalDamage);
            }
          
          } catch (Exception e) {
            
            e.printStackTrace();
            ability.startCooldown(player);
          } 
        }
      } 
    } 
  }
  
  @SubscribeEvent
  public static void onPlayarLogsOut(PlayerEvent.PlayerLoggedOutEvent event) {
    if ((event.getPlayer()).world.isRemote) {
      return;
    }
    PlayerEntity player = event.getPlayer();
    
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    
    for (Ability ability : props.getEquippedAbilities(APIConfig.AbilityCategory.ALL)) {
      
      if (ability != null) {
        
        try {

          
          if (ability instanceof ChargeableAbility && ability.isCharging()) {
            ((ChargeableAbility)ability).stopCharging(player);
          }
          if (ability instanceof ContinuousAbility && ability.isContinuous()) {
            ((ContinuousAbility)ability).stopContinuity(player);
          }
        } catch (Exception e) {
          
          e.printStackTrace();
          ability.startCooldown(player);
        } 
      }
    } 
  }
  
  @SubscribeEvent
  public static void onPotionEvent(PotionEvent.PotionApplicableEvent event) {
    if (!(event.getEntityLiving() instanceof PlayerEntity)) {
      return;
    }
    PlayerEntity player = (PlayerEntity)event.getEntityLiving();
    
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    
    for (Ability ability : props.getUnlockedAbilities(APIConfig.AbilityCategory.ALL)) {
      
      if (ability == null) {
        continue;
      }
      
      try {
        if (ability instanceof PotionPassiveAbility)
        {
          boolean applied = ((PotionPassiveAbility)props.getUnlockedAbility(ability)).check(player, event.getPotionEffect());
          if (applied) {
            event.setResult(Event.Result.ALLOW); continue;
          } 
          event.setResult(Event.Result.DENY);
        }
      
      } catch (Exception e) {
        
        e.printStackTrace();
      } 
    } 
  }

  
  @SubscribeEvent
  public static void onEntityShootProjectile(ArrowLooseEvent event) {
    if (event.getPlayer() != null) {
      
      IAbilityData props = AbilityDataCapability.get((LivingEntity)event.getPlayer());
      
      for (Ability abl : props.getEquippedAbilities()) {
        
        if (abl instanceof ISniperAbility && props.hasEquippedAbility(abl) && props.getEquippedAbility(abl).isContinuous()) {
          
          ((ISniperAbility)props.getEquippedAbility(abl)).shoot(event.getPlayer());
          props.getEquippedAbility(abl).use(event.getPlayer());
          event.setCanceled(true);
        } 
      } 
    } 
  }
}


