package xyz.pixelatedw.mineminenomi.abilities.pero;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.pero.CandyWaveProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class CandyWaveAbility extends Ability {
  public static final CandyWaveAbility INSTANCE = new CandyWaveAbility();

  
  public CandyWaveAbility() {
    super("Candy Wave", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(12.0D);
    setDescription("Launches a wave of candy and traps enemies in hardened candy");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    CandyWaveProjectile proj = new CandyWaveProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
    
    return true;
  }
}


