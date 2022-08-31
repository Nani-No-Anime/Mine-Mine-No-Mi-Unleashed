/*    */ package xyz.pixelatedw.mineminenomi.abilities.kira;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IBodyOverlayAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*    */ 
/*    */ public class DiamondBodyAbility extends ContinuousAbility implements IParallelContinuousAbility, IBodyOverlayAbility {
/* 22 */   public static final DiamondBodyAbility INSTANCE = new DiamondBodyAbility();
/*    */   
/* 24 */   private static final AbilityAttributeModifier ARMOR_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Diamond Body Armor Modifier", 24.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 25 */   private static final AbilityAttributeModifier ARMOR_THOUGHNESS_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("a647e093-70f1-4369-86c4-7736718ef26c"), (Ability)INSTANCE, "Diamond Body Armor Thoughness Modifier", 12.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 26 */   private static final AbilityAttributeModifier ATTACK_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("d6ee4b3f-92dd-4b55-9bb5-3c8b1100e438"), (Ability)INSTANCE, "Diamond Body Attack Modifier", 6.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 27 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setTexture(ModResources.DIAMOND_BODY).setColor(new Color(255, 255, 255, 165));
/*    */ 
/*    */   
/*    */   public DiamondBodyAbility() {
/* 31 */     super("Diamond Body", AbilityHelper.getDevilFruitCategory());
/* 32 */     setThreshold(120.0D);
/* 33 */     setDescription("Allows the user's body to become diamond, gaining high strength and defence");
/*    */     
/* 35 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 36 */     this.duringContinuityEvent = this::duringContinuity;
/* 37 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 42 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuity(PlayerEntity player, int passiveTimer) {
/* 47 */     player.addPotionEffect(new EffectInstance(ModEffects.PHYSICAL_MOVING_GUARD, 2, 3, false, false));
/*    */     
/* 49 */     IAttributeInstance attri = player.getAttribute(SharedMonsterAttributes.ARMOR);
/* 50 */     if (!attri.hasModifier((AttributeModifier)ARMOR_MODIFIER))
/* 51 */       attri.applyModifier((AttributeModifier)ARMOR_MODIFIER); 
/* 52 */     IAttributeInstance attrib = player.getAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS);
/* 53 */     if (!attrib.hasModifier((AttributeModifier)ARMOR_THOUGHNESS_MODIFIER))
/* 54 */       attrib.applyModifier((AttributeModifier)ARMOR_THOUGHNESS_MODIFIER); 
/* 55 */     IAttributeInstance attribu = player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
/* 56 */     if (!attribu.hasModifier((AttributeModifier)ATTACK_MODIFIER)) {
/* 57 */       attribu.applyModifier((AttributeModifier)ATTACK_MODIFIER);
/*    */     }
/*    */   }
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 62 */     IAttributeInstance attri = player.getAttribute(SharedMonsterAttributes.ARMOR);
/* 63 */     if (attri.hasModifier((AttributeModifier)ARMOR_MODIFIER))
/* 64 */       attri.removeModifier((AttributeModifier)ARMOR_MODIFIER); 
/* 65 */     IAttributeInstance attrib = player.getAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS);
/* 66 */     if (attrib.hasModifier((AttributeModifier)ARMOR_THOUGHNESS_MODIFIER))
/* 67 */       attrib.removeModifier((AttributeModifier)ARMOR_THOUGHNESS_MODIFIER); 
/* 68 */     IAttributeInstance attribu = player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
/* 69 */     if (attribu.hasModifier((AttributeModifier)ATTACK_MODIFIER)) {
/* 70 */       attribu.removeModifier((AttributeModifier)ATTACK_MODIFIER);
/*    */     }
/* 72 */     int cooldown = (int)Math.round(this.continueTime / 60.0D);
/* 73 */     setMaxCooldown(cooldown);
/* 74 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbilityOverlay getBodyOverlay() {
/* 80 */     return OVERLAY;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\kira\DiamondBodyAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */