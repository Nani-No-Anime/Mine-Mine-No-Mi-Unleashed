package xyz.pixelatedw.mineminenomi.abilities.hitodaibutsu;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.projectiles.hitodaibutsu.ImpactBlastProjectile;
import xyz.pixelatedw.mineminenomi.entities.zoan.HitoDaibutsuZoanInfo;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchTriggerAbility;

public class ImpactBlastAbility extends PunchTriggerAbility implements IFormRequiredAbility {
  public static final Ability INSTANCE = (Ability)new ImpactBlastAbility();

  
  public ImpactBlastAbility() {
    super("Impact Blast", AbilityHelper.getDevilFruitCategory());
    setDescription("Launches a golden shock wave forward when punching an enemy or the air, hurting every entity in its path");
    setMaxCooldown(6.0D);
    
    stopAfterUsage(true);
    this.onSwingEvent = this::onSwingEvent;
  }

  
  private boolean onSwingEvent(PlayerEntity player) {
    ImpactBlastProjectile proj = new ImpactBlastProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0.0F);
    return true;
  }


  
  public ZoanInfo[] getRequiredForms(PlayerEntity player) {
    return new ZoanInfo[] { (ZoanInfo)HitoDaibutsuZoanInfo.INSTANCE };
  }
}


