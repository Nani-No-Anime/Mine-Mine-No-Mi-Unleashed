package xyz.pixelatedw.mineminenomi.abilities.toriphoenix;

import java.util.UUID;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.PhoenixAssaultZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class PhoenixAssaultPointAbility extends ZoanAbility {
  public static final PhoenixAssaultPointAbility INSTANCE = new PhoenixAssaultPointAbility();
  
  private static final AbilityAttributeModifier REGEN_RATE_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("eb43ce22-3c5a-45a0-810c-03c0f552efe7"), (Ability)INSTANCE, "Phoenix Assault Health Regeneration Speed Modifier", 0.6000000238418579D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier STRENGTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("eb43ce22-3c5a-45a0-810c-03c0f552efe7"), (Ability)INSTANCE, "Phoenix Assault Strength Modifier", 3.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);

  
  public PhoenixAssaultPointAbility() {
    super("Phoenix Assault Point", AbilityHelper.getDevilFruitCategory());
    setDescription("Transforms the user into a half-phoenix hybrid, which focuses on speed and healing");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
    
    needsClientSide();
    addZoanModifier(ModAttributes.REGEN_RATE, (AttributeModifier)REGEN_RATE_MODIFIER);
    addZoanModifier(ModAttributes.PUNCH_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER);
  }


  
  protected boolean onStartContinuityEvent(PlayerEntity player) {
    if (!super.onStartContinuityEvent(player)) {
      return false;
    }
    if (player.isCreative() || player.isSpectator()) {
      return super.onStartContinuityEvent(player);
    }
    player.abilities.allowFlying = true;
    ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayerAbilitiesPacket(player.abilities));
    
    return true;
  }

  
  public void duringContinuityEvent(PlayerEntity player, int activeTime) {
    super.duringContinuityEvent(player, activeTime);
    
    if (player.getAttribute(ModAttributes.JUMP_HEIGHT).getValue() < 0.0D || AbilityHelper.isNearbyKairoseki(player)) {
      
      player.abilities.allowFlying = false;
      player.abilities.isFlying = false;
    } else {
      player.abilities.allowFlying = true;
      boolean canFly = DevilFruitHelper.isFlyingAtMaxHeight(player, 128.0D);
      if (player.abilities.isFlying) {

        
        float speedMultiplier = 1.0F - ((player.getActivePotionEffect(ModEffects.FATIGUE_EFFECT) != null) ? (Math.min(player.getActivePotionEffect(ModEffects.FATIGUE_EFFECT).getAmplifier(), 3) * 0.05F) : 0.0F);
        player.setMotion(player.getMotion().mul(speedMultiplier, (speedMultiplier - 0.25F), speedMultiplier));
        DevilFruitHelper.vanillaFlightThreshold((LivingEntity)player, canFly ? 256 : ((int)player.getPosY() - 1));
        player.fallDistance = 0.0F;
      } 
    } 
    if (!player.world.isRemote) {
      ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayerAbilitiesPacket(player.abilities));
    }
  }

  
  protected boolean onEndContinuityEvent(PlayerEntity player) {
    if (player.isCreative() || player.isSpectator()) {
      return super.onEndContinuityEvent(player);
    }
    player.abilities.allowFlying = false;
    player.abilities.isFlying = false;
    ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayerAbilitiesPacket(player.abilities));
    
    return super.onEndContinuityEvent(player);
  }


  
  public ZoanInfo getTransformation() {
    return (ZoanInfo)PhoenixAssaultZoanInfo.INSTANCE;
  }
}


