package xyz.pixelatedw.mineminenomi.abilities.brawler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.renderers.animations.PointBothArmsAnimation;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

import java.util.List;

public class SuplexAbility extends ChargeableAbility implements IAnimatedAbility {
  public static final SuplexAbility INSTANCE = new SuplexAbility();
  private LivingEntity grabbedEntity = null;

  
  public SuplexAbility() {
    super("Suplex", AbilityHelper.getStyleCategory());
    setDescription("Grabs an opponent from the back and launches it into the ground");
    setMaxCooldown(7.0D);
    setMaxChargeTime(1.0D);
    
    this.onStartChargingEvent = this::onUseEvent;
    this.duringChargingEvent = this::duringChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    if (!AbilityHelper.canUseBrawlerAbilities((LivingEntity)player)) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_FIST, new Object[0]));
      return false;
    } 
    
    List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.2D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
    list.remove(player);
    list.removeIf(entity -> AbilityHelper.isTargetBlockingAbility((LivingEntity)player, entity));
    
    if (list.size() < 1) {
      return false;
    }
    this.grabbedEntity = (list.size() > 0) ? list.get(0) : null;
    return true;
  }

  
  private void duringChargingEvent(PlayerEntity player, int chargeTimer) {
    if (!this.grabbedEntity.isAlive() || (DevilFruitCapability.get(this.grabbedEntity).isLogia() && !HakiHelper.hasHardeningActive((LivingEntity)player)) || AbilityHelper.isTargetBlockingAbility((LivingEntity)player, this.grabbedEntity)) {
      
      endCharging(player);
      
      return;
    } 
    this.grabbedEntity.rotationPitch = this.grabbedEntity.prevRotationPitch;
    this.grabbedEntity.rotationYaw = this.grabbedEntity.prevRotationYaw;
    
    this.grabbedEntity.addPotionEffect(new EffectInstance(ModEffects.PARALYSIS, 5, 3));
    player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1));
    
    float distance = 1.0F;
    Vec3d lookVec = this.grabbedEntity.getLookVec();
    Vec3d pos = new Vec3d(this.grabbedEntity.getPosX() - lookVec.x * distance, this.grabbedEntity.getPosY(), this.grabbedEntity.getPosZ() - lookVec.z * distance);
    player.setPositionAndUpdate(pos.getX(), player.getPosY(), pos.getZ());
  }

  
  private boolean onEndChargingEvent(PlayerEntity player) {
    if (this.grabbedEntity == null || getChargeTime() >= getMaxChargeTime()) {
      return false;
    }
    this.grabbedEntity.attackEntityFrom((DamageSource)ModDamageSource.causeAbilityDamage((LivingEntity)player, (Ability)this, "player"), 6.0F);
    
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)player, player.world, this.grabbedEntity.getPosX(), this.grabbedEntity.getPosY(), this.grabbedEntity.getPosZ(), 1.0F);
    explosion.setStaticDamage(6.0F);
    explosion.setExplosionSound(true);
    explosion.setDamageOwner(false);
    explosion.setDestroyBlocks(false);
    explosion.setFireAfterExplosion(false);
    explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(1));
    explosion.setDamageEntities(true);
    explosion.setDamageSource((DamageSource)ModDamageSource.causeAbilityDamage((LivingEntity)player, (Ability)this, "player"));
    explosion.doExplosion();
    
    return true;
  }


  
  public IAnimation<?> getAnimation() {
    return PointBothArmsAnimation.INSTANCE;
  }


  
  public boolean isAnimationActive() {
    return isCharging();
  }
}


