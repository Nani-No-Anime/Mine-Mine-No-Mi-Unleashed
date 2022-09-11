package xyz.pixelatedw.mineminenomi.abilities.fishmankarate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.fishmankarate.MurasameProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;

public class MurasameAbility extends RepeaterAbility {
  public static final Ability INSTANCE = (Ability)new MurasameAbility();

  
  public MurasameAbility() {
    super("Murasame", AbilityHelper.getRacialCategory());
    setDescription("The user fires densely compressed shark-shaped waterbullets at the opponent");
    setMaxCooldown(15.0D);
    setMaxRepeaterCount(5, 3);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    MurasameProjectile proj = new MurasameProjectile(player.world, (LivingEntity)player);
    if (player.canSwim())
      proj.setDamage(15.0F); 
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
    
    return true;
  }
}


