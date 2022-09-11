package xyz.pixelatedw.mineminenomi.abilities.zushi;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

public class GraviPullAbility extends ChargeableAbility {
  public static final GraviPullAbility INSTANCE = new GraviPullAbility();

  
  public GraviPullAbility() {
    super("Gravi Pull", AbilityHelper.getDevilFruitCategory());
    setDescription("Pulls all enemies in a radius towards the user");
    this.onStartChargingEvent = this::onStartChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
    
    setMaxCooldown(17.0D);
    setMaxChargeTime(3.0D);
  }

  
  private boolean onStartChargingEvent(PlayerEntity player) {
    for (double i = 0.0D; i < 7.283185307179586D; i += 0.09817477042468103D) {
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.GASU);
      data.setLife(100);
      data.setSize(2.0F);
      double offsetX = Math.cos(i);
      double offsetZ = Math.sin(i);
      data.setMotion(offsetX / 5.0D, 0.0D, offsetZ / 5.0D);
      data.setHasMotionDecay(false);
      WyHelper.spawnParticles(data, (ServerWorld)player.world, player.getPosX() + offsetX, player.getPosY() + 1.0D, player.getPosZ() + offsetZ);
    } 
    return true;
  }

  
  private boolean onEndChargingEvent(PlayerEntity player) {
    for (double i = 0.0D; i < 7.283185307179586D; i += 0.09817477042468103D) {
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.GASU);
      data.setLife(5);
      data.setSize(2.0F);
      double offsetX = Math.cos(i) * 20.0D;
      double offsetZ = Math.sin(i) * 20.0D;
      data.setMotion(-offsetX / 10.0D, 0.0D, -offsetZ / 10.0D);
      WyHelper.spawnParticles(data, (ServerWorld)player.world, player.getPosX() + offsetX, player.getPosY() + 1.0D, player.getPosZ() + offsetZ);
    } 
    
    List<Entity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 16.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
    list.forEach(e -> {
          double offsetX = player.getPosX() - e.getPosX();
          
          double offsetZ = player.getPosZ() - e.getPosZ();
          e.setMotion(offsetX / 2.0D, (player.getPosY() - e.getPosY()) / 4.0D, offsetZ / 2.0D);
          e.velocityChanged = true;
        });
    return true;
  }
}


