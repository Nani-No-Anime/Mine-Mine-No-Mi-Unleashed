package xyz.pixelatedw.mineminenomi.abilities.moku;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.moku.WhiteGrabProjectile;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;

public class WhiteGrabAbility extends ContinuousAbility {
  public static final WhiteGrabAbility INSTANCE = new WhiteGrabAbility();
  
  private LivingEntity grabbedEntity = null;
  private WhiteGrabProjectile proj = null;

  
  public WhiteGrabAbility() {
    super("White Grab", AbilityHelper.getDevilFruitCategory());
    setDescription("Fires both fists at an enemy and lifts them up, moving them around according to the user's movements");
    setMaxCooldown(15.0D);
    setThreshold(5.0D);
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }

  
  public boolean onStartContinuityEvent(PlayerEntity player) {
    this.proj = new WhiteGrabProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)this.proj);
    this.proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 0.0F);
    
    return true;
  }

  
  public void duringContinuityEvent(PlayerEntity player, int timer) {
    if ((this.proj == null || !this.proj.isAlive()) && this.grabbedEntity == null) {
      
      endContinuity(player);
      
      return;
    } 
    if (this.grabbedEntity != null && !this.grabbedEntity.isAlive()) {
      
      endContinuity(player);
      return;
    } 
    if (this.grabbedEntity != null) {
      
      this.grabbedEntity.rotationPitch = this.grabbedEntity.prevRotationPitch;
      this.grabbedEntity.rotationYaw = this.grabbedEntity.prevRotationYaw;
      this.grabbedEntity.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1));
      this.grabbedEntity.addPotionEffect(new EffectInstance(ModEffects.SMOKE, 40, 0));
      
      double distance = 7.0D;
      Vec3d lookVec = player.getLookVec();
      Vec3d pos = new Vec3d(player.getPosX() + lookVec.x * distance, player.getPosY() + (player.getEyeHeight() / 2.0F) + lookVec.y * distance, player.getPosZ() + lookVec.z * distance);
      if (!player.world.getBlockState(new BlockPos(pos)).isSolid()) {
        this.grabbedEntity.setPositionAndUpdate(pos.x, pos.y, pos.z);
      }
      this.grabbedEntity.fallDistance = 0.0F;
    } 
  }

  
  public boolean onEndContinuityEvent(PlayerEntity player) {
    this.grabbedEntity = null;
    this.proj = null;
    
    return true;
  }

  
  public void grabEntity(LivingEntity target) {
    this.grabbedEntity = target;
  }
}


