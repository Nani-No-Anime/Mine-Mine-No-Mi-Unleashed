package xyz.pixelatedw.mineminenomi.abilities.zou;

import java.util.List;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZouGuardZoanInfo;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.baku.BakuMunchParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class IvoryDartAbility extends Ability implements IMultiTargetAbility, IFormRequiredAbility {
  public static final IvoryDartAbility INSTANCE = new IvoryDartAbility();
  
  private static final ParticleEffect PARTICLES = (ParticleEffect)new BakuMunchParticleEffect();
  
  private int initialY;

  
  public IvoryDartAbility() {
    super("Ivory Dart", AbilityHelper.getDevilFruitCategory());
    setDescription("Launches the user forward in their elephant form, causing damage and destruction");
    setMaxCooldown(14.0D);
    
    this.onUseEvent = this::onUseEvent;
    this.duringCooldownEvent = this::duringCooldown;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    if (!AbilityHelper.canUseMomentumAbility(player)) {
      return false;
    }
    clearTargets();
    
    this.initialY = (int)player.getPosY();
    Vec3d speed = WyHelper.propulsion((LivingEntity)player, 4.0D, 4.0D);
    player.setMotion(speed.x, player.getMotion().getY(), speed.z);
    ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
    
    return true;
  }

  
  private void duringCooldown(PlayerEntity player, int cooldownTimer) {
    if (canDealDamage() && player.getPosY() >= this.initialY) {
      
      List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.6D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
      list.remove(player);
      
      list.forEach(entity -> {
            if (isTarget(entity)) {
              entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 20.0F);
            }
          });
      
      for (BlockPos location : WyHelper.getNearbyBlocks((LivingEntity)player, 4)) {
        
        if (location.getY() >= player.getPosY())
        {
          if (AbilityHelper.placeBlockIfAllowed(player.world, location.getX(), location.getY(), location.getZ(), Blocks.AIR, DefaultProtectionRules.CORE_FOLIAGE_ORE))
          {
            PARTICLES.spawn(player.world, location.getX(), location.getY(), location.getZ(), 0.0D, 0.0D, 0.0D);
          }
        }
      } 
    } 
  }

  
  public boolean canDealDamage() {
    return (this.cooldown > getMaxCooldown() * 0.7D);
  }


  
  public ZoanInfo[] getRequiredForms(PlayerEntity player) {
    return new ZoanInfo[] { (ZoanInfo)ZouGuardZoanInfo.INSTANCE };
  }
}


