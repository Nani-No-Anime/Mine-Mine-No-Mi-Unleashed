package xyz.pixelatedw.mineminenomi.abilities.yami;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.yami.DarkMatterProjectile;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.yami.DarkMatterChargingParticleEffect;
import xyz.pixelatedw.mineminenomi.renderers.animations.RaiseArmAnimation;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

public class DarkMatterAbility extends ChargeableAbility implements IAnimatedAbility {
  public static final DarkMatterAbility INSTANCE = new DarkMatterAbility();
  private static final ParticleEffect PARTICLES = (ParticleEffect)new DarkMatterChargingParticleEffect();
  
  private DarkMatterProjectile proj = null;

  
  public DarkMatterAbility() {
    super("Dark Matter", AbilityHelper.getDevilFruitCategory());
    setDescription("Launches a ball of darkness that engulfs the opponent");
    setMaxCooldown(14.0D);
    setMaxChargeTime(4.0D);
    
    this.duringChargingEvent = this::duringChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
  }

  
  private void duringChargingEvent(PlayerEntity player, int chargeTime) {
    if (this.proj == null) {
      
      this.proj = new DarkMatterProjectile(player.world, (LivingEntity)player);
      player.world.addEntity((Entity)this.proj);
    }
    else {
      
      this.proj.setLife(this.proj.getMaxLife());
      this.proj.increaseSize();
      this.proj.setPosition(player.getPosX(), player.getPosYEye() + 3.0D, player.getPosZ());
    } 
    
    if (chargeTime % 2 == 0) {
      PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    }
  }
  
  private boolean onEndChargingEvent(PlayerEntity player) {
    this.proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 1.0F);
    player.world.playSound(null, player.getPosition(), ModSounds.MERA_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
    
    this.proj = null;
    
    return true;
  }


  
  public IAnimation getAnimation() {
    return (IAnimation)RaiseArmAnimation.INSTANCE;
  }


  
  public boolean isAnimationActive() {
    return isCharging();
  }
}


