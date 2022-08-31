/*    */ package xyz.pixelatedw.mineminenomi.abilities.saraaxolotl;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.AxolotlHeavyZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class AxolotlHeavyPointAbility
/*    */   extends ZoanAbility {
/* 17 */   public static final AxolotlHeavyPointAbility INSTANCE = new AxolotlHeavyPointAbility();
/*    */   
/* 19 */   private static final AbilityAttributeModifier SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("d141ef28-e77a-418d-927b-ca9a09417189"), (Ability)INSTANCE, "Axolotl Heavy Point Modifier", 0.3D, AttributeModifier.Operation.MULTIPLY_BASE)).setSaved(false);
/* 20 */   private static final AbilityAttributeModifier ARMOR_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Axolotl Heavy Point Modifier", 20.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 21 */   private static final AbilityAttributeModifier STRENGTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("4b03a4b4-1eb5-464a-8312-0f9079044462"), (Ability)INSTANCE, "Axolotl Heavy Point Modifier", 5.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 22 */   private static final AbilityAttributeModifier ATTACK_SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("1d78a133-8a0e-4b8f-8790-1360007d4741"), (Ability)INSTANCE, "Axolotl Heavy Point Modifier", -0.6000000238418579D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 23 */   private static final AbilityAttributeModifier JUMP_BOOST = (new AbilityAttributeModifier(UUID.fromString("72ee5c43-a900-4545-883c-709d84ef1f9c"), (Ability)INSTANCE, "Axolotl Heavy Point Modifier", 1.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 24 */   private static final AbilityAttributeModifier REACH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Axolotl Heavy Point Reach Modifier", 0.6D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 25 */   private static final AbilityAttributeModifier REGEN_RATE_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("eb43ce22-3c5a-45a0-810c-03c0f552efe7"), (Ability)INSTANCE, "Axolotl Heavy Point Health Regeneration Modifier", 0.25D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 26 */   private static final AbilityAttributeModifier KNOCKBACK_RESISTANCE = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Axolotl Heavy Point Knockback Resistance Modifier", 0.5D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*    */ 
/*    */   
/*    */   public AxolotlHeavyPointAbility() {
/* 30 */     super("Axolotl Heavy Point", AbilityHelper.getDevilFruitCategory());
/* 31 */     setDescription("Transforms the user into a half-axolotl hybrid, which focuses on strength.");
/* 32 */     addZoanModifier(SharedMonsterAttributes.MOVEMENT_SPEED, (AttributeModifier)SPEED_MODIFIER);
/* 33 */     addZoanModifier(SharedMonsterAttributes.ARMOR, (AttributeModifier)ARMOR_MODIFIER);
/* 34 */     addZoanModifier(SharedMonsterAttributes.ATTACK_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER);
/* 35 */     addZoanModifier(SharedMonsterAttributes.ATTACK_SPEED, (AttributeModifier)ATTACK_SPEED_MODIFIER);
/* 36 */     addZoanModifier(ModAttributes.JUMP_HEIGHT, (AttributeModifier)JUMP_BOOST);
/* 37 */     addZoanModifier(PlayerEntity.REACH_DISTANCE, (AttributeModifier)REACH_MODIFIER);
/* 38 */     addZoanModifier(ModAttributes.ATTACK_RANGE, (AttributeModifier)REACH_MODIFIER);
/* 39 */     addZoanModifier(ModAttributes.REGEN_RATE, (AttributeModifier)REGEN_RATE_MODIFIER);
/* 40 */     addZoanModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, (AttributeModifier)KNOCKBACK_RESISTANCE);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo getTransformation() {
/* 46 */     return (ZoanInfo)AxolotlHeavyZoanInfo.INSTANCE;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\saraaxolotl\AxolotlHeavyPointAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */