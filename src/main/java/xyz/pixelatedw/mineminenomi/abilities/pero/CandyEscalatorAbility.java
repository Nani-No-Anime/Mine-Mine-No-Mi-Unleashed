package xyz.pixelatedw.mineminenomi.abilities.pero;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.pero.CandyEscalatorProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class CandyEscalatorAbility extends Ability {
  public static final CandyEscalatorAbility INSTANCE = new CandyEscalatorAbility();

  
  public CandyEscalatorAbility() {
    super("Candy Escalator", AbilityHelper.getDevilFruitCategory());
    setDescription("The user creates an escalator made out of candy");
    setMaxCooldown(8.0D);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    CandyEscalatorProjectile proj = new CandyEscalatorProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
    return true;
  }
}


