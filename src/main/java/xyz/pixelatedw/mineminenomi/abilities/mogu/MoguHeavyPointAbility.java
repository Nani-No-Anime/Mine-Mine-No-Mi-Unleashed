/*    */ package xyz.pixelatedw.mineminenomi.abilities.mogu;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.MoguHeavyZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class MoguHeavyPointAbility extends ZoanAbility {
/* 19 */   public static final MoguHeavyPointAbility INSTANCE = new MoguHeavyPointAbility();
/*    */   
/* 21 */   private static final AbilityAttributeModifier ARMOR_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Mogu Heavy Point Modifier", 5.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 22 */   private static final AbilityAttributeModifier STRENGTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("4b03a4b4-1eb5-464a-8312-0f9079044462"), (Ability)INSTANCE, "Mogu Heavy Point Modifier", 3.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 23 */   private static final AbilityAttributeModifier ATTACK_SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("1d78a133-8a0e-4b8f-8790-1360007d4741"), (Ability)INSTANCE, "Mogu Heavy Point Modifier", 0.15000000596046448D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 24 */   private static final AbilityAttributeModifier REACH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Mogu Heavy Reach Modifier", -0.5D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*    */ 
/*    */   
/*    */   public MoguHeavyPointAbility() {
/* 28 */     super("Mogu Heavy Point", AbilityHelper.getDevilFruitCategory());
/* 29 */     setDescription("Transforms the user into a mole, which focuses on strength and digging speed");
/* 30 */     addZoanModifier(SharedMonsterAttributes.ARMOR, (AttributeModifier)ARMOR_MODIFIER);
/* 31 */     addZoanModifier(ModAttributes.PUNCH_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER);
/* 32 */     addZoanModifier(SharedMonsterAttributes.ATTACK_SPEED, (AttributeModifier)ATTACK_SPEED_MODIFIER);
/* 33 */     addZoanModifier(PlayerEntity.REACH_DISTANCE, (AttributeModifier)REACH_MODIFIER);
/* 34 */     addZoanModifier(ModAttributes.ATTACK_RANGE, (AttributeModifier)REACH_MODIFIER);
/*    */     
/* 36 */     this.duringContinuityEvent = this::duringContinuityEvent;
/* 37 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void duringContinuityEvent(PlayerEntity player, int time) {
/* 43 */     super.duringContinuityEvent(player, time);
/* 44 */     player.addPotionEffect(new EffectInstance(Effects.HASTE, 5, 2, false, false));
/* 45 */     if (!player.isPotionActive(Effects.NIGHT_VISION) || player.getActivePotionEffect(Effects.NIGHT_VISION).getDuration() < 500) {
/* 46 */       player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 500, 0, false, false));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean onEndContinuityEvent(PlayerEntity player) {
/* 52 */     player.removePotionEffect(Effects.HASTE);
/* 53 */     player.removePotionEffect(Effects.NIGHT_VISION);
/* 54 */     return super.onEndContinuityEvent(player);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo getTransformation() {
/* 60 */     return (ZoanInfo)MoguHeavyZoanInfo.INSTANCE;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\mogu\MoguHeavyPointAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */