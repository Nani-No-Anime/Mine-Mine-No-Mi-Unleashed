package xyz.pixelatedw.mineminenomi.abilities.hie;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.RayTraceResult;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.hie.IceBlockAvalancheProjectile;
import xyz.pixelatedw.mineminenomi.particles.effects.hie.IceBlockAvalancheParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

public class IceBlockAvalancheAbility extends ChargeableAbility {
  public static final IceBlockAvalancheAbility INSTANCE = new IceBlockAvalancheAbility();
  private IceBlockAvalancheProjectile proj;
  public static final IceBlockAvalancheParticleEffect PARTICLES = new IceBlockAvalancheParticleEffect();

  
  public IceBlockAvalancheAbility() {
    super("Ice Block: Avalanche", AbilityHelper.getDevilFruitCategory());
    setDescription("Makes a big ice ball drop onto the spot the user is aiming at");
    setMaxCooldown(18.0D);
    setMaxChargeTime(5.0D);
    this.onStartChargingEvent = this::onStartChargingEvent;
    this.duringChargingEvent = this::duringChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
    setCancelable();
  }

  
  private boolean onStartChargingEvent(PlayerEntity player) {
    RayTraceResult ray = WyHelper.rayTraceBlocksAndEntities((Entity)player, 64.0D);
    removeDuplicate();
    this.proj = new IceBlockAvalancheProjectile(player.world, (LivingEntity)player);
    this.proj.setPosition(ray.getHitVec().getX(), ray.getHitVec().getY() + 20.0D, ray.getHitVec().getZ());
    this.proj.setMotion(0.0D, 0.0D, 0.0D);
    player.world.addEntity((Entity)this.proj);
    return true;
  }

  
  private void duringChargingEvent(PlayerEntity player, int ticks) {
    if (player != null && this.proj != null) {
      PARTICLES.spawn(player.world, this.proj.getPosX(), this.proj.getPosY(), this.proj.getPosZ(), 0.0D, 0.0D, 0.0D);
    }
  }
  
  private boolean onEndChargingEvent(PlayerEntity player) {
    if (this.proj == null)
      return false; 
    this.proj.finalized = true;
    this.proj.setMotion(0.0D, -2.0D, 0.0D);
    return true;
  }

  
  private void removeDuplicate() {
    if (this.proj != null)
    {
      if (this.proj.isAddedToWorld())
      {
        this.proj.remove();
      }
    }
  }
}


