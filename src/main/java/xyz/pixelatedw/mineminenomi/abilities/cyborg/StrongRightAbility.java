package xyz.pixelatedw.mineminenomi.abilities.cyborg;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg.StrongRightProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class StrongRightAbility extends Ability {
  public static final Ability INSTANCE = new StrongRightAbility();

  
  public StrongRightAbility() {
    super("Strong Right", AbilityHelper.getRacialCategory());
    setMaxCooldown(5.0D);
    setDescription("The user punches the opponent with a metal fist");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    StrongRightProjectile proj = new StrongRightProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
    
    return true;
  }
}


