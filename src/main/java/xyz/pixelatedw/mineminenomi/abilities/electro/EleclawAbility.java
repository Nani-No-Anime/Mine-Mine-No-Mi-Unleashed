package xyz.pixelatedw.mineminenomi.abilities.electro;
import java.lang.invoke.SerializedLambda;
import java.util.UUID;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.particles.effects.goro.GenericUseLightningEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

public class EleclawAbility extends PunchAbility implements IParallelContinuousAbility {
  public static final EleclawAbility INSTANCE = new EleclawAbility();
  
  private static final GenericUseLightningEffect PARTICLES = new GenericUseLightningEffect();
  private static final AbilityAttributeModifier ATTACK_SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("1d78a133-8a0e-4b8f-8790-1360007d4741"), (Ability)INSTANCE, "Eleclaw Attack Speed Modifier", 0.3499999940395355D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  
  private static final int MAX_USES = 9;
  public int uses = 9;

  
  public EleclawAbility() {
    super("Eleclaw", AbilityHelper.getRacialCategory());
    setMaxCooldown(10.0D);
    setDescription("The user coats their hands and weapons with lightning, enabling the use of other electric skills and giving the chance to stun foes");
    setStoppingAfterHit(false);
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
    this.onHitEntityEvent = this::onHitEntityEvent;
    this.onHitEffectEvent = this::onHitEffectEvent;
  }

  
  private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
    if (player.getRNG().nextInt(10) <= 2) {
      
      target.addPotionEffect(new EffectInstance(ModEffects.PARALYSIS, 20, 0, false, false, true));
      PARTICLES.spawn(player.world, target.getPosX(), target.getPosY(), target.getPosZ(), 0.0D, 0.0D, 0.0D);
    } 
  }

  
  public float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
    return 3.0F;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).removeModifier((AttributeModifier)ATTACK_SPEED_MODIFIER);
    player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).applyModifier((AttributeModifier)ATTACK_SPEED_MODIFIER);
    return true;
  }

  
  private void duringContinuityEvent(PlayerEntity player, int i) {
    if (this.uses <= 0) {
      
      endContinuity(player);
      this.uses = 9;
    } 
    
    if (player.areEyesInFluid(FluidTags.WATER, true)) {
      
      player.attackEntityFrom((DamageSource)ModDamageSource.LIGHTNING_BOLT.getSource(), 4.0F);
      endContinuity(player);
    } 
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    this.uses = 9;
    
    int extraCooldown = Math.min(25, (int)Math.round(this.continueTime / 20.0D));
    setMaxCooldown((5 + extraCooldown));
    player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).removeModifier((AttributeModifier)ATTACK_SPEED_MODIFIER);
    return true;
  }



































  
  public void reduceUsage(PlayerEntity player, int number) {
    SulongAbility sulongAbility = (SulongAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)SulongAbility.INSTANCE);
    boolean sulongEnabled = (sulongAbility != null && sulongAbility.isContinuous());
    if (!sulongEnabled)
      this.uses -= number; 
  }
}


