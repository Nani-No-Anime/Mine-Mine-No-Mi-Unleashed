package xyz.pixelatedw.mineminenomi.abilities.blackleg;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.blackleg.BienCuitGrillShotParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

import java.util.List;

public class BienCuitGrillShotAbility extends Ability implements IMultiTargetAbility {
  public static final BienCuitGrillShotAbility INSTANCE = new BienCuitGrillShotAbility();
  
  private static final ParticleEffect PARTICLES = (ParticleEffect)new BienCuitGrillShotParticleEffect();

  
  public BienCuitGrillShotAbility() {
    super("Bien Cuit: Grill Shot", AbilityHelper.getStyleCategory());
    setMaxCooldown(20.0D);
    setDescription("A strong kick that launches the user forwards and creates a grill-patterened particle to appear, which sets anyone touching it on fire");
    
    this.onUseEvent = this::onUseEvent;
    this.duringCooldownEvent = this::duringCooldown;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    if (!AbilityHelper.canUseMomentumAbility(player)) {
      return false;
    }
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    Ability diableJambeAbility = props.getEquippedAbility((Ability)DiableJambeAbility.INSTANCE);
    if (diableJambeAbility == null || !diableJambeAbility.isContinuous()) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_DIABLE_JAMBE, new Object[0]));
      return false;
    } 
    
    clearTargets();
    
    Vec3d speed = WyHelper.propulsion((LivingEntity)player, 3.0D, 3.0D);
    player.setMotion(speed.x, 0.3D, speed.z);
    player.velocityChanged = true;
    ((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf((Entity)player, (IPacket)new SAnimateHandPacket((Entity)player, 0));
    
    PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), (player.getMotion()).x, (player.getMotion()).y, (player.getMotion()).z);
    
    return true;
  }

  
  private void duringCooldown(PlayerEntity player, int cooldownTimer) {
    if (canDealDamage()) {
      
      List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.8D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
      targets.remove(player);
      
      for (LivingEntity entity : targets) {
        
        if (isTarget(entity)) {
          
          entity.attackEntityFrom((DamageSource)ModDamageSource.causeAbilityDamage((LivingEntity)player, this, "player"), 30.0F);
          Vec3d speed = WyHelper.propulsion((LivingEntity)player, 2.0D, 2.0D);
          entity.setMotion(speed.x, 0.2D, speed.z);
          entity.velocityChanged = true;
          entity.setFire(2);
        } 
      } 
    } 
  }


  
  public boolean canDealDamage() {
    return (getCooldownPercentage() > 90.0D);
  }
}


