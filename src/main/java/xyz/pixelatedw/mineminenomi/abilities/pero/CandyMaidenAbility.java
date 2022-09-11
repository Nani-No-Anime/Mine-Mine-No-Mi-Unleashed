package xyz.pixelatedw.mineminenomi.abilities.pero;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.pero.CandyMaidenProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class CandyMaidenAbility extends Ability {
  public static final CandyMaidenAbility INSTANCE = new CandyMaidenAbility();

  
  public CandyMaidenAbility() {
    super("Candy Maiden", AbilityHelper.getDevilFruitCategory());
    setDescription("Creates a gigant maiden shaped projectile that slows down any entity in it's way");
    
    setMaxCooldown(10.0D);
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    CandyMaidenProjectile proj = new CandyMaidenProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
    return true;
  }
}


