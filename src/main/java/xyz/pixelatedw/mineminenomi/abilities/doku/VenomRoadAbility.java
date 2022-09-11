package xyz.pixelatedw.mineminenomi.abilities.doku;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.doku.DemonHydraProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.doku.HydraProjectile;
import xyz.pixelatedw.mineminenomi.entities.zoan.VenomDemonZoanInfo;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class VenomRoadAbility extends Ability {
  public static final VenomRoadAbility INSTANCE = new VenomRoadAbility();

  
  public VenomRoadAbility() {
    super("Venom Road", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(8.0D);
    setDescription("The user fires a Hydra at the target location and transports there through its path");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    AbilityProjectileEntity proj = VenomDemonZoanInfo.INSTANCE.isActive((LivingEntity)player) ? (AbilityProjectileEntity)new DemonHydraProjectile(player.world, (LivingEntity)player, true) : (AbilityProjectileEntity)new HydraProjectile(player.world, (LivingEntity)player, true);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 1.0F);
    
    return true;
  }

  
  public void enableVenomDemoMode() {
    setCustomTexture("venom_road_venom");
    setDisplayName("Demon Road");
  }

  
  public void disableVenomDemoMode() {
    setCustomTexture("");
    setDisplayName("Venom Road");
  }
}


