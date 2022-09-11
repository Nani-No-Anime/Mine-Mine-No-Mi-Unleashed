package xyz.pixelatedw.mineminenomi.abilities.ryupteranodon;

import java.util.UUID;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
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
import xyz.pixelatedw.mineminenomi.entities.zoan.PteranodonAssaultZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class PteranodonAssaultPointAbility extends ZoanAbility {
  public static final PteranodonAssaultPointAbility INSTANCE = new PteranodonAssaultPointAbility();
  
  private static final AbilityAttributeModifier STRENGTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("eb43ce22-3c5a-45a0-810c-03c0f552efe7"), (Ability)INSTANCE, "Pteranodon Assault Point Strength Modifier", 3.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("4a89d972-ad2f-4a81-a959-34da54c60fd3"), (Ability)INSTANCE, "Pteranodon Assault Point Speed Modifier", 2.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);

  
  public PteranodonAssaultPointAbility() {
    super("Pteranodon Assault Point", AbilityHelper.getDevilFruitCategory());
    setDescription("Transforms the user into a half-pteranodon hybrid");
    
    addZoanModifier(ModAttributes.PUNCH_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.ATTACK_SPEED, (AttributeModifier)SPEED_MODIFIER);
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }


  
  protected boolean onStartContinuityEvent(PlayerEntity player) {
    if (player.isCreative() || player.isSpectator()) {
      return super.onStartContinuityEvent(player);
    }
    player.abilities.allowFlying = true;
    ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayerAbilitiesPacket(player.abilities));
    
    return super.onStartContinuityEvent(player);
  }


  
  public void duringContinuityEvent(PlayerEntity player, int activeTime) {
    super.duringContinuityEvent(player, activeTime);
    
    if (player.getAttribute(ModAttributes.JUMP_HEIGHT).getValue() < 0.0D || AbilityHelper.isNearbyKairoseki(player)) {
      
      player.abilities.allowFlying = false;
      player.abilities.isFlying = false;
    }
    else {
      
      player.abilities.allowFlying = true;
      boolean canFly = DevilFruitHelper.isFlyingAtMaxHeight(player, 128.0D);
      if (player.abilities.isFlying)
        DevilFruitHelper.vanillaFlightThreshold((LivingEntity)player, canFly ? 256 : ((int)player.getPosY() - 1)); 
      player.fallDistance = 0.0F;
    } 
    ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayerAbilitiesPacket(player.abilities));
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
    return (ZoanInfo)PteranodonAssaultZoanInfo.INSTANCE;
  }
}


