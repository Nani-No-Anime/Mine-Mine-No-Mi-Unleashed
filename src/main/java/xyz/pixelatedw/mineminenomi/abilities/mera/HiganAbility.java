package xyz.pixelatedw.mineminenomi.abilities.mera;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.mera.HiganProjectile;
import xyz.pixelatedw.mineminenomi.renderers.animations.PointArmAnimation;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;

public class HiganAbility extends RepeaterAbility implements IAnimatedAbility {
  public static final Ability INSTANCE = (Ability)new HiganAbility();

  
  public HiganAbility() {
    super("Higan", AbilityHelper.getDevilFruitCategory());
    setDescription("Turns the user's fingertips into flames and shoots bullets made of fire from them");
    setMaxCooldown(9.0D);
    setMaxRepeaterCount(10, 2);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    HiganProjectile proj = new HiganProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
    
    return true;
  }


  
  public IAnimation getAnimation() {
    return (IAnimation)PointArmAnimation.INSTANCE;
  }


  
  public boolean isAnimationActive() {
    return isContinuous();
  }
}


