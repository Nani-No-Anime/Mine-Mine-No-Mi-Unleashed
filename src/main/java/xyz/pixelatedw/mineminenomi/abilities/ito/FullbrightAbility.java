package xyz.pixelatedw.mineminenomi.abilities.ito;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.RayTraceResult;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.ito.StringPillarProjectile;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.renderers.animations.RaiseArmAnimation;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

public class FullbrightAbility extends ChargeableAbility implements IAnimatedAbility {
  public static final FullbrightAbility INSTANCE = new FullbrightAbility();

  
  public FullbrightAbility() {
    super("Fullbright", AbilityHelper.getDevilFruitCategory());
    setDescription("Throws five strings to impale a target from above.");
    setMaxCooldown(12.0D);
    setMaxChargeTime(1.0D);
    this.onStartChargingEvent = this::onStartCharging;
    this.onEndChargingEvent = this::onEndChargingEvent;
  }

  
  private boolean onStartCharging(PlayerEntity player) {
    player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 20, 1, false, false));
    StringPillarProjectile pillar = new StringPillarProjectile(player.world, (LivingEntity)player);
    pillar.rotationPitch = 90.0F;
    pillar.setMaxLife(20);
    pillar.setPosition(player.getPosX(), player.getPosY(), player.getPosZ());
    pillar.setMotion(0.0D, 0.0D, 0.0D);
    player.world.addEntity((Entity)pillar);
    return true;
  }

  
  private boolean onEndChargingEvent(PlayerEntity player) {
    for (int a = 0; a < 5; a++) {
      
      RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player, 32.0D);
      
      double i = (mop.getHitVec()).x + Math.random() * 1.3D;
      double j = (mop.getHitVec()).y;
      double k = (mop.getHitVec()).z + Math.random() * 1.3D;
      
      StringPillarProjectile pillar = new StringPillarProjectile(player.world, (LivingEntity)player);
      pillar.rotationPitch = 90.0F;
      pillar.setPosition(i, j + 24.0D, k);
      pillar.setMotion(0.0D, -1.5D, 0.0D);
      player.world.addEntity((Entity)pillar);
    } 
    
    return true;
  }

  
  public IAnimation getAnimation() {
    return (IAnimation)RaiseArmAnimation.INSTANCE;
  }

  
  public boolean isAnimationActive() {
    return isCharging();
  }
}


