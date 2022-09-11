package xyz.pixelatedw.mineminenomi.abilities.sniper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.ISniperAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.sniper.RenpatsuNamariBoshiProjectile;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;

public class RenpatsuNamariBoshiAbility extends ContinuousAbility implements ISniperAbility {
  public static final RenpatsuNamariBoshiAbility INSTANCE = new RenpatsuNamariBoshiAbility();

  
  public RenpatsuNamariBoshiAbility() {
    super("Renpatsu Namari Boshi", AbilityHelper.getStyleCategory());
    setMaxCooldown(6.0D);
    setDescription("Lets the user fire a barrage of exploding shots");
  }


  
  public void shoot(PlayerEntity player) {
    for (int i = 0; i < 4; i++) {
      
      RenpatsuNamariBoshiProjectile proj = new RenpatsuNamariBoshiProjectile(player.world, (LivingEntity)player);
      proj.setLocationAndAngles(player
          .getPosX() + WyHelper.randomWithRange(-1, 1) / 2.0D + WyHelper.randomDouble(), player
          .getPosY() + 0.75D + WyHelper.randomDouble(), player
          .getPosZ() + WyHelper.randomWithRange(-1, 1) / 2.0D + WyHelper.randomDouble(), 0.0F, 0.0F);
      
      player.world.addEntity((Entity)proj);
      proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 5.0F);
    } 
  }
}


