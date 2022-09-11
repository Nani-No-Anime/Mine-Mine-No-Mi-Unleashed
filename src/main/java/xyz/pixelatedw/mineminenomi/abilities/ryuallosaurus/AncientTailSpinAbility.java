package xyz.pixelatedw.mineminenomi.abilities.ryuallosaurus;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.animations.TimeAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.AllosaurusHeavyZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.AllosaurusWalkZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.renderers.animations.BodyRotateAnimation;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class AncientTailSpinAbility extends Ability implements IFormRequiredAbility, IAnimatedAbility {
  public static final AncientTailSpinAbility INSTANCE = new AncientTailSpinAbility();
  private static final BodyRotateAnimation ANIMATION = new BodyRotateAnimation(12.0F);

  
  public AncientTailSpinAbility() {
    super("Ancient Tail Spin", AbilityHelper.getDevilFruitCategory());
    setDescription("");
    setMaxCooldown(10.0D);
    
    this.onUseEvent = this::onUseEvent;
  }


  
  private boolean onUseEvent(PlayerEntity player) {
    List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 5.5D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
    list.remove(player);
    
    getAnimation().start();
    
    list.forEach(entity -> {
          entity.attackEntityFrom((DamageSource)ModDamageSource.causeAbilityDamage((LivingEntity)player, this), 15.0F);
          
          Vec3d dist = entity.getPositionVec().subtract(player.getPositionVec()).add(0.0D, -1.0D, 0.0D);
          
          double speedReduction = 2.0D;
          double xSpeed = -dist.x / speedReduction;
          double zSpeed = -dist.z / speedReduction;
          entity.setMotion(-xSpeed, 0.10000000149011612D, -zSpeed);
          entity.velocityChanged = true;
        });
    return true;
  }


  
  public ZoanInfo[] getRequiredForms(PlayerEntity player) {
    return new ZoanInfo[] { (ZoanInfo)AllosaurusHeavyZoanInfo.INSTANCE, (ZoanInfo)AllosaurusWalkZoanInfo.INSTANCE };
  }


  
  public TimeAnimation getAnimation() {
    return (TimeAnimation)ANIMATION;
  }


  
  public boolean isAnimationActive() {
    return (isOnCooldown() && getCooldown() > getMaxCooldown() - 10.0D);
  }
}


