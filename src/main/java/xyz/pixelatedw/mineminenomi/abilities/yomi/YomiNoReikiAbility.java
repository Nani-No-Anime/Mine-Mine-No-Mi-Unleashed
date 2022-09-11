package xyz.pixelatedw.mineminenomi.abilities.yomi;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.abilities.DamagedContinuousAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.IExtraUpdateData;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.PhysicalBodyEntity;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;
import xyz.pixelatedw.mineminenomi.api.abilities.IOutOfBodyAbility;

public class YomiNoReikiAbility extends DamagedContinuousAbility implements IOutOfBodyAbility, IExtraUpdateData {
  public static final YomiNoReikiAbility INSTANCE = new YomiNoReikiAbility();
  
  private PhysicalBodyEntity body;
  
  private BlockPos pivotPoint;
  
  public YomiNoReikiAbility() {
    super("Yomi no Reiki", AbilityHelper.getDevilFruitCategory());
    setDescription("The user's spirit leaves their body, allowing them to freely explore the nearby areas");
    setThreshold(200.0D);
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
    this.onDamagedEvent = this::onDamagedEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    if (player.isCreative() || player.isSpectator()) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_SUVIVAL_ONLY, new Object[0]));
      return false;
    } 
    
    player.setMotion(0.0D, 5.0D, 0.0D);
    ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
    player.velocityChanged = true;
    
    this.body = new PhysicalBodyEntity(player.world);
    this.body.setPositionAndRotation(player.getPosX(), player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
    this.body.setOwner((LivingEntity)player);
    player.world.addEntity((Entity)this.body);
    this.body.setHealth(player.getHealth());
    this.body.setParentAbility((Ability)this);
    
    this.pivotPoint = new BlockPos(this.body.getPositionVec().getX(), this.body.getPositionVec().getY(), this.body.getPositionVec().getZ());
    
    startOutOfBody(player);
    
    WyNetwork.sendToAllTrackingAndSelf(new SSyncAbilityDataPacket(player.getEntityId(), AbilityDataCapability.get((LivingEntity)player)), (LivingEntity)player);
    
    return true;
  }

  
  private void duringContinuityEvent(PlayerEntity player, int continueTime) {
    if (Math.sqrt(player.getDistanceSq(this.pivotPoint.getX(), this.pivotPoint.getY(), this.pivotPoint.getZ())) > getMaxRange()) {
      
      endContinuity(player);
      
      return;
    } 
    if (this.pivotPoint.getX() != (this.body.getPositionVec()).x || this.pivotPoint.getZ() != (this.body.getPositionVec()).z || this.pivotPoint.getY() != (this.body.getPositionVec()).y) {
      this.pivotPoint = this.body.getPosition();
    }
    if (this.body == null) {
      
      endContinuity(player);
      
      return;
    } 
    if (!this.body.isAlive()) {
      player.attackEntityFrom(DamageSource.MAGIC, player.getMaxHealth());
    }
  }
  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    double cooldown = (this.continueTime / 20.0F);
    
    setMaxCooldown(cooldown);
    
    if (this.body != null) {
      
      this.body.remove();
      this.body = null;
      player.setPositionAndUpdate(this.pivotPoint.getX(), this.pivotPoint.getY(), this.pivotPoint.getZ());
    } 
    
    stopOutOfBody(player);
    
    return true;
  }
  
  private boolean onDamagedEvent(LivingEntity entity, DamageSource damageSource, double v) {
    if (entity instanceof PlayerEntity) {
      if (((PlayerEntity)entity).isCreative() || entity.isSpectator()) {
        return false;
      }
      return (damageSource != DamageSource.MAGIC && !damageSource.isDamageAbsolute());
    } 
    return false;
  }


  
  public float getMaxRange() {
    return 60.0F;
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


  
  public boolean isPhysical() {
    return false;
  }
}


