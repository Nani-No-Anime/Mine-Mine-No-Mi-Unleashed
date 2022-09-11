package xyz.pixelatedw.mineminenomi.abilities.yami;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.yami.BlackRoadProjectile;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.yami.DarkMatterChargingParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

public class BlackRoadAbility extends ChargeableAbility {
  public static final BlackRoadAbility INSTANCE = new BlackRoadAbility();
  
  private static final ParticleEffect PARTICLES = (ParticleEffect)new DarkMatterChargingParticleEffect();

  
  public BlackRoadAbility() {
    super("Black Road", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(20.0D);
    setMaxChargeTime(6.0D);
    setCancelable();
    setDescription("The user spreads darkness in a forward path");
    
    this.duringChargingEvent = this::duringChargingEvent;
    this.onEndChargingEvent = this::endChargingEvent;
  }

  
  private void duringChargingEvent(PlayerEntity player, int chargeTime) {
    if (chargeTime % 2 == 0) {
      PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    }
  }
  
  private boolean endChargingEvent(PlayerEntity player) {
    float time = getChargeTime() / getMaxChargeTime();
    float multiplier = 1.0F - time;
    int range = (int)(24.0F * multiplier);
    
    BlackRoadProjectile proj = new BlackRoadProjectile(player.world, (LivingEntity)player);
    proj.setMaxLife(range);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 1.1F, 5.0F);
    
    PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    
    return true;
  }
}


