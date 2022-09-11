package xyz.pixelatedw.mineminenomi.abilities.gomu;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.GomuHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoBazookaProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoGrizzlyMagnumProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoJetBazookaProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoLeoBazookaProjectile;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.renderers.animations.gomu.GomuBazookaAnimation;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class GomuGomuNoBazookaAbility extends ChargeableAbility implements IAnimatedAbility {
  public static final GomuGomuNoBazookaAbility INSTANCE = new GomuGomuNoBazookaAbility();

  
  public GomuGomuNoBazookaAbility() {
    super("Gomu Gomu no Bazooka", AbilityHelper.getDevilFruitCategory());
    setDescription("Hits the enemy with both hands to launch them away");
    setMaxCooldown(10.0D);
    setMaxChargeTime(2.0D);
    this.onEndChargingEvent = this::onEndChargingEvent;
  }
  
  private boolean onEndChargingEvent(PlayerEntity player) {
    
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    AbilityProjectileEntity projectile1 = null;
    AbilityProjectileEntity projectile2 = null;
    float speed = 2.0F;
    double spacingMod = 1.0D;
    
    if (GomuHelper.hasGearFourthActive(props)) {
      
      GomuGomuNoLeoBazookaProjectile gomuGomuNoLeoBazookaProjectile1 = new GomuGomuNoLeoBazookaProjectile(player.world, (LivingEntity)player);
      gomuGomuNoLeoBazookaProjectile1.setCollisionSize(2.5D);
      GomuGomuNoLeoBazookaProjectile gomuGomuNoLeoBazookaProjectile2 = new GomuGomuNoLeoBazookaProjectile(player.world, (LivingEntity)player);
      gomuGomuNoLeoBazookaProjectile2.setCollisionSize(2.5D);
      setMaxCooldown(12.0D);
      setDisplayName("Gomu Gomu no Leo Bazooka");
      speed = 3.0F;
      spacingMod = 2.5D;
                projectile1 = gomuGomuNoLeoBazookaProjectile1;
                projectile2 = gomuGomuNoLeoBazookaProjectile2;
    }
    else if (GomuHelper.hasGearThirdActive(props)) {
      
      GomuGomuNoGrizzlyMagnumProjectile gomuGomuNoGrizzlyMagnumProjectile1 = new GomuGomuNoGrizzlyMagnumProjectile(player.world, (LivingEntity)player);
      gomuGomuNoGrizzlyMagnumProjectile1.setCollisionSize(2.5D);
      GomuGomuNoGrizzlyMagnumProjectile gomuGomuNoGrizzlyMagnumProjectile2 = new GomuGomuNoGrizzlyMagnumProjectile(player.world, (LivingEntity)player);
      gomuGomuNoGrizzlyMagnumProjectile2.setCollisionSize(2.5D);
      setMaxCooldown(15.0D);
      setDisplayName("Gomu Gomu no Grizzly Magnum");
      speed = 1.8F;
      spacingMod = 2.5D;
                projectile1 = gomuGomuNoGrizzlyMagnumProjectile1;
                projectile2 = gomuGomuNoGrizzlyMagnumProjectile2;
    }
    else if (GomuHelper.hasGearSecondActive(props)) {
      
      GomuGomuNoJetBazookaProjectile gomuGomuNoJetBazookaProjectile1 = new GomuGomuNoJetBazookaProjectile(player.world, (LivingEntity)player);
      GomuGomuNoJetBazookaProjectile gomuGomuNoJetBazookaProjectile2 = new GomuGomuNoJetBazookaProjectile(player.world, (LivingEntity)player);
      setMaxCooldown(7.0D);
      setDisplayName("Gomu Gomu no Jet Bazooka");
      speed = 3.0F;
                projectile1 = gomuGomuNoJetBazookaProjectile1;
                projectile2 = gomuGomuNoJetBazookaProjectile2;
    }
    else {
       
      GomuGomuNoBazookaProjectile gomuGomuNoBazookaProjectile1 = new GomuGomuNoBazookaProjectile(player.world, (LivingEntity)player);
      GomuGomuNoBazookaProjectile gomuGomuNoBazookaProjectile2 = new GomuGomuNoBazookaProjectile(player.world, (LivingEntity)player);
      setDisplayName("Gomu Gomu no Bazooka");
      setMaxCooldown(10.0D);
                projectile1 = gomuGomuNoBazookaProjectile1;
                projectile2 = gomuGomuNoBazookaProjectile2;
    } 
    
    Vec3d dirVec = Vec3d.ZERO;
    Direction dir = Direction.fromAngle(player.rotationYaw);
    dirVec = dirVec.add(Math.abs(dir.getDirectionVec().getX()), Math.abs(dir.getDirectionVec().getY()), Math.abs(dir.getDirectionVec().getZ())).mul(spacingMod, 1.0D, spacingMod);
    
    double yPos = player.getPosY() + player.getEyeHeight() - 0.7D;
    projectile1.setLocationAndAngles(player.getPosX() + dirVec.z, yPos, player.getPosZ() + dirVec.x, 0.0F, 0.0F);
    projectile2.setLocationAndAngles(player.getPosX() - dirVec.z, yPos, player.getPosZ() - dirVec.x, 0.0F, 0.0F);
    
    player.world.addEntity((Entity)projectile1);
    player.world.addEntity((Entity)projectile2);
    projectile1.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, speed, 0.0F);
    projectile2.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, speed, 0.0F);
    player.world.playSound(null, player.getPosition(), ModSounds.GOMU_SFX, SoundCategory.PLAYERS, 0.8F, (float)MathHelper.clamp(player.getRNG().nextDouble() * 2.0D, 1.0D, 1.2999999523162842D));
    
    return true;
  }


  
  public IAnimation getAnimation() {
    return (IAnimation)GomuBazookaAnimation.INSTANCE;
  }


  
  public boolean isAnimationActive() {
    return isCharging();
  }
}


