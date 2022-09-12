package xyz.pixelatedw.mineminenomi.abilities.zoumammoth;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.MammothGuardZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.MammothHeavyZoanInfo;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

import java.util.List;

public class AncientSweepAbility extends ChargeableAbility implements IFormRequiredAbility {
  public static final AncientSweepAbility INSTANCE = new AncientSweepAbility();

  
  public AncientSweepAbility() {
    super("Ancient Sweep", AbilityHelper.getDevilFruitCategory());
    setDescription("Hits all enemies in a frontal cone with your trunk.");
    setMaxCooldown(8.0D);
    setMaxChargeTime(2.0D);
    
    this.onEndChargingEvent = this::onEndChargingEvent;
  }

  
  public boolean onEndChargingEvent(PlayerEntity player) {
    float damage = 15.0F;
    double radius = 3.0D;
    
    if (MammothGuardZoanInfo.INSTANCE.isActive((LivingEntity)player)) {
      
      radius *= 2.0D;
      damage += 5.0F;
    } 
    
    Vec3d look = player.getLookVec();
    
    List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition().offset(Direction.getFacingFromVector(look.x, look.y, look.z), 2), player.world, radius, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
    targets.remove(player);
    
    for (LivingEntity target : targets) {
      
      target.attackEntityFrom(DamageSource.causePlayerDamage(player), damage);
      Vec3d speed = WyHelper.propulsion((LivingEntity)player, 3.0D, 3.0D);
      target.setMotion(speed.x, player.getMotion().getY() + 0.5D, speed.z);
      target.velocityChanged = true;
    } 
    
    ((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf((Entity)player, (IPacket)new SAnimateHandPacket((Entity)player, 0));
    
    return true;
  }


  
  public ZoanInfo[] getRequiredForms(PlayerEntity player) {
    return new ZoanInfo[] { (ZoanInfo)MammothGuardZoanInfo.INSTANCE, (ZoanInfo)MammothHeavyZoanInfo.INSTANCE };
  }
}


