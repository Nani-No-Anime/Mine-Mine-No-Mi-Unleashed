package xyz.pixelatedw.mineminenomi.abilities.suna;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.suna.BarjanProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class BarjanAbility extends Ability {
  public static final Ability INSTANCE = new BarjanAbility();

  
  public BarjanAbility() {
    super("Barjan", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(10.0D);
    setDescription("Launches a crescent-shaped wave of sand at the opponent, which dehydrates them");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    BarjanProjectile proj = new BarjanProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
    
    return true;
  }
}


