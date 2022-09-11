package xyz.pixelatedw.mineminenomi.abilities.fishmankarate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.fishmankarate.UchimizuProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;

public class UchimizuAbility extends RepeaterAbility {
  public static final Ability INSTANCE = (Ability)new UchimizuAbility();

  
  public UchimizuAbility() {
    super("Uchimizu", AbilityHelper.getRacialCategory());
    setDescription("The user hurls big and fast water droplets at the opponent");
    setMaxCooldown(4.0D);
    setMaxRepeaterCount(8, 2);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    UchimizuProjectile proj = new UchimizuProjectile(player.world, (LivingEntity)player);
    if (player.canSwim())
      proj.setDamage(3.0F); 
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.2F);
    
    return true;
  }
}


