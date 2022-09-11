package xyz.pixelatedw.mineminenomi.abilities.sai;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.SaiHeavyZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.SaiWalkZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class HornDashAbility extends Ability implements IMultiTargetAbility, IFormRequiredAbility {
  public static final HornDashAbility INSTANCE = new HornDashAbility();

  
  public HornDashAbility() {
    super("Horn Dash", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(9.0D);
    setDescription("The user dashes forward hurting the enemy and pushing them forward using their horn.");
    
    this.onUseEvent = this::onUseEvent;
    this.duringCooldownEvent = this::duringCooldown;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    if (!AbilityHelper.canUseMomentumAbility(player)) {
      return false;
    }
    clearTargets();
    
    Vec3d speed = WyHelper.propulsion((LivingEntity)player, 3.0D, 3.0D);
    player.setMotion(speed.x, 0.2D, speed.z);
    player.velocityChanged = true;
    player.world.playSound(null, player.getPosition(), ModSounds.DASH_ABILITY_SWOOSH_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
    
    return true;
  }

  
  private void duringCooldown(PlayerEntity player, int cooldownTimer) {
    if (canDealDamage()) {
      
      List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.7D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
      list.remove(player);
      
      list.forEach(entity -> {
            if (isTarget(entity) && player.canEntityBeSeen((Entity)entity)) {
              entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 10.0F);
              Vec3d speed = WyHelper.propulsion((LivingEntity)player, 3.0D, 3.0D);
              entity.setMotion(speed.x, 0.5D, speed.z);
              entity.velocityChanged = true;
            } 
          });
    } 
  }



  
  public boolean canDealDamage() {
    return (this.cooldown > getMaxCooldown() * 0.9D);
  }


  
  public ZoanInfo[] getRequiredForms(PlayerEntity player) {
    return new ZoanInfo[] { (ZoanInfo)SaiHeavyZoanInfo.INSTANCE, (ZoanInfo)SaiWalkZoanInfo.INSTANCE };
  }
}


