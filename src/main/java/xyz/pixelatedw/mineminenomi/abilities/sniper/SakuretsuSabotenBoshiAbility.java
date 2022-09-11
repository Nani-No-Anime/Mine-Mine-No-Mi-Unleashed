package xyz.pixelatedw.mineminenomi.abilities.sniper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.ISniperAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.sniper.SakuretsuSabotenBoshiProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;

public class SakuretsuSabotenBoshiAbility extends ContinuousAbility implements ISniperAbility {
  public static final Ability INSTANCE = (Ability)new SakuretsuSabotenBoshiAbility();

  
  public SakuretsuSabotenBoshiAbility() {
    super("Sakuretsu Saboten Boshi", AbilityHelper.getStyleCategory());
    setMaxCooldown(10.0D);
    setDescription("The fired projectile explodes on impact and creates cacti arond the target to trap them");
  }


  
  public void shoot(PlayerEntity player) {
    SakuretsuSabotenBoshiProjectile proj = new SakuretsuSabotenBoshiProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 3.0F);
  }
}


