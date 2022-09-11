package xyz.pixelatedw.mineminenomi.abilities.bomu;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.bomu.NoseFancyCannonProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class NoseFancyCannonAbility extends Ability {
  public static final Ability INSTANCE = new NoseFancyCannonAbility();

  
  public NoseFancyCannonAbility() {
    super("Nose Fancy Cannon", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(5.0D);
    setDescription("Shoots dried mucus at the opponent, which explodes on impact");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    NoseFancyCannonProjectile proj = new NoseFancyCannonProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
    
    return true;
  }
}


