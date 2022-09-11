package xyz.pixelatedw.mineminenomi.abilities.mero;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.EntityRayTraceResult;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.mero.SlaveArrowProjectile;
import xyz.pixelatedw.mineminenomi.particles.effects.mero.SlaveArrowParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

public class SlaveArrowAbility extends ChargeableAbility {
  public static final SlaveArrowAbility INSTANCE = new SlaveArrowAbility();
  
  private static final SlaveArrowParticleEffect PARTICLES = new SlaveArrowParticleEffect();
  private int limit = 0;

  
  public SlaveArrowAbility() {
    super("Slave Arrow", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(13.0D);
    setMaxChargeTime(3.0D);
    setDescription("Creates a big heart from which the user shoots multiple love arrows, petrifying enemies");
    
    this.duringChargingEvent = this::duringChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
    this.duringCooldownEvent = this::duringCooldownEvent;
  }

  
  private void duringCooldownEvent(PlayerEntity player, int cooldown) {
    int projectileSpace = 1;
    if (this.limit > 0 && (this.cooldown - 10.0D) % 2.0D == 0.0D) {
      
      SlaveArrowProjectile proj = new SlaveArrowProjectile(player.world, (LivingEntity)player);
      proj.setLocationAndAngles(player
          .getPosX() + WyHelper.randomWithRange(-projectileSpace, projectileSpace) + WyHelper.randomDouble(), player
          .getPosY() + 0.3D + WyHelper.randomWithRange(0, projectileSpace) + WyHelper.randomDouble(), player
          .getPosZ() + WyHelper.randomWithRange(-projectileSpace, projectileSpace) + WyHelper.randomDouble(), 0.0F, 0.0F);
      
      player.world.addEntity((Entity)proj);
      proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 4.0F);
      this.limit--;
    } 
  }

  
  private void duringChargingEvent(PlayerEntity player, int chargeTime) {
    if (chargeTime > getMaxChargeTime() / 2) {
      PARTICLES.setSize(PARTICLES.getSize() + 0.2F);
    } else if (chargeTime <= getMaxChargeTime() / 2) {
      PARTICLES.setSize(PARTICLES.getSize() - 0.05F);
    } 
    EntityRayTraceResult trace = WyHelper.rayTraceEntities((Entity)player, 0.8D);
    
    if (chargeTime % 2 == 0)
      PARTICLES.spawn(player.world, trace.getHitVec().getX(), player.getPosY(), trace.getHitVec().getZ(), 0.0D, 0.0D, 0.0D); 
    player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 10, 2000));
    player.setMotion(0.0D, 0.0D, 0.0D);
    player.velocityChanged = true;
  }

  
  private boolean onEndChargingEvent(PlayerEntity player) {
    this.limit = 20;
    
    PARTICLES.setSize(5.0F);
    
    return true;
  }
}


