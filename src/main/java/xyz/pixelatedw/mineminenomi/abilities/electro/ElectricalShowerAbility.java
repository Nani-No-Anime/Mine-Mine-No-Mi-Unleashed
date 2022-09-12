package xyz.pixelatedw.mineminenomi.abilities.electro;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
import xyz.pixelatedw.mineminenomi.api.abilities.IFallDamageBlockingAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningBallEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.particles.effects.electro.ElectroChargingParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

public class ElectricalShowerAbility extends ChargeableAbility implements IFallDamageBlockingAbility {
  public static final ElectricalShowerAbility INSTANCE = new ElectricalShowerAbility();
  
  private int boltsLeft = 9;
  private static final ElectroChargingParticleEffect PARTICLES = new ElectroChargingParticleEffect();
  private LightningBallEntity ballEntity = null;
  
  private static final int COOLDOWN = 12;
  private static final float CHARGE_TIME = 3.0F;
  boolean hasFallDamage = true;
  
  public ElectricalShowerAbility() {
    super("Electrical Shower", AbilityHelper.getRacialCategory());
    setMaxCooldown(12.0D);
    setMaxChargeTime(3.0D);
    addInPool(new AbilityPool[] { AbilityPool.MINK_ELECTRO });
    setDescription("Launches the user into the air and showers down lightning bolts underneath");
    
    this.onStartChargingEvent = this::onStartChargingEvent;
    this.duringChargingEvent = this::duringChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
  }

  
  private boolean onStartChargingEvent(PlayerEntity player) {
    if (!AbilityHelper.canUseMomentumAbility(player)) {
      return false;
    }
    this.boltsLeft = (int)WyHelper.randomWithRange(8, 10);
    
    this.hasFallDamage = false;
    return true;
  }

  
  private void duringChargingEvent(PlayerEntity player, int i) {
    if (i % 5 == 0) {
      PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    }
    player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 4, 1, false, false));
    
    float percentage = 1.0F - getChargeTime() / getMaxChargeTime();
    
    if (this.ballEntity == null) {
      
      LightningBallEntity ball = new LightningBallEntity((Entity)player, player.getPosX(), player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
      
      player.world.addEntity((Entity)ball);
      this.ballEntity = ball;
    } else {
      
      float distance = percentage * 2.0F;
      Vec3d lookVec = player.getLookVec();
      Vec3d pos = new Vec3d(player.getPosX() + lookVec.x * distance, player.getPosY() + player.getEyeHeight() * 0.85D + lookVec.y * distance, player.getPosZ() + lookVec.z * distance);
      this.ballEntity.setSize(percentage * 0.3F);
      this.ballEntity.setLightningLength(3.0F);
      this.ballEntity.setPositionAndRotation(pos.getX(), pos.getY(), pos.getZ(), player.rotationYaw, player.rotationPitch);
    } 

    
    if (percentage > 0.65D) {
      
      Vec3d startVec = player.getPositionVec();

      
      boolean blockUnder = player.world.rayTraceBlocks(new RayTraceContext(startVec, startVec.add(0.0D, -15.0D, 0.0D), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, (Entity)player)).getType().equals(RayTraceResult.Type.BLOCK);
      
      if (blockUnder) {
        
        player.setMotion((player.getMotion()).x, 1.0D, (player.getMotion()).z);
        player.velocityChanged = true;
      } else {
        AbilityHelper.slowEntityFall((LivingEntity)player);
      } 
    } 
  }
  
  private boolean onEndChargingEvent(PlayerEntity player) {
    if (this.ballEntity != null) {
      
      this.ballEntity.remove();
      this.ballEntity = null;
    } 
    Vec3d startVec = player.getPositionVec();
    
    boolean canShoot = !player.world.rayTraceBlocks(new RayTraceContext(startVec, startVec.add(0.0D, -10.0D, 0.0D), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, (Entity)player)).getType().equals(RayTraceResult.Type.BLOCK);
    if (!canShoot) {
      return true;
    }
    EleclawAbility eleclawAbility = (EleclawAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)EleclawAbility.INSTANCE);
    
    SulongAbility sulongAbility = (SulongAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)SulongAbility.INSTANCE);
    boolean sulongEnabled = (sulongAbility != null && sulongAbility.isContinuous());
    
    if (eleclawAbility != null && eleclawAbility.isContinuous()) {
      
      BlockRayTraceResult blockRayTraceResult = WyHelper.rayTraceBlocks((Entity)player, 50.0D);
      double beamDistance = Math.sqrt(player.getDistanceSq((blockRayTraceResult.getHitVec()).x, (blockRayTraceResult.getHitVec()).y, (blockRayTraceResult.getHitVec()).z));
      
      ((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf((Entity)player, (IPacket)new SAnimateHandPacket((Entity)player, 0));
      
      for (int z = 0; z < this.boltsLeft; z++) {
        
        LightningEntity bolt = new LightningEntity((Entity)player, player.getPosX() + WyHelper.randomWithRange(-3, 3), player.getPosY(), player.getPosZ() + WyHelper.randomWithRange(-3, 3), player.rotationYaw, player.rotationPitch, ((int)beamDistance + 2), 8.0F);
        
        bolt.setAliveTicks(5);
        bolt.setDamage((10 + (sulongEnabled ? 10 : 0)));
        bolt.setExplosion(2, false, 0.0F);
        bolt.setSize(0.025F);
        bolt.disableLightningMimic();
        bolt.setBoxSizeDivision(0.025D);
        bolt.disableExplosionKnockback();
        bolt.setSegments((int)(beamDistance + WyHelper.randomWithRange(-4, 8)));
        player.world.addEntity((Entity)bolt);
      } 
      
      eleclawAbility.reduceUsage(player, 2);
    } 
    
    return true;
  }

  
  public void resetFallDamage(LivingEntity player) {
    this.hasFallDamage = true;
  }


  
  public boolean hasFallDamage() {
    return this.hasFallDamage;
  }
}


