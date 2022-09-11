package xyz.pixelatedw.mineminenomi.abilities.hie;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.hie.IceBlockPartisanProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;

public class IceBlockPartisanAbility extends RepeaterAbility {
  public static final Ability INSTANCE = (Ability)new IceBlockPartisanAbility();

  
  public IceBlockPartisanAbility() {
    super("Ice Block: Partisan", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(10.0D);
    setMaxRepeaterCount(8, 4);
    setDescription("Creates several spears of ice that the user hurls at the enemy");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    IceBlockPartisanProjectile proj = new IceBlockPartisanProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
    
    return true;
  }
}


