package xyz.pixelatedw.mineminenomi.abilities.nikyu;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.renderers.animations.PointArmAnimation;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;

public class PuniAbility
  extends ContinuousAbility
  implements IAnimatedAbility {
  public static final PuniAbility INSTANCE = new PuniAbility();

  
  public PuniAbility() {
    super("Puni", AbilityHelper.getDevilFruitCategory());
    setDescription("The user takes a defensive posture that uses their paw pads to repel and counter enemy attacks");
    setThreshold(2.0D);
    setMaxCooldown(4.0D);
    
    this.onStartContinuityEvent = this::onStartContiunityEvent;
    this.duringContinuityEvent = this::duringContinuity;
  }

  
  private boolean onStartContiunityEvent(PlayerEntity player) {
    return player.getHeldItemMainhand().isEmpty();
  }

  
  private void duringContinuity(PlayerEntity player, int passiveTimer) {
    if (!player.getHeldItemMainhand().isEmpty()) {
      endContinuity(player);
    }
    player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1, false, false));
    
    int range = 3;
    double boxSize = 0.85D;
    for (int i = 0; i < range * 2; i++) {
      
      double distance = i / 2.0D;
      Vec3d lookVec = player.getLookVec();
      Vec3d pos = new Vec3d(player.getPosX() + lookVec.x * distance, player.getPosY() + player.getEyeHeight() + lookVec.y * distance, player.getPosZ() + lookVec.z * distance);
      List<Entity> list = player.world.getEntitiesInAABBexcluding((Entity)player, new AxisAlignedBB(pos.x - boxSize, pos.y - boxSize, pos.z - boxSize, pos.x + boxSize, pos.y + boxSize * 2.0D, pos.z + boxSize), entity -> (entity != player));
      
      for (Entity e : list) {
        
        if (e instanceof LivingEntity) {
          
          e.attackEntityFrom(DamageSource.causePlayerDamage(player), 15.0F);
          Vec3d speed = WyHelper.propulsion((LivingEntity)player, 6.0D, 6.0D);
          e.setMotion(speed.x, 1.5D, speed.z);
          e.velocityChanged = true; continue;
        }  if (e instanceof net.minecraft.entity.projectile.ThrowableEntity) {
          e.setMotion(-(e.getMotion()).x * 3.0D, (e.getMotion()).y + 0.5D, -(e.getMotion()).x * 3.0D);
        }
      } 
    } 
  }

  
  public IAnimation getAnimation() {
    return (IAnimation)PointArmAnimation.INSTANCE;
  }


  
  public boolean isAnimationActive() {
    return isContinuous();
  }
}


