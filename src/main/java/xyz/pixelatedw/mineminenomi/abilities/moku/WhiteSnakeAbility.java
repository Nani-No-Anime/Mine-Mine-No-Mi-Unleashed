package xyz.pixelatedw.mineminenomi.abilities.moku;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.moku.WhiteSnakeProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class WhiteSnakeAbility extends Ability {
  public static final Ability INSTANCE = new WhiteSnakeAbility();

  
  public WhiteSnakeAbility() {
    super("White Snake", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(12.0D);
    setDescription("Launches a long dense cloud of smoke in the shape of a snake that will damage and give poison to its target");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    WhiteSnakeProjectile proj = new WhiteSnakeProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 1.0F);
    
    return true;
  }
}


