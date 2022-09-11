package xyz.pixelatedw.mineminenomi.abilities.blackleg;


import java.util.UUID;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
import xyz.pixelatedw.mineminenomi.api.abilities.IPunchOverlayAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
import xyz.pixelatedw.mineminenomi.particles.effects.blackleg.DiableJambeParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public class DiableJambeAbility extends ContinuousAbility implements IParallelContinuousAbility, IPunchOverlayAbility {
  public static final DiableJambeAbility INSTANCE = new DiableJambeAbility();
  
  private static final DiableJambeParticleEffect PARTICLES = new DiableJambeParticleEffect();
  private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setTexture(ModResources.HOT_BOILING_SPECIAL_ARM).setColor("#FFBB44AA");
  private static final AbilityAttributeModifier DIABLE_JAMBE_STRENGTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("e3ae074c-40a9-49ff-aa3b-7cc9b98ddc2d"), (Ability)INSTANCE, "Diable Jambe Attack Multiplier", 4.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier DIABLE_JAMBE_ATTACK_SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("a984413a-7459-4989-8362-7c46a2663f0e"), (Ability)INSTANCE, "Diable Jambe Speed Multiplier", 0.4000000059604645D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  
  private boolean frozen = false;

  
  public DiableJambeAbility() {
    super("Diable Jambe", AbilityHelper.getStyleCategory());
    setThreshold(40.0D);
    setDescription("The user heats up their leg, dealing additional damage and shortly setting the target on fire (Enhances all Blackleg attacks)");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuity;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier((AttributeModifier)DIABLE_JAMBE_STRENGTH_MODIFIER);
    player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier((AttributeModifier)DIABLE_JAMBE_STRENGTH_MODIFIER);
    
    player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).removeModifier((AttributeModifier)DIABLE_JAMBE_ATTACK_SPEED_MODIFIER);
    player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).applyModifier((AttributeModifier)DIABLE_JAMBE_ATTACK_SPEED_MODIFIER);
    
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    
    ExtraHachisAbility extraHachiAbl = (ExtraHachisAbility)abilityProps.getEquippedAbility((Ability)ExtraHachisAbility.INSTANCE);
    if (extraHachiAbl != null) {
      
      extraHachiAbl.enableDiableJambeMode();
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, (Ability)extraHachiAbl), (LivingEntity)player);
    } 
    this.frozen = false;
    return true;
  }

  
  private void duringContinuity(PlayerEntity player, int timer) {
    if (player.getActivePotionEffect(ModEffects.FROZEN) != null) {
      
      this.frozen = true;
      AbilityHelper.reduceEffect(player.getActivePotionEffect(ModEffects.FROZEN), 1.100000023841858D);
      endContinuity(player);
    } 
    
    if (player.getActivePotionEffect(ModEffects.FROSTBITE) != null) {
      
      this.frozen = true;
      AbilityHelper.reduceEffect(player.getActivePotionEffect(ModEffects.FROSTBITE), 1.5D);
      endContinuity(player);
    } 
    
    PARTICLES.spawn(player, player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier((AttributeModifier)DIABLE_JAMBE_STRENGTH_MODIFIER);
    player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).removeModifier((AttributeModifier)DIABLE_JAMBE_ATTACK_SPEED_MODIFIER);
    int cooldown = (this.frozen ? 20 : 4) + (int)WyHelper.clamp(Math.round(this.continueTime / 40.0D), 0L, 200L);
    setMaxCooldown(cooldown);
    
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    
    ExtraHachisAbility extraHachiAbl = (ExtraHachisAbility)abilityProps.getEquippedAbility((Ability)ExtraHachisAbility.INSTANCE);
    if (extraHachiAbl != null) {
      
      extraHachiAbl.disableDiableJambeMode();
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, (Ability)extraHachiAbl), (LivingEntity)player);
    } 
    
    return true;
  }


  
  public AbilityOverlay getPunchOverlay(LivingEntity entity) {
    return OVERLAY;
  }
}


