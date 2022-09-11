package xyz.pixelatedw.mineminenomi.abilities.horo;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.horo.TokuHollowProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class TokuHollowAbility extends Ability {
  public static final TokuHollowAbility INSTANCE = new TokuHollowAbility();

  
  public TokuHollowAbility() {
    super("Toku Hollow", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(15.0D);
    setDescription("Creates a huge ghost that causes a massive explosion upon impact");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    TokuHollowProjectile proj = new TokuHollowProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
    
    return true;
  }
}


