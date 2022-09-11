package xyz.pixelatedw.mineminenomi.abilities.beta;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.beta.HanamizuShinkenShirahadoriEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;

public class HanamizuShinkenAbility
  extends ContinuousAbility
{
  public static final HanamizuShinkenAbility INSTANCE = new HanamizuShinkenAbility();

  
  private ParticleEffect particles = (ParticleEffect)new HanamizuShinkenShirahadoriEffect();
  public HanamizuShinkenAbility() {
    super("Hanamizu Shinken Shirahadori", AbilityHelper.getDevilFruitCategory());
    setThreshold(3.0D);
    setDescription("Creates a protecting wall of mucus, protecting the user from attacks");
    
    this.duringContinuityEvent = this::duringContinuity;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }
  
  private void duringContinuity(PlayerEntity player, int passiveTimer) {
    player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 0, false, false));
    
    int range = 2;
    double boxSize = 1.2D;
    for (int i = 0; i < range * 2; i++) {
      double distance = i / 2.0D;
      Vec3d lookVec = player.getLookVec();
      Vec3d pos = new Vec3d(player.getPosX() + lookVec.x * distance, player.getPosY() + player.getEyeHeight() + lookVec.y * distance, player.getPosZ() + lookVec.z * distance);
      List<Entity> list = player.world.getEntitiesInAABBexcluding((Entity)player, new AxisAlignedBB(pos.x - boxSize, pos.y - boxSize, pos.z - boxSize, pos.x + boxSize, pos.y + boxSize * 2.0D, pos.z + boxSize), entity -> (entity != player));
      
      for (Entity e : list) {
        if (e instanceof LivingEntity) {
          e.attackEntityFrom(DamageSource.causePlayerDamage(player), 4.0F);
          ((LivingEntity)e).addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200, 4));
          Vec3d speed = WyHelper.propulsion((LivingEntity)player, 3.0D, 3.0D);
          e.setMotion(speed.x, 0.5D, speed.z);
          e.velocityChanged = true; continue;
        }  if (e instanceof net.minecraft.entity.projectile.ThrowableEntity || e instanceof net.minecraft.entity.projectile.AbstractArrowEntity) {
          e.setMotion(-(e.getMotion()).x, (e.getMotion()).y, -(e.getMotion()).x);
        }
      } 
    } 
    EntityRayTraceResult trace = WyHelper.rayTraceEntities((Entity)player, range);
    if (passiveTimer % 2 == 0)
      this.particles.spawn(player.world, trace.getHitVec().getX(), player.getPosY(), trace.getHitVec().getZ(), 0.0D, 0.0D, 0.0D); 
  }
  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    int cooldown = 2 + (int)Math.round(this.continueTime / 20.0D);
    setMaxCooldown(cooldown);
    return true;
  }
}


