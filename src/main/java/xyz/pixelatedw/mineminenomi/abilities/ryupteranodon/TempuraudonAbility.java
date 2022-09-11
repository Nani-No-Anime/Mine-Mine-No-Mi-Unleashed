package xyz.pixelatedw.mineminenomi.abilities.ryupteranodon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.projectiles.ryupteranodon.TempuraudonProjectile;
import xyz.pixelatedw.mineminenomi.entities.zoan.PteranodonAssaultZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.PteranodonFlyZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

public class TempuraudonAbility extends ChargeableAbility implements IFormRequiredAbility {
  public static final TempuraudonAbility INSTANCE = new TempuraudonAbility();

  
  public TempuraudonAbility() {
    super("Tempuraudon", AbilityHelper.getDevilFruitCategory());
    setDescription("Stretches its head back, releasing it really fast and acting as a sniper");
    setMaxChargeTime(1.5D);
    setMaxCooldown(12.0D);
    
    this.onEndChargingEvent = this::onEndChargingEvent;
  }

  
  private boolean onEndChargingEvent(PlayerEntity player) {
    TempuraudonProjectile proj = new TempuraudonProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 5.0F, 0.0F);
    player.world.playSound(null, player.getPosition(), ModSounds.DASH_ABILITY_SWOOSH_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
    
    return true;
  }


  
  public ZoanInfo[] getRequiredForms(PlayerEntity player) {
    return new ZoanInfo[] { (ZoanInfo)PteranodonFlyZoanInfo.INSTANCE, (ZoanInfo)PteranodonAssaultZoanInfo.INSTANCE };
  }
}


