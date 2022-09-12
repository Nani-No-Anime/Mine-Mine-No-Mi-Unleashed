package xyz.pixelatedw.mineminenomi.abilities.electro;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;

import java.util.UUID;

public class SulongAbility extends ContinuousAbility implements IParallelContinuousAbility {
  public static final SulongAbility INSTANCE = new SulongAbility();
  
  private static final AbilityAttributeModifier SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("e158d542-5644-4921-9d5f-895f0b0a164c"), (Ability)INSTANCE, "Sulong Speed Modifier", 1.5D, AttributeModifier.Operation.MULTIPLY_BASE)).setSaved(false);
  private static final AbilityAttributeModifier STRENGTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("00bd47ca-63b1-4040-b942-d9857231c9da"), (Ability)INSTANCE, "Sulong Damage Modifier", 7.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier JUMP_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("00bd47ca-63b1-4040-b942-d9857231c9da"), (Ability)INSTANCE, "Sulong Damage Modifier", 8.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier FALL_RESISTANCE = (new AbilityAttributeModifier(UUID.fromString("00bd47ca-63b1-4040-b942-d9857231c9da"), (Ability)INSTANCE, "Sulong Damage Modifier", 8.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier STEP_HEIGHT = (new AbilityAttributeModifier(UUID.fromString("eab680cd-a6dc-438a-99d8-46f9eb53a950"), (Ability)INSTANCE, "Sulong Step Height Modifier", 1.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);

  
  public SulongAbility() {
    super("Sulong Form", AbilityHelper.getRacialCategory());
    setThreshold(300.0D);
    setDescription("The user reveals their true power during the full moon, enhancing their physical and electrical power");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }

  
  private void duringContinuityEvent(PlayerEntity player, int i) {
    boolean sulongCheck = canTransform(player.world);
    if (!sulongCheck) {
      endContinuity(player);
    }
  }
  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    boolean sulongCheck = (canTransform(player.world) && player.world.canSeeSky(player.getPosition().add(0.0D, 1.2D, 0.0D)));
    
    if (sulongCheck) {
      
      player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier((AttributeModifier)SPEED_MODIFIER);
      player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).applyModifier((AttributeModifier)SPEED_MODIFIER);
      player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier((AttributeModifier)STRENGTH_MODIFIER);
      player.getAttribute(ModAttributes.JUMP_HEIGHT).applyModifier((AttributeModifier)JUMP_MODIFIER);
      player.getAttribute(ModAttributes.FALL_RESISTANCE).applyModifier((AttributeModifier)FALL_RESISTANCE);
      player.getAttribute(ModAttributes.STEP_HEIGHT).applyModifier((AttributeModifier)STEP_HEIGHT);
    } else {
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_MOON, new Object[0]));
    } 
    return sulongCheck;
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    player.addPotionEffect(new EffectInstance(Effects.HUNGER, this.continueTime, (int)(this.continueTime / 2000.0F)));
    player.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, (int)(this.continueTime * 0.75F), (int)(this.continueTime / 2000.0F)));
    player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, (int)(this.continueTime * 0.5F), (int)(this.continueTime / 2000.0F)));
    player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, (int)(this.continueTime * 0.5F), (int)(this.continueTime / 2000.0F)));
    
    IEntityStats stats = EntityStatsCapability.get((LivingEntity)player);
    if (this.continueTime / 20 > stats.getDoriki() / 32)
    {
      player.addPotionEffect(new EffectInstance(ModEffects.UNCONSCIOUS, 800, 0));
    }
    
    player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier((AttributeModifier)SPEED_MODIFIER);
    player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier((AttributeModifier)SPEED_MODIFIER);
    player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).removeModifier((AttributeModifier)SPEED_MODIFIER);
    player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier((AttributeModifier)STRENGTH_MODIFIER);
    player.getAttribute(ModAttributes.JUMP_HEIGHT).removeModifier((AttributeModifier)JUMP_MODIFIER);
    player.getAttribute(ModAttributes.FALL_RESISTANCE).removeModifier((AttributeModifier)FALL_RESISTANCE);
    player.getAttribute(ModAttributes.STEP_HEIGHT).removeModifier((AttributeModifier)STEP_HEIGHT);
    
    player.addExhaustion(5.0F + this.continueTime / 100.0F);
    setMaxCooldown((this.continueTime / 10.0F));
    return true;
  }

  
  private boolean canTransform(World world) {
    if (WyDebug.isDebug() && APIConfig.BUILD_MODE == APIConfig.BuildMode.DEV)
      return true; 
    return (world.getDimension().getMoonPhase(world.getDayTime()) == 0 && !world.isRaining() && world.getDimension().getType() == DimensionType.OVERWORLD && world.isNightTime());
  }
}


