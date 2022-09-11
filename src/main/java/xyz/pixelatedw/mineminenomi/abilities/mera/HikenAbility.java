package xyz.pixelatedw.mineminenomi.abilities.mera;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.mera.HikenProjectile;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class HikenAbility extends Ability {
  public static final Ability INSTANCE = new HikenAbility();

  
  public HikenAbility() {
    super("Hiken", AbilityHelper.getDevilFruitCategory());
    setDescription("Turns the user's fist into flames and launches it towards the target");
    setMaxCooldown(12.0D);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    HikenProjectile proj = new HikenProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
    player.world.playSound(null, player.getPosition(), ModSounds.MERA_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
    
    return true;
  }
}


