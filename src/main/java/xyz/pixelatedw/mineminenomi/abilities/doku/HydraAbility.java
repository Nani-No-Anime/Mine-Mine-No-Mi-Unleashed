package xyz.pixelatedw.mineminenomi.abilities.doku;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.doku.DemonHydraProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.doku.HydraProjectile;
import xyz.pixelatedw.mineminenomi.entities.zoan.VenomDemonZoanInfo;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class HydraAbility extends Ability {
  public static final HydraAbility INSTANCE = new HydraAbility();

  
  public HydraAbility() {
    super("Hydra", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(5.0D);
    setDescription("Launches a dragon made out of liquid poison at the opponent");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    AbilityProjectileEntity proj = VenomDemonZoanInfo.INSTANCE.isActive((LivingEntity)player) ? (AbilityProjectileEntity)new DemonHydraProjectile(player.world, (LivingEntity)player, false) : (AbilityProjectileEntity)new HydraProjectile(player.world, (LivingEntity)player, false);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
    
    return true;
  }

  
  public void enableVenomDemoMode() {
    setDisplayName("Demon Hydra");
    setCustomTexture("hydra_venom");
  }

  
  public void disableVenomDemoMode() {
    setDisplayName("Hydra");
    setCustomTexture("");
  }
}


