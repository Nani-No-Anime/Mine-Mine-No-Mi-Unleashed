package xyz.pixelatedw.mineminenomi.abilities.beta;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.abilities.blackleg.DiableJambeAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.*;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.LogiaParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class BetaCoatingAbility extends DamagedContinuousAbility implements IParallelContinuousAbility, IBodyOverlayAbility {
  public static final BetaCoatingAbility INSTANCE = new BetaCoatingAbility();
  private final ArrayList<DamageSource> explosiveSources = new ArrayList<>(Arrays.asList(new DamageSource[] { DamageSource.IN_FIRE, DamageSource.LIGHTNING_BOLT, DamageSource.ON_FIRE, DamageSource.LAVA }));
  private static final AbilityAttributeModifier SPEED_MULTIPLIER = (new AbilityAttributeModifier(UUID.fromString("efa08cbd-57e5-478f-b15c-6295eb1b375e"), (Ability)INSTANCE, "Beta Speed Modifier", -0.25D, AttributeModifier.Operation.MULTIPLY_TOTAL)).setSaved(false);
  private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setTexture(ModResources.BETA_COATING).setColor(WyHelper.hexToRGB("#FFFFFFA6"));
  boolean exploded; private boolean onDamagedEvent(LivingEntity entity, DamageSource damageSource, double damage) { for (DamageSource s : this.explosiveSources) { if (damageSource.getDamageType().equals(s.getDamageType())) { disableAbilityAndExplode(entity); return true; }  }
     if (damageSource.getImmediateSource() instanceof LivingEntity && damageSource.getImmediateSource().isBurning())
      return true;  if (!damageSource.isExplosion() && !damageSource.isFireDamage() && !damageSource.isDamageAbsolute()) { LogiaParticleEffect logiaParticleEffect = new LogiaParticleEffect(ModParticleTypes.BETA); logiaParticleEffect.spawn(entity.world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), 0.0D, 0.0D, 0.0D); return false; }
     disableAbilityAndExplode(entity); return true; } public BetaCoatingAbility() { super("Beta Coating", AbilityHelper.getDevilFruitCategory());























































    
    this.exploded = false; setDescription("Covers the user in a thick mucus coat, which makes them immune to almost all attacks, but extremely vulnerable to fire"); setMaxCooldown(10.0D); this.onDamagedEvent = this::onDamagedEvent; this.onStartContinuityEvent = this::onStartContinuityEvent; this.onEndContinuityEvent = this::onEndContinuityEvent;
    this.duringContinuityEvent = this::duringContinuity; }
  private boolean onStartContinuityEvent(PlayerEntity player) { this.exploded = false;
    player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier((AttributeModifier)SPEED_MULTIPLIER);
    return true; } private void disableAbilityAndExplode(LivingEntity entity) { if (!this.exploded) {
      
      if (entity instanceof PlayerEntity) {
        endContinuity((PlayerEntity)entity);
      }
      this.exploded = true;
      entity.extinguish();
      ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)entity, entity.world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), 6.0F);
      explosion.setExplosionSound(true);
      explosion.setDamageOwner(true);
      explosion.setDestroyBlocks(true);
      explosion.setFireAfterExplosion(true);
      explosion.setStaticDamage(100.0F);
      explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(6));
      explosion.setDamageEntities(true);
      explosion.doExplosion();
    }  } private void duringContinuity(PlayerEntity player, int i) { DiableJambeAbility ability = (DiableJambeAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)DiableJambeAbility.INSTANCE);
    if (player.isBurning() || (ability != null && ability.isContinuous()))
      disableAbilityAndExplode((LivingEntity)player);  }
  private boolean onEndContinuityEvent(PlayerEntity player) { player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier((AttributeModifier)SPEED_MULTIPLIER);
    return true; }
  public AbilityOverlay getBodyOverlay() {
    return OVERLAY;
  }
}


