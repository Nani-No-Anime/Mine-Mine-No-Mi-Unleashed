package xyz.pixelatedw.mineminenomi.abilities.gura;


import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.GroundParticlesEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

public class TenchiMeidoAbility extends ChargeableAbility {
  public static final Ability INSTANCE = (Ability)new TenchiMeidoAbility();
  
  private static final ParticleEffect PARTICLES = (ParticleEffect)new GroundParticlesEffect(20, 200);

  
  public TenchiMeidoAbility() {
    super("Tenchi Meido", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(20.0D);
    setMaxChargeTime(1.0D);
    setDescription("The user grabs the air and pulls it downwards, after which all of the opponents are tossed into the air");
    
    this.duringChargingEvent = this::duringChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
  }

  
  private void duringChargingEvent(PlayerEntity player, int chargeTime) {
    if (chargeTime % 2 == 0) {
      PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    }
    player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 10, 0));
    
    List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 26.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
    targets.remove(player);
    
    targets.stream()
      .filter(target -> 
        (target != null && target.canEntityBeSeen((Entity)player) && target.isAlive()))

      
      .forEach(target -> target.addPotionEffect(new EffectInstance(ModEffects.DIZZY, 10, 0, false, false)));
  }


  
  private boolean onEndChargingEvent(PlayerEntity player) {
    List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 26.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
    targets.remove(player);
    
    targets.stream().filter(target -> (target != null && target.isAlive())).forEach(target -> {
          Vec3d dirVec = player.getPositionVec().subtract(target.getPositionVec()).normalize().mul(15.0D, 1.0D, 15.0D);
          
          target.setMotion(-dirVec.x, 3.0D, -dirVec.z);
          
          target.velocityChanged = true;
        });
    return true;
  }
}


