package xyz.pixelatedw.mineminenomi.abilities.buki;

import java.util.Arrays;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg.FreshFireProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.CannonBallProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchTriggerAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;

public class BukimorphoseAbility extends PunchTriggerAbility {
  public static final BukimorphoseAbility INSTANCE = new BukimorphoseAbility();
  private Mode mode = Mode.SWORD;
  boolean released = false;
  boolean exploded;
  
  public BukimorphoseAbility() {
    super("Bukimorphose", AbilityHelper.getDevilFruitCategory());










    
    this.exploded = false; this.onStartContinuityEvent = this::onStartContinuity; this.duringContinuityEvent = this::duringContinuityEvent; this.onEndContinuityEvent = this::onEndContinuityEvent;
    stopAfterUsage(false);
    this.onSwingEvent = this::onSwingEvent;
    this.duringCooldownEvent = this::duringCooldownEvent; } private void duringCooldownEvent(PlayerEntity player, int i) { if (this.mode == Mode.MISSILE && !this.exploded) {
      
      float maxSpeed = 4.0F;
      Vec3d vec = player.getLookVec();
      player.setMotion(vec.x * maxSpeed, vec.y * maxSpeed, vec.z * maxSpeed);
      player.velocityChanged = true;
      
      if (player.collided) {
        
        ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)player, player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 6.0F);
        explosion.setExplosionSound(true);
        explosion.setDamageOwner(false);
        explosion.setDestroyBlocks(true);
        explosion.setFireAfterExplosion(true);
        explosion.setStaticDamage(100.0F);
        explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(6));
        explosion.setDamageEntities(true);
        explosion.doExplosion();
        this.exploded = true;
      } 
    }  }

  
  private boolean onStartContinuity(PlayerEntity player) {
    this.released = false;
    return true;
  }

  
  private boolean onSwingEvent(PlayerEntity player) {
    stopAfterUsage(false);
    if (this.mode == Mode.FIRE) {
      
      FreshFireProjectile proj = new FreshFireProjectile(player.world, (LivingEntity)player);
      player.world.addEntity((Entity)proj);
      proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 10.0F);
    } else if (this.mode == Mode.GATLING) {
      
      NormalBulletProjectile normalBulletProjectile = new NormalBulletProjectile(player.world, (LivingEntity)player);
      player.world.addEntity((Entity)normalBulletProjectile);
      normalBulletProjectile.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 2.0F);
    } else if (this.mode == Mode.CANNON) {
      
      stopAfterUsage(true);
      CannonBallProjectile cannonBallProjectile = new CannonBallProjectile(player.world, (LivingEntity)player);
      setMaxCooldown(2.0D);
      player.world.addEntity((Entity)cannonBallProjectile);
      cannonBallProjectile.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 2.0F);
    } else if (this.mode == Mode.MISSILE) {
      
      stopAfterUsage(true);
      setMaxCooldown(8.0D);
      this.exploded = false;
    } 
    
    return true;
  }

  
  private void duringContinuityEvent(PlayerEntity player, int i) {
    if (i % (player.isSneaking() ? 10 : 40) == 0 && !this.released) {
      
      this.mode = this.mode.getNext();
      player.sendMessage((ITextComponent)new TranslationTextComponent("Ability mode set to: " + this.mode, new Object[0]));
      IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
      WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), props), player);
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, (Ability)this), (LivingEntity)player);
    } 
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    if (!this.released) {
      
      this.released = true;
      return false;
    } 
    
    return true;
  }
  
  public enum Mode
  {
    SWORD,
    CANNON,
    GATLING,
    FIRE,
    MISSILE;

    
    public Mode getNext() {
      return (ordinal() == Arrays.<Mode>stream(values()).count() - 1L) ? SWORD : values()[ordinal() + 1];
    }
  }
}


