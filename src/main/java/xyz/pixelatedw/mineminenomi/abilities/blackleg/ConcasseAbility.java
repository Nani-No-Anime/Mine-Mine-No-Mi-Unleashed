package xyz.pixelatedw.mineminenomi.abilities.blackleg;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.IExtraUpdateData;
import xyz.pixelatedw.mineminenomi.api.abilities.IFallDamageBlockingAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateExtraDataPacket;
import xyz.pixelatedw.mineminenomi.particles.effects.blackleg.ConcasseParticleEffect;
import xyz.pixelatedw.mineminenomi.renderers.animations.blackleg.ConcasseAnimation;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

import java.util.List;

public class ConcasseAbility extends Ability implements IMultiTargetAbility, IFallDamageBlockingAbility, IExtraUpdateData, IAnimatedAbility {
  public static final ConcasseAbility INSTANCE = new ConcasseAbility();
  
  private static final ConcasseParticleEffect PARTICLES = new ConcasseParticleEffect();
  
  private boolean hasLanded = false;
  
  private boolean hasFallDamage = true;
  private LivingEntity owner;
  
  public ConcasseAbility() {
    super("Concasse", AbilityHelper.getStyleCategory());
    setMaxCooldown(15.0D);
    setDescription("Leaps forward kicking all nearby enemies for moderate damage and knocking them down");
    
    this.onUseEvent = this::onUseEvent;
    this.duringCooldownEvent = this::duringCooldown;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    if (!AbilityHelper.canUseMomentumAbility(player)) {
      return false;
    }
    clearTargets();
    
    player.setMotion((player.getMotion()).x, 1.3D, (player.getMotion()).z);
    player.velocityChanged = true;
    this.hasLanded = false;
    this.hasFallDamage = false;
    this.owner = (LivingEntity)player;
    
    return true;
  }

  
  private void duringCooldown(PlayerEntity player, int cooldownTimer) {
    if (player.isInWater() && !this.hasLanded) {
      this.hasLanded = true;
    }
    if (player.onGround && getCooldownPercentage() < 98.0D && !this.hasLanded) {
      
      List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.75D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
      targets.remove(player);
      
      for (LivingEntity entity : targets) {
        
        if (isTarget(entity)) {
          
          entity.attackEntityFrom((DamageSource)ModDamageSource.causeAbilityDamage((LivingEntity)player, this, "player"), 15.0F);
          EffectInstance effInst = new EffectInstance(ModEffects.UNCONSCIOUS, 60, 0, false, false);
          entity.addPotionEffect(effInst);
          ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(entity.getEntityId(), effInst));
        } 
      } 
      
      if (targets.size() > 0) {
        ((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf((Entity)player, (IPacket)new SAnimateHandPacket((Entity)player, 0));
      }
      this.hasLanded = true;
      if (this.hasLanded) {
        WyNetwork.sendTo(new SUpdateExtraDataPacket(player, this), player);
      }
    } 
    DiableJambeAbility diableJambeAbility = (DiableJambeAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)DiableJambeAbility.INSTANCE);
    boolean isAbilityEnabled = (diableJambeAbility != null && diableJambeAbility.isContinuous());
    
    if (isAbilityEnabled && !player.onGround && !this.hasLanded) {
      PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    }
  }
  
  public boolean hasLanded() {
    return this.hasLanded;
  }


  
  public CompoundNBT getExtraData() {
    CompoundNBT nbt = new CompoundNBT();
    nbt.putBoolean("hasLanded", this.hasLanded);
    return nbt;
  }


  
  public void setExtraData(CompoundNBT nbt) {
    this.hasLanded = nbt.getBoolean("hasLanded");
  }


  
  public void resetFallDamage(LivingEntity player) {
    this.hasFallDamage = true;
  }


  
  public boolean hasFallDamage() {
    return this.hasFallDamage;
  }


  
  public IAnimation getAnimation() {
    return (IAnimation)ConcasseAnimation.INSTANCE;
  }


  
  public boolean isAnimationActive() {
    return (isOnCooldown() && !hasLanded());
  }
}


