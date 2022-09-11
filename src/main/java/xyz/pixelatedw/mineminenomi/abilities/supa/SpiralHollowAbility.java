package xyz.pixelatedw.mineminenomi.abilities.supa;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.supa.SpiralHollowProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class SpiralHollowAbility extends Ability {
  public static final SpiralHollowAbility INSTANCE = new SpiralHollowAbility();

  
  public SpiralHollowAbility() {
    super("Spiral Hollow", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(9.0D);
    setDescription("Creates circular blades along the user's forearms slicing enemies in a close line");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    SpiralHollowProjectile proj = new SpiralHollowProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
    
    return true;
  }
}


