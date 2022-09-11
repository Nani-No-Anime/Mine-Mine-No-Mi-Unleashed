package xyz.pixelatedw.mineminenomi.abilities.ryupteranodon;


import java.util.UUID;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.PteranodonFlyZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class PteranodonFlyPointAbility extends ZoanAbility {
  public static final PteranodonFlyPointAbility INSTANCE = new PteranodonFlyPointAbility();
  
  private static final AbilityAttributeModifier STRENGTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("eb43ce22-3c5a-45a0-810c-03c0f552efe7"), (Ability)INSTANCE, "Pteranodon Assault Point Strength Modifier", 2.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("4a89d972-ad2f-4a81-a959-34da54c60fd3"), (Ability)INSTANCE, "Pteranodon Assault Point Speed Modifier", 1.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  
  public float speed = 0.0F;

  
  public PteranodonFlyPointAbility() {
    super("Pteranodon Fly Point", AbilityHelper.getDevilFruitCategory());
    setDescription("Transforms the user into an ancient pteranodon");
    
    addZoanModifier(ModAttributes.PUNCH_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.ATTACK_SPEED, (AttributeModifier)SPEED_MODIFIER);
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuityEvent;
  }


  
  public void duringContinuityEvent(PlayerEntity player, int activeTime) {
    super.duringContinuityEvent(player, activeTime);
    player.fallDistance = 0.0F;
  }


  
  public ZoanInfo getTransformation() {
    return (ZoanInfo)PteranodonFlyZoanInfo.INSTANCE;
  }
}


