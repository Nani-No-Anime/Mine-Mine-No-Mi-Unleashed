package xyz.pixelatedw.mineminenomi.abilities.netsu;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.netsu.NekkaiGyoraiProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;

public class NekkaiGyoraiAbility extends RepeaterAbility {
  public static final NekkaiGyoraiAbility INSTANCE = new NekkaiGyoraiAbility();

  
  public NekkaiGyoraiAbility() {
    super("Nekkai Gyorai", AbilityHelper.getDevilFruitCategory());
    setDescription("Shoots heat-torpedoes, exploding and setting the enemy on fire");
    setMaxCooldown(5.0D);
    setMaxRepeaterCount(3, 5);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    NekkaiGyoraiProjectile proj = new NekkaiGyoraiProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
    return true;
  }
}


