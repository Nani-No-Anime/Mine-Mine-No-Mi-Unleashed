package xyz.pixelatedw.mineminenomi.abilities.goe;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.goe.TodorokiProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class TodorokiAbility extends Ability {
  public static final Ability INSTANCE = new TodorokiAbility();

  
  public TodorokiAbility() {
    super("Todoroki", AbilityHelper.getDevilFruitCategory());
    setDescription("The user shouts and creates a powerful sound blast");
    setMaxCooldown(8.0D);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    TodorokiProjectile proj = new TodorokiProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 4.5F, 1.0F);
    
    return true;
  }
}


