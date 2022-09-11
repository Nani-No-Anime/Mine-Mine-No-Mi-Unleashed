package xyz.pixelatedw.mineminenomi.abilities.pika;

import java.awt.Color;
import java.lang.invoke.SerializedLambda;
import java.util.UUID;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
import xyz.pixelatedw.mineminenomi.api.abilities.IPunchOverlayAbility;
import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IChangeDamageSourceAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;

public class LightAccelerationAbility extends ContinuousAbility implements IChangeDamageSourceAbility, IPunchOverlayAbility, IParallelContinuousAbility {
  public static final Ability INSTANCE = (Ability)new LightAccelerationAbility();
  
  private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setRenderType(AbilityOverlay.RenderType.ENERGY).setColor(new Color(255, 220, 0));
  public static final AbilityAttributeModifier ATTACK_SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("1d78a133-8a0e-4b8f-8790-1360007d4741"), INSTANCE, "Light Acceleration Attack Speed Multiplier", 4.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);

  
  public LightAccelerationAbility() {
    super("Light Acceleration", AbilityHelper.getDevilFruitCategory());
    setThreshold(20.0D);
    setMaxCooldown(10.0D);
    setDescription("The user accelerates its attacks by converting into light before impact");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuity;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    if (!player.getHeldItemMainhand().isEmpty()) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_FIST, new Object[0]));
      return false;
    } 
    
    player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).applyModifier((AttributeModifier)ATTACK_SPEED_MODIFIER);
    
    return true;
  }

  
  private void duringContinuityEvent(PlayerEntity player, int i) {
    if (!player.getHeldItemMainhand().isEmpty()) {
      endContinuity(player);
    }
  }
  
  private boolean onEndContinuity(PlayerEntity player) {
    player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).removeModifier((AttributeModifier)ATTACK_SPEED_MODIFIER);
    return true;
  }


  
  public float damageToEntityWithSource(PlayerEntity player, LivingEntity target) {
    float strength = Math.min((float)player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue(), 50.0F);
    
    if (target.hurtResistantTime > 0 && target.hurtResistantTime <= 5) {
      target.hurtTime = target.hurtResistantTime = 0;
    }
    float punchMultiplier = 1.0F + 5.0F * strength / 60.0F;

    
    Vec3d speed = player.getLook(1.0F).mul(punchMultiplier, (punchMultiplier * 0.75F), punchMultiplier);
    target.setMotion(speed.x, speed.y, speed.z);
    target.velocityChanged = true;
    target.fallDistance = 0.0F;
    
    return 10.0F + strength;
  }


  
  public DamageSource getSourceToUse(PlayerEntity player) {
    return (DamageSource)new AbilityDamageSource("player", null, (Ability)this);
  }


  
  public boolean cancelsOriginalDamage() {
    return false;
  }


  
  public boolean isSourceChangeEnabled() {
    return isContinuous();
  }


  
  public AbilityOverlay getPunchOverlay(LivingEntity entity) {
    return entity.isSwingInProgress ? OVERLAY : (new AbilityOverlay()).setTexture(ModResources.DOKU_COATING).setColor(new Color(255, 255, 255, 0));
  }
}


