package xyz.pixelatedw.mineminenomi.api.helpers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Difficulty;
import xyz.pixelatedw.mineminenomi.abilities.haki.*;
import xyz.pixelatedw.mineminenomi.api.abilities.IHakiAbility;
import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.events.HakiGainEvents;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncHakiDataPacket;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

import javax.annotation.Nullable;
import java.util.Random;



public class HakiHelper
{
  enum HakiRank
  {
    BEGINNER,
    INITIATE,
    ADEPT,
    PROFICIENT,
    MASTER;
  }

  
  public static boolean isHaoshokuBorn(PlayerEntity player) {
    boolean isKing = false;
    
    String[] bits = ("" + player.getUniqueID().getMostSignificantBits()).split("");
    int playerBitsSum = 0;
    for (String bit : bits) {
      
      if (!bit.equalsIgnoreCase("-"))
      {
        playerBitsSum += Integer.parseInt(bit); } 
    } 
    playerBitsSum = MathHelper.clamp(playerBitsSum & 0xA, 0, 10);
    
    if (CommonConfig.INSTANCE.getHaoshokuUnlockLogic() == CommonConfig.HaoshokuUnlockLogic.TRUE_RANDOM) {
      
      String[] seedBits = String.valueOf(player.world.getSeed()).split("");
      int worldBitsSum = 0;
      for (String bit : seedBits) {
        
        if (!bit.equalsIgnoreCase("-"))
        {
          worldBitsSum += Integer.parseInt(bit); } 
      } 
      worldBitsSum = MathHelper.clamp(worldBitsSum & 0xA, 0, 10);
      isKing = (playerBitsSum == worldBitsSum);
    }
    else {
      
      isKing = (playerBitsSum <= 1);
    } 
    
    return isKing;
  }

  
  public static boolean canItemGainImbuing(@Nullable ItemStack itemStack) {
    if (itemStack == null || itemStack.isEmpty()) {
      return false;
    }
    if (ItemsHelper.isSword(itemStack)) {
      return true;
    }
    boolean hasDamageAttribute = (itemStack.getAttributeModifiers(EquipmentSlotType.MAINHAND).get(SharedMonsterAttributes.ATTACK_DAMAGE.getName()).size() > 0);
    
    return hasDamageAttribute;
  }

  
  public static double getBusoshokuHardeningExpNeeded(PlayerEntity player) {
    Random rand = player.getRNG();
    rand.setSeed(player.getUniqueID().getMostSignificantBits());
    return 30.0D + WyHelper.randomWithRange(rand, -2, 25);
  }

  
  public static double getBusoshokuFullBodyExpNeeded(PlayerEntity player) {
    Random rand = player.getRNG();
    rand.setSeed(player.getUniqueID().getMostSignificantBits());
    return 50.0D + WyHelper.randomWithRange(rand, 0, 20);
  }

  
  public static double getBusoshokuImbuingExpNeeded(PlayerEntity player) {
    Random rand = player.getRNG();
    rand.setSeed(player.getUniqueID().getMostSignificantBits());
    return 30.0D + WyHelper.randomWithRange(rand, -5, 20);
  }

  
  public static double getKenbunshokuAuraExpNeeded(PlayerEntity player) {
    Random rand = player.getRNG();
    rand.setSeed(player.getUniqueID().getMostSignificantBits());
    return 30.0D + WyHelper.randomWithRange(rand, -5, 20);
  }

  
  public static double getKenbunshokuFutureSightExpNeeded(PlayerEntity player) {
    Random rand = player.getRNG();
    rand.setSeed(player.getUniqueID().getMostSignificantBits());
    return 50.0D + WyHelper.randomWithRange(rand, 0, 30);
  }

  
  public static String getHakiRank(HakiType type, PlayerEntity player) {
    IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)player);
    IHakiData hakiData = HakiDataCapability.get((LivingEntity)player);
    HakiRank rank = HakiRank.values()[0];
    
    float exp = 0.0F;
    boolean check = false;
    
    if (type == HakiType.HARDENING) {
      
      exp = hakiData.getBusoshokuHardeningHakiExp();
      check = abilityData.hasUnlockedAbility((Ability)BusoshokuHakiHardeningAbility.INSTANCE);
    }
    else if (type == HakiType.IMBUING) {
      
      exp = hakiData.getBusoshokuImbuingHakiExp();
      check = abilityData.hasUnlockedAbility((Ability)BusoshokuHakiImbuingAbility.INSTANCE);
    }
    else if (type == HakiType.KENBUNSHOKU) {
      
      exp = hakiData.getKenbunshokuHakiExp();
      check = abilityData.hasUnlockedAbility((Ability)KenbunshokuHakiAuraAbility.INSTANCE);
    } 
    
    if (exp >= 0.0F && exp < 15.0F) {
      rank = HakiRank.BEGINNER;
    } else if (exp >= 15.0F && check) {
      
      if (exp >= 15.0F && exp < 30.0F) {
        rank = HakiRank.INITIATE;
      } else if (exp >= 30.0F && exp < 50.0F) {
        rank = HakiRank.ADEPT;
      } else if (exp >= 50.0F && exp < 90.0F) {
        rank = HakiRank.PROFICIENT;
      } else if (exp >= 90.0F) {
        rank = HakiRank.MASTER;
      } 
    } 
    return (new TranslationTextComponent(ModI18n.TRAINER_HAKI_RANK, new Object[] { rank })).getFormattedText();
  }

  
  @Deprecated
  public static String getHakiProgress(HakiType type, PlayerEntity player) {
    IHakiData hakiData = HakiDataCapability.get((LivingEntity)player);
    IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)player);
    
    if (type == HakiType.HARDENING) {
      
      float f = hakiData.getBusoshokuHardeningHakiExp();
      boolean hasHardening = abilityData.hasUnlockedAbility((Ability)BusoshokuHakiHardeningAbility.INSTANCE);
      boolean hasFullbody = abilityData.hasUnlockedAbility((Ability)BusoshokuHakiFullBodyHardeningAbility.INSTANCE);
      
      if (!hasHardening) {
        
        String translatedAbility = (new TranslationTextComponent(BusoshokuHakiHardeningAbility.INSTANCE.getI18nKey(), new Object[0])).getFormattedText();
        int avg = (int)(40.0F - f);
        if (avg > 0) {
          return (new TranslationTextComponent(ModI18n.TRAINER_KEEP_TRAINING, new Object[] { Integer.valueOf(avg), translatedAbility })).getFormattedText();
        }
        return (new TranslationTextComponent(ModI18n.TRAINER_UNUSUAL_TRAINING, new Object[] { translatedAbility })).getFormattedText();
      } 
      if (!hasFullbody) {
        
        String translatedAbility = (new TranslationTextComponent(BusoshokuHakiFullBodyHardeningAbility.INSTANCE.getI18nKey(), new Object[0])).getFormattedText();
        int avg = (int)(60.0F - f);
        if (avg > 0) {
          return (new TranslationTextComponent(ModI18n.TRAINER_KEEP_TRAINING, new Object[] { Integer.valueOf(avg), translatedAbility })).getFormattedText();
        }
        return (new TranslationTextComponent(ModI18n.TRAINER_UNUSUAL_TRAINING, new Object[] { translatedAbility })).getFormattedText();
      } 
      
      return (new TranslationTextComponent(ModI18n.TRAINER_FINISHED_HARDENING_TRAINING, new Object[0])).getFormattedText();
    } 
    if (type == HakiType.IMBUING) {
      
      float f = hakiData.getBusoshokuImbuingHakiExp();
      boolean hasImbuing = abilityData.hasUnlockedAbility((Ability)BusoshokuHakiImbuingAbility.INSTANCE);
      
      if (!hasImbuing) {
        
        String translatedAbility = (new TranslationTextComponent(BusoshokuHakiImbuingAbility.INSTANCE.getI18nKey(), new Object[0])).getFormattedText();
        int avg = (int)(40.0F - f);
        if (avg > 0) {
          return (new TranslationTextComponent(ModI18n.TRAINER_KEEP_TRAINING, new Object[] { Integer.valueOf(avg), translatedAbility })).getFormattedText();
        }
        return (new TranslationTextComponent(ModI18n.TRAINER_UNUSUAL_TRAINING, new Object[] { translatedAbility })).getFormattedText();
      } 
      
      return (new TranslationTextComponent(ModI18n.TRAINER_FINISHED_IMBUING_TRAINING, new Object[0])).getFormattedText();
    } 

    
    float exp = hakiData.getKenbunshokuHakiExp();
    boolean hasAura = abilityData.hasUnlockedAbility((Ability)KenbunshokuHakiAuraAbility.INSTANCE);
    boolean hasFutureSight = abilityData.hasUnlockedAbility((Ability)KenbunshokuHakiFutureSightAbility.INSTANCE);
    
    if (!hasAura) {
      
      String translatedAbility = (new TranslationTextComponent(KenbunshokuHakiAuraAbility.INSTANCE.getI18nKey(), new Object[0])).getFormattedText();
      int avg = (int)(40.0F - exp);
      if (avg > 0) {
        return (new TranslationTextComponent(ModI18n.TRAINER_KEEP_TRAINING, new Object[] { Integer.valueOf(avg), translatedAbility })).getFormattedText();
      }
      return (new TranslationTextComponent(ModI18n.TRAINER_UNUSUAL_TRAINING, new Object[] { translatedAbility })).getFormattedText();
    } 
    if (!hasFutureSight) {
      
      String translatedAbility = (new TranslationTextComponent(KenbunshokuHakiFutureSightAbility.INSTANCE.getI18nKey(), new Object[0])).getFormattedText();
      int avg = (int)(75.0F - exp);
      if (avg > 0) {
        return (new TranslationTextComponent(ModI18n.TRAINER_KEEP_TRAINING, new Object[] { Integer.valueOf(avg), translatedAbility })).getFormattedText();
      }
      return (new TranslationTextComponent(ModI18n.TRAINER_UNUSUAL_TRAINING, new Object[] { translatedAbility })).getFormattedText();
    } 
    
    return (new TranslationTextComponent(ModI18n.TRAINER_FINISHED_OBSERVATION_TRAINING, new Object[0])).getFormattedText();
  }


  
  public static void checkForUnlocks(PlayerEntity player) {
    IHakiData props = HakiDataCapability.get((LivingEntity)player);
    IEntityStats statsProps = EntityStatsCapability.get((LivingEntity)player);
    
    if (statsProps.getDoriki() > 4000 && props.getBusoshokuHardeningHakiExp() > getBusoshokuHardeningExpNeeded(player)) {
      
      HakiGainEvents.giveHakiAbility(player, (Ability)BusoshokuHakiHardeningAbility.INSTANCE);
      
      if (statsProps.getDoriki() > 5000 && props.getBusoshokuHardeningHakiExp() > getBusoshokuFullBodyExpNeeded(player)) {
        HakiGainEvents.giveHakiAbility(player, (Ability)BusoshokuHakiFullBodyHardeningAbility.INSTANCE);
      }
    } 
    if (statsProps.getDoriki() > 4500 && props.getBusoshokuImbuingHakiExp() > getBusoshokuImbuingExpNeeded(player)) {
      HakiGainEvents.giveHakiAbility(player, (Ability)BusoshokuHakiImbuingAbility.INSTANCE);
    }
    if (statsProps.getDoriki() > 1500 && props.getKenbunshokuHakiExp() > getKenbunshokuAuraExpNeeded(player)) {
      
      HakiGainEvents.giveHakiAbility(player, (Ability)KenbunshokuHakiAuraAbility.INSTANCE);
      if (statsProps.getDoriki() > 6000 && props.getKenbunshokuHakiExp() > getKenbunshokuFutureSightExpNeeded(player)) {
        HakiGainEvents.giveHakiAbility(player, (Ability)KenbunshokuHakiFutureSightAbility.INSTANCE);
      }
    } 
    WyNetwork.sendToAllTrackingAndSelf(new SSyncHakiDataPacket(player.getEntityId(), props), (LivingEntity)player);
  }

  
  public static boolean canUseHaki(PlayerEntity player, Ability ability) {
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    
    for (Ability abl : props.getEquippedAbilities(AbilityHelper.getHakiCategory())) {
      
      if (abl != null && abl instanceof ContinuousAbility) {
        
        ContinuousAbility hakiAbility = (ContinuousAbility)abl;
        
        if (hakiAbility.isContinuous()) {

          
          if (getHakiType((Ability)hakiAbility) == HakiType.HARDENING && getHakiType(ability) == HakiType.HARDENING) {
            return false;
          }
          if (getHakiType((Ability)hakiAbility) == HakiType.IMBUING && getHakiType(ability) == HakiType.IMBUING) {
            return false;
          }
          if (getHakiType((Ability)hakiAbility) == HakiType.KENBUNSHOKU && getHakiType(ability) == HakiType.KENBUNSHOKU)
            return false; 
        } 
      } 
    } 
    return true;
  }

  
  public static boolean hasAnyHakiEnabled(LivingEntity entity) {
    IAbilityData attackerAbilityProps = AbilityDataCapability.get(entity);
    Ability busoHaki = attackerAbilityProps.getEquippedAbility((Ability)BusoshokuHakiHardeningAbility.INSTANCE);
    boolean hasBusoHakiActive = (busoHaki != null && busoHaki.isContinuous());
    
    Ability fullBodyBusoHaki = attackerAbilityProps.getEquippedAbility((Ability)BusoshokuHakiFullBodyHardeningAbility.INSTANCE);
    boolean hasFullBodyBusoHakiActive = (fullBodyBusoHaki != null && fullBodyBusoHaki.isContinuous());
    
    Ability imbuingHaki = attackerAbilityProps.getEquippedAbility((Ability)BusoshokuHakiImbuingAbility.INSTANCE);
    ItemStack heldItem = entity.getHeldItemMainhand();
    boolean hasImbuingBusoHakiActive = (!heldItem.isEmpty() && imbuingHaki != null && imbuingHaki.isContinuous());
    
    boolean hasNPCBusoActive = (entity instanceof OPEntity && ((OPEntity)entity).hasBusoHaki());
    
    return (hasBusoHakiActive || hasFullBodyBusoHakiActive || hasNPCBusoActive || hasImbuingBusoHakiActive);
  }

  
  public static boolean hasHardeningActive(LivingEntity entity) {
    if (entity instanceof PlayerEntity) {
      
      IAbilityData props = AbilityDataCapability.get(entity);
      
      Ability busoHaki = props.getEquippedAbility((Ability)BusoshokuHakiHardeningAbility.INSTANCE);
      boolean hasBusoHakiActive = (busoHaki != null && busoHaki.isContinuous());
      
      Ability fullBodyBusoHaki = props.getEquippedAbility((Ability)BusoshokuHakiFullBodyHardeningAbility.INSTANCE);
      boolean hasFullBodyBusoHakiActive = (fullBodyBusoHaki != null && fullBodyBusoHaki.isContinuous());
      
      return (hasBusoHakiActive || hasFullBodyBusoHakiActive);
    } 
    if (entity instanceof OPEntity) {
      
      boolean hasNPCBusoActive = ((OPEntity)entity).hasBusoHaki();
      
      return hasNPCBusoActive;
    } 

    
    IAttributeInstance attrAtk = entity.getAttributes().getAttributeInstance(SharedMonsterAttributes.ATTACK_DAMAGE);
    double atk = (attrAtk != null) ? attrAtk.getBaseValue() : 0.0D;
    
    boolean hasDoriki = (EntityStatsCapability.get(entity).getDoriki() >= 5000);
    boolean hasPseudoHaki = (atk >= ((entity.world.getDifficulty() == Difficulty.HARD) ? 5 : 7));
    
    if (hasPseudoHaki || hasDoriki) {
      return true;
    }
    
    return false;
  }

  
  public static boolean hasImbuingActive(LivingEntity entity) {
    if (entity instanceof PlayerEntity) {
      
      IAbilityData attackerAbilityProps = AbilityDataCapability.get(entity);
      BusoshokuHakiImbuingAbility imbuingHaki = (BusoshokuHakiImbuingAbility)attackerAbilityProps.getEquippedAbility((Ability)BusoshokuHakiImbuingAbility.INSTANCE);
      boolean hasImbuingBusoHakiActive = (imbuingHaki != null && imbuingHaki.isContinuous());
      
      return hasImbuingBusoHakiActive;
    } 
    if (entity instanceof OPEntity) {
      
      boolean hasNPCBusoActive = ((OPEntity)entity).hasBusoHaki();
      
      return hasNPCBusoActive;
    } 
    
    return false;
  }

  
  public static HakiType getHakiType(Ability ability) {
    if (ability instanceof IHakiAbility) {
      return ((IHakiAbility)ability).getType();
    }
    return HakiType.HARDENING;
  }


  
  @Deprecated
  public static float getTotalHakiExp(LivingEntity player) {
    return HakiDataCapability.get(player).getTotalHakiExp();
  }


  
  @Deprecated
  public static float getBusoHardeningHakiExp(LivingEntity player) {
    return HakiDataCapability.get(player).getBusoshokuHardeningHakiExp();
  }

  
  public static boolean checkForHakiOveruse(PlayerEntity player, int overuseBonus) {
    if (player.world.isRemote) {
      return false;
    }
    IHakiData hakiProps = HakiDataCapability.get((LivingEntity)player);
    
    if (overuseBonus != -1) {
      hakiProps.alterHakiOveruse(1 + overuseBonus);
    }
    return !canEnableHaki(player);
  }

  
  public static boolean canEnableHaki(PlayerEntity player) {
    return (HakiDataCapability.get((LivingEntity)player).getHakiOveruse() < getMaxOveruse(player));
  }

  
  public static float getMaxOveruse(PlayerEntity player) {
    return 2200.0F + getTotalHakiExp((LivingEntity)player) * 32.0F;
  }
}


