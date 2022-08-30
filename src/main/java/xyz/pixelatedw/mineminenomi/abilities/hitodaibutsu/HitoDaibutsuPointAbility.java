/*    */ package xyz.pixelatedw.mineminenomi.abilities.hitodaibutsu;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IBodyOverlayAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.HitoDaibutsuZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class HitoDaibutsuPointAbility extends ZoanAbility implements IBodyOverlayAbility {
/* 23 */   public static final HitoDaibutsuPointAbility INSTANCE = new HitoDaibutsuPointAbility();
/*    */   
/* 25 */   private static final AbilityAttributeModifier SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("d171ef28-e77a-418d-927b-ca9a09417189"), (Ability)INSTANCE, "Daibutsu Point Speed Modifier", -0.20000000298023224D, AttributeModifier.Operation.MULTIPLY_BASE)).setSaved(false);
/* 26 */   private static final AbilityAttributeModifier JUMP_BOOST = (new AbilityAttributeModifier(UUID.fromString("13b3d607-ed90-4459-832c-01e616a5ef16"), (Ability)INSTANCE, "Daibutsu Point Jump Modifier", 5.0D, AttributeModifier.Operation.MULTIPLY_BASE)).setSaved(false);
/* 27 */   private static final AbilityAttributeModifier STRENGTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("4b03a4b4-1eb5-464a-8312-0f9079044462"), (Ability)INSTANCE, "Daibutsu Point Strength Modifier", 15.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 28 */   private static final AbilityAttributeModifier ATTACK_SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("1d78a133-8a0e-4b8f-8790-1360007d4741"), (Ability)INSTANCE, "Daibutsu Point Attack Speed Modifier", -0.5D, AttributeModifier.Operation.MULTIPLY_BASE)).setSaved(false);
/* 29 */   private static final AbilityAttributeModifier ARMOR_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Daibutsu Point Armor Modifier", 20.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 30 */   private static final AbilityAttributeModifier ARMOR_THOUGNESS_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Daibutsu Point Armor Thougness Modifier", 10.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 31 */   private static final AbilityAttributeModifier KNOCKBACK_RESISTANCE_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("a20e0f50-74e5-4ae9-83ad-7da9c288050c"), (Ability)INSTANCE, "Daibutsu Point Knockback Resistance Modifier", 5.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 32 */   private static final AbilityAttributeModifier REACH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Daibutsu Point Reach Modifier", 6.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 33 */   private static final AbilityAttributeModifier JUMP_RESISTANCE = (new AbilityAttributeModifier(UUID.fromString("12ee5c43-a900-4545-883c-709d84ef1f9c"), (Ability)INSTANCE, "Daibutsu Point Jump Resitance Modifier", 30.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 34 */   private static final AbilityAttributeModifier DAMAGE_REDUCTION = (new AbilityAttributeModifier(UUID.fromString("8a048300-6f4b-11eb-9439-0242ac130002"), (Ability)INSTANCE, "Daibutsu Point Damage Reduction Modifier", 0.2D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 35 */   private static final AbilityAttributeModifier HEALTH_BOOST = (new AbilityAttributeModifier(UUID.fromString("4b03a4b4-1eb5-464a-8312-0f9079044462"), (Ability)INSTANCE, "Daibutsu Point Health Modifier", 20.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 36 */   private static final AbilityAttributeModifier STEP_HEIGHT = (new AbilityAttributeModifier(UUID.fromString("1d68a133-8a0e-4b8f-8790-1360007d4741"), (Ability)INSTANCE, "Daibutsu Point Modifier", 1.5D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 37 */   private static final AbilityAttributeModifier FALL_RESISTANCE_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("8521a343-3d02-4d64-bbd3-a7e272fd8b74"), (Ability)INSTANCE, "Daibutsu Point Fall Resistance Modifier", 7.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*    */   
/* 39 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setTexture(ModResources.BUDDHA_COATING).setColor(WyHelper.hexToRGB("#FFFFFFAA"));
/*    */ 
/*    */   
/*    */   public HitoDaibutsuPointAbility() {
/* 43 */     super("Daibutsu Point", AbilityHelper.getDevilFruitCategory());
/* 44 */     setDescription("Allows the user to transforms into a Golden Buddha statue, which focuses on strength and defense");
/* 45 */     addZoanModifier(SharedMonsterAttributes.MOVEMENT_SPEED, (AttributeModifier)SPEED_MODIFIER);
/* 46 */     addZoanModifier(SharedMonsterAttributes.ATTACK_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER);
/* 47 */     addZoanModifier(SharedMonsterAttributes.ATTACK_SPEED, (AttributeModifier)ATTACK_SPEED_MODIFIER);
/* 48 */     addZoanModifier(SharedMonsterAttributes.ARMOR, (AttributeModifier)ARMOR_MODIFIER);
/* 49 */     addZoanModifier(SharedMonsterAttributes.ARMOR_TOUGHNESS, (AttributeModifier)ARMOR_THOUGNESS_MODIFIER);
/* 50 */     addZoanModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, (AttributeModifier)KNOCKBACK_RESISTANCE_MODIFIER);
/* 51 */     addZoanModifier(ModAttributes.JUMP_HEIGHT, (AttributeModifier)JUMP_BOOST);
/* 52 */     addZoanModifier(ModAttributes.FALL_RESISTANCE, (AttributeModifier)JUMP_RESISTANCE);
/* 53 */     addZoanModifier(ModAttributes.DAMAGE_REDUCTION, (AttributeModifier)DAMAGE_REDUCTION);
/* 54 */     addZoanModifier(PlayerEntity.REACH_DISTANCE, (AttributeModifier)REACH_MODIFIER);
/* 55 */     addZoanModifier(ModAttributes.ATTACK_RANGE, (AttributeModifier)REACH_MODIFIER);
/* 56 */     addZoanModifier(SharedMonsterAttributes.MAX_HEALTH, (AttributeModifier)HEALTH_BOOST);
/* 57 */     addZoanModifier(ModAttributes.STEP_HEIGHT, (AttributeModifier)STEP_HEIGHT);
/* 58 */     addZoanModifier(ModAttributes.FALL_RESISTANCE, (AttributeModifier)FALL_RESISTANCE_MODIFIER);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean onEndContinuityEvent(PlayerEntity player) {
/* 64 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/* 65 */     ImpactBlastAbility impactWaveAbility = (ImpactBlastAbility)props.getEquippedAbility(ImpactBlastAbility.INSTANCE);
/* 66 */     if (impactWaveAbility != null && impactWaveAbility.isContinuous()) {
/* 67 */       impactWaveAbility.endContinuity(player);
/*    */     }
/* 69 */     return super.onEndContinuityEvent(player);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbilityOverlay getBodyOverlay() {
/* 75 */     return OVERLAY;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo getTransformation() {
/* 81 */     return (ZoanInfo)HitoDaibutsuZoanInfo.INSTANCE;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\hitodaibutsu\HitoDaibutsuPointAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */