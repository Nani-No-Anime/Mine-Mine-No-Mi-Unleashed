package xyz.pixelatedw.mineminenomi.abilities.magu;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
import xyz.pixelatedw.mineminenomi.api.abilities.IPunchOverlayAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.magu.DaiFunkaProjectile;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

import java.awt.*;

public class DaiFunkaAbility extends ChargeableAbility implements IPunchOverlayAbility {
  public static final Ability INSTANCE = (Ability)new DaiFunkaAbility();
  private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setTexture(ModResources.DOKU_COATING).setColor(new Color(160, 0, 0));

  
  public DaiFunkaAbility() {
    super("Dai Funka", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(7.0D);
    setMaxChargeTime(1.0D);
    setDescription("Transforms the user's fist into pure magma before expanding and throwing it forward");
    
    this.onEndChargingEvent = this::onEndChargingEvent;
  }

  
  private boolean onEndChargingEvent(PlayerEntity player) {
    DaiFunkaProjectile proj = new DaiFunkaProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 1.0F);
    player.world.playSound(null, player.getPosition(), ModSounds.MAGU_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
    
    return true;
  }


  
  public AbilityOverlay getPunchOverlay(LivingEntity entity) {
    return isCharging() ? OVERLAY : null;
  }
}


