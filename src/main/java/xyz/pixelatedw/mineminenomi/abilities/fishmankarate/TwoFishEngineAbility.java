/*    */ package xyz.pixelatedw.mineminenomi.abilities.fishmankarate;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ 
/*    */ public class TwoFishEngineAbility extends ContinuousAbility {
/* 14 */   public static final TwoFishEngineAbility INSTANCE = new TwoFishEngineAbility();
/*    */   
/* 16 */   private static final AbilityAttributeModifier SWIN_SPEED = (new AbilityAttributeModifier(UUID.fromString("c6ad4347-b287-4bd5-b6c9-1533543fd15c"), (Ability)INSTANCE, "Fishman Speed Modifier", 1.75D, AttributeModifier.Operation.MULTIPLY_TOTAL)).setSaved(false);
/*    */ 
/*    */   
/*    */   public TwoFishEngineAbility() {
/* 20 */     super("Two Fish Engine", AbilityHelper.getRacialCategory());
/* 21 */     setThreshold(10.0D);
/* 22 */     setMaxCooldown(20.0D);
/* 23 */     setDescription("Increases the user's swimming speed");
/*    */     
/* 25 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 26 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 31 */     if (!player.getAttribute(LivingEntity.SWIM_SPEED).hasModifier((AttributeModifier)SWIN_SPEED)) {
/* 32 */       player.getAttribute(LivingEntity.SWIM_SPEED).applyModifier((AttributeModifier)SWIN_SPEED);
/*    */     }
/* 34 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 39 */     player.getAttribute(LivingEntity.SWIM_SPEED).removeModifier((AttributeModifier)SWIN_SPEED);
/* 40 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\fishmankarate\TwoFishEngineAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */