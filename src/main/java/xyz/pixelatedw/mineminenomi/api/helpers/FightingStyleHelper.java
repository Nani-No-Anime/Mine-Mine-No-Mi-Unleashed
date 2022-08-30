/*    */ package xyz.pixelatedw.mineminenomi.api.helpers;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ 
/*    */ public class FightingStyleHelper
/*    */ {
/* 13 */   private static final AttributeModifier BLACK_LEG_ATTACK_SPEED_MODIFIER = (new AttributeModifier(UUID.fromString("aa257b39-effd-43b3-9034-615b21e49038"), "Black Leg Speed Multiplier", 0.30000001192092896D, AttributeModifier.Operation.ADDITION)).setSaved(true);
/* 14 */   private static final AttributeModifier BLACK_LEG_ATTACK_RANGE_MODIFIER = (new AttributeModifier(UUID.fromString("cd7d0526-005b-4ef2-a61f-0e941b0d6e1a"), "Black Leg Range Multiplier", 0.5D, AttributeModifier.Operation.ADDITION)).setSaved(true);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean hasBlackLegModifiers(PlayerEntity player) {
/* 23 */     boolean flag1 = player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).hasModifier(getBlackLegStrengthModifier(player));
/* 24 */     boolean flag2 = player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).hasModifier(BLACK_LEG_ATTACK_SPEED_MODIFIER);
/* 25 */     boolean flag3 = player.getAttribute(ModAttributes.ATTACK_RANGE).hasModifier(BLACK_LEG_ATTACK_RANGE_MODIFIER);
/*    */     
/* 27 */     return (flag1 || flag2 || flag3);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void removeBlackLegModifiers(PlayerEntity player) {
/* 32 */     player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier(getBlackLegStrengthModifier(player));
/* 33 */     player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).removeModifier(BLACK_LEG_ATTACK_SPEED_MODIFIER);
/* 34 */     player.getAttribute(ModAttributes.ATTACK_RANGE).removeModifier(BLACK_LEG_ATTACK_RANGE_MODIFIER);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void applyBlackLegModifiers(PlayerEntity player) {
/* 39 */     removeBlackLegModifiers(player);
/*    */     
/* 41 */     player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier(getBlackLegStrengthModifier(player));
/* 42 */     player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).applyModifier(BLACK_LEG_ATTACK_SPEED_MODIFIER);
/* 43 */     player.getAttribute(ModAttributes.ATTACK_RANGE).applyModifier(BLACK_LEG_ATTACK_RANGE_MODIFIER);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean hasBrawlerModifiers(PlayerEntity player) {
/* 53 */     return player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).hasModifier(getBrawlerStrength(player));
/*    */   }
/*    */ 
/*    */   
/*    */   public static void removeBrawlerModifiers(PlayerEntity player) {
/* 58 */     player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier(getBrawlerStrength(player));
/*    */   }
/*    */ 
/*    */   
/*    */   public static void applyBrawlerModifiers(PlayerEntity player) {
/* 63 */     removeBrawlerModifiers(player);
/* 64 */     player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier(getBrawlerStrength(player));
/*    */   }
/*    */ 
/*    */   
/*    */   private static AttributeModifier getBrawlerStrength(PlayerEntity player) {
/* 69 */     return (new AttributeModifier(UUID.fromString("4e4d55e7-774b-4010-8722-a15f9da99807"), "Brawler Attack Multiplier", (1.0F + 
/* 70 */         EntityStatsCapability.get((LivingEntity)player).getDoriki() * 5.0E-4F), AttributeModifier.Operation.ADDITION)).setSaved(true);
/*    */   }
/*    */ 
/*    */   
/*    */   private static AttributeModifier getBlackLegStrengthModifier(PlayerEntity player) {
/* 75 */     return (new AttributeModifier(UUID.fromString("be5937cc-c1da-4891-9583-cebbab2134d3"), "Black Leg Attack Multiplier", (2.0F + 
/* 76 */         EntityStatsCapability.get((LivingEntity)player).getDoriki() * 4.0E-4F), AttributeModifier.Operation.ADDITION)).setSaved(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\helpers\FightingStyleHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */