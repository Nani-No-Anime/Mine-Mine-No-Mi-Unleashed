package xyz.pixelatedw.mineminenomi.abilities.netsu;
import java.lang.invoke.SerializedLambda;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
import xyz.pixelatedw.mineminenomi.api.abilities.IBodyOverlayAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.particles.effects.netsu.NetsuEnhancementParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IChangeDamageSourceAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.events.SetOnFireEvent;

public class NetsuEnhancementAbility extends ContinuousAbility implements IParallelContinuousAbility, IChangeDamageSourceAbility, IBodyOverlayAbility {
  public static final NetsuEnhancementAbility INSTANCE = new NetsuEnhancementAbility();
  
  private static final NetsuEnhancementParticleEffect PARTICLES = new NetsuEnhancementParticleEffect();
  private static final AbilityAttributeModifier MULTIPLIER = (new AbilityAttributeModifier(UUID.fromString("efa08cbd-57e5-478f-b15c-6295eb1b375e"), (Ability)INSTANCE, "Netsu Enhancement Modifier", 0.25D, AttributeModifier.Operation.MULTIPLY_BASE)).setSaved(false);
  private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setRenderType(AbilityOverlay.RenderType.ENERGY).setColor(WyHelper.hexToRGB("#962A2AAA"));

  
  public NetsuEnhancementAbility() {
    super("Netsu Enhancement", AbilityHelper.getDevilFruitCategory());
    setThreshold(40.0D);
    setDescription("Increases the user's attacks and body functions through heat");
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
    this.duringContinuityEvent = this::duringContinuityEvent;
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier((AttributeModifier)MULTIPLIER);
    player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier((AttributeModifier)MULTIPLIER);
    player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).removeModifier((AttributeModifier)MULTIPLIER);
    
    int cooldown = (int)Math.round(this.continueTime / 40.0D) + 15;
    setMaxCooldown(cooldown);
    return true;
  }

  
  private void duringContinuityEvent(PlayerEntity player, int timer) {
    PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier((AttributeModifier)MULTIPLIER);
    player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier((AttributeModifier)MULTIPLIER);
    player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).applyModifier((AttributeModifier)MULTIPLIER);
    return true;
  }


  
  public AbilityOverlay getBodyOverlay() {
    return OVERLAY;
  }


  
  public float damageToEntityWithSource(PlayerEntity player, LivingEntity target) {
    float strength = Math.min((float)player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue(), 60.0F);
    float keptDamage = 0.375F * (1.0F - strength / 160.0F);
    
    if (target.hurtResistantTime > 0 && target.hurtResistantTime <= 5) {
      target.hurtTime = target.hurtResistantTime = 0;
    }
    SetOnFireEvent event = new SetOnFireEvent((LivingEntity)player, target, 5);
    if (!MinecraftForge.EVENT_BUS.post(event)) {
      target.setFire(5);
    }
    return 8.0F + Math.min(strength * keptDamage, 15.0F);
  }

  
  public DamageSource getSourceToUse(PlayerEntity player) {
    return (DamageSource)(new ModDamageSource("onFire", false, true, false)).causeEntityDamageFromSource((Entity)player);
  }

  
  public boolean cancelsOriginalDamage() {
    return false;
  }

  
  public boolean isSourceChangeEnabled() {
    return isContinuous();
  }
}


