package xyz.pixelatedw.mineminenomi.abilities.brawler;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.renderers.animations.PointBothArmsAnimation;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

public class SpinningBrawlAbility extends ChargeableAbility implements IAnimatedAbility {
  public static final SpinningBrawlAbility INSTANCE = new SpinningBrawlAbility();
  private LivingEntity grabbedEntity = null;
  private int yaw = 0;

  
  public SpinningBrawlAbility() {
    super("Spinning Brawl", AbilityHelper.getStyleCategory());
    setDescription("Grabs an opponent from the back and launches it into the ground");
    setMaxCooldown(12.0D);
    setMaxChargeTime(5.0D);
    
    this.onStartChargingEvent = this::onUseEvent;
    this.duringChargingEvent = this::duringChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    if (!AbilityHelper.canUseBrawlerAbilities((LivingEntity)player)) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_FIST, new Object[0]));
      return false;
    } 
    
    RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player, 3.0D);
    if (mop instanceof EntityRayTraceResult) {
      
      EntityRayTraceResult entityRayTraceResult = (EntityRayTraceResult)mop;
      if (entityRayTraceResult.getEntity() instanceof LivingEntity) {
        
        LivingEntity e = (LivingEntity)entityRayTraceResult.getEntity();
        if (!e.isAlive() || (DevilFruitCapability.get(e).isLogia() && !HakiHelper.hasHardeningActive((LivingEntity)player)) || AbilityHelper.isTargetBlockingAbility((LivingEntity)player, e))
          return false; 
        this.grabbedEntity = e;
      } 
    } else {
      
      return false;
    } 
    return true;
  }

  
  private void duringChargingEvent(PlayerEntity player, int chargeTimer) {
    if (!this.grabbedEntity.isAlive() || (DevilFruitCapability.get(this.grabbedEntity).isLogia() && !HakiHelper.hasHardeningActive((LivingEntity)player)) || AbilityHelper.isTargetBlockingAbility((LivingEntity)player, this.grabbedEntity)) {
      
      endCharging(player);
      
      return;
    } 
    this.grabbedEntity.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1));
    this.grabbedEntity.setMotion(0.0D, 0.0D, 0.0D);
    player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1));
    
    if (this.yaw + 10 > 360)
      this.yaw = 0; 
    this.yaw += 10;
    
    float distance = 2.0F;
    Vec3d lookVec = player.getLookVec();
    Vec3d pos = new Vec3d(player.getPosX() + lookVec.x * distance, player.getPosY() + (player.getEyeHeight() / 2.0F) + lookVec.y * distance, player.getPosZ() + lookVec.z * distance);
    player.setPositionAndRotation(player.getPosX(), player.getPosY(), player.getPosZ(), this.yaw, 0.0F);
    player.setPositionAndUpdate(player.getPosX(), player.getPosY(), player.getPosZ());
    this.grabbedEntity.setPositionAndUpdate(pos.x, pos.y, pos.z);
    
    List<LivingEntity> list = WyHelper.getEntitiesNearSphere(player.getPosition(), player.world, 4.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
    list.remove(player);
    list.remove(this.grabbedEntity);
    if (!HakiHelper.hasHardeningActive((LivingEntity)player)) {
      list.removeIf(entity -> DevilFruitCapability.get(entity).isLogia());
    }
    list.forEach(e -> {
          boolean hit = e.attackEntityFrom((DamageSource)ModDamageSource.causeAbilityDamage((LivingEntity)player, (Ability)this, "player"), 8.0F);
          if (hit) {
            Vec3d speed = WyHelper.propulsion((LivingEntity)player, 2.0D, 2.0D);
            e.setMotion(speed.x, 1.0D, speed.z);
            e.velocityChanged = true;
          } 
        });
  }



  
  private boolean onEndChargingEvent(PlayerEntity player) {
    if (this.grabbedEntity == null || getChargeTime() >= getMaxChargeTime()) {
      return false;
    }
    this.grabbedEntity.attackEntityFrom(DamageSource.causePlayerDamage(player), 20.0F);
    return true;
  }


  
  public IAnimation getAnimation() {
    return (IAnimation)PointBothArmsAnimation.INSTANCE;
  }


  
  public boolean isAnimationActive() {
    return isCharging();
  }
}


