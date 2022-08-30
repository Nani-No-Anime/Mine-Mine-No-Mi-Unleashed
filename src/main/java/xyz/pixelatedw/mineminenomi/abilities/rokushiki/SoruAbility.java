/*    */ package xyz.pixelatedw.mineminenomi.abilities.rokushiki;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ public class SoruAbility extends ContinuousAbility {
/* 23 */   public static final Ability INSTANCE = (Ability)new SoruAbility();
/*    */   
/* 25 */   private static final AbilityAttributeModifier STEP_HEIGHT_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("1d68a133-8a0e-4b8f-8790-1360007d4741"), INSTANCE, "Soru Step Height Modifier", 1.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*    */ 
/*    */   
/*    */   public SoruAbility() {
/* 29 */     super("Soru", AbilityHelper.getRacialCategory());
/* 30 */     setMaxCooldown(10.0D);
/* 31 */     setThreshold(2.0D);
/* 32 */     setDescription("Allows the user to move at an extremely high speed for a short period");
/*    */     
/* 34 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 35 */     this.duringContinuityEvent = this::duringContinuity;
/* 36 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 41 */     if (!AbilityHelper.canUseMomentumAbility(player)) {
/* 42 */       return false;
/*    */     }
/* 44 */     if (!player.onGround) {
/*    */       
/* 46 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONLY_IN_GROUND, new Object[] { getName() }));
/* 47 */       return false;
/*    */     } 
/*    */     
/* 50 */     player.getAttribute(ModAttributes.STEP_HEIGHT).removeModifier((AttributeModifier)STEP_HEIGHT_MODIFIER);
/* 51 */     player.getAttribute(ModAttributes.STEP_HEIGHT).applyModifier((AttributeModifier)STEP_HEIGHT_MODIFIER);
/* 52 */     player.world.playSound(null, player.getPosition(), ModSounds.TELEPORT_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*    */     
/* 54 */     int maxUse = 10 + (int)Math.min(Math.round(EntityStatsCapability.get((LivingEntity)player).getDoriki() * 0.003D / 10.0D) * 10L, 30L);
/* 55 */     setThresholdInTicks(maxUse);
/* 56 */     WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, (Ability)this), (LivingEntity)player);
/* 57 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuity(PlayerEntity player, int passiveTimer) {
/* 62 */     if (player.collidedHorizontally || player.isInWater() || !AbilityHelper.canUseMomentumAbility(player)) {
/* 63 */       endContinuity(player);
/*    */     }
/* 65 */     int doriki = EntityStatsCapability.get((LivingEntity)player).getDoriki();
/* 66 */     float maxSpeed = 1.25F + (int)Math.min(Math.round(doriki * 7.5E-5D), 0.75D);
/*    */     
/* 68 */     Vec3d vec = player.getLookVec();
/* 69 */     if (player.onGround) {
/*    */       
/* 71 */       player.setMotion(vec.x * maxSpeed, (player.getMotion()).y, vec.z * maxSpeed);
/*    */     } else {
/*    */       
/* 74 */       player.setMotion((doriki > 6000) ? (vec.x * maxSpeed * 0.75D) : (player.getMotion()).x, 
/* 75 */           (player.getMotion()).y, 
/* 76 */           (doriki > 6000) ? (vec.z * maxSpeed * 0.5D) : (player.getMotion()).z);
/*    */     } 
/*    */     
/* 79 */     player.velocityChanged = true;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 84 */     int cooldown = 2 + (int)Math.round(this.continueTime / 5.0D);
/* 85 */     setMaxCooldown(cooldown);
/* 86 */     player.getAttribute(ModAttributes.STEP_HEIGHT).removeModifier((AttributeModifier)STEP_HEIGHT_MODIFIER);
/* 87 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\rokushiki\SoruAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */