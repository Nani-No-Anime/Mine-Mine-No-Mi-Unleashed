package xyz.pixelatedw.mineminenomi.abilities.sniper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.ISniperAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.sniper.KaenBoshiProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;

public class KaenBoshiAbility extends ContinuousAbility implements ISniperAbility {
  public static final Ability INSTANCE = (Ability)new KaenBoshiAbility();

  
  public KaenBoshiAbility() {
    super("Kaen Boshi", AbilityHelper.getStyleCategory());
    setDescription("Fires a flaming pellet, that sets the target on fire");
    setMaxCooldown(5.0D);
  }


  
  public void shoot(PlayerEntity player) {
    KaenBoshiProjectile proj = new KaenBoshiProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
  }
}


