package xyz.pixelatedw.mineminenomi.abilities.zou;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.projectiles.zou.TrunkShotProjectile;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZouGuardZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZouHeavyZoanInfo;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class TrunkShotAbility extends Ability implements IFormRequiredAbility {
  public static final TrunkShotAbility INSTANCE = new TrunkShotAbility();

  
  public TrunkShotAbility() {
    super("Trunk Shot", AbilityHelper.getDevilFruitCategory());
    setDescription("Launches an elephant trunk at the enemy");
    setMaxCooldown(8.0D);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    TrunkShotProjectile proj = new TrunkShotProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.5F, 0.0F);
    
    return true;
  }


  
  public ZoanInfo[] getRequiredForms(PlayerEntity player) {
    return new ZoanInfo[] { (ZoanInfo)ZouGuardZoanInfo.INSTANCE, (ZoanInfo)ZouHeavyZoanInfo.INSTANCE };
  }
}


