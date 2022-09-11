package xyz.pixelatedw.mineminenomi.abilities;


import java.util.List;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;

public abstract class RunningSmashAbility
  extends PassiveAbility {
  private double smashArea = 1.5D;
  private float smashDamage = 2.0F;

  
  public RunningSmashAbility(String name, APIConfig.AbilityCategory category, double area, float damage) {
    super(name, category);
    
    this.duringPassiveEvent = this::duringPassiveEvent;
    hideInGUI(false);
  }

  
  private void duringPassiveEvent(PlayerEntity player) {
    if (player.isSprinting()) {
      
      List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, this.smashArea);
      targets.remove(player);
      
      for (LivingEntity target : targets) {
        
        Vec3d speed = WyHelper.propulsion((LivingEntity)player, 2.0D, 2.0D);
        target.attackEntityFrom(DamageSource.causePlayerDamage(player), this.smashDamage);
        target.setMotion(speed.x, 0.2D, speed.z);
      } 
    } 
  }
}


