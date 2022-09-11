package xyz.pixelatedw.mineminenomi.abilities.horo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.horo.NegativeHollowProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class NegativeHollowAbility extends Ability {
  public static final NegativeHollowAbility INSTANCE = new NegativeHollowAbility();

  
  public NegativeHollowAbility() {
    super("Negative Hollow", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(4.0D);
    setDescription("The user launches a ghost that drains the target's will, debuffing them");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    NegativeHollowProjectile proj = new NegativeHollowProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
    
    return true;
  }
}


