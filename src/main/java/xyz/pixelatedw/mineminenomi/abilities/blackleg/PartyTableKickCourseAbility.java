package xyz.pixelatedw.mineminenomi.abilities.blackleg;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.animations.TimeAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.particles.effects.blackleg.PartyTableKickCourseParticleEffect;
import xyz.pixelatedw.mineminenomi.renderers.animations.blackleg.PartyTableKickCourseAnimation;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

public class PartyTableKickCourseAbility extends ContinuousAbility implements IMultiTargetAbility, IAnimatedAbility {
  public static final PartyTableKickCourseAbility INSTANCE = new PartyTableKickCourseAbility();
  
  private static final PartyTableKickCourseParticleEffect PARTICLES = new PartyTableKickCourseParticleEffect();

  
  public PartyTableKickCourseAbility() {
    super("Party-Table Kick Course", AbilityHelper.getStyleCategory());
    setMaxCooldown(12.0D);
    setThreshold(1.0D);
    setDescription("The user does a hand stand on the ground, legs spread out spinning and dealing damage to all nearby enemies");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuityEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    getAnimation().start();
    return true;
  }

  
  private boolean duringContinuityEvent(PlayerEntity player, int time) {
    clearTargets();
    
    player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1));
    
    List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 2.5D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
    list.remove(player);
    
    list.forEach(entity -> {
          if (isTarget(entity)) {
            boolean hit = entity.attackEntityFrom((DamageSource)ModDamageSource.causeAbilityDamage((LivingEntity)player, (Ability)this, "player"), 25.0F);
            
            if (hit) {
              Vec3d speed = WyHelper.propulsion((LivingEntity)player, 1.5D, 1.5D);
              
              entity.setMotion(speed.x, 1.5D, speed.z);
              
              entity.velocityChanged = true;
            } 
          } 
        });
    
    if (list.size() > 0) {
      ((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf((Entity)player, (IPacket)new SAnimateHandPacket((Entity)player, 0));
    }
    DiableJambeAbility diableJambeAbility = (DiableJambeAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)DiableJambeAbility.INSTANCE);
    boolean isAbilityEnabled = (diableJambeAbility != null && diableJambeAbility.isContinuous());
    if (isAbilityEnabled) {
      PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    }
    return true;
  }


  
  public TimeAnimation getAnimation() {
    return (TimeAnimation)PartyTableKickCourseAnimation.INSTANCE;
  }


  
  public boolean isAnimationActive() {
    return isContinuous();
  }
}


