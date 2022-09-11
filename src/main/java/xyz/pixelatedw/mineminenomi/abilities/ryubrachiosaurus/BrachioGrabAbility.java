package xyz.pixelatedw.mineminenomi.abilities.ryubrachiosaurus;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.entities.zoan.BrachiosaurusHeavyZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.renderers.animations.PointBothArmsAnimation;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

public class BrachioGrabAbility extends ChargeableAbility implements IFormRequiredAbility, IAnimatedAbility {
  public static final BrachioGrabAbility INSTANCE = new BrachioGrabAbility();
  private LivingEntity grabbedEntity = null;

  
  public BrachioGrabAbility() {
    super("Brachio Grab", AbilityHelper.getDevilFruitCategory());
    setDescription("Grabs an opponent and squashes them");
    setMaxCooldown(15.0D);
    setMaxChargeTime(3.0D);
    
    this.onStartChargingEvent = this::onStartChargingEvent;
    this.duringChargingEvent = this::duringChargingEvent;
  }

  
  private boolean onStartChargingEvent(PlayerEntity player) {
    List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 4.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
    list.remove(player);
    list.removeIf(entity -> AbilityHelper.isTargetBlockingAbility((LivingEntity)player, entity));
    if (!HakiHelper.hasHardeningActive((LivingEntity)player)) {
      list.removeIf(entity -> DevilFruitCapability.get(entity).isLogia());
    }
    this.grabbedEntity = (list.size() > 0) ? list.get(0) : null;
    return (list.size() > 0);
  }

  
  private void duringChargingEvent(PlayerEntity player, int chargeTimer) {
    if (!this.grabbedEntity.isAlive() || (DevilFruitCapability.get(this.grabbedEntity).isLogia() && !HakiHelper.hasHardeningActive((LivingEntity)player)) || AbilityHelper.isTargetBlockingAbility((LivingEntity)player, this.grabbedEntity)) {
      
      endCharging(player);
      
      return;
    } 
    this.grabbedEntity.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1));
    player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1));
    
    this.grabbedEntity.attackEntityFrom((DamageSource)ModDamageSource.causeAbilityDamage((LivingEntity)player, (Ability)this, "player"), 6.0F);
  }


  
  public IAnimation getAnimation() {
    return (IAnimation)PointBothArmsAnimation.INSTANCE;
  }


  
  public boolean isAnimationActive() {
    return isCharging();
  }


  
  public ZoanInfo[] getRequiredForms(PlayerEntity player) {
    return new ZoanInfo[] { (ZoanInfo)BrachiosaurusHeavyZoanInfo.INSTANCE };
  }
}


