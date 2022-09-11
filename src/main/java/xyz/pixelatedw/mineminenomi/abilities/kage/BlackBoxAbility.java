package xyz.pixelatedw.mineminenomi.abilities.kage;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.kage.BlackBoxProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class BlackBoxAbility extends Ability {
  public static final BlackBoxAbility INSTANCE = new BlackBoxAbility();

  
  public BlackBoxAbility() {
    super("Black Box", AbilityHelper.getDevilFruitCategory());
    setDescription("Encases and suffocates the opponent in a box made of shadows");
    setMaxCooldown(16.0D);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    BlackBoxProjectile proj = new BlackBoxProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0.5F);
    
    return true;
  }
}


