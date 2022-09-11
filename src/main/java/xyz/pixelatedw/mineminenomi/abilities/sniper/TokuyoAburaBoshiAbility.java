package xyz.pixelatedw.mineminenomi.abilities.sniper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.ISniperAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.sniper.TokuyoAburaBoshiProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;

public class TokuyoAburaBoshiAbility extends ContinuousAbility implements ISniperAbility {
  public static final Ability INSTANCE = (Ability)new TokuyoAburaBoshiAbility();

  
  public TokuyoAburaBoshiAbility() {
    super("Tokuyo Abura Boshi", AbilityHelper.getStyleCategory());
    setMaxCooldown(7.0D);
    setDescription("Fires a pellet that upon impact releases oil making the ground slippery");
  }


  
  public void shoot(PlayerEntity player) {
    TokuyoAburaBoshiProjectile proj = new TokuyoAburaBoshiProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
  }
}


