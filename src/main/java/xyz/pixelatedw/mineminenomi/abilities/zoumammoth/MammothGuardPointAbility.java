/*    */ package xyz.pixelatedw.mineminenomi.abilities.zoumammoth;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.MammothGuardZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class MammothGuardPointAbility
/*    */   extends ZoanAbility {
/* 17 */   public static final MammothGuardPointAbility INSTANCE = new MammothGuardPointAbility();
/*    */   
/* 19 */   private static final AbilityAttributeModifier SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("d141ef28-e77a-418d-927b-ca9a09417189"), (Ability)INSTANCE, "Mammoth Guard Point Modifier", -0.30000001192092896D, AttributeModifier.Operation.MULTIPLY_BASE)).setSaved(false);
/* 20 */   private static final AbilityAttributeModifier HEALTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("d141ef28-e77a-418d-927b-ca9a09417189"), (Ability)INSTANCE, "Mammoth Guard Point Modifier", 25.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 21 */   private static final AbilityAttributeModifier ARMOR_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Mammoth Guard Point Modifier", 18.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 22 */   private static final AbilityAttributeModifier STRENGTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("4b03a4b4-1eb5-464a-8312-0f9079044462"), (Ability)INSTANCE, "Mammoth Guard Point Modifier", 6.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 23 */   private static final AbilityAttributeModifier ATTACK_SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("1d78a133-8a0e-4b8f-8790-1360007d4741"), (Ability)INSTANCE, "Mammoth Guard Point Modifier", -0.15000000596046448D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 24 */   private static final AbilityAttributeModifier REACH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Mammoth Guard Point Reach Modifier", 1.8D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 25 */   private static final AbilityAttributeModifier KNOCKBACK_RESISTANCE = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Mammoth Guard Point Knockback Resistance Modifier", 1.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 26 */   private static final AbilityAttributeModifier STEP_HEIGHT = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Mammoth Guard Point Step Height Modifier", 1.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 27 */   private static final AbilityAttributeModifier FALL_RESISTANCE_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("8521a343-3d02-4d64-bbd3-a7e272fd8b74"), (Ability)INSTANCE, "Mammoth Guard Fall Resistance Modifier", 10.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*    */ 
/*    */   
/*    */   public MammothGuardPointAbility() {
/* 31 */     super("Mammoth Guard Point", AbilityHelper.getDevilFruitCategory());
/* 32 */     setDescription("Transforms the user into an ancient mammoth, which focuses on defense");
/* 33 */     addZoanModifier(SharedMonsterAttributes.MOVEMENT_SPEED, (AttributeModifier)SPEED_MODIFIER);
/* 34 */     addZoanModifier(SharedMonsterAttributes.ARMOR, (AttributeModifier)ARMOR_MODIFIER);
/* 35 */     addZoanModifier(SharedMonsterAttributes.MAX_HEALTH, (AttributeModifier)HEALTH_MODIFIER);
/* 36 */     addZoanModifier(SharedMonsterAttributes.ATTACK_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER);
/* 37 */     addZoanModifier(SharedMonsterAttributes.ATTACK_SPEED, (AttributeModifier)ATTACK_SPEED_MODIFIER);
/* 38 */     addZoanModifier(PlayerEntity.REACH_DISTANCE, (AttributeModifier)REACH_MODIFIER);
/* 39 */     addZoanModifier(ModAttributes.ATTACK_RANGE, (AttributeModifier)REACH_MODIFIER);
/* 40 */     addZoanModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, (AttributeModifier)KNOCKBACK_RESISTANCE);
/* 41 */     addZoanModifier(ModAttributes.STEP_HEIGHT, (AttributeModifier)STEP_HEIGHT);
/* 42 */     addZoanModifier(ModAttributes.FALL_RESISTANCE, (AttributeModifier)FALL_RESISTANCE_MODIFIER);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo getTransformation() {
/* 48 */     return (ZoanInfo)MammothGuardZoanInfo.INSTANCE;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\zoumammoth\MammothGuardPointAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */