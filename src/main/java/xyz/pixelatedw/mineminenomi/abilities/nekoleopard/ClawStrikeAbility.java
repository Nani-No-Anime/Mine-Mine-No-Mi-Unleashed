package xyz.pixelatedw.mineminenomi.abilities.nekoleopard;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.LeopardHeavyZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.LeopardWalkZoanInfo;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class ClawStrikeAbility extends PunchAbility implements IFormRequiredAbility {
  public static final ClawStrikeAbility INSTANCE = new ClawStrikeAbility();

  
  public ClawStrikeAbility() {
    super("Claw Strike", AbilityHelper.getDevilFruitCategory());
    setDescription("Hits using the user's sharp claws");
    setMaxCooldown(5.0D);
    
    this.onHitEntityEvent = this::onHitEntityEvent;
  }

  
  private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
    return 20.0F;
  }


  
  public ZoanInfo[] getRequiredForms(PlayerEntity player) {
    return new ZoanInfo[] { (ZoanInfo)LeopardHeavyZoanInfo.INSTANCE, (ZoanInfo)LeopardWalkZoanInfo.INSTANCE };
  }
}


