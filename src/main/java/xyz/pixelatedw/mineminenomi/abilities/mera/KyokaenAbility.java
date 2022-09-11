package xyz.pixelatedw.mineminenomi.abilities.mera;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.particles.effects.mera.KyokaenParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.events.SetOnFireEvent;

public class KyokaenAbility
  extends ContinuousAbility
{
  public static final KyokaenAbility INSTANCE = new KyokaenAbility();
  private static final KyokaenParticleEffect PARTICLES = new KyokaenParticleEffect();

  
  public KyokaenAbility() {
    super("Kyokaen", AbilityHelper.getDevilFruitCategory());
    setDescription("Creates a wall of fire protecting the user");
    setThreshold(5.0D);
    
    this.duringContinuityEvent = this::duringContinuity;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }
  
  private void duringContinuity(PlayerEntity player, int passiveTimer) {
    int range = 2;
    double boxSize = 1.1D;
    for (int i = 0; i < range * 2; i++) {
      double distance = i / 2.0D;
      Vec3d lookVec = player.getLookVec();
      Vec3d pos = new Vec3d(player.getPosX() + lookVec.x * distance, player.getPosY() + player.getEyeHeight() + lookVec.y * distance, player.getPosZ() + lookVec.z * distance);
      List<Entity> list = player.world.getEntitiesInAABBexcluding((Entity)player, new AxisAlignedBB(pos.x - boxSize, pos.y - boxSize, pos.z - boxSize, pos.x + boxSize, pos.y + boxSize * 2.0D, pos.z + boxSize), entity -> (entity != player));
      
      for (Entity e : list) {
        if (e instanceof LivingEntity) {
          if (!e.isBurning()) {
            SetOnFireEvent event = new SetOnFireEvent((LivingEntity)player, (LivingEntity)e, 3);
            if (!MinecraftForge.EVENT_BUS.post(event))
              e.setFire(3); 
            e.attackEntityFrom((DamageSource)ModDamageSource.FIRE.getSource(), 3.0F);
          } 
          Vec3d speed = WyHelper.propulsion((LivingEntity)player, 3.0D, 3.0D);
          e.setMotion(speed.x, 0.5D, speed.z);
          e.velocityChanged = true; continue;
        } 
        if (e instanceof net.minecraft.entity.projectile.AbstractArrowEntity || e instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.extra.KairosekiBulletProjectile || e instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile) {
          e.remove(); continue;
        }  if (e instanceof net.minecraft.entity.projectile.ThrowableEntity) {
          e.setMotion(-(e.getMotion()).x * 1.350000023841858D, (e.getMotion()).y, -(e.getMotion()).x * 1.350000023841858D);
        }
      } 
    } 
    
    EntityRayTraceResult trace = WyHelper.rayTraceEntities((Entity)player, range);
    if (passiveTimer % 2 == 0) {
      PARTICLES.spawn(player.world, trace.getHitVec().getX(), player.getPosY(), trace.getHitVec().getZ(), 0.0D, 0.0D, 0.0D);
    }
  }
  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    int cooldown = 2 + (int)Math.round(this.continueTime / 20.0D);
    setMaxCooldown(cooldown);
    return true;
  }
}


