package xyz.pixelatedw.mineminenomi.abilities.fishmankarate;


import java.util.UUID;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;

public class TwoFishEngineAbility extends ContinuousAbility {
  public static final TwoFishEngineAbility INSTANCE = new TwoFishEngineAbility();
  
  private static final AbilityAttributeModifier SWIN_SPEED = (new AbilityAttributeModifier(UUID.fromString("c6ad4347-b287-4bd5-b6c9-1533543fd15c"), (Ability)INSTANCE, "Fishman Speed Modifier", 1.75D, AttributeModifier.Operation.MULTIPLY_TOTAL)).setSaved(false);

  
  public TwoFishEngineAbility() {
    super("Two Fish Engine", AbilityHelper.getRacialCategory());
    setThreshold(10.0D);
    setMaxCooldown(20.0D);
    setDescription("Increases the user's swimming speed");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    if (!player.getAttribute(LivingEntity.SWIM_SPEED).hasModifier((AttributeModifier)SWIN_SPEED)) {
      player.getAttribute(LivingEntity.SWIM_SPEED).applyModifier((AttributeModifier)SWIN_SPEED);
    }
    return true;
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    player.getAttribute(LivingEntity.SWIM_SPEED).removeModifier((AttributeModifier)SWIN_SPEED);
    return true;
  }
}


