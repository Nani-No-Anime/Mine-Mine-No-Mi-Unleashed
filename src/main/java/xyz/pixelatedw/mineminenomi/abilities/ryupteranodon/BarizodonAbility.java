package xyz.pixelatedw.mineminenomi.abilities.ryupteranodon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.projectiles.ryupteranodon.BarizodonProjectile;
import xyz.pixelatedw.mineminenomi.entities.zoan.PteranodonAssaultZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.PteranodonFlyZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;

public class BarizodonAbility extends RepeaterAbility implements IFormRequiredAbility {
  public static final BarizodonAbility INSTANCE = new BarizodonAbility();

  
  public BarizodonAbility() {
    super("Barizodon", AbilityHelper.getDevilFruitCategory());
    setDescription("Shoots out a barrage of elliptic air projectiles using the user's wings");
    setMaxCooldown(7.0D);
    setMaxRepeaterCount(8, 2);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    for (int i = 0; i < WyHelper.randomWithRange(1, 4); i++) {
      
      BarizodonProjectile proj = new BarizodonProjectile(player.world, (LivingEntity)player);
      player.world.addEntity((Entity)proj);
      proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 10.0F);
    } 
    
    player.world.playSound(null, player.getPosition(), ModSounds.DASH_ABILITY_SWOOSH_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
    
    return true;
  }


  
  public ZoanInfo[] getRequiredForms(PlayerEntity player) {
    return new ZoanInfo[] { (ZoanInfo)PteranodonFlyZoanInfo.INSTANCE, (ZoanInfo)PteranodonAssaultZoanInfo.INSTANCE };
  }
}


