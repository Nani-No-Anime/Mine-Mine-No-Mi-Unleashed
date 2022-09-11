package xyz.pixelatedw.mineminenomi.abilities.awa;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.awa.RelaxHourProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;

public class RelaxHourAbility extends RepeaterAbility {
  public static final RelaxHourAbility INSTANCE = new RelaxHourAbility();

  
  public RelaxHourAbility() {
    super("Relax Hour", AbilityHelper.getDevilFruitCategory());
    setDescription("Fires a barrage of cleaning bubbles, leaving their targets weakened and immobile");
    setMaxCooldown(4.0D);
    setMaxRepeaterCount(3, 2);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    RelaxHourProjectile proj = new RelaxHourProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 1.0F, 1.0F);
    
    return true;
  }
}


