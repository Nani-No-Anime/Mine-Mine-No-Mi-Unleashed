package xyz.pixelatedw.mineminenomi.abilities.kachi;


import java.util.UUID;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
import xyz.pixelatedw.mineminenomi.api.abilities.IBodyOverlayAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class VulcanizationAbility extends PunchAbility implements IBodyOverlayAbility {
  public static final VulcanizationAbility INSTANCE = new VulcanizationAbility();
  
  private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setTexture(ModResources.HOT_BOILING_SPECIAL_ARM).setColor("#FFFFFFAA");
  private static final AbilityAttributeModifier ARMOR_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("b0e012c8-1479-414d-8015-9044225572a7"), (Ability)INSTANCE, "Vulcanization Modifier", 7.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);

  
  public VulcanizationAbility() {
    super("Vulcanization", AbilityHelper.getDevilFruitCategory());
    setDescription("Coats the user into a thick stone-like layer that increases the armor of its user");
    setMaxCooldown(15.0D);
    setThreshold(10.0D);
    setStoppingAfterHit(false);
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
    this.onHitEntityEvent = this::onHitEntityEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    player.getAttribute(SharedMonsterAttributes.ARMOR).removeModifier((AttributeModifier)ARMOR_MODIFIER);
    player.getAttribute(SharedMonsterAttributes.ARMOR).applyModifier((AttributeModifier)ARMOR_MODIFIER);
    return true;
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    player.getAttribute(SharedMonsterAttributes.ARMOR).removeModifier((AttributeModifier)ARMOR_MODIFIER);
    return true;
  }

  
  private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
    return 4.0F;
  }


  
  public AbilityOverlay getBodyOverlay() {
    return OVERLAY;
  }
}


