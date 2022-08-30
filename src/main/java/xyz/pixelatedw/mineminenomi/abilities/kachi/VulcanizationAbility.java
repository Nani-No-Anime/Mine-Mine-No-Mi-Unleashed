/*    */ package xyz.pixelatedw.mineminenomi.abilities.kachi;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IBodyOverlayAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class VulcanizationAbility extends PunchAbility implements IBodyOverlayAbility {
/* 18 */   public static final VulcanizationAbility INSTANCE = new VulcanizationAbility();
/*    */   
/* 20 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setTexture(ModResources.HOT_BOILING_SPECIAL_ARM).setColor("#FFFFFFAA");
/* 21 */   private static final AbilityAttributeModifier ARMOR_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("b0e012c8-1479-414d-8015-9044225572a7"), (Ability)INSTANCE, "Vulcanization Modifier", 7.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*    */ 
/*    */   
/*    */   public VulcanizationAbility() {
/* 25 */     super("Vulcanization", AbilityHelper.getDevilFruitCategory());
/* 26 */     setDescription("Coats the user into a thick stone-like layer that increases the armor of its user");
/* 27 */     setMaxCooldown(15.0D);
/* 28 */     setThreshold(10.0D);
/* 29 */     setStoppingAfterHit(false);
/*    */     
/* 31 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 32 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/* 33 */     this.onHitEntityEvent = this::onHitEntityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 38 */     player.getAttribute(SharedMonsterAttributes.ARMOR).removeModifier((AttributeModifier)ARMOR_MODIFIER);
/* 39 */     player.getAttribute(SharedMonsterAttributes.ARMOR).applyModifier((AttributeModifier)ARMOR_MODIFIER);
/* 40 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 45 */     player.getAttribute(SharedMonsterAttributes.ARMOR).removeModifier((AttributeModifier)ARMOR_MODIFIER);
/* 46 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
/* 51 */     return 4.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbilityOverlay getBodyOverlay() {
/* 57 */     return OVERLAY;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\kachi\VulcanizationAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */