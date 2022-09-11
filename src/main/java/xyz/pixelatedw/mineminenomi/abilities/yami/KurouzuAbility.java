package xyz.pixelatedw.mineminenomi.abilities.yami;

import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.yami.KurouzuChargeParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.yami.KurouzuParticleEffect;
import xyz.pixelatedw.mineminenomi.renderers.animations.PointArmAnimation;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

public class KurouzuAbility
  extends ChargeableAbility implements IAnimatedAbility {
  public static final KurouzuAbility INSTANCE = new KurouzuAbility();
  
  private static final ParticleEffect PARTICLES = (ParticleEffect)new KurouzuParticleEffect();
  private static final KurouzuChargeParticleEffect PARTICLES_CHARGE = new KurouzuChargeParticleEffect();
  private List<LivingEntity> entities = new ArrayList<>();

  
  public KurouzuAbility() {
    super("Kurouzu", AbilityHelper.getDevilFruitCategory());
    setDescription("Creates a strong gravitational force, that pulls the opponent towards the user");
    setMaxCooldown(12.0D);
    setMaxChargeTime(3.0D);
    setCancelable();
    
    this.duringChargingEvent = this::duringChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
  }


  
  private void duringChargingEvent(PlayerEntity player, int chargeTimer) {
    RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player, 100.0D);
    
    double i = (mop.getHitVec()).x;
    double j = (mop.getHitVec()).y - ((mop instanceof net.minecraft.util.math.EntityRayTraceResult) ? 1 : 0);
    double k = (mop.getHitVec()).z;
    
    if (chargeTimer % 5 == 0) {
      PARTICLES.spawn(player.world, i, j, k, 0.0D, 0.0D, 0.0D);
    }
    if (chargeTimer % 5 == 0) {
      
      Vec3d lookVec = player.getLookVec();
      Vec3d particlePos = player.getPositionVec().add(lookVec.x, lookVec.y + 1.5D, lookVec.z);
      PARTICLES_CHARGE.spawn(player.world, particlePos.getX(), particlePos.getY(), particlePos.getZ(), 0.0D, 0.0D, 0.0D);
    } 
    
    this.entities.clear();
    List<LivingEntity> targets = WyHelper.getEntitiesNear(new BlockPos(i, j, k), player.world, 5.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
    targets.remove(player);
    
    this.entities.addAll(targets);
  }

  
  private boolean onEndChargingEvent(PlayerEntity player) {
    for (LivingEntity target : this.entities) {
      
      target.setPositionAndUpdate(player.getPosX(), player.getPosY(), player.getPosZ());
      target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200, 5));
      target.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 200, 5));
      target.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 200, 5));
      target.addPotionEffect(new EffectInstance(ModEffects.ABILITY_OFF, 200, 1, false, false, false));
    } 
    
    return true;
  }


  
  public boolean isAnimationActive() {
    return isCharging();
  }


  
  public IAnimation getAnimation() {
    return (IAnimation)PointArmAnimation.INSTANCE;
  }
}


