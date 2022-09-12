package xyz.pixelatedw.mineminenomi.abilities.toriphoenix;


import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.PhoenixFlyZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

import java.util.UUID;

public class PhoenixFlyPointAbility extends ZoanAbility {
  public static final PhoenixFlyPointAbility INSTANCE = new PhoenixFlyPointAbility();
  
  private static final AbilityAttributeModifier REGEN_RATE_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("eb43ce22-3c5a-45a0-810c-03c0f552efe7"), (Ability)INSTANCE, "Phoenix Fly Point Health Regeneration Speed Modifier", 1.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier FALL_DAMAGE_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("eb43ce22-3c5a-45a0-810c-03c0f552efe7"), (Ability)INSTANCE, "Phoenix Fly Point Fall Damage Modifier", 500.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  public float speed = 0.0F;

  
  public PhoenixFlyPointAbility() {
    super("Phoenix Fly Point", AbilityHelper.getDevilFruitCategory());
    setDescription("Transforms the user into a phoenix, which focuses on speed and healing");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuityEvent;
    addZoanModifier(ModAttributes.REGEN_RATE, (AttributeModifier)REGEN_RATE_MODIFIER);
    
    addZoanModifier(ModAttributes.FALL_RESISTANCE, (AttributeModifier)FALL_DAMAGE_MODIFIER);
  }


  
  public void duringContinuityEvent(PlayerEntity player, int activeTime) {
    super.duringContinuityEvent(player, activeTime);
    player.fallDistance = 0.0F;
  }


  
  public ZoanInfo getTransformation() {
    return (ZoanInfo)PhoenixFlyZoanInfo.INSTANCE;
  }
}


