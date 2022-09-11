package xyz.pixelatedw.mineminenomi.api.helpers;

import java.util.UUID;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class FightingStyleHelper
{
  private static final AttributeModifier BLACK_LEG_ATTACK_SPEED_MODIFIER = (new AttributeModifier(UUID.fromString("aa257b39-effd-43b3-9034-615b21e49038"), "Black Leg Speed Multiplier", 0.30000001192092896D, AttributeModifier.Operation.ADDITION)).setSaved(true);
  private static final AttributeModifier BLACK_LEG_ATTACK_RANGE_MODIFIER = (new AttributeModifier(UUID.fromString("cd7d0526-005b-4ef2-a61f-0e941b0d6e1a"), "Black Leg Range Multiplier", 0.5D, AttributeModifier.Operation.ADDITION)).setSaved(true);






  
  public static boolean hasBlackLegModifiers(PlayerEntity player) {
    boolean flag1 = player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).hasModifier(getBlackLegStrengthModifier(player));
    boolean flag2 = player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).hasModifier(BLACK_LEG_ATTACK_SPEED_MODIFIER);
    boolean flag3 = player.getAttribute(ModAttributes.ATTACK_RANGE).hasModifier(BLACK_LEG_ATTACK_RANGE_MODIFIER);
    
    return (flag1 || flag2 || flag3);
  }

  
  public static void removeBlackLegModifiers(PlayerEntity player) {
    player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier(getBlackLegStrengthModifier(player));
    player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).removeModifier(BLACK_LEG_ATTACK_SPEED_MODIFIER);
    player.getAttribute(ModAttributes.ATTACK_RANGE).removeModifier(BLACK_LEG_ATTACK_RANGE_MODIFIER);
  }

  
  public static void applyBlackLegModifiers(PlayerEntity player) {
    removeBlackLegModifiers(player);
    
    player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier(getBlackLegStrengthModifier(player));
    player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).applyModifier(BLACK_LEG_ATTACK_SPEED_MODIFIER);
    player.getAttribute(ModAttributes.ATTACK_RANGE).applyModifier(BLACK_LEG_ATTACK_RANGE_MODIFIER);
  }






  
  public static boolean hasBrawlerModifiers(PlayerEntity player) {
    return player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).hasModifier(getBrawlerStrength(player));
  }

  
  public static void removeBrawlerModifiers(PlayerEntity player) {
    player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier(getBrawlerStrength(player));
  }

  
  public static void applyBrawlerModifiers(PlayerEntity player) {
    removeBrawlerModifiers(player);
    player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier(getBrawlerStrength(player));
  }

  
  private static AttributeModifier getBrawlerStrength(PlayerEntity player) {
    return (new AttributeModifier(UUID.fromString("4e4d55e7-774b-4010-8722-a15f9da99807"), "Brawler Attack Multiplier", (1.0F + 
        EntityStatsCapability.get((LivingEntity)player).getDoriki() * 5.0E-4F), AttributeModifier.Operation.ADDITION)).setSaved(true);
  }

  
  private static AttributeModifier getBlackLegStrengthModifier(PlayerEntity player) {
    return (new AttributeModifier(UUID.fromString("be5937cc-c1da-4891-9583-cebbab2134d3"), "Black Leg Attack Multiplier", (2.0F + 
        EntityStatsCapability.get((LivingEntity)player).getDoriki() * 4.0E-4F), AttributeModifier.Operation.ADDITION)).setSaved(true);
  }
}


