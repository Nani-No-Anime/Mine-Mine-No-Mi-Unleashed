package xyz.pixelatedw.mineminenomi.abilities.zou;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZouHeavyZoanInfo;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class IvoryStompAbility extends PunchAbility implements IFormRequiredAbility {
  public static final IvoryStompAbility INSTANCE = new IvoryStompAbility();

  
  public IvoryStompAbility() {
    super("Ivory Stomp", AbilityHelper.getDevilFruitCategory());
    setDescription("A strong punch from a hybrid elephant form, which launches the enemy");
    setMaxCooldown(8.0D);
    
    this.onHitEntityEvent = this::onHitEntityEvent;
    this.onHitEffectEvent = this::onHitEffectEvent;
  }

  
  private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
    Vec3d speed = WyHelper.propulsion((LivingEntity)player, 2.5D, 2.5D);
    target.setMotion(speed.x, player.getMotion().getY() + 0.1D, speed.z);
    target.velocityChanged = true;
  }

  
  private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
    return 19.0F;
  }


  
  public ZoanInfo[] getRequiredForms(PlayerEntity player) {
    return new ZoanInfo[] { (ZoanInfo)ZouHeavyZoanInfo.INSTANCE };
  }
}


