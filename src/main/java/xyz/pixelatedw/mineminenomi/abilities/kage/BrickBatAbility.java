package xyz.pixelatedw.mineminenomi.abilities.kage;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.kage.BrickBatProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;

public class BrickBatAbility extends RepeaterAbility {
  public static final BrickBatAbility INSTANCE = new BrickBatAbility();

  
  public BrickBatAbility() {
    super("Brick Bat", AbilityHelper.getDevilFruitCategory());
    setDescription("Launches bats created from the user's shadow at the opponent");
    setMaxCooldown(8.0D);
    setMaxRepeaterCount(6, 3);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    BrickBatProjectile proj = new BrickBatProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.5F);
    
    return true;
  }
}


