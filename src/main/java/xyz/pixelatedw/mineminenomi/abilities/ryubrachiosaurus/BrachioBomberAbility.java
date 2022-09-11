package xyz.pixelatedw.mineminenomi.abilities.ryubrachiosaurus;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.IFallDamageBlockingAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.BrachiosaurusGuardZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.BrachiosaurusHeavyZoanInfo;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.zou.GreatStompParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class BrachioBomberAbility extends Ability implements IMultiTargetAbility, IFallDamageBlockingAbility, IFormRequiredAbility {
  public static final BrachioBomberAbility INSTANCE = new BrachioBomberAbility();
  private static final ParticleEffect PARTICLES = (ParticleEffect)new GreatStompParticleEffect();
  
  private boolean wasActivated = false;
  
  private boolean canHit = false;
  private boolean hasFallDamage = true;
  
  public BrachioBomberAbility() {
    super("Brachio Bomber", AbilityHelper.getDevilFruitCategory());
    setDescription("Dives from a high place and lands on his opponent, crushing them under the user's weight");
    setMaxCooldown(15.0D);
    
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
      
      PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
      
      int size = 7;
      if (BrachiosaurusGuardZoanInfo.INSTANCE.isActive((LivingEntity)player)) {
        size = 15;
      }
      ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)player, player.world, player.getPosX(), player.getPosY(), player.getPosZ(), size);
      explosion.setDestroyBlocks(true);
      explosion.setStaticDamage((size * 2));
      explosion.doExplosion();
      
      this.canHit = false;
    } 
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    if (!AbilityHelper.canUseMomentumAbility(player)) {
      return false;
    }
    clearTargets();
    
    double jump = 1.3D;
    if (BrachiosaurusGuardZoanInfo.INSTANCE.isActive((LivingEntity)player)) {
      jump = 1.6D;
    }
    Vec3d speed = WyHelper.propulsion((LivingEntity)player, 1.0D, 1.0D);
    player.setMotion(speed.x, jump, speed.z);
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
    return new ZoanInfo[] { (ZoanInfo)BrachiosaurusGuardZoanInfo.INSTANCE, (ZoanInfo)BrachiosaurusHeavyZoanInfo.INSTANCE };
  }
}


