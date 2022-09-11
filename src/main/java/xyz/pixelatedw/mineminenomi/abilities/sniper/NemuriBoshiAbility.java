package xyz.pixelatedw.mineminenomi.abilities.sniper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.ISniperAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.sniper.NemuriBoshiProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;

public class NemuriBoshiAbility extends ContinuousAbility implements ISniperAbility {
  public static final Ability INSTANCE = (Ability)new NemuriBoshiAbility();

  
  public NemuriBoshiAbility() {
    super("Nemuri Boshi", AbilityHelper.getStyleCategory());
    setMaxCooldown(20.0D);
    setDescription("Fires a pellet, that puts the enemy to sleep for a few seconds");
  }


  
  public void shoot(PlayerEntity player) {
    NemuriBoshiProjectile proj = new NemuriBoshiProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 1.0F);
  }
}


