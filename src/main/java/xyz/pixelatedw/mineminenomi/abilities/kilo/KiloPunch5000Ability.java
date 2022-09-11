package xyz.pixelatedw.mineminenomi.abilities.kilo;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class KiloPunch5000Ability extends PunchAbility {
  public static final KiloPunch5000Ability INSTANCE = new KiloPunch5000Ability();

  
  public KiloPunch5000Ability() {
    super("5000 Kilo Punch", AbilityHelper.getDevilFruitCategory());
    setDescription("Delivers a 5000 kilo punch, the user is slowed down due to the extra kilos");
    setMaxCooldown(7.0D);
    
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onHitEntityEvent = this::onHitEntityEvent;
  }

  
  private void duringContinuityEvent(PlayerEntity player, int time) {
    player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 10, 5, false, false));
  }

  
  private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
    return 20.0F;
  }
}


