package xyz.pixelatedw.mineminenomi.abilities.fishmankarate;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.fishmankarate.YarinamiProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

public class YarinamiAbility extends ChargeableAbility {
  public static final YarinamiAbility INSTANCE = new YarinamiAbility();

  
  public YarinamiAbility() {
    super("Yarinami", AbilityHelper.getRacialCategory());
    setDescription("The user fires a densely compressed spear-shaped waterbullet at the opponent");
    setMaxCooldown(12.0D);
    setMaxChargeTime(3.0D);
    
    this.onEndChargingEvent = this::onEndChargingEvent;
  }

  
  private boolean onEndChargingEvent(PlayerEntity player) {
    YarinamiProjectile proj = new YarinamiProjectile(player.world, (LivingEntity)player);
    if (player.canSwim())
      proj.setDamage(40.0F); 
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.5F, 0.0F);
    
    return true;
  }
}


