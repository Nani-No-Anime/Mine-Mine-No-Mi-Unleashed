/*    */ package xyz.pixelatedw.mineminenomi.abilities.ushigiraffe;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.GiraffeWalkZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class GiraffeWalkPointAbility extends ZoanAbility {
/* 17 */   public static final GiraffeWalkPointAbility INSTANCE = new GiraffeWalkPointAbility();
/*    */   
/* 19 */   private static final AbilityAttributeModifier SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("d171ef28-e77a-418d-927b-ca9a09417189"), (Ability)INSTANCE, "Giraffe Walk Point Speed Modifier", 0.800000011920929D, AttributeModifier.Operation.MULTIPLY_BASE)).setSaved(false);
/* 20 */   private static final AbilityAttributeModifier JUMP_BOOST = (new AbilityAttributeModifier(UUID.fromString("13b3d607-ed90-4459-832c-01e616a5ef16"), (Ability)INSTANCE, "Giraffe Walk Point Jump Modifier", 2.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 21 */   private static final AbilityAttributeModifier STRENGTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("4b03a4b4-1eb5-464a-8312-0f9079044462"), (Ability)INSTANCE, "Giraffe Walk Point Modifier", 1.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 22 */   private static final AbilityAttributeModifier STEP_HEIGHT = (new AbilityAttributeModifier(UUID.fromString("1d68a133-8a0e-4b8f-8790-1360007d4741"), (Ability)INSTANCE, "Giraffe Walk Point Modifier", 1.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 23 */   private static final AbilityAttributeModifier ATTACK_SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("1d78a133-8a0e-4b8f-8790-1360007d4741"), (Ability)INSTANCE, "Giraffe Walk Point Modifier", 0.15000000596046448D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 24 */   private static final AbilityAttributeModifier REACH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Giraffe Walk Point Reach Modifier", 1.1D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 25 */   private static final AbilityAttributeModifier FALL_RESISTANCE_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("8521a343-3d02-4d64-bbd3-a7e272fd8b74"), (Ability)INSTANCE, "Giraffe Walk Point Fall Resistance Modifier", 5.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 26 */   private static final AbilityAttributeModifier ARMOR_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Giraffe Walk Point Modifier", 3.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*    */ 
/*    */   
/*    */   public GiraffeWalkPointAbility() {
/* 30 */     super("Giraffe Walk Point", AbilityHelper.getDevilFruitCategory());
/* 31 */     setDescription("Transforms the user into a giraffe, which focuses on speed and high jumps");
/* 32 */     addZoanModifier(SharedMonsterAttributes.MOVEMENT_SPEED, (AttributeModifier)SPEED_MODIFIER);
/* 33 */     addZoanModifier(ModAttributes.PUNCH_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER);
/* 34 */     addZoanModifier(SharedMonsterAttributes.ATTACK_SPEED, (AttributeModifier)ATTACK_SPEED_MODIFIER);
/* 35 */     addZoanModifier(ModAttributes.JUMP_HEIGHT, (AttributeModifier)JUMP_BOOST);
/* 36 */     addZoanModifier(ModAttributes.STEP_HEIGHT, (AttributeModifier)STEP_HEIGHT);
/* 37 */     addZoanModifier(ModAttributes.ATTACK_RANGE, (AttributeModifier)REACH_MODIFIER);
/* 38 */     addZoanModifier(ModAttributes.FALL_RESISTANCE, (AttributeModifier)FALL_RESISTANCE_MODIFIER);
/* 39 */     addZoanModifier(SharedMonsterAttributes.ARMOR, (AttributeModifier)ARMOR_MODIFIER);
/*    */     
/* 41 */     this.duringContinuityEvent = this::duringContinuityEvent;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void duringContinuityEvent(PlayerEntity player, int activeTime) {
/* 47 */     super.duringContinuityEvent(player, activeTime);
/* 48 */     player.fallDistance = (float)(player.fallDistance - 0.05D);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo getTransformation() {
/* 54 */     return (ZoanInfo)GiraffeWalkZoanInfo.INSTANCE;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilitie\\ushigiraffe\GiraffeWalkPointAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */