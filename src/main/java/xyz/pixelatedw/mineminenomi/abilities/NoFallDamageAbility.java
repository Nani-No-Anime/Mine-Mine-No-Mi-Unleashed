/*    */ package xyz.pixelatedw.mineminenomi.abilities;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;
/*    */ 
/*    */ public class NoFallDamageAbility extends PassiveAbility {
/* 15 */   public static final NoFallDamageAbility INSTANCE = new NoFallDamageAbility();
/*    */   
/* 17 */   private static final AbilityAttributeModifier FALL_RESISTANCE_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("12ee5c43-a900-4545-883c-709d84ef1f9c"), (Ability)INSTANCE, "Jump Resistance Modifier", 10000.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*    */ 
/*    */   
/*    */   public NoFallDamageAbility() {
/* 21 */     this("No Fall Damage", AbilityHelper.getDevilFruitCategory());
/* 22 */     this.duringPassiveEvent = this::duringPassiveEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   public NoFallDamageAbility(String name, APIConfig.AbilityCategory category) {
/* 27 */     super(name, category);
/* 28 */     this.duringPassiveEvent = this::duringPassiveEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void duringPassiveEvent(PlayerEntity player) {
/* 33 */     if (!player.getAttribute(ModAttributes.FALL_RESISTANCE).hasModifier((AttributeModifier)FALL_RESISTANCE_MODIFIER))
/* 34 */       player.getAttribute(ModAttributes.FALL_RESISTANCE).applyModifier((AttributeModifier)FALL_RESISTANCE_MODIFIER); 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\NoFallDamageAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */