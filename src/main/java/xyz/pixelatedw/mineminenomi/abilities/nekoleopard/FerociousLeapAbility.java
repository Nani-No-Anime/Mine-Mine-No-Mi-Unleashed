package xyz.pixelatedw.mineminenomi.abilities.nekoleopard;


import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.api.abilities.IFallDamageBlockingAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.LeopardHeavyZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.LeopardWalkZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

import java.util.List;

public class FerociousLeapAbility extends Ability implements IMultiTargetAbility, IFallDamageBlockingAbility, IFormRequiredAbility {
  public static final FerociousLeapAbility INSTANCE = new FerociousLeapAbility();
  
  private boolean wasActivated = false;
  
  private boolean canHit = false;
  private boolean hasFallDamage = true;
  
  public FerociousLeapAbility() {
    super("Ferocious Leap", AbilityHelper.getDevilFruitCategory());
    setDescription("Leaps at the enemy and damages all nearby entities on landing");
    setMaxCooldown(8.0D);
    
    this.onUseEvent = this::onUseEvent;
    this.duringCooldownEvent = this::duringCooldown;
    this.onEndCooldownEvent = this::onEndCooldown;
  }

  
  private void onEndCooldown(PlayerEntity player) {
    this.wasActivated = false;
  }

  
  private void duringCooldown(PlayerEntity player, int cooldown) {
    if (cooldown < getMaxCooldown() && !this.canHit && !player.onGround && !this.wasActivated) {
      
      this.canHit = true;
      this.wasActivated = true;
    } 
    
    if (player.onGround && this.canHit) {
      
      List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 3.5D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
      targets.remove(player);
      
      for (LivingEntity entity : targets) {
        
        if (isTarget(entity) && player.canEntityBeSeen((Entity)entity))
        {
          entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 10.0F);
        }
      } 
      
      this.canHit = false;
    } 
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    if (!AbilityHelper.canUseMomentumAbility(player) || player
      .getAttribute(ModAttributes.JUMP_HEIGHT).getValue() < 0.0D || !player.onGround)
    {
      return false;
    }
    clearTargets();
    
    Vec3d speed = WyHelper.propulsion((LivingEntity)player, 1.0D, 1.0D);
    player.setMotion(speed.x, 1.4D, speed.z);
    player.velocityChanged = true;
    this.canHit = false;
    this.hasFallDamage = false;
    
    return true;
  }


  
  public void resetFallDamage(LivingEntity player) {
    this.hasFallDamage = true;
  }


  
  public boolean hasFallDamage() {
    return this.hasFallDamage;
  }


  
  public ZoanInfo[] getRequiredForms(PlayerEntity player) {
    return new ZoanInfo[] { (ZoanInfo)LeopardWalkZoanInfo.INSTANCE, (ZoanInfo)LeopardHeavyZoanInfo.INSTANCE };
  }
}


