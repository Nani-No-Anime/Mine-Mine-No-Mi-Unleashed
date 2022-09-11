package xyz.pixelatedw.mineminenomi.abilities.sui;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class NekomimiPunchAbility extends Ability {
  public static final NekomimiPunchAbility INSTANCE = new NekomimiPunchAbility();
  
  public NekomimiPunchAbility() {
    super("Nekomimi Punch", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(6.0D);
    setDescription("Propels the swimming user forward and deals huge damage to all entities they hit");
    
    this.onUseEvent = this::onUseEvent;
    this.duringCooldownEvent = this::duringCooldown;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    if (!AbilityHelper.canUseMomentumAbility(player)) {
      return false;
    }
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    
    if (((FreeSwimmingAbility)props.getEquippedAbility((Ability)FreeSwimmingAbility.INSTANCE)).isSwimming) {
      
      Vec3d speed = WyHelper.propulsion((LivingEntity)player, 3.0D, 1.5D, 3.0D);
      player.setMotion(speed.x, 0.5D + speed.y, speed.z);
      
      ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
      return true;
    } 
    
    return false;
  }


  
  private void duringCooldown(PlayerEntity player, int cooldownTimer) {
    if ((cooldownTimer / 20) > this.maxCooldown / 20.0D - 3.0D) {
      
      List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.4D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
      list.remove(player);
      for (LivingEntity target : list)
      {
        target.attackEntityFrom(DamageSource.causePlayerDamage(player), 20.0F);
      }
    } 
  }
}


