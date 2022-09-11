package xyz.pixelatedw.mineminenomi.abilities.ito;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
import xyz.pixelatedw.mineminenomi.api.abilities.IFallDamageBlockingAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public class SoraNoMichiAbility extends Ability implements IFallDamageBlockingAbility {
  public static final SoraNoMichiAbility INSTANCE = new SoraNoMichiAbility();
  
  public int airJumps = 0;
  
  private boolean hasFallDamage = true;
  
  public SoraNoMichiAbility() {
    super("Sora no Michi", AbilityHelper.getDevilFruitCategory());
    addInPool(new AbilityPool[] { AbilityPool.GEPPO_LIKE });
    setMaxCooldown(1.0D);
    setDescription("The user attaches the strings to clouds, allowing them to move through the air");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    if (!AbilityHelper.canUseMomentumAbility(player) || player.getPosY() > player.world.getWorldInfo().getGenerator().getCloudHeight()) {
      return false;
    }
    ((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf((Entity)player, (IPacket)new SAnimateHandPacket((Entity)player, 0));
    
    if (player.onGround) {
      
      Vec3d speed = WyHelper.propulsion((LivingEntity)player, 1.1D, 1.1D);
      player.setMotion(speed.x, 2.4D, speed.z);
    }
    else {
      
      Vec3d speed = WyHelper.propulsion((LivingEntity)player, 2.5D, 2.5D);
      player.setMotion(speed.x, 0.8D, speed.z);
    } 
    
    AbilityHelper.setAirJumps(player, this.airJumps + 1);
    player.velocityChanged = true;
    this.hasFallDamage = false;
    
    if (this.airJumps >= 12) {
      
      setMaxCooldown(this.airJumps);
      startCooldown(player);
      AbilityHelper.setAirJumps(player, 0);
      return true;
    } 
    
    setMaxCooldown(1.0D);
    return true;
  }


  
  public void resetFallDamage(LivingEntity player) {
    if (this.airJumps > 0) {
      
      setMaxCooldown((this.airJumps / 2));
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


