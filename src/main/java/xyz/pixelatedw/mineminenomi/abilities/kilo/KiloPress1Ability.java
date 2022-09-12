package xyz.pixelatedw.mineminenomi.abilities.kilo;


import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;

import java.util.UUID;

public class KiloPress1Ability extends ContinuousAbility {
  public static final KiloPress1Ability INSTANCE = new KiloPress1Ability();
  
  private static final AbilityAttributeModifier KILO_PRESS_JUMP_HEIGHT = new AbilityAttributeModifier(UUID.fromString("692759d2-5d8d-4809-912d-86ad362f8f95"), (Ability)INSTANCE, "Kilo Press Jump Height Modifier", 25.0D, AttributeModifier.Operation.ADDITION);

  
  public KiloPress1Ability() {
    super("1 Kilo Press", AbilityHelper.getDevilFruitCategory());
    setDescription("Makes the user become extremely light, their jumps become higher and they can use an Umbrella to float");
    setThreshold(60.0D);
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    player.getAttribute(ModAttributes.JUMP_HEIGHT).applyModifier((AttributeModifier)KILO_PRESS_JUMP_HEIGHT);
    
    return true;
  }

  
  private void duringContinuityEvent(PlayerEntity player, int time) {
    player.fallDistance = 0.0F;
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    player.getAttribute(ModAttributes.JUMP_HEIGHT).removeModifier((AttributeModifier)KILO_PRESS_JUMP_HEIGHT);
    
    int cooldown = (int)Math.round(this.continueTime / 30.0D);
    setMaxCooldown(cooldown);
    
    return true;
  }
}


