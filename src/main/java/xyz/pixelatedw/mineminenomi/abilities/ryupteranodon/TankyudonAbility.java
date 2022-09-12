package xyz.pixelatedw.mineminenomi.abilities.ryupteranodon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.IExtraUpdateData;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.entities.zoan.PteranodonFlyZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
import xyz.pixelatedw.mineminenomi.renderers.animations.pteranodon.OpenMouthAnimation;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

import java.util.Optional;

public class TankyudonAbility extends ContinuousAbility implements IFormRequiredAbility, IAnimatedAbility, IExtraUpdateData {
  public static final TankyudonAbility INSTANCE = new TankyudonAbility();
  
  private LivingEntity grabbedEntity = null;
  private int grabbedEntityId = -1;

  
  public TankyudonAbility() {
    super("Tankyudon", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(10.0D);
    setThreshold(10.0D);
    setDescription("The user dashes forward and grabs the enemy, dealing damage while doing so");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    if (!AbilityHelper.canUseMomentumAbility(player)) {
      return false;
    }
    this.grabbedEntity = null;
    player.world.playSound(null, player.getPosition(), ModSounds.DASH_ABILITY_SWOOSH_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
    
    return true;
  }

  
  private void duringContinuityEvent(PlayerEntity player, int time) {
    if (this.grabbedEntity == null) {
      
      if (canGrab()) {
        
        Optional<LivingEntity> target = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.2D, new Class[] { LivingEntity.class }).stream().filter(e -> (e != player)).findFirst();
        if (target.isPresent())
        {
          LivingEntity e = target.get();
          if (!e.isAlive() || (
            DevilFruitCapability.get(e).isLogia() && !HakiHelper.hasHardeningActive((LivingEntity)player)) || 
            AbilityHelper.isTargetBlockingAbility((LivingEntity)player, e)) {
            
            endContinuity(player);
            return;
          } 
          this.grabbedEntity = e;
          this.grabbedEntityId = e.getEntityId();
          WyNetwork.sendTo(new SUpdateEquippedAbilityPacket(player, (Ability)this), player);
          this.grabbedEntity.attackEntityFrom((DamageSource)ModDamageSource.causeAbilityDamage((LivingEntity)player, (Ability)this, "player"), 8.0F);
        }
      
      } else {
        
        endContinuity(player);
      }
    
    } else {
      
      if (!this.grabbedEntity.isAlive() || this.grabbedEntity
        .getDistanceSq((Entity)player) > 32.0D || (
        DevilFruitCapability.get(this.grabbedEntity).isLogia() && !HakiHelper.hasHardeningActive((LivingEntity)player)) || 
        AbilityHelper.isTargetBlockingAbility((LivingEntity)player, this.grabbedEntity)) {
        
        endContinuity(player);
        
        return;
      } 
      this.grabbedEntity.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1));
      this.grabbedEntity.setMotion(0.0D, 0.0D, 0.0D);
      
      float distance = 2.0F;
      Vec3d lookVec = player.getLookVec();
      Vec3d pos = new Vec3d(player.getPosX() + lookVec.x * distance, player.getPosY() + player.getEyeHeight() - 2.0D + lookVec.y * distance, player.getPosZ() + lookVec.z * distance);
      this.grabbedEntity.setPositionAndUpdate(pos.x, pos.y, pos.z);
    } 
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    this.grabbedEntity = null;
    this.grabbedEntityId = -1;
    WyNetwork.sendTo(new SUpdateEquippedAbilityPacket(player, (Ability)this), player);
    return true;
  }

  
  public boolean canGrab() {
    return (isContinuous() && this.continueTime > 0 && this.grabbedEntityId <= 0 && this.continueTime < getThreshold() * 0.5D);
  }


  
  public CompoundNBT getExtraData() {
    CompoundNBT nbt = new CompoundNBT();
    nbt.putInt("grabbedEntity", this.grabbedEntityId);
    return nbt;
  }


  
  public void setExtraData(CompoundNBT nbt) {
    this.grabbedEntityId = nbt.getInt("grabbedEntity");
  }


  
  public ZoanInfo[] getRequiredForms(PlayerEntity player) {
    return new ZoanInfo[] { (ZoanInfo)PteranodonFlyZoanInfo.INSTANCE };
  }


  
  public IAnimation getAnimation() {
    return (IAnimation)OpenMouthAnimation.INSTANCE;
  }


  
  public boolean isAnimationActive() {
    return canGrab();
  }
}


