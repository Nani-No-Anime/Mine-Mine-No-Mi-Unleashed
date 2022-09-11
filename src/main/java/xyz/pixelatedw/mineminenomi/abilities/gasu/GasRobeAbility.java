package xyz.pixelatedw.mineminenomi.abilities.gasu;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.gasu.GasRobeProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;

public class GasRobeAbility extends RepeaterAbility {
  public static final GasRobeAbility INSTANCE = new GasRobeAbility();

  
  public GasRobeAbility() {
    super("Gas Robe", AbilityHelper.getDevilFruitCategory());
    setDescription("Launches a cloud of poisonous gas at the opponent");
    setMaxCooldown(10.0D);
    setMaxRepeaterCount(6, 3);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    GasRobeProjectile proj = new GasRobeProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.5F, 2.0F);
    
    return true;
  }
}


