package xyz.pixelatedw.mineminenomi.abilities.supa;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.baku.BakuMunchParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class SparklingDaisyAbility
  extends Ability implements IMultiTargetAbility {
  public static final SparklingDaisyAbility INSTANCE = new SparklingDaisyAbility();
  
  private static final ParticleEffect PARTICLES = (ParticleEffect)new BakuMunchParticleEffect();
  
  private int initialY;

  
  public SparklingDaisyAbility() {
    super("Sparkling Daisy", AbilityHelper.getDevilFruitCategory());
    setDescription("Launches the user forward, slicing anything in their path");
    setMaxCooldown(7.0D);
    
    this.onUseEvent = this::onUseEvent;
    this.duringCooldownEvent = this::duringCooldownEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    if (!AbilityHelper.canUseMomentumAbility(player)) {
      return false;
    }
    clearTargets();
    this.initialY = (int)player.getPosY();
    double[] motion = AbilityHelper.propulsion((LivingEntity)player, 3.0D, 3.0D);
    player.setMotion(motion[0], (player.getMotion()).y, motion[1]);
    player.velocityChanged = true;
    
    return true;
  }

  
  private void duringCooldownEvent(PlayerEntity player, int cooldownTimer) {
    if (getCooldown() > getMaxCooldown() * 0.9D && player.getPosY() >= this.initialY) {
      
      List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.6D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
      list.remove(player);
      list.forEach(entity -> {
            if (isTarget(entity)) {
              entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 25.0F);
            }
          });
      
      for (BlockPos location : WyHelper.getNearbyBlocks((LivingEntity)player, 3)) {
        
        if (location.getY() >= player.getPosY())
        {
          if (AbilityHelper.placeBlockIfAllowed(player.world, location.getX(), location.getY(), location.getZ(), Blocks.AIR, DefaultProtectionRules.CORE_FOLIAGE_ORE))
          {
            PARTICLES.spawn(player.world, location.getX(), location.getY(), location.getZ(), 0.0D, 0.0D, 0.0D);
          }
        }
      } 
    } 
  }
}


