package xyz.pixelatedw.mineminenomi.abilities.doku;

import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
import xyz.pixelatedw.mineminenomi.entities.zoan.VenomDemonZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateCustomTexturePacket;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.doku.VenomDemonParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

import java.util.UUID;

public class VenomDemonAbility extends ZoanAbility {
  public static final VenomDemonAbility INSTANCE = new VenomDemonAbility();
  
  private static final ParticleEffect PARTICLES = (ParticleEffect)new VenomDemonParticleEffect();
  private static final BlockProtectionRule GRIEF_RULE = DefaultProtectionRules.AIR_FOLIAGE.addApprovedBlocks(new Block[] { ModBlocks.POISON, ModBlocks.DEMON_POISON });
  
  private static final AbilityAttributeModifier ATTACK_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Venom Demon Attack Modifier", 8.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier REACH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("930fd8dc-5f0c-4daa-869c-d42d6166d3f1"), (Ability)INSTANCE, "Venom Demon Reach Modifier", 2.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("4e2e4824-f5e8-449f-a54b-7d0a5572c6a1"), (Ability)INSTANCE, "Venom Demon Speed Modifier", 0.02D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier ATTACK_SPEED = (new AbilityAttributeModifier(UUID.fromString("2880a753-352a-4e1c-bf4e-4e4e4649febe"), (Ability)INSTANCE, "Venom Demon Attack Speed Modifier", 0.15D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier STEP_ASSIST = (new AbilityAttributeModifier(UUID.fromString("2880a753-352a-4e1c-bf4e-4e4e4649febe"), (Ability)INSTANCE, "Venom Demon Step assist Modifier", 1.5D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier JUMP_HEIGHT = (new AbilityAttributeModifier(UUID.fromString("2880a753-352a-4e1c-bf4e-4e4e4649febe"), (Ability)INSTANCE, "Venom Demon Jump Height Modifier", 2.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier KNOCKBACK_RESISTANCE = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Venom Demon Knockback Resistance Modifier", 1.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);

  
  public VenomDemonAbility() {
    super("Venom Demon", AbilityHelper.getDevilFruitCategory());
    setDescription("The user coats themselves in layers of strong corrosive venom, becoming a Venom Demon and leaving a highly poisonous trail. Also enhances all Posion abilities");
    
    setMaxCooldown(100.0D);
    setThreshold(60.0D);
    
    addZoanModifier(PlayerEntity.REACH_DISTANCE, (AttributeModifier)REACH_MODIFIER);
    addZoanModifier(ModAttributes.ATTACK_RANGE, (AttributeModifier)REACH_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.ATTACK_DAMAGE, (AttributeModifier)ATTACK_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.MOVEMENT_SPEED, (AttributeModifier)SPEED_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.ATTACK_SPEED, (AttributeModifier)ATTACK_SPEED);
    addZoanModifier(ModAttributes.STEP_HEIGHT, (AttributeModifier)STEP_ASSIST);
    addZoanModifier(ModAttributes.FALL_RESISTANCE, (AttributeModifier)STEP_ASSIST);
    addZoanModifier(ModAttributes.JUMP_HEIGHT, (AttributeModifier)JUMP_HEIGHT);
    addZoanModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, (AttributeModifier)KNOCKBACK_RESISTANCE);
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuity;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }


  
  public boolean onStartContinuityEvent(PlayerEntity player) {
    if (!super.onStartContinuityEvent(player)) {
      return false;
    }
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    
    HydraAbility hydraAbility = (HydraAbility)abilityProps.getEquippedAbility(HydraAbility.INSTANCE);
    if (hydraAbility != null) {
      
      hydraAbility.enableVenomDemoMode();
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateCustomTexturePacket(player, hydraAbility), (LivingEntity)player);
    } 
    
    ChloroBallAbility chloroBallAbility = (ChloroBallAbility)abilityProps.getEquippedAbility((Ability)ChloroBallAbility.INSTANCE);
    if (chloroBallAbility != null) {
      
      chloroBallAbility.enableVenomDemoMode();
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateCustomTexturePacket(player, (Ability)chloroBallAbility), (LivingEntity)player);
    } 
    
    DokuGumoAbility dokuGumoAbility = (DokuGumoAbility)abilityProps.getEquippedAbility((Ability)DokuGumoAbility.INSTANCE);
    if (dokuGumoAbility != null) {
      
      dokuGumoAbility.enableVenomDemoMode();
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateCustomTexturePacket(player, (Ability)dokuGumoAbility), (LivingEntity)player);
    } 
    
    VenomRoadAbility venomDemonAbility = (VenomRoadAbility)abilityProps.getEquippedAbility(VenomRoadAbility.INSTANCE);
    if (venomDemonAbility != null) {
      
      venomDemonAbility.enableVenomDemoMode();
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateCustomTexturePacket(player, venomDemonAbility), (LivingEntity)player);
    } 
    
    return true;
  }

  
  private void duringContinuity(PlayerEntity player, int timer) {
    player.addPotionEffect(new EffectInstance(ModEffects.PHYSICAL_MOVING_GUARD, 2, 3, false, false));
    
    if (!AbilityHelper.isNearbyKairoseki(player))
    {
      for (int x = -1; x < 1; x++) {
        for (int z = -1; z < 1; z++) {
          
          BlockPos pos = new BlockPos(player.getPosX() + x, player.getPosY(), player.getPosZ() + z);
          if (player.world.getBlockState(pos.down()).isSolid())
            AbilityHelper.placeBlockIfAllowed(player.world, player.getPosX() + x, player.getPosY(), player.getPosZ() + z, ModBlocks.DEMON_POISON, GRIEF_RULE); 
        } 
      } 
    }
    PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
  }


  
  public boolean onEndContinuityEvent(PlayerEntity player) {
    super.onEndContinuityEvent(player);
    
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    
    HydraAbility hydraAbility = (HydraAbility)abilityProps.getEquippedAbility(HydraAbility.INSTANCE);
    if (hydraAbility != null) {
      
      hydraAbility.disableVenomDemoMode();
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateCustomTexturePacket(player, hydraAbility), (LivingEntity)player);
    } 
    
    ChloroBallAbility chloroBallAbility = (ChloroBallAbility)abilityProps.getEquippedAbility((Ability)ChloroBallAbility.INSTANCE);
    if (chloroBallAbility != null) {
      
      chloroBallAbility.disableVenomDemoMode();
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateCustomTexturePacket(player, (Ability)chloroBallAbility), (LivingEntity)player);
    } 
    
    DokuGumoAbility dokuGumoAbility = (DokuGumoAbility)abilityProps.getEquippedAbility((Ability)DokuGumoAbility.INSTANCE);
    if (dokuGumoAbility != null) {
      
      dokuGumoAbility.disableVenomDemoMode();
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateCustomTexturePacket(player, (Ability)dokuGumoAbility), (LivingEntity)player);
    } 
    
    VenomRoadAbility venomDemonAbility = (VenomRoadAbility)abilityProps.getEquippedAbility(VenomRoadAbility.INSTANCE);
    if (venomDemonAbility != null) {
      
      venomDemonAbility.disableVenomDemoMode();
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateCustomTexturePacket(player, venomDemonAbility), (LivingEntity)player);
    } 
    
    return true;
  }


  
  public ZoanInfo getTransformation() {
    return (ZoanInfo)VenomDemonZoanInfo.INSTANCE;
  }
}


