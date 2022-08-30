/*    */ package xyz.pixelatedw.mineminenomi.abilities.toriphoenix;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.PhoenixFlyZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class PhoenixFlyPointAbility extends ZoanAbility {
/* 16 */   public static final PhoenixFlyPointAbility INSTANCE = new PhoenixFlyPointAbility();
/*    */   
/* 18 */   private static final AbilityAttributeModifier REGEN_RATE_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("eb43ce22-3c5a-45a0-810c-03c0f552efe7"), (Ability)INSTANCE, "Phoenix Fly Point Health Regeneration Speed Modifier", 1.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 19 */   private static final AbilityAttributeModifier FALL_DAMAGE_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("eb43ce22-3c5a-45a0-810c-03c0f552efe7"), (Ability)INSTANCE, "Phoenix Fly Point Fall Damage Modifier", 500.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 20 */   public float speed = 0.0F;
/*    */ 
/*    */   
/*    */   public PhoenixFlyPointAbility() {
/* 24 */     super("Phoenix Fly Point", AbilityHelper.getDevilFruitCategory());
/* 25 */     setDescription("Transforms the user into a phoenix, which focuses on speed and healing");
/*    */     
/* 27 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 28 */     this.duringContinuityEvent = this::duringContinuityEvent;
/* 29 */     addZoanModifier(ModAttributes.REGEN_RATE, (AttributeModifier)REGEN_RATE_MODIFIER);
/*    */     
/* 31 */     addZoanModifier(ModAttributes.FALL_RESISTANCE, (AttributeModifier)FALL_DAMAGE_MODIFIER);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void duringContinuityEvent(PlayerEntity player, int activeTime) {
/* 37 */     super.duringContinuityEvent(player, activeTime);
/* 38 */     player.fallDistance = 0.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo getTransformation() {
/* 44 */     return (ZoanInfo)PhoenixFlyZoanInfo.INSTANCE;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\toriphoenix\PhoenixFlyPointAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */