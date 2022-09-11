package xyz.pixelatedw.mineminenomi.abilities.fishmankarate;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class KachiageHaisokuAbility extends PunchAbility {
  public static final Ability INSTANCE = (Ability)new KachiageHaisokuAbility();

  
  public KachiageHaisokuAbility() {
    super("Kachiage Haisoku", AbilityHelper.getRacialCategory());
    setDescription("The user delivers a powerful kick to the opponent's chin, which is stronger inside water");
    setMaxCooldown(8.0D);
    
    this.onHitEntityEvent = this::onHitEntity;
  }

  
  private float onHitEntity(PlayerEntity player, LivingEntity target) {
    return player.isInWater() ? 40.0F : 20.0F;
  }
}


