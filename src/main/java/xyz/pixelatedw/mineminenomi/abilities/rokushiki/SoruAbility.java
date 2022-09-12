package xyz.pixelatedw.mineminenomi.abilities.rokushiki;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

import java.util.UUID;

public class SoruAbility extends ContinuousAbility {
  public static final Ability INSTANCE = (Ability)new SoruAbility();
  
  private static final AbilityAttributeModifier STEP_HEIGHT_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("1d68a133-8a0e-4b8f-8790-1360007d4741"), INSTANCE, "Soru Step Height Modifier", 1.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);

  
  public SoruAbility() {
    super("Soru", AbilityHelper.getRacialCategory());
    setMaxCooldown(10.0D);
    setThreshold(2.0D);
    setDescription("Allows the user to move at an extremely high speed for a short period");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuity;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    if (!AbilityHelper.canUseMomentumAbility(player)) {
      return false;
    }
    if (!player.onGround) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONLY_IN_GROUND, new Object[] { getName() }));
      return false;
    } 
    
    player.getAttribute(ModAttributes.STEP_HEIGHT).removeModifier((AttributeModifier)STEP_HEIGHT_MODIFIER);
    player.getAttribute(ModAttributes.STEP_HEIGHT).applyModifier((AttributeModifier)STEP_HEIGHT_MODIFIER);
    player.world.playSound(null, player.getPosition(), ModSounds.TELEPORT_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
    
    int maxUse = 10 + (int)Math.min(Math.round(EntityStatsCapability.get((LivingEntity)player).getDoriki() * 0.003D / 10.0D) * 10L, 30L);
    setThresholdInTicks(maxUse);
    WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, (Ability)this), (LivingEntity)player);
    return true;
  }

  
  private void duringContinuity(PlayerEntity player, int passiveTimer) {
    if (player.collidedHorizontally || player.isInWater() || !AbilityHelper.canUseMomentumAbility(player)) {
      endContinuity(player);
    }
    int doriki = EntityStatsCapability.get((LivingEntity)player).getDoriki();
    float maxSpeed = 1.25F + (int)Math.min(Math.round(doriki * 7.5E-5D), 0.75D);
    
    Vec3d vec = player.getLookVec();
    if (player.onGround) {
      
      player.setMotion(vec.x * maxSpeed, (player.getMotion()).y, vec.z * maxSpeed);
    } else {
      
      player.setMotion((doriki > 6000) ? (vec.x * maxSpeed * 0.75D) : (player.getMotion()).x, 
          (player.getMotion()).y, 
          (doriki > 6000) ? (vec.z * maxSpeed * 0.5D) : (player.getMotion()).z);
    } 
    
    player.velocityChanged = true;
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    int cooldown = 2 + (int)Math.round(this.continueTime / 5.0D);
    setMaxCooldown(cooldown);
    player.getAttribute(ModAttributes.STEP_HEIGHT).removeModifier((AttributeModifier)STEP_HEIGHT_MODIFIER);
    return true;
  }
}


