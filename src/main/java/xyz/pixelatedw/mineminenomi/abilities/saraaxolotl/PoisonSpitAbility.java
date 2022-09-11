package xyz.pixelatedw.mineminenomi.abilities.saraaxolotl;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.projectiles.saraaxolotl.PoisonSpitProjectile;
import xyz.pixelatedw.mineminenomi.entities.zoan.AxolotlHeavyZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.AxolotlWalkZoanInfo;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class PoisonSpitAbility extends Ability implements IFormRequiredAbility {
  public static final PoisonSpitAbility INSTANCE = new PoisonSpitAbility();

  
  public PoisonSpitAbility() {
    super("Poison Spit", AbilityHelper.getDevilFruitCategory());
    setDescription("Spits a small dose of poison.");
    setMaxCooldown(7.0D);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    PoisonSpitProjectile proj = new PoisonSpitProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
    
    return true;
  }


  
  public ZoanInfo[] getRequiredForms(PlayerEntity player) {
    return new ZoanInfo[] { (ZoanInfo)AxolotlHeavyZoanInfo.INSTANCE, (ZoanInfo)AxolotlWalkZoanInfo.INSTANCE };
  }
}


