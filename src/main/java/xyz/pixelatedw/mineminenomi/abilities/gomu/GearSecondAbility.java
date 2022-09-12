package xyz.pixelatedw.mineminenomi.abilities.gomu;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
import xyz.pixelatedw.mineminenomi.api.abilities.IBodyOverlayAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.GomuHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.gomu.GearSecondParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

import java.awt.*;
import java.util.UUID;

public class GearSecondAbility extends ContinuousAbility implements IBodyOverlayAbility, IParallelContinuousAbility {
  public static final GearSecondAbility INSTANCE = new GearSecondAbility();
  
  private static final AbilityAttributeModifier JUMP_HEIGHT = (new AbilityAttributeModifier(UUID.fromString("a44a9644-369a-4e18-88d9-323727d3d85b"), (Ability)INSTANCE, "Gear Second Jump Modifier", 5.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier STRENGTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("a2337b58-7e6d-4361-a8ca-943feee4f906"), (Ability)INSTANCE, "Gear Second Attack Damage Modifier", 4.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier STEP_HEIGHT = (new AbilityAttributeModifier(UUID.fromString("eab680cd-a6dc-438a-99d8-46f9eb53a950"), (Ability)INSTANCE, "Gear Second Step Height Modifier", 1.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  
  public static final ParticleEffect PARTICLES = (ParticleEffect)new GearSecondParticleEffect();
  
  private boolean prevSprintValue = false;
  
  public GearSecondAbility() {
    super("Gear Second", AbilityHelper.getDevilFruitCategory());
    setThreshold(50.0D);
    setDescription("By speding up their blood flow, the user gains strength, speed and mobility");
    
    this.onStartContinuityEvent = this::onStartContinuity;
    this.duringContinuityEvent = this::duringContinuity;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }

  
  private boolean onStartContinuity(PlayerEntity player) {
    if (!GomuHelper.canActivateGear(AbilityDataCapability.get((LivingEntity)player), (Ability)INSTANCE)) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_GEAR_ACTIVE, new Object[0]));
      return false;
    } 
    
    player.getAttribute(ModAttributes.STEP_HEIGHT).applyModifier((AttributeModifier)STEP_HEIGHT);
    player.getAttribute(ModAttributes.JUMP_HEIGHT).applyModifier((AttributeModifier)JUMP_HEIGHT);
    player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).applyModifier((AttributeModifier)STRENGTH_MODIFIER);
    player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier((AttributeModifier)STRENGTH_MODIFIER);
    player.world.playSound(null, player.getPosition(), ModSounds.GEAR_SECOND_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
    this.prevSprintValue = player.isSprinting();
    return true;
  }

  
  private void duringContinuity(PlayerEntity player, int passiveTimer) {
    if (passiveTimer % 10 == 0) {
      PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    }
    if (player.isInWater() || !AbilityHelper.canUseMomentumAbility(player)) {
      endContinuity(player);
    }
    if (player.isSprinting()) {
      
      if (!this.prevSprintValue) {
        player.world.playSound(null, player.getPosition(), ModSounds.TELEPORT_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
      }
    } else {
      this.prevSprintValue = false;
      
      return;
    } 
    float maxSpeed = 2.2F;
    
    Vec3d vec = player.getLookVec();
    if (player.onGround) {
      
      player.setMotion(vec.x * maxSpeed, (player.getMotion()).y, vec.z * maxSpeed);
    } else {
      
      player.setMotion(vec.x * maxSpeed * 0.5D, 
          (player.getMotion()).y, vec.z * maxSpeed * 0.5D);
    } 

    
    this.prevSprintValue = player.isSprinting();
    player.velocityChanged = true;
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    int cooldown = (int)Math.round(this.continueTime / 30.0D);
    setMaxCooldown(cooldown);
    if (this.continueTime > getThreshold() / 1.425D && EntityStatsCapability.get((LivingEntity)player).getDoriki() < 2000) {
      
      player.addPotionEffect(new EffectInstance(Effects.HUNGER, 600, 3, true, true));
      player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200, 1, true, true));
    } 
    
    player.getAttribute(ModAttributes.STEP_HEIGHT).removeModifier((AttributeModifier)STEP_HEIGHT);
    player.getAttribute(ModAttributes.JUMP_HEIGHT).removeModifier((AttributeModifier)JUMP_HEIGHT);
    player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).removeModifier((AttributeModifier)STRENGTH_MODIFIER);
    player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier((AttributeModifier)STRENGTH_MODIFIER);
    return true;
  }
  
  private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setColor(new Color(232, 54, 54, 74));

  
  public AbilityOverlay getBodyOverlay() {
    return OVERLAY;
  }
}


