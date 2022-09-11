package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
import xyz.pixelatedw.mineminenomi.abilities.suna.SunaHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.zoan.ShinokuniZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.packets.server.SFlightValuePacket;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.SpecialFlyingParticleEffect;
import xyz.pixelatedw.mineminenomi.renderers.animations.SpecialFlyAnimation;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public class SpecialFlyAbility extends PassiveAbility implements IAnimatedAbility {
  public static final SpecialFlyAbility INSTANCE = new SpecialFlyAbility();
  
  private static final ParticleEffect PARTICLES_SUNA = (ParticleEffect)new SpecialFlyingParticleEffect(ModParticleTypes.SUNA2);
  private static final ParticleEffect PARTICLES_GASU = (ParticleEffect)new SpecialFlyingParticleEffect(ModParticleTypes.GASU);
  private static final ParticleEffect PARTICLES_MOKU = (ParticleEffect)new SpecialFlyingParticleEffect(ModParticleTypes.MOKU);
  
  private boolean isFlying;

  
  public SpecialFlyAbility() {
    super("Special Fly", AbilityHelper.getDevilFruitCategory());
    setDescription("Allows the user to fly");
    this.duringPassiveEvent = this::duringPassiveEvent;
    hideInGUI(false);
    setDisplayName("Elemental Flight");
    setCustomTexture("special_fly");
  }


  
  public void tick(PlayerEntity player) {
    if ((isPaused() || !canUse(player)) && !player.isCreative() && !player.isSpectator() && player.abilities.allowFlying) {
      
      player.abilities.allowFlying = false;
      player.abilities.isFlying = false;
      if (player instanceof ServerPlayerEntity) {
        ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayerAbilitiesPacket(player.abilities));
      }
    } 
    super.tick(player);
  }

  
  private void duringPassiveEvent(PlayerEntity player) {
    if (!CommonConfig.INSTANCE.isSpecialFlyingEnabled() || player.isCreative() || player.isSpectator()) {
      return;
    }
    IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
    boolean isSuna = props.hasDevilFruit(ModAbilities.SUNA_SUNA_NO_MI);
    
    if (player.getAttribute(ModAttributes.JUMP_HEIGHT).getValue() < 0.0D || (isSuna && player.isWet())) {
      
      player.abilities.allowFlying = false;
      player.abilities.isFlying = false;
      if (player instanceof ServerPlayerEntity) {
        ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayerAbilitiesPacket(player.abilities));
      }
      return;
    } 
    boolean isGasu = props.hasDevilFruit(ModAbilities.GASU_GASU_NO_MI);
    boolean isMoku = props.hasDevilFruit(ModAbilities.MOKU_MOKU_NO_MI);
    this.isFlying = player.abilities.isFlying;
    boolean flight = (!AbilityHelper.isAffectedByWater((LivingEntity)player) && !player.isPotionActive(ModEffects.ABILITY_OFF) && !DevilFruitHelper.kairosekiChecks((LivingEntity)player));
    
    if (!player.world.isRemote) {
      
      WyNetwork.sendTo(new SFlightValuePacket(flight), player);
      player.abilities.allowFlying = flight;
      
      if (this.isFlying)
      {
        if (isSuna) {
          PARTICLES_SUNA.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
        } else if (isMoku) {
          PARTICLES_MOKU.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
        } else if (isGasu) {
          PARTICLES_GASU.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
        } 
      }
    } 
    if (this.isFlying) {
      
      double maxDifference = isGasu ? 64.0D : 40.0D;
      float speedMultiplier = isGasu ? 1.0F : (isMoku ? 0.95F : (SunaHelper.isFruitBoosted(player) ? 1.0F : 0.93F));
      if (ShinokuniZoanInfo.INSTANCE.isActive((LivingEntity)player)) {
        speedMultiplier = (float)(speedMultiplier + 0.05D);
      }
      player.setMotion(player.getMotion().mul(speedMultiplier, (speedMultiplier - 0.25F), speedMultiplier));
      
      if (player.isSprinting()) {
        
        player.setMotion(player.getMotion().mul(0.67D, 1.0D, 0.67D));
        player.setSprinting(false);
      } 
      
      boolean canFly = DevilFruitHelper.isFlyingAtMaxHeight(player, maxDifference);
      DevilFruitHelper.vanillaFlightThreshold((LivingEntity)player, canFly ? 256 : ((int)player.getPosY() - 1));
    } 
    
    if (!flight) {
      player.abilities.isFlying = false;
    }
  }

  
  public IAnimation getAnimation() {
    return (IAnimation)SpecialFlyAnimation.INSTANCE;
  }


  
  public boolean isAnimationActive() {
    return this.isFlying;
  }
}


