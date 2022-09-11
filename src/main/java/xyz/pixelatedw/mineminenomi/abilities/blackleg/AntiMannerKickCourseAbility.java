package xyz.pixelatedw.mineminenomi.abilities.blackleg;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class AntiMannerKickCourseAbility extends PunchAbility {
  public static final AntiMannerKickCourseAbility INSTANCE = new AntiMannerKickCourseAbility();

  
  public AntiMannerKickCourseAbility() {
    super("Anti-Manner Kick Course", AbilityHelper.getStyleCategory());
    setMaxCooldown(12.0D);
    setDescription("Kicks an enemy and launches them vertically");
    
    this.onHitEntityEvent = this::onHitEntity;
    this.onHitEffectEvent = this::onHitEffectEvent;
  }

  
  private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
    target.setPosition(target.getPosX(), target.getPosY() + 10.0D, target.getPosZ());
    target.addPotionEffect(new EffectInstance(Effects.NAUSEA, 40, 0));
  }

  
  private float onHitEntity(PlayerEntity player, LivingEntity target) {
    return 25.0F;
  }
}


