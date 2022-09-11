package xyz.pixelatedw.mineminenomi.abilities.goe;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.goe.DragonsRoarProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;

public class DragonsRoarAbility extends RepeaterAbility {
  public static final DragonsRoarAbility INSTANCE = new DragonsRoarAbility();

  
  public DragonsRoarAbility() {
    super("Dragon's Roar", AbilityHelper.getDevilFruitCategory());
    setDescription("The user shouts and creates a series of powerful sound shockwaves");
    setMaxCooldown(8.0D);
    setMaxRepeaterCount(10, 6);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    float size = (Math.abs(getMaxRepeaterCount() - getRepeaterCount() - 10) * 2);
    DragonsRoarProjectile proj = new DragonsRoarProjectile(player.world, (LivingEntity)player, size);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.5F, 0.0F);
    
    return true;
  }
}


