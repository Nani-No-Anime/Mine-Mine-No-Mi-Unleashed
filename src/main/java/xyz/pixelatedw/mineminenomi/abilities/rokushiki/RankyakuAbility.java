package xyz.pixelatedw.mineminenomi.abilities.rokushiki;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.rokushiki.RankyakuProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class RankyakuAbility extends Ability {
  public static final Ability INSTANCE = new RankyakuAbility();

  
  public RankyakuAbility() {
    super("Rankyaku", AbilityHelper.getRacialCategory());
    setMaxCooldown(12.0D);
    setDescription("By kicking at a very high speed, the user launches an air blade at the opponent");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    RankyakuProjectile proj = new RankyakuProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.25F, 1.0F);
    
    return true;
  }
}


