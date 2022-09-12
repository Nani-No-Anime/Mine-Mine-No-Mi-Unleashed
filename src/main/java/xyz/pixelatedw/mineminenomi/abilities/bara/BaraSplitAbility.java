package xyz.pixelatedw.mineminenomi.abilities.bara;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import xyz.pixelatedw.mineminenomi.api.abilities.IExtraUpdateData;
import xyz.pixelatedw.mineminenomi.api.abilities.IOutOfBodyAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.BottomHalfBodyEntity;
import xyz.pixelatedw.mineminenomi.entities.zoan.BaraSplitZoanInfo;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public class BaraSplitAbility extends ZoanAbility implements IOutOfBodyAbility, IExtraUpdateData {
  public static final BaraSplitAbility INSTANCE = new BaraSplitAbility();
  
  private BottomHalfBodyEntity legs;
  
  private BlockPos pivotPoint;
  
  public BaraSplitAbility() {
    super("Bara Split", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(15.0D);
    setThreshold(50.0D);
    setDescription("Allows the user to split its upper part of the body from the lower.");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContiunityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }


  
  protected boolean onStartContinuityEvent(PlayerEntity player) {
    if (!super.onStartContinuityEvent(player) || !AbilityHelper.canUseMomentumAbility(player)) {
      return false;
    }
    player.setMotion(0.0D, 2.0D, 0.0D);
    ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
    player.velocityChanged = true;
    
    this.legs = new BottomHalfBodyEntity(player.world);
    this.legs.setPositionAndRotation(player.getPosX(), player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
    this.legs.setOwner((LivingEntity)player);
    player.world.addEntity((Entity)this.legs);
    this.legs.setHealth(player.getHealth());
    this.legs.setParentAbility((Ability)this);
    
    startOutOfBody(player);
    
    return true;
  }

  
  private void duringContiunityEvent(PlayerEntity player, int time) {
    if (this.legs == null) {
      
      endContinuity(player);
      
      return;
    } 
    if (Math.sqrt(player.getDistanceSq(this.legs.getPosX(), this.legs.getPosY(), this.legs.getPosZ())) > getMaxRange()) {
      
      endContinuity(player);
      
      return;
    } 
    if (this.legs != null) {
      
      if (!this.legs.isAlive()) {
        player.attackEntityFrom(DamageSource.MAGIC, player.getMaxHealth());
      }
      this.pivotPoint = this.legs.getPosition();
    } 
    
    if (time > 10 && player.onGround) {
      
      endContinuity(player);
      
      return;
    } 
    if (time % 20 == 0) {
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, (Ability)this), (LivingEntity)player);
    }
  }

  
  protected boolean onEndContinuityEvent(PlayerEntity player) {
    if (!super.onEndContinuityEvent(player)) {
      return false;
    }
    double cooldown = (this.continueTime / 20.0F);
    
    setMaxCooldown(cooldown);
    player.fallDistance = 0.0F;
    
    if (this.legs != null) {
      
      player.setPositionAndUpdate(this.legs.getPosX(), this.legs.getPosY(), this.legs.getPosZ());
      this.legs.remove();
      this.legs = null;
    } 
    
    stopOutOfBody(player);
    
    return true;
  }


  
  public float getMaxRange() {
    return 30.0F;
  }


  
  public BlockPos getPivotPoint() {
    return this.pivotPoint;
  }


  
  public CompoundNBT getExtraData() {
    CompoundNBT nbt = new CompoundNBT();
    if (this.pivotPoint != null) {
      
      nbt.putDouble("x", this.pivotPoint.getX());
      nbt.putDouble("y", this.pivotPoint.getY());
      nbt.putDouble("z", this.pivotPoint.getZ());
    } 
    return nbt;
  }


  
  public void setExtraData(CompoundNBT nbt) {
    double x = nbt.getDouble("x");
    double y = nbt.getDouble("y");
    double z = nbt.getDouble("z");
    this.pivotPoint = new BlockPos(x, y, z);
  }


  
  public ZoanInfo getTransformation() {
    return (ZoanInfo)BaraSplitZoanInfo.INSTANCE;
  }


  
  public boolean isPhysical() {
    return true;
  }
}


