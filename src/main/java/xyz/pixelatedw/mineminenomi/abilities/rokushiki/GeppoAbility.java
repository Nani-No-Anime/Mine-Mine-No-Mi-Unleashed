package xyz.pixelatedw.mineminenomi.abilities.rokushiki;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
import xyz.pixelatedw.mineminenomi.api.abilities.IFallDamageBlockingAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.rokushiki.GeppoParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public class GeppoAbility extends Ability implements IFallDamageBlockingAbility {
  public static final GeppoAbility INSTANCE = new GeppoAbility();
  public static final ParticleEffect PARTICLES = (ParticleEffect)new GeppoParticleEffect();
  
  public int airJumps = 0;
  
  private boolean hasFallDamage = true;
  
  public GeppoAbility() {
    super("Geppo", AbilityHelper.getRacialCategory());
    addInPool(AbilityPool.GEPPO_LIKE );
    setMaxCooldown(1.0D);
    setDescription("The user kicks the air beneath them to launch themselves into the air");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    if (!AbilityHelper.canUseMomentumAbility(player)) {
      return false;
    }
    
    if (!player.isInWater()) {
      
      if (player.onGround) {
        
        Vec3d speed = WyHelper.propulsion((LivingEntity)player, 1.0D, 1.0D);
        player.setMotion(speed.x, 1.86D, speed.z);
      }
      else {
        
        Vec3d speed = WyHelper.propulsion((LivingEntity)player, 1.5D, 1.5D);
        player.setMotion(speed.x, 1.25D, speed.z);
      } 
      AbilityHelper.setAirJumps(player, this.airJumps + 1);
      player.velocityChanged = true;
    }
    else {
      
      Vec3d speed = WyHelper.propulsion((LivingEntity)player, 2.0D, 2.0D, 2.0D);
      player.setMotion(speed.x, speed.y, speed.z);
      AbilityHelper.setAirJumps(player, 0);
      player.velocityChanged = true;
      setMaxCooldown((5 + this.airJumps));
      startCooldown(player);
      return true;
    } 
    
    player.world.playSound(null, player.getPosition(), ModSounds.GEPPO_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
    PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    this.hasFallDamage = false;
    
    int maxJumps = (int)WyHelper.clamp(Math.round(EntityStatsCapability.get((LivingEntity)player).getDoriki() * 0.001D), 3L, 6L);
    if (this.airJumps >= maxJumps) {
      
      setMaxCooldown(this.airJumps * 2.5D);
      startCooldown(player);
      AbilityHelper.setAirJumps(player, 0);
      return true;
    } 
    
    setMaxCooldown(1.0D);
    return true;
  }


  
  public void resetFallDamage(LivingEntity player) {
    if (this.airJumps > 0) {
      
      setMaxCooldown((this.airJumps * 2.5F));
      startCooldown((PlayerEntity)player);
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket((PlayerEntity)player, this), player);
      checkAbilityPool((PlayerEntity)player, Ability.State.COOLDOWN);
    } 
    
    this.hasFallDamage = true;
    AbilityHelper.setAirJumps((PlayerEntity)player, 0);
  }


  
  public boolean hasFallDamage() {
    return this.hasFallDamage;
  }
}


