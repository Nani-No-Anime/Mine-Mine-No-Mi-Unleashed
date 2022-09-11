package xyz.pixelatedw.mineminenomi.abilities.goro;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.RayTraceResult;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.RaigoProjectile;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.goro.RaigoParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

public class RaigoAbility extends ChargeableAbility {
  public static final Ability INSTANCE = (Ability)new RaigoAbility();
  
  private static final ParticleEffect PARTICLES = (ParticleEffect)new RaigoParticleEffect();
  
  private double posX;
  
  private double posY;
  private double posZ;
  
  public RaigoAbility() {
    super("Raigo", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(70.0D);
    setMaxChargeTime(5.0D);
    setDescription("Creates a huge cloud filled with electricity, which drops a massive lighting ball onto enemies");
    
    this.onStartChargingEvent = this::onStartChargingEvent;
    this.duringChargingEvent = this::duringChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
  }

  
  private boolean onStartChargingEvent(PlayerEntity player) {
    RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player);
    
    double x = (mop.getHitVec()).x;
    double y = (mop.getHitVec()).y;
    double z = (mop.getHitVec()).z;
    
    this.posX = x;
    this.posY = y;
    this.posZ = z;
    
    return true;
  }

  
  private boolean onEndChargingEvent(PlayerEntity player) {
    RaigoProjectile raigoProjectile = new RaigoProjectile(player.world, (LivingEntity)player);
    raigoProjectile.setLocationAndAngles(this.posX, this.posY + 90.0D, this.posZ, 0.0F, 0.0F);
    raigoProjectile.setMotion(0.0D, -2.0D, 0.0D);
    player.world.addEntity((Entity)raigoProjectile);
    
    return true;
  }

  
  private void duringChargingEvent(PlayerEntity player, int chargeTime) {
    if (chargeTime % 10 == 0)
      PARTICLES.spawn(player.world, this.posX, this.posY + 40.0D, this.posZ, 0.0D, 0.0D, 0.0D); 
  }
}


