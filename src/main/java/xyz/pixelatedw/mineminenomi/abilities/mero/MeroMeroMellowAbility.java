package xyz.pixelatedw.mineminenomi.abilities.mero;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.mero.MeroMeroMellowProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class MeroMeroMellowAbility extends Ability {
  public static final MeroMeroMellowAbility INSTANCE = new MeroMeroMellowAbility();

  
  public MeroMeroMellowAbility() {
    super("Mero Mero Mellow", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(15.0D);
    setDescription("Fires a heart-shaped beam, turning every enemy in its path into stone");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    MeroMeroMellowProjectile proj = new MeroMeroMellowProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
    
    return true;
  }
}


