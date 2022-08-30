/*    */ package xyz.pixelatedw.mineminenomi.abilities.kilo;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ 
/*    */ public class KiloPress1Ability extends ContinuousAbility {
/* 14 */   public static final KiloPress1Ability INSTANCE = new KiloPress1Ability();
/*    */   
/* 16 */   private static final AbilityAttributeModifier KILO_PRESS_JUMP_HEIGHT = new AbilityAttributeModifier(UUID.fromString("692759d2-5d8d-4809-912d-86ad362f8f95"), (Ability)INSTANCE, "Kilo Press Jump Height Modifier", 25.0D, AttributeModifier.Operation.ADDITION);
/*    */ 
/*    */   
/*    */   public KiloPress1Ability() {
/* 20 */     super("1 Kilo Press", AbilityHelper.getDevilFruitCategory());
/* 21 */     setDescription("Makes the user become extremely light, their jumps become higher and they can use an Umbrella to float");
/* 22 */     setThreshold(60.0D);
/*    */     
/* 24 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 25 */     this.duringContinuityEvent = this::duringContinuityEvent;
/* 26 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 31 */     player.getAttribute(ModAttributes.JUMP_HEIGHT).applyModifier((AttributeModifier)KILO_PRESS_JUMP_HEIGHT);
/*    */     
/* 33 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuityEvent(PlayerEntity player, int time) {
/* 38 */     player.fallDistance = 0.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 43 */     player.getAttribute(ModAttributes.JUMP_HEIGHT).removeModifier((AttributeModifier)KILO_PRESS_JUMP_HEIGHT);
/*    */     
/* 45 */     int cooldown = (int)Math.round(this.continueTime / 30.0D);
/* 46 */     setMaxCooldown(cooldown);
/*    */     
/* 48 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\kilo\KiloPress1Ability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */