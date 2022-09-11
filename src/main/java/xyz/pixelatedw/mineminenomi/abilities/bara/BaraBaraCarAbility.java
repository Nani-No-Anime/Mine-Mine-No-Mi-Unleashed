package xyz.pixelatedw.mineminenomi.abilities.bara;

import java.lang.invoke.SerializedLambda;
import java.util.UUID;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.BaraCarZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class BaraBaraCarAbility extends ZoanAbility {
  public static final BaraBaraCarAbility INSTANCE = new BaraBaraCarAbility();
  
  private static final AbilityAttributeModifier SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("d141ef28-e77a-418d-927b-ca9a09417189"), (Ability)INSTANCE, "Walk Point Modifier", 1.2000000476837158D, AttributeModifier.Operation.MULTIPLY_BASE)).setSaved(false);
  private static final AbilityAttributeModifier STEP_HEIGHT = (new AbilityAttributeModifier(UUID.fromString("0349c517-823a-4f20-8a95-b9a0e3787c47"), (Ability)INSTANCE, "Bara Bara Festival Modifier", 1.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);

  
  public BaraBaraCarAbility() {
    super("Bara Bara Car", AbilityHelper.getDevilFruitCategory());
    setDescription("Turns the user's body into a car");
    setThreshold(60.0D);
    
    addZoanModifier(SharedMonsterAttributes.MOVEMENT_SPEED, (AttributeModifier)SPEED_MODIFIER);
    addZoanModifier(ModAttributes.STEP_HEIGHT, (AttributeModifier)STEP_HEIGHT);
    
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }


  
  public boolean onEndContinuityEvent(PlayerEntity player) {
    double cooldown = (5.0F + this.continueTime / 20.0F);
    setMaxCooldown(cooldown);
    
    return super.onEndContinuityEvent(player);
  }


  
  public ZoanInfo getTransformation() {
    return (ZoanInfo)BaraCarZoanInfo.INSTANCE;
  }
}


