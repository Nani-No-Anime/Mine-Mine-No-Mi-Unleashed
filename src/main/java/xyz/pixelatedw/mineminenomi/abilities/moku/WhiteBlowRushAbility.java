package xyz.pixelatedw.mineminenomi.abilities.moku;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.moku.WhiteBlowProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;

public class WhiteBlowRushAbility extends RepeaterAbility {
  public static final Ability INSTANCE = (Ability)new WhiteBlowRushAbility();


  
  public WhiteBlowRushAbility() {
    super("White Blow Rush", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(6.0D);
    setMaxRepeaterCount(8, 3);
    setDescription("Shoots clouds of smoke to engulf the opponent and trap them");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    WhiteBlowProjectile proj = new WhiteBlowProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.75F, 4.0F);
    
    return true;
  }
}


