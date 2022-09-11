package xyz.pixelatedw.mineminenomi.abilities.kage;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.KageHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class KageKakumeiAbility extends PunchAbility {
  public static final Ability INSTANCE = (Ability)new KageKakumeiAbility();
  
  private static final int SHADOWS_REQUIRED = 20;

  
  public KageKakumeiAbility() {
    super("Kage Kakumei", AbilityHelper.getDevilFruitCategory());
    setDescription("By hitting another entity the user impales §220§r shadows at once into them boosting their physical abilities");
    setMaxCooldown(20.0D);
    
    this.onHitEntityEvent = this::onHitEntityEvent;
    this.onHitEffectEvent = this::onHitEffectEvent;
  }

  
  private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
    target.addPotionEffect(new EffectInstance(Effects.SPEED, 400, 0));
    target.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 400, 0));
    target.addPotionEffect(new EffectInstance(Effects.STRENGTH, 400, 0));
    target.addPotionEffect(new EffectInstance(Effects.HEALTH_BOOST, 400, 0));
    target.addPotionEffect(new EffectInstance(Effects.REGENERATION, 400, 0));
    KageHelper.removeShadows(player, 20);
  }

  
  private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
    if (KageHelper.getAvailableShadows(player) < 20) {
      
      player.sendMessage(KageHelper.NOT_ENOUGH_SHADOWS_WARN);
      return -1.0F;
    } 
    
    return 1.0F;
  }
}


