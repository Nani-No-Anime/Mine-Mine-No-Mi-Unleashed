/*    */ package xyz.pixelatedw.mineminenomi.abilities.ryupteranodon;
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
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.PteranodonFlyZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class PteranodonFlyPointAbility extends ZoanAbility {
/* 17 */   public static final PteranodonFlyPointAbility INSTANCE = new PteranodonFlyPointAbility();
/*    */   
/* 19 */   private static final AbilityAttributeModifier STRENGTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("eb43ce22-3c5a-45a0-810c-03c0f552efe7"), (Ability)INSTANCE, "Pteranodon Assault Point Strength Modifier", 2.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 20 */   private static final AbilityAttributeModifier SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("4a89d972-ad2f-4a81-a959-34da54c60fd3"), (Ability)INSTANCE, "Pteranodon Assault Point Speed Modifier", 1.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*    */   
/* 22 */   public float speed = 0.0F;
/*    */ 
/*    */   
/*    */   public PteranodonFlyPointAbility() {
/* 26 */     super("Pteranodon Fly Point", AbilityHelper.getDevilFruitCategory());
/* 27 */     setDescription("Transforms the user into an ancient pteranodon");
/*    */     
/* 29 */     addZoanModifier(ModAttributes.PUNCH_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER);
/* 30 */     addZoanModifier(SharedMonsterAttributes.ATTACK_SPEED, (AttributeModifier)SPEED_MODIFIER);
/*    */     
/* 32 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 33 */     this.duringContinuityEvent = this::duringContinuityEvent;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void duringContinuityEvent(PlayerEntity player, int activeTime) {
/* 39 */     super.duringContinuityEvent(player, activeTime);
/* 40 */     player.fallDistance = 0.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo getTransformation() {
/* 46 */     return (ZoanInfo)PteranodonFlyZoanInfo.INSTANCE;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\ryupteranodon\PteranodonFlyPointAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */