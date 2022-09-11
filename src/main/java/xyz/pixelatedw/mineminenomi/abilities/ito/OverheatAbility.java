package xyz.pixelatedw.mineminenomi.abilities.ito;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.ito.OverheatProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class OverheatAbility extends Ability {
  public static final OverheatAbility INSTANCE = new OverheatAbility();

  
  public OverheatAbility() {
    super("Overheat", AbilityHelper.getDevilFruitCategory());
    setDescription("The user shoots a rope made of heated strings at the opponent, exploding upon impact");
    setMaxCooldown(10.0D);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    OverheatProjectile proj = new OverheatProjectile(player.world, (LivingEntity)player);
    proj.setPosition(proj.getPosX(), player.getPosY() + player.getEyeHeight() - 0.4000000059604645D, proj.getPosZ());
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.25F, 0.0F);
    
    return true;
  }
}


