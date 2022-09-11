package xyz.pixelatedw.mineminenomi.abilities.mogu;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.MoguHeavyZoanInfo;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class MoguraBananaAbility extends PunchAbility implements IFormRequiredAbility {
  public static final MoguraBananaAbility INSTANCE = new MoguraBananaAbility();

  
  public MoguraBananaAbility() {
    super("Mogura Banana", AbilityHelper.getDevilFruitCategory());
    setDescription("Hit the enemy with big mole claws to launch them away");
    setMaxCooldown(6.0D);
    
    this.onHitEntityEvent = this::onHitEntityEvent;
    this.onHitEffectEvent = this::onHitEffectEvent;
  }

  
  private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
    Vec3d speed = WyHelper.propulsion((LivingEntity)player, 3.0D, 3.0D);
    target.setMotion(speed.x, player.getMotion().getY() + 0.1D, speed.z);
    target.velocityChanged = true;
  }

  
  private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
    return 12.0F;
  }


  
  public ZoanInfo[] getRequiredForms(PlayerEntity player) {
    return new ZoanInfo[] { (ZoanInfo)MoguHeavyZoanInfo.INSTANCE };
  }
}


