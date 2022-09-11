package xyz.pixelatedw.mineminenomi.abilities.bari;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class BariBariNoPistolAbility extends PunchAbility {
  public static final BariBariNoPistolAbility INSTANCE = new BariBariNoPistolAbility();

  
  public BariBariNoPistolAbility() {
    super("Bari Bari no Pistol", AbilityHelper.getDevilFruitCategory());
    setDescription("The user shapes a barrier aroud their fist, which greatly increases the power of a punch");
    
    setMaxCooldown(5.0D);
    this.onHitEntityEvent = this::onHitEntity;
  }

  
  private float onHitEntity(PlayerEntity player, LivingEntity target) {
    return 12.0F;
  }
}


