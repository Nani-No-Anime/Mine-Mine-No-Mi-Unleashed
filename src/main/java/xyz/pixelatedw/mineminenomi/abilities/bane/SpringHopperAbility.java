package xyz.pixelatedw.mineminenomi.abilities.bane;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.SpringLegsZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class SpringHopperAbility extends ZoanAbility {
  public static final Ability INSTANCE = (Ability)new SpringHopperAbility();
  public int jumpPower = 0;
  
  public boolean canIncreaseJumpPower = false;
  
  public SpringHopperAbility() {
    super("Spring Hopper", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(10.0D);
    setThreshold(50.0D);
    setDescription("By turning the user's legs into springs, they can jump around with great ease bouncing around surfaces");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContiunityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
    this.onStopContinuityEvent = this::onStopContinuityEvent;
  }


  
  protected boolean onStartContinuityEvent(PlayerEntity player) {
    return (super.onStartContinuityEvent(player) && AbilityHelper.canUseMomentumAbility(player));
  }

  
  private void duringContiunityEvent(PlayerEntity player, int chargeTime) {
    if (!AbilityHelper.canUseMomentumAbility(player) || player.hurtResistantTime > 0) {
      endContinuity(player);
    }
  }

  
  protected boolean onEndContinuityEvent(PlayerEntity player) {
    return super.onEndContinuityEvent(player);
  }


  
  protected void onStopContinuityEvent(PlayerEntity player) {
    player.getAttribute(ModAttributes.JUMP_HEIGHT).removeModifier(SpringMovementAbility.SPRING_POWER_UUID);
    super.onStopContinuityEvent(player);
  }


  
  public ZoanInfo getTransformation() {
    return (ZoanInfo)SpringLegsZoanInfo.INSTANCE;
  }
}


