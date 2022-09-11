package xyz.pixelatedw.mineminenomi.abilities.noro;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class KyubiRushAbility extends PunchAbility {
  public static final Ability INSTANCE = (Ability)new KyubiRushAbility();

  
  public KyubiRushAbility() {
    super("Kyubi Rush", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(5.0D);
    setDescription("While the opponent is slowed, the user delivers a series of punches, which hits the opponent all at once (a stronger slowness effect causes more damage)");
    
    this.onHitEntityEvent = this::onHitEntity;
  }

  
  private float onHitEntity(PlayerEntity player, LivingEntity target) {
    float damageFromSlowness = 0.0F;
    
    if (target.isPotionActive(ModEffects.NORO_SLOWNESS)) {
      
      damageFromSlowness = (float)(Math.sqrt(target.getActivePotionEffect(ModEffects.NORO_SLOWNESS).getDuration()) / 1.5D);
      int newTime = target.getActivePotionEffect(ModEffects.NORO_SLOWNESS).getDuration() / 2;
      int newAmplifier = Math.min(target.getActivePotionEffect(ModEffects.NORO_SLOWNESS).getAmplifier() - 2, 0);
      target.removePotionEffect(ModEffects.NORO_SLOWNESS);
      target.addPotionEffect(new EffectInstance(ModEffects.NORO_SLOWNESS, newTime, newAmplifier));
    } else {
      
      damageFromSlowness = 1.0F;
    } 
    return damageFromSlowness;
  }
}


