package xyz.pixelatedw.mineminenomi.abilities.horo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.horo.MiniHollowProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;

public class MiniHollowAbility extends RepeaterAbility {
  public static final MiniHollowAbility INSTANCE = new MiniHollowAbility();

  
  public MiniHollowAbility() {
    super("Mini Hollow", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(9.0D);
    setMaxRepeaterCount(4, 5);
    setDescription("Launches small ghosts at the opponent, exploding upon impact");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    MiniHollowProjectile proj = new MiniHollowProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 3.0F);
    
    return true;
  }
}


