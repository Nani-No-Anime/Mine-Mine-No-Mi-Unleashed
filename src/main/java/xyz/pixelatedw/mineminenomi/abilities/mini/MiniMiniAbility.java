package xyz.pixelatedw.mineminenomi.abilities.mini;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.MiniZoanInfo;

public class MiniMiniAbility extends ZoanAbility {
  public static final MiniMiniAbility INSTANCE = new MiniMiniAbility();

  
  public MiniMiniAbility() {
    super("Mini Mini", AbilityHelper.getDevilFruitCategory());
    setDescription("Allows the user to become smaller than their actual size.");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }


  
  public boolean onStartContinuityEvent(PlayerEntity player) {
    if (super.onStartContinuityEvent(player)) {
      
      AbilityHelper.setPose((LivingEntity)player, Pose.STANDING);
      return true;
    } 
    
    return false;
  }


  
  protected boolean onEndContinuityEvent(PlayerEntity player) {
    return super.onEndContinuityEvent(player);
  }


  
  public ZoanInfo getTransformation() {
    return (ZoanInfo)MiniZoanInfo.INSTANCE;
  }
}


