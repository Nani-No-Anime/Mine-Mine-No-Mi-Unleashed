package xyz.pixelatedw.mineminenomi.abilities.toriphoenix;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SEntityVelocityPacket;

import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.PhoenixAssaultZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

public class BlueBirdAbility extends ChargeableAbility implements IFormRequiredAbility {
  public static final BlueBirdAbility INSTANCE = new BlueBirdAbility();
  private List<LivingEntity> list = new ArrayList<>();
  
  private BlockPos pos = null;
  
  public float rotationPitch;
  public float rotationYaw;
  
  public BlueBirdAbility() {
    super("Blue Bird", AbilityHelper.getDevilFruitCategory());
    setMaxChargeTime(2.0D);
    setMaxCooldown(8.0D);
    setDescription("While in the air, the user builds up momentum through blue flames, to deliver a devastating kick");
    
    this.onStartChargingEvent = this::onStartChargingEvent;
    this.duringChargingEvent = this::duringChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
  }

  
  private boolean onStartChargingEvent(PlayerEntity player) {
    if (!AbilityHelper.canUseMomentumAbility(player)) {
      return false;
    }
    this.list.clear();
    
    if (player.onGround) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONLY_IN_AIR, new Object[] { getName() }));
      return false;
    } 
    
    this.pos = player.getPosition();
    this.rotationPitch = player.rotationPitch;
    this.rotationYaw = player.rotationYaw;
    return true;
  }

  
  private void duringChargingEvent(PlayerEntity player, int time) {
    float incrementPI = 0.049087387F;
    float radius1 = (float)fromRangeToRange(0.0D, getMaxChargeTime(), 0.15D, 1.25D, (getMaxChargeTime() - time));
    float radius2 = (float)fromRangeToRange(0.0D, getMaxChargeTime(), 0.05D, 0.3D, (getMaxChargeTime() - time));
    float rotation = (float)(incrementPI * fromRangeToRange(0.0D, getMaxChargeTime(), -128.0D, 128.0D, time));
    float rotation2 = (float)(incrementPI * fromRangeToRange(0.0D, getMaxChargeTime(), -128.0D, 128.0D, time) + 2.0943951023931953D);
    float rotation3 = (float)(incrementPI * fromRangeToRange(0.0D, getMaxChargeTime(), -128.0D, 128.0D, time) - 2.0943951023931953D);
    
    Vec3d normalizedH = getPerpendicularHorizontalLine(Vec3d.ZERO, player.getLookVec(), radius1);
    Vec3d normalizedV = getPerpendicularVerticalLine(Vec3d.ZERO, player.getLookVec(), normalizedH.mul(-3.141592653589793D, -3.141592653589793D, -3.141592653589793D), radius2);
    
    Vec3d finalPosition1 = getParticlePositionInSpiral(rotation, normalizedH, normalizedV);
    
    Vec3d finalPosition2 = getParticlePositionInSpiral(rotation2, normalizedH, normalizedV);
    
    Vec3d finalPosition3 = getParticlePositionInSpiral(rotation3, normalizedH, normalizedV);
    
    GenericParticleData data = new GenericParticleData(ModParticleTypes.BLUE_FLAME);
    data.setLife(15);
    data.setSize(2.0F);
    data.setMotion(player.getLookVec().getX() / 10.0D, player.getLookVec().getY() / 10.0D, player.getLookVec().getZ() / 10.0D);
    
    double posX = player.getPosX() + finalPosition1.x;
    double posY = player.getPosYEye() + finalPosition1.y;
    double posZ = player.getPosZ() + finalPosition1.z;
    
    double posX2 = player.getPosX() + finalPosition2.x;
    double posY2 = player.getPosYEye() + finalPosition2.y;
    double posZ2 = player.getPosZ() + finalPosition2.z;
    
    double posX3 = player.getPosX() + finalPosition3.x;
    double posY3 = player.getPosYEye() + finalPosition3.y;
    double posZ3 = player.getPosZ() + finalPosition3.z;
    
    WyHelper.spawnParticles(data, (ServerWorld)player.world, posX, posY, posZ);
    WyHelper.spawnParticles(data, (ServerWorld)player.world, posX2, posY2, posZ2);
    WyHelper.spawnParticles(data, (ServerWorld)player.world, posX3, posY3, posZ3);
    
    if (!player.onGround && (time == 1 || time == 4 || time == 8 || time == 10)) {
      
      player.setMotion(player.getLookVec().mul(new Vec3d(5.0D * time / 10.0D, 5.0D, 5.0D * time / 10.0D)));
      ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
      player.fallDistance = 0.0F;
    } 
    
    if (time < 20) {
      
      List<LivingEntity> exList = WyHelper.getEntitiesNear(player.getPosition(), player.world, 4.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), LivingEntity.class );
      
      exList.remove(player);
      exList.removeIf(o -> this.list.contains(o));
      this.list.addAll(exList);
      
      if (time == 1)
        for (LivingEntity target : this.list)
          target.attackEntityFrom((DamageSource)ModDamageSource.causeAbilityDamage((LivingEntity)player, (Ability)this), 25.0F);  
    } 
    if (time > 20) {
      
      player.rotationPitch = this.rotationPitch;
      player.rotationYaw = this.rotationYaw;
      player.setPositionAndUpdate(this.pos.getX(), this.pos.getY(), this.pos.getZ());
    } 
  }

  
  private boolean onEndChargingEvent(PlayerEntity player) {
    this.list.clear();
    return true;
  }

  
  private static Vec3d getPerpendicularHorizontalLine(Vec3d startPoint, Vec3d endPoint, float radius) {
    Vec3d axis = new Vec3d(1.0D, 0.0D, 1.0D);
    Vec3d newStart = startPoint.mul(axis);
    Vec3d newEnd = endPoint.mul(axis);
    Vec3d line = newEnd.subtract(newStart);
    line.add((line.getX() == 0.0D) ? 0.001D : 0.0D, 0.0D, (line.getZ() == 0.0D) ? 0.001D : 0.0D);
    
    Vec3d newLine = new Vec3d(line.getZ(), 0.0D, -line.getX());
    
    return newLine.normalize().mul(radius, radius, radius);
  }


  
  private static Vec3d getPerpendicularVerticalLine(Vec3d startPoint, Vec3d endPoint, Vec3d horizontalPoint, float radius) {
    Vec3d vec1 = endPoint.subtract(startPoint);
    Vec3d vec2 = horizontalPoint.subtract(startPoint);
    Vec3d newLine = vec1.crossProduct(vec2);
    
    return newLine.normalize().mul(radius, radius, radius);
  }

  
  private static double fromRangeToRange(double oldMin, double oldMax, double min, double max, double oldValue) {
    return (oldValue - oldMin) * (max - min) / (oldMax - oldMin) + min;
  }

  
  private static Vec3d getParticlePositionInSpiral(float rotation, Vec3d normalizedH, Vec3d normalizedV) {
    Vec3d incrementH = normalizedH.mul(Math.cos(rotation), Math.cos(rotation), Math.cos(rotation));
    Vec3d incrementV = normalizedV.mul(Math.sin(rotation), Math.sin(rotation), Math.sin(rotation));
    
    Vec3d HVVec = incrementV.subtract(incrementH);
    
    return incrementV.add(HVVec.mul(0.5D, 0.5D, 0.5D));
  }


  
  public ZoanInfo[] getRequiredForms(PlayerEntity player) {
    return new ZoanInfo[] { (ZoanInfo)PhoenixAssaultZoanInfo.INSTANCE };
  }
}


