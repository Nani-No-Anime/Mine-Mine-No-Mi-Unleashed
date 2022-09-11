package xyz.pixelatedw.mineminenomi.abilities.gasu;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.gasu.BigGastilleProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.gasu.GastilleProjectile;
import xyz.pixelatedw.mineminenomi.entities.zoan.ShinokuniZoanInfo;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class GastilleAbility extends Ability {
  public static final GastilleAbility INSTANCE = new GastilleAbility();

  
  public GastilleAbility() {
    super("Gastille", AbilityHelper.getDevilFruitCategory());
    setDescription("shoots a beam of lit gas from the users mouth, imbued a effect, that explodes on impact");
    setMaxCooldown(7.0D);
    
    this.onUseEvent = this::onUseEvent;
  }


  
  private boolean onUseEvent(PlayerEntity player) {
    if (ShinokuniZoanInfo.INSTANCE.isActive((LivingEntity)player)) {
      
      BigGastilleProjectile bigGastilleProjectile = new BigGastilleProjectile(player.world, (LivingEntity)player);
      player.world.addEntity((Entity)bigGastilleProjectile);
      bigGastilleProjectile.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 1.0F);
      return true;
    } 
    
    GastilleProjectile proj = new GastilleProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.5F, 1.0F);
    
    return true;
  }
}


