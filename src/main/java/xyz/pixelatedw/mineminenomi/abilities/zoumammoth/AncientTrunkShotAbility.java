package xyz.pixelatedw.mineminenomi.abilities.zoumammoth;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.MammothGuardZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.MammothHeavyZoanInfo;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class AncientTrunkShotAbility extends PunchAbility implements IFormRequiredAbility {
  public static final AncientTrunkShotAbility INSTANCE = new AncientTrunkShotAbility();

  
  public AncientTrunkShotAbility() {
    super("Ancient Trunk Shot", AbilityHelper.getDevilFruitCategory());
    setDescription("Hit using the massive trunk");
    setMaxCooldown(5.0D);
    
    this.onHitEntityEvent = this::onHitEntityEvent;
    this.onHitEffectEvent = this::onHitEffectEvent;
  }

  
  private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
    Vec3d dirVec = player.getPositionVec().subtract(target.getPositionVec()).normalize().mul(5.0D, 1.0D, 5.0D);
    target.setMotion(-dirVec.x, 0.25D, -dirVec.z);
    target.velocityChanged = true;
  }

  
  private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
    return 15.0F;
  }


  
  public ZoanInfo[] getRequiredForms(PlayerEntity player) {
    return new ZoanInfo[] { (ZoanInfo)MammothGuardZoanInfo.INSTANCE, (ZoanInfo)MammothHeavyZoanInfo.INSTANCE };
  }
}


