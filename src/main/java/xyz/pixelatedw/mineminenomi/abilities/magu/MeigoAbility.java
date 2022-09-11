package xyz.pixelatedw.mineminenomi.abilities.magu;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.magu.MeigoProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class MeigoAbility extends Ability {
  public static final Ability INSTANCE = new MeigoAbility();

  
  public MeigoAbility() {
    super("Meigo", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(30.0D);
    setDescription("The user transforms their arm into magma and thrusts it at the opponent at incredible speeds");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    MeigoProjectile proj = new MeigoProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
    
    return true;
  }
}


