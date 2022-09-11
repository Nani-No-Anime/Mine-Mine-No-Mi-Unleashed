package xyz.pixelatedw.mineminenomi.abilities.hie;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.hie.IceBallProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class IceBallAbility extends Ability {
  public static final Ability INSTANCE = new IceBallAbility();

  
  public IceBallAbility() {
    super("Ice Ball", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(10.0D);
    setDescription("Creates a sphere of ice where the projectile hits");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    IceBallProjectile proj = new IceBallProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
    
    return true;
  }
}


