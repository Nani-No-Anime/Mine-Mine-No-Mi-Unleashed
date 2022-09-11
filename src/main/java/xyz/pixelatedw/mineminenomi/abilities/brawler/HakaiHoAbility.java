package xyz.pixelatedw.mineminenomi.abilities.brawler;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class HakaiHoAbility extends PunchAbility implements IMultiTargetAbility {
  public static final HakaiHoAbility INSTANCE = new HakaiHoAbility();

  
  public HakaiHoAbility() {
    super("Hakai Ho", AbilityHelper.getStyleCategory());
    setDescription("The user punches with enough force to create an explosion");
    setMaxCooldown(10.0D);
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.onHitEntityEvent = this::onHitEntity;
    this.onHitEffectEvent = this::onHitEffectEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    if (!AbilityHelper.canUseBrawlerAbilities((LivingEntity)player)) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_FIST, new Object[0]));
      return false;
    } 
    
    return true;
  }

  
  private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
    clearTargets();
    
    List<LivingEntity> targets = WyHelper.getEntitiesNear(target.getPosition(), target.world, 2.0D);
    targets.remove(player);
    
    for (LivingEntity aoeTarget : targets) {
      
      if (isTarget(aoeTarget)) {
        
        aoeTarget.attackEntityFrom((DamageSource)ModDamageSource.causeAbilityDamage((LivingEntity)player, (Ability)this, "player"), 10.0F);
        aoeTarget.addPotionEffect(new EffectInstance(ModEffects.DIZZY, 100, 0, false, false));
        Vec3d speed = WyHelper.propulsion((LivingEntity)player, 3.0D, 3.0D);
        aoeTarget.setMotion(speed.x, 0.5D, speed.z);
        aoeTarget.velocityChanged = true;
      } 
    } 
    
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)target, target.world, target.getPosX(), target.getPosY(), target.getPosZ(), 2.0F);
    explosion.setStaticDamage(15.0F);
    explosion.setDestroyBlocks(false);
    explosion.setDamageEntities(false);
    explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
    explosion.doExplosion();
  }


  
  private float onHitEntity(PlayerEntity player, LivingEntity target) {
    return 15.0F;
  }
}


