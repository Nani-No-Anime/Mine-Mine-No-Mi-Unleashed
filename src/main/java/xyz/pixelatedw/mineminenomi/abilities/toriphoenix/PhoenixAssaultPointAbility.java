/*    */ package xyz.pixelatedw.mineminenomi.abilities.toriphoenix;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.PhoenixAssaultZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class PhoenixAssaultPointAbility extends ZoanAbility {
/* 20 */   public static final PhoenixAssaultPointAbility INSTANCE = new PhoenixAssaultPointAbility();
/*    */   
/* 22 */   private static final AbilityAttributeModifier REGEN_RATE_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("eb43ce22-3c5a-45a0-810c-03c0f552efe7"), (Ability)INSTANCE, "Phoenix Assault Health Regeneration Speed Modifier", 0.6000000238418579D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 23 */   private static final AbilityAttributeModifier STRENGTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("eb43ce22-3c5a-45a0-810c-03c0f552efe7"), (Ability)INSTANCE, "Phoenix Assault Strength Modifier", 3.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*    */ 
/*    */   
/*    */   public PhoenixAssaultPointAbility() {
/* 27 */     super("Phoenix Assault Point", AbilityHelper.getDevilFruitCategory());
/* 28 */     setDescription("Transforms the user into a half-phoenix hybrid, which focuses on speed and healing");
/*    */     
/* 30 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 31 */     this.duringContinuityEvent = this::duringContinuityEvent;
/* 32 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */     
/* 34 */     needsClientSide();
/* 35 */     addZoanModifier(ModAttributes.REGEN_RATE, (AttributeModifier)REGEN_RATE_MODIFIER);
/* 36 */     addZoanModifier(ModAttributes.PUNCH_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean onStartContinuityEvent(PlayerEntity player) {
/* 42 */     if (!super.onStartContinuityEvent(player)) {
/* 43 */       return false;
/*    */     }
/* 45 */     if (player.isCreative() || player.isSpectator()) {
/* 46 */       return super.onStartContinuityEvent(player);
/*    */     }
/* 48 */     player.abilities.allowFlying = true;
/* 49 */     ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayerAbilitiesPacket(player.abilities));
/*    */     
/* 51 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void duringContinuityEvent(PlayerEntity player, int activeTime) {
/* 56 */     super.duringContinuityEvent(player, activeTime);
/*    */     
/* 58 */     if (player.getAttribute(ModAttributes.JUMP_HEIGHT).getValue() < 0.0D || AbilityHelper.isNearbyKairoseki(player)) {
/*    */       
/* 60 */       player.abilities.allowFlying = false;
/* 61 */       player.abilities.isFlying = false;
/*    */     } else {
/* 63 */       player.abilities.allowFlying = true;
/* 64 */       boolean canFly = DevilFruitHelper.isFlyingAtMaxHeight(player, 128.0D);
/* 65 */       if (player.abilities.isFlying) {
/*    */ 
/*    */         
/* 68 */         float speedMultiplier = 1.0F - ((player.getActivePotionEffect(ModEffects.FATIGUE_EFFECT) != null) ? (Math.min(player.getActivePotionEffect(ModEffects.FATIGUE_EFFECT).getAmplifier(), 3) * 0.05F) : 0.0F);
/* 69 */         player.setMotion(player.getMotion().mul(speedMultiplier, (speedMultiplier - 0.25F), speedMultiplier));
/* 70 */         DevilFruitHelper.vanillaFlightThreshold((LivingEntity)player, canFly ? 256 : ((int)player.getPosY() - 1));
/* 71 */         player.fallDistance = 0.0F;
/*    */       } 
/*    */     } 
/* 74 */     if (!player.world.isRemote) {
/* 75 */       ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayerAbilitiesPacket(player.abilities));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean onEndContinuityEvent(PlayerEntity player) {
/* 81 */     if (player.isCreative() || player.isSpectator()) {
/* 82 */       return super.onEndContinuityEvent(player);
/*    */     }
/* 84 */     player.abilities.allowFlying = false;
/* 85 */     player.abilities.isFlying = false;
/* 86 */     ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayerAbilitiesPacket(player.abilities));
/*    */     
/* 88 */     return super.onEndContinuityEvent(player);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo getTransformation() {
/* 94 */     return (ZoanInfo)PhoenixAssaultZoanInfo.INSTANCE;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\toriphoenix\PhoenixAssaultPointAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */