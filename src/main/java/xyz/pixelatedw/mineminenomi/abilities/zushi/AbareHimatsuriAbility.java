package xyz.pixelatedw.mineminenomi.abilities.zushi;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;

public class AbareHimatsuriAbility extends ContinuousAbility implements IParallelContinuousAbility {
  public static final AbareHimatsuriAbility INSTANCE = new AbareHimatsuriAbility();
  
  private boolean stateChanged = false;

  
  public AbareHimatsuriAbility() {
    super("Abare Himatsuri", AbilityHelper.getDevilFruitCategory());
    setDescription("Makes the user fly using gravity on the ground below you");
    setMaxCooldown(15.0D);
    setThreshold(60.0D);
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    if (!player.world.getBlockState(player.getPosition().down()).getMaterial().isSolid()) {
      return false;
    }
    if (player.isCreative() || player.isSpectator()) {
      return true;
    }
    player.abilities.allowFlying = true;
    ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayerAbilitiesPacket(player.abilities));
    
    return true;
  }

  
  private void duringContinuityEvent(PlayerEntity player, int activeTime) {
    player.fallDistance = 0.0F;
    if (!AbilityHelper.canUseMomentumAbility(player)) {
      
      if (player.abilities.allowFlying)
      {
        player.abilities.allowFlying = false;
        player.abilities.isFlying = false;
        this.stateChanged = true;
      
      }
    
    }
    else if (!player.abilities.allowFlying) {
      
      player.abilities.allowFlying = true;
      this.stateChanged = true;
    } 
    
    ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayerAbilitiesPacket(player.abilities));
    
    boolean canFly = DevilFruitHelper.isFlyingAtMaxHeight(player, 48.0D);
    if (player.abilities.isFlying) {
      DevilFruitHelper.vanillaFlightThreshold((LivingEntity)player, canFly ? 256 : ((int)player.getPosY() - 1));
    }
    if (player.isSprinting()) {
      player.setMotion(player.getMotion().mul(0.69D, 1.0D, 0.69D));
      player.setSprinting(false);
      this.stateChanged = true;
    } 
    
    if (this.stateChanged) {
      player.velocityChanged = true;
      this.stateChanged = false;
    } 
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    if (player.isCreative() || player.isSpectator()) {
      return true;
    }
    player.abilities.allowFlying = false;
    player.abilities.isFlying = false;
    ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayerAbilitiesPacket(player.abilities));
    
    return true;
  }
}


