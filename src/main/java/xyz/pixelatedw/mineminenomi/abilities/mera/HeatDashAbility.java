package xyz.pixelatedw.mineminenomi.abilities.mera;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.MinecraftForge;
import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.mera.HeatDashParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.events.SetOnFireEvent;

import java.util.List;

public class HeatDashAbility extends Ability implements IMultiTargetAbility {
  public static final HeatDashAbility INSTANCE = new HeatDashAbility();
  
  private static final ParticleEffect PARTICLES = (ParticleEffect)new HeatDashParticleEffect();

  
  public HeatDashAbility() {
    super("Heat Dash", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(7.0D);
    setDescription("Transforms the user into fire and launches them forward");
    
    this.onUseEvent = this::onUseEvent;
    this.duringCooldownEvent = this::duringCooldown;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    if (!AbilityHelper.canUseMomentumAbility(player)) {
      return false;
    }
    clearTargets();
    
    player.world.playMovingSound(null, (Entity)player, ModSounds.MERA_SFX, SoundCategory.PLAYERS, 2.0F, 2.0F);
    Vec3d speed = WyHelper.propulsion((LivingEntity)player, 3.0D, 3.0D, 3.0D);
    player.setMotion(speed.x, 0.5D + speed.y, speed.z);
    
    ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
    
    return true;
  }

  
  private void duringCooldown(PlayerEntity player, int cooldownTimer) {
    if (canDealDamage()) {
      
      Vec3d speed = WyHelper.propulsion((LivingEntity)player, 3.0D, 3.0D, 3.0D);
      player.setMotion(speed.x, speed.y, speed.z);
      ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
      
      if (cooldownTimer % 2 == 0) {
        PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
      }
      List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.4D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
      list.remove(player);
      for (LivingEntity target : list) {
        
        if (isTarget(target) && player.canEntityBeSeen((Entity)target)) {
          
          SetOnFireEvent event = new SetOnFireEvent((LivingEntity)player, target, 2);
          if (!MinecraftForge.EVENT_BUS.post(event)) {

            target.setFire(2);
          }
        } 
      } 
    } 
  }
  
  public boolean canDealDamage() {
    return (this.cooldown > getMaxCooldown() * 0.9D);
  }
}


