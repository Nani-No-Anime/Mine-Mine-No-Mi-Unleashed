package xyz.pixelatedw.mineminenomi.abilities.sui;
import java.lang.invoke.SerializedLambda;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

public class NyanNyanSuplexAbility extends ChargeableAbility {
  public static final NyanNyanSuplexAbility INSTANCE = new NyanNyanSuplexAbility();
  private LivingEntity grabbedEntity = null;

  
  public NyanNyanSuplexAbility() {
    super("Nyan Nyan Suplex", AbilityHelper.getDevilFruitCategory());
    setDescription("While swimming, grabs an opponent from the back and launches them into the ground, dealing moderate damage and creating a small crater");
    setMaxCooldown(10.0D);
    setMaxChargeTime(2.0D);
    
    this.onStartChargingEvent = this::onUseEvent;
    this.duringChargingEvent = this::duringChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    this.grabbedEntity = null;
    FreeSwimmingAbility ability = (FreeSwimmingAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)FreeSwimmingAbility.INSTANCE);
    if (ability != null && ability.isSwimming) {
      List<LivingEntity> list = WyHelper.getEntitiesNearSphere(player.getPosition(), player.world, 1.2D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
      list.remove(player);
      list.removeIf(entity -> AbilityHelper.isTargetBlockingAbility((LivingEntity)player, entity));
      if (!HakiHelper.hasHardeningActive((LivingEntity)player))
        list.removeIf(entity -> DevilFruitCapability.get(entity).isLogia()); 
      this.grabbedEntity = (list.size() > 0) ? list.get(0) : null;
      return (list.size() > 0);
    } 
    return false;
  }


  
  private void duringChargingEvent(PlayerEntity player, int chargeTimer) {
    if (!this.grabbedEntity.isAlive() || (DevilFruitCapability.get(this.grabbedEntity).isLogia() && !HakiHelper.hasHardeningActive((LivingEntity)player)) || AbilityHelper.isTargetBlockingAbility((LivingEntity)player, this.grabbedEntity)) {
      
      endCharging(player);
      
      return;
    } 
    this.grabbedEntity.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 20, 0));
    FreeSwimmingAbility ability = (FreeSwimmingAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)FreeSwimmingAbility.INSTANCE);
    boolean isActive = (ability != null && ability.isContinuous());
    if (isActive) {
      ability.endContinuity(player);
    }
    float distance = 1.0F;
    Vec3d lookVec = this.grabbedEntity.getLookVec();

    
    Vec3d pos = new Vec3d(this.grabbedEntity.getPosX() - lookVec.x * distance, this.grabbedEntity.getPosY(), this.grabbedEntity.getPosZ() - lookVec.z * distance);
    player.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
  }

  
  private boolean onEndChargingEvent(PlayerEntity player) {
    if (this.grabbedEntity == null || getChargeTime() >= getMaxChargeTime()) {
      return false;
    }
    this.grabbedEntity.attackEntityFrom(DamageSource.causePlayerDamage(player), 10.0F);
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)player, player.world, this.grabbedEntity.getPosX(), this.grabbedEntity.getPosY(), this.grabbedEntity.getPosZ(), 2.0F);
    explosion.setStaticDamage(30.0F);
    explosion.setExplosionSound(true);
    explosion.setDamageOwner(false);
    explosion.setDestroyBlocks(true);
    explosion.setFireAfterExplosion(false);
    explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
    explosion.setDamageEntities(true);
    explosion.doExplosion();
    
    return true;
  }
}


