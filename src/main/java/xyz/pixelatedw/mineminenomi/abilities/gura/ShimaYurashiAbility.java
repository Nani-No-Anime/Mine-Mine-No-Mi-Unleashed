package xyz.pixelatedw.mineminenomi.abilities.gura;
import com.google.common.collect.ImmutableList;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.animations.TimeAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.GroundParticlesEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.gura.AirCrackParticleEffect;
import xyz.pixelatedw.mineminenomi.renderers.animations.gura.KaishinAnimation;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

public class ShimaYurashiAbility extends ChargeableAbility implements IAnimatedAbility {
  public static final Ability INSTANCE = (Ability)new ShimaYurashiAbility();
  
  private static final int EXPLOSION_RADIUS = 27;
  private static final int EXPLOSION_DEPTH = 10;
  private static final ParticleEffect PARTICLES = (ParticleEffect)new GroundParticlesEffect(27, 800);
  private static final ParticleEffect AIR_GRAB_PARTICLES = (ParticleEffect)new AirCrackParticleEffect();
  
  public List<FallingBlockEntity> entityBlocks = new ArrayList<>();

  
  public ShimaYurashiAbility() {
    super("Shima Yurashi", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(60.0D);
    setMaxChargeTime(5.0D);
    setDescription("The user grabs the air and pulls it downwards after which the nearby land and entities are sent flying");
    
    this.onStartChargingEvent = this::onStartChargingEvent;
    this.duringChargingEvent = this::duringChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
    this.duringCooldownEvent = this::duringCooldownEvent;
  }

  
  private boolean onStartChargingEvent(PlayerEntity player) {
    if (!player.onGround) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONLY_IN_GROUND, new Object[] { getName() }));
      return false;
    } 


    
    List<BlockPos> randomPositions = (List<BlockPos>)WyHelper.getNearbyBlocks(player.getPosition(), (IWorld)player.world, 13, (List)ImmutableList.of(Blocks.AIR)).stream().filter(pos -> DefaultProtectionRules.CORE_FOLIAGE_ORE.check(player.world, (BlockPos)pos, player.world.getBlockState((BlockPos)pos))).collect(Collectors.toList());
    Collections.shuffle(randomPositions, player.getRNG());
    
    this

      
      .entityBlocks = (List<FallingBlockEntity>)randomPositions.stream().limit(600L).map(pos -> new FallingBlockEntity(player.world, pos.getX(), pos.getY(), pos.getZ(), player.world.getBlockState(pos))).collect(Collectors.toList());
    
    return CommonConfig.INSTANCE.isAbilityGriefingEnabled();
  }

  
  private void duringChargingEvent(PlayerEntity player, int chargeTime) {
    if (chargeTime % 2 == 0) {
      PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    }
    player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 10, 0, false, false));
    
    List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 27.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
    targets.remove(player);
    
    targets.stream()
      .filter(target -> 
        (target != null && target.canEntityBeSeen((Entity)player) && target.isAlive() && target.world.rayTraceBlocks(new RayTraceContext(target.getPositionVec(), target.getPositionVec().add(0.0D, -10.0D, 0.0D), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.ANY, (Entity)target)).getType().equals(RayTraceResult.Type.BLOCK)))


      
      .forEach(target -> target.addPotionEffect(new EffectInstance(ModEffects.DIZZY, 10, 0, false, false)));

    
    if (chargeTime < 30 && !getAnimation().isPlaying()) {
      getAnimation().start();
    }
  }
  
  private boolean onEndChargingEvent(PlayerEntity player) {
    List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 27.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
    targets.remove(player);
    
    targets.stream().filter(target -> (target != null && target.isAlive() && player.canEntityBeSeen((Entity)target)))
      .forEach(target -> {
          double distance = Math.sqrt(target.getDistanceSq((Entity)player));
          
          double damage = 80.0D - distance / 2.0D;
          
          target.attackEntityFrom(ModDamageSource.causeAbilityDamage((LivingEntity)player, INSTANCE).setDamageBypassingLogiaInvulnerability().setDamageBypassesArmor(), (float)damage);
          
          if (target.onGround) {
            Vec3d dirVec = player.getPositionVec().subtract(target.getPositionVec()).normalize().mul(25.0D, 1.0D, 25.0D);
            
            target.setMotion(-dirVec.x, 3.0D, -dirVec.z);
            target.velocityChanged = true;
            damage *= 1.5D;
          } 
        });
    EntityRayTraceResult trace = WyHelper.rayTraceEntities((Entity)player, 1.5D);
    AIR_GRAB_PARTICLES.spawn(player.world, trace.getHitVec().getX(), player.getPosY() + 0.5D, trace.getHitVec().getZ(), 0.0D, 0.0D, 0.0D);
    
    Iterator<FallingBlockEntity> iter = this.entityBlocks.iterator();
    while (iter.hasNext()) {
      
      FallingBlockEntity entity = iter.next();
      Vec3d dirVec = player.getPositionVec().subtract(entity.getPositionVec()).normalize().mul(2.0D, 2.0D, 2.0D);
      entity.setPosition(player
          .getPosition().getX() + WyHelper.randomDouble() * 20.0D, player
          .getPosition().getY() + WyHelper.randomDouble() * 3.0D, player
          .getPosition().getZ() + WyHelper.randomDouble() * 20.0D);
      entity.setMotion(-dirVec.x, 1.0D + dirVec.y, -dirVec.z);


      
      entity.velocityChanged = true;
      entity.shouldDropItem = false;
      entity.fallTime = 1;
      player.world.addEntity((Entity)entity);
    } 
    
    int craterRadius = 72;
    AbilityHelper.createSphereWithProtection(player.world, player.getPosition(), craterRadius, 10, Blocks.AIR, 3, DefaultProtectionRules.CORE_FOLIAGE_ORE_LIQUID);
    player.world.playSound(null, player.getPosition(), ModSounds.GURA_SFX, SoundCategory.PLAYERS, 2.0F, 1.0F);
    
    getAnimation().stop();
    
    return true;
  }



  
  private void duringCooldownEvent(PlayerEntity player, int i) {}


  
  public TimeAnimation getAnimation() {
    return (TimeAnimation)KaishinAnimation.INSTANCE;
  }


  
  public boolean isAnimationActive() {
    return isCharging();
  }
}


