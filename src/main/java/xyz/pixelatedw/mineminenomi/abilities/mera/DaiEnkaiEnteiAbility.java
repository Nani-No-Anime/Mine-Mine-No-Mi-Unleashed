package xyz.pixelatedw.mineminenomi.abilities.mera;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.mera.DaiEnkaiEnteiProjectile;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.mera.DaiEnkai2ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.mera.DaiEnkaiParticleEffect;
import xyz.pixelatedw.mineminenomi.renderers.animations.RaiseArmAnimation;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

public class DaiEnkaiEnteiAbility extends ChargeableAbility implements IAnimatedAbility {
  public static final Ability INSTANCE = (Ability)new DaiEnkaiEnteiAbility();
  
  private static final ParticleEffect PARTICLES_1 = (ParticleEffect)new DaiEnkaiParticleEffect();
  private static final ParticleEffect PARTICLES_2 = (ParticleEffect)new DaiEnkai2ParticleEffect();
  
  private DaiEnkaiEnteiProjectile proj = null;

  
  public DaiEnkaiEnteiAbility() {
    super("Dai Enkai: Entei", AbilityHelper.getDevilFruitCategory());
    setDescription("Amasses the user's flames into a gigantic fireball that the user hurls at the opponent");
    setMaxCooldown(25.0D);
    setMaxChargeTime(8.0D);
    
    this.duringChargingEvent = this::duringChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
  }

  
  private void duringChargingEvent(PlayerEntity player, int chargeTime) {
    if (this.proj == null) {
      
      this.proj = new DaiEnkaiEnteiProjectile(player.world, (LivingEntity)player);
      player.world.addEntity((Entity)this.proj);
    }
    else {
      
      this.proj.setLife(this.proj.getMaxLife());
      this.proj.increaseSize();
      this.proj.setPosition(player.getPosX(), player.getPosYEye() + 3.0D, player.getPosZ());
    } 
    
    if (chargeTime % 2 == 0) {
      PARTICLES_2.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    }
  }
  
  private boolean onEndChargingEvent(PlayerEntity player) {
    this.proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 1.0F);
    player.world.playSound(null, player.getPosition(), ModSounds.MERA_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
    
    this.proj = null;
    PARTICLES_1.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    
    return true;
  }


  
  public IAnimation getAnimation() {
    return (IAnimation)RaiseArmAnimation.INSTANCE;
  }


  
  public boolean isAnimationActive() {
    return isCharging();
  }
}


