/*    */ package xyz.pixelatedw.mineminenomi.abilities.ryupteranodon;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
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
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.PteranodonAssaultZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class PteranodonAssaultPointAbility extends ZoanAbility {
/* 20 */   public static final PteranodonAssaultPointAbility INSTANCE = new PteranodonAssaultPointAbility();
/*    */   
/* 22 */   private static final AbilityAttributeModifier STRENGTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("eb43ce22-3c5a-45a0-810c-03c0f552efe7"), (Ability)INSTANCE, "Pteranodon Assault Point Strength Modifier", 3.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 23 */   private static final AbilityAttributeModifier SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("4a89d972-ad2f-4a81-a959-34da54c60fd3"), (Ability)INSTANCE, "Pteranodon Assault Point Speed Modifier", 2.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*    */ 
/*    */   
/*    */   public PteranodonAssaultPointAbility() {
/* 27 */     super("Pteranodon Assault Point", AbilityHelper.getDevilFruitCategory());
/* 28 */     setDescription("Transforms the user into a half-pteranodon hybrid");
/*    */     
/* 30 */     addZoanModifier(ModAttributes.PUNCH_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER);
/* 31 */     addZoanModifier(SharedMonsterAttributes.ATTACK_SPEED, (AttributeModifier)SPEED_MODIFIER);
/*    */     
/* 33 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 34 */     this.duringContinuityEvent = this::duringContinuityEvent;
/* 35 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean onStartContinuityEvent(PlayerEntity player) {
/* 41 */     if (player.isCreative() || player.isSpectator()) {
/* 42 */       return super.onStartContinuityEvent(player);
/*    */     }
/* 44 */     player.abilities.allowFlying = true;
/* 45 */     ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayerAbilitiesPacket(player.abilities));
/*    */     
/* 47 */     return super.onStartContinuityEvent(player);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void duringContinuityEvent(PlayerEntity player, int activeTime) {
/* 53 */     super.duringContinuityEvent(player, activeTime);
/*    */     
/* 55 */     if (player.getAttribute(ModAttributes.JUMP_HEIGHT).getValue() < 0.0D || AbilityHelper.isNearbyKairoseki(player)) {
/*    */       
/* 57 */       player.abilities.allowFlying = false;
/* 58 */       player.abilities.isFlying = false;
/*    */     }
/*    */     else {
/*    */       
/* 62 */       player.abilities.allowFlying = true;
/* 63 */       boolean canFly = DevilFruitHelper.isFlyingAtMaxHeight(player, 128.0D);
/* 64 */       if (player.abilities.isFlying)
/* 65 */         DevilFruitHelper.vanillaFlightThreshold((LivingEntity)player, canFly ? 256 : ((int)player.getPosY() - 1)); 
/* 66 */       player.fallDistance = 0.0F;
/*    */     } 
/* 68 */     ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayerAbilitiesPacket(player.abilities));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean onEndContinuityEvent(PlayerEntity player) {
/* 74 */     if (player.isCreative() || player.isSpectator()) {
/* 75 */       return super.onEndContinuityEvent(player);
/*    */     }
/* 77 */     player.abilities.allowFlying = false;
/* 78 */     player.abilities.isFlying = false;
/* 79 */     ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayerAbilitiesPacket(player.abilities));
/*    */     
/* 81 */     return super.onEndContinuityEvent(player);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo getTransformation() {
/* 87 */     return (ZoanInfo)PteranodonAssaultZoanInfo.INSTANCE;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\ryupteranodon\PteranodonAssaultPointAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */