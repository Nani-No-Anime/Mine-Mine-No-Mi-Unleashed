package xyz.pixelatedw.mineminenomi.abilities.mero;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.mero.PistolKissProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class PistolKissAbility extends Ability {
  public static final PistolKissAbility INSTANCE = new PistolKissAbility();

  
  public PistolKissAbility() {
    super("Pistol Kiss", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(5.0D);
    setDescription("A weaker but faster variant of 'Mero Mero Mellow'");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    PistolKissProjectile proj = new PistolKissProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.5F, 1.0F);
    
    return true;
  }
}


