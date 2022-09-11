package xyz.pixelatedw.mineminenomi.abilities.ushibison;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.BisonHeavyZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.BisonWalkZoanInfo;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class FiddleBanffAbility extends Ability implements IMultiTargetAbility, IFormRequiredAbility {
  public static final FiddleBanffAbility INSTANCE = new FiddleBanffAbility();

  
  public FiddleBanffAbility() {
    super("Fiddle Banff", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(7.0D);
    setDescription("The transformed user dashes towards the opponent to crash against them with great power");
    
    this.onUseEvent = this::onUseEvent;
    this.duringCooldownEvent = this::duringCooldown;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    if (!AbilityHelper.canUseMomentumAbility(player)) {
      return false;
    }
    clearTargets();
    
    Vec3d speed = WyHelper.propulsion((LivingEntity)player, 5.0D, 5.0D);
    player.setMotion(speed.x, player.getMotion().getY(), speed.z);
    ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
    
    return true;
  }

  
  private void duringCooldown(PlayerEntity player, int cooldownTimer) {
    if (canDealDamage()) {
      
      List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.6D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
      list.remove(player);
      for (LivingEntity target : list) {
        
        if (isTarget(target)) {
          target.attackEntityFrom(DamageSource.causePlayerDamage(player), 8.0F);
        }
      } 
    } 
  }



  
  public boolean canDealDamage() {
    return (this.cooldown > 100.0D);
  }


  
  public ZoanInfo[] getRequiredForms(PlayerEntity player) {
    return new ZoanInfo[] { (ZoanInfo)BisonHeavyZoanInfo.INSTANCE, (ZoanInfo)BisonWalkZoanInfo.INSTANCE };
  }
}


