package xyz.pixelatedw.mineminenomi.abilities.ope;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.ope.GammaKnifeProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class GammaKnifeAbility extends Ability {
  public static final Ability INSTANCE = new GammaKnifeAbility();

  
  public GammaKnifeAbility() {
    super("Gamma Knife", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(25.0D);
    setDescription("Creates a blade of gamma radiation which massively damages the opponent's organs");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    if (!OpeHelper.hasRoomActive(player, this)) {
      return false;
    }
    GammaKnifeProjectile proj = new GammaKnifeProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
    
    return true;
  }
}


