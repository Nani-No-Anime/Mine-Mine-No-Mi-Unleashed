package xyz.pixelatedw.mineminenomi.abilities.mera;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.mera.JujikaProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class JujikaAbility extends Ability {
  public static final Ability INSTANCE = new JujikaAbility();

  
  public JujikaAbility() {
    super("Jujika", AbilityHelper.getDevilFruitCategory());
    setDescription("Launches a cross-shaped column of fire at the opponent");
    setMaxCooldown(12.0D);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    JujikaProjectile proj = new JujikaProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
    
    return true;
  }
}


