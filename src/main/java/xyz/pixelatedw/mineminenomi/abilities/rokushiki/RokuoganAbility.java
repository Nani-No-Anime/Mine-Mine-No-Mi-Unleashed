package xyz.pixelatedw.mineminenomi.abilities.rokushiki;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.rokushiki.RokuoganProjectile;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchTriggerAbility;

public class RokuoganAbility extends PunchTriggerAbility {
  public static final RokuoganAbility INSTANCE = new RokuoganAbility();

  
  public RokuoganAbility() {
    super("Rokuogan", AbilityHelper.getRacialCategory());
    setMaxCooldown(50.0D);
    setDescription("The user places both their fists right in front of the target to focus their physical strength to launch a devastating shockwave forward");
    
    stopAfterUsage(true);
    this.onSwingEvent = this::onSwingEvent;
  }

  
  private boolean onSwingEvent(PlayerEntity player) {
    RokuoganProjectile proj = new RokuoganProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
    
    player.world.playSound(null, player.getPosition(), ModSounds.ROKUOGAN, SoundCategory.PLAYERS, 1.0F, 0.2F + player.getRNG().nextFloat());
    
    return true;
  }
}


