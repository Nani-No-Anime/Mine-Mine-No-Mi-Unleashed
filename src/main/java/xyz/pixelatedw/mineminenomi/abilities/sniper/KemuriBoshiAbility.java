package xyz.pixelatedw.mineminenomi.abilities.sniper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.ISniperAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.sniper.KemuriBoshiProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;

public class KemuriBoshiAbility extends ContinuousAbility implements ISniperAbility {
  public static final Ability INSTANCE = (Ability)new KemuriBoshiAbility();

  
  public KemuriBoshiAbility() {
    super("Kemuri Boshi", AbilityHelper.getStyleCategory());
    setMaxCooldown(6.0D);
    setDescription("On impact releases smoke that poisons and confuses targets");
  }


  
  public void shoot(PlayerEntity player) {
    KemuriBoshiProjectile proj = new KemuriBoshiProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
  }
}


