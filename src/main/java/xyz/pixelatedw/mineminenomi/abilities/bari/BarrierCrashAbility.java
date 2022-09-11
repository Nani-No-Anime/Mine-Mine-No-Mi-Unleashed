package xyz.pixelatedw.mineminenomi.abilities.bari;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.bari.BarrierCrashProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class BarrierCrashAbility extends Ability {
  public static final BarrierCrashAbility INSTANCE = new BarrierCrashAbility();

  
  public BarrierCrashAbility() {
    super("Barrier Crash", AbilityHelper.getDevilFruitCategory());
    setDescription("Launches a barrier towards the opponent, smashing it against them");
    
    setMaxCooldown(8.0D);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    BarrierCrashProjectile proj = new BarrierCrashProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
    
    return true;
  }
}


