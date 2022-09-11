package xyz.pixelatedw.mineminenomi.abilities.suke;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.suke.ShishaNoTeProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class ShishaNoTeAbility extends Ability {
  public static final Ability INSTANCE = new ShishaNoTeAbility();

  
  public ShishaNoTeAbility() {
    super("Shisha no Te", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(8.0D);
    setDescription("Shoots invisible projectiles that explode upon impact");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    ShishaNoTeProjectile proj = new ShishaNoTeProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
    
    return true;
  }
}


