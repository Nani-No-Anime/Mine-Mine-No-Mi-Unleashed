package xyz.pixelatedw.mineminenomi.abilities.doctor;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class DopingAbility extends PunchAbility {
  public static final DopingAbility INSTANCE = new DopingAbility();

  
  public DopingAbility() {
    super("Doping", AbilityHelper.getStyleCategory());
    setDescription("The user injects a target with special medicine providing boosting their physical powers\n\n§2SHIFT-USE§r: The user injects themselves");
    setMaxCooldown(25.0D);
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.onHitEntityEvent = this::onHitEntity;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    if (player.isSneaking()) {
      
      player.addPotionEffect(new EffectInstance(ModEffects.TENSION_HORMONE, 200, 1));
      endContinuity(player);
      return false;
    } 
    
    return true;
  }

  
  private float onHitEntity(PlayerEntity player, LivingEntity target) {
    target.addPotionEffect(new EffectInstance(ModEffects.TENSION_HORMONE, 200, 1));
    return 0.0F;
  }
}


